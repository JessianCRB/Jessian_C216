package br.inatel.labs.labjpa.service;

import br.inatel.labs.labjpa.entity.Fornecedor;
import br.inatel.labs.labjpa.entity.Produto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class FornecedorService {

    @PersistenceContext
    private EntityManager em;

    public Fornecedor salvar(Fornecedor fornecedor){
        return em.merge(fornecedor);
    }

    public Fornecedor buscarFornecedorPeloId(Long id){
        return em.find(Fornecedor.class,id);
    }

    public List<Fornecedor> listar(){
        return em.createQuery("select f from Fornecedor f", Fornecedor.class).getResultList();
    }

    public void remover(Fornecedor fornecedor){
        fornecedor = em.merge(fornecedor);
        em.remove(fornecedor);
    }

}
