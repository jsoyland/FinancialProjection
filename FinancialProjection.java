package com.jesse.FinancialProjection;

import java.util.Calendar;

public class FinancialProjection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar calStartDate;
		Calendar calEndDate;
		
		String strEventsFile = new String();
		String strStartDate = new String();
		String strEndDate = new String();
		String[] strStartSplit;
		String[] strEndSplit;

		strEventsFile=args[0];
		strStartDate=args[1];
		strEndDate=args[2];
		
		strStartSplit=strStartDate.split("-");
		strEndSplit=strEndDate.split("-");
		
		calStartDate = Calendar.getInstance();
		calStartDate.set(Integer.valueOf(strStartSplit[0]), Integer.valueOf(strStartSplit[1])-1, Integer.valueOf(strStartSplit[2]), 0, 0, 0); 
		calEndDate = Calendar.getInstance();
		calEndDate.set(Integer.valueOf(strEndSplit[0]), Integer.valueOf(strEndSplit[1])-1, Integer.valueOf(strEndSplit[2]), 0, 0, 0); 
		
		FinancialEvents feProjection=new FinancialEvents();
		feProjection.read(strEventsFile);
		feProjection.predict(calStartDate, calEndDate);
		

		//feEvents.print();
	}

}
