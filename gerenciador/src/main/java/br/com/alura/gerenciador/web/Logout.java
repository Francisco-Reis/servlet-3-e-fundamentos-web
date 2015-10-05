package br.com.alura.gerenciador.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/logout")
public class Logout extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().removeAttribute("usuario.logado");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/paginas/logout.html");
        dispatcher.forward(req, resp);
		
//		PrintWriter writer = resp.getWriter();
//		writer.println("<html><body>Usuário deslogado com sucesso</body><html>");
//		Cookie cookie = new Cookies(req.getCookies()).getUsuarioLogado();
//		if(cookie == null){
//			writer.println("<html><body>Usuário não estava logado</body><html>");
//			return;
//		}
//		cookie.setMaxAge(0);
//		resp.addCookie(cookie);
	}
}
