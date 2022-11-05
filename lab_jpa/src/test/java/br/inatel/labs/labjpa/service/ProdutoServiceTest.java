package br.inatel.labs.labjpa.service;

import br.inatel.labs.labjpa.entity.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ProdutoServiceTest {

    @Autowired
    private ProdutoService service;

    @Test
    void dadoIdValido_quandoInvocaBuscarPeloId_entaoProdutoRetornadoEhValido() {
        Long idValido = 1L;
        Produto produtoEncontrado = service.buscarProdutoPeloId(idValido);
        assertNotNull(produtoEncontrado);
        assertFalse(produtoEncontrado.getDescricao().isBlank());
        assertEquals(idValido, produtoEncontrado.getId());
        assertEquals("Serrote",produtoEncontrado.getDescricao());
    }

}