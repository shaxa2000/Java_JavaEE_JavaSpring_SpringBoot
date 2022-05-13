package com.dvd.web;



import com.dvd.model.DVDLIbrary;
import java.util.Base64;

import java.io.*;
import java.nio.charset.StandardCharsets;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest _request, ServletResponse _response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) _request;
        HttpServletResponse response = (HttpServletResponse) _response;
//        ServletContext context = filterConfig.getServletContext();

        HttpSession session = request.getSession();
        DVDLIbrary library = (DVDLIbrary) session.getAttribute("library");

        Throwable problem = null;

        if ( library != null ) {
            try {
                chain.doFilter(request, response);
            } catch(Throwable t) {

                problem = t;
                t.printStackTrace();
            }
        } else {

            String credentials = request.getHeader("Authorization");
            if ( credentials != null ) {

                credentials = credentials.substring(6);

                byte[] data =  credentials.getBytes(StandardCharsets.UTF_8);
                String pair = new String(data);


                String username = pair.substring(0, pair.indexOf(':'));



                library = new DVDLIbrary(username);
//                session.setAttribute("library", library);



                try {
                    chain.doFilter(request, response);
                } catch(Throwable t) {
                    problem = t;
                    t.printStackTrace();
                }

            } else {

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setHeader("WWW-Authenticate", "Basic realm=dvdLogin");
            }
        }
    }

    @Override
    public void destroy() {

    }
}