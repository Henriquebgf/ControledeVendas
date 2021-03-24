package br.edu.iff.ControledeVendas.repository;

import br.edu.iff.ControledeVendas.model.Pedido;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    public List<Pedido> findByFuncionarioId(Long funcionarioId, Pageable page);

    public List<Pedido> findByClienteId(Long clienteId, Pageable page);

    public List<Pedido> findByClienteIdAndFuncionarioId(Long clienteId, Long funcionarioId, Pageable page);


}
