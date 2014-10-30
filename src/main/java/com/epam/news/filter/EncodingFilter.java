package com.epam.news.filter;

import javax.servlet.*;


import java.io.IOException;


/**
 * Encoding filter.
 * Filter all request respond to custom encoding.
 * Get encoding from web.xml param. By default using utf8.
 * @author Alexander_Demeshko
 *
 */
public class EncodingFilter implements Filter {

    private String encoding = "utf-8";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        response.setCharacterEncoding(encoding);
        request.setCharacterEncoding(encoding);
        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;      
        }
    }

    public void destroy() {
        // nothing to do
    }

}