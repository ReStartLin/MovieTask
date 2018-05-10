package restart.com.movietask.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/10.
 */

public class Json2Movie {
    private int count;
    private int start;
    private int total;
    private List<Movie> movies;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Json2Movie{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", movies=" + movies +
                '}';
    }
}
