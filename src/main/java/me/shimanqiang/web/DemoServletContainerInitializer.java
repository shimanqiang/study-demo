package me.shimanqiang.web;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author shimanqiang
 * @since 2019/6/10 14:54
 */
@HandlesTypes(DemoWebApplicationInitializer.class)
public class DemoServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> webAppInitializerClasses, ServletContext ctx) throws ServletException {
        System.out.println("servlet container init ............");

        List<DemoWebApplicationInitializer> initializers = new LinkedList<DemoWebApplicationInitializer>();

        if (webAppInitializerClasses != null) {
            for (Class<?> waiClass : webAppInitializerClasses) {
                if (!waiClass.isInterface() && !Modifier.isAbstract(waiClass.getModifiers()) &&
                        DemoWebApplicationInitializer.class.isAssignableFrom(waiClass)) {
                    try {
                        initializers.add((DemoWebApplicationInitializer) waiClass.newInstance());
                    } catch (Throwable ex) {
                        throw new ServletException("Failed to instantiate WebApplicationInitializer class", ex);
                    }
                }
            }
        }

        if (initializers.isEmpty()) {
            ctx.log("No Spring WebApplicationInitializer types detected on classpath");
            return;
        }

        ctx.log(initializers.size() + " Spring WebApplicationInitializers detected on classpath");
        for (DemoWebApplicationInitializer initializer : initializers) {
            initializer.onStartup(ctx);
        }
    }
}
