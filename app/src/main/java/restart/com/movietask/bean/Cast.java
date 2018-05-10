package restart.com.movietask.bean;

/**
 * Created by Administrator on 2018/5/10.
 */

public class Cast {
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
