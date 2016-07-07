import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class MagicMonth {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Calendar calStartDate;
		Calendar calEndDate;
		Calendar calPayday;
		
		String strEventsFile = new String();
		String strStartDate = new String();
		String strEndDate = new String();
		String strPayday = new String();
		String[] strStartSplit;
		String[] strEndSplit;
		String[] strPaydaySplit;
		
		strEventsFile=args[0];
		strPayday=args[1];
		strStartDate=args[2];
		strEndDate=args[3];
		
		strStartSplit=strStartDate.split("-");
		strEndSplit=strEndDate.split("-");
		strPaydaySplit = strPayday.split("-");
		
		calStartDate = Calendar.getInstance();
		calStartDate.set(Integer.valueOf(strStartSplit[0]), Integer.valueOf(strStartSplit[1])-1, Integer.valueOf(strStartSplit[2]), 0, 0, 0); 
		calEndDate = Calendar.getInstance();
		calEndDate.set(Integer.valueOf(strEndSplit[0]), Integer.valueOf(strEndSplit[1])-1, Integer.valueOf(strEndSplit[2]), 0, 0, 0); 
		calPayday = Calendar.getInstance();
		calPayday.set(Integer.valueOf(strPaydaySplit[0]), Integer.valueOf(strPaydaySplit[1])-1, Integer.valueOf(strPaydaySplit[2]), 0, 0, 0);
		
		
		FinancialEvent feEvent = new FinancialEvent("Paycheck", 0, 0.0, calPayday, "Payday", 1, 2);
		
		
		Calendar newOccurance;
		FinancialEvent feNew;
		ArrayList<FinancialEvent> PredictionList=new ArrayList<FinancialEvent>();
		String DATE_FORMAT = "MM-dd-yyyy";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);

		System.out.println("Start: " + sdf.format(calStartDate.getTime()));
		System.out.println("End: " + sdf.format(calEndDate.getTime()));
	
		
		while (feEvent.getNextOccurance().before(calStartDate));  // Advance current occurrence until start date
		while (feEvent.getCurrentOccurance().before(calEndDate)){
			newOccurance = Calendar.getInstance();
			newOccurance.setTime(feEvent.getCurrentOccurance().getTime());
				
			feNew = new FinancialEvent(feEvent.getName(), feEvent.getDebitInd(), feEvent.getAmount(), newOccurance, feEvent.getLabel(), 0, 0);
			PredictionList.add(feNew);
			feEvent.getNextOccurance();

			}

		Collections.sort(PredictionList);
	
		int iCurMonth = 0;
		int iLastMonth = 0;
		int iMonthCount=0;
		
		for (int i = 0; i < PredictionList.size(); i++){
			iCurMonth=PredictionList.get(i).getCurrentOccurance().get(2);
			if (iLastMonth==iCurMonth)
				iMonthCount++;
			else
				iMonthCount=0;

			iLastMonth=iCurMonth;
			
			if (iMonthCount==2)
				//System.out.println("Month:  " + sdf.format(PredictionList.get(i).getCurrentOccurance().getTime()));
				System.out.println((iCurMonth+1) + "/" + PredictionList.get(i).getCurrentOccurance().get(1));
			
		}

	}

}
