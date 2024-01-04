package br.com.alura.escola.dominio.aluno;

public class CPF {
	
	private String numero;
	
	public CPF(String numero) {
		if(numero == null || !numero.matches("\\d{11}")) {
			throw new IllegalArgumentException("CPF inválido");
		}
		this.numero = numero;
	}
	
	public String getNumero() {
		return numero;
	}

}
