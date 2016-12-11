package io.swagger.api;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-08T07:10:17.438Z")
public class ApiOriginFilter implements javax.servlet.Filter {

    private static final Logger logger = LogManager.getLogger(ApiOriginFilter.class);

    private String login;

    private String password;

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        logger.info("Filtre des requètes");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        res.addHeader("Access-Control-Allow-Headers", "Content-Type");
        logger.info("Filtre des requètes");
        String authHeader = req.getHeader("Authorization");
        if (authHeader != null) {
            StringTokenizer st = new StringTokenizer(authHeader);
            if (st.hasMoreTokens()) {
                String basic = st.nextToken();

                if (basic.equalsIgnoreCase("Basic")) {
                    try {
                        String credentials = new String(Base64.decodeBase64(st.nextToken()), "UTF-8");
                        logger.debug("Credentials: " + credentials);
                        int p = credentials.indexOf(":");
                        if (p != -1) {
                            String _username = credentials.substring(0, p).trim();
                            String _password = credentials.substring(p + 1).trim();

                            if (!login.equals(_username) || !password.equals(_password)) {
                                forbidden(res, "Forbidden");
                            }
                            else {
                                chain.doFilter(request, response);
                            }
                        } else {
                            forbidden(res, "Forbidden");
                        }
                    } catch (UnsupportedEncodingException e) {
                        throw new Error("Couldn't retrieve authentication", e);
                    }
                }
            }
        } else {
            unauthorized(res);
        }
    }

    public void destroy() {}
    public void init(FilterConfig filterConfig) throws ServletException {
        login = Constants.API_LOGIN;
        password = Constants.API_PASSWORD;
    }

    private void unauthorized(HttpServletResponse response, String message) throws IOException {
        response.sendError(401, message);
    }

    private void unauthorized(HttpServletResponse response) throws IOException {
        unauthorized(response, "Unauthorized");
        logger.info("Pas d'identification");
    }

    private void forbidden(HttpServletResponse response, String message) throws IOException {
        response.sendError(403,message);
        logger.info("Un requète a été refusé");
    }
}