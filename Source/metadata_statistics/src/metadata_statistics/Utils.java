package metadata_statistics;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import org.jdom.Document;
import org.jdom.Element;

public class Utils 
{
	public static Hashtable<String, Template> tableTemplate = new Hashtable <String,Template>();
	public static Hashtable<String, Integer> globalRep= new Hashtable<String, Integer>();
	public static Hashtable<String, Integer> globalAtts= new Hashtable<String, Integer>();
	public static Hashtable<String, Integer> tableFr = new Hashtable <String,Integer>();
	public static Hashtable<String, Integer> tableElements = new Hashtable <String, Integer>();
	public static Hashtable<String, Hashtable<String, Integer>> table = new Hashtable <String, Hashtable <String, Integer>>();
	
	public void putTemplate (String name, Template tmp)
	{
		try
		{
			tableTemplate.put(name, tmp);
		}
		catch (Exception e)
		{
			writeLogFich (e, "utils.putTemplate");
		}
	}
	
	public void putAtts (String name, Integer count)
	{
		try
		{
			globalAtts.put(name, count);
		}
		catch (Exception e)
		{
			writeLogFich (e, "utils.putAtts");
		}
	}
	
	public void putGlobalRep (String name, Integer count)
	{
		try
		{
			globalRep.put(name, count);
		}
		catch (Exception e)
		{
			writeLogFich (e, "utils.putGlobalRep");
		}
	}
	
	public Hashtable<String, Template> getTableTemple ()
	{
		return tableTemplate;
	}
	
	public Hashtable<String, Integer> getAttTable ()
	{
		return globalAtts;
	}
	
	public Hashtable<String, Integer> getGlobalRep ()
	{
		return globalRep;
	}
	
    public static Object[] getTableTemplate() 
    {
        Object peraux[];
        peraux = tableTemplate.values().toArray();
        return peraux;
    }
    
    public static Object[] getTableAtts() 
    {
        Object peraux[];
        peraux = globalAtts.values().toArray();
        return peraux;
    }
    
    public static Object[] getTableGlobal() 
    {
        Object peraux[];
        peraux = globalRep.values().toArray();
        return peraux;
    }
    
    public static int getRandom ()
    {
    	int number = 0;
    	
    	Random rnd = new Random ();
    	number = rnd.nextInt(100000000);
    	return number;
    }
    /*
     * Delete a template element from tableTemplate
     */
    public void deleteTemplate (String uid)
	{
		  try
		 {
			  tableTemplate.remove(uid);
		 }
		 catch(Exception e)
		 {
			 writeLogFich (e, "utils.deleteTemplate");
		 }
	}
    
    /*
     * Saves a template element into tableTemplate
     */
    public void saveData (Template tmp)
    {
         try
        {
        	 tableTemplate.put(tmp.getUid(), tmp);
        }
        catch(Exception e)
        {
        	writeLogFich (e, "utils.saveData");
        }
    }
    
	/*
     * This method returns execution time in seconds.
     */
    public static long getExecutionTime (long time_start, long time_end)
    {
    	long time;   	
    	time = time_end - time_start;
    	time = time / 1000; //seconds
    	return time;
    }
    
    /*
     * Method that print a error message in a text file.
     */
    public void writeLogFich (Exception ex, String voids)
    {
    	Writer out = null;
		try 
		{
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("log.txt",true), "UTF-8"));
			out.write("Error: " + ex.getCause() + "    .Method: " + voids + "\n");
		    out.close();
		}
		catch (IOException e) 
		{
			System.out.println("Error in writeLogFich. Error during saving message error.");
		}
		finally
		{
			out = null;
		}
    }
    
    public String getXpathChildrenRoot (Document doc)
    {
    	Element root = null;
    	List<Element> nodes = null;
    	String xpath = null;
    	
    	try
    	{
    		root = doc.getRootElement();
			nodes = root.getChildren();
			if (root.getNamespacePrefix() != null || root.getNamespacePrefix() != "")
			{
				xpath = "/" + root.getNamespacePrefix() + ":" + root.getName() + "/";
			}
			else
			{
				xpath = "/" + root.getName() + "/";
			}
			if (nodes.size() > 0)
			{
				if (nodes.get(1).getNamespacePrefix() != null || nodes.get(1).getNamespacePrefix() != "")
				{
					xpath += nodes.get(1).getNamespacePrefix() + ":" + nodes.get(1).getName();
				}
			}
    		return xpath;
    	}
    	catch (Exception ex)
    	{
    		writeLogFich (ex, "utils.getXpathChildrenRoot");
    		return xpath;
    	}
    }
    
    public void FiletoCsv ()
    {
    	
    }
}
