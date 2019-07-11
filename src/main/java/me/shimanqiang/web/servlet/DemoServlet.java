package me.shimanqiang.web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @author shimanqiang
 * @since 2019/6/10 11:26
 */
@WebServlet(
        name = "DemoServlet",
        urlPatterns = "/api/demo",
        loadOnStartup = 1,
        asyncSupported = true,
        initParams = {
                @WebInitParam(name = "abc", value = "123"),
                @WebInitParam(name = "def", value = "456")
        }
)
public class DemoServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {

    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        res.setCharacterEncoding("utf-8");

        PrintWriter pw = res.getWriter();
        pw.append("aaaaaaaaa");
        pw.append("\n");
        ServletConfig servletConfig = this.getServletConfig();
        Enumeration<String> paramNames = servletConfig.getInitParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            pw.append(paramName + "：" + servletConfig.getInitParameter(paramName) + "<br>");
        }

        pw.flush();
        pw.close();
    }

    @Override
    public void destroy() {

    }
}
