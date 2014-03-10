import java.io.*;

public class JavaCompiler
{
	public static void main(String[] args)
	{
		try
		{
			String current = new File( "." ).getCanonicalPath();
			
			String sourcePath = current + "/sources.txt";
			String jarPath = current + "/jars.txt";
			File jarFiles = new File(jarPath);		
			if(!jarFiles.exists())
				System.out.println("The jars.txt cannot be found. Are you sure it generated correctlly");
			
			File sources = new File(sourcePath);
			if(!sources.exists())
				System.out.println("The sources.txt cannot be found. Are you sure it generated correclty?");

			if(!jarFiles.exists() && !sources.exists())
				throw new Exception("FIles do not exist");	

			String content = createClassPath(jarFiles);
			PrintWriter out = new PrintWriter("jarSource.txt");
			out.println(content);
			out.close();
			System.out.println("Content of jar file: " +content);
		}
		catch(Exception e)
		{ System.out.println("Exception: " + e.getMessage()); }
	}

	private static String createClassPath(File file)
	{
		String result = "";
		try
		{
			boolean first = true;

			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) 
			{
   				if(!first)
					result += ":";

				result += line;
				first = false;
			}
			br.close();
		}catch(Exception e){}
		return result;
	}
}
