package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns="/*")
public class FiltroDeAuditoria implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		
		String usuario = getUsuario(req, resp);
		
		String requestURI = req.getRequestURI();
		System.out.println("Usuario "+ usuario +" acessou: " + requestURI);
		
		
		arg2.doFilter(arg0, arg1);
	}
	
	private String getUsuario(HttpServletRequest req, HttpServletResponse resp) {
		Cookie cookie = new Cookies(req.getCookies()).getUsuarioLogado();
		if (cookie == null){
			return "<deslogado>";
		} else {
			cookie.setMaxAge(10 * 60);
			resp.addCookie(cookie);
			return cookie.getValue();
		}
//		Cookie[] cookies = req.getCookies();
//		if (cookies == null) {
//			return "<deslogado>";
//		} else {
//			for (Cookie cookie : cookies) {
//				if(cookie.getName().equals("usuario.logado")){
//					return cookie.getValue();
//				}
//			}
//		}
//		
//		return "<deslogado>";
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
