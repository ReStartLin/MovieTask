package restart.com.movietask.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/10.
 */

public class Rating  implements Serializable {
    private int max;
    private String average;
    private String stars;
    private int min;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "max=" + max +
                ", average=" + average +
                ", stars='" + stars + '\'' +
                ", min=" + min +
                '}';
    }
}
