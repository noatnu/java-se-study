package util.array;


import entiyi.User;
import help.Zhou_StdRandom;
import help.Zhou_String;
import help.Zhou_Word;

/**
 * @author zhou
 */
public class ToObjectArray {
    private static final java.util.concurrent.locks.Lock lock = new java.util.concurrent.locks.ReentrantLock();
    private static final java.util.concurrent.locks.Condition condition = lock.newCondition();
    private volatile static ToObjectArray toObjectArray;
    private final int START = 10;
    private final int END = 100;

    public User[] getUsers(){
        int num = Zhou_StdRandom.uniform(12,20);
        User[] users = new User[num];
        synchronized (this){

            if (start==null)start = START;
            if (end==null)end = END;
            if (start>end&&start!=null&&end!=null){
                int result = start;
                start = end;
                end = result;
            }

            User user = null;
            int j = 0;
            for (int i = 0; i < num-1; i++) {
                user = new User();
                user.setPassword(Zhou_String.toLowerCase(3 + i));
                user.setUsername(Zhou_Word.getEnglishName());
                user.setSalt(Zhou_String.toMath(8));
                user.setLock_User(i<num);
                user.setName(Zhou_Word.getChineseName_Random());
                users[i] = user;
                j = i;
            }
            user = new User();
            user.setUsername("zch");
            j++;
            users[j] = user;
        }
        return users;
    }

    public static ToObjectArray getToObjectArray() {
        try {
            lock.lock();
            if (toObjectArray==null)toObjectArray = new ToObjectArray();
            Thread.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();//锁释放!
        }
        return toObjectArray;
    }

    private ToObjectArray(){}

    private Integer start;

    private Integer end;

    public ToObjectArray setStart(Integer start) {
        synchronized(this){
            this.start = start;
        }
        return this;
    }

    public ToObjectArray setEnd(Integer end) {
        synchronized (this){
            this.end = end;
        }
        return this;
    }
}
