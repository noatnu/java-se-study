package other.jdk8.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author noatn
 * @Description 商品
 * @createDate 2019/2/4
 **/
public class Commodity implements Serializable,Comparable<Commodity> {

    private java.math.BigDecimal price;
    private String name;
    private Date createDate;

    @Override
    public int compareTo(Commodity o) {
        return this.getName().compareTo(o.getName());
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Commodity commodity = (Commodity) o;

        return new EqualsBuilder()
                .append(price, commodity.price)
                .append(name, commodity.name)
                .append(createDate, commodity.createDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(price)
                .append(name)
                .append(createDate)
                .toHashCode();
    }
}
