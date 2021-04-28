package br.edu.iff.ControledeVendas.model;

import br.edu.iff.ControledeVendas.annotation.EmailValidation;
import br.edu.iff.ControledeVendas.annotation.NomeValidation;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @Size(min = 4, max = 80)
    @NotBlank(message = "Nome obrigatório.")
    @NomeValidation(message = "Nome inválido.") // Nomes de pessoa e cidade não podem conter números
    private String nome;
    @Column(nullable = false, length = 14, unique = true, updatable = true)
    @NotBlank(message = "Telefone obrigatório.")
    @Pattern(regexp = "\\([0-9]{2}\\)[0-9]{4,5}-[0-9]{4}", message = "Padrão deve ser obedecido.")
    @Length(min=13, max = 14, message = "Telefone deve ter 13 ou 14 caracteres (Ex: (99)9999-9999 ou (99)99999-9999")
    private String telefone;
    @Column(nullable = false, unique = true, updatable = true)
    @Size(min = 10, max = 80)
    @EmailValidation(message = "Email inválido.")
    @NotBlank(message = "Email obrigatório.")
    private String email;
    @Column(nullable = false, length = 14, unique = true, updatable = false)
    @CPF(message = "CPF inválido.")
    @NotBlank(message = "CPF obrigatório.")
    private String cpf;
    @Embedded
    @Valid
    private Endereco endereco;

    public Pessoa() {

    }

    public Long getId() {
        return id;
    }

 

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Pessoa other = (Pessoa) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
