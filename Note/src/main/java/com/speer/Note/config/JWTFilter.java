package com.speer.Note.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

// Implementing Filter interface of jakarta.servlet
@Configuration
public class JWTFilter implements Filter {

	// Filtering is performed in the doFilter method of Filter
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String authHeader = req.getHeader("authorization");
		
		if("OPTIONS".equals(req.getMethod())){
			res.setStatus(HttpServletResponse.SC_OK);
			filterchain.doFilter(request, response);
		}else {
			
			if(authHeader == null || !authHeader.startsWith("Bearer ")) {
				throw new ServletException("Missing or Invalid Authorization header");
			}
			
			String token = authHeader.split(" ")[1];
			String username = req.getHeader("username");
			try {
			final Claims claims = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody();
			if(!username.equals(claims.getSubject())){
				throw new ServletException("Token mismatch with username");
			}
			request.setAttribute("claims", claims);
			filterchain.doFilter(request, response);
			}catch(JsonParseException j) {
				System.out.println(j.getMessage());
			}
		}		
	}
}
