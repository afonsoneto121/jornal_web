package br.ufc.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebFilter ("/*")
public class FiltroTempoDeExecucao implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		String uri = ((HttpServletRequest)request).getRequestURI();
		
		if(uri.endsWith("cadastrarUsuario") || uri.endsWith("cadastrarUsuarioFormulario")|| uri.endsWith("paginaPrincipal")|| 
				uri.endsWith("login") || uri.endsWith("/") || uri.endsWith("loginFormulario"))
		{
			chain.doFilter(request, response);
		}
		
		else if(((HttpServletRequest) request).getSession().getAttribute("usuario_logado") != null)
		{
			chain.doFilter(request, response);
		}
		
		else if( uri.endsWith("direcionarNoticia") || uri.endsWith("listarNoticiasSecao") 
				|| uri.endsWith("listarClassificados")|| uri.endsWith("recuperarSenha"))
		{
			chain.doFilter(request, response);
		}
		
		else if(uri.endsWith("enviarEmail") || uri.endsWith("informacaoAutor")|| uri.contains("resources/"))
		{
			chain.doFilter(request, response);
		}
		
		else {
			((HttpServletResponse) response).sendRedirect("paginaPrincipal");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
