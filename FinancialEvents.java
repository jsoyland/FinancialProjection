import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class FinancialEvents {

	ArrayList<FinancialEvent> m_FinancialEvent;
	ArrayList<FinancialEvent> m_PredictionList;
	int m_iCount;
	
	FinancialEvents(){
		m_FinancialEvent=new ArrayList<FinancialEvent>();
		m_PredictionList=new ArrayList<FinancialEvent>();
		m_iCount = 0;
	}
	
	public int read(String strFilename){
		String strName;
		Integer iDebitInd;
		double dAmount;
		Calendar calStartDate;
		String strLabel;
		Integer iFrequencyID;
		Integer iFrequencyCount;
		FinancialEvent feNew;
		FileReader fr;
		BufferedReader input;
		String s;
		String[] strSplit;
		String[] strDateSplit;
		
		try{
			fr = new FileReader(strFilename);
			input = new BufferedReader(fr);
			s = input.readLine();
		
			while( s instanceof String ){
				if ((s.length() == 0)){
					// do nothing - just loop
					s = input.readLine();
				} 
				else{
					// it's a line
					strSplit=s.split("\t");
					
					try{
						strName = strSplit[0];
					}
					catch(Exception e){
						strName = "";
					}
					
					try{
						iDebitInd = Integer.valueOf(strSplit[1]);
					}
					catch(Exception e){
						iDebitInd = 0;
					}
					
					try{
						dAmount = Double.valueOf(strSplit[2]);
					}
					catch(Exception e){
						dAmount = 0.0;
					}
					
					try{
						iFrequencyID = Integer.valueOf(strSplit[3]);
					}
					catch(Exception e){
						iFrequencyID = 0;
					}

					try{
						strDateSplit = strSplit[4].split("-");
						calStartDate = Calendar.getInstance();
						calStartDate.set(Integer.valueOf(strDateSplit[0]), Integer.valueOf(strDateSplit[1])-1, Integer.valueOf(strDateSplit[2]), 0, 0, 0);
					}
					catch(Exception e){
						calStartDate = Calendar.getInstance();
						calStartDate.set(1999, 0, 1, 0, 0, 0);
					}
					
					try{
						iFrequencyCount = Integer.valueOf(strSplit[5]);
					}
					catch(Exception e){
						iFrequencyCount = 0;
					}
					
					try{
						strLabel = strSplit[6];
					}
					catch(Exception e){
						strLabel = "";
					}
					
					feNew = new FinancialEvent(strName, iDebitInd, dAmount, calStartDate, strLabel, iFrequencyID, iFrequencyCount);
					add(feNew);
					s = input.readLine();
				}
			}
		}
		catch (Exception e){

		}
		
		return 0;
	}

	public int add(FinancialEvent feNew){
		m_FinancialEvent.add(feNew);
		m_iCount++;
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public int print(){
	
		Collections.sort(m_FinancialEvent);
	
		for (int i = 0; i < m_iCount; i++){
			m_FinancialEvent.get(i).print();
			System.out.println("********************");
		}
		return 0;
	}
	
	public int predict(Calendar calStartDate, Calendar calEndDate){
		FinancialEvent feNew;
		Calendar newOccurance;		
//		m_PredictionList.clear();
		
		//String DATE_FORMAT = "MM-dd-yyyy HH:mm:ss";
		String DATE_FORMAT = "MM-dd-yyyy";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		
		System.out.println("Start: " + sdf.format(calStartDate.getTime()));
		System.out.println("End: " + sdf.format(calEndDate.getTime()));
			
		
		for (int i = 0; i < m_iCount; i++){
			while (m_FinancialEvent.get(i).getNextOccurance().before(calStartDate));  // Advance current occurrence until start date
			while (m_FinancialEvent.get(i).getCurrentOccurance().before(calEndDate)){
		
				newOccurance = Calendar.getInstance();
				newOccurance.setTime(m_FinancialEvent.get(i).getCurrentOccurance().getTime());
				
				feNew = new FinancialEvent(m_FinancialEvent.get(i).getName(), 
										   m_FinancialEvent.get(i).getDebitInd(),
										   m_FinancialEvent.get(i).getAmount(), 
										   newOccurance, 
										   m_FinancialEvent.get(i).getLabel(), 
										   0, 
										   0);
				m_PredictionList.add(feNew);
				
				m_FinancialEvent.get(i).getNextOccurance();
			}
		}

		Collections.sort(m_PredictionList);
	
		for (int i = 0; i < m_PredictionList.size(); i++){
			m_PredictionList.get(i).print();
		}
		
		
		return 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


