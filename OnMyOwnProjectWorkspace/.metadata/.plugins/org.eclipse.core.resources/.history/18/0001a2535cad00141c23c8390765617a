package OnMyOwnProjectSchoolSelectionProgram;

import java.awt.Color;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

import Core.ProblemInfo;

public class OnMyOwnProjectSchoolSelectionProgram 
{
	List<Student> students = new ArrayList<Student>();
	List<School> schools = new ArrayList<School>();
	List<Student> stuckStudents = new ArrayList<Student>();
	
	public OnMyOwnProjectSchoolSelectionProgram()
	{
		
		
		
			readInSchoolsFromDatabase();
			readInStudentsDatabase();
			//readInSchoolsExcel();
			//readInStudentsExcel();
		
		
		boolean possible = checkIfPossible();
		//writeInputOut();
		if(possible)
		{
			assignStudents();
			assignStuckKids();
			printShortResults();
			printLongResults();
		}
		else
		{
			System.out.println("Need more placements!");
		}
		
		
	}
	public boolean checkIfPossible()
	{
		if(ProblemInfo.numStudents > ProblemInfo.totalNumPlacements)
		{
			System.out.println("Huston we have a problem");
			return false;
		}
		else
		return true;
		
	}
	public void assignStudents()
	{
		int randNum;
		String firstChoice;
		String secondChoice;
		String thirdChoice;
		boolean done = false;
		
		while (!done)
		{
			randNum = getRandomNumber(ProblemInfo.numStudents-1,0);
			if(students.get(randNum).isAssigned())
			{
				//if the rand number chosen is already routed
			}
			else
			{
				firstChoice = students.get(randNum).getStudentsFirstChoice();
				secondChoice = students.get(randNum).getStudentsSecondChoice();
				thirdChoice = students.get(randNum).getStudentsThirdChoice();
				
				for(int i=0; i < ProblemInfo.numSchools;i++)
				{
					if(firstChoice.equals(schools.get(i).getSchoolName()))
					{
						if(schools.get(i).getNumStudentsPerSchool() > 0)
						{
							students.get(randNum).assign();
							students.get(randNum).setAssignedSchool(schools.get(i).getSchoolName());
							schools.get(i).minusOneStudentFromSchoolCounter();
							schools.get(i).addOneToNumStudentsAssigned();
							ProblemInfo.print(students.get(randNum).getStudentsName() + " was placed in " + schools.get(i).getSchoolName() + " as their first choice");
							break;
						}
						else
						{
							//student didnt get their first choice
							ProblemInfo.print(students.get(randNum).getStudentsName() + "didnt get his first choice");
							for(int q=0; q<ProblemInfo.numSchools;q++)
							{
								if(secondChoice.equals(schools.get(q).getSchoolName()))
								{
									if(schools.get(q).getNumStudentsPerSchool() > 0)
									{
										
										students.get(randNum).assign();
										students.get(randNum).setAssignedSchool(schools.get(q).getSchoolName());
										schools.get(q).minusOneStudentFromSchoolCounter();
										schools.get(q).addOneToNumStudentsAssigned();
										ProblemInfo.print(students.get(randNum).getStudentsName() + " was placed in " + schools.get(q).getSchoolName() + " as their second choice");
										break;
									}
									else
									{
										ProblemInfo.print(students.get(randNum).getStudentsName() + "didnt get his second choice");
										for(int r =0; r < ProblemInfo.numSchools; r++)
										{
											if(thirdChoice.equals(schools.get(r).getSchoolName()))
											{
												if(schools.get(r).getNumStudentsPerSchool() > 0)
												{
													students.get(randNum).assign();
													students.get(randNum).setAssignedSchool(schools.get(r).getSchoolName());
													schools.get(r).minusOneStudentFromSchoolCounter();
													schools.get(q).addOneToNumStudentsAssigned();
													ProblemInfo.print(students.get(randNum).getStudentsName() + " was placed in " + schools.get(r).getSchoolName() + " as their third choice");
													break;
												}
												else
												{
													ProblemInfo.print(students.get(randNum).getStudentsName() +" didnt get his third choice");
													stuckStudents.add(students.get(randNum));
													students.get(randNum).assign();
												}
											}
										}
									}
								}
							}
						
						}
						
					}

				}
			}
			
			int studentCounter=0;
			for(int i=0;i<ProblemInfo.numStudents;i++)
			{
				
				if(!students.get(i).isAssigned())
				{
					done = false;
					studentCounter++;
				}
				else
				{
					//done = true;
					
				}
			}
			
			if(studentCounter == 0)
			{
				done = true;
			}
		}
		
	}
	public void assignStuckKids()
	{
		int randomStuckStudent=0;
		int randomSchoolForStuckStudent=0;
		while(stuckStudents.size()>0)
		{
			randomStuckStudent=getRandomNumber(stuckStudents.size()-1,0);
			randomSchoolForStuckStudent=getRandomNumber(ProblemInfo.numSchools-1,0);
			
			while(schools.get(randomSchoolForStuckStudent).getNumStudentsPerSchool()==0)
			{
				randomSchoolForStuckStudent=getRandomNumber(ProblemInfo.numSchools-1,0);
			}
			stuckStudents.get(randomStuckStudent).setAssignedSchool(schools.get(randomSchoolForStuckStudent).getSchoolName());
			schools.get(randomSchoolForStuckStudent).minusOneStudentFromSchoolCounter();
			schools.get(randomSchoolForStuckStudent).addOneToNumStudentsAssigned();
			ProblemInfo.print(stuckStudents.get(randomStuckStudent).getStudentsName() + " was assigned to " + schools.get(randomSchoolForStuckStudent).getSchoolName());
			stuckStudents.remove(randomStuckStudent);
		}
	}
	public void readInStudentsDatabase()
	{
		Connection readStudentsTableConnection = null;
		try 
		{
			readStudentsTableConnection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/placeStudentsEducation","root", "test!"); //root! at sru test! at home
			//.getConnection("jdbc:mysql://localhost:3306/placeStudentsEducation","root", "root!"); //root! at sru test! at home
		} 
		catch (SQLException e) 
		{
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (readStudentsTableConnection != null) 
		{
			String sqlGetStudents = "SELECT * FROM STUDENTS";
			String sqlNumStudents = "SELECT COUNT(*) FROM STUDENTS";
			try
			{
				java.sql.PreparedStatement getNumStudents = readStudentsTableConnection.prepareStatement(sqlNumStudents);
				ResultSet numStudentsSet = getNumStudents.executeQuery();
				while(numStudentsSet.next())
				{
					ProblemInfo.numStudents = numStudentsSet.getInt(1);
				}
				java.sql.PreparedStatement getStudents = readStudentsTableConnection.prepareStatement(sqlGetStudents);
				ResultSet studentsSet = getStudents.executeQuery();
				while(studentsSet.next())
				{
					String nameOfStudent = studentsSet.getString("name");
					String firstRegion = studentsSet.getString("firstRegion");
					String secondRegion = studentsSet.getString("secondRegion");
					String thirdRegion = studentsSet.getString("thridRegion");
					String fourthRegion = studentsSet.getString("fourthRegion");
					String fifthRegion = studentsSet.getString("fifthRegion");
					String sixthRegion = studentsSet.getString("sixthRegion");
					
					String firstWildCard = studentsSet.getString("firstWildCard");
					String secondWildCard = studentsSet.getString("secondWildCard");
					String thirdWildCard = studentsSet.getString("thirdWildCard");
					students.add(new Student(nameOfStudent,firstRegion,secondRegion,thirdRegion,fourthRegion,fifthRegion,sixthRegion,firstWildCard,secondWildCard,thirdWildCard));
				}
				studentsSet.close();
				numStudentsSet.close();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try
				{
					readStudentsTableConnection.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void readInSchoolsFromDatabase()
	{
		//TODO this needs tested.... should work but not assuming anything... need a way to insert schools before this can be tested

		Connection readSchoolsTableConnection = null;
		try 
		{
			readSchoolsTableConnection= DriverManager
			.getConnection("jdbc:mysql://localhost:3306/placeStudentsEducation","root", "test!"); //root! at sru test! at home
			//.getConnection("jdbc:mysql://localhost:3306/placeStudentsEducation","root", "root!"); //root! at sru test! at home
		} 
		catch (SQLException e) 
		{
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (readSchoolsTableConnection != null) 
		{
			String sqlGetSchools = "SELECT * FROM SCHOOLS";
			String sqlNumSchools = "SELECT COUNT(*) FROM SCHOOLS";
			int numOverallPlacements=0;
			try
			{
				java.sql.PreparedStatement getNumSchools = readSchoolsTableConnection.prepareStatement(sqlNumSchools);
				ResultSet numSchoolsSet = getNumSchools.executeQuery();
				while(numSchoolsSet.next())
				{
					ProblemInfo.numSchools = numSchoolsSet.getInt(1);
				}
				java.sql.PreparedStatement getSchools = readSchoolsTableConnection.prepareStatement(sqlGetSchools);
				ResultSet schoolsSet = getSchools.executeQuery();
				while(schoolsSet.next())
				{
					String nameOfSchool = schoolsSet.getString("schoolName");
					int numStudentsAccepting= schoolsSet.getInt("numStudentsRecieving");
					String regionOfSchool = schoolsSet.getString("regionOfSchool");
					numOverallPlacements += numStudentsAccepting;
					schools.add(new School(nameOfSchool,numStudentsAccepting,regionOfSchool));
				}
				ProblemInfo.totalNumPlacements=numOverallPlacements;
				schoolsSet.close();
				numSchoolsSet.close();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try
				{
					readSchoolsTableConnection.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	public void readInStudentsExcel() throws IOException
	{
//		try
//		{
//			FileInputStream fileStudent = new FileInputStream(new File(ProblemInfo.inputPath + "Students.xls"));
//			HSSFWorkbook studentWorkbook = new HSSFWorkbook(fileStudent);
//			HSSFSheet studentSheet = studentWorkbook.getSheetAt(0);
//			ProblemInfo.numStudents = studentSheet.getPhysicalNumberOfRows() -1;
//			Cell cell = null;
//			int rowCounter = 1;
//			int colCounter = 0;
//			String nameOfStudent;
//			String firstChoice;
//			String secondChoice;
//			String thirdChoice;
//			
//			for(int i=ProblemInfo.numStudents; i >0; i--)
//			{
//				cell = studentSheet.getRow(rowCounter).getCell(colCounter++);
//				nameOfStudent = cell.toString();
//				cell = studentSheet.getRow(rowCounter).getCell(colCounter++);
//				firstChoice = cell.toString();
//				cell = studentSheet.getRow(rowCounter).getCell(colCounter++);
//				secondChoice = cell.toString();
//				cell = studentSheet.getRow(rowCounter).getCell(colCounter++);
//				thirdChoice = cell.toString();
//				
//				students.add(new Student(nameOfStudent,firstChoice,secondChoice,thirdChoice));
//				rowCounter++;
//				colCounter=0;
//				
//			}
//			
//			fileStudent.close();
//		}
//		catch(FileNotFoundException e){
//			e.printStackTrace();
//		}
//		
	}
	public void readInSchoolsExcel() throws IOException
	{
		try {
			FileInputStream fileSchool = new FileInputStream(new File(ProblemInfo.inputPath + "Schools.xls"));
			HSSFWorkbook schoolWorkbook = new HSSFWorkbook(fileSchool);
			HSSFSheet schoolSheet = schoolWorkbook.getSheetAt(0);
			ProblemInfo.numSchools = schoolSheet.getPhysicalNumberOfRows() -1;
			Cell cell = null;
		
			int rowCountSchools = 1;
			int colCounter = 0;
			String schoolName;
			String tempString;
			int numStudentsPerSchool;
			
		
			for(int numSchools = ProblemInfo.numSchools;numSchools>0;numSchools--)
			{
				cell = schoolSheet.getRow(rowCountSchools).getCell(colCounter++);
				schoolName = cell.toString();
				cell = schoolSheet.getRow(rowCountSchools).getCell(colCounter);
				tempString = cell.toString();
				tempString = tempString.substring(0,tempString.length()-2);
				numStudentsPerSchool = Integer.parseInt(tempString);
				rowCountSchools++;
				colCounter=0;
				ProblemInfo.totalNumPlacements+=numStudentsPerSchool;
				schools.add(new School(schoolName,numStudentsPerSchool));
				
				
			}
		fileSchool.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	public void writeInputOut()
	{
//		HSSFWorkbook printOutWorkBook = new HSSFWorkbook();
//		HSSFSheet printSheet = printOutWorkBook.createSheet("Read In Data");
//		int rowNum =0;
//		Row row = printSheet.createRow(rowNum);
//		Cell cell = row.createCell(0);
//		cell.setCellValue("SCHOOLS");
//		row = printSheet.createRow(++rowNum);
//		cell = row.createCell(0);
//		cell.setCellValue("Name of School");
//		cell=row.createCell(1);
//		cell.setCellValue("Number of students allowed");
//		
//		
//		for(int i=0;i<ProblemInfo.numSchools;i++)
//		{
//			row= printSheet.createRow(++rowNum);
//			cell=row.createCell(0);
//			cell.setCellValue(schools.get(i).getSchoolName());
//			cell=row.createCell(1);
//			cell.setCellValue(schools.get(i).getNumStudentsPerSchool());
//			
//		}
//		
//		row = printSheet.createRow(++rowNum);
//		row = printSheet.createRow(++rowNum);
//		
//		cell = row.createCell(0);
//		cell.setCellValue("STUDENTS");
//		row = printSheet.createRow(++rowNum);
//		cell=row.createCell(0);
//		cell.setCellValue("Student Name");
//		cell=row.createCell(1);
//		cell.setCellValue("Student First Choice");
//		cell=row.createCell(2);
//		cell.setCellValue("Student Second Choice");
//		cell=row.createCell(3);
//		cell.setCellValue("Student Third Choice");
//		
//		for(int i=0;i<ProblemInfo.numStudents;i++)
//		{
//			row = printSheet.createRow(++rowNum);
//			cell=row.createCell(0);
//			cell.setCellValue(students.get(i).getStudentsName());
//			cell = row.createCell(1);
//			cell.setCellValue(students.get(i).getStudentsFirstChoice());
//			cell = row.createCell(2);
//			cell.setCellValue(students.get(i).getStudentsSecondChoice());
//			cell = row.createCell(3);
//			cell.setCellValue(students.get(i).getStudentsThirdChoice());
//		}
//		
//		for(int i=0; i < 6; i++)
//		{
//			printSheet.autoSizeColumn(i);
//		}
//		
//		try {
//			FileOutputStream inputFileOut =
//					new FileOutputStream(new File(ProblemInfo.
//							outputPath + "inputInfoCheck.xls"));
//			printOutWorkBook.write(inputFileOut);
//			inputFileOut.close();
//			ProblemInfo.print("Done Printing out inputInfoCheck.xls");
//
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
		
	}
	public int getRandomNumber(int max, int min)
	{
		int randNum = 0;
		Random rand = new Random();
		randNum = rand.nextInt((max-min)+1)+min;
		
		return randNum;
	}
	public void printShortResults()
	{
		HSSFWorkbook printOutWorkBook = new HSSFWorkbook();
		HSSFSheet printSheet = printOutWorkBook.createSheet("Short Results Data");
		int rowNum =0;
		
		Row row = printSheet.createRow(rowNum);
		Cell cell = row.createCell(0);
		cell.setCellValue("SCHOOLS");
		row=printSheet.createRow(++rowNum);
		row = printSheet.createRow(++rowNum);
		cell = row.createCell(0);
		cell.setCellValue("Name of School");
		cell=row.createCell(1);
		cell.setCellValue("Number of students assigned to school");
		for(int i=0;i<ProblemInfo.numSchools;i++)
		{
			row=printSheet.createRow(++rowNum);
			cell=row.createCell(0);
			cell.setCellValue(schools.get(i).getSchoolName());
			cell = row.createCell(1);
			cell.setCellValue(schools.get(i).getNumStudentsAssigned());
		}
		row=printSheet.createRow(++rowNum);
		row=printSheet.createRow(++rowNum);
		cell=row.createCell(0);
		cell.setCellValue("STUDENTS");
		row=printSheet.createRow(++rowNum);
		row=printSheet.createRow(++rowNum);
		cell.setCellValue("Student Name");
		cell = row.createCell(1);
		cell.setCellValue("Is assigned?");
		
		
		for(int i=0;i<ProblemInfo.numStudents;i++)
		{
			row=printSheet.createRow(++rowNum);
			cell=row.createCell(0);
			cell.setCellValue(students.get(i).getStudentsName());
			cell = row.createCell(1);
			cell.setCellValue(students.get(i).isAssigned());
			
		}
		
		for(int i=0; i < 6; i++)
		{
			printSheet.autoSizeColumn(i);
		}
		
		try {
			FileOutputStream shortFileOut =
					new FileOutputStream(new File(ProblemInfo.
							outputPath + "shortResultsSchoolSelection.xls"));
			printOutWorkBook.write(shortFileOut);
			shortFileOut.close();
			ProblemInfo.print("Done Printing out shortResultsSchoolSelection.xls");


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void printLongResults()
	{
		HSSFWorkbook printOutWorkBook = new HSSFWorkbook();
		HSSFSheet printSheet = printOutWorkBook.createSheet("Short Results Data");
		
		  HSSFCellStyle red_style = printOutWorkBook.createCellStyle();
	        /* We will now specify a background cell color */
	        red_style.setFillPattern(HSSFCellStyle.FINE_DOTS );
	        red_style.setFillForegroundColor(new HSSFColor.RED().getIndex());
	        red_style.setFillBackgroundColor(new HSSFColor.RED().getIndex());
	        
		HSSFCellStyle defaultStyle = printOutWorkBook.createCellStyle();
		defaultStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);
		  defaultStyle.setFillForegroundColor(new HSSFColor.WHITE().getIndex());
	        defaultStyle.setFillBackgroundColor(new HSSFColor.WHITE().getIndex());
	    
	        HSSFCellStyle green_style = printOutWorkBook.createCellStyle();
	        /* We will now specify a background cell color */
	       green_style.setFillPattern(HSSFCellStyle.FINE_DOTS );
	        green_style.setFillForegroundColor(new HSSFColor.GREEN().getIndex());
	        green_style.setFillBackgroundColor(new HSSFColor.GREEN().getIndex());
		
	        HSSFCellStyle light_green_Style = printOutWorkBook.createCellStyle();
	        light_green_Style.setFillPattern(HSSFCellStyle.FINE_DOTS);
	        light_green_Style.setFillForegroundColor(new HSSFColor.LIGHT_GREEN().getIndex());
	        light_green_Style.setFillBackgroundColor(new HSSFColor.LIGHT_GREEN().getIndex());
		        

		        HSSFCellStyle orange_Style = printOutWorkBook.createCellStyle();
		        orange_Style.setFillPattern(HSSFCellStyle.FINE_DOTS);
		        orange_Style.setFillForegroundColor(new HSSFColor.ORANGE().getIndex());
		        orange_Style.setFillBackgroundColor(new HSSFColor.ORANGE().getIndex());
		int rowNum =0;
		
		Row row = printSheet.createRow(rowNum);
		Cell cell = row.createCell(0);
		cell.setCellValue("SCHOOLS");
		row = printSheet.createRow(++rowNum);
		row=printSheet.createRow(++rowNum);
		cell = row.createCell(0);
		cell.setCellValue("Name of School");
		cell=row.createCell(1);
		cell.setCellValue("Number of students assigned to school");
		for(int i=0;i<ProblemInfo.numSchools;i++)
		{
			row=printSheet.createRow(++rowNum);
			cell=row.createCell(0);
			cell.setCellValue(schools.get(i).getSchoolName());
			cell = row.createCell(1);
			cell.setCellValue(schools.get(i).getNumStudentsAssigned());
		}
		row=printSheet.createRow(++rowNum);
		row=printSheet.createRow(++rowNum);
		cell=row.createCell(0);
		cell.setCellValue("STUDENTS");
		row=printSheet.createRow(++rowNum);
		cell = row.createCell(0);
		cell.setCellValue("Student Name");
		cell = row.createCell(1);
		cell.setCellValue("First Choice");
		cell = row.createCell(2);
		cell.setCellValue("Second Choice");
		cell = row.createCell(3);
		cell.setCellValue("Third Choice");
		cell = row.createCell(4);
		cell.setCellValue("Placement");
		
		row=printSheet.createRow(++rowNum);
		for(int i=0;i<ProblemInfo.numStudents;i++)
		{
			row=printSheet.createRow(++rowNum);
			cell=row.createCell(0);
			cell.setCellValue(students.get(i).getStudentsName());
			cell = row.createCell(1);
			cell.setCellValue(students.get(i).getStudentsFirstChoice());
			cell = row.createCell(2);
			cell.setCellValue(students.get(i).getStudentsSecondChoice());
			
			cell = row.createCell(3);
			cell.setCellValue(students.get(i).getStudentsThirdChoice());
			
			cell = row.createCell(4);
			
			  
			cell.setCellValue(students.get(i).getAssignedSchool());
			if(students.get(i).getAssignedSchool().equals(students.get(i).getStudentsFirstChoice()))
			{
				  cell.setCellStyle(green_style);	
			}
			else if(students.get(i).getAssignedSchool().equals(students.get(i).getStudentsSecondChoice()))
			{
				cell.setCellStyle(light_green_Style);
			}
			else if(students.get(i).getAssignedSchool().equals(students.get(i).getStudentsThirdChoice()))
			{
				cell.setCellStyle(orange_Style);
			}
			else	
			  cell.setCellStyle(red_style);
			
			
			
		}
		
		for(int i=0; i < 6; i++)
		{
			printSheet.autoSizeColumn(i);
		}
		
		try {
			FileOutputStream longFileOut =
					new FileOutputStream(new File(ProblemInfo.
							outputPath + "longResultsSchoolSelection.xls"));
			printOutWorkBook.write(longFileOut);
			longFileOut.close();
			ProblemInfo.print("Done Printing out longResultsSchoolSelection.xls");


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
