package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.TelefoneUser;
import model.UserPosJava;

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(UserPosJava userPosJava) {
		try {
			String sql = "INSERT INTO userposjava (nome, email) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userPosJava.getNome());
			insert.setString(2, userPosJava.getEmail());

			insert.execute();
			connection.commit(); // salva no banco

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void salvarTelefone(TelefoneUser telefone) {
		try {
			String sql = "INSERT INTO telefoneuser(numero, tipo, usuariopessoa) VALUES (?, ?, ?);";
			PreparedStatement insertTelefone = connection.prepareStatement(sql);
			insertTelefone.setString(1, telefone.getNumero());
			insertTelefone.setString(2, telefone.getTipo());
			insertTelefone.setLong(3, telefone.getUsuario());
			
			insertTelefone.execute();
			connection.commit();
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<UserPosJava> listar() throws Exception {
		List<UserPosJava> list = new ArrayList<UserPosJava>();

		String sql = "SELECT  FROM userposjava";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			UserPosJava userPosJava = new UserPosJava();
			userPosJava.setId(resultado.getLong("id"));
			userPosJava.setNome(resultado.getString("nome"));
			userPosJava.setEmail(resultado.getString("email"));

			list.add(userPosJava);

		}

		return list;
	}

	public UserPosJava buscar(Long id) throws Exception {
		UserPosJava retorno = new UserPosJava();

		String sql = "SELECT * FROM userposjava WHERE id = " + id;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));

		}

		return retorno;
	}
	
	public List<BeanUserFone> listaUserFone(Long idUser){
		List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();
		
		String sql = " SELECT  nome, email, numero ";
		sql += " FROM userposjava as u ";
		sql += " inner join telefoneuser te on u.id = te.usuariopessoa ";
		sql += " where u.id = " + idUser;
		
		try {
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()) {
				BeanUserFone userFone = new BeanUserFone();
				userFone.setEmail(resultado.getString("email"));
				userFone.setNome(resultado.getString("nome"));
				userFone.setNumero(resultado.getString("numero"));
				beanUserFones.add(userFone);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return beanUserFones;
	}

	public void atualizar(UserPosJava userPosJava) throws SQLException {
		try {
			String sql = "UPDATE userposjava SET email = ? WHERE id = " + userPosJava.getId();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userPosJava.getNome());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}

	}
	
	public void deletar(Long id) {
		try {
			String sql = "DELETE FROM userposjava WHERE id = " + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void deleteFonesPorUser(Long idUser) {
		try {
			String sqlFone = " DELETE FROM telefoneuser WHERE usuariopessoa = " + idUser;
			String sqlUser = " DELETE FROM userposjava WHERE id = " + idUser;
			
			PreparedStatement statement = connection.prepareStatement(sqlFone);
			statement.executeUpdate();
			connection.commit();
			
			statement = connection.prepareStatement(sqlUser);
			statement.executeUpdate();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
