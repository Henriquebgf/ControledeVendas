package br.edu.iff.ControledeVendas.service;

import br.edu.iff.ControledeVendas.model.Produto;
import br.edu.iff.ControledeVendas.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    public List<Produto> findAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }

    public List<Produto> findAll() {
        return repo.findAll();
    }

    public Produto findById(Long id) throws NotFoundException {
        Optional<Produto> result = repo.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException("Produto não encontrado.");
        }
        return result.get();
    }

    public Produto save(Produto p) {
        verificaDescricaoCadastrado(p.getDescricao());
        try {
            return repo.save(p);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o Produto.");
        }
    }

    public Produto update(Produto p) throws NotFoundException {
        Produto obj = findById(p.getId());
        try {
            p.setDescricao(obj.getDescricao());
            p.setPreco(obj.getPreco());
            p.setQuantidadeEstoque(obj.getQuantidadeEstoque());
            return repo.save(p);
        } catch (Exception e) {
            Throwable t = e;
            while (t.getCause() != null) {
                t = t.getCause();
                if (t instanceof ConstraintViolationException) {
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Falha ao atualizar o Produto.");
        }
    }

    public void delete(Long id) throws NotFoundException {
        Produto obj = findById(id);
        verificaExclusaoprodutosVendidos(obj);

        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao deletar o produto.");
        }
    }

    private void verificaExclusaoprodutosVendidos(Produto p) {

        if (!p.getItemvendas().isEmpty()) {
            throw new RuntimeException("Não é possível excluir produtos pedidos.");

        }
    }

    private void verificaDescricaoCadastrado(String descricao) {
        List<Produto> result = repo.findByDescricao(descricao);
        if (!result.isEmpty()) {
            throw new RuntimeException("Já existe um produto com essa Descrição");
        }
    }
}
