package tool.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: zch
 * @Date: 2019/2/2 18:39
 * @Description:实现描述：获取spring容器，以访问容器中定义的其他bean
 */
@Component
@Lazy(false)
public class SpringContextUtils implements ApplicationContextAware {

    private final static Logger logger = LoggerFactory.getLogger(SpringContextUtils.class);

    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    /** 使用闭锁来管理当spring容器还没有启动的时候被调用的问题 */
    public static final CountDownLatch latch = new CountDownLatch(1);

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return SpringContextUtils.applicationContext;
    }

    /**
     * 获取对象,通过Class类型拿到实例对象，前提实例名字和类名一致且第一个字符为小写
     *
     * @param targetClass
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> targetClass) throws BeansException {
        String clazzName = StringUtils.substringAfterLast(targetClass.getName(), ".");
        String instanceName = clazzName.substring(0, 1).toLowerCase() + clazzName.substring(1);
        return (T) getBean(instanceName);
    }

    /**
     * 获取对象 这里重写了bean方法，起主要作用
     *
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        if (applicationContext == null) {
            try {
                latch.await();
            } catch (InterruptedException e) {
                logger.error("Interrupt latch!", e);
                return null;
            }
        }

        if (applicationContext == null) {
            logger.error("Occur some unpredictable error, applicationContext is null.");
            return null;
        }

        return SpringContextUtils.applicationContext.getBean(name);
    }

    /**
     * @param applicationContext
     */
    private static void initialize(ApplicationContext applicationContext) {
        SpringContextUtils.applicationContext = applicationContext;
    }

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     *
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtils.initialize(applicationContext);
        latch.countDown();
    }

}