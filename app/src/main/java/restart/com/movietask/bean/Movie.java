package restart.com.movietask.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/10.
 */

public class Movie implements Serializable {
    private String id;
    private Rating rating;
    private String title;
    private String description;
    private String year;
    private String imageUrl;
    private List<String> types;
    private List<Cast> casts;
    private List<Director> directors;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }
    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", rating=" + rating +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", year='" + year + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", types=" + types +
                ", casts=" + casts +
                ", directors=" + directors +
                '}';
    }


}
