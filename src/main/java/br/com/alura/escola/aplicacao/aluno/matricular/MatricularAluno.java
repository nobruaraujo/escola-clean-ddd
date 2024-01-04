package br.com.alura.escola.aplicacao.aluno.matricular;

import br.com.alura.escola.dominio.aluno.Aluno;
import br.com.alura.escola.dominio.aluno.RepositorioDeAlunos;

public class MatricularAluno {
	
	//Use Case
	
	private final RepositorioDeAlunos repositorio;

	public MatricularAluno(RepositorioDeAlunos repositorio) {
		this.repositorio = repositorio;
	}
	
	//COMMAND PATTERN
	public void executa(MatricularAlunoDto dados) {
		Aluno aluno = dados.criarAluno();
		repositorio.matricular(aluno);
	}

}
