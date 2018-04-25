package other.jdbc.util;


public class DynamicProxy {

    private static DynamicProxy dynamicProxy = null;

    public  Object dynamic(Object king,Class sun){
        try {
            king = sun.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return king;
    }

    public static DynamicProxy getDynamicProxy() {
        if (dynamicProxy==null)dynamicProxy = new DynamicProxy();
        return dynamicProxy;
    }

}
