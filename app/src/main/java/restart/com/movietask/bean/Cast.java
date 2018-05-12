package restart.com.movietask.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/10.
 */

public class Cast implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "name='" + name + '\'' +
                '}';
    }
}
