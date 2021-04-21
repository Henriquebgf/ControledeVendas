
package br.edu.iff.ControledeVendas.controller.view;

import br.edu.iff.ControledeVendas.model.Pedido;
import br.edu.iff.ControledeVendas.service.ClienteService;
import br.edu.iff.ControledeVendas.service.FuncionarioService;
import br.edu.iff.ControledeVendas.service.PedidoService;
import br.edu.iff.ControledeVendas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/pedidos")
public class PedidoViewController {
    
    @Autowired
    private PedidoService service;
    
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public String getAll( Model model) {
        model.addAttribute("pedidos", service.findAll());      
        return "pedidos";
    }
    
     @GetMapping(path = "/pedido")
    public String cadastro(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("funcionarios", funcionarioService.findAll());
        model.addAttribute("produtos", produtoService.findAll());
        return "formPedido";
    }

}
