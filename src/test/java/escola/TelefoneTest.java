package escola;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.alura.escola.dominio.aluno.Telefone;

class TelefoneTest {

	@Test
	void naoDeveriaAdicionarTelefoneInvalido() {
		
		assertThrows(IllegalArgumentException.class, 
				() -> new Telefone(null, null));
		
		assertThrows(IllegalArgumentException.class, 
				() -> new Telefone("", ""));
		
		assertThrows(IllegalArgumentException.class, 
				() -> new Telefone("11", "12345678"));
	}

}
