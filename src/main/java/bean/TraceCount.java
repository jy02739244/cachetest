package bean;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Title Created by xzm
 * @date 2017/3/3.
 */
@Table(name="traceCount")
public class TraceCount {

    @Id
    private int id;

    private int count;

    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

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
}
