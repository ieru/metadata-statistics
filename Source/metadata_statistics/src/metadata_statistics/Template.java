package metadata_statistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;

public class Template 
{
	private String title;
	private Output output;
	private Element action;
	private String uid;
	private int result, n;
	Date date = new Date ();
	private Utils utils = new Utils ();
	
	
	public Template ()
	{
	}
	/*
	 * Class Constructor
	 * @action Element action type org.jdom.Element
	 * @output Element type output
	 * @uid Unique identifier consisting of the date and a random number
	 */
	public Template (Element action, Output output)
	{
		this.action = action;
		this.output = output;
		this.uid = date.toString() + Utils.getRandom();
	}
	
	/*
	 * Class Constructor
	 * @title The title of the query filter
	 * @action Element action type org.jdom.Element
	 * @output Element type output
	 * @uid Unique identifier consisting of the date and a random number
	 */
	public Template (String title, Element action, int result)
	{
		this.title = title;
		this.action = action;
		this.result = result;
		this.uid = date.toString() + Utils.getRandom();
		
	}
	/*
	 * Class Constructor
	 * @title The title of the query filter
	 * @action Element action type org.jdom.Element
	 * @output Element type output
	 * @total of elements
	 * @uid Unique identifier consisting of the date and a random number
	 */
	public Template (String title, Element action, int result, int n)
	{
		this.title = title;
		this.action = action;
		this.result = result;
		this.n = n;
		this.uid = date.toString() + Utils.getRandom();
		
	}
	/*
	 * Function that returns the title
	 */
    public String getTitle () 
    {

        return this.title;
    }
    /*
     * Function that returns the action element
     */
    public Element getAction () 
    {

        return this.action;
    }
    /*
     * Function that returns the uid
     */
    public String getUid () 
    {

        return this.uid;
    }
    /*
     * Function that returns output element
     */
    public Output getOutput () 
    {

        return this.output;
    }
    /*
     * Function that returns the number of occurrences/results
     */
    public int getN () 
    {

        return this.n;
    }
    /*
     * Function that returns the number of occurrences/results
     */
    public int getResult () 
    {

        return this.result;
    }
    /*
     * Method that puts a result indicated in the results variable
     */
    public void setResult (int results) 
    {

        this.result = results;
    }
    /*
     * Method that puts a result indicated in the results variable
     */
    public void setN (int numb) 
    {

        this.n = numb;
    }
    /*
     * Returns xpath value from action element
     */
    public String getXpathAction (Element action)
    {
    	String actionXpath = null;
    	
    	actionXpath = action.getValue();
    	
    	return actionXpath;
    }

    /*
     * Function that returns the value of an element indicate in field
     * Could be count, mean, list, frequencies, classify, classifiedcount
     * option, special, valueIsURI, separator
     */		
	public String getElement(Element action, String field) 
	{
		try
		{
	    	List <Attribute> atts = action.getAttributes();
	    	String actionDo = null;
	    	if (atts.size() > 0)
	    	{
	    		for (int i = 0; i < atts.size(); i++)
	    		{
	    			Attribute at = atts.get(i);
	    			if (at.getName().toLowerCase().equals(field))
	    			{
	    				actionDo = at.getValue();
	    				break;
	    			}
	    		}
	    	}
	    	return actionDo;
		}
		catch (Exception ex)
		{
			utils.writeLogFich(ex, "Template.getElement");
			return null;
		}
	}
	
    /*
     * Function that returns the value of an element indicate in field
     * Could be count, mean, list, frequencies, classify, classifiedcount
     * option, special, valueIsURI, separator
     */		
	public ArrayList getElements(Element action, String field) 
	{
		try
		{
	    	List <Attribute> atts = action.getAttributes();
	    	ArrayList<String> actionDo = null;
	    	int j = 0;
	    	if (atts.size() > 0)
	    	{
				actionDo = new ArrayList<String> ();
	    		for (int i = 0; i < atts.size(); i++)
	    		{
	    			Attribute at = atts.get(i);
	    			if (at.getName().toLowerCase().equals(field))
	    			{
	    				actionDo.add(at.getValue());
	    				j++;
	    			}
	    		}
	    	}
	    	return actionDo;
		}
		catch (Exception ex)
		{
			utils.writeLogFich(ex, "Template.getElement");
			return null;
		}
	}
}
