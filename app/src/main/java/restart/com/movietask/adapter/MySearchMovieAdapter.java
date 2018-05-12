package restart.com.movietask.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import restart.com.movietask.R;
import restart.com.movietask.bean.Cast;
import restart.com.movietask.bean.Director;
import restart.com.movietask.bean.Movie;

/**
 * Created by Administrator on 2018/5/12.
 */

public class MySearchMovieAdapter extends RecyclerView.Adapter<MySearchMovieAdapter.SearchMovieViewHolder> {
    private Context context;
    private List<Movie> data = new ArrayList<>();
    private LayoutInflater inflater;

    public MySearchMovieAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        return data.size();
    }
    @NonNull
    @Override
    public SearchMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchMovieViewHolder(inflater.inflate(R.layout.movie_item_search,parent,false));
    }

    public void setData(List<Movie> data) {
        this.data = data;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMovieViewHolder holder, int position) {
        Movie movie = data.get(position);
        Picasso.with(context)
                .load(Uri.parse(movie.getImageUrl()))
                .placeholder(R.drawable.temp)
                .into(holder.iv_search_movie_img);
        holder.tv_search_movie_name.setText(movie.getTitle());
        float average = Float.parseFloat(movie.getRating().getAverage());
        holder.rb_search_movie_average.setRating(average/2.0f);
        holder.tv_search_movie_average.setText(String.valueOf(average));

        holder.tv_search_movie_director.setText(getDirector(movie.getDirectors()));
        holder.tv_search_movie_cats.setText(getCat(movie.getCasts()));
        holder.tv_search_movie_time.setText("上映: "+movie.getYear());
    }

    /**
     * 获取主演
     * @param casts
     * @return
     */
    private String getCat(List<Cast> casts) {
        StringBuilder stringBuilder = new StringBuilder("主演:");
        for (Cast cast : casts) {
            stringBuilder.append(" "+cast.getName());
        }
        return stringBuilder.toString();
    }



    /**
     * 获取导演
     * @param directors
     * @return
     */
    private String getDirector(List<Director> directors) {
        StringBuilder stringBuilder = new StringBuilder("导演:");
        for (Director director : directors) {
            stringBuilder.append(" "+director.getName());
        }
        return stringBuilder.toString();
    }

    static class SearchMovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.id_iv_search_img)
        ImageView iv_search_movie_img;
        @BindView(R.id.id_tv_search_movie_name)
        TextView tv_search_movie_name;
        @BindView(R.id.id_rb_search_movie_average)
        RatingBar rb_search_movie_average;
        @BindView(R.id.id_tv_search_movie_average)
        TextView tv_search_movie_average;
        @BindView(R.id.id_tv_search_movie_director)
        TextView tv_search_movie_director;
        @BindView(R.id.id_tv_search_movie_cats)
        TextView tv_search_movie_cats;
        @BindView(R.id.id_tv_search_movie_time)
        TextView tv_search_movie_time;



        SearchMovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
