package huffmanCompression;

public class TimeChar {

	String charName;
	int time;
	
	public TimeChar(String m_charName, int m_time){
		charName = m_charName;
		time = m_time;
	}
	
	public int getTime(){
		return time;
	}
	
	public String getName(){
		return charName;
	}
	
	public void addTime(){
		time++;
	}
}
