package restart.com.movietask.biz;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import restart.com.movietask.bean.Json2Movie;
import restart.com.movietask.bean.Movie;

/**
 * Created by Administrator on 2018/5/12.
 */

public class MyHttpBiz {
    private static MyHttpBiz myHttpBiz;
    public static final String BASE_URL = "http://www.imooc.com/api/movie";

    private MyHttpBiz() {
    }

    public static MyHttpBiz getInstance() {
        if (myHttpBiz == null) {
            myHttpBiz = new MyHttpBiz();
        }
        return myHttpBiz;
    }
    public List<Movie> getAllMovie() {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                char[] buff = new char[1024];
                final StringBuilder stringBuilder = new StringBuilder();
                int len = -1;
                while ((len = inputStreamReader.read(buff) ) != -1) {
                    stringBuilder.append(buff, 0, len);
                }

                Gson gson = new Gson();
                Json2Movie json2Movie = gson.fromJson(stringBuilder.toString(), Json2Movie.class);
                if (json2Movie.getCount() > 0) {
                    return json2Movie.getMovies();
                }
                return null;

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

