package com.scaffold.filter;

import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)
@Log4j2
public class RunTimeLoggingFilter implements Filter {

    private static final String X_LOG_LEVEL = "x-log-level";
    private static final String X_REQUEST_ID = "x-request-id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        // to update log level on the fly
        updateMDC(req, X_LOG_LEVEL);

        // to relate logs with the request id
        updateMDC(req, X_REQUEST_ID);
        chain.doFilter(request, response);
    }

    private void updateMDC(HttpServletRequest request, String requestParam) {
        log.info("RunTimeLoggingFilter#updateMDC");
        String value = request.getHeader(requestParam);
        if (value != null) {
            log.debug("updating MDC : %s = %s", requestParam, value);
            MDC.put(requestParam, value);
        }
    }
}
