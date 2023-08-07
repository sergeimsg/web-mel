package by.itacademy.htp.ex.util.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	private String code;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		code = filterConfig.getInitParameter("encoding");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String codeRequest = request.getCharacterEncoding();

		if (code != null && !code.equals(codeRequest)) {
			request.setCharacterEncoding(code);
			response.setCharacterEncoding(code);

			}
		
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		code = null;
		

	}

}
