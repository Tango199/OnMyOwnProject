package OnMyOwnProjectSchoolSelectionProgram;

public class School {

	private String schoolName;
	private int numStudentsPerSchool;
	private int numStudentsAssigned;
	
	public School()
	{
		
	}
	public School(String schoolName, int numStudentsPerSchool)
	{
		this.schoolName = schoolName;
		this.numStudentsPerSchool = numStudentsPerSchool;
		numStudentsAssigned =0;
	}
	
	public int getNumStudentsAssigned()
	{
		return numStudentsAssigned;
	}
	public void addOneToNumStudentsAssigned()
	{
		numStudentsAssigned++;
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
	public void minusOneStudentFromSchoolCounter()
	{
		numStudentsPerSchool--;
	}
	public void addOneStudentFromSchoolCounter()
	{
		numStudentsPerSchool++;
	}
}
