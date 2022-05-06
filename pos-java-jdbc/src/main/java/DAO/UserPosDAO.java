package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import conexaojdbc.SingleConnection;
import model.UserPosJava;

public class UserPosDAO {
	
	private Connection connection;
	
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(UserPosJava userPosJava) {
		try {
			String sql = "INSERT INTO userposjava (id, nome, email) values (?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setLong(1, userPosJava.getId());
			insert.setString(2, userPosJava.getNome());
			insert.setString(3, userPosJava.getEmail());
			
			insert.execute();
			connection.commit(); //salva no banco
			
		} catch (Exception e) {
			try {
				connection.rollback();				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
