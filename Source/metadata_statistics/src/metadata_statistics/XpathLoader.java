package metadata_statistics;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

public class XpathLoader 
{
	static Utils utils = new Utils ();
   
	/*
	 * Obtains all the nodes that match with the xpath expression
	 * Return a list with all the elements
	 */
	public List<Element> getNodesfromXpath (Document doc, String expression)
    {    		
    		 try
    	        {
    			 	//Build the xpath expression
    			 	XPath xpathExpression = XPath.newInstance(expression);
    			 	@SuppressWarnings("unchecked")
					List<Element> nodes =  (List<Element>) xpathExpression.selectNodes(doc);
    	            return nodes;
    	        }
    		 	catch (Exception ex)
    		 	{
    		 		utils.writeLogFich(ex, "xpathLoader.getNodesfromXpath");
    		 		return null;
    		 	}
    }
	
	/*
	 * Returns the value of xpath expression
	 * @expression Xpath expression to evaluate
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String getNameFromXpath (Document doc, String expression)
	{
		String name = null;
		
		try
        {
		 	//Build the xpath expression
		 	XPath xpathExpression = XPath.newInstance(expression);
		 	if (expression.contains("/@"))
		 	{
		 		List<Attribute> nodes = (List<Attribute>) xpathExpression.selectNodes(doc);
		 		if (nodes != null)
			 	{
			 		if (nodes.size() > 0)
			 		name = nodes.get(0).getValue();
			 		if (name != null)
			 		{
				 		if (name.contains("\n"))
				 		{
				 			name = null;
				 		}
			 		}
			 	}
		 	}
		 	else
		 	{	
		 		List <Element> nodes =  (List<Element>) xpathExpression.selectNodes(doc);
		 		if (nodes != null)
			 	{
			 		if (nodes.size() > 0)
			 		name = nodes.get(0).getValue();
			 		if (name != null)
			 		{
				 		if (name.contains("\n"))
				 		{
				 			name = null;
				 		}
			 		}
			 	}
		 	}
            return name;
        }
	 	catch (Exception ex)
	 	{
	 		utils.writeLogFich(ex, "xpathLoader.getNamefromXpath");
	 		return null;
	 	}
	}
	
	/*
	 * This method write the list of items in nodes into a txt file whith the options indicate
	 * in tmp element. 
	 * @path Where txt file will be saved
	 * @filename The name that the user choose for every text file
	 * @ext The extion for the text file
	 */
	public void printXpathValuesTextFile (String path, String filename, String ext, List<Element> nodes, Template tmp) throws IOException
	{
		String idname = null;
		int size = nodes.size();
		String output = path + filename + "." + ext;
		
		FileWriter fichero = null;
		File folder = new File (path);
        PrintWriter pw = null;
        try
        {
        	if (!folder.exists())
        	{
        		folder.mkdir();
        	}
			if (size > 0)
			{
				fichero = new FileWriter(output);
	            pw = new PrintWriter(fichero);
	 
	            String separator = tmp.getElement(tmp.getAction(), "separator");
				String option = tmp.getElement(tmp.getAction(), "option");	
	            for (int i=0; i<size;i++)
	            {
				 			Element id = nodes.get(i);
				 			if (option.equals("lowcase"))
				 			{
				 				idname = id.getValue().toLowerCase();
				 			}
				 			else
				 			{
				 				idname = id.getValue().toUpperCase();
				 			}
				 			if ((separator.equals("")) || (separator == null))
				 			{
				 				pw.println(idname);
				 			}
				 			else
				 			{
				 				String wrt = idname + separator;
				 				pw.print(wrt);
				 			}
	            }
			} 
        } 
        catch (Exception e) 
        {
        	utils.writeLogFich(e, "xpathLoader.printXpathValuesTextFile");
        } 
        finally 
        {
           try 
           {
	           if (null != fichero)
	              fichero.close();
           } 
           catch (Exception ex) 
           {
        	   utils.writeLogFich(ex, "xpathLoader.printXpathValuesTextFile");
           }
        }
	}
	
	/*
	 * This method writes the elements in tableTemplate to a XML file with the correct
	 * format. 
	 */
	 public static void writeXMLFile (String filePath)
	 {
		 
		 Template tmp = null;
		 String key = null;
		 Element template = null;
		 boolean write = false;
		 
		 try 
		 {
		        template = new Element("results");
		        Document doc = new Document(template);
		        Object [] aux = Utils.getTableTemplate();
		        String action = null;
		        
		        for (int i =0; i < Utils.getTableTemplate().length; i++)
		        {
		        	tmp = (Template) aux[i]; 
			        Element el = new Element("select");
			        String title = tmp.getTitle();
			        action = tmp.getElement(tmp.getAction(), "do");
			        if (tmp.getOutput() == null)
			        {	
			        	write = true;
				       	if (title != null)
				       	{
				       		el.addContent(new Element ("title").setText(title));
				       	}
				        el.addContent(new Element("action").setText(tmp.getXpathAction(tmp.getAction())));
				        if (action.equals("count"))
				        {
				        	el.addContent(new Element ("results").setText(String.valueOf(tmp.getResult())));
				        }
				        if (action.equals("mean"))
				        {
				        	double mean = tmp.getResult() / tmp.getN();
				        	el.addContent(new Element ("results").setText(String.valueOf(mean)));
				        }
				        if (action.equals("frequencies"))
				        {
				        	Enumeration<String> enums = utils.tableFr.keys();
				    		while(enums.hasMoreElements())
				    		{
				    			String clave = enums.nextElement();
				    			int value = utils.tableFr.get(clave);
				    			double fr = ((double)value / (double)tmp.getResult()) * (double)100;
				    			Element va = new Element("value").setText(clave);
				    			va.setAttribute("frequency", Double.toString(fr));
				    			el.addContent(va);
				    		}
				        }
				        if (action.equals("list"))
				        {
				        	Enumeration<String> enums = utils.tableElements.keys();
				    		while(enums.hasMoreElements())
				    		{
				    			String clave = enums.nextElement();
				    			Element va = new Element("value").setText(clave);
				    			el.addContent(va);
				    		}
				        }
				        if (action.equals("classify"))
				        {
				        	Enumeration<String> enums = utils.table.keys();
				    		while(enums.hasMoreElements())
				    		{
					        	Element r = new Element ("result");
					        	el.addContent(r);
				    			String clave = enums.nextElement();
				    			Element va = new Element("key").setText(clave);
				    			r.addContent(va);
				    			Hashtable<String, Integer> tt = new Hashtable <String, Integer>();
				    			tt = utils.table.get(clave);
				    			Enumeration<String> list = tt.keys();
				    			while(list.hasMoreElements())
					    		{
				    				String name = list.nextElement();
				    				Element v = new Element("value").setText(name);
				    				r.addContent(v);
					    		}
				    		}
				        }
				        if (action.equals("classifiedcount"))
				        {
				        	Enumeration<String> enums = utils.table.keys();
				    		while(enums.hasMoreElements())
				    		{
					        	Element r = new Element ("result");
					        	el.addContent(r);
				    			String clave = enums.nextElement();
				    			Element va = new Element("key").setText(clave);
				    			r.addContent(va);
				    			Hashtable<String, Integer> tt = new Hashtable <String, Integer>();
				    			tt = utils.table.get(clave);
				    			Enumeration<String> list = tt.keys();
				    			while(list.hasMoreElements())
					    		{
				    				String name = list.nextElement();
				    				Element v = new Element("value").setText(name);
				    				v.setAttribute("times", Integer.toString(tt.get(name)));
				    				r.addContent(v);
					    		}
				    		}
				        }
			 
				        doc.getRootElement().addContent(el);
			        }
		        }
		 
		        if (write)
		        {
		        	
			        doc.setRootElement(template);
			        XMLOutputter xmlOutput = new XMLOutputter();		 
			        xmlOutput.setFormat(Format.getPrettyFormat());
			        xmlOutput.output(doc, new FileWriter(filePath));
			 
			        System.out.println("XML File with final results saved!");
		        }
		 } 
		 catch (IOException io) 
		 {
			 utils.writeLogFich(io, "xpathLoader.getNodesfromXpath");
		 }
	 }

}
