package other.base.reflection;

import org.springframework.util.ObjectUtils;
import tool.log.LoggerFactoryGET;

import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * @Auther: zch
 * @Date: 2018/7/28 18:31
 * @Description:反射方法
 */
public class ReflectionExampleMethodDemo {
    private final Logger logger = LoggerFactoryGET.getLoggerFactory().getLoggerAll();

    public static void main(String[] args) {
        final Logger logger = LoggerFactoryGET.getLoggerFactory().getLoggerAll();
        try {
            Class<?> cClass = Class.forName("other.base.reflection.ReflectionExampleMethodDemo");
            logger.info(String.format("class name: %s", cClass.getSimpleName()));

            Method[] methods = null;
            /*获取所有的public修饰的方法 包括父类的方法*/
            methods = cClass.getMethods();
            if (ObjectUtils.isArray(methods)) {
                for (Method method : methods) {
//                    logger.info(String.format("method name: %s",method.getName()));
                }
            }

            /*获取class对象的所有声明方法:建议平时尽量使用getDeclaredMethods()*/
            methods = cClass.getDeclaredMethods();
            if (ObjectUtils.isArray(methods)) {
                for (Method method : methods) {
                    logger.info(String.format("DeclaredMethod name: %s", method.getName()));
                }
            }
        } catch (ClassNotFoundException e) {
            logger.warning(String.format("error: %s", e.getMessage()));
        }
    }

    public void info() {
        logger.info(String.format("info() ===>", System.currentTimeMillis()));
    }

    protected void log() {
        logger.info(String.format("log() ===>", System.currentTimeMillis()));
    }
}
