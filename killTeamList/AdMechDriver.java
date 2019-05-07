package killTeamList;

import java.util.ArrayList;


public class AdMechDriver {

	public static void main(String [] args) {
		
		AdMech lst = new AdMech();
	
		ArrayList<String> list = lst.assignment();
			
		for(int i = 0; i < list.size(); i++) {
			
				System.out.println(list.get(i));
		}				
	}
}
