package br.com.alura.escola.dominio.aluno;

public class Email {
	
	//Value Object
	
	private String endereco;
	
	public Email(String endereco) {
		if (endereco == null 
				|| !endereco.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
			throw new IllegalArgumentException("E-mail inválido");
		}
		this.endereco = endereco;
	}

	public String getEmail() {
		return this.endereco;
	}

}
