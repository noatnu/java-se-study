package jdbc.dao;

import org.testng.annotations.Test;
import other.jdbc.domin.User;
import other.jdbc.service.UserService;
import zch.help.Zhou_StdRandom;
import zch.help.Zhou_String;
import zch.help.Zhou_Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserServiceTest {

    private final UserService userService = new UserService();

    @Test
    public void add(){
        String[] roles = new String[]{"普通用户","学生","底层老百姓","士族"};
        User user = new User();
        user.setAccount(Zhou_StdRandom.uniform(2000,100000)+"");
        user.setAddress("china");
        user.setAge(Zhou_StdRandom.uniform(0,100));
        user.setGroup("中石油");
        user.setJurisdiction("都督");
        user.setPassword(Zhou_StdRandom.uniform(100000,999999)+"");
        user.setName(Zhou_Word.getChineseName_Random());
        user.setUsername(Zhou_Word.getEnglishName());
        user.setPermission("user:remove");
        user.setRole(roles[Zhou_StdRandom.uniform(0,roles.length-1)]);
        user.setSex(new Random().nextBoolean()?"man":"woman");
        try {
            userService.insert(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void batch(){
        List<User> users = new ArrayList<>();
        int num =1000000;
        User user = null;
        for (int i = 0; i < num; i++) {
            user = new User();
            String[] roles = new String[]{"普通用户","学生","底层老百姓","士族"};
            user.setAccount(Zhou_StdRandom.uniform(2000,100000)+"");
            user.setAddress("china");
            user.setAge(Zhou_StdRandom.uniform(0,100));
            user.setGroup("中石油");
            user.setJurisdiction("都督");
            user.setPassword(Zhou_StdRandom.uniform(100000,999999)+"");
            user.setName(Zhou_Word.getChineseName_Random());
            user.setUsername(Zhou_Word.getEnglishName());
            user.setPermission("user:remove");
            user.setRole(roles[Zhou_StdRandom.uniform(0,roles.length-1)]);
            user.setSex(new Random().nextBoolean()?"man":"woman");
            users.add(user);
        }
        long start = System.currentTimeMillis();
        userService.batch(users);
        long time = (System.currentTimeMillis()-start)/1000;
        System.out.println(time);
    }

    @Test
    public void find(){
        List<User> users = userService.find();
        users.forEach(user -> {
            System.out.println(user);
        });
    }

    @Test
    public void findByID(){
        String id = "8ae9b840-39a8-11e8-8103-74e543dde5e3";
        User user = userService.findByUserId(id);
        System.out.println(user);
    }

    @Test
    public void update(){
        String id = "8ae99663-39a8-11e8-8103-74e543dde5e3";
        User user = userService.findByUserId(id);
        user.setName("张三");
        userService.update(user);
    }

    @Test
    public void delete(){
        String id = "a7007586-3991-11e8-8103-74e543dde5e3";
        userService.delete(id);
    }
}
