package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Userposjava obj) {
		try {
			String sql = "insert into userposjava(nome, email) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, obj.getNome());
			insert.setString(2, obj.getEmail());
			insert.execute();

			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public List<Userposjava> listar() {
		List<Userposjava> lista = new ArrayList<Userposjava>();
		String sql = "select * from userposjava";
		try {
			PreparedStatement consulta = connection.prepareStatement(sql);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				Userposjava userposjava = new Userposjava();
				userposjava.setId(resultado.getLong("id"));
				userposjava.setNome(resultado.getString("nome"));
				userposjava.setEmail(resultado.getString("email"));
				lista.add(userposjava);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	public Userposjava buscarId(Long id) {
		Userposjava retorno = new Userposjava();
		String sql = "select * from userposjava where id = " + id;
		try {
			PreparedStatement consulta = connection.prepareStatement(sql);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {

				retorno.setId(resultado.getLong("id"));
				retorno.setNome(resultado.getString("nome"));
				retorno.setEmail(resultado.getString("email"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public void atualizar(Userposjava obj) {
		String sql = "update userposjava set nome =? where id = " +obj.getId();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, obj.getNome());
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}		
	}
	
	public void deletar(Long id) {
		String sql = "delete from userposjava where id = " + id;
		try {
			PreparedStatement statement =  connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void salvarTelefone(Telefone telefone) {
		
		try {
			String sql = "insert into telefoneuser(numero, tipo, usuariopessoa) values(?,?,?)";
			PreparedStatement inserir = connection.prepareStatement(sql);
			inserir.setString(1, telefone.getNumero());
			inserir.setString(2, telefone.getTipo());
			inserir.setLong(3, telefone.getUsuario());
			inserir.execute();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public List<BeanUserFone> buscarTelefonePorId(Long id){
		List<BeanUserFone> lista = new ArrayList<BeanUserFone>();
		String sql = "select fone.usuariopessoa, userp.nome, fone.numero, fone.tipo, userp.email "; 
				sql += "from telefoneuser as fone "; 
				sql += "inner join ";
				sql += "userposjava as userp on fone.usuariopessoa = userp.id where userp.id = "+id;
		try {
			PreparedStatement consulta = connection.prepareStatement(sql);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				BeanUserFone userfone = new BeanUserFone();
				userfone.setNome(resultado.getString("nome"));
				userfone.setNumero(resultado.getString("numero"));
				userfone.setEmail(resultado.getString("email"));
				userfone.setTipo(resultado.getString("tipo"));
				lista.add(userfone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void deleteTelefoneByUser(Long id) {
		String sqlFone = "delete from telefoneuser where usuariopessoa = "+id;
		String sqlUser= "delete from userposjava where id = "+id;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sqlFone);
			ps.execute();
			connection.commit();
			
			ps = connection.prepareStatement(sqlUser);
			ps.execute();
			connection.commit();
			System.out.println("Usuario deletado com Sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	

}
