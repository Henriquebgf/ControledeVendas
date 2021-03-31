package br.edu.iff.ControledeVendas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity

public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = false, updatable = true)
    @Size(min = 5, max = 80)
    @NotBlank
    private String descricao;

    @Column(nullable = false, unique = false, updatable = true, precision = 2)
    @PositiveOrZero
    @NotNull
    private double preco;
    @Column(nullable = true, unique = false, updatable = true)
    @PositiveOrZero
    @NotNull
    private int quantidadeEstoque;

    @OneToMany(mappedBy = "produto")
    @JsonIgnore
    private List<ItemVenda> itemvendas = new ArrayList<>();

    public Produto() {

    }

    public long getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
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
        return true;
    }

  

    public void setId(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
