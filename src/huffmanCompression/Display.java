package huffmanCompression;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Display {
	
	private JFrame frame;
	private JTextField text0;
	private JTextField text1;
	private JButton button0;
	private JButton button1;
	private JLabel label;
	private JPanel textPanel;
	private JPanel buttonPanel;

	public void onGUI(){
		//setup variables
		final int HEIGHT = 170;
		final int WIDTH = 450;
		
		//setup all GUI elements
		frame = new JFrame("CompressionWare");
		text0 = new JTextField("input.txt");
		text1 = new JTextField("output.sxy");
		button0 = new JButton("compress");
		button1 = new JButton("decompress");
		label = new JLabel("input file needs to be in the same directory");
		textPanel = new JPanel();
		buttonPanel = new JPanel();
		
		//set alignments and sizes
		text0.setSize((int) (WIDTH * .5), (int) (HEIGHT * .7));
		text1.setSize((int) (WIDTH * .5), (int) (HEIGHT * .7));
		label.setHorizontalAlignment(0);
		
		//setup font
		Font fontButton = new Font("Tahoma", Font.BOLD, 14);
		Font fontText = new Font("Calibri", Font.PLAIN, 45);
		Font fontLabel = new Font("Calibri", Font.ITALIC, 15);
		button0.setFont(fontButton);
		button1.setFont(fontButton);
		label.setFont(fontLabel);
		text0.setFont(fontText);
		text1.setFont(fontText);
		
		//add to frame
		button0.addActionListener(event -> clickCompress());
		button1.addActionListener(event -> clickDecompress());
		textPanel.add(text0);
		textPanel.add(text1);
		buttonPanel.add(button0);
		buttonPanel.add(button1);
		frame.getContentPane().add(textPanel, BorderLayout.CENTER);
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		frame.getContentPane().add(label, BorderLayout.NORTH);
		
		//display the frame
		frame.setLocation(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(3);
	}
	
	private void clickCompress() {
		//setup variables
		final String START_DICTIONARY = "~";
		final String DICTIONARY_SEC = "-";
		ArrayList<TimeChar> timeCharObj  = new ArrayList<TimeChar>();
		ArrayList<DefineChar> dictionary  = new ArrayList<DefineChar>();
		
		CompressTool compressTool = new CompressTool();
		compressTool.compress(getInputName(), getOutputName(), START_DICTIONARY, DICTIONARY_SEC, timeCharObj, dictionary);
	}
	
	//not finished
	private void clickDecompress(){
		DecompressTool decompressTool = new DecompressTool();
		decompressTool.decompress();
	}
	
	public String getInputName(){
		return text0.getText();
	}
	
	public String getOutputName(){
		return text1.getText();
	}
	
	public void setInputName(String m_input){
		text0.setText(m_input);
	}
	
	public void setOutputName(String m_output){
		text0.setText(m_output);
	}
}
