package by.itacademy.htp.ex.util.filter;

import java.io.IOException;



import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/*"}, initParams = {@WebInitParam(name="local", value="en", description = "Set Locale")})
public class SetLocaleFilter implements Filter {
	
			private String localeInitParam;
			private static final String LOCAL = "local"; 

		@Override
	public void init(FilterConfig filterConfig) throws ServletException {
			
			localeInitParam = filterConfig.getInitParameter(LOCAL);
								 
			
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		String checkLocale = (String) session.getAttribute(LOCAL);
		
				
		if (checkLocale == null ) {
			
			session.setAttribute("local",localeInitParam);
			
			
		}
		
		chain.doFilter(request, response);
	}



	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

}
