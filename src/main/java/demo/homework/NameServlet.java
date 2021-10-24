package demo.homework;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/name")
public class NameServlet extends HttpServlet {
    private static final String SPRING_APP_CONTEXT = "SPRING_APP_CONTEXT";

    @Override
    public void init(ServletConfig servletConfig) {
        var applicationContext = new AnnotationConfigApplicationContext(NameProvider.class);
        servletConfig.getServletContext().setAttribute(SPRING_APP_CONTEXT, applicationContext);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        var context = (AnnotationConfigApplicationContext) req.getServletContext().getAttribute(SPRING_APP_CONTEXT);
        try {
            var nameProvider = context.getBean(NameProvider.class);
            PrintWriter writer = resp.getWriter();
            writer.println(nameProvider.getName());
        } catch (IOException | BeansException e) {
            e.printStackTrace();
        }
    }
}