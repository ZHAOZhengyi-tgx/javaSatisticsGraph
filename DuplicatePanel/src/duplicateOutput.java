import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;
import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector.Matcher;
import com.sun.xml.internal.ws.util.StringUtils;

import javafx.scene.control.TableColumn;

import java.awt.TextArea;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Button;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.Dimension;


public class duplicateOutput extends JPanel {
	
	private String line;
	//private String textOutput;
	private JTextField textField;
	private String findstr = "MTAXF";
	private int FireIce=26;
	private int splitFireIce=25;
	private JTable table;
	public String textFileNameInput="";

	private int columnNumber=0;
	private File lastPath;  //zzy
	
	public duplicateOutput(){
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 48, 582, 430);
		add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(945, 100, 374, 388);
		add(scrollPane_1);
		
		JTextArea duplicateDisplay = new JTextArea();
		scrollPane_1.setViewportView(duplicateDisplay);
		duplicateDisplay.setEditable(false);
		
		JTextArea textOutput = new JTextArea(12,30);
		scrollPane.setViewportView(textOutput);
		textOutput.setEditable(false);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(1416, 100, 350, 388);
		add(scrollPane_2);
		
		JTextArea duplicates = new JTextArea();
		duplicates.setEditable(false);
		scrollPane_2.setViewportView(duplicates);
		
		
		
	
		DefaultTableModel model = new DefaultTableModel(); 
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setEnabled(false);
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_3.setBounds(40, 564, 1500, 388);
		add(scrollPane_3);
		table = new JTable(model);
		
	    table.setColumnSelectionAllowed(true);
	    table.setRowSelectionAllowed(false);
		table.setPreferredScrollableViewportSize(new Dimension(10000, 10000));
		table.setSize(new Dimension(10000,10000));
		table.setMinimumSize(new Dimension(100000, 10000));
		//table.setPreferredScrollableViewportSize(new Dimension(822, 300));
		//table.setPreferredSize(new Dimension(822, 300));
		scrollPane_3.setViewportView(table);
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		
		//table.setRowHeight(300);
		//model.getColumnCount();
		
		
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.setBounds(710, 106, 83, 29);
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				textOutput.setText(""); 
				duplicateDisplay.setText("");
				duplicates.setText("");
				model.setRowCount(0);
				model.setColumnCount(0);
				model.fireTableDataChanged();
				
				
				
				JFileChooser fileChooser = new JFileChooser();	
				fileChooser.setMultiSelectionEnabled(true);
				
				if (lastPath != null) 
				{
					fileChooser.setCurrentDirectory(lastPath);
				}
				int returnVal = fileChooser.showOpenDialog(null); //replace null with your swing container
				try {
					
				File file = null;
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					file = fileChooser.getSelectedFile(); 
					textFileNameInput=fileChooser.getSelectedFile().getAbsolutePath();
							//					textFileNameInput=textFileNameInput.substring(0, textFileNameInput.lastIndexOf("."));
					lastPath = file.getParentFile(); // zzy
					
				}
				
				BufferedReader in = new BufferedReader(new FileReader(file));
				line = in.readLine();
				while(line != null){
					textOutput.append(line + "\n");
					line = in.readLine();
				}
					} catch (IOException e1) 
					{
						e1.printStackTrace();
					}
			}
		});
		setLayout(null);
		add(btnUpload);
		
		JButton Search = new JButton("Search");
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				duplicateDisplay.setText("");
				int lastIndex=0;
				int defStartIndex=0;
				int defEndIndex=0;
				int foundIndex=0;
				String defStr="working unit Auto waiting work Operation by:"; 
//				String FFduplicationCheck=" working unit Auto laser barcode done check";
				
				int count=0; 
				//int errorChecker=-2;
				int twoString=0; 
				String str = textOutput.getText().toString();
				//String findstr = "MTAXF";
				String amountStr="";
				String stringCompare="";
				String stringCompare2=""; 
				String finalString="";
				int idRange=0;
				int escapeSearch=0;
				int x=0;
				defStartIndex = str.indexOf(defStr,0);
				//System.out.println(defStartIndex);
				while(defStartIndex != -1&&defEndIndex != -1)
				{
						defEndIndex = str.indexOf(defStr,defStartIndex+findstr.length());
									
						if(defStartIndex != -1&&defEndIndex !=-1)
						{
							
							amountStr= str.substring(defStartIndex, defEndIndex);
							do{
								lastIndex = amountStr.indexOf(findstr,lastIndex);
								//System.out.println(lastIndex);
								//System.out.println(defEndIndex);
								if(lastIndex != -1&&((defEndIndex-defStartIndex-lastIndex)>=34))
								{
									// unit Auto laser barcode done check
										if(!amountStr.substring(lastIndex,lastIndex+34).contains("working")) // Zhengyi 210817
										{
											foundIndex=lastIndex;
											System.out.println(lastIndex);
											lastIndex=0;
											escapeSearch=2;
											break;
										}

									
								} 
								else if(lastIndex == -1)
								{
									escapeSearch=1; //empty string indicator
								}
								lastIndex += findstr.length();
							}while(escapeSearch!=1);
							
							defStartIndex=defEndIndex;
						//	defStartIndex=defEndIndex+1;
						
							if(escapeSearch==2)
							{
								stringCompare =  amountStr.substring(foundIndex,foundIndex+34);
								finalString +=(stringCompare+"\n"); 
								duplicateDisplay.setText(finalString);
								//count++;
						    	//duplicateCount.setText(Integer.toString(count));
						    	
								System.out.println(finalString);
							}
							escapeSearch=0;
						
						}
				}
				if(defStartIndex != -1&&defEndIndex == -1)
				{
					lastIndex=defStartIndex;
					do {
						lastIndex = str.indexOf(findstr,lastIndex);
						idRange=str.length()-1-lastIndex;
						if(lastIndex==-1)
							break;
						// Zhengyi 210817
						else if(idRange>=34&&!str.substring(lastIndex,lastIndex+34).contains("working"))
						{
							foundIndex=lastIndex;
							stringCompare2 =  str.substring(foundIndex,foundIndex+34);
							finalString +=(stringCompare2+"\n"); 
							duplicateDisplay.setText(finalString);
							lastIndex=0;
							x=1;
						
							break;
						}
						
						lastIndex += findstr.length();
					}while(x!=1);
				}
				
			}
		});
		Search.setBounds(697, 165, 115, 29);
		add(Search);
		
		JLabel lblNewLabel = new JLabel("Number of duplications:");
		lblNewLabel.setBounds(1522, 61, 186, 20);
		add(lblNewLabel);
		
		JLabel lblDuplicateIdList = new JLabel("All Panel and barcode list:");
		lblDuplicateIdList.setBounds(1051, 61, 203, 20);
		add(lblDuplicateIdList);
		
		
		
		JButton duplicateChecker = new JButton("Check for Duplicate");
		duplicateChecker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String strTemp1="";
				String strTemp2="";
				int tempIndex=0;
				int tempIndex2=0;
				int noMarking=0;
				int count=0;
				int duplicationCount=0;
				int duplicationList=0;
				String duplication="";
				String check=duplicateDisplay.getText().toString();
				String FFduplicationCheck=" working unit Auto laser barcode done check";
				
				while(tempIndex!=-1){
					tempIndex=check.indexOf(findstr,tempIndex);
					if(tempIndex!=-1)
					{
						
						strTemp1=check.substring(tempIndex, tempIndex+34);
						//strTemp2+=strTemp1+"\n";
						
						tempIndex2=tempIndex;
					//	tempIndex2 += strTemp1.length();
						tempIndex2 +=strTemp1.length();
						while(tempIndex2!=-1)
						{
							tempIndex2=check.indexOf(strTemp1,tempIndex2);
							if(tempIndex2!=-1)
							{
								noMarking=numberduplication(textOutput.getText().toString(),strTemp1+FFduplicationCheck);
								System.out.println(noMarking);
								System.out.println(FireIce);
								System.out.println(splitFireIce);
								if(noMarking>=FireIce*2)
								{
									duplicationCount=noMarking/splitFireIce;
									duplicationList++;
									duplication = duplication+duplicationList+") "+strTemp1+": "+duplicationCount+("\n");
									check=check.replaceAll(strTemp1,"");
									tempIndex=0;
								
									
								}
								else
								{
									//System.out.println(duplication);
									tempIndex2 += strTemp1.length();
								}
							}
						
							
						}
						tempIndex += findstr.length();
						//tempIndex=0;
					}
					
					
				}
				//System.out.println(check);
				duplicates.setText(duplication);
				
			}
		});
		duplicateChecker.setBounds(658, 210, 204, 96);
		add(duplicateChecker);
		
		
		JRadioButton IF = new JRadioButton("IceFish");
		IF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JRadioButton FF = new JRadioButton("FireFish");
		FF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		FF.setSelected(true);
		FF.setBounds(652, 45, 155, 49);
		add(FF);
		IF.setBounds(814, 45, 155, 49);
		add(IF);
		
	
		
		JButton organise = new JButton("Organise unique flex ID");
		organise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String unique=duplicateDisplay.getText().toString();
				String flex="";
				String uniqueString;
				String rowString="";
				String rowString2="";
				String columnString="";
				
				
				ArrayList<String> organisedString = new ArrayList<String>();
				
				//uniqueString.add(flex);
				//uniqueString.add("");
				//uniqueString.add("B");
				//uniqueString.add("C");
			//	
				int x=0;
				int index=0;
				int fixedIndex=0;
				int row=0;
				int row2=0;
				int column=1;
				
				
				Object rowTable="";
				String findstr2="";
				String lotId="";
				String[][] bcID = new String[100][100]; 
			//	String[] panelId = new String[100]; 
				String flexID="";
				
				
				
				flex=searchFunction(findstr,unique);
				fixedIndex=flex.indexOf(findstr,fixedIndex);
				organisedString.add("No.");
				if((flex.length()-fixedIndex-1)>14)
				{
					findstr2=flex.substring(fixedIndex,fixedIndex+14);
				}
				organisedString.add(findstr2);//panelId[column]=findstr2;
				
			//	System.out.println(flex);
				do{
					index=flex.indexOf(findstr2,index);
	//				System.out.println(index);
					if(index!=-1&&((flex.length()-index-1)>=34))
					{
						
						bcID[row2][0]=""+(row2+1);
						bcID[row2][column] = (flex.substring(index+15,index+34)); //find bcID
						flexID=flex.substring(index,index+34);
					//	System.out.println(flexID);
						
						//organisedString.add(row2,bcID); //add column for row
						flex=flex.replaceAll(flexID,""); //rid of flexId from original string
						index=0;
						
						
						row2++;
						
					}
					else if(flex.indexOf(findstr)!=-1)
					{	
						column++;
						index=flex.indexOf(findstr);
						//System.out.println(index);
						organisedString.add((flex.substring(index,index+14)));//panelId[column]=(flex.substring(index,index+14)); //add row for lotID
						//lotId=findstr2;
						findstr2=(flex.substring(index,index+14));
						index=0;
						row2=0;
						
						//System.out.println(panelId[column]);
					}
					
					//System.out.println(index);
				}while(flex.indexOf(findstr)!=-1);
				//System.out.println(flex);
				row=0;
				column=0;
				duplicateDisplay.setText(searchFunction(findstr,unique));
				
				//organisedString.add(flex);
				//put tabletemplate into Jtable
				
			//	String[][] tableTemplate = new String[5][40];
				model.setRowCount(0);
				model.setColumnCount(0);
				model.fireTableDataChanged();
				
				//model.addColumn(organisedString);
				for (int col = 0; col < organisedString.size(); col++) 
			    {
			    //rowString = bcID[col+1][row+1];
			    //	tableTemplate[row][col] = (items[col]);
			    //	tableTemplate[row][col] = bcID[column][row2];
			 //   	model.addColumn(tableTemplate[row][col]);
			    	//tableTemplate[row][col] = bcID[row][col];
			  //  	System.out.println(panelId[col]);
					
					
					columnString = organisedString.get(col);
					
					model.addColumn(columnString);
					
					   //model.addColumn(panelId[col]);
			    }
				
//				for(int col=0;col<organisedString.size();col++)
//				{
//					model.addColumn(items[col]);
//				}
				
			//	System.out.println(organisedString.size());
			
				for (row = 0; row < 40; row++) 
				{	
//				    rowString = organisedString.get(row);
//				    rowString= rowString.replaceAll("\\s+", ",");
	//			    String[] items = rowString.split(",");
				    
//				    rowString2 = bcID[0][row];
//				    rowString2 = rowString2.replaceAll("\\s+", "");
//				    String[] items = rowString.split("-");
				    
				//    System.out.println(tableTemplate);
				  //  tableTemplate[row] = new String[items.length];
				   // model.addRow(tableTemplate[row]);
				
					
				//	System.out.println(bcID[row]);
					model.addRow(bcID[row]);
					
				}
				    
				
			
				
				model.fireTableDataChanged();
				

			}
		});
		organise.setBounds(659, 345, 203, 111);
		add(organise);
		
		JButton btnNewButton = new JButton("Save Table");
		JMenuItem save = new JMenuItem("save to file");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				final JPopupMenu popup = new JPopupMenu();

				 try {

				     ExcelExporter exp = new ExcelExporter();
				     
				     JFileChooser fileChooser = new JFileChooser();
				     fileChooser.setDialogTitle("Choose Only File Location");
				     fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

//				     int returnValue = fileChooser.showSaveDialog(null) ;

				    
				    // File file = new File(textFileNameInput);
				   // fileChooser.setSelectedFile(file);
				     
				     
//				     if(returnValue == JFileChooser.APPROVE_OPTION&&fileChooser.getSelectedFile().getName() == null)
//				     {
//				    	 String fileName=fileChooser.getSelectedFile().getAbsolutePath();
//				    	 exp.exportTable(table, new File(fileName+"\\"+textFileNameInput+".xls"));
//				    	 
//				     } 
//				     if( returnValue == JFileChooser.APPROVE_OPTION ) 
				     {   // zzy with Jay
				    	 
//				    	 	String directories=textFileNameInput.getSelectedFile().getPath(); // fileChooser
//				    	 	System.out.println(directories);
				    	 	String fileNameOutput = textFileNameInput + ".xls";
//				    	 	System.out.println(fileChooser.getSelectedFile().getName());
//				            exp.exportTable(table, new File(directories+"\\"+fileName+".xls"));
				    	 	System.out.println(fileNameOutput);
				    	 	 exp.exportTable(table, new File(fileNameOutput));
				     }
				    
				    

				    }

				    catch (IOException ex) {

				     System.out.println(ex.getMessage());

				     ex.printStackTrace();

				    }

				  popup.add(save);

			}
		});
		btnNewButton.setBounds(1594, 626, 150, 89);
		add(btnNewButton);
		
		FF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(FF.isSelected())
					{
						IF.setSelected(false);
						FireIce=26;
						splitFireIce=25;
						findstr="MTAXF";
					}
				else
				{
					IF.setSelected(true);
					FireIce=40;
					splitFireIce=40;
				}
				
			}
		});
		IF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(IF.isSelected())
				{
					FF.setSelected(false);
					FireIce=40;
					findstr="MSAXF";
					splitFireIce=40;
				}
				else
				{
					FF.setSelected(true);
					FireIce=26;
					
				}
			}
		});
		
		
		
		
		
		}
	private int numberduplication(String fullString,String realDuplicates)
	{
		int searchIndex=0;
		int numberChecker=0;
	
		do{
			searchIndex=fullString.indexOf(realDuplicates, searchIndex);
			if(searchIndex!=-1)
			{
				numberChecker++;
				searchIndex+=realDuplicates.length();
			}
			
		}while(searchIndex!=-1);
		return numberChecker;
		
		
	}
	private String searchFunction(String defineID,String fullString)
	{
		int index=0;
		String flexIdLength="";
		String organisedString="";
		do
		{
			index=fullString.indexOf(defineID,index);
			
			//System.out.println(flexIdLength);
			//System.out.println(fullString);
	//		System.out.println(fullString.length()-1-index);
			if((fullString.length()-index-1)>=34&&index!=-1)
			{
				//System.out.println(fullString.length()-1-index);
				flexIdLength=fullString.substring(index,index+34);
				
					organisedString+=flexIdLength+"\n";
					fullString=fullString.replaceAll(flexIdLength,"");
					index=0;
				
				
			}
		
				
		//	System.out.println(index);
		}while(index!=-1);
		
		return organisedString;
	}
}

