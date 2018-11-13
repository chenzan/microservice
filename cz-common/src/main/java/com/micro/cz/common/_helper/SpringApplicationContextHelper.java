package com.micro.cz.common._helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author chenzan
 * @version V1.0
 * @description TODO
 * @create-date 2018/11/13
 * @modifier
 * @modifier-data
 */
@Component
public class SpringApplicationContextHelper implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringApplicationContextHelper.applicationContext = applicationContext;
    }

    /**
     * 获取实例
     *
     * @param requiredType
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    /**
     * 获取实例
     *
     * @param beanName
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanName, Class<T> tClass) {
        return applicationContext.getBean(beanName, tClass);
    }
}
