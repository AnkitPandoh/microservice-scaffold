package com.scaffold.filter;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)
public class RunTimeLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getHeader("X-Log-Level") != null) {
            MDC.put("X-Log-Level", req.getHeader("X-Log-Level"));
        }
        chain.doFilter(request, response);
    }
}
