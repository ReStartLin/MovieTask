package restart.com.movietask.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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
import restart.com.movietask.ShowMovieActivity;
import restart.com.movietask.bean.Movie;

/**
 * Created by Administrator on 2018/5/11.
 */

public class MyAllMovieAdapter extends RecyclerView.Adapter<MyAllMovieAdapter.AllMovieViewHolder> {
    private Context context;
    private List<Movie> data = new ArrayList<>();
    private LayoutInflater inflater;

    public MyAllMovieAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setData(List<Movie> data) {
        this.data = data;
    }
    @NonNull
    @Override
    public MyAllMovieAdapter.AllMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllMovieViewHolder(inflater.inflate(R.layout.movie_item_simple,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllMovieViewHolder holder, int position) {
        final Movie movie = data.get(position);
        Picasso.with(context)
                .load(Uri.parse(movie.getImageUrl()))
                .placeholder(R.drawable.temp)
                .into(holder.iv_movie_img);
        holder.tv_movie_name.setText(movie.getTitle());
        float average = Float.parseFloat(movie.getRating().getAverage());
        holder.rb_average.setRating(average/2.0f);
        holder.tv_movie_average.setText(String.valueOf(average));
        holder.cv_simple_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = ShowMovieActivity.getInstance(context, movie);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        return data.size();
    }

     static class AllMovieViewHolder extends RecyclerView.ViewHolder {
         @BindView(R.id.id_iv_movie_img)
          ImageView iv_movie_img;
         @BindView(R.id.id_tv_movie_name)
          TextView tv_movie_name;
         @BindView(R.id.id_rb_average)
          RatingBar rb_average;
         @BindView(R.id.id_tv_movie_average)
          TextView tv_movie_average;

         @BindView(R.id.id_cv_simple_view)
         CardView cv_simple_view;

          AllMovieViewHolder(View itemView) {
             super(itemView);
             ButterKnife.bind(this, itemView);
         }
    }

}
