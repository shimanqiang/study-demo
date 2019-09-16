package me.shimanqiang.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author shimanqiang
 * @since 2019/9/6 11:30
 */
public class SpringMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);

        TestBean bean = context.getBean(TestBean.class);

        System.out.println(bean);
    }
}
