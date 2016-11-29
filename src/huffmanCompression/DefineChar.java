package huffmanCompression;

public class DefineChar {

	String charName;
	int charCode;
	String code;
	
	public DefineChar(String m_charName, int m_charCode){
		charName = m_charName;
		charCode = m_charCode;
	}
	
	public int getCode(){
		return charCode;
	}
	
	public String getName(){
		return charName;
	}
	
	public String getCodeInDictionary(){
		return code;
	}
	
	public void setCode(String m_code){
		code = m_code;
	}
}
