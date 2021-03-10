
package br.edu.iff.ControledeVendas.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

@Entity
public class ItemVenda implements Serializable{
    @Id
    @GeneratedValue(strategy=  GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = false, updatable = true)
        @Min(value = 0, message = "Não aceita valores negativos")
    private int quantidade;
    @Column(nullable = false, unique = false, updatable = true)
    @Min(value = 0, message = "Não aceita valores negativos")
    private double subtotal;
    
    @JoinColumn(nullable = false)   
    @ManyToOne
    private Produto produto;  
    @JoinColumn(nullable = false)
    @ManyToOne
    private Pedido pedido;
    
    
    public ItemVenda() {
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemVenda other = (ItemVenda) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

 
    
}
