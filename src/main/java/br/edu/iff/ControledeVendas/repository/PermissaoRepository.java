
package br.edu.iff.ControledeVendas.repository;

import br.edu.iff.ControledeVendas.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
    
}
