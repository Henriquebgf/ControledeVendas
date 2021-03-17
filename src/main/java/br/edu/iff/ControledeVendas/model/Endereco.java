
package br.edu.iff.ControledeVendas.model;

import br.edu.iff.ControledeVendas.annotation.NomeValidation;
import br.edu.iff.ControledeVendas.annotation.SemEspacoValidation;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;


public class Endereco implements Serializable{
private static final long serialVersionUID = 1L;
    @Column(nullable = true,unique = false, updatable = true)
    @Size(min=5, max=30)
    private String bairro;
    @Column(nullable = true,unique = false, updatable = true)
    private String rua;
    @Column(nullable = true,unique = false, updatable = true)
    @Digits(integer = 4, fraction = 0, message = "Número deve ser inteiro e ter até 4 digitos.")
    private int numero;
    @Column(nullable = true,unique = false, updatable = true)
    @Size(min=5, max=30)
    @NomeValidation(message = "Nome inválido.")
    private String cidade;
    @Column(nullable = true,unique = false, updatable = true) 
    @Length(min = 9, max = 9, message = "CEP deve ter exatamente 9 caracteres (Ex: 99999-999).")
    @SemEspacoValidation
    private String cep;

    public Endereco() {
        
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.bairro);
        hash = 97 * hash + Objects.hashCode(this.rua);
        hash = 97 * hash + this.numero;
        hash = 97 * hash + Objects.hashCode(this.cidade);
        hash = 97 * hash + Objects.hashCode(this.cep);
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
        final Endereco other = (Endereco) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (!Objects.equals(this.rua, other.rua)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep)) {
            return false;
        }
        return true;
    }
    
    
}
