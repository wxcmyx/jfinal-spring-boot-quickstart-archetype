package ${package}.common;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: 86185
 * Date: 2019/9/13
 * Time: 22:12
 * Description: No Description
 */
@Data
public class ResponInfo <T> extends HashMap {

    public ResponInfo() {
    }

    public ResponInfo(int code, String msg, T data,int count) {
        this.put("code",code);
        this.put("msg",msg);
        this.put("data",data);
        this.put("count",count);
    }

    /**
     * @param count 设置数量
     * @return
     */
    public ResponInfo<T> setCount(int count) {
        this.put("count",count);
        return this;
    }

    /**
     * @param msg 设置显示信息
     * @return
     */
    public ResponInfo<T> setMsg(String msg) {
        this.put("msg",msg);
        return this;
    }
    /**
     * @param data
     * @param <T>
     * @return 0成功，1失败
     */
    public static <T> ResponInfo makeSuc(T data){
        ResponInfo responInfo = new ResponInfo();
        responInfo.setCode(0);
        responInfo.setMsg("请求成功");
        responInfo.setData(data);
        return responInfo;
    }

    private ResponInfo<T> setData(T data) {
        this.put("data",data);
        return this;
    }

    private ResponInfo<T> setCode(int code) {
        this.put("code",code);
        return this;
    }

    /**
     * @param msg
     * @return 0成功，1失败
     */
    public static ResponInfo makeFail(String msg){
        ResponInfo responInfo = new ResponInfo();
        responInfo.setCode(1);
        responInfo.setMsg("请求失败");
        if (null != msg){
            responInfo.setMsg(msg);
        }
        return responInfo;
    }

    public ResponInfo putExtra(Object key,Object value){
        this.put(key,value);
        return this;
    }

}
