package com.jesse.FinancialProjection;

import java.util.Calendar;

public class FinancialEvent {
	String strName;
	double dAmount;
	Calendar calStartDate;
	String strLabel;
	Integer iFrequencyID;		// 1 = weekly, 2 = monthly
	Integer iFrequencyCount;
	Calendar m_CurrentOccurance;
	Integer iDebitInd;

	FinancialEvent(){
		strName = "";
		dAmount = 0.0;
		iDebitInd=0;
		calStartDate = Calendar.getInstance();
		calStartDate.set(1999, 0, 1, 0, 0, 0); 
		strLabel = "";
		iFrequencyID = 0;
		iFrequencyCount = 0;
		m_CurrentOccurance = calStartDate;
	}

	FinancialEvent(String newName, Integer newDebitInd, double newAmount, Calendar newStartDate, String newLabel, Integer newFrequencyID, Integer newFrequencyCount){
		strName = newName;
		iDebitInd = newDebitInd;
		dAmount = newAmount;
		calStartDate = newStartDate; 
		strLabel = newLabel;
		iFrequencyID = newFrequencyID;
		iFrequencyCount = newFrequencyCount;
		m_CurrentOccurance = calStartDate;
	}

	
	void print(){
		
		String DATE_FORMAT = "MM-dd-yyyy";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
/*		
		System.out.println("strName: \t" + strName);
		System.out.println("dAmount: \t" + dAmount);
		System.out.println("calStartDate: \t" + sdf.format(calStartDate.getTime()));
		System.out.println("strLabel: \t" + strLabel);
		System.out.println("iFrequencyID: \t" + iFrequencyID);
		System.out.println("iFrequencyCount: \t" + iFrequencyCount);
*/
		if (iDebitInd==1)
			System.out.println(sdf.format(calStartDate.getTime()) + "\t\t" + strName + "\t=-" + strLabel);
		else
			System.out.println(sdf.format(calStartDate.getTime()) + "\t\t" + strName + "\t=" + strLabel);
	}
	
	public static void main(String[] args){
		FinancialEvent fe = new FinancialEvent();
		fe.print();
	}
	
	Calendar getNextOccurance(){
		if (iFrequencyID == 1) { // Weekly 
			m_CurrentOccurance.add(Calendar.DATE, iFrequencyCount * 7);
		}
		if (iFrequencyID == 2) { // Monthly 
			m_CurrentOccurance.add(Calendar.MONTH, iFrequencyCount);
		}
		return m_CurrentOccurance;
	}
	
	Calendar getCurrentOccurance(){
		return m_CurrentOccurance;
	}
	
	String getName(){
		return strName;
	}
	
	Double getAmount(){
		return dAmount;
	}
	
	Integer getDebitInd(){
		return iDebitInd;
	}
	
	String getLabel(){
		return strLabel;
	}
	
}
