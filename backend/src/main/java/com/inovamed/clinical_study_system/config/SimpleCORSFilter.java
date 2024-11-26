package com.inovamed.clinical_study_system.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SimpleCORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;


        String origin = request.getHeader("Origin");
        System.out.println(origin+ " aqui 1");

        List<String> allowedOrigins = Arrays.asList("https://inovamed-sigma.vercel.app/login","https://inovamed-sigma.vercel.app","https://inovamed-sigma.vercel.app/", "http://localhost:5173");
        if (allowedOrigins.contains(origin)) {
            System.out.println(origin+ " aqui 2");
            response.setHeader("Access-Control-Allow-Origin", origin);
        }

        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            System.out.println(" aqui 3");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            System.out.println(" aqui 4");
            chain.doFilter(req, res);
        }
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
