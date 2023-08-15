package by.itacademy.htp.ex.controller.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	private String chracterEncoding;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		chracterEncoding = filterConfig.getInitParameter("encoding");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String codeRequest = request.getCharacterEncoding();

		if (chracterEncoding != null && !chracterEncoding.equals(codeRequest)) {
			request.setCharacterEncoding(chracterEncoding);
			response.setCharacterEncoding(chracterEncoding);

			}
		
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		chracterEncoding = null;
		

	}

}
