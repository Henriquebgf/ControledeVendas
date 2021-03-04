
package br.edu.iff.ControledeVendas.model;

import java.util.List;
import java.util.Objects;


public class Cliente extends Pessoa{
    private String documentos;
    
    private List<Pedido> pedidos;

    public Cliente() {
        
    }

    public String getDocumentos() {
        return documentos;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    

    
}
