package escola;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.alura.escola.dominio.aluno.CPF;

class CPFTest {

	@Test
	void naoDeveriaCriarCPFsComNumerosInsuficientes() {
		assertThrows(IllegalArgumentException.class, 
				() -> new CPF(null));
		
		assertThrows(IllegalArgumentException.class, 
				() -> new CPF(""));
		
		assertThrows(IllegalArgumentException.class, 
				() -> new CPF("0123456789"));
	}

}
