package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import DAO.UserPosDAO;
import model.BeanUserFone;
import model.TelefoneUser;
import model.UserPosJava;

public class TesteBancoJDBC {

	@Test
	public void initBanco() {
		UserPosDAO userPosDAO = new UserPosDAO();
		UserPosJava userPosJava = new UserPosJava();

		userPosJava.setNome("Rafaela Restier");
		userPosJava.setEmail("fafa.restier@gmail.com");

		userPosDAO.salvar(userPosJava);
	}

	@Test
	public void initListar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<UserPosJava> list = dao.listar();

			for (UserPosJava userPosJava : list) {
				System.out.println(userPosJava.getNome());
				System.out.println("-----------------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initBucar() {
		UserPosDAO dao = new UserPosDAO();

		try {
			UserPosJava userPosJava = dao.buscar(5L);

			System.out.println(userPosJava);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initAtualizar() {
		UserPosDAO dao = new UserPosDAO();

		try {
			
			UserPosJava objetoBanco = dao.buscar(4L);
			objetoBanco.setEmail("melquisedeque@gmail.com");
			dao.atualizar(objetoBanco);
			
			System.out.println(objetoBanco.getEmail());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initDeletar() {
		
		try {
			UserPosDAO dao = new UserPosDAO();
			dao.deletar(7L);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void initSalvarTelefone() {
		try {
			TelefoneUser telefoneUser = new TelefoneUser();
			telefoneUser.setNumero("(31)975427379");
			telefoneUser.setTipo("Celular");
			telefoneUser.setUsuario(11L);
			
			UserPosDAO dao = new UserPosDAO();
			dao.salvarTelefone(telefoneUser);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testeCarregaFonesUser() {
		UserPosDAO dao = new UserPosDAO();
		
		List<BeanUserFone> beanUserFones = dao.listaUserFone(11L);
		
		for (BeanUserFone beanUserFone : beanUserFones) {
			System.out.println(beanUserFone);
			System.out.println("-------------------------------");
		}
	}
	
	@Test
	public void testeDeleteUserFone() {
		UserPosDAO dao = new UserPosDAO();
		
		dao.deleteFonesPorUser(10L);
	}

}
