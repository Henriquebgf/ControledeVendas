package br.edu.iff.ControledeVendas;

import br.edu.iff.ControledeVendas.model.Cliente;
import br.edu.iff.ControledeVendas.model.Endereco;
import br.edu.iff.ControledeVendas.model.Funcionario;
import br.edu.iff.ControledeVendas.model.ItemVenda;
import br.edu.iff.ControledeVendas.model.Pedido;
import br.edu.iff.ControledeVendas.model.Produto;
import br.edu.iff.ControledeVendas.repository.ClienteRepository;
import br.edu.iff.ControledeVendas.repository.FuncionarioRepository;
import br.edu.iff.ControledeVendas.repository.PedidoRepository;
import br.edu.iff.ControledeVendas.repository.ProdutoRepository;
import br.edu.iff.ControledeVendas.service.PedidoService;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControledeVendasApplication implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private FuncionarioRepository funcionarioRepo;
    @Autowired
    private ProdutoRepository produtoRepo;
    @Autowired
    private PedidoRepository pedidoRepo;

    public static void main(String[] args) {
        SpringApplication.run(ControledeVendasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Cliente
        Cliente c1 = new Cliente();
        c1.setNome("Antonia");
        c1.setCpf("765.620.220-02");
        c1.setEmail("Antonia@gmail.com");
        c1.setTelefone("(22)99222-2222");

        Endereco end = new Endereco();
        end.setRua("Rua das flores");
        end.setNumero(123);
        end.setBairro("Parque das flores");
        end.setCidade("Campos");
        end.setCep("28000-000");

        c1.setEndereco(end);
        clienteRepo.save(c1);

        //Funcionario
        Funcionario f1 = new Funcionario();
        f1.setNome("Luis");
        f1.setEmail("Luis@gmail.com");
        f1.setCpf("325.291.890-05");
        f1.setEndereco(end);
        f1.setTelefone("(22)9983-9949");
        f1.setSetor("vendas");
        f1.setSenha("12385678");

        funcionarioRepo.save(f1);

        //produto
        Produto prod1 = new Produto();
        prod1.setDescricao("xbox series x");
        prod1.setPreco(45);
        prod1.setQuantidadeEstoque(6);

        //produto
        Produto prod2 = new Produto();
        prod2.setDescricao("Playstation 5");
        prod2.setPreco(30);
        prod2.setQuantidadeEstoque(5);

        produtoRepo.save(prod1);

        produtoRepo.save(prod2);

        //pedido
        Pedido p1 = new Pedido();
        Calendar datahora = Calendar.getInstance();
        datahora.set(2021, 10, 10);
        p1.setDatahora(datahora);
        p1.setValorTotal(120);
        p1.setCliente(c1);
        p1.setFuncionario(f1);
        //itemvenda
        ItemVenda i1 = new ItemVenda();
        i1.setQuantidade(2);
        i1.setSubtotal(90);
        i1.setProduto(prod1);

        ItemVenda i2 = new ItemVenda();
        i2.setQuantidade(1);
        i2.setSubtotal(30);
        i2.setProduto(prod2);
        p1.setItemvendas(List.of(i1, i2));
        pedidoRepo.save(p1);

        i2.setPedido(p1);
        i1.setPedido(p1);
        

    }

}
