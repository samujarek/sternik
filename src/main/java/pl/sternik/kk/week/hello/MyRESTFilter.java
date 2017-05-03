package pl.sternik.kk.week.hello;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "/MyRESTFilter", 
//servletNames = { "HelloServlet" }, 
urlPatterns = { "/hello" })
public class MyRESTFilter implements javax.servlet.Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        
        System.out.println("--------------- filtr 1111 przed ---------------");
        System.out.println(req.getParameter("format"));
        
        chain.doFilter(req, res);
        
        System.out.println("--------------- filtr 1111 po ---------------");
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}