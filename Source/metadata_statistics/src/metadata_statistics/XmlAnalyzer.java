package metadata_statistics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class XmlAnalyzer 
{
	/*
	 * @xmlOrigen Contains the path from the xml to analyse
	 * @template Contains the path where the template is located
	 * @output Contains the path where the output file is going to be saved
	 * @f Contains the number of files that are located in a directory
	 * @utils Instance for Utils Class
	 * @tmp Private instance for templateReader class
	 */
	private String xmlOrigen = "";	
	private String template = "";
	private String output = "";
	public static int f = 0;
	private Utils utils = new Utils ();
	private templateReader tmp;
	
	
	public XmlAnalyzer(String dirXmlOrigen,String temp,String out, long time_start)
	{
		xmlOrigen = dirXmlOrigen;
		template = temp;
		if (out == null)
		{
			output = "output.xml";
		}
		else
		{
			output = out;
		}
		if (template != null)
		{
			if (template.contains(".xml"))
			{
				//load template
				tmp = new templateReader ();
				tmp.loadTemplate(template);
			}
			else
			{
				System.out.println ("Template must be in xml format.");
			}
		}
		
		if (xmlOrigen.contains(".xml"))
		{
			readXML();
		}
		else
		{
			File fi = new File (xmlOrigen);
			readFilesFromFolders(fi);
		}
		
		//Write results
		XpathLoader.writeXMLFile(output);
		long time_end = System.currentTimeMillis();
		System.out.println ("Analysis finished...");
		System.out.println("Execution time: " + Utils.getExecutionTime(time_start, time_end) + " seconds.");
		
	}	
	
	private void readFilesFromFolders(File path)
	{
		try
		{
			File [] list = path.listFiles();
			for (int x= 0; x < list.length; x++)
				{
					if (list[x].isDirectory())
					{
						readFilesFromFolders (list[x]);
					}
					else
					{
						xmlOrigen = list[x].getPath();
						System.out.println("Ruta:" + list[x]);
						if (xmlOrigen.contains(".xml"))
						{
							readXML();
						}
					}
				}
		}
		catch (Exception ex)
		{
			utils.writeLogFich(ex, "xmlAnalyzer.readFilesFromFolders");
		}
	}
	/*
	 * Read a xml file an all its elements. 
	 */
	private void readXML()
	{
		SAXBuilder builder;
		Document doc;
		Element root;
		File f;
		FileWriter w;
		BufferedWriter bw;
		PrintWriter wr;
		try
		{	
			builder = new SAXBuilder();
			doc = builder.build(xmlOrigen);
			root=doc.getRootElement();
			if (template != null)
			{				
				getTemplateStatistics(doc);
			}
			else
			{
				getAllStatistics(root);
				f = new File (output);
				w = new FileWriter(f);
				bw = new BufferedWriter(w);
				wr = new PrintWriter(bw);	
				imprimeHashtabletext("Elements", utils.getGlobalRep(),wr);
				imprimeHashtabletext("Attibutes", utils.getAttTable(),wr);
			}			
		}
		catch(Exception e)
		{
			if (e.getMessage().contains(".dtd"))
			{
				doc = null;
				builder = null;
				root = null;
				readXMLDtd();
			}
			else
			{
				utils.writeLogFich(e, "xmlAnalyzer.readXML");
				System.gc();
			}
		}
		finally
		{
			doc = null; builder = null; root = null;
			f = null; w = null; bw = null; wr = null;
			System.gc();
		}
	}
	
	private void readXMLDtd ()
	{
		SAXBuilder builder;
		Document doc;
		Element root;
		File f;
		FileWriter w;
		BufferedWriter bw;
		PrintWriter wr;
		try
		{	
			builder = new SAXBuilder();
			builder.setValidation(false);
			builder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			doc = builder.build(xmlOrigen);
			root=doc.getRootElement();
			if (template != null)
			{				
				getTemplateStatistics(doc);
			}
			else
			{
				getAllStatistics(root);
				f = new File (output);
				w = new FileWriter(f);
				bw = new BufferedWriter(w);
				wr = new PrintWriter(bw);	
				imprimeHashtabletext("Elements", utils.getGlobalRep(),wr);
				imprimeHashtabletext("Attibutes", utils.getAttTable(),wr);
			}	
			
		}
		catch(Exception e)
		{
			utils.writeLogFich(e, "xmlAnalyzer.readXMLDtd");
		}
		finally
		{
			doc = null; builder = null; root = null;
			f = null; w = null; bw = null; wr = null;
			System.gc();
		}
	}
	
	@SuppressWarnings("static-access")
	private void getTemplateStatistics(Document doc) 
	{
		Actions act = new Actions ();
		Object [] aux = utils.getTableTemplate();
		Element action = null;
		Template tmp = null;
		String doAction = null;
		try
		{
				for (int i= 0; i <= aux.length-1;i++)
				{
					tmp = (Template) aux[i];
					action = tmp.getAction();
					if (action != null)
					{
						doAction = tmp.getElement(action, "do"); 
						if (doAction.toLowerCase().equals("count"))
						{
							act.countAction (doc, tmp);						
						}
						if (doAction.toLowerCase().equals("mean"))
						{
							act.meanAction(doc, tmp);
						}
						if (doAction.toLowerCase().equals("list"))
						{
							act.listAction(doc, tmp);
						}
						if (doAction.toLowerCase().equals("classifiedcount"))
						{
							act.classifiedCountAction(doc, tmp);
						}
						if (doAction.toLowerCase().equals("classify"))
						{
							act.classifyAction(doc, tmp);
						}
						if (doAction.toLowerCase().equals("frequencies"))
						{
							act.frequenciesAction(doc, tmp);
						}
						if (doAction.toLowerCase().equals("tocsv"))
						{
							act.toCsv(doc, tmp);
						}
					}
				}
		}
		catch (Exception ex)
		{
			System.out.println ("Error in getTemplateStatistics: " + ex.getMessage());
			ex.printStackTrace();
		}
		finally
		{
			act = null;
			aux = null; action = null;
			tmp = null; doAction = null;
			System.gc();
		}
		
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	private void getAllStatistics(Element root)
	{
		String tree = getParents(root);
		
		if(utils.globalRep.get(tree)==null)
			utils.getGlobalRep().put(tree, 1);
		else
			utils.globalRep.put(tree, utils.globalRep.get(tree)+1);
		
		Iterator<Attribute> itAtributos = root.getAttributes().iterator();
		while(itAtributos.hasNext())
		{
			Attribute atributoActual = itAtributos.next();
			
			String arbolAtributo = tree+"."+atributoActual.getName();
			if(utils.globalAtts.get(arbolAtributo)==null)
				utils.globalAtts.put(arbolAtributo, 1);
			else
				utils.globalAtts.put(arbolAtributo, utils.globalAtts.get(arbolAtributo)+1);								 		
		}					
				
		Hashtable<String, Integer> childs = new Hashtable<String, Integer>();
		
		Iterator<Element> it = root.getChildren().iterator();								
		while(it.hasNext())
		{
			Element eleActual = it.next();
			
			if(childs.get(eleActual.getName())==null) //Inicializo la cuenta
				childs.put(eleActual.getName(), 1);
			else //Aumento la cuenta
				childs.put(eleActual.getName(), childs.get(eleActual.getName())+1);								
			
			getAllStatistics(eleActual);
		}
		
		if(childs.size()>0)
			printHashtable(root.getName(), childs);		
	}
	
	//Imprime una hashtable (par clave, numero de repeticiones) con un encabezado
	private void printHashtable(String root, Hashtable<String, Integer> table)
	{
		//System.out.println(root);
		//System.out.println("____");
		Enumeration<String> enumera = table.keys();
		while(enumera.hasMoreElements())
		{
			String clave = enumera.nextElement();
			System.out.println(clave+" -> "+table.get(clave));
		}
		System.out.println("=====");
	}
	
	private void imprimeHashtabletext(String root, Hashtable<String, Integer> table, PrintWriter wr)
	{
		//wr.write(root);//escribimos en el archivo 
		//wr.println("____");
		//wr.append("____"); //concatenamos en el archivo sin borrar lo existente
		Enumeration<String> enumera = table.keys();
		while(enumera.hasMoreElements())
		{
			String clave = enumera.nextElement();
			wr.println(clave+" -> "+table.get(clave));
			//wr.append(clave+" -> "+tabla.get(clave));
		}
		//wr.append("=====");
		wr.println("____");
		wr.close();;
	       }
	
	private String getParents (Element child)
	{
		if (child.getParentElement() != null)
		{
			return getParents (child.getParentElement()) + "." + child.getName();
		}
		else
		{
			return child.getName();
		}
	}
	
}



