package ca.gologic.league.game.config;

import io.micrometer.tracing.CurrentTraceContext;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class TraceFilter implements Filter {

    private static final String TRACE_ID_HEADER_NAME = "traceparent";
    public static final String DEFAULT = "00";
    private final Tracer tracer;

    public TraceFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        if (!response.getHeaderNames().contains(TRACE_ID_HEADER_NAME)) {
            if(Optional.of(tracer).map(Tracer::currentTraceContext).map(CurrentTraceContext::context).isEmpty()) {
                chain.doFilter(req, res);
                return;
            }
            var context = tracer.currentTraceContext().context();
            var traceId = context.traceId();
            var parentId = context.spanId();
            var traceparent = DEFAULT + "-" + traceId + "-" + parentId + "-" + DEFAULT;
            response.setHeader(TRACE_ID_HEADER_NAME, traceparent);
        }
        chain.doFilter(req, res);
    }
}
