package pl.sternik.kk.week.hello;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter(filterName = "/MyRESTFilter2", 
//servletNames = { "HelloServlet" }, 
urlPatterns = { "/hello" })
public class MyRESTFilter2 implements javax.servlet.Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        
        System.out.println("--------------- filtr 2222 przed ---------------");
        
        chain.doFilter(req, res);

        System.out.println("--------------- filtr 2222 po ---------------");

        HttpServletRequest r =  (HttpServletRequest) req;
        HttpSession session = r.getSession(true);
        if(session != null){
            System.out.println("----Mam sesje!!!");
            System.out.println("Sesion id:"+session.getId());
            Enumeration<String> attributeNames = session.getAttributeNames();
            while(attributeNames.hasMoreElements())
                System.out.println(attributeNames.nextElement());
            session.putValue("ddd", "Kukuku");
        }
        
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}