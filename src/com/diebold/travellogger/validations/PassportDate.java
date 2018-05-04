package com.diebold.travellogger.validations;

import java.util.Date;

public class PassportDate {
	public static int validateDate(Date start, Date end, Date expire, Date issue){
        if((end.getTime()-start.getTime())>0){
              if((expire.getTime()-start.getTime())>0 && (expire.getTime()-end.getTime())>0 && (issue.getTime()-start.getTime()<0)){
                   return 0;
              }else{
                   return 1;
              }
        }
        return 2;
        
   }

	
	public static boolean validateJourney(Date start, Date end){
		if((end.getTime()-start.getTime())>0){
				return true;
		}
		return false;
	}
}
