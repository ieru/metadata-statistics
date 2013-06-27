/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uah.cc.ie.metadatastatistics.parsers;

import es.uah.cc.ie.metadatastatistics.*;
import es.uah.cc.ie.metadatastatistics.conversor.dc.*;
import es.uah.cc.ie.metadatastatistics.schemas.DCMetadataSchema;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *DC parser
 * @author flag
 */
public class DCXMLMetadataParser implements MetadataParser {

    public Resource parse(Object obj) {

        Resource res = new Resource(new DCMetadataSchema());
        DublinCore dc;
        dc = new DublinCore((File) obj);
        dc.parseDCXML();
        try {

            if (!dc.getSubjects().isEmpty()) {
                res.set("subject", dc.getSubjects());
            }
            if (!dc.getDescriptions().isEmpty()) {
                res.set("description", dc.getDescriptions());
            }
            if (!dc.getPublishers().isEmpty()) {
                res.set("publisher", dc.getPublishers());
            }
            if (!dc.getDates().isEmpty()) {
                res.set("date", dc.getDates());
            }
            if (!dc.getTypes().isEmpty()) {
                res.set("type", dc.getTypes());
            }
            if (!dc.getFormats().isEmpty()) {
                res.set("format", dc.getFormats());
            }
            if (!dc.getIdentifiers().isEmpty()) {
                res.set("identifier", dc.getIdentifiers());
            }
            if (!dc.getSources().isEmpty()) {
                res.set("source", dc.getSources());
            }
            if (!dc.getLanguages().isEmpty()) {
                res.set("language", dc.getLanguages());
            }
            if (!dc.getRelations().isEmpty()) {
                res.set("relation", dc.getRelations());
            }
            if (!dc.getRights().isEmpty()) {
                res.set("rights", dc.getRights());
            }
            if (!dc.getCreators().isEmpty()) {
                res.set("creator", dc.getCreators());
            }
//            if (!dc.getTitles().isEmpty()) {
//                res.set("title", dc.getTitles());
//            }
              if (!dc.getTitles().isEmpty()) {
                res.set("title", dc.getTitles());
            

                if (dc.getTitle("null") != null) {
                    System.out.println("ENTRO");
                    res.set("title_without_lang", "null");
                }

            }
            if (!dc.getContributors().isEmpty()) {
                res.set("contributor", dc.getContributors());
            }
            if (!dc.getCoverages().isEmpty()) {
                res.set("coverage", dc.getCoverages());
            }
            //System.out.println();
            //System.out.println((File) obj);
        } catch (NoSuchFieldException ex) {

            Logger.getLogger(DCXMLMetadataParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;


    }
}
