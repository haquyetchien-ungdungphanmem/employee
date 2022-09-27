package com.example.qlnvproject.Log;

import com.example.qlnvproject.jwt.JwtFilter;
import com.example.qlnvproject.jwt.JwtUtil;
import com.example.qlnvproject.model.History;
import com.example.qlnvproject.service.HistoryService;
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
public class LogFilter extends OncePerRequestFilter {

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    HistoryService historyService;

    private static final Logger LOGGER = LoggerFactory.getLogger(LogFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
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

        if (token != null && request.getMethod().equals("POST")){
            String usernameLogin = jwtUtil.getUsernameByToken(token);
            LOGGER.info(
                    "\n FINISHED PROCESSING :\n USERNAME: {};\n METHOD: {};\n REQUESTURI: {};\n REQUEST: {};\n STATUS: {};" +
                            "\n RESPONSE: {};\n TIME TAKEN: {};\n Date:{} ",
                    usernameLogin, request.getMethod(), request.getRequestURI(), requestBody, response.getStatus(), responseBody,
                    timeTaken, date);
            History history = new History(usernameLogin,request.getMethod(), request.getRequestURI(), requestBody, response.getStatus(), responseBody,
                    timeTaken, date);
            historyService.save(history);
        }else {

        }
        responseWrapper.copyBodyToResponse();
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
