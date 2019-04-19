package killTeamList;

import java.util.ArrayList;
import java.util.Scanner;

public class AdMechDriver {

public static void main(String [] args) {
		
		AdMech lst = new AdMech();
	
		Scanner question = new Scanner(System.in);
		
		System.out.println("You want a list?");
		
		String answer = question.nextLine();
		
		if(answer.toLowerCase() == "no") {
			
			System.out.println("Ok bye!");
		}
		
		else {			
			
			ArrayList<String> list = lst.retrieve();
			
			for(int i = 0; i < list.size(); i++) {
			
				System.out.println(list.get(i));
			}				
		}
	}
}
