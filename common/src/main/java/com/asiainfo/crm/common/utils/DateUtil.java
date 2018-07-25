package com.asiainfo.crm.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 受理时间工具类
 * 
 * @author
 * 
 */
public class DateUtil {

	public final static Integer YEAR = new Integer(1);
	public final static Integer MONTH = new Integer(2);
	public final static Integer DATE = new Integer(3);
	public final static Integer HOUR = new Integer(4);
	public final static Integer MINUTES = new Integer(5);
	public final static Integer SECONDS = new Integer(6);
	
	/**
	 * 格式化为23:59:59
	 */
	public final static String MIDNIGHT=new String("23:59:59");
	/**
	 * 格式化为0:0:0
	 */
	public final static String ZERO_TIME=new String("0:0:0");
	

	/**
	 * 根据维度值dimension截取时间串。 例如trunctDate(2013-9-9 11:11:11,MONTH)，则返回2013-9-1
	 * 0:0:0 trunctDate(2013-9-9 11:11:11,DATE)，则返回2013-9-9 0:0:0
	 * 
	 * @param date
	 * @param dimension
	 * @return
	 */
	public static Date trunctDate(Date date, Integer dimension) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (dimension == YEAR) {
			c.set(c.get(Calendar.YEAR), 1, 1, 0, 0, 0);
		} else if (dimension == MONTH) {
			c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1, 0, 0, 0);
		} else if (dimension == DATE) {
			c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
					.get(Calendar.DATE), 0, 0, 0);
		}
		return c.getTime();
	}
	
	/**
	 * 相对baseDate的次月一号0:0:0
	 * @return
	 */
	public static Date nextMonthFirstDay(Date baseDate){
		Calendar c=Calendar.getInstance();
		c.setTime(baseDate);
		c.add(Calendar.MONTH, 1);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1, 0, 0, 0);
		return c.getTime();
	}
	
	/**
	 * baseDate的当月一号0:0:0
	 * @param baseDate
	 * @return
	 */
	public static Date monthFirstDay(Date baseDate){
		return trunctDate(baseDate, DateUtil.MONTH);
	}
	
	
	/**
	 * 相对baseDate的当月最后一天23:59:59
	 * @return
	 */
	public static Date lastDayOfMonthDate(Date baseDate){
		Calendar c=Calendar.getInstance();
		c.setTime(baseDate);
		int lastDay=c.getActualMaximum(Calendar.DATE);
		c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),lastDay, 23, 59,59);
		return c.getTime();
	}
	
	/**
	 * 通过时间维度，变更时间
	 * @param baseDt
	 * @param dimension
	 * @param value 相对变更时长
	 * @return
	 */
	public static Date changeDateByDim(Date baseDt,Integer dimension,int value){
		Calendar c=Calendar.getInstance();
		c.setTime(baseDt);
		if (dimension == YEAR) {
			c.add(Calendar.YEAR, value);
		} else if (dimension == MONTH) {
			c.add(Calendar.MONTH, value);
		} else if (dimension == DATE) {
			c.add(Calendar.DATE, value);
		}
		return c.getTime();
	}
	
	/**
	 * 格式化时间为yyyy:MM:dd 0:0:0或者yyyy:MM:dd 23:59:59
	 * @param baseDt
	 * @param timePattern
	 * @return
	 */
	public static Date formateDateByPattern(Date baseDt,String timePattern){
		Calendar c=Calendar.getInstance();
		c.setTime(baseDt);
		if(timePattern.equalsIgnoreCase(MIDNIGHT)){
			c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 23, 59, 59);
		}else if(timePattern.equalsIgnoreCase(ZERO_TIME)){
			c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
		}
		return c.getTime();
	}
	
	/**
	 * 获取当前日期的次日凌晨
	 * @param baseDt
	 * @return
	 */
	public static Date getNextDateZeroTime(Date baseDt) {
		Date nextDt = null;
		nextDt = DateUtil.trunctDate(baseDt, DateUtil.DATE);
		nextDt = DateUtil.changeDateByDim(nextDt, DateUtil.DATE, 1);
		return nextDt;
	}
}
