package ${package}.common;
import org.apache.commons.lang3.StringUtils;

/**
 * @author fenglei
 */
public class SensitiveOffUtil {

    /**
     * 说明：  [手机号码] 前三位，后四位，其他隐藏<例子:138******1234>
     * @param num 手机号码
     * @return
     * @author fenglei
     * @date  2018年7月1日 下午5:47:06
     */
    public static String mobilePhone(String num) {
        if (StringUtils.isBlank(num)) {
            return null;
        }else if(num.length() != 11){
            return num;
        }
        return StringUtils.left(num, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*"), "***"));
    }

    /**
     * 说明：  [证件号] 显示第一位和最后一位，其他隐藏。<例子：1************3>
     * @param id 证件号码
     * @return
     * @author fenglei
     * @date  2016年7月1日 下午6:20:55
     */
    public static String idCardNum(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }else if(id.length() <= 1){
            return id;
        }
        String src = "";
        for (int i = 0; i < id.length()-2 ; i++) {
            src += "*";
        }
        id = id.substring(0, 1) + src + id.substring(id.length() - 1);
        return id;
    }

}
