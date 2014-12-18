package OnMyOwnProjectSchoolSelectionProgram;

public class School {

	private String schoolName;
	private int numStudentsPerSchool;
	
	public School()
	{
		
	}
	public School(String schoolName, int numStudentsPerSchool)
	{
		this.schoolName = schoolName;
		this.numStudentsPerSchool = numStudentsPerSchool;
	}
	
	
	public String getSchoolName()
	{
		return schoolName;
	}
	public void setSchoolName(String name)
	{
		schoolName = name;
	}
	
	public int getNumStudentsPerSchool()
	{
		return numStudentsPerSchool;
	}
	public void setNumStudentsPerSchool(int number)
	{
		numStudentsPerSchool = number;
	}
}
