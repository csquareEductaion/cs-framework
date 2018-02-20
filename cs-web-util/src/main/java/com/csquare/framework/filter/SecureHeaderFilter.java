package com.csquare.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Custom class to hold error information
 *
 * @copyright Copyright (c) Akoni. All Right Reserved.
 * @author Akoni
 */
public class SecureHeaderFilter implements Filter {

    /** variable added to set the value of mode to mitigate Clickjack attack **/
    private String xFrameOption;

    /** variable added to check whether HTTPs is enabled or not **/
    private Boolean httpsEnabled;

    private String allowOrigin;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // String originHeader = httpRequest.getHeader("Origin");
        // if (allowOrigin.contains(originHeader)) {
        httpResponse.setHeader("Access-Control-Allow-Origin", allowOrigin);
        // }

        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD");
        httpResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with,X-CSRF-Token,X-XSRF-Token,Content-Type,Accept,Authorization");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

        httpResponse.addHeader("X-XSS-Protection", "1; mode=block");
        httpResponse.addHeader("X-Content-Type-Options", "nosniff");
        httpResponse.addHeader("X-FRAME-OPTIONS", xFrameOption);

        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setHeader("Expires", "0");

        if (httpsEnabled) {
            httpResponse.addHeader("Strict-Transport-Security", "max-age=16070400; includeSubDomains");
        }

        chain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) {

        this.httpsEnabled = false;
        this.xFrameOption = "SAMEORIGIN";
        this.allowOrigin = getAllowedOrigin();
    }

    /**
     * Method to get Origin List as String
     *
     * @return allowedOrigin - The String
     */
    private String getAllowedOrigin() {

        StringBuilder allowedOrigin = new StringBuilder("*");
        return allowedOrigin.toString();
    }

    @Override
    public void destroy() {

    }
}