package me.shimanqiang.web.init;

import me.shimanqiang.web.DemoWebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author shimanqiang
 * @since 2019/6/10 15:03
 */
public class Demo002WebApplicationInitializer implements DemoWebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("Demo002 init");
    }
}
