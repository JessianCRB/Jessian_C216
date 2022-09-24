package br.inatel.labs.labrest.server.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.inatel.labs.labrest.server.model.Curso;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CursoControllerTest {

	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void deveListarCursos() {
		webTestClient.get()
			.uri("/curso")
			.exchange()
			.expectStatus()
				.isOk() //status está ok?
			.expectBody()
				.returnResult(); //retornou algo?
	}
	
	@Test
	void dadoCursoIdValido_quandoGetCursoPorId_entaoRespondeComCursovalido() {
		Long cursoIdValido = 1L;
		
		Curso cursoRespondido = webTestClient.get()
			.uri("/curso/" + cursoIdValido)
			.exchange()
			.expectStatus()
				.isOk()
			.expectBody(Curso.class)
				.returnResult()
				.getResponseBody();
		assertNotNull(cursoRespondido);
		assertEquals(cursoRespondido.getId(), cursoIdValido);
	}

	@Test
	void dadoCursoIdInvalido_quandoGetCursoPeloId_entaoRespondeComStatusNotFound() {
		Long cursoIdInvalido = 99L;
		
		webTestClient.get()
			.uri("/curso/" + cursoIdInvalido)
			.exchange()
			.expectStatus()
				.isNotFound();
	}
	
	@Test
	void dadoNovoCurso_quandoPostCurso_entaoRespondeComStatusCreatedECursoValido() {
		Curso novoCurso = new Curso();
		novoCurso.setDescricao("Testando REST com Spring WebFlux");
		novoCurso.setCargaHoraria(120);
		
		Curso cursoRespondido = webTestClient.post()
			.uri("/curso")
			.bodyValue(novoCurso)
			.exchange()
			.expectStatus()
				.isCreated()
			.expectBody(Curso.class)
				.returnResult()
				.getResponseBody();
		
		assertThat(cursoRespondido).isNotNull();
		assertThat(cursoRespondido.getId()).isNotNull();
	}
	
	@Test
    void dadoCursoValido_quandoPutCurso_entaoResponseComStatusAccepted() {
        Curso cursoExistente = new Curso(1L, "REST e Spring Boot e Spring WebFlux Testando", 120);

         webTestClient
                .put()
                .uri("/curso")
                .bodyValue(cursoExistente)
                .exchange()
                .expectStatus()
                	.isAccepted();
    }
	
	@Test
    void dadoCursoIdValido_quandoDeleteCursoPeloId_entaoRespondeComStatusSemConteudoEVazio(){

        Long cursoIdDeletar = 1L;

        EntityExchangeResult<Void> cursoRespondido = webTestClient
                .delete()
                .uri("/curso/" + cursoIdDeletar)
                .exchange()
                .expectStatus()
                	.isNoContent() // sem conteúdo
                .expectBody()
                	.isEmpty(); //vazio
    }

    @Test
    void dadoCursoIdInvalido_quandoDeleteCursoPeloId_entaoRespondeComStatusNotFound(){

        Long cursoIdDeletar = 10L;

        webTestClient
                .delete()
                .uri("/curso/" + cursoIdDeletar)
                .exchange()
                .expectStatus()
                	.isNotFound();
    }
}
