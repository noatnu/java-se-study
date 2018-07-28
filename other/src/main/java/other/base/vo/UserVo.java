package other.base.vo;

/**
 * @Auther: zch
 * @Date: 2018/7/28 18:33
 * @Description:
 */
public class UserVo {
    public String name = "blake";
    private int age = (int) StrictMath.round(StrictMath.random() * 1000);
    private String address = (char) StrictMath.round(StrictMath.random() * 100000) + "" + (char) StrictMath.round(StrictMath.random() * 100000);

    protected UserVo(int age, String address) {
        this.age = age;
        this.address = address;
    }

    public UserVo() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
