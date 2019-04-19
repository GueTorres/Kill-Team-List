package killTeamList;

import java.util.ArrayList;
import java.util.Random;

public class AdMech {
	

	//1st step create an ArrayList that holds every possible combination of squad
	//2nd step apply point values to each variation
	//**3rd step make combinations w/applied values using an 100 point limit
	//4th step try various limits based on current inventory
	
	private int points;	
	private String combi;	
	private AdMechNode D = null;
		
		
		
	public ArrayList<String> retrieve() {
			
		ArrayList<String> lst = new ArrayList<String>();
		
		if(isEmpty()) D = new AdMechNode(points,combi,null);
			
		else { lst = D.retrieve();}
		
		return lst;
	}
		
	public boolean isEmpty() { return D == null;}
		
	public class AdMechNode{
		
		int points = 0;
		
		String combi = null;
		
		AdMechNode head = D;
		
		AdMechNode previous = null;
		
		AdMechNode next;
		
		public AdMechNode(int points, String combi, AdMechNode d) {
		
			this.points = points;
			this.combi = combi;
			this.next = d;
		
		}

		public boolean isEmpty() {
			
			return head == null;
		}
		
		public ArrayList<String> retrieve() { //collects Nodes randomly to apply to createList()
						
					
			assignment();
			
			ArrayList<AdMechNode> AM_random = new ArrayList<AdMechNode>();
			ArrayList<AdMechNode> AM_list = new ArrayList<AdMechNode>();
			Random rand = new Random();
			
			if(previous.next != D) {
				AM_list.add(previous.next);
				previous = previous.next;
				retrieve();
			}
						
			for(int i = 0; i < AM_list.size(); i++) {
				int generate = rand.nextInt((AM_list.size() - 1) - i);
				AM_random.add(AM_list.get(generate));
			}
			
			
			ArrayList<String> stats = createList(AM_random);
			return stats;
			
		}
		
		public  void insert(int points, String combi) { //insert new Nodes
		
			if(previous == null) {
				
			AdMechNode D = new AdMechNode(points, combi, null);
				
			previous.next = new AdMechNode(points,combi, D);
		
			previous = previous.next;
			}
			else	previous.next = new AdMechNode(points,combi, D);
		}

		public void assignment() { //Point assignment

			ArrayList<String> combi = combi();
		
			int [] points = points(combi);
		
			for(int q = 0; q < points.length; q++) {
			
				insert(points[q], combi.get(q));
			}
		}
		
		public ArrayList<String> createList(ArrayList<AdMechNode> AM_random){ //provides an array of stats to create the 100 point rosters
			
			ArrayList<AdMechNode> u_list = new ArrayList<AdMechNode>();
			
			ArrayList<String> stats = new ArrayList<String>();
			
			int roster = 0;
			
			for(int i = 0; i < AM_random.size(); i++) {
				roster += AM_random.get(i).points;
				if(roster <= 100 ) { 
						u_list.add(AM_random.get(i));
				}
			}
			
			for(int j = 0; j < u_list.size(); j++) {
								
				stats.add("Candidate: " + u_list.get(j).combi + 
				"/nPoints per Model Configuration: " + u_list.get(j).points);
				
			}
			
			return stats;
		}

		public ArrayList<String> combi() {
		
			String vanguard = " Skitarii Vanguard ";
			String ranger = " Skitarii Ranger ";
			String vgunner = " Vanguard Gunner ";
			String rgunner = " Ranger Gunner ";
			String valpha = " Vanguard Alpha ";
			String ralpha = " Ranger Alpha ";
			String ruststalker = " Sicarian Ruststalker ";
			String infiltrator = " Sicarian Infiltrator ";
			String rprinceps = " Ruststalker Princeps ";
			String iprinceps = " Infiltrator Princeps ";

			String ospex = " Omnispex ";
		String edTether = " Enhanced Data-Tether ";
		String amaul = " Arc Maul ";
		String psword = " Power Sword ";
		String rpistol = " Radium Pistol ";
		String rcarbine = " Radium Carbine ";
		String grifle = " Galvanic Rifle ";
		String fblaster = " Flechette Blaster ";
		String pbpistol = " Phosphor Blast Pistol ";
		String apistol = " Arc Pistol ";
		String pcaliver = " Plasma Caliver ";
		String arifle = " Arc Rifle ";
		String tarquebus = " Transuranic Arquebus ";
		String tblades = " Transuranic Blades ";
		String trazor = " Transuranic Razor ";
		String cclaw = " Chordclaw ";
		String scarbine = " Stubcarbine ";
		String tgoad = " Taser Goad ";


		String [] wargear = new String[] {amaul, psword, rpistol, rcarbine, grifle, fblaster, pbpistol, apistol,
										pcaliver, arifle, tarquebus, tblades, trazor, cclaw, scarbine, tgoad};

		String [] tool = new String[] {ospex, edTether, null};

		String [] squad = new String[]{vanguard, ranger, rgunner, vgunner, valpha, ralpha,
			  						ruststalker, infiltrator, rprinceps, iprinceps};

		ArrayList<String> troops = new ArrayList<String> ();


		 //may need to add a to each conditional

		for(int i = 0; i < squad.length; i++) {
		
			if(squad[i] == vanguard) {	//galvanic rifle and omnispex or edt or nothing
				vanguard += grifle;
				for(int j = 0; j < tool.length; j++) {
					vanguard += tool[j];
					troops.add(vanguard);
					
				}
			}
		
			if(squad[i] == ranger) {	//radium carbine and omnispex or edt or nothing
				ranger += rcarbine;
				for(int j = 0; j < tool.length; j++) {
					ranger += tool[j];
					troops.add(ranger);
					
				}
			}
		
			if(squad[i] == rgunner) {	// galvanic rifle or arc rifle or plasma caliver or transuranic arquebus
				for(int j = 0; j < wargear.length; j++) {
					if(wargear[j] == grifle || wargear[j] == arifle || wargear[j] == pcaliver || wargear[j] == tarquebus) {
						rgunner += wargear[j];
						troops.add(rgunner);
						
					}
				}
			}
		
			if(squad[i] == vgunner) {	//radium carbine or (arc maul or power sword and radium pistol or phosphor blast pistol
				for(int j = 0; j < wargear.length; j++) {
					if(wargear[j] == grifle || wargear[j] == arifle || wargear[j] == pcaliver || wargear[j] == tarquebus) {
						vgunner += wargear[j];
						troops.add(vgunner);
						
					}
				}
			}
		
			if(squad[i] == ralpha) { //galvanic rifle or (arc maul or power sword and radium pistol or phosphor blast pistol)
				for(int j = 0; j < wargear.length; j++) {
					if(wargear[j] == grifle) {
					ralpha += wargear[j];
					troops.add(ralpha);
					}
					if(wargear[j] == amaul || wargear[j] == psword) {
						ralpha += wargear[j];
									
						for(int k = 0; k < wargear.length; k++) {
							if(wargear[k] == rpistol || wargear[k] == pbpistol) {
							ralpha += wargear[k];
							troops.add(ralpha);
							
							}
						
						}
						
					}
				}
			}
			
			if(squad[i] == valpha) { //galvanic rifle or (arc maul or power sword and radium pistol or phosphor blast pistol)
				for(int j = 0; j < wargear.length; j++) {
					if(wargear[j] == grifle) {
						valpha += wargear[j];
						troops.add(valpha);
						
					}
					if(wargear[j] == amaul || wargear[j] == psword) {
						valpha += wargear[j];
						for(int k = 0; k < wargear.length; k++) {
							if(wargear[k] == rpistol || wargear[k] == pbpistol) {
									valpha += wargear[j];
									troops.add(valpha);
									
							}
						}
					}
				}	
			}
			
		
			if(squad[i] == ruststalker) {	//trazor and cclaw
				for(int j = 0; j < wargear.length; j++) {
					if(wargear[j] == trazor) {
						ruststalker += wargear[j];
						ruststalker += cclaw;
						troops.add(ruststalker);
						
					}
				}
			
				for(int k = 0; k < wargear.length; k++) {
					if(wargear[k] == tblades) {
						ruststalker += wargear[k];
						troops.add(ruststalker);
						
					}
					
				}
			
			}
		
			if(squad[i] == rprinceps) {	//tblades and cclaw
				for(int j = 0; j < wargear.length; j++) {
					if(wargear[j] == trazor) {
						rprinceps += wargear[j];
						rprinceps += cclaw;
						troops.add(ruststalker);
						
						}
					}
				for(int k = 0; k < wargear.length; k++) {
					if(wargear[k] == tblades) {
						rprinceps += wargear[k];
						rprinceps += cclaw;
						troops.add(rprinceps);
					}					
				}
			}
			if(squad[i] == infiltrator) {	//scarbine and psword or fblaster and tgoad
				for(int j = 0; j < wargear.length; j++) {
					if(wargear[j] != scarbine && wargear[j] == psword) infiltrator += wargear[j];
				
					if(wargear[j] != psword && wargear[j] == scarbine) infiltrator += wargear[j];
				
					troops.add(infiltrator);
					
				}
		
				for(int j = 0; j < wargear.length; j++) {
					if(wargear[j] != fblaster && wargear[j] == tgoad) infiltrator += wargear[j];
				
					if(wargear[j] != tgoad && wargear[j] == fblaster) infiltrator += wargear[j];
					
					troops.add(infiltrator);
					
				}
				
			}
			if(squad[i] == iprinceps) {	//scarbine and psword or fblaster and tgoad
				for(int j = 0; j < wargear.length; j++) {
					if(wargear[j] != scarbine && wargear[j] == psword) iprinceps += wargear[j];
				
					if(wargear[j] != psword && wargear[j] == scarbine) iprinceps += wargear[j];
				
					troops.add(iprinceps);
					
				}
		
				for(int j = 0; j < wargear.length; j++) {
					if(wargear[j] != fblaster && wargear[j] == tgoad) iprinceps += wargear[j];
				
					if(wargear[j] != tgoad && wargear[j] == fblaster) iprinceps += wargear[j];
				
					troops.add(iprinceps);
					
				}
			
			}
		}
		return troops;
	}
		
		public int [] points(ArrayList<String> troops) {
		
		int [] points = {troops.size()};
		for(int i = 0; i < troops.size(); i++) {
			
			String str = troops.get(i); 
			String split[] = str.split(" ", 0); 
			for(String s:split) {
				
				if(s == " Alpha ") points[i] = 10;
				
				if(s == " Gunner ") points[i] = 10;
				
				if(s == " Skitarii ") points[i] = 9;
				
				if(s == " Sicarian ") points[i] = 14;
				
				if(s == " Princeps ") points[i] = 15;
				
				if(s == " Enhanced ") points[i] += 5;
				
				if(s == " Omnispex ") points[i] += 1;
				
				if(s == " Chordclaw ") points[i] += 1;
				
				if(s == " Taser ") points[i] += 1;
				
				if(s == " Plasma ") points[i] += 3;
				
				if(s == " Arquebus ") points[i] += 5;
							
			}
		
		}
		return points;
	}
		
	}

	}






