package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO {
//Metodo de Conexão
	// Parâmetros para Conexão

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "backend@24";

	// Metodo de conxão:
	private Connection conectar() {
		Connection con = null;

		// Exception Treatments:
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;

		}

//Controller(Classe) =>Servelt
		// => 3 etapas depois da classe DAO:
		// 1 - import DAO(model);
		// 2 - Instância: DAO dao = new DAO();
		// 3 - DAO testeconexão();
	}

	// CRUD CREATE
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos(nome ,fone,email) values (?, ?, ?)";
		try {
			// abrir a conexão
			Connection con = conectar();
			// Preparar a consulta(query) para a execução no Banco de Dados:
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parametros (?) pelo conteudos dos atributos da classe JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// Executar a query (ctrl + Enter)
			pst.executeUpdate();
			// Encerrar a conexão com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// CRUD READ
	public ArrayList<JavaBeans> listaContatos() {

		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select * from contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// O laço abaixo será executado eqto houver contatos
			while (rs.next()) {
				// variaveis de apoio que recebem os dados do Banco
				int idcon = rs.getInt(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// Populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email));

			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;

		}
	}

	// CRUD UPDATE
	// Selecionar contato
	public void selecaoContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setInt(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();

			// Laço while para receber dados do BD e enviar para classe JavaBeans

			while (rs.next()) {
				// setar as variáveis Javabeans
				contato.setIdcon(rs.getInt(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void alterarContato(JavaBeans contato) {
		String atualizar = "update contatos set nome=?,fone=?,email=? where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(atualizar);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setInt(4, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public void deletarContato(int id) {
		String sql = "DELETE FROM contatos WHERE idcon = " + id;
		try {
			Connection con = conectar();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			con.close();
			
			
		} catch (Exception e) {
			System.out.println("Erro no Delete " + e);
		}
		
	
	}

	// teste de conexão:
	/*
	 * public void testeConexão() { try { Connection con = conectar();
	 * System.out.println(con); con.close(); } catch (Exception e) {
	 * System.out.println(e); }
	 */
}
