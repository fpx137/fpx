package com.ruoyi.job.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间工具�?,自定义标签函数用
 *
 * @author wuhh、fll
 *
 */
public class DateUtils {
	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
	public static final String YMDHMS_PATTERN = "yyyy-MM-dd HH:mm:ss";// 年月日时分秒模式字符串
	public static void main(String[] args) {

	}

	/**
	 * 是否是周末
	 * @return
	 */
	public static boolean isWeekend(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
	}

	//判断当日期是否是在startDate和endDate日期范围内，是返回true,否返回false
	public static boolean betweenCurDate(Date startDate, Date endDate) {
		if(startDate == null || endDate == null){
			return false;
		}
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		Calendar currentCal = Calendar.getInstance();
		currentCal.set(Calendar.HOUR,0);
		currentCal.set(Calendar.MINUTE,0);
		currentCal.set(Calendar.SECOND,0);
		currentCal.set(Calendar.MILLISECOND,0);
		return currentCal.compareTo(startCal) >= 0 && currentCal.compareTo(endCal) <=0;
	}

	public static Date getAfterDate(Date date,int afterSecond){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND,afterSecond);
		return calendar.getTime();
	}

	public static Date getAfterDate(Date date,int calendarField,int after){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendarField,after);
		return calendar.getTime();
	}

	public static Date getAfterDate(int afterSecond){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND,afterSecond);
		return calendar.getTime();
	}

	public static Date getAfterDate(int calendarField,int after){
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendarField,after);
		return calendar.getTime();
	}

	public static String longToString(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss SSS");
		return format.format(cal.getTime());
	}

	public static Date getCurentDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	public static String dateToStringyyyyMMddHHmmssSSS(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss SSS");
		String curdate = format.format(date);
		// LOG.info((String)request.getSession().getAttribute("singlephone")+"==IndexAction Start:"+curdate);
		return curdate;
	}

	public static String dateToStringyyyyMMddHHmmss(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String curdate = format.format(date);
		return curdate;
	}

	public static String dateToString(Date date,String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String curdate = format.format(date);
		return curdate;
	}

	public static String getTimeyyyyMMdd() {
		Date nowDate = new Date();
		// yyyy-MM-dd HH:mm:ss
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String curdate = format.format(nowDate);
		return curdate;
	}

	public static String getCurdateToStringyyyyMMddHHmmssSSS() {
		Date date1 = new Date();
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss SSS");
		String curdate = format.format(date1);
		// LOG.info((String)request.getSession().getAttribute("singlephone")+"==IndexAction Start:"+curdate);
		return curdate;
	}

	public static String getDiffDateToDate(Date date,
                                           Date toDate) {
		long second = date.getTime() - toDate.getTime();
		return secondToOutput(second);
	}

	/**
	 * get day of week such as (1,2...7)
	 *
	 * @param date
	 * @return
	 */
	public static int dayOfWeek(Date date) {
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(date);
		int x = aCalendar.get(Calendar.DAY_OF_WEEK);
		switch (x) {
		case Calendar.MONDAY:
			x = 1;
			break;
		case Calendar.TUESDAY:
			x = 2;
			break;
		case Calendar.WEDNESDAY:
			x = 3;
			break;
		case Calendar.THURSDAY:
			x = 4;
			break;
		case Calendar.FRIDAY:
			x = 5;
			break;
		case Calendar.SATURDAY:
			x = 6;
			break;
		case Calendar.SUNDAY:
			x = 7;
			break;
		}
		return x;
	}

	/**
	 * 把秒数，转化成可输出的时间字符串，格式如下 61 负1分1秒 0 0秒 59 59秒 61 1分1秒 7836 2小时10分36秒 90345
	 * 1天1小时5分45秒 9990245 115天15小时4分5秒
	 */
	public static String secondToOutput(long second) {
		String result = "";
		if (second < 0) {
			second = Math.abs(second);
			result += "负";
		}
		if (second < 60) {
			result += second + "秒";
		} else if (second < 3600) {
			long minute = second / 60;
			long secon = second % 60;
			result += minute + "分";
			if (secon != 0) {
				result += secon + "秒";
			}
		} else if (second < 86400) {
			long hour = second / 3600;
			long seconded = second % 3600;
			long minute = seconded / 60;
			long secon = seconded % 60;
			result += hour + "小时";
			if (minute != 0) {
				result += minute + "分";
			}
			if (secon != 0) {
				result += secon + "秒";
			}
		} else {
			long day = second / 86400;
			long seconded = second % 86400;
			long hour = seconded / 3600;
			seconded = seconded % 3600;
			long minute = seconded / 60;
			long secon = seconded % 60;
			result += day + "天";
			if (hour != 0) {
				result += hour + "小时";
			}
			if (minute != 0) {
				result += minute + "分";
			}
			if (secon != 0) {
				result += secon + "秒";
			}
		}

		return second + "    " + result;
	}

	public static String timestampToGTM(long timestamp){
        SimpleDateFormat format=new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return format.format(new Date(timestamp));
    }

	public static long GTMToTimestamp(String GTMDate){
		try {
			SimpleDateFormat format=new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
	        Date date = format.parse(GTMDate);
	        return date.getTime();
		} catch (Exception e) {
			logger.error("GTMToTimestamp error,GTMDate="+GTMDate);
			return -1;
		}
    }


	public static String getCurDateString(){
		return getCurDateString(YMDHMS_PATTERN);
	}

	public static String getCurDateString(String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}

	public static String getYesterday(String pattern){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(cal.getTime());
	}

	public static Date getYesterday(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		return cal.getTime();
	}

	public static String getDateString(Date date){
		return getDateString(date,null);
	}

	public static String getDateString(Date date,String pattern){
		if(date==null){
			return null;
		}
		if(StringUtils.isBlank(pattern)){
			pattern = YMDHMS_PATTERN;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String curdate = format.format(date);
		return curdate;
	}

	public static Date parseDate(String dateStr,String pattern){
		try{
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.parse(dateStr);
		}catch (Exception e){
			logger.error("parseDate error,dateStr="+dateStr+",pattern="+pattern,e);
			return null;
		}
	}

	public static Date getDate(long time){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		return cal.getTime();
	}

	public static Date getDate(String date,String pattern){
		try{
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			Date dateD = format.parse(date);
			return dateD;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}



	/**
	 * 间隔时间(分钟)
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDiffMinute(Date date1,Date date2){
		long dateL = date1.getTime() - date2.getTime();
		long res = dateL/1000/60;
		return res;
	}

	/**
	 * 间隔时间(秒)
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDiffSecond(Date date1,Date date2){
		long dateL = date1.getTime() - date2.getTime();
		long res = dateL/1000;
		return res;
	}

	/**
	 * 比日期
	 * @param date1
	 * @param date2
	 * @return date1小于date2:-1,date1==date2:0,date1大于date2:1
	 */
	public static int compareDate(Date date1,Date date2){
		Calendar date1Cal = Calendar.getInstance();
		date1Cal.setTime(date1);

		Calendar date2Cal = Calendar.getInstance();
		date2Cal.setTime(date2);

		int year1 = date1Cal.get(Calendar.YEAR);
		int year2 = date2Cal.get(Calendar.YEAR);
		if(year1 > year2){
			return 1;
		}
		if(year1 < year2){
			return -1;
		}

		int day1 = date1Cal.get(Calendar.DAY_OF_YEAR);
		int day2 = date2Cal.get(Calendar.DAY_OF_YEAR);
		if(day1 > day2){
			return 1;
		}
		if(day1 < day2){
			return -1;
		}
		return 0;
	}

	/**
	 * 根据生日计算当前周岁数,当月的算足岁
	 */
	public static int getCurrentAgeMonth(Date birthday) {
		Calendar birthdayCal = Calendar.getInstance();
		birthdayCal.setTime(birthday);
		birthdayCal.add(Calendar.MONTH,-2);
		birthdayCal.add(Calendar.DATE,-1);
		return getCurrentAge(birthdayCal.getTime());
	}

	/**
	 * 根据生日计算当前周岁数
	 */
	public static int getCurrentAge(Date birthday) {
		// 当前时间
		Calendar curr = Calendar.getInstance();
		// 生日
		Calendar born = Calendar.getInstance();
		born.setTime(birthday);
		// 年龄 = 当前年 - 出生年
		int age = curr.get(Calendar.YEAR) - born.get(Calendar.YEAR);
		if (age <= 0) {
			return 0;
		}
		// 如果当前月份小于出生月份: age-1
		// 如果当前月份等于出生月份, 且当前日小于出生日: age-1
		int currMonth = curr.get(Calendar.MONTH);
		int currDay = curr.get(Calendar.DAY_OF_MONTH);
		int bornMonth = born.get(Calendar.MONTH);
		int bornDay = born.get(Calendar.DAY_OF_MONTH);
		if ((currMonth < bornMonth) || (currMonth == bornMonth && currDay <= bornDay)) {
			age--;
		}
		return age < 0 ? 0 : age;
	}

	/**
	 * 对比date和日期
	 * @param date
	 * @param curdate 0：今天，-1昨天，1明天，类推
	 * @return 相等true,不相等false
	 */
	public static boolean compareDate(Date date,int curdate){
		Calendar cal = Calendar.getInstance();
		if(curdate != 0){
			cal.add(Calendar.DATE,curdate);
		}
		String curDate = getDateString(cal.getTime(),"yyyyMMdd");
		String dateStr = getDateString(date,"yyyyMMdd");
		if(curDate.equals(dateStr)){
			return true;
		}
		return false;
	}


	public static Date add(Date date,int field, int amount){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field,amount);
		return cal.getTime();
	}


	//获取区间的月份
	public static List<Date> getMonths(Date startDateD, Date endDateD){
		if(startDateD == null){
			return null;
		}
		List<Date> resList = new ArrayList<>();
		if(endDateD == null){
			resList.add(startDateD);
			return resList;
		}
		if(startDateD.getTime() > endDateD.getTime()){
			return null;
		}

		String startDate = DateUtils.getDateString(startDateD,"yyyy-MM");
		String endDate = DateUtils.getDateString(endDateD,"yyyy-MM");
		if(startDate.equals(endDate)){
			resList.add(startDateD);
			return resList;
		}

		Date startDateDate = startDateD;
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDateDate);
		while (true){
			resList.add(startCal.getTime());
			startCal.add(Calendar.MONTH,1);
			String endDateNew = DateUtils.getDateString(startCal.getTime(),"yyyy-MM");
			if(endDateNew.equals(endDate)){
				resList.add(startCal.getTime());
				return resList;
			}
		}
	}

	//获取区间的日期
	public static List<Date> getDates(Date startDateD,Date endDateD){
		if(startDateD == null){
			return null;
		}
		List<Date> resList = new ArrayList<>();
		if(endDateD == null){
			resList.add(startDateD);
			return resList;
		}
		if(startDateD.getTime() > endDateD.getTime()){
			return null;
		}

		String startDate = DateUtils.getDateString(startDateD,"yyyy-MM-dd");
		String endDate = DateUtils.getDateString(endDateD,"yyyy-MM-dd");
		if(startDate.equals(endDate)){
			resList.add(startDateD);
			return resList;
		}

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDateD);
		while (true){
			resList.add(startCal.getTime());
			startCal.add(Calendar.DATE,1);
			String endDateNew = DateUtils.getDateString(startCal.getTime(),"yyyy-MM-dd");
			if(endDateNew.equals(endDate)){
				resList.add(startCal.getTime());
				return resList;
			}
		}
	}
}
