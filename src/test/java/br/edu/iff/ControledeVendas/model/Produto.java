
package br.edu.iff.ControledeVendas.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Produto implements Serializable{
    private static final long serialVersionUID = 1L;
     
    private long id;
    private String descricao;
    private double preco;
    private int quantidadeEstoque;
    
    private List<ItemVenda> itemvendas;
    

    public Produto() {
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public List<ItemVenda> getItemvendas() {
        return itemvendas;
    }

    public void setItemvendas(List<ItemVenda> itemvendas) {
        this.itemvendas = itemvendas;
    }

   

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 13 * hash + Objects.hashCode(this.descricao);
        hash = 13 * hash + (int) (Double.doubleToLongBits(this.preco) ^ (Double.doubleToLongBits(this.preco) >>> 32));
        hash = 13 * hash + this.quantidadeEstoque;
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
        final Produto other = (Produto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.preco) != Double.doubleToLongBits(other.preco)) {
            return false;
        }
        if (this.quantidadeEstoque != other.quantidadeEstoque) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }
    
    
    
}
