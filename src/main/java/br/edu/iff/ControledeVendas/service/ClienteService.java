
package br.edu.iff.ControledeVendas.service;

import br.edu.iff.ControledeVendas.model.Cliente;
import br.edu.iff.ControledeVendas.model.Pessoa;
import br.edu.iff.ControledeVendas.repository.ClienteRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClienteService {
     @Autowired
    private ClienteRepository repo;

    public List<Cliente> findAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Cliente findById(Long id) throws NotFoundException {
        Optional<Cliente> result = repo.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException("Cliente não encontrado.");
        }
        return result.get();
    }

    public Cliente save(Cliente c, MultipartFile file) {
        //arquivo
        if (file != null) {
            if (!file.isEmpty()) {
                salvarArquivo(file, c.getCpf() + ".pdf");
                c.setDocumentos(c.getCpf() + ".pdf");
            } else {
                c.setDocumentos(null);
            }
        }
        //Verifica se cpf e email já são cadastrados
        verificaCpfEmailCadastrado(c.getCpf(), c.getEmail());
        try {
            return repo.save(c);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o cliente.");
        }
    }

    public Cliente update(Cliente c, MultipartFile file) throws NotFoundException {
        //Cliente já existe
        Cliente obj = findById(c.getId());
        //arquivo
        c.setDocumentos(obj.getDocumentos());
        if (file != null) {
            if (!file.isEmpty()) {
                salvarArquivo(file, c.getCpf() + ".pdf");
                c.setDocumentos(c.getCpf() + ".pdf");
            }
        }
        try {
            c.setCpf(obj.getCpf());
            c.setEmail(obj.getEmail());
            return repo.save(c);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao atualizar o cliente.");
        }
    }

    public void delete(Long id) throws NotFoundException {
        Cliente obj = findById(id);
        //verificar se há reservas
        verificaExclusaoClienteComPedidos(obj);
        try {
            repo.delete(obj);
            if (obj.getDocumentos() != null) {
                Path caminho = Paths.get("uploads", obj.getDocumentos());
                Files.deleteIfExists(caminho);
            }
        } catch (Exception e) {
            throw new RuntimeException("Falha ao excluir o cliente.");
        }
    }

    private void salvarArquivo(MultipartFile file, String novoNome) {
        if (file.getContentType().equals(MediaType.APPLICATION_PDF_VALUE)) {
            Path dest = Paths.get("uploads", novoNome);
            try {
                file.transferTo(dest);
            } catch (IOException ex) {
                throw new RuntimeException("Falha ao salvar o arquivo.");
            }
        } else {
            throw new RuntimeException("Arquivo deve ser do tipo PDF.");
        }
    }

    private void verificaCpfEmailCadastrado(String cpf, String email) {
        List<Pessoa> result = repo.findByCpfOrEmail(cpf, email);
        if (!result.isEmpty()) {
            throw new RuntimeException("CPF ou EMAIL já cadastrados.");
        }
    }

    private void verificaExclusaoClienteComPedidos(Cliente c) {
        if (!c.getPedidos().isEmpty()) {
            throw new RuntimeException("Cliente possui reservas. Não pode ser excluído.");
        }
    }
}
