package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import dao.UserPosDAO;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class TestBancoJdbc {

	public void initBanco() {
		UserPosDAO userPosDAO = new UserPosDAO();
		Userposjava userposjava = new Userposjava();

		userposjava.setNome("Roberto");
		userposjava.setEmail("roberto@gmail.com");

		userPosDAO.salvar(userposjava);
		initListar();
	}

	public void initListar() {
		UserPosDAO dao = new UserPosDAO();
		List<Userposjava> list = dao.listar();

		for (Userposjava userposjava : list) {
			System.out.println(userposjava);
			System.out.println("-----------------------------------------------------------------------");
		}
	}

	public void initBuscarId() {
		UserPosDAO dao = new UserPosDAO();
		Userposjava buscar = dao.buscarId(4L);
		System.out.println(buscar);
	}

	public void initAtualizarId() {
		UserPosDAO dao = new UserPosDAO();
		Userposjava obj = dao.buscarId(4L);
		obj.setNome("teste executado com sucesso");
		dao.atualizar(obj);
		System.out.println();

		initBuscarId();
	}

	
	public void initDeletarId() {
		initListar();
		UserPosDAO dao = new UserPosDAO();
		Userposjava obj = dao.buscarId(10L);
		System.out.println("Foi excluido o usuario " + obj.getNome());
		dao.deletar(10L);
		initListar();
	}


	public void testSalvarTelefone() {
		UserPosDAO dao = new UserPosDAO();
		Telefone telefone = new Telefone();

		telefone.setNumero("(64) 9 98756-5522");
		telefone.setTipo("Casa");
		telefone.setUsuario(3L);
		dao.salvarTelefone(telefone);
	}
	
	
	public void BuscarTelefonePorId() {
		UserPosDAO dao = new UserPosDAO();
		List<BeanUserFone> buscar = dao.buscarTelefonePorId(5L);
		for (BeanUserFone beanUserFone : buscar) {
			System.out.println(beanUserFone);
		}		
	}
	
	@Test
	public void deleterTelefonePorId() {
		UserPosDAO dao = new UserPosDAO();
		dao.deleteTelefoneByUser(3l);
	}

}
