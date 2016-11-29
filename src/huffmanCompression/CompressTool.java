package huffmanCompression;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CompressTool {

	public void compress(String m_inputName, String m_outputName, String m_dicStart, String m_dicSec,
			ArrayList<TimeChar> m_timeChar, ArrayList<DefineChar> m_defChar){
		
		try {
			File input = new File(m_inputName);
			
			FileReader reader = new FileReader(input);
			String inputString = "";
			
			long charSum = input.length();
			char[] tempCharRead = new char[(int)charSum];
			reader.read(tempCharRead);
			for(char c : tempCharRead){
				inputString += c;
			}
			reader.close();
			
			String result = virtualCompress(inputString, m_timeChar, m_defChar);
			String output = "";
			
			for(int i = 0; i < result.length() - 7; i += 7){
				output += (char)((byte)Byte.valueOf(result.substring(i, i + 7), 2));
			}
			
			try{
				FileWriter write = new FileWriter(m_outputName);
				write.write(output);
				write.write(m_dicStart);
				for(int i = 0; i < m_defChar.size(); i++){
					write.write(m_defChar.get(i).getName());
					write.write(m_dicSec + m_defChar.get(i).getCodeInDictionary());
				}
				write.close();	
			}catch(IOException e){
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private String virtualCompress(String compressString, ArrayList<TimeChar> m_timeChar, ArrayList<DefineChar> m_dictionary){
		findTime(compressString, m_timeChar);
		define(m_timeChar, m_dictionary);
		setRealCode(m_dictionary);
		
		return transform(m_dictionary, compressString);
	}

	private void findTime(String m_compressString, ArrayList<TimeChar> m_timeChar){
		for(int i = 0; i < m_compressString.length(); i++){
			if(i == 0){
				m_timeChar.add(new TimeChar(m_compressString.substring(0, 1), 1));
			}else{
				boolean alreadySet = false;
				for(int j = 0; j < m_timeChar.size(); j++){
					if(m_timeChar.get(j).getName().equals(m_compressString.substring(i, i + 1))){
						m_timeChar.get(j).addTime();
						alreadySet = true;
					}
				}
				if(!alreadySet){
					m_timeChar.add(new TimeChar(m_compressString.substring(i, i + 1), 1));
				}
			}
		}
	}
	
	private void define(ArrayList<TimeChar> m_timeChar, ArrayList<DefineChar> m_dictionary){
		TimeChar leastChar;
		int leastTime;
		
		do{
			//Define local variables
			String localNameForRemovedChar = "";
			int localTimeForRemovedChar = 0;
			
			for(int i = 0; i < 2; i++){
				leastChar = m_timeChar.get(0);
				leastTime = m_timeChar.get(0).getTime();
				for(int j = 1; j < m_timeChar.size(); j++){
					if(m_timeChar.get(i).getTime() < leastTime){
						leastChar = m_timeChar.get(i);
						leastTime = m_timeChar.get(i).getTime();
					}
				}
				localNameForRemovedChar += leastChar.getName();
				localTimeForRemovedChar += leastTime;
				m_dictionary.add(new DefineChar(leastChar.getName(), i));
				m_timeChar.remove(leastChar);
			}
			
			//Make replacement nodes
			m_timeChar.add(new TimeChar(localNameForRemovedChar, localTimeForRemovedChar));
		}while(m_timeChar.size() > 1);
	}
	
	private void setRealCode(ArrayList<DefineChar> m_dictionary){
		
		for(int k = 0; k < m_dictionary.size(); k++){
			m_dictionary.get(k).setCode(m_dictionary.get(k).getCode() + "");
		}
		
		int size = 2;
		
		do{
		for(int i = 0; i < m_dictionary.size(); i++){
			if(m_dictionary.get(i).getName().length() == size){
				for(int j = 0; j < m_dictionary.get(i).getName().length(); j++){
					for(int k = 0; k < m_dictionary.size(); k++){
						if(m_dictionary.get(k).getName().equals(m_dictionary.get(i).getName().substring(j, j+ 1))){
							m_dictionary.get(k).setCode(m_dictionary.get(k).getCodeInDictionary() + m_dictionary.get(i).getCodeInDictionary());
						}
					}
				}
			}
		}
		size++;
		}while(size < 100);
		
		cleanDictionary(m_dictionary);
	}
	
	private void cleanDictionary(ArrayList<DefineChar> m_dictionary){
		for(int i = 0; i < m_dictionary.size(); i++){
			if(m_dictionary.get(i).getName().length() > 1){
				m_dictionary.remove(i);
				i--;
			}
		}
	}
	
	private String transform(ArrayList<DefineChar> m_dictionary, String m_convert){
		String convert = "";
		for(int i = 0; i < m_convert.length(); i++){
			for(int j = 0; j < m_dictionary.size(); j++){
				if(m_convert.substring(i, i + 1).equals(m_dictionary.get(j).getName())){
					convert += m_dictionary.get(j).getCodeInDictionary();
				}
			}
		}
		return convert;
	}
	
}
