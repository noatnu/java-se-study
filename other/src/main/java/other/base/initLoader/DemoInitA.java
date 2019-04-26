package other.base.initLoader;

import java.util.Objects;

/**
 * @author: zch
 * @date: 2019/4/18 13:58
 * @description:
 */
public class DemoInitA {
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemoInitA demoInitA = (DemoInitA) o;
        return Objects.equals(name, demoInitA.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DemoInitA{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
