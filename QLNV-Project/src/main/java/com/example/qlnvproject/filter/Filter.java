package com.example.qlnvproject.filter;

import com.example.qlnvproject.jwt.JwtFilter;
import com.example.qlnvproject.jwt.JwtUtil;
import com.example.qlnvproject.model.Employee;
import com.example.qlnvproject.model.History;
import com.example.qlnvproject.model.RoleUri;
import com.example.qlnvproject.model.Uri;
import com.example.qlnvproject.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component

public class Filter extends OncePerRequestFilter {

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    HistoryService historyService;

    @Autowired
    employeeService employeeService;

    @Autowired
    RoleUriService roleUriService;

    @Autowired
    UriService uriService;

    @Autowired
    LogWithUriService logWithUriService;

    private static final Logger LOGGER = LoggerFactory.getLogger(Filter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        String token = jwtFilter.jwtByRequest(request);


        long startTime = System.currentTimeMillis();
        filterChain.doFilter(requestWrapper, responseWrapper);
        long timeTaken = System.currentTimeMillis() - startTime;

        Date date = new Date();


        String requestBody = getStringValue(requestWrapper.getContentAsByteArray(),
                request.getCharacterEncoding());
        String responseBody = getStringValue(responseWrapper.getContentAsByteArray(),
                response.getCharacterEncoding());



        if (token != null) {
            String username = jwtUtil.getUsernameByToken(token);
            Employee employee = employeeService.findByUsername(username);
            String requestUri = request.getRequestURI();
            Uri uri = uriService.findByUri(requestUri);
            RoleUri roleUri = roleUriService.findByRoleIdAndUriId(employee.getRole(), uri);
            if (roleUri != null){
                if ( request.getMethod().equals("POST") && logWithUriService.findByUri(request.getRequestURI()) != null) {
                    String usernameLogin = jwtUtil.getUsernameByToken(token);
                    LOGGER.info(
                            "\n FINISHED PROCESSING :\n USERNAME: {};\n METHOD: {};\n REQUESTURI: {};\n REQUEST: {};\n STATUS: {};" +
                                    "\n RESPONSE: {};\n TIME TAKEN: {};\n Date:{} ",
                            usernameLogin, request.getMethod(), request.getRequestURI(), requestBody, response.getStatus(),
                            responseBody, timeTaken, date);
                    History history = new History(usernameLogin, request.getMethod(), request.getRequestURI(), requestBody,
                            response.getStatus(), responseBody, timeTaken, date);
                    historyService.save(history);
                }
                responseWrapper.copyBodyToResponse();
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }else {
            responseWrapper.copyBodyToResponse();
        }


    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
