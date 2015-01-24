package Core;

public class ProblemInfo {

	public static String workingDirectory = System.getProperty("user.dir");
	public static String inputPath = workingDirectory + "\\data\\input\\";
	public static String outputPath = workingDirectory + "\\data\\output\\";
	public static int numSchools;
	public static int numStudents;
	public static int totalNumPlacements;
	
	public static void print(Object printThis)
	{
		System.out.println(printThis);
		
	}
}
