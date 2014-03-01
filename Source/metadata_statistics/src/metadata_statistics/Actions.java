package metadata_statistics;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;

public class Actions 
{

	private Utils utils = new Utils ();
	private XpathLoader xp = new XpathLoader ();
	
	public void countAction (Document doc, Template tmp)
	{
		List<Element> nodes = null;
		String xpathAction = null, special = null;
		ArrayList<String> option = null;
		try
		{
			option = tmp.getElements(tmp.getAction(), "option");
			if (option != null)
			{
				for (int z=0; z<option.size();z++)
				{
					if (option.get(z).toLowerCase().equals("existences"))
						existsCountAction (doc, tmp);
				}
			}
			else
			{
				special = tmp.getElement(tmp.getAction(), "special");
				if (special != null)
				{
					if (special.toLowerCase().equals("valueisuri"))
						checkURIValue (doc, tmp);
				}
				else
				{
					xpathAction = tmp.getXpathAction (tmp.getAction());
					nodes = xp.getNodesfromXpath(doc, xpathAction);
					tmp.setResult(tmp.getResult() + nodes.size());
					utils.deleteTemplate(tmp.getUid());
					utils.saveData(tmp);
				}
			}
		}
		catch (Exception ex)
		{
			utils.writeLogFich(ex, "Actions.countAction");
		}
		finally
		{
			nodes = null;
			xpathAction = null;
			System.gc();
		}
	}
	
	public void existsCountAction (Document doc, Template tmp)
	{
		String element = null, xpathAction = null, aux = null;
		List <Element> nodes = null, children = null;
		int exists = 0;
		try
		{
			element = tmp.getElement(tmp.getAction(), "element");
			xpathAction = tmp.getXpathAction (tmp.getAction());
			nodes = xp.getNodesfromXpath(doc, xpathAction);
			if (nodes.size() > 0)
			{
				for ( int i=0; i<nodes.size();i++)
				{
					int j = i+1;
					aux = element.replace(xpathAction, xpathAction + "[" + j + "]");
					children = xp.getNodesfromXpath(doc, aux);
					if (children.size() > 0)
					{
						exists += 1;	
					}
				}
				tmp.setResult(tmp.getResult() + exists);
				utils.deleteTemplate(tmp.getUid());
				utils.saveData(tmp);				
			}
		}
		catch (Exception ex)
		{
			utils.writeLogFich(ex, "Actions.existsCountAction");
		}
		finally
		{
			element = null; xpathAction = null;
			nodes = null; children = null;
			exists = 0;
			System.gc();
		}
	}
	
	public void checkURIValue (Document doc, Template tmp)
	{
		List<Element> nodes = null;
		String xpathAction = null, text = null;
		Element element = null;
		int uri = 0;
		try
		{
			xpathAction = tmp.getXpathAction (tmp.getAction());
			nodes = xp.getNodesfromXpath(doc, xpathAction);
			if (nodes.size() > 0)
			{
				for (int i = 0; i < nodes.size(); i++)
				{
					element = nodes.get(i);
					text = element.getValue();
					if ((text.contains("http")) || (text.contains("wwww")))
					{
						uri += 1;
					}
				}
				tmp.setResult(tmp.getResult() + uri);
				utils.deleteTemplate(tmp.getUid());
				utils.saveData(tmp);
			}
		}
		catch (Exception ex)
		{
			utils.writeLogFich(ex, "action.checkURIValue");
		}
		finally
		{
			nodes = null; xpathAction = null; text = null;
			element = null;
			System.gc();
		}
		
	}
	
	public void meanAction (Document doc, Template tmp)
	{
		String n = null, expression = null;
		List<Element> numbers = null, elements = null;
		
		try
		{
			n = tmp.getElement(tmp.getAction(), "n");
			numbers = xp.getNodesfromXpath(doc, n); 
			expression = tmp.getXpathAction(tmp.getAction());
			elements = xp.getNodesfromXpath(doc, expression);
			tmp.setResult(tmp.getResult() + elements.size());
			tmp.setN(tmp.getN() + numbers.size());
			utils.deleteTemplate(tmp.getUid());
			utils.saveData(tmp);
		}
		catch (Exception ex)
		{
			utils.writeLogFich(ex, "actions.meanAction");
		}
		finally
		{
			n = null; expression = null;
			numbers = null; elements = null;
			System.gc();
		}
	}
	
	public void listAction (Document doc, Template tmp)
	{	
		String xcut = null, format = null, fileName = null, path = null;
		String xpathAction = null, type = null, name = null;
		ArrayList<String> option = null;
		List <Element> nodes = null, elements = null, node = null;
		Element action = null, nod = null;
		boolean norepeat = false;
		try
		{
			if (tmp.getOutput() == null)
			{
				xpathAction = tmp.getXpathAction(tmp.getAction());
				nodes = xp.getNodesfromXpath(doc, xpathAction);
				option = tmp.getElements(tmp.getAction(), "option");
					if (nodes.size() > 0)
					{
						for (int i=0; i<nodes.size();i++)
						{
							nod = nodes.get(i);
							name = nod.getValue().toLowerCase();
							for (int j= 0; j<option.size();j++)
							{

								if (option.get(j).toLowerCase().equals("norepeat"))
									norepeat = true;
							}
							if (norepeat)
							{
								if(utils.tableElements.get(name)==null)
									utils.tableElements.put(name, 1);
								else
									utils.tableElements.put(name, utils.tableElements.get(name)+1);		
							}
							else
							{
								utils.tableElements.put(name, 1);
							}
						}
					}
			}
			else
			{
				xcut = tmp.getOutput().getCut();
				nodes = xp.getNodesfromXpath(doc, xcut);
				for (int j =0; j<nodes.size();j++)
				{
					xpathAction = tmp.getXpathAction (tmp.getAction());
					int element = j+1;
					String a = xcut + "[" + element + "]";
					xpathAction = xpathAction.replace(xcut, a);
					elements = xp.getNodesfromXpath(doc, xpathAction);
					//File name and file path
					fileName = tmp.getOutput().getName().getText();
					type = tmp.getOutput().getTypeName(tmp.getOutput());
					format = tmp.getOutput().getFormat (tmp.getOutput());
					path = tmp.getOutput().getPath (tmp.getOutput());
					if (type != null)
					{
						if (type.equals("xpath"))
						{
							fileName = fileName.replace(xcut, a);
							fileName = xp.getNameFromXpath (doc, fileName);
							xp.printXpathValuesTextFile(path, fileName, format, elements,tmp);
						}
					}
					else
					{
						xp.printXpathValuesTextFile(path, fileName, format, elements,tmp);
					}
								
				}
			}
		}
		catch (Exception ex)
		{
			utils.writeLogFich(ex, "actios.listAction");
		}
		finally
		{
			xcut = null; format = null; fileName = null; path = null;
			xpathAction = null; type = null;
			nodes = null; elements = null;
			System.gc();
		}
	}
	
	public void frequenciesAction (Document doc, Template tmp)
	{
		String xpathAction = null, name = null;
		List <Element> nodes = null;
		Element nod = null;
		
		try
		{
			xpathAction = tmp.getXpathAction (tmp.getAction());
			nodes = xp.getNodesfromXpath(doc, xpathAction);
			if (nodes.size() > 0)
			{
				for (int i=0; i<nodes.size();i++)
				{
					nod = nodes.get(i);
					name = nod.getValue().toLowerCase();
					if(utils.tableFr.get(name)==null)
						utils.tableFr.put(name, 1);
					else
						utils.tableFr.put(name, utils.tableFr.get(name)+1);		
				}
				
				tmp.setResult(tmp.getResult() + nodes.size());
				utils.deleteTemplate(tmp.getUid());
				utils.saveData(tmp);
			}
		}
		catch (Exception ex)
		{
			utils.writeLogFich(ex, "Actions.frecuenciesAction");
		}
		finally
		{
			xpathAction = null; name = null;
			nodes = null;
			nod = null;
			System.gc();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void classifyAction (Document doc, Template tmp)
	{
		String options = null, children = null, option = null, aux = null, xpath = null;
		String[] opts = null;
		String xpathAction = null, key = "";
		List <Element> nodes = null, elements = null;
		Element node = null, root = null;
		Hashtable<String, Integer> classification = null;
		
		try
		{
    		root = doc.getRootElement();
			nodes = root.getChildren();
			children = utils.getXpathChildrenRoot(doc);			
			options = tmp.getElement(tmp.getAction(), "by");
			xpathAction = tmp.getXpathAction (tmp.getAction());
			if (options.contains(","))
			{
				opts = options.split(",");
			}
			for (int x=0; x<nodes.size();x++)
			{
				for (int i=0; i<opts.length; i++)
				{
					int count = x+1;
					aux  = children + "[" + count + "]";
					option = opts[i];
					option = option.replace(children, aux);
					if (xp.getNameFromXpath(doc, option) != null)
						key += xp.getNameFromXpath(doc, option) + ",";
				}
				xpath = xpathAction.replace(children, aux);
				elements = xp.getNodesfromXpath(doc, xpath);
				if (elements.size() > 0)
				{
					if (utils.table.containsKey(key))
						classification = utils.table.get(key);
					else
						classification = new Hashtable<String, Integer>();
					for (int z =0; z<elements.size();z++)
					{
						node = elements.get(z);
						classification.put(node.getValue(), 1);
					}
					if (!utils.table.containsKey(key))
						utils.table.put(key,classification);
				}
				key="";
			}
		}
		catch (Exception ex)
		{
			utils.writeLogFich(ex, "Actions.classifyAction");
		}
		finally
		{
			options = null; children = null; option = null; aux = null;
			opts = null; xpathAction = null; key = null;
			nodes = null; elements = null;
			node = null;
			classification = null;
			System.gc();
		}
	}
	
	@SuppressWarnings("static-access")
	public void classifiedCountAction (Document doc, Template tmp)
	{
		String options = null, children = null, option = null, aux = null, xpath = null;
		String[] opts = null;
		String xpathAction = null, key = "";
		List <Element> nodes = null, elements = null;
		Element node = null, root = null;
		Hashtable<String, Integer> classification = null;
		
		try
		{
    		root = doc.getRootElement();
			nodes = root.getChildren();
			children = utils.getXpathChildrenRoot(doc);			
			options = tmp.getElement(tmp.getAction(), "by");
			xpathAction = tmp.getXpathAction (tmp.getAction());
			if (options.contains(","))
			{
				opts = options.split(",");
			}
			for (int x=0; x<nodes.size();x++)
			{
				for (int i=0; i<opts.length; i++)
				{
					int count = x+1;
					aux  = children + "[" + count + "]";
					option = opts[i];
					option = option.replace(children, aux);
					if (xp.getNameFromXpath(doc, option) != null)
						key += xp.getNameFromXpath(doc, option) + ",";
				}
				xpath = xpathAction.replace(children, aux);
				elements = xp.getNodesfromXpath(doc, xpath);
				if (elements.size() > 0)
				{
					if (utils.table.containsKey(key))
						classification = utils.table.get(key);
					else
						classification = new Hashtable<String, Integer>();
					for (int z =0; z<elements.size();z++)
					{
						node = elements.get(z);
						if (classification.containsKey(node.getValue()))
							classification.put(node.getValue(), classification.get(node.getValue())+1);
						else
							classification.put(node.getValue(), 1);
					}
					if (!utils.table.containsKey(key))
						utils.table.put(key,classification);
				}
				key="";
			}
		}
		catch (Exception ex)
		{
			utils.writeLogFich(ex, "Actions.classifyAction");
		}
		finally
		{
			options = null; children = null; option = null; aux = null;
			opts = null; xpathAction = null; key = null;
			nodes = null; elements = null;
			node = null;
			classification = null;
			System.gc();
		}
	}
	
	public void toCsv (Document doc, Template tmp)
	{
		String title = null, aux = null, option = null, repl = null, text = "";
		List <Element> columns = null, nodes = null;
		Element action = null;
		String[] columnName = null, columnValue = null;
		FileWriter writer; 
		
		try
		{
			title = tmp.getTitle();
			writer = new FileWriter(title + ".csv");
			action = tmp.getAction();
			columns = action.getChildren();
			for (int z= 0; z<columns.size(); z++)
			{
				columnName[z] = columns.get(z).getAttributeValue("name");
				columnValue[z] = columns.get(z).getValue();
				writer.append(columnName[z]);
			}
			aux = utils.getXpathChildrenRoot(doc);	
			nodes = xp.getNodesfromXpath(doc, aux);
			for (int j=0; j< nodes.size(); j++)
			{
				for (int i=0; i<columns.size();i++)
				{
					int count = j+1;
					repl  = aux + "[" + count + "]";
					option = columnValue[i];
					option = option.replace(aux, repl);
					text += xp.getNameFromXpath(doc, option);
				}
				
				//print
				text = "";
			}
			
		}
		catch (Exception ex)
		{
			
		}
		finally
		{
			
		}
	}
}
