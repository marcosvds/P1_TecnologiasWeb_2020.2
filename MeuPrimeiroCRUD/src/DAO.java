import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DAO {
	
	private Connection connection = null;
	public DAO() throws SQLException, ClassNotFoundException {
		 Class.forName("com.mysql.jdbc.Driver");
		 connection = DriverManager.getConnection("jdbc:mysql://localhost/projeto1", "root", "295166");
	}
	
	public List<Tarefas> getLista() throws SQLException{
		
		List<Tarefas> tarefas = new ArrayList<Tarefas>();
		 
		String sql = "SELECT Tarefas.id, Tarefas.titulo, Tarefas.conteudo, Tarefas.categoria, Tarefas.prioridade, Tarefas.data FROM Tarefas";		
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Tarefas tarefa = new Tarefas();
			tarefa.setId(rs.getInt("id"));
			tarefa.setTitulo(rs.getString("titulo"));
			tarefa.setConteudo(rs.getString("conteudo"));
			tarefa.setPrioridade(rs.getInt("prioridade"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data"));
			tarefa.setData(data);
			tarefas.add(tarefa);
		} 
		
		rs.close();
		stmt.close();
		
		return tarefas;
	}
	
	public void adiciona(Tarefas tarefa) throws SQLException {
		String sql = "INSERT Into Tarefa" + "(titulo, conteudo, categoria, prioridade, data) values (?,?,?,?,?)";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, tarefa.getTitulo());
		stmt.setString(2, tarefa.getConteudo());
		stmt.setString(3, tarefa.getCategoria());
		stmt.setInt(4, tarefa.getPrioridade());
		stmt.setDate(5, new java.sql.Date(tarefa.getData().getTimeInMillis()));		
		stmt.execute();
		stmt.close();		
	}
	
	public void altera(Tarefas tarefa) throws SQLException {
		String sql = "UPDATE Tarefa SET " + "titulo=?, conteudo=?, categoria=?, prioridade=?, data=? WHERE id=?" ;
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, tarefa.getTitulo());
		stmt.setString(2, tarefa.getConteudo());
		stmt.setString(3, tarefa.getCategoria());
		stmt.setInt(4, tarefa.getPrioridade());
		stmt.setDate(5, new java.sql.Date(tarefa.getData().getTimeInMillis()));
		stmt.setInt(6, tarefa.getId());
		
		stmt.execute();
		stmt.close();		
	}
	
	public void remove(Integer id) throws SQLException { 
		String sql = "DELETE FROM Tarefa WHERE id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
	}
	
	public void close() throws SQLException {
		connection.close();
	}
		
	
}
