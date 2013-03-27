/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uah.cc.ie.metadatastatistics.schemas;

import es.uah.cc.ie.metadatastatistics.MetadataSchema;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author flag
 */
public class DCMetadataSchema extends MetadataSchema {

    public DCMetadataSchema()  {
        super();
        
        Collection<String> dcCollection = new ArrayList<String>();

       
        dcCollection.add("title");
        dcCollection.add("creator");
        dcCollection.add("subject");
        dcCollection.add("description");
        dcCollection.add("publisher");
        dcCollection.add("contributor");
        dcCollection.add("date");
        dcCollection.add("type");
        dcCollection.add("format");
        dcCollection.add("identifier");
        dcCollection.add("source");
        dcCollection.add("language");
        dcCollection.add("elation");
        dcCollection.add("coverage");
        dcCollection.add("rights");
        // System.out.println("ENTROOOOOOOOOOO");
        declareFields(dcCollection);
    }
   
   
}
