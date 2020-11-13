package com.spring.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	//현재 날짜 가져오는 메소드 yyyy-MM-dd
	public static String getNowDate() {
		//현재 시간 세팅
		Calendar cal = Calendar.getInstance();
		//시간 데이터의 포맷을 정해주는 객체
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//현재 날짜
		String toDate = dateFormat.format(cal.getTime());
		
		return toDate;
	}
	
	//한달 전 날짜 가져오는 메소드 yyyy-MM-dd
	public static String getBeforeDate() {
		//현재 시간 세팅
		Calendar cal = Calendar.getInstance();
		//시간 데이터의 포맷을 정해주는 객체
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//한달 전 날짜
		cal.add(Calendar.MONTH, -1);
		String fromDate = dateFormat.format(cal.getTime());
		
		return fromDate;
	}
}
