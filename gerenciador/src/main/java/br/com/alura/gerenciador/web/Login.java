package br.com.alura.gerenciador.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.Usuario;
import br.com.alura.gerenciador.dao.UsuarioDAO;

@WebServlet(urlPatterns="/login")
public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String senha = req.getParameter("senha");
		
		PrintWriter writer = resp.getWriter();
		Usuario usuario = new UsuarioDAO().buscaPorEmailESenha(email, senha);
		if(usuario == null) {
			writer.println("<html><body>Usuario invalido</body></html>");
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("usuario.logado", usuario);
			
//			Cookie cookie = new Cookie("usuario.logado", email);
//			cookie.setMaxAge(10 * 60);
//			resp.addCookie(cookie);
			
			writer.println("<html><body>Usuario logado: "+email+"</body></html>");
		}
	}
}
