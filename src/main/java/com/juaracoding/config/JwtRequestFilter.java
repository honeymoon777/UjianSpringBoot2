package com.juaracoding.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.juaracoding.Service.JwtPenumpangDetailService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	JwtPenumpangDetailService jwtCustomerDetailService;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiSnVhcmEgQ29kaW5nIn0.nFgKqog3l5cnZ4gB7V8FG9IriHrt67hq-JhClqnfkrU
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = null;
		String username= null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token"); // Ga bisa dapetin jwt token
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired"); // Jwt token lu expired , tolong jeneret lagi
			}
		}else {
			logger.warn("JWT Token does not begin with Bearer String"); // Jwt lu ga mulai dari kata bearer
		}
		
		// ketika kita dapet token itu, kita validasi di code bawah ini
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = jwtCustomerDetailService.loadUserByUsername(username);
			
			//Jika token valid maka kita akan melakukan config
			if(jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(
						userDetails,null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request));
				
				//setelah kita spesifikan authentikasi kita
				// kita memasukannya ke dalam konfigurasi context atau security context
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			
		}
		filterChain.doFilter(request, response);
	}

}
