/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uah.cc.ie.metadatastatistics.schemas;

import es.uah.cc.ie.metadatastatistics.MetadataSchema;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Voa3rAp4 Schema
 * @author flag
 */
public class Voa3rAp4MetadataSchema extends MetadataSchema {

    public Voa3rAp4MetadataSchema()  {
        super();
        Collection<String> voa3tap4Collection = new ArrayList<String>();

        //voa3tap4Collection.add("URL");
        voa3tap4Collection.add("title");
        voa3tap4Collection.add("title_without_lang");
        voa3tap4Collection.add("alternative");
        voa3tap4Collection.add("creator");
        voa3tap4Collection.add("creator_name");
        voa3tap4Collection.add("creator_email");
        voa3tap4Collection.add("creator_organization");
        voa3tap4Collection.add("creator_uri");
        voa3tap4Collection.add("creator_foaf");
        voa3tap4Collection.add("contributor");
         voa3tap4Collection.add("contributor_name");
        voa3tap4Collection.add("contributor_email");
        voa3tap4Collection.add("contributor_organization");
        
         //voa3tap4Collection.add("contributor_URI");
        voa3tap4Collection.add("publisher");
         voa3tap4Collection.add("publisher_name");
        voa3tap4Collection.add("publisher_email");
        voa3tap4Collection.add("publisher_organization");
        voa3tap4Collection.add("date");
        voa3tap4Collection.add("identifier");
        voa3tap4Collection.add("identifier_ISBN");
        voa3tap4Collection.add("identifier_ISSN");
        voa3tap4Collection.add("identifier_URI");
        voa3tap4Collection.add("identifier_DOI");
        voa3tap4Collection.add("language");
        voa3tap4Collection.add("relation");
        voa3tap4Collection.add("rights");
        voa3tap4Collection.add("format");
        voa3tap4Collection.add("isShownBy");
        voa3tap4Collection.add("isShownAt");
        voa3tap4Collection.add("subject");
        voa3tap4Collection.add("description");
        voa3tap4Collection.add("abstract");
        voa3tap4Collection.add("bibligraphicCitation_URI");
        voa3tap4Collection.add("bibligraphicCitation_BIBTEXT");
        voa3tap4Collection.add("bibligraphicCitation_BIBTEXT_ARTICLE");
        voa3tap4Collection.add("bibligraphicCitation_BIBTEXT_ARTICLE_title");
        voa3tap4Collection.add("bibligraphicCitation_BIBTEXT_ARTICLE_volume");
        voa3tap4Collection.add("bibligraphicCitation_BIBTEXT_ARTICLE_startpage");
        voa3tap4Collection.add("bibligraphicCitation_BIBTEXT_ARTICLE_issn");
        voa3tap4Collection.add("bibligraphicCitation");
        
        voa3tap4Collection.add("type");
        voa3tap4Collection.add("accessRights");
        voa3tap4Collection.add("license");
        voa3tap4Collection.add("reviewStatus");
        voa3tap4Collection.add("relation");
        voa3tap4Collection.add("publicationStatus");
        voa3tap4Collection.add("conformsTo");
        voa3tap4Collection.add("references");
        voa3tap4Collection.add("isReferencedBy");
        voa3tap4Collection.add("hasPart");
        voa3tap4Collection.add("isPartOf");
        voa3tap4Collection.add("hasVersion");
        voa3tap4Collection.add("isVersionOf");
        voa3tap4Collection.add("hasTranslation");
        voa3tap4Collection.add("isTranslationOf");
        voa3tap4Collection.add("hasMetametadata");
        voa3tap4Collection.add("hasResearch");
      

        this.declareFields(voa3tap4Collection);
        //List<String> titleNames = new ArrayList<String>();

    }
}
