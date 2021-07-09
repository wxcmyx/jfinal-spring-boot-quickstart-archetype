package com.wixct.pachong.common;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {
	
	/**
	 * 功能描述：格式化日期
	 * 
	 * @param dateStr
	 *            String 字符型日期
	 * @param format
	 *            String 格式
	 * @return Date 日期
	 */
	public static Date parseDate(String dateStr, String format) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			return  dateFormat.parse(dateStr);
		} catch (Exception e) {
			return null ;
		}
	}

	/**
	 * 功能描述：格式化日期
	 * 
	 * @param dateStr
	 *            String 字符型日期：YYYY/MM/DD 格式
	 * @return Date
	 */
	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy/MM/dd");
	}
	/**
	 * 功能描述：格式化日期
	 *
	 * @param dateStr
	 *            String 字符型日期：YYYY-MM-DD 格式
	 * @return Date
	 */
	public static Date parseDateWithCrossbar(String dateStr) {
		return parseDate(dateStr, "yyyy-MM-dd");
	}

	/**
	 * 功能描述：格式化输出日期
	 * 
	 * @param date
	 *            Date 日期
	 * @param format
	 *            String 格式
	 * @return 返回字符型日期
	 */
	public static String formatDate(Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				DateFormat dateFormat = new SimpleDateFormat(format);
				result = dateFormat.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 功能描述：
	 * 
	 * @param date
	 *            Date 日期
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDate(date, "yyyy/MM/dd");
	}

	/**
	 * 功能描述：返回年份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回年份
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 功能描述：返回月份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能描述：返回日份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能描述：返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	/**
	 * 功能描述：加上一小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回Date
	 * @throws ParseException 
	 */
	public static Date addOneHour(Date date)  {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE,60);
		date = calendar.getTime();
		try {
			date=formater.parse(formater.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	
	//加一天
	public static Date addOneDay(Date date)  {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,1);
		date = calendar.getTime();
		try {
			date=formater.parse(formater.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	
	 /** 
     * 根据开始时间和结束时间返回时间段内的时间集合 
     *  
     * @param beginDate 
     * @param endDate 
     * @return List 
     */  
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {  
        List<Date> lDate = new ArrayList<Date>();  
        	 lDate.add(beginDate);// 把开始时间加入集合  
        Calendar cal = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间  
        cal.setTime(beginDate);  
        boolean bContinue = true;  
        while (bContinue) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
            cal.add(Calendar.DAY_OF_MONTH, 1);  
            // 测试此日期是否在指定日期之后  
            if (endDate.after(cal.getTime())) {  
                lDate.add(cal.getTime());  
            } else {  
                break;  
            }  
        } 
        	lDate.add(endDate);// 把结束时间加入集合  
        	
        return lDate;  
    }  
    
    
    public static List<Date> getDatesBetweenTwoDate2(Date beginDate, Date endDate) {  
        List<Date> lDate = new ArrayList<Date>();  
        lDate.add(beginDate);// 把开始时间加入集合  
        Calendar cal = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间  
        cal.setTime(beginDate);  
        boolean bContinue = true;  
        while (bContinue) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
            cal.add(Calendar.DAY_OF_MONTH, 1);  
            // 测试此日期是否在指定日期之后  
            if (endDate.after(cal.getTime())) {  
                lDate.add(cal.getTime());  
            } else {  
                break;  
            }  
        }  
        //lDate.add(endDate);// 把结束时间加入集合  
        return lDate;  
    } 
	/**
	 * 功能描述：返回分钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 功能描述：返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * 功能描述：返回字符型日期
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型日期 yyyy/MM/dd 格式
	 */
	public static String getDate(Date date) {
		return formatDate(date, "yyyy/MM/dd");
	}

	/**
	 * 功能描述：返回字符型时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型时间 HH:mm:ss 格式
	 */
	public static String getTime(Date date) {
		return formatDate(date, "HH:mm:ss");
	}

	/**
	 * 功能描述：返回字符型日期时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型日期时间 yyyy/MM/dd HH:mm:ss 格式
	 */
	public static String getDateTime(Date date) {
		return formatDate(date, "yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * 功能描述：日期相加
	 * 
	 * @param date
	 *            Date 日期
	 * @param day
	 *            int 天数
	 * @return 返回相加后的日期
	 */
	public static Date addDate(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		long millis = getMillis(date) + ((long) day) * 24 * 3600 * 1000;
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	/**
	 * 功能描述：日期相减
	 * 
	 * @param date
	 *            Date 日期
	 * @param date1
	 *            Date 日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	/**
	 * 功能描述：取得指定月份的第一天
	 * 
	 * @param strdate
	 *            String 字符型日期
	 * @return String yyyy-MM-dd 格式
	 */
	public static String getMonthBegin(String strdate) {
		Date date = parseDate(strdate);
		return formatDate(date, "yyyy-MM") + "-01";
	}

	/**
	 * 功能描述：取得指定月份的最后一天
	 * 
	 * @param strdate
	 *            String 字符型日期
	 * @return String 日期字符串 yyyy-MM-dd格式
	 */
	public static String getMonthEnd(String strdate) {
		Date date = parseDate(getMonthBegin(strdate));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDate(calendar.getTime());
	}
	
	/**
	 * 获得一天的开始时间
	 * @param date
	 * @return
	 */
	public static Date getBeginOfDay(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date result = date;
		try {
			result = formater.parse(formater.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获得一天的结束时间
	 * @param date
	 * @return
	 */
	public static Date getEndOfDay(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date result = date;
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, 1);
			result = formater.parse(formater.format(cal.getTime()));
			result.setTime(result.getTime() - 1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 获得本月的结束时间
	 * @param src
	 * @return Long
	 */
	public static Long converter(String  src) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(StringUtils.isNotBlank(src)){
			try {
				return formater.parse(src).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	
	/**
	 * 获得本月的开始时间
	 * @param date
	 * @return
	 */
	public static Date getBeginOfMonth(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date result = date;
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			result = formater.parse(formater.format(cal.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获得本月的结束时间
	 * @param date
	 * @return
	 */
	public static Date getEndOfMonth(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date result = date;
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.add(Calendar.MONTH, 1);
			result = formater.parse(formater.format(cal.getTime()));
			result.setTime(result.getTime() - 1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获得本年的开始时间
	 * @param date
	 * @return
	 */
	public static Date getBeginOfYear(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date result = date;
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			cal.set(Calendar.DAY_OF_YEAR, 1);
			result = formater.parse(formater.format(cal.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获得本年的结束时间
	 * @param date
	 * @return
	 */
	public static Date getEndOfYear(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date result = date;
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			cal.set(Calendar.DAY_OF_YEAR, 1);
			cal.add(Calendar.YEAR, 1);
			result = formater.parse(formater.format(cal.getTime()));
			result.setTime(result.getTime() - 1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	 
	/**
	 * 根据一个日期，返回其以星期一为一周的第一天的一周的第一天日期对象，精确到毫秒即yyyy-MM-dd 00:00:00.000
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeginOfMondayFirstWeek(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return new Date(cal.getTimeInMillis());
	}
	
	/**
	 * 根据一个日期，返回其以星期一为一周的第一天的一周的最后一天日期对象，精确到毫秒即yyyy-MM-dd 23:59:59.999
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndOfMondayFirstWeek(Date date) {
		
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);

		return new Date(cal.getTimeInMillis());
	}
	
	/**
	 * 通过一个给定的年获取这一年的开始时间，精确到毫秒，yyyy-01-01 00:00:00.000
	 * 
	 * @param year
	 * @return
	 */
	public static Date getBeginOfYear(int year){
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return new Date(cal.getTimeInMillis());
	}
	
	/**
	 * 通过一个给定的年获取这一年的结束时间，精确到毫秒，yyyy-12-31 23:59:59.999
	 * 
	 * @param year
	 * @return
	 */
	public static Date getEndOfYear(int year){
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);

		return new Date(cal.getTimeInMillis());
	}
	
	/**
	 * 通过一个给定的年和月（从0～11）获取这一月的开始时间，精确到毫秒，yyyy-MM-01 00:00:00.000
	 * 
	 * @param year
	 * @return
	 */
	public static Date getBeginOfMonth(int year, int Month){
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, Month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return new Date(cal.getTimeInMillis());
	}
	
	/**
	 * 通过一个给定的年和月（从0～11）获取这一月的结束时间，精确到毫秒，yyyy-MM-31 23:59:59.999
	 * 
	 * @param year
	 * @return
	 */
	public static Date getEndOfMonth(int year, int Month){
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, Month + 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return new Date(cal.getTimeInMillis() - 1);
	}
	
	/**
	 * 获得几天前的开始时间
	 * @param date
	 * @return
	 */
	public static Date getDayBefore(Date date, int beforeDay) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date result = date;
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, -beforeDay);
			result = formater.parse(formater.format(cal.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获得几天后的开始时间
	 * @param date
	 * @return
	 */
	public static Date getDayAfter(Date date, int afterDay) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date result = date;
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, afterDay);
			result = formater.parse(formater.format(cal.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获得几分钟前的开始时间
	 * @param date
	 * @return
	 */
	public static Date getDayBeforeMinute(Date date, int beforeMinute) {
		long time = date.getTime() - beforeMinute * 60 * 1000;
		Date result = new Date(time);
		return result;
	}
	
	
	/**
	 * 获得几秒钟前的开始时间
	 * @param date
	 * @return
	 */
	public static Date getDayBeforeSeconds(Date date, int beforeSecond) {
		long time = date.getTime() - beforeSecond * 1000;
		Date result = new Date(time);
		return result;
	}
	
	public static Date get19700101Date(Date date){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(1970, 0, 1);
		return calendar.getTime();
	}
	
	public static Time get19700101Time(Time time){

		return new Time(get19700101Date(time).getTime());
	}
	
	public static Date get19700101NowDate(){

		return get19700101Date(new Date());
	}
	
	public static Time get19700101NowTime(){
		
		return new Time(get19700101NowDate().getTime());
	}
	
	public static Time get19700101BeginTime(){
		
		return new Time(getBeginOfDay(get19700101NowDate()).getTime());
	}
	
	public static Time get19700101EndTime(){
		
		return new Time(getEndOfDay(get19700101NowDate()).getTime());
	}
	
	public static void main(String[] args) throws ParseException{
		System.out.println(getBeginOfDay(new Date()).getTime());
	}

    protected static int absoluteDay(Calendar cal, boolean use1904windowing) {
		return 0;
    }
}
