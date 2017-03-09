package bean;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Title Created by xzm
 * @date 2017/3/6.
 */
@Table(name="goods")
public class Goods {
    @Id
    private int id;
    private String name;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
