package br.inatel.labs.labrest.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import br.inatel.labs.labrest.server.model.Curso;
/**
 * Servico com método de negócio para curso
 * @author Jessian
 *
 */
@Service
public class CursoService {

	private List<Curso> listaDeCursos = new ArrayList<>();
	
	@PostConstruct
	private void setup() {
		Curso c1 = new Curso(1L, "REST com Spring Boot", 20);
		Curso c2 = new Curso(2L, "Programação Java 11", 80);
		Curso c3 = new Curso(3L, "Dominando a IDE", 120);
		
		listaDeCursos.add( c1 );
		listaDeCursos.add( c2 );
		listaDeCursos.add( c3 );
	}
	
	public List<Curso> pesquisarCurso(){
		return listaDeCursos;
	}
	
	public Optional<Curso> buscarCursoPeloId(Long cursoId) {
		Optional<Curso> opCurso = listaDeCursos.stream()
				.filter(c -> c.getId().equals(cursoId))
				.findFirst();
		return opCurso;
	}
	
	public Curso criarCurso(Curso curso) {
		Long id = new Random().nextLong();
		curso.setId( id );
		listaDeCursos.add( curso );
		return curso;
	}
	
	public void atualizarCurso(Curso curso) {
		listaDeCursos.remove( curso );
		listaDeCursos.add( curso );
	}
	
	/**
	 * antigo método de excluir
	public void removerCursoPeloId(Long cursoId) {
		Optional<Curso> opCurso = buscarCursoPeloId(cursoId);
		if(opCurso.isPresent()) {
			Curso cursoASerRemovido = opCurso.get();
			listaDeCursos.remove(cursoASerRemovido);
		}
	}
	*/
	public void removerCurso(Curso cursoASerRemovido) {
		listaDeCursos.remove( cursoASerRemovido );
	}
	
	
	/**
	 * Pesquisa cursos pelo fragmento da descrição ignorante espaços em brancos e case sensitive
	 * @param fragDescricao
	 * @return
	 */
	public List<Curso> pesquisarCursoPeloFragDescricao(String fragDescricao){
		if(Objects.isNull(fragDescricao) || fragDescricao.isBlank()) {
			return List.of();
		}
		
		List<Curso> listaDeCursosEncontrados = listaDeCursos.stream()
			.filter(c -> c.getDescricao().trim().toLowerCase().contains(fragDescricao.trim().toLowerCase() ))
			.collect( Collectors.toList() );
		
		return listaDeCursosEncontrados;
	}
	
}
