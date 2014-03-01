package metadata_statistics;

import java.util.Iterator;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;

public class Output 
{
	private String xcut = null;
	private Element name = null;
	private static List <Attribute> atts = null;
	
	@SuppressWarnings("static-access")
	public Output (String cut, Element name, List <Attribute> atts)
	{
		this.xcut = cut;
		this.name = name;
		this.atts = atts;
	}
	
	public String getCut ()
	{
		return this.xcut;
	}
	
	public Element getName ()
	{
		return this.name;
	}
	
	@SuppressWarnings("static-access")
	public List<Attribute> getAtts ()
	{
		return this.atts;
	}
	
	@SuppressWarnings("unchecked")
	public static Output getOutputElement (Element output)
	{
		atts = output.getAttributes();
		Iterator<Element> it = output.getChildren().iterator();
		Element eleActual = null, name = null;
		String xcut = null;
		while(it.hasNext())
		{
			eleActual = it.next();
			if (eleActual.getName().equals("name"))
			{
				name = eleActual;		
			}
			if (eleActual.getName().equals("xcut"))
			{
				xcut = eleActual.getValue();
			}
		}
		
		Output out = new Output (xcut, name, atts);
		return out;
	}
	
	@SuppressWarnings("unchecked")
	public String getTypeName (Output out)
	{
		String type = null;
		
		List<Attribute> atts = out.getName().getAttributes();
		if (atts.size() > 0)
		{
			for (int i = 0; i < atts.size(); i++)
			{
				Attribute aux = atts.get(i);
				if (aux.getName().equals("type"))
				{
					type = aux.getValue();
				}
			}
		}
		
		return type;
	}
	
	public String getPath (Output out)
	{
		String url = null;
		
		List<Attribute> atts = out.getAtts();
		if (atts.size() > 0)
		{
			for (int i = 0; i < atts.size(); i++)
			{
				Attribute aux = atts.get(i);
				if (aux.getName().equals("url"))
				{
					url = aux.getValue();
				}
			}
		}
		
		return url;
	}
	
	public String getFormat (Output out)
	{
		String ext = null;
		
		List<Attribute> atts = out.getAtts();
		if (atts.size() > 0)
		{
			for (int i = 0; i < atts.size(); i++)
			{
				Attribute aux = atts.get(i);
				if (aux.getName().equals("ext"))
				{
					ext = aux.getValue();
				}
			}
		}
		
		return ext;
	}
}
