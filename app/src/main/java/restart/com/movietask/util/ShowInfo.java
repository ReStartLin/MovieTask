package restart.com.movietask.util;

/**
 * Created by Administrator on 2018/5/12.
 */

import java.util.List;

import restart.com.movietask.bean.Cast;
import restart.com.movietask.bean.Director;

/**
 * 获取信息 整理输出
 */
public class ShowInfo {

    /**
     * 获取主演
     * @param casts
     * @return
     */
    public static String getCat(List<Cast> casts) {
        StringBuilder stringBuilder = new StringBuilder("主演:");
        for (Cast cast : casts) {
            stringBuilder.append(" ").append(cast.getName());
        }
        return stringBuilder.toString();
    }



    /**
     * 获取导演
     * @param directors
     * @return
     */
    public static String getDirector(List<Director> directors) {
        StringBuilder stringBuilder = new StringBuilder("导演:");
        for (Director director : directors) {
            stringBuilder.append(" ").append(director.getName());
        }
        return stringBuilder.toString();
    }

    /**
     * 获取类型
     * @param types
     * @return
     */
    public static String getTypes(List<String> types) {
        StringBuilder stringBuilder = new StringBuilder("类型:");
        for (String type : types) {
            stringBuilder.append(type).append("/");

        }
        return stringBuilder.toString();
    }

}
