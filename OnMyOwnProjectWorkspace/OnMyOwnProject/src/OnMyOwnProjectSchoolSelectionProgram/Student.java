package OnMyOwnProjectSchoolSelectionProgram;

public class Student {

	private String nameOfStudent;
	private String firstRegion;
	private String secondRegion;
	private String thirdRegion;
	private String fourthRegion;
	private String fifthRegion;
	private String sixthRegion;
	private String firstWildCard;
	private String secondWildCard;
	private String thirdWildCard;
	
	private boolean isAssigned;
	private String assignedSchool;
	public Student()
	{
		
	}
	/*public Student(String nameOfStudent, String firstChoice, String secondChoice, String thirdChoice)
	{
		this.nameOfStudent = nameOfStudent;
		this.firstRegion = firstChoice;
		this.secondRegion = secondChoice;
		this.thirdRegion = thirdChoice;
		isAssigned = false;
		assignedSchool=null;
	}*/
	public Student (String nameOfStudent, String firstRegion, String secondRegion, String thirdRegion, String fourthRegion, String fifthRegion, String sixthRegion, 
			String firstWildCard, String secondWildCard,String thirdWildCard)
	{
		this.nameOfStudent = nameOfStudent;
		this.firstRegion = firstRegion;
		this.secondRegion = secondRegion;
		this.thirdRegion = thirdRegion;
		this.fourthRegion = fourthRegion;
		this.fifthRegion = fifthRegion;
		this.sixthRegion = sixthRegion;
		this.firstWildCard = firstWildCard;
		this.secondWildCard = secondWildCard;
		this.thirdWildCard = thirdWildCard;
	}
	public String getAssignedSchool()
	{
		return assignedSchool;
	}
	public void setAssignedSchool(String school)
	{
		assignedSchool = school;
	}
	public boolean isAssigned()
	{
		return isAssigned;
	}
	public void assign()
	{
		isAssigned = true;
	}
	public String getStudentsName()
	{
		return nameOfStudent;
	}
	public void setStudentsName(String name)
	{
		nameOfStudent = name;
	}
	public String getStudentsFirstRegion()
	{
		return firstRegion;
	}
	public void setStudentsRegion(String choice)
	{
		firstRegion = choice;
	}
	public String getStudentsSecondRegion()
	{
		return secondRegion;
	}
	public void setStudentsSecondRegion(String choice)
	{
		secondRegion = choice;
	}
	public String getStudentsThirdRegion()
	{
		return thirdRegion;
	}
	public void setStudentsThirdRegion(String choice)
	{
		thirdRegion = choice;
	}
	
	public String getStudentsFourthRegion()
	{
		return fourthRegion;
	}
	public void setStudentsFourthRegion(String choice)
	{
		fourthRegion = choice;
	}
	public String getStudentsFifthRegion()
	{
		return fifthRegion;
	}
	public void setStudentsFifthRegion(String choice)
	{
		fifthRegion = choice;
	}
	public String getStudentsSixthRegion()
	{
		return sixthRegion;
	}
	public void setStudentsSixthRegion(String choice)
	{
		sixthRegion = choice;
	}
	public String getStudentsFirstWildCard()
	{
		return firstWildCard;
	}
	public void setStudentsFirstWildCard(String choice)
	{
		firstWildCard = choice;
	}
	public String getStudentsSecondWildCard()
	{
		return secondWildCard;
	}
	public void setStudentsSecondWildCard(String choice)
	{
		secondWildCard = choice;
	}
	public String getStudentsThirdWildCard()
	{
		return thirdWildCard;
	}
	public void setStudentsThirdWildCard(String choice)
	{
		thirdWildCard = choice;
	}
	
}
