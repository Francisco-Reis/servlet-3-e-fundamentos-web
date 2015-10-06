package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/executa")
public class Controller extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String paramTarefa = req.getParameter("tarefa");
		String nomeDaClasse = "br.com.alura.gerenciador.web." + paramTarefa;
		
		try {
			Class<?> type = Class.forName(nomeDaClasse);
			Tarefa tarefa = (Tarefa) type.newInstance();
			String pagina = tarefa.executa(req, resp);
			req.getRequestDispatcher(pagina).forward(req, resp);
			
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			throw new ServletException(e); 
		}
		
	}
}
