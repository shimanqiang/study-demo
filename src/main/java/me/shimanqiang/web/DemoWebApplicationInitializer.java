package me.shimanqiang.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author shimanqiang
 * @since 2019/6/10 14:54
 */

public interface DemoWebApplicationInitializer {
    void onStartup(ServletContext servletContext) throws ServletException;
}
