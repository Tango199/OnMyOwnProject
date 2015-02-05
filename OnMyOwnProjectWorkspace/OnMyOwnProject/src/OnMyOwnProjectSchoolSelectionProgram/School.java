package OnMyOwnProjectSchoolSelectionProgram;

public class School {

	private String schoolName;
	private int numStudentsPerSchool;
	private int numStudentsAssigned;
	private String region;
	
	public School()
	{
		
	}
	public School(String schoolName, int numStudentsPerSchool)
	{
		this.schoolName = schoolName;
		this.numStudentsPerSchool = numStudentsPerSchool;
		numStudentsAssigned =0;
	}
	public School(String schoolName, int numStudentsPerSchool,String region)
	{
		this.schoolName = schoolName;
		this.numStudentsPerSchool = numStudentsPerSchool;
		this.region = region;
	}
	
	public String getRegion()
	{
		return region;
	}
	public void setRegion(String region)
	{
		this.region = region;
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
