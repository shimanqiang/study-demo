package me.shimanqiang.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shimanqiang
 * @since 2019/9/6 11:31
 */
@Configuration
public class BeansConfig {

    @Bean
    public TestBean testBean() {
        TestBean bean = new TestBean();
        bean.setName("Tom");
        bean.setAge("14");
        return bean;
    }
}
