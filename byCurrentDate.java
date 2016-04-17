package com.jesse.FinancialProjection;

@SuppressWarnings("unchecked")
public class byCurrentDate implements java.util.Comparator {
	public int compare(Object arg0, Object arg1) {
		FinancialEvent a = (FinancialEvent) arg0;
		FinancialEvent b = (FinancialEvent) arg1;
		 if (a.getCurrentOccurance().getTime().before(b.getCurrentOccurance().getTime())){
			 return -1;
		 }
		 if (a.getCurrentOccurance().getTime().equals(b.getCurrentOccurance().getTime())){
			 return 0;
		 }
		 if (a.getCurrentOccurance().getTime().after(b.getCurrentOccurance().getTime())){
			 return 1;
		 }
		 return 0;

	}
} 