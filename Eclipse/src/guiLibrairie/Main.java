package guiLibrairie;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		
		Date datedebut = new Date (119, 11, 12);
		Date datefin = new Date(119, 11, 17);
		Date date = new Date(0, 0, 0);
		
		SimpleDateFormat formater = null;

	    //Date aujourdhui = new Date();

	    formater = new SimpleDateFormat("dd-MM-yyyy");
	    

	    System.out.println(formater.format(datedebut));
	    System.out.println(diffDate(datefin, datedebut));
	}
	    
	    public static String diffDate(Date datefin,Date datedebut){
	    	long diff = datefin.getTime() - datedebut.getTime();
	    	long seconds=0;
	    	long minutes=0;
	    	long hours=0;
	    	long days=0;

	    	while(diff>1000){
	    	diff=diff-1000;
	    	seconds++;
	    	if(seconds==60){
	    	seconds=0;
	    	minutes++;
	    	}

	    	if(minutes==60){
	    	minutes=0;
	    	hours++;
	    	}

	    	if(hours==24){
	    	hours=0;
	    	days++;
	    	}
	    	}



	    	String inter=""+days+"jours "+hours+"h"+minutes+"m"+seconds+"s";
	    	return inter;


	    	
	}

}
