package me.shimanqiang.spring;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;

/**
 * @author shimanqiang
 * @since 2019/9/3 17:21
 */
public class TestAutoProxyCreator extends AbstractAutoProxyCreator implements PointcutAdvisor {
    @Nullable
    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName, @Nullable TargetSource customTargetSource) throws BeansException {
        return new Object[0];
    }

    @Override
    public Pointcut getPointcut() {
        return null;
    }

    @Override
    public Advice getAdvice() {
        return null;
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}
