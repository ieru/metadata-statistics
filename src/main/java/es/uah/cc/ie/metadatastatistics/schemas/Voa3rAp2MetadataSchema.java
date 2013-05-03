/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uah.cc.ie.metadatastatistics.schemas;

import es.uah.cc.ie.metadatastatistics.MetadataSchema;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Voa3rAp2 Schema
 * @author flag
 */
public class Voa3rAp2MetadataSchema extends MetadataSchema {

    public Voa3rAp2MetadataSchema() {
        super();
        Collection<String> voa3tap2Collection = new ArrayList<String>();

        voa3tap2Collection.add("title");
        voa3tap2Collection.add("alternative");
        voa3tap2Collection.add("creator");
        voa3tap2Collection.add("contributor");
        voa3tap2Collection.add("publisher");
        voa3tap2Collection.add("date");
        voa3tap2Collection.add("identifier");    
        voa3tap2Collection.add("language");
        voa3tap2Collection.add("relation");      
        voa3tap2Collection.add("rights");
        voa3tap2Collection.add("format");
        voa3tap2Collection.add("isShownBy");
        voa3tap2Collection.add("isShownAt");
        voa3tap2Collection.add("subject");
        voa3tap2Collection.add("description");
        voa3tap2Collection.add("abstract");
        voa3tap2Collection.add("bibligraphicCitation");
        voa3tap2Collection.add("type");
        voa3tap2Collection.add("accessRights");
        voa3tap2Collection.add("license");
        voa3tap2Collection.add("reviewStatus");
        voa3tap2Collection.add("publicationStatus");
        voa3tap2Collection.add("conformsTo");
        voa3tap2Collection.add("references");
        voa3tap2Collection.add("isReferencedBy");
        voa3tap2Collection.add("hasPart");
        voa3tap2Collection.add("isPartOf");
        //voa3tap2Collection.add("agrovocTerms");   
        
        
        this.declareFields(voa3tap2Collection);
        //List<String> titleNames = new ArrayList<String>();
      
    }
}
