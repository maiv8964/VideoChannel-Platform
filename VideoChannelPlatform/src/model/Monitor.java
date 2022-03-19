package model;

public class Monitor extends Follower {
	
	private String name;
	private int maxchannels;
	private int numberofchannels;
	
	private double ptime;
	private double pview;
	private double pmaxtime;
	private int pcount;
	private double maxtime;

	public Monitor(String string, int i) {
		
		super(string, i);
		this.name = string;
		this.maxchannels = i;	
		this.ptime = 0;
		this.pview = 0;
		this.pmaxtime = 0;
		
	}
	
	public void setPrior(double a, double b, double c, int d) {
		
		this.ptime = a;
		this.pview = b;
		this.pmaxtime = c;
		this.pcount = d;
		
	}
	
	public String toString() {
		
		String s = "";
		
		s += "Monitor " + this.name + " follows";
		
		if(super.getnoc() == 0) {
			
			s += " no channels.";
			
		}else {
			
			s += " [";
			
			for(int i = 0; i < super.getnoc(); i++) {
				
				s += super.getLoc()[i].getName();
				
				if(super.getLoc()[i].getView() != 0) {
					
					if(this.pview == 0) {
						
						s += " {#views: " + String.format("%.0f", super.getLoc()[i].getView()) + ", max watch time: " +
								String.format("%.0f", super.getLoc()[i].getMaxTime()) + ", avg watch time: " + 
								String.format("%.2f", (super.getLoc()[i].getTime()) / super.getLoc()[i].getView()) + "}";
					
					}else if(super.getLoc()[i].getView() - this.pview != 0) {
						
						int[] tempList = new int[100];
						
						tempList = super.getLoc()[i].getlistoftimes();
						
						this.maxtime = tempList[this.pcount];
						
						for(int t = this.pcount + 1; t < 100; t++) {
							
							if(tempList[t] > this.maxtime) {
								
								this.maxtime = tempList[t];
								
							}
							
						}

						s += " {#views: " + String.format("%.0f", super.getLoc()[i].getView() - this.pview) + ", max watch time: " +
								String.format("%.0f", this.maxtime) + ", avg watch time: " + 
								String.format("%.2f", (super.getLoc()[i].getTime() - this.ptime) / (super.getLoc()[i].getView() - this.pview)) + "}";
							
					}
											
				}
				
				if( i < super.getnoc() - 1) {
					
					s += ", ";
					
				}
				
			}
			
			s += "].";
			
		}
		
		return s;
		
	}

}
