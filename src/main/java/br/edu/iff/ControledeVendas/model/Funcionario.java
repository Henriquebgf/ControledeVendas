
package br.edu.iff.ControledeVendas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class Funcionario extends Pessoa {
    @Column(nullable = false, unique = false, updatable = true)
    @Size(min=5,max=50 )
    private String setor;
    @Column(nullable = false, unique = true,  updatable = true)
    @Size(min=4,max=50 )
    private String senha;
    @JsonBackReference
    @OneToMany(mappedBy = "funcionario")
    private List<Pedido> pedidos = new ArrayList<>();
    
    


    public Funcionario() {
        
    }

    
    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    

    
   
}
