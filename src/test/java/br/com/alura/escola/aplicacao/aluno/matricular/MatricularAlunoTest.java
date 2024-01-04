package br.com.alura.escola.aplicacao.aluno.matricular;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.alura.escola.dominio.aluno.Aluno;
import br.com.alura.escola.dominio.aluno.CPF;
import br.com.alura.escola.infra.aluno.RepositorioDeAlunosEmMemoria;

class MatricularAlunoTest {

	@Test
	void alunoDeveriaSerPersistido() {
		RepositorioDeAlunosEmMemoria repositorio = new RepositorioDeAlunosEmMemoria();
		MatricularAluno useCase = new MatricularAluno(repositorio);
		
		MatricularAlunoDto dados = new MatricularAlunoDto(
				"Fulano", 
				"01234567811", 
				"fulano@escola.com");
		useCase.executa(dados);
		
		Aluno aluno = repositorio.buscarPorCPF(new CPF("01234567811"));
		
		assertEquals("Fulano", aluno.getNome());
		assertEquals("01234567811", aluno.getCpf());
		assertEquals("fulano@escola.com", aluno.getEmail());
		
	}

}
