package br.com.alura.escola.infra.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.escola.dominio.aluno.Aluno;
import br.com.alura.escola.dominio.aluno.AlunoNaoEncontrado;
import br.com.alura.escola.dominio.aluno.CPF;
import br.com.alura.escola.dominio.aluno.Email;
import br.com.alura.escola.dominio.aluno.RepositorioDeAlunos;
import br.com.alura.escola.dominio.aluno.Telefone;

public class RepositorioDeAlunosComJDBC implements RepositorioDeAlunos {

	private Connection connection;

	public RepositorioDeAlunosComJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void matricular(Aluno aluno) {
		String sql = "INSERT INTO tb_aluno VALUES(?, ?, ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, aluno.getCpf());
			ps.setString(2, aluno.getNome());
			ps.setString(3, aluno.getEmail());
			ps.execute();

			sql = "INSERT INTO tb_telefone VALUES(?, ?)";
			ps = connection.prepareStatement(sql);

			for (Telefone telefone : aluno.getTelefones()) {
				ps.setString(1, telefone.getDdd());
				ps.setString(2, telefone.getNumero());
				ps.execute();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Aluno buscarPorCPF(CPF cpf) {
		try {
			String sql = "SELECT id, nome, email FROM tb_aluno WHERE CPF = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, cpf.getNumero());

			ResultSet rs = ps.executeQuery();
			boolean encontrou = rs.next();

			if (!encontrou) {
				throw new AlunoNaoEncontrado(cpf);
			}

			String nome = rs.getString("nome");
			Email email = new Email(rs.getString("email"));
			Aluno encontrado = new Aluno(cpf, nome, email);

			Long id = rs.getLong("id");
			sql = "SELECT ddd, numero FROM tb_telefone WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				String ddd = rs.getString("ddd");
				String numero = rs.getString("numero");
				encontrado.adicionarTelefone(ddd, numero);
			}

			return encontrado;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Aluno> listarTodosAlunosMatriculados() {

		List<Aluno> alunos = new ArrayList();

		try {
			String sql = "SELECT * FROM tb_alunos";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			boolean encontrou = rs.next();

			if (!encontrou) {
				throw new RuntimeException("Nenhum aluno encontrado");
			}

			Aluno aluno;

			while (rs.next()) {
				aluno = new Aluno(new CPF(rs.getString("cpf")), rs.getString("nome"), new Email(rs.getString("email")));
				alunos.add(aluno);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return alunos;
	}

}
