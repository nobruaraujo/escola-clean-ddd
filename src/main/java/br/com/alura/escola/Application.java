package br.com.alura.escola;

import br.com.alura.escola.aplicacao.aluno.matricular.MatricularAluno;
import br.com.alura.escola.aplicacao.aluno.matricular.MatricularAlunoDto;
import br.com.alura.escola.dominio.aluno.Aluno;
import br.com.alura.escola.dominio.aluno.CPF;
import br.com.alura.escola.dominio.aluno.Email;
import br.com.alura.escola.dominio.aluno.RepositorioDeAlunos;
import br.com.alura.escola.infra.aluno.RepositorioDeAlunosEmMemoria;

public class Application {

	public static void main(String[] args) {

		String nome = "Fulano da Silva";
		String cpf = "01234567890";
		String email = "teste@teste.com";

		MatricularAluno matricular = new MatricularAluno(
				new RepositorioDeAlunosEmMemoria()
			);
		matricular.executa(new MatricularAlunoDto(nome, cpf, email));
	}

}
