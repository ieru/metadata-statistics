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

    public DCMetadataSchema() {
        Collection<String> dcCollection = new ArrayList<String>();


        dcCollection.add("Title");
        dcCollection.add("Creator");
        dcCollection.add("Subject");
        dcCollection.add("Description");
        dcCollection.add("Publisher");
        dcCollection.add("Contributor");
        dcCollection.add("Date");
        dcCollection.add("Type");
        dcCollection.add("Format");
        dcCollection.add("Identifier");
        dcCollection.add("Source");
        dcCollection.add("Language");
        dcCollection.add("elation");
        dcCollection.add("Coverage");
        dcCollection.add("Rights");
    }
}
