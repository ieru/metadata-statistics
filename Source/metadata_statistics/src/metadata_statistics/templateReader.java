package metadata_statistics;

import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class templateReader 
{	
	Utils utils = new Utils ();
	
	/*
	 * Load XML file
	 */
	public void loadTemplate (String template)
	{
		Document doc;
		try
		{						
			doc= new SAXBuilder().build(template);
			loadElements (doc.getRootElement());
			
		}
		catch(Exception e)
		{
			utils.writeLogFich(e, "templateReader.loadTemplate");
		}
		finally
		{
			doc = null;
		}
	}
	
	/*
	 * Load the elements that are in a template. This elements are the querys for the statistics.
	 */
	@SuppressWarnings("unchecked")
	private void loadElements(Element root)
	{					
		int result = 0, n = 0;
		String title = null;
		Element action = null, output = null, eleAct = null, nod = null;
		List<Element> nodes = null;
		Template tp = null;
		Template tmp = new Template ();
		try
		{
			Iterator<Element> it = root.getChildren().iterator();
			while(it.hasNext())
			{
				eleAct = it.next();
				nodes = eleAct.getChildren();
				for (int i=0; i<nodes.size();i++)
				{
					nod = nodes.get(i);
					if (nod.getName().equals("title"))
					{
						title = nod.getText();
						continue;
					}
					if (nod.getName().equals("action"))
					{
						action = nod;
						continue;
					}
					if (nod.getName().equals("output"))
					{
						output = nod;
						continue;
					}	
					
				}	
				if (output != null)
				{
					Output out = Output.getOutputElement (output);
					tp = new Template (action, out);
					action = null; out = null;
				}
				if ((title != null) || (action !=  null))
				{
					if (tmp.getElement(action, "do").toLowerCase().equals("mean"))
					{
						tp = new Template (title, action, result, n);
					}
					else
					{
						tp = new Template (title, action,result);
					}
					title = null; action = null;
				}
				//Save template option
				Utils.tableTemplate.put(tp.getUid(), tp);
			}
		}
		catch (Exception e)
		{
			utils.writeLogFich(e, "templateReader.loadElements");
		}	
		finally
		{
			title = null;
			action = null; output = null; eleAct = null; nod = null;
			nodes = null; tp = null; tmp = null;
		}
	}
	
}
