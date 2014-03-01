/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uah.cc.ie.metadatastatistics.parsers;

import es.uah.cc.ie.metadatastatistics.*;
import es.uah.cc.ie.metadatastatistics.conversor.Voa3rAP2.*;
import es.uah.cc.ie.metadatastatistics.schemas.Voa3rAp2MetadataSchema;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Voa3rAp2 parser
 * @author flag
 */
public class Voa3rAP2XMLMetadataParser implements MetadataParser {

    public Resource parse(Object obj) {
        Resource res = new Resource(new Voa3rAp2MetadataSchema());
        File obj2 = (File) obj;
        Voa3rAP2 vap2;
        vap2 = new Voa3rAP2(obj2);
        vap2.parseVoa3rAP2XML();
        try {

//            if (!vap2.getTitles().isEmpty()) {
//                res.set("title", vap2.getTitles());
//            }
            System.out.println(vap2.getTitles());
             if (!vap2.getTitles().isEmpty()) {
                res.set("title", vap2.getTitles());

                if (vap2.getTitle("null") != null) {
                    System.out.println("ENTRO");
                    res.set("title_without_lang", "null");
                }

            }
            if (!vap2.getAlternative().isEmpty()) {
                res.set("alternative", vap2.getAlternative());
            }
            if (!vap2.getCreators().isEmpty()) {
                res.set("creator", vap2.getCreators());
            }
            if (!vap2.getContributors().isEmpty()) {
                res.set("contributor", vap2.getContributors());
            }
            if (!vap2.getContributors().isEmpty()) {
                res.set("publisher", vap2.getContributors());
            }
            if (!vap2.getDates().isEmpty()) {
                res.set("date", vap2.getDates());
            }
            if (!vap2.getIdentifiers().isEmpty()) {
                  ArrayList<String> identifiers=vap2.getIdentifiers();
                 for (String ident : identifiers)  
                 {
                      System.out.println(ident);
                     if(ident.indexOf("http:")>=0)
                     {
                        res.set("identifier_URI", vap2.getIdentifiers());
                     }
                     else if(ident.indexOf("ISBN:")>=0)
                     {
                        res.set("identifier_ISBN", vap2.getIdentifiers());
                     }
                     else if(ident.indexOf("ISSN:")>=0)
                     {
                        res.set("identifier_ISSN", vap2.getIdentifiers());
                     }
                      else if(ident.indexOf("doi:")>=0)
                     {
                        res.set("identifier_DOI", vap2.getIdentifiers());
                     }
                 }
                res.set("identifier", vap2.getIdentifiers());
            }
            if (!vap2.getLanguages().isEmpty()) {
                res.set("language", vap2.getLanguages());
            }
            if (!vap2.getRelations().isEmpty()) {
                res.set("relation", vap2.getRelations());
            }

            if (!vap2.getRights().isEmpty()) {
                res.set("rights", vap2.getRights());
            }

            if (!vap2.getSources().isEmpty()) {
                res.set("format", vap2.getFormats());
            }

            if (!vap2.getIsShownBy().isEmpty()) {
                res.set("isShownBy", vap2.getIsShownBy());
            }
            if (!vap2.getIsShownAt().isEmpty()) {
                res.set("isShownAt", vap2.getIsShownAt());
            }
            if (!vap2.getSubjects().isEmpty()) {
                res.set("subject", vap2.getSubjects());
            }
            if (!vap2.getDescriptions().isEmpty()) {
                res.set("description", vap2.getDescriptions());
            }
            if (!vap2.getAbstract().isEmpty()) {
                res.set("abstract", vap2.getAbstract());
            }
            if (!vap2.getBibliographicCitation().isEmpty()) {
                res.set("bibligraphicCitation", vap2.getBibliographicCitation());
            }
            if (!vap2.getTypes().isEmpty()) {
                res.set("type", vap2.getTypes());
            }
            if (!vap2.getAccessRights().isEmpty()) {
                res.set("accessRights", vap2.getAccessRights());
            }
            if (!vap2.getLicense().isEmpty()) {
                res.set("license", vap2.getLicense());
            }
            if (!vap2.getReviewStatus().isEmpty()) {
                res.set("reviewStatus", vap2.getReviewStatus());
            }
            if (!vap2.getPublicationStatus().isEmpty()) {
                res.set("publicationStatus", vap2.getPublicationStatus());
            }
            if (!vap2.getConformsTo().isEmpty()) {
                res.set("conformsTo", vap2.getConformsTo());
            }
            if (!vap2.getReferences().isEmpty()) {
                res.set("references", vap2.getReferences());
            }
            if (!vap2.getIsReferencedBy().isEmpty()) {
                res.set("isReferencedBy", vap2.getIsReferencedBy());
            }
            if (!vap2.getHasPart().isEmpty()) {
                res.set("hasPart", vap2.getHasPart());
            }
            if (!vap2.getIsPartOf().isEmpty()) {
                res.set("isPartOf", vap2.getIsPartOf());
            }
            //System.out.println();
            //System.out.println((File) obj);
        } catch (NoSuchFieldException ex) {

            Logger.getLogger(DCXMLMetadataParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }
}
