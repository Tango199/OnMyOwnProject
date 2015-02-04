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
import java.sql.ResultSet;
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

import com.mysql.jdbc.PreparedStatement;

import Core.ProblemInfo;
import OnMyOwnProjectSchoolSelectionProgram.OnMyOwnProjectSchoolSelectionProgram;

public class FirstProjectAddRemoveScreen 
{
	boolean alreadyDisplayAddStudent = false;
	boolean alreadyDisplayRemoveStudent = false;
	boolean alreadyDisplayAddSchool = false;
	boolean alreadyDisplayRemoveSchool = false;
	
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
		
		//TODO fix the formatting of the gui,  the two new radio buttons messed with the formatting
		JRadioButton addStudentRadioButton = new JRadioButton("Add Student");
		JRadioButton removeStudentRadioButton = new JRadioButton("Remove Student");
		JRadioButton addSchoolRadioButton = new JRadioButton("Add School");
		JRadioButton removeSchoolRadioButton = new JRadioButton("Remove School");
		
		ButtonGroup groupButton = new ButtonGroup();
		groupButton.add(addStudentRadioButton);
		groupButton.add(removeStudentRadioButton);
		groupButton.add(addSchoolRadioButton);
		groupButton.add(removeSchoolRadioButton);
		final JButton firstProgramButton = new JButton("Run School Selecting Program");
		panel.add(addStudentRadioButton);
		panel.add(addSchoolRadioButton);
		panel.add(removeStudentRadioButton);
		panel.add(removeSchoolRadioButton);
		//add student stuff
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
		
		//remove student stuff
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
		final JLabel space8 = new JLabel("");
		
		//add school stuff
		final JLabel schoolToAddLabel = new JLabel("School to Add");
		final JTextField schoolNameFieldToAdd = new JTextField(45);
		final JLabel numberOfPlacementsLabel = new JLabel("Number of placements offered");
		final JTextField numberOfPlacementsField = new JTextField(5);
		final JButton addSchool = new JButton("Add School");
		
		//remove school stuff
		final JLabel schoolToRemoveLabel = new JLabel("School to Remove");
		final JTextField schoolNameFieldToRemove = new JTextField(45);
		final JButton removeSchoolButton = new JButton("Remove School");
		
		addSchoolRadioButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(alreadyDisplayRemoveStudent)
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
					alreadyDisplayRemoveStudent = false;
				}
				else if(alreadyDisplayAddStudent)
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
					panel.revalidate();
					panel.repaint();
					alreadyDisplayAddStudent = false;
				}
				else if(alreadyDisplayRemoveSchool)
				{
					panel.remove(schoolToRemoveLabel);
					panel.remove(schoolNameFieldToRemove);
					panel.remove(removeSchoolButton);
					panel.remove(space7);
					panel.remove(space8);
					panel.remove(space2);
					panel.remove(space3);
					panel.remove(space4);
					panel.remove(space5);
					panel.remove(space6);
					
					panel.remove(firstProgramButton);
					panel.revalidate();
					panel.repaint();
					alreadyDisplayRemoveSchool=false;
				}
				
				//set the add school stuff
				panel.add(schoolToAddLabel);
				panel.add(schoolNameFieldToAdd);
				panel.add(numberOfPlacementsLabel);
				panel.add(numberOfPlacementsField);
				panel.add(addSchool);
				addSchool.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String schoolToAdd = schoolNameFieldToAdd.getText();
						schoolToAdd = replaceSpace(schoolToAdd);
						int numSchoolPlacements = Integer.parseInt(numberOfPlacementsField.getText());
						boolean addSchoolFlag=addSchoolToDatabase(schoolToAdd,numSchoolPlacements);
						if(addSchoolFlag)
						{
							System.out.println("School added");
							
						}
						else
						{
							System.out.println("Could not add school");
						}
					}
				});
				panel.add(space2);
				panel.add(space3);
				panel.add(space4);
				panel.add(space5);
				panel.add(space6);
				
				panel.add(firstProgramButton);
				theFrame.getContentPane().add(panel);
				theFrame.pack();
				theFrame.setVisible(true);
				alreadyDisplayAddSchool = true;
			}
		});
		removeSchoolRadioButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(alreadyDisplayRemoveStudent)
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
					alreadyDisplayRemoveStudent = false;
				}
				else if(alreadyDisplayAddStudent)
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
					panel.revalidate();
					panel.repaint();
					alreadyDisplayAddStudent = false;
				}
				else if(alreadyDisplayAddSchool)
				{
					panel.remove(schoolToAddLabel);
					panel.remove(schoolNameFieldToAdd);
					panel.remove(numberOfPlacementsLabel);
					panel.remove(numberOfPlacementsField);
					panel.remove(addSchool);
					panel.remove(space2);
					panel.remove(space3);
					panel.remove(space4);
					panel.remove(space5);
					panel.remove(space6);
					
					panel.remove(firstProgramButton);
					panel.revalidate();
					panel.repaint();
					alreadyDisplayAddSchool=false;
				}
				
				
				panel.add(schoolToRemoveLabel);
				panel.add(schoolNameFieldToRemove);
				panel.add(removeSchoolButton);
				removeSchoolButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String schoolToRemove = schoolNameFieldToRemove.getText();
						schoolToRemove = replaceSpace(schoolToRemove);
						boolean addSchoolFlag=removeSchoolFromDatabase(schoolToRemove);
						if(addSchoolFlag)
						{
							System.out.println("School removed");
							
						}
						else
						{
							System.out.println("Could not remove school");
						}
					}
				});
				panel.add(space7);
				panel.add(space8);
				panel.add(space2);
				panel.add(space3);
				panel.add(space4);
				panel.add(space5);
				panel.add(space6);
				
				panel.add(firstProgramButton);
				theFrame.getContentPane().add(panel);
				theFrame.pack();
				theFrame.setVisible(true);
				alreadyDisplayRemoveSchool = true;
			}
		});
		addStudentRadioButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(alreadyDisplayRemoveStudent)
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
					alreadyDisplayRemoveStudent = false;
				}
				else if(alreadyDisplayRemoveSchool)
				{
					panel.remove(schoolToRemoveLabel);
					panel.remove(schoolNameFieldToRemove);
					panel.remove(removeSchoolButton);
					panel.remove(space7);
					panel.remove(space8);
					panel.remove(space2);
					panel.remove(space3);
					panel.remove(space4);
					panel.remove(space5);
					panel.remove(space6);
					
					panel.remove(firstProgramButton);
					panel.revalidate();
					panel.repaint();
					alreadyDisplayRemoveSchool=false;
				}
				else if(alreadyDisplayAddSchool)
				{
					panel.remove(schoolToAddLabel);
					panel.remove(schoolNameFieldToAdd);
					panel.remove(numberOfPlacementsLabel);
					panel.remove(numberOfPlacementsField);
					panel.remove(addSchool);
					panel.remove(space2);
					panel.remove(space3);
					panel.remove(space4);
					panel.remove(space5);
					panel.remove(space6);
					
					panel.remove(firstProgramButton);
					panel.revalidate();
					panel.repaint();
					alreadyDisplayAddSchool=false;
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
				alreadyDisplayAddStudent = true;
				
			}
		});
		removeStudentRadioButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(alreadyDisplayAddStudent)
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
					panel.revalidate();
					panel.repaint();
					alreadyDisplayAddStudent = false;
					
				}
				else if(alreadyDisplayAddSchool)
				{
					panel.remove(schoolToAddLabel);
					panel.remove(schoolNameFieldToAdd);
					panel.remove(numberOfPlacementsLabel);
					panel.remove(numberOfPlacementsField);
					panel.remove(addSchool);
					panel.remove(space2);
					panel.remove(space3);
					panel.remove(space4);
					panel.remove(space5);
					panel.remove(space6);
					
					panel.remove(firstProgramButton);
					panel.revalidate();
					panel.repaint();
					alreadyDisplayAddSchool=false;
				}
				else if(alreadyDisplayRemoveSchool)
				{
					panel.remove(schoolToRemoveLabel);
					panel.remove(schoolNameFieldToRemove);
					panel.remove(removeSchoolButton);
					panel.remove(space7);
					panel.remove(space8);
					panel.remove(space2);
					panel.remove(space3);
					panel.remove(space4);
					panel.remove(space5);
					panel.remove(space6);
					
					panel.remove(firstProgramButton);
					panel.revalidate();
					panel.repaint();
					alreadyDisplayRemoveSchool=false;
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
				alreadyDisplayRemoveStudent = true;
				
				removeStudent.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						String studentName = studentNameFieldToRemove.getText();
						studentName=replaceSpace(studentName);
						//boolean removedFlag = removeStudentFromFile(studentName); TODO removeFromFile isnt working
						boolean removeFromDB = removeStudentFromDB(studentName);
						if(removeFromDB)
						{
							System.out.println("Student Removed from database");
						}
						else
						{
							System.out.println("Student did not exist in the database");
						}

						studentNameFieldToRemove.setText("");
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
	public boolean removeStudentFromDB(String name)
	{
		Connection removeStudentConnection = null;
		boolean removeFlag=false;
		try 
		{
			removeStudentConnection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/placeStudentsEducation","root", "test!"); //root! at sru test! at home
			//.getConnection("jdbc:mysql://localhost:3306/placeStudentsEducation","root", "root!"); //root! at sru test! at home
		} 
		catch (SQLException e) 
		{
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			
		}

		if (removeStudentConnection != null) 
		{
			
			
			try {
				
	
				String sqlRemove = " DELETE FROM STUDENTS WHERE name =?";
				
				//prepared statements used to prevent sql injection
				java.sql.PreparedStatement preparedRemoveStatement = removeStudentConnection.prepareStatement(sqlRemove);
				preparedRemoveStatement.setString(1,name);
				int testIfDeleted = preparedRemoveStatement.executeUpdate();
				if(testIfDeleted== 1)
				{
					removeFlag = true;
				}
				else
				{
					removeFlag = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		if(removeStudentConnection != null)
		{
			try {
				removeStudentConnection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}		
		return removeFlag;
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
		//TODO return if it is successful or not
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
	public boolean addSchoolToDatabase(String schoolName, int numStudents)
	{
		Connection addSchoolConnection = null;
		boolean addSchoolFlag = false;
		try 
		{
			addSchoolConnection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/placeStudentsEducation","root", "test!"); //root! at sru test! at home
			//.getConnection("jdbc:mysql://localhost:3306/placeStudentsEducation","root", "root!"); //root! at sru test! at home
		} 
		catch (SQLException e) 
		{
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			
		}

		if (addSchoolConnection != null) 
		{
			
			try {
				
				String sqlAddSchool = " INSERT INTO SCHOOLS(schoolName,numStudentsRecieving)"
						+ "VALUES(?,?)";
				
				//prepared statements used to prevent sql injection
				java.sql.PreparedStatement preparedAddSchoolStatement = addSchoolConnection.prepareStatement(sqlAddSchool);
				preparedAddSchoolStatement .setString(1,schoolName);
				preparedAddSchoolStatement .setInt(2, numStudents);
				int testIfAdded = preparedAddSchoolStatement.executeUpdate();
				if(testIfAdded== 1)
				{
					addSchoolFlag = true;
				}
				else
				{
					addSchoolFlag = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		if(addSchoolConnection != null)
		{
			try {
				addSchoolConnection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}		
		return addSchoolFlag;
		
	}
	public boolean removeSchoolFromDatabase(String schoolName)
	{
		boolean removeSchoolFlag = false;
		Connection removeSchoolConnection = null;
		try 
		{
			removeSchoolConnection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/placeStudentsEducation","root", "test!"); //root! at sru test! at home
			//.getConnection("jdbc:mysql://localhost:3306/placeStudentsEducation","root", "root!"); //root! at sru test! at home
		} 
		catch (SQLException e) 
		{
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			
		}

		if (removeSchoolConnection != null) 
		{
			
			try {
				
				String sqlRemoveSchool = " DELETE FROM SCHOOLS WHERE schoolName =?";
				
				//prepared statements used to prevent sql injection
				java.sql.PreparedStatement preparedRemoveSchoolStatement = removeSchoolConnection.prepareStatement(sqlRemoveSchool);
				preparedRemoveSchoolStatement .setString(1,schoolName);
				int testIfDeleted = preparedRemoveSchoolStatement.executeUpdate();
				if(testIfDeleted== 1)
				{
					removeSchoolFlag = true;
				}
				else
				{
					removeSchoolFlag = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		if(removeSchoolConnection != null)
		{
			try {
				removeSchoolConnection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}		
		
		
		return removeSchoolFlag;
	}
}




