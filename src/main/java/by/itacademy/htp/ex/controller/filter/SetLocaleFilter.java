package by.itacademy.htp.ex.controller.filter;

import java.io.IOException;

import by.itacademy.htp.ex.controller.impl.ConstName;
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
	
	private String localeParameterFromInitParams;


		@Override
	public void init(FilterConfig filterConfig) throws ServletException {
			
			localeParameterFromInitParams = filterConfig.getInitParameter( ConstName.ATTRIBUTE_TO_CHECK_LOCALE);
								 
			
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		String checkLocale = (String) session.getAttribute(ConstName.ATTRIBUTE_TO_CHECK_LOCALE);
		
				
		if (checkLocale == null ) {
			
			session.setAttribute(ConstName.ATTRIBUTE_TO_CHECK_LOCALE, localeParameterFromInitParams );
			
			
		}
		
		chain.doFilter(request, response);
	}



	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

}
