package restart.com.movietask.loader;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import restart.com.movietask.bean.Movie;
import restart.com.movietask.biz.MyHttpBiz;

/**
 * Created by Administrator on 2018/5/11.
 */

public class MySearchLoader extends android.support.v4.content.AsyncTaskLoader<List<Movie>> {
    private Bundle bundle;
    private List<Movie> data;
    public MySearchLoader(Context context,Bundle args) {
        super(context);
        bundle = args;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (isStarted()) {
            forceLoad();
        }
    }

    @Override
    public List<Movie> loadInBackground() {
        if (bundle == null) {
            data = MyHttpBiz.getInstance().getAllMovie();
            if (data == null) {
                Log.d("tag", "loadInBackground:  data null ----------------");
            }
        } else {
            String title = bundle.getString("title");
            String type = bundle.getString("type");
            data = MyHttpBiz.getInstance().getSearchMovie(title, type);
            if (data == null) {
                Log.d("tag", "loadInBackground:  data null ----------------");
            }

        }
        return data;
    }
}
