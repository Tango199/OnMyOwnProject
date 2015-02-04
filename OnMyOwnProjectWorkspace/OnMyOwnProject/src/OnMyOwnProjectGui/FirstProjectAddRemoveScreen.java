package OnMyOwnProjectGui;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import Core.ProblemInfo;
import OnMyOwnProjectSchoolSelectionProgram.OnMyOwnProjectSchoolSelectionProgram;

public class FirstProjectAddRemoveScreen 
{
	boolean alreadyDisplayAdd = false;
	boolean alreadyDisplayRemove = false;
	public FirstProjectAddRemoveScreen()
	{
		final JFrame theFrame = new JFrame();
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int widthOfScreen = gd.getDisplayMode().getWidth();
		int heightOfScreen = gd.getDisplayMode().getHeight();
		theFrame.setTitle("First Project Screen");
		theFrame.setSize(widthOfScreen-50,heightOfScreen-50);
		theFrame.setLocation(550, 400);
		
		final JPanel panel = new JPanel(new GridLayout(7,2));
		
		JRadioButton addRadioButton = new JRadioButton("Add Student");
	
		JRadioButton removeRadioButton = new JRadioButton("Remove Student");
		ButtonGroup groupButton = new ButtonGroup();
		groupButton.add(addRadioButton);
		groupButton.add(removeRadioButton);
		final JButton firstProgramButton = new JButton("Run School Selecting Program");
		panel.add(addRadioButton);
		panel.add(removeRadioButton);
		//add stuff
		final JLabel studentNameLabel = new JLabel("Student Name");
		final JTextField studentNameField = new JTextField(25);
		final JLabel studentFirstPlacementLabel = new JLabel("First Choice");
		final JTextField firstPlacementField = new JTextField(50);
		final JLabel studentSecondPlacementLabel = new JLabel("Second Choice");
		final JTextField secondPlacementField = new JTextField(50);
		final JLabel studentThirdPlacementLabel = new JLabel("Third Choice");
		final JTextField thirdPlacementField = new JTextField(50);
		final JButton addStudent = new JButton("Add Student");
		final JLabel space1 = new JLabel("");
		
		//remove stuff
		final JLabel studentNameLabelRemove = new JLabel("Student Name to remove");
		final JTextField studentNameFieldToRemove = new JTextField(25);
		//studentNameFieldToRemove.setBackground(Color.BLUE);
		final JButton removeStudent = new JButton("Remove Student");
		final JLabel space2 = new JLabel("");
		final JLabel space3 = new JLabel("");
		final JLabel space4 = new JLabel("");
		final JLabel space5 = new JLabel("");
		final JLabel space6 = new JLabel("");
		final JLabel space7 = new JLabel("");
		
		addRadioButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(alreadyDisplayRemove)
				{
					panel.remove(studentNameLabelRemove);
					panel.remove(studentNameFieldToRemove);
					panel.remove(space1);
					panel.remove(removeStudent);
					panel.remove(space2);
					panel.remove(space3);
					panel.remove(space4);
					panel.remove(space5);
					panel.remove(space6);
					panel.remove(firstProgramButton);
					panel.revalidate();
					panel.repaint();
					alreadyDisplayRemove = false;
				}
				panel.add(studentNameLabel);
				panel.add(studentNameField);
				panel.add(studentFirstPlacementLabel);
				panel.add(firstPlacementField);
				panel.add(studentSecondPlacementLabel);
				panel.add(secondPlacementField);
				panel.add(studentThirdPlacementLabel);
				panel.add(thirdPlacementField);
				panel.add(space1);
				panel.add(addStudent);
				
				addStudent.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String studentName = studentNameField.getText();
						String firstChoice = firstPlacementField.getText();
						String secondChoice = secondPlacementField.getText();
						String thirdChoice = thirdPlacementField.getText();
						
						studentName = replaceSpace(studentName);
						firstChoice = replaceSpace(firstChoice);
						secondChoice = replaceSpace(secondChoice);
						thirdChoice = replaceSpace(thirdChoice);
						
						addStudentToFile(studentName,firstChoice,secondChoice,thirdChoice);
						addStudentToDatabase(studentName,firstChoice,secondChoice,thirdChoice);
						System.out.println("Added Student");
						studentNameField.setText("");
						firstPlacementField.setText("");
						secondPlacementField.setText("");
						thirdPlacementField.setText("");
						
					}
				});
				
				panel.add(firstProgramButton);
				theFrame.getContentPane().add(panel);
				theFrame.pack();
				theFrame.setVisible(true);
				alreadyDisplayAdd = true;
				
			}
		});
		removeRadioButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(alreadyDisplayAdd)
				{
					panel.remove(studentNameLabel);
					panel.remove(studentNameField);
					panel.remove(studentFirstPlacementLabel);
					panel.remove(firstPlacementField);
					panel.remove(studentSecondPlacementLabel);
					panel.remove(secondPlacementField);
					panel.remove(studentThirdPlacementLabel);
					panel.remove(thirdPlacementField);
					panel.remove(space1);
					panel.remove(addStudent);
					panel.remove(firstProgramButton);
					alreadyDisplayAdd = false;
					
				}
				panel.add(studentNameLabelRemove);
				panel.add(studentNameFieldToRemove);
				panel.add(space1);
				panel.add(removeStudent);
				panel.add(space2);
				panel.add(space3);
				panel.add(space4);
				panel.add(space5);
				panel.add(space6);
				panel.add(firstProgramButton);
				panel.revalidate();
				panel.repaint();
				alreadyDisplayRemove = true;
				
				removeStudent.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String studentName = studentNameFieldToRemove.getText();
						boolean removedFlag = removeStudentFromFile(studentName);
						System.out.println("Removing Student");
					}
				});
				theFrame.pack();
				theFrame.setVisible(true);
			}
		});
		
	
		
		theFrame.getContentPane().add(panel);
		theFrame.pack();
		theFrame.setVisible(true);
		
		firstProgramButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				OnMyOwnProjectSchoolSelectionProgram firstProgram = new OnMyOwnProjectSchoolSelectionProgram();
				
				
			}
		});
		
		
		theFrame.pack();
		theFrame.setVisible(true);
		
		theFrame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
	
	public void addStudentToFile(String name, String firstChoice, String secondChoice, String thirdChoice)
	{
		File studentFile = new File(ProblemInfo.inputPath + "Students.xls");
		
		if(studentFile.exists())
		{
			try
			{
				FileInputStream fileIn = new FileInputStream(new File(ProblemInfo.inputPath + "Students.xls"));
				HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
				HSSFSheet sheet = workbook.getSheetAt(0);
				int rowNum = sheet.getLastRowNum();
				Row row = sheet.createRow(++rowNum);
				Cell cell = row.createCell(0);
				cell.setCellValue(name);
				cell = row.createCell(1);
				cell.setCellValue(firstChoice);
				cell = row.createCell(2);
				cell.setCellValue(secondChoice);
				cell = row.createCell(3);
				cell.setCellValue(thirdChoice);
				
				try {
					FileOutputStream addStudent =
							new FileOutputStream(new File(ProblemInfo.inputPath + "Students.xls"));
					workbook.write(addStudent);
					addStudent.close();


				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			//create file, because it does not exist in the file location 
		}

	}
	public boolean removeStudentFromFile(String name)
	{
		File studentFile = new File(ProblemInfo.inputPath + "Students.xls");
		
		if(studentFile.exists())
		{
			try
			{
				FileInputStream fileIn = new FileInputStream(new File(ProblemInfo.inputPath + "Students.xls"));
				HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
				HSSFSheet sheet = workbook.getSheetAt(0);
				int rowNumIn=0;
				
				for (Row row : sheet)
				{
			        for (Cell cell : row) 
			        {
				        	
			        	if(cell.toString().equals(name))
			        	{
			        		rowNumIn= row.getRowNum();
			        		break;
			        	}
			           
			                	
			        }
				}
				Row rowToRemove = sheet.getRow(rowNumIn);
				sheet.removeRow(rowToRemove);
				
				//shift still isnt working correctly.... especially if it is the last row
				sheet.shiftRows(rowNumIn+1, sheet.getLastRowNum(), -1);
				
			
				
				try {
					FileOutputStream addStudent =
							new FileOutputStream(new File(ProblemInfo.inputPath + "Students.xls"));
					workbook.write(addStudent);
					addStudent.close();


				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			//create file, because it does not exist in the file location 
		}

		
		
		return true;
	}
	public String replaceSpace(String stringToReplace)
	{
		String replacedString = stringToReplace.replace(" ", "_");
		return replacedString;
	}
	public void addStudentToDatabase(String name, String firstChoice, String secondChoice,String thirdChoice)
	{
		Connection addStudentConnection = null;
		try 
		{
			addStudentConnection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/placeStudentsEducation","root", "test!"); //root! at sru test! at home
			//.getConnection("jdbc:mysql://localhost:3306/placeStudentsEducation","root", "root!"); //root! at sru test! at home
		} 
		catch (SQLException e) 
		{
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (addStudentConnection != null) 
		{
			
			
			try {
				
				String sqlAdd = " INSERT INTO STUDENTS(name,firstChoice,secondChoice,thirdChoice)"
						+ "VALUES(?,?,?,?)";
				
				//prepared statements used to prevent sql injection
				java.sql.PreparedStatement preparedAddStatement = addStudentConnection.prepareStatement(sqlAdd);
				preparedAddStatement.setString(1,name);
				preparedAddStatement.setString(2, firstChoice);
				preparedAddStatement.setString(3,secondChoice);
				preparedAddStatement.setString(4,thirdChoice);
				preparedAddStatement.execute();
				System.out.println("Added "+name+" to the student table");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		if(addStudentConnection != null)
		{
			try {
				addStudentConnection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}		
	}
}




