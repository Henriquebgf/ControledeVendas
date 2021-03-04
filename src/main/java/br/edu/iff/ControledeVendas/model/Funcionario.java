
package br.edu.iff.ControledeVendas.model;

import java.util.List;
import java.util.Objects;


public class Funcionario extends Pessoa {
    private String setor;
    private String senha;
    
    private List<Pedido> pedidos;
    

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
