/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uah.cc.ie.metadatastatistics.schemas;

import es.uah.cc.ie.metadatastatistics.MetadataSchema;
import java.util.ArrayList;
import java.util.Collection;

/**
 * AgrisAP Schema
 *
 * @author flag
 */
public class AgrisAPMetadataSchema extends MetadataSchema {

    public AgrisAPMetadataSchema() {
        super();
        Collection<String> agapCollection = new ArrayList<String>();


//        //List<String> titleNames = new ArrayList<String>();
        agapCollection.add("title");
        agapCollection.add("title_without_lang");
        agapCollection.add("alternative");

        // List<String> creatorNames = new ArrayList<String>();
        agapCollection.add("creator");
        agapCollection.add("creatorPersonal");
        agapCollection.add("creatorCorporate");
        agapCollection.add("creatorConference");

        // List<String> publisherNames = new ArrayList<String>();
        agapCollection.add("publisher");
        agapCollection.add("publisherName");
        agapCollection.add("publisherPlace");

        // List<String> dateNames = new ArrayList<String>();
        agapCollection.add("date");
        //extra cheking about the date and dateIssued elements inside 
        //    the Date2Date translator
        //agapCollection.add("dateIssued");

        //List<String> subjectNames = new ArrayList<String>();
        agapCollection.add("subject");
        agapCollection.add("subjectClassification");
        agapCollection.add("subjectThesaurus");
        agapCollection.add("subjectThesaurus_URI");
        agapCollection.add("subjectThesaurus_TEXT");
        agapCollection.add("subjectThesaurus_AGROVOC_TEXT");
        agapCollection.add("subjectThesaurus_AGROVOC_URI");
        agapCollection.add("subjectThesaurus_PO_TEXT");
        agapCollection.add("subjectThesaurus_PO_URI");
        agapCollection.add("subjectThesaurus_CABI_TEXT");
        agapCollection.add("subjectThesaurus_CABI_URI");
        agapCollection.add("subjectThesaurus_ASFAT_TEXT");
        agapCollection.add("subjectThesaurus_ASFAT_URI");
        agapCollection.add("subjectThesaurus_NALT_TEXT");
        agapCollection.add("subjectThesaurus_NALT_URI");



        // List<String> descriptionNames = new ArrayList<String>();
        agapCollection.add("abstract");
        agapCollection.add("descriptionNotes");
        agapCollection.add("descriptionEdition");

        // List<String> identifierNames = new ArrayList<String>();
        agapCollection.add("identifier");
        agapCollection.add("identifier_ISBN");
        agapCollection.add("identifier_ISSN");
        agapCollection.add("identifier_URI");
        agapCollection.add("identifier_DOI");

        // List<String> typeNames = new ArrayList<String>();
        agapCollection.add("type");

        //List<String> formatNames = new ArrayList<String>();
        agapCollection.add("format");
        agapCollection.add("extent");
        agapCollection.add("medium");

        //  List<String> languageNames = new ArrayList<String>();
        agapCollection.add("language");

        //  List<String> relationNames = new ArrayList<String>();
        agapCollection.add("relation");
        agapCollection.add("isVersionOf");
        agapCollection.add("hasVersion");
        agapCollection.add("isReplacedBy");
        agapCollection.add("replaces");
        agapCollection.add("isRequiredBy");
        agapCollection.add("requires");
        agapCollection.add("isPartOf");
        agapCollection.add("hasPart");
        agapCollection.add("isReferencedBy");
        agapCollection.add("references");
        agapCollection.add("isFormatOf");
        agapCollection.add("hasFormat");
        agapCollection.add("isTranslationOf");
        agapCollection.add("hasTranslation");

        // List<String> sourceNames = new ArrayList<String>();
        agapCollection.add("source");

        //  List<String> rightsNames = new ArrayList<String>();
        agapCollection.add("rights");
        agapCollection.add("rightsStatement");
        agapCollection.add("termsOfUse");

        // List<String> coverageNames = new ArrayList<String>();
        agapCollection.add("coverage");
        agapCollection.add("spatial");
        agapCollection.add("temporal");

        // List<String> availabilityNames = new ArrayList<String>();
        agapCollection.add("availability");
        agapCollection.add("availabilityLocation");
        agapCollection.add("availabilityNumber");

        // List<String> citationNames = new ArrayList<String>();
        agapCollection.add("citation");
        agapCollection.add("citationTitle");
        agapCollection.add("citationIdentifier");
        agapCollection.add("citationNumber");
        agapCollection.add("citationChronology");
        this.declareFields(agapCollection);

//     List<String> titleNames = new ArrayList<String>();
//        titleNames.add("title");
//        titleNames.add("alternative");
//        agapCollection.addAll(titleNames);
//        
//        List<String> creatorNames = new ArrayList<String>();
//        creatorNames.add("creator");
//        creatorNames.add("creatorPersonal");
//        creatorNames.add("creatorCorporate");
//        creatorNames.add("creatorConference");
//        agapCollection.addAll(creatorNames);
//        
//        List<String> publisherNames = new ArrayList<String>();
//        publisherNames.add("publisher");
//        publisherNames.add("publisherName");
//        publisherNames.add("publisherPlace");
//        agapCollection.addAll(publisherNames);
//        
//        List<String> dateNames = new ArrayList<String>();
//        dateNames.add("date");
//        agapCollection.addAll(dateNames);
//        //extra cheking about the date and dateIssued elements inside 
//        //    the Date2Date translator
//        //dateNames.add("dateIssued");
//        
//        List<String> subjectNames = new ArrayList<String>();
//        subjectNames.add("subject");
//        subjectNames.add("subjectClassification");
//        subjectNames.add("subjectThesaurus");
//        agapCollection.addAll(subjectNames);
//        
//        List<String> descriptionNames = new ArrayList<String>();
//        descriptionNames.add("abstract");
//        descriptionNames.add("descriptionNotes");
//        descriptionNames.add("descriptionEdition");
//        agapCollection.addAll(descriptionNames);
//        
//        List<String> identifierNames = new ArrayList<String>();
//        identifierNames.add("identifier");
//        agapCollection.addAll(identifierNames);
//        
//        List<String> typeNames = new ArrayList<String>();
//        typeNames.add("type");
//        agapCollection.addAll(typeNames);
//        
//        List<String> formatNames = new ArrayList<String>();
//        formatNames.add("format");
//        formatNames.add("extent");
//        formatNames.add("medium");
//        agapCollection.addAll(formatNames);
//        
//        List<String> languageNames = new ArrayList<String>();
//        languageNames.add("language");
//        agapCollection.addAll(languageNames);
//        
//        List<String> relationNames = new ArrayList<String>();
//        relationNames.add("relation");
//        relationNames.add("isVersionOf");
//        relationNames.add("hasVersion");
//        relationNames.add("isReplacedBy");
//        relationNames.add("replaces");
//        relationNames.add("isRequiredBy");
//        relationNames.add("requires");
//        relationNames.add("isPartOf");
//        relationNames.add("hasPart");
//        relationNames.add("isReferencedBy");
//        relationNames.add("references");
//        relationNames.add("isFormatOf");
//        relationNames.add("hasFormat");
//        relationNames.add("isTranslationOf");
//        relationNames.add("hasTranslation");
//        agapCollection.addAll(relationNames);
//        
//        List<String> sourceNames = new ArrayList<String>();
//        sourceNames.add("source");
//        agapCollection.addAll(sourceNames);
//        
//        List<String> rightsNames = new ArrayList<String>();
//        rightsNames.add("rights");
//        rightsNames.add("rightsStatement");
//        rightsNames.add("termsOfUse");
//        agapCollection.addAll(rightsNames);
//        
//        List<String> coverageNames = new ArrayList<String>();
//        coverageNames.add("coverage");
//        coverageNames.add("spatial");
//        coverageNames.add("temporal");
//        agapCollection.addAll(coverageNames);
//        
//        List<String> availabilityNames = new ArrayList<String>();
//        availabilityNames.add("availability");
//        availabilityNames.add("availabilityLocation");
//        availabilityNames.add("availabilityNumber");
//        agapCollection.addAll(availabilityNames);
//        
//        List<String> citationNames = new ArrayList<String>();
//        citationNames.add("citation");
//        citationNames.add("citationTitle");
//        citationNames.add("citationIdentifier");
//        citationNames.add("citationNumber");
//        citationNames.add("citationChronology");
//        agapCollection.addAll(citationNames);
//        
//        this.declareFields(agapCollection);
//        
    }
}
