package model;

public class Follower {
	
	private int channels;
	private Channel[] loc;
	protected int noc;
	private String name;
	private int maxC;
	private int maxRV;
	protected String[] lorv;
	private int norv;
	private String type;
	private double tempMax;

	
	public Follower() {
		
	}
	
	public Follower(String string, int i, int j) {
		
		this.name = string;
		this.maxC = i;
		this.maxRV = j;
		this.setLoc(new Channel[this.maxC]);
		this.lorv = new String[this.maxRV];
		
	}

	public Follower(String string, int i) {
		
		this.name = string;
		this.maxC = i;
		this.setLoc(new Channel[this.maxC]);
		this.type = "monitor";
		this.tempMax = 0;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}

	public void addChannel(Channel channel) {
		
		this.getLoc()[noc] = channel;
		this.noc++;
		
	}
	
	public void addRec(String string) {
		
		this.lorv[this.norv] = string;
		this.norv++;
		
	}
	
	public void removeChannel(Channel channel) {
		
		int index = 0;
		
		for(int i = 0; i < this.noc; i++) {
			
			if(this.getLoc()[i] == channel) {
				
				index = i;
				this.getLoc()[i] = null;
				break;
				
			}
			
		}
		
		for(int j = index; j < this.noc - 1; j++) {
			
			this.getLoc()[j] = this.getLoc()[j + 1];
			
		}
		
		this.getLoc()[this.noc] = null;
		this.noc--;
		
	}
	
	public int getnoc() {
		
		return this.noc;
		
	}
	
	public String[] getRec() {
		
		return this.lorv;
		
	}
	
	public int getnorv() {
		
		return this.norv;
		
	}

	public Channel[] getLoc() {
		
		return loc;
	
	}

	public void setLoc(Channel[] loc) {
		
		this.loc = loc;
		
	}

}
