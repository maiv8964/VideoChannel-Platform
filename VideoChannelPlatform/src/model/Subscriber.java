package model;

public class Subscriber extends Follower {
	
	private String name;
	private int maxchannels;
	private int maxvideos;
	private int numberofchannels;

	public Subscriber(String string, int i, int j) {
		
		super(string, i, j);
		this.name = string;
		this.maxchannels = i;
		this.maxvideos = j;
		this.numberofchannels = 0;
		
	}
	
	public void watch(String string, int i) {
		
		boolean found = false;
		int index = 0;
		
		for(int d = 0; d < super.noc; d++) {
			
			String[] tempList = new String[super.getLoc()[d].getnov()];
			
			tempList = super.getLoc()[d].getVideos();  
			
			for(int e = 0; e < super.getLoc()[d].getnov(); e++) {
				
				if(tempList[e].equals(string)) {
					
					found = true;
					break;
					
				}		
			}
			
			if(found) {
				
				index = d;
				break;
				
			}
			
		}
		
		super.getLoc()[index].addTimeView(i);		
		
	}

	public String toString() {
		
		String s = "";
		
		s += "Subscriber " + this.name + " follows";
		
		if(super.getnoc() == 0) {
			
			s += " no channels";
			
		}else {
			
			s += " [";
			
			for(int i = 0; i < super.getnoc(); i++) {
				
				s += super.getLoc()[i].getName();
				
				if( i < super.getnoc() - 1) {
					
					s += ", ";
					
				}
				
			}
			
			s += "]";
			
		}
		
		s += " and";
		
		if(super.getnorv() == 0) {
			
			s += " has no recommended videos.";
			
		}else {
			
			s += " is recommended <";
			
			for(int i = 0; i < super.getnorv(); i++) {
				
				s += super.lorv[i];
				
				if(i < super.getnorv() - 1) {
					
					s += ", ";
					
				}
				
			}
			
			s += ">.";
			
		}
		
		return s;
		
	}

}
