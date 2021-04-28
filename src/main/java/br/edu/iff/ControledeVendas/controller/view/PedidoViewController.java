
package br.edu.iff.ControledeVendas.controller.view;

import br.edu.iff.ControledeVendas.model.Pedido;
import br.edu.iff.ControledeVendas.service.ClienteService;
import br.edu.iff.ControledeVendas.service.FuncionarioService;
import br.edu.iff.ControledeVendas.service.PedidoService;
import br.edu.iff.ControledeVendas.service.ProdutoService;
import java.util.ArrayList;
import java.util.List;
import javassist.NotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @PostMapping(path = "/pedido")
    public String save(@Valid @ModelAttribute Pedido pedido,BindingResult result, Model model) {
       
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("funcionarios", funcionarioService.findAll());
        model.addAttribute("produtos", produtoService.findAll());

        //Elimina erro de dataHora
        List<FieldError> list = new ArrayList<>();
        for (FieldError fe : result.getFieldErrors()) {
            if (!fe.getField().equals("datahora")) {
                list.add(fe);
            }
        }
        if (!list.isEmpty()) {
            model.addAttribute("msgErros", list);
            return "formPedido";
        }

        pedido.setId(null);
        try {
            service.save(pedido);
            model.addAttribute("msgSucesso", "Pedido cadastrada com sucesso.");
            model.addAttribute("pedido", new Pedido());
            return "formPedido";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("pedido", e.getMessage()));
            return "formPedido";
        }
    }
    
     @GetMapping(path = "/pedido/{id}")
    public String alterar(@PathVariable("id") Long id,Model model) throws NotFoundException {
        model.addAttribute("pedido", service.findById(id));

        return "formPedido";
    }
    
       @PostMapping(path = "/pedido/{id}")
    public String update(@Valid @ModelAttribute Pedido pedido, BindingResult result, @PathVariable("id") Long id, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formPedido";
        }
       pedido.setId(id);
        try {
            service.update(pedido);
            model.addAttribute("msgSucesso", "Pedido atualizado com sucesso.");
            model.addAttribute("pedido", pedido);
            return "formPedido";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Pedido", e.getMessage()));
            return "formPedido";
        }
    }

     @GetMapping(path = "/{id}/deletar")
    public String deletar(@PathVariable("id") Long id) throws NotFoundException {
        service.delete(id);
        return "redirect:/pedidos";
    }
}
