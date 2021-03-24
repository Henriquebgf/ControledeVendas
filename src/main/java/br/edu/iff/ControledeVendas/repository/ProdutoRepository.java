package br.edu.iff.ControledeVendas.repository;

import br.edu.iff.ControledeVendas.model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public List<Produto> findByDescricao(String descricao);
   /* @Query("UPDATE produto SET quantidade_estoque= estoque where id=:id")
    public Produto updateInventory(@Param("quantidade_estoque") int quantidade_estoque,@Param("id") Long id );*/
}
