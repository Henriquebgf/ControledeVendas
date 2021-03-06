package br.edu.iff.ControledeVendas.model;

import br.edu.iff.ControledeVendas.annotation.SemEspacoValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

@Entity
@JsonIgnoreProperties(value = "senha", allowGetters = false, allowSetters = true)
public class Funcionario extends Pessoa {

    @Column(nullable = false, unique = false, updatable = true)
    @Length(min = 4, max = 50, message = "Setor deve ter no mínimo 5 e no máximo 50 caracteres.")
    @NotBlank(message = "Setor obrigatório.")
    private String setor;
    @Column(nullable = false, unique = true, updatable = true)
    @Length(min = 4 , message = "Senha deve ter no mínimo 4  caracteres.")
    @SemEspacoValidation
    @NotBlank(message = "Senha obrigatória.")
    private String senha;
    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<Pedido> pedidos = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @Size(min = 1, message = "Funcionário deve ter no mínimo 1 permissão.")
    private List<Permissao> permissoes = new ArrayList<>();

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

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
