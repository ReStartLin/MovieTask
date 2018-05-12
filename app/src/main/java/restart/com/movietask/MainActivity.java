package restart.com.movietask;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import restart.com.movietask.adapter.MyAllMovieAdapter;
import restart.com.movietask.adapter.MySearchMovieAdapter;
import restart.com.movietask.bean.Movie;
import restart.com.movietask.loader.MySearchLoader;

public class MainActivity extends AppCompatActivity  implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<Movie>> {
    private static final String ALL = "all";
    private static final String SEARCH = "search";

    @BindView(R.id.id_edt_movie_name)
    public EditText edt_movie_name;//筛选的电影名字
    @BindView(R.id.id_sp_movie_type)
    public Spinner sp_movie_type;//选择的类型
    @BindView(R.id.id_ib_search)
    public ImageButton ib_search;//查找

    @BindView(R.id.id_rl_all_movie)
    public RelativeLayout rl_all_movie;//所有电影
    @BindView(R.id.id_rv_all_movie)
    public RecyclerView rv_all_movie;//所有电影的recyclerview

    @BindView(R.id.id_rl_search_movie)
    public RelativeLayout rl_search_movie;//查找的电影
    @BindView(R.id.id_tv_back)
    public TextView tv_back;//返回
    @BindView(R.id.id_rv_search_movie)
    public RecyclerView rv_search_movie;//查找的电影的recyelerview
    @BindView(R.id.id_tv_search)
    public TextView tv_search;//查找的说明
    //dialog
    private ProgressDialog mLoadingDialog;

    private String nowType = "";


    private MyAllMovieAdapter myAllMovieAdapter;
    private MySearchMovieAdapter mySearchMovieAdapter;
    private android.support.v4.app.LoaderManager supportLoaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //加载Dialog
        mLoadingDialog = new ProgressDialog(this);
        mLoadingDialog.setMessage("加载中...");

        myAllMovieAdapter = new MyAllMovieAdapter(this);
        rv_all_movie.setLayoutManager(new GridLayoutManager(this,2));
        rv_all_movie.setAdapter(myAllMovieAdapter);
        mySearchMovieAdapter = new MySearchMovieAdapter(this);
        rv_search_movie.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL,false));
        rv_search_movie.setAdapter(mySearchMovieAdapter);

        initEvent();
        //changeView(ALL);
        supportLoaderManager = getSupportLoaderManager();
        supportLoaderManager.initLoader(0, null, this);
        startLoadingProgress();


    }

    private void initEvent() {
        sp_movie_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                nowType = sp_movie_type.getItemAtPosition(position).toString();
                if (nowType.equals("请选择")) {
                    nowType = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * 转换视图
     * @param rl
     */
    private void changeView(String rl) {
        switch (rl) {
            case ALL:
                rl_all_movie.setVisibility(View.VISIBLE);
                rl_search_movie.setVisibility(View.GONE);
                break;
            case SEARCH:
                rl_all_movie.setVisibility(View.GONE);
                rl_search_movie.setVisibility(View.VISIBLE);
                break;
        }
    }

    @OnClick({R.id.id_ib_search,R.id.id_tv_back})
    public void bindClick(View view) {
        switch (view.getId()) {
            case R.id.id_ib_search:
                changeView(SEARCH);
                search();
                break;
            case R.id.id_tv_back:
                changeView(ALL);
                searchAll();
                break;
        }
    }

    private void search() {
        String title = edt_movie_name.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        bundle.putString("type",nowType);
        supportLoaderManager.restartLoader(1, bundle, this);
        startLoadingProgress();
    }
    private void searchAll() {
        supportLoaderManager.restartLoader(0, null, this);
        startLoadingProgress();
    }


    @NonNull
    @Override
    public android.support.v4.content.Loader<List<Movie>> onCreateLoader(int id, @Nullable Bundle args) {
        return new MySearchLoader(this,args);
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<List<Movie>> loader, List<Movie> data) {
        stopLoadingProgress();
        if (data == null) {
            return;
        }
        switch (loader.getId()) {
            case 0:
                if (myAllMovieAdapter != null) {
                    myAllMovieAdapter.setData(data);
                    myAllMovieAdapter.notifyDataSetChanged();
                }
                break;
            case 1:
                if (data.size() == 0) {
                    tv_search.setText("无结果");
                } else {
                    tv_search.setText("搜索");
                }
                Log.d("tag", "onLoadFinished: ---------------------------------------");
                for (Movie datum : data) {

                        Log.d("tag", "onLoadFinished:"+datum.toString());

                }
                Log.d("tag", "onLoadFinished: ---------------------------------------");
                if (mySearchMovieAdapter != null) {
                    mySearchMovieAdapter.setData(data);
                    mySearchMovieAdapter.notifyDataSetChanged();
                }
                break;
        }

    }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<List<Movie>> loader) {

    }


    /**
     * startLoadingProgress
     */
    protected void startLoadingProgress() {
        mLoadingDialog.show();
    }
    /**
     * stopLoadingProgress
     */
    protected void stopLoadingProgress() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

}
