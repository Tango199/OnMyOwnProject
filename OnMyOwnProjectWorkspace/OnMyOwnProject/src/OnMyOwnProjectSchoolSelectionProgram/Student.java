package OnMyOwnProjectSchoolSelectionProgram;

public class Student {

	private String nameOfStudent;
	private String firstChoice;
	private String secondChoice;
	private String thirdChoice;
	
	public Student()
	{
		
	}
	public Student(String nameOfStudent, String firstChoice, String secondChoice, String thirdChoice)
	{
		this.nameOfStudent = nameOfStudent;
		this.firstChoice = firstChoice;
		this.secondChoice = secondChoice;
		this.thirdChoice = thirdChoice;
	}
	
	public String getStudentName()
	{
		return nameOfStudent;
	}
	public void setStudentName(String name)
	{
		nameOfStudent = name;
	}
	public String getStudentsSecondChoice()
	{
		return secondChoice;
	}
	public void setStudentSecondChoice(String choice)
	{
		secondChoice = choice;
	}
	public String getStudentThirdChoice()
	{
		return thirdChoice;
	}
	public void setStudentThirdChoice(String choice)
	{
		thirdChoice = choice;
	}
	
}
