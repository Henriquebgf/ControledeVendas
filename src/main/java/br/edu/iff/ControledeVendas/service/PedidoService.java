package br.edu.iff.ControledeVendas.service;

import br.edu.iff.ControledeVendas.model.Pedido;
import br.edu.iff.ControledeVendas.repository.PedidoRepository;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public List<Pedido> findAll() {
        return repo.findAll();
    }

    public List<Pedido> findAll(int page, int size, Long clienteId, Long funcionarioId) {
        Pageable p = PageRequest.of(page, size);
        if (clienteId != 0 && funcionarioId != 0) {
            return repo.findByClienteIdAndFuncionarioId(clienteId, funcionarioId, p);
        } else if (clienteId != 0) {
            return repo.findByClienteId(clienteId, p);
        }
        if (funcionarioId != 0) {
            return repo.findByFuncionarioId(funcionarioId, p);
        }

        return repo.findAll(p).toList();
    }

    public Pedido findById(Long id) throws NotFoundException {
        Optional<Pedido> obj = repo.findById(id);
        if (obj.isEmpty()) {
            throw new NotFoundException("Pedido não encontrado.");
        }
        return obj.get();
    }

    public Pedido save(Pedido p) {

        try {
            p.setDatahora(Calendar.getInstance());
            return repo.save(p);
        } catch (Exception e) {

            throw new RuntimeException("Falha ao salvar a Pedido.");
        }
    }

    public Pedido update(Pedido p) throws NotFoundException {
        //verificar se já existe
        Pedido obj = findById(p.getId());

        try {
            p.setDatahora(Calendar.getInstance());

            return repo.save(p);
        } catch (Exception e) {
            Throwable t = e;
            while (t.getCause() != null) {
                t = t.getCause();
                if (t instanceof ConstraintViolationException) {
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Falha ao atualizar a pedido");
        }
    }

    public void delete(Long id) throws NotFoundException {
        Pedido obj = findById(id);
        try {

            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao deletar a pedido.");
        }
    }
}
