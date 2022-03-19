package model;

public class Channel {
	
	private String name;
	private int maxF;
	private int maxV;
	private String[] lov; // list of videos
	private Follower[] lof; // list of followers
	private int videocount;
	private int followercount;
	private double time;
	private double views;
	private double maxtime;
	private int[] listoftimes;
	private int count;

	public Channel(String string, int i, int j) {
		
		this.name = string;
		this.maxF = i;
		this.maxV = j;
		this.videocount = 0;
		this.followercount = 0;
		this.time = 0;
		this.views = 0;
		this.maxtime = 0;
		this.count = 0;
		
		this.lov = new String[this.maxV];
		this.lof = new Follower[this.maxF];
		
		this.listoftimes = new int[100];
		
		for(int e = 0; e < 100; e++) {
			
			this.listoftimes[e] = 0;
			
		}
		
	}
	
	public void releaseANewVideo(String string) {
		
		this.lov[this.videocount] = string;
		
		for(int i = 0; i < this.followercount; i++) {
			
			if(this.lof[i] instanceof Subscriber) {
				
				this.lof[i].addRec(string);
				
			}
			
		}
		
		this.videocount++;
		
	}
	
	public void follow(Follower f1) {
		
		this.lof[this.followercount] = f1;
		this.followercount++;
		f1.addChannel(this);
		
		if(f1 instanceof Monitor && this.time != 0 && this.views != 0 && this.maxtime != 0) {
			
			Monitor a = (Monitor) f1;
		    a.setPrior(this.time, this.views, this.maxtime, this.count);
			
		}
	
	}
	
	public void unfollow(Follower f1) {
		
		boolean check = false;
		
		for(int i = 0; i < this.followercount; i++) {
			
			if(this.lof[i] == f1) {
				
				check = true;

			}
			
		}
		
		if(check == true) {
			
			f1.removeChannel(this);
			
			int index = 0;
			
			for(int i = 0; i < this.followercount; i++) {
				
				if(this.lof[i] == f1) {
					
					index = i;
					this.lof[i] = null;
					break;
					
				}
				
			}
			
			for(int j = index; j < this.followercount - 1; j++) {
				
				this.lof[j] = this.lof[j + 1];
				
			}
			
			this.lof[this.followercount] = null;
			this.followercount--;
			
		}
		
	}
	
	public void addTimeView(int i) {
		
		if(this.maxtime < i) {

			this.maxtime = i;
			
		}
		
		this.time += i;
		
		this.listoftimes[this.count] = i;
		this.count++;
		
		this.views++;
		
	}
	
	public int[] getlistoftimes() {
		
		return this.listoftimes;
		
	}
	public double getTime() {
		
		return this.time;
		
	}
	
	public double getMaxTime() {
	    
		return this.maxtime;
		
	}
	
	public double getView() {
		
		return this.views;
		
	}
	
	public int getnov() {
		
		return this.videocount;
		
	}
	
	public String[] getVideos() {
		
		return this.lov;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}

	public String toString() {
		
		String s = "";
		
		s += this.name + " released";
		
		if(this.videocount == 0) {
			
			s += " no videos";
			
		}else {
			
			s += " <";
			
			for(int i = 0; i < this.videocount; i++) {
				
				s += this.lov[i];
				
				if(i < this.videocount - 1) {
					
					s += ", ";
					
				}
				
			}
			
			s += ">";
			
		}
		
		s += " and";
		
		if(this.followercount == 0) {
			
			s += " has no followers.";
			
		}else {
			
			s += " is followed by [";
			
			for(int i = 0; i < this.followercount; i++) {
				
				if(this.lof[i] instanceof Subscriber) {
					
					s += "Subscriber ";
					
				}
				
				if(this.lof[i] instanceof Monitor) {
					
					s += "Monitor ";
					
				}
				
				s += this.lof[i].getName();
				
				if(i < this.followercount - 1) {
					
					s += ", ";
					
				}
				
			}
			
			s += "].";
			
		}
		
		return s;
		
	}
	
}
