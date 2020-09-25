package br.edu.insper.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.edu.insper.model.DAO;
import br.edu.insper.model.Tarefas;

/**
 * Servlet implementation class Atualiza
 */
@WebServlet("/Atualiza")
public class Atualiza extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Atualiza() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Atualiza.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao;
		try {
			dao = new DAO();
			Tarefas tarefa = new Tarefas();
			tarefa.setId(Integer.valueOf(request.getParameter("id")));
			tarefa.setTitulo(request.getParameter("titulo"));
			tarefa.setConteudo(request.getParameter("conteudo"));
			tarefa.setCategoria(request.getParameter("categoria"));
			tarefa.setPrioridade(Integer.valueOf(request.getParameter("prioridade")));
			String data = request.getParameter("data");
			Date data_ = new SimpleDateFormat("yyyy-MM-dd").parse(data);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(data_);
			tarefa.setData(calendar);
			dao.altera(tarefa);

			List<Tarefas> tarefas = dao.getLista();

			request.setAttribute("tarefas", tarefas);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Lista.jsp");
			dispatcher.forward(request, response);
			
			dao.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
