
package br.edu.iff.ControledeVendas.service;

import br.edu.iff.ControledeVendas.model.ItemVenda;
import br.edu.iff.ControledeVendas.model.Produto;
import br.edu.iff.ControledeVendas.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    
     @Autowired
    private ProdutoRepository repo;

    public List<Produto> findAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }

    public List<Produto> findAll() {
        return repo.findAll();
    }

    public Produto findById(Long id) throws NotFoundException {
        Optional<Produto> result = repo.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException("Produto não encontrado.");
        }
        return result.get();
    }
    
    public Produto save(Produto p) {
        verificaDescricaoCadastrado(p.getDescricao());
        try {
            return repo.save(p);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o Produto.");
        }
    }
    
    public Produto update(Produto p) throws NotFoundException{
        Produto obj = findById(p.getId());
        
        List<ItemVenda> itensAtuais = obj.getItemvendas();
        itensAtuais.removeAll(obj.getItemvendas());
        try{
            p.setDescricao(obj.getDescricao());
            p.setPreco(obj.getPreco());
            p.setQuantidadeEstoque(obj.getQuantidadeEstoque());
            return repo.save(p);
        }catch(Exception e){
            throw new RuntimeException("Falha ao atualizar o Hotel.");
        }
    }
    
    public void delete(Long id) throws NotFoundException{
        Produto obj = findById(id);
        verificaExclusaoprodutosVendidos(obj.getItemvendas());
        
        try{
            repo.delete(obj);
        }catch(Exception e){
            throw new RuntimeException("Falha ao deletar o produto.");
        }
    }
    
     private void verificaExclusaoprodutosVendidos(List<ItemVenda> ItemVenda){
        for(ItemVenda i : ItemVenda){
            if(!i.getProduto().isEmpty()){
                throw new RuntimeException("Não é possível excluir produtos pedidos.");
            }
        }
    }
    
    private void verificaDescricaoCadastrado(String descricao) {
        List<Produto> result = repo.findByDescricao(descricao);
        if (!result.isEmpty()) {
            throw new RuntimeException("Já existe um produto com essa Descrição");
        }
    }
}
