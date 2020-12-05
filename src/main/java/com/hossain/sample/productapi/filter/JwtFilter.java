package com.hossain.sample.productapi.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hossain.sample.productapi.repository.UserDetailsRepository;
import com.hossain.sample.productapi.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter implements WebMvcConfigurer {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsRepository service;


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,observe");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		response.addHeader("Access-Control-Expose-Headers", "responseType");
		response.addHeader("Access-Control-Expose-Headers", "observe");
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        System.out.println("WebConfig; " + request.getRequestURI());
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            userName = jwtUtil.extractUsername(token);
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = service.loadUserByUsername(userName);
            if (jwtUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(req, res);
    }
    
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/***")
		.allowedOrigins("http://localhost:4200");
	}
    
}
