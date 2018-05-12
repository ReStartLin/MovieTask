package restart.com.movietask;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import restart.com.movietask.bean.Movie;
import restart.com.movietask.util.ShowInfo;

public class ShowMovieActivity extends AppCompatActivity {
    public static final String MOVIE = "MOVIE";

    @BindView(R.id.id_rl_show)
    public RelativeLayout rl_show;
    @BindView(R.id.id_show_movie_img)
    public ImageView show_movie_img;
    @BindView(R.id.id_show_movie_title)
    public TextView show_movie_title;
    @BindView(R.id.id_tv_show_movie_director)
    public TextView show_movie_director;
    @BindView(R.id.id_tv_show_movie_cats)
    public TextView show_movie_cats;
    @BindView(R.id.id_tv_show_movie_time)
    public TextView show_movie_time;
    @BindView(R.id.id_tv_show_movie_type)
    public TextView show_movie_type;
    @BindView(R.id.id_tv_show_movie_description)
    public TextView show_movie_description;


    private Movie data;

    //滑动监听
    private float y1 = 0;
    private float y2 = 0;
    public static final int IMG_HEIGHT_MAX = 300; //图像高度最大值 dp
    public static final int IMG_HEIGHT_MIN = 150; //图像高度最小值 dp
    public static final int IMG_HEIGHT_OFFSET = 150; //图像高度差值 dp

    public static final Float IMG_APACHE_MIN = 0.5f;//图像透明度最小值
    public static final Float IMG_APACHE_OFFSET = 0.5f;//图像透明度差值
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movie);
        Intent intent = this.getIntent();
        data = (Movie) intent.getSerializableExtra(MOVIE);
        if (data == null) {
            Toast.makeText(this, "数据有误", Toast.LENGTH_SHORT).show();
            finish();
        }
        ButterKnife.bind(this);
        bindData();



    }

    private void bindData() {
        Picasso.with(this)
                .load(Uri.parse(data.getImageUrl()))
                .placeholder(R.drawable.temp)
                .into(show_movie_img);
        show_movie_title.setText(data.getTitle());
        show_movie_director.setText(ShowInfo.getDirector(data.getDirectors()));
        show_movie_cats.setText(ShowInfo.getCat(data.getCasts()));
        show_movie_time.setText("上映: "+data.getYear());
        show_movie_type.setText(ShowInfo.getTypes(data.getTypes()));
        show_movie_description.setText("故事简介:\n"+data.getDescription());

    }

    public static Intent getInstance(Context context,Movie movie) {
        Intent intent = new Intent(context,ShowMovieActivity.class);
        intent.putExtra(MOVIE, movie);
        return intent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE) {
            //当手指离开的时候
            int minheight = dp2px(this, IMG_HEIGHT_MIN);
            int maxheight = dp2px(this, IMG_HEIGHT_MAX);
            y2 = event.getY();
            float change = y2 - y1;
            y1 = y2;
            int height = rl_show.getHeight();
            int newheight = 0;

            if (height >= minheight  && height <=  maxheight) {
                int temp = (int) (height + change) ;
                newheight = (temp < minheight ? minheight : (temp > maxheight ? maxheight : temp));
                ViewGroup.LayoutParams layoutParams = rl_show.getLayoutParams();
                layoutParams.height = newheight;
                rl_show.setLayoutParams(layoutParams);
            }
            int abs = Math.abs(newheight - minheight);
            int ff = dp2px(this, IMG_HEIGHT_OFFSET);
            float i = Float.valueOf(abs) / Float.valueOf(ff);
            show_movie_img.setAlpha(i*IMG_APACHE_OFFSET+IMG_APACHE_MIN);
        }

        return super.onTouchEvent(event);
    }
    /*
     * dp转换成px
     */
    private int dp2px(Context context,float dpValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }
}
