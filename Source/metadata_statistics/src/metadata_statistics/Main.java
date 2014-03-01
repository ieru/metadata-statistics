package metadata_statistics;

public class Main 
{

	public static void main(String[] args) 
	{
		String output = null;
		String template = null;
		String pathXML = args[0];
		
		if (args.length > 2)
		{
			output = args[1];
			template = args[2];
		}
		else 
		{
			if ( args.length == 2)
			{
				output = args[1];
			}
		}
		if (args.length > 0)
		{
			if (pathXML != null)
			{
				if (template != null)
				{
					if (template.contains (".xml"))
					{
						@SuppressWarnings("unused")
						long time_start = System.currentTimeMillis();
						System.out.println("Starting Analysis ...");
						System.out.println ("Loading ...");
						XmlAnalyzer an = new XmlAnalyzer (pathXML,template,output,time_start);
					}
					else
					{
						System.out.println("Introduce a XML template file to analize. Template is not in a correct format.");
					}
				}
				else
				{
					long time_start = System.currentTimeMillis();
					System.out.println("Starting Analysis ...");
					System.out.println ("Loading ...");
					XmlAnalyzer an = new XmlAnalyzer (pathXML,template,output,time_start);
				}
				
			}
			else
			{
				System.out.println("Introduce a XML file to analize.");
				System.out.println("Help: java -jar [jar file] [arg1: Name File] [arg2: Output File] (Optional) [arg3: Template]");
			}
	}
		else
		{
			System.out.println("Help: java -jar [jar file] [arg1: Name File] [arg2: Output File] (Optional) [arg3: Template]");
		}
		
	/*	String pathXML = "E:\\muestra";
		String template = "E:\\template.xml";
		String output = null;
		long time_start = System.currentTimeMillis();
		System.out.println("Starting Analysis ...");
		System.out.println ("Loading ...");
		XmlAnalyzer an = new XmlAnalyzer (pathXML,template,output,time_start);*/
	}

}
