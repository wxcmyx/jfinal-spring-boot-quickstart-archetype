package ${package}.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author fenglei
 */
public class IsValidDate {

//    /**
//     * 验证输入的日期是否合法
//     * @param expDate
//     * @return
//     */
//    public static boolean isValidDate(String expDate) {
//        int year = Integer.parseInt(expDate.substring(0, 4));
//        int month = Integer.parseInt(expDate.substring(5, 7));
//        int day = Integer.parseInt(expDate.substring(8, 10));
//        if (month > 12 || month < 1) {
//            return false;
//        }
//
//        int[] monthLengths = new int[] { 0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
//
//        if (isLeapYear(year)) {
//            monthLengths[2] = 29;
//        } else {
//            monthLengths[2] = 28;
//        }
//
//        int monthLength = monthLengths[month];
//        if (day < 1 || day > monthLength) {
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 是否是闰年
//     * */
//    private static boolean isLeapYear(int year) {
//        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
//    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }
}
