/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uah.cc.ie.metadatastatistics.parsers;

import es.uah.cc.ie.metadatastatistics.MetadataParser;
import es.uah.cc.ie.metadatastatistics.Resource;
import es.uah.cc.ie.metadatastatistics.conversor.Voa3rAP4.Voa3rAP4;
import es.uah.cc.ie.metadatastatistics.schemas.Voa3rAp4MetadataSchema;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flag
 */
public class Voa3rAP4XMLMetadataParser implements MetadataParser {

    public Resource parse(Object obj) {
        Resource res = new Resource(new Voa3rAp4MetadataSchema());
        File obj2 = (File) obj;
        Voa3rAP4 vap4;
        vap4 = new Voa3rAP4(obj2);
        vap4.parseVoa3rAP4XML();
        try {

            if (!vap4.getTitles().isEmpty()) {
                res.set("title", vap4.getTitles());
            }
            if (!vap4.getAlternative().isEmpty()) {
                res.set("alternative", vap4.getAlternative());
            }
            if (!vap4.getCreators().isEmpty()) {
                res.set("creator", vap4.getCreators());
            }
            if (!vap4.getContributors().isEmpty()) {
                res.set("contributor", vap4.getContributors());
            }
            if (!vap4.getPublishers().isEmpty()) {
                res.set("publisher", vap4.getPublishers());
            }
            if (!vap4.getDates().isEmpty()) {
                res.set("date", vap4.getDates());
            }
            if (!vap4.getIdentifiers().isEmpty()) {
                res.set("identifier", vap4.getIdentifiers());
            }
            if (!vap4.getLanguages().isEmpty()) {
                res.set("language", vap4.getLanguages());
            }
            if (!vap4.getRelations().isEmpty()) {
                res.set("relation", vap4.getRelations());
            }
            if (!vap4.getRights().isEmpty()) {
                res.set("rights", vap4.getRights());
            }
            if (!vap4.getFormats().isEmpty()) {
                res.set("format", vap4.getFormats());
            }
            if (!vap4.getIsShownBy().isEmpty()) {
                res.set("isShownBy", vap4.getIsShownBy());
            }
            if (!vap4.getIsShownAt().isEmpty()) {
                res.set("isShownAt", vap4.getIsShownAt());
            }
            if (!vap4.getSubjects().isEmpty()) {
                res.set("subject", vap4.getSubjects());
            }
            if (!vap4.getDescriptions().isEmpty()) {
                res.set("description", vap4.getDescriptions());
            }
            if (!vap4.getAbstract().isEmpty()) {
                res.set("abstract", vap4.getAbstract());
            }
            if (!vap4.getBibliographicCitation().isEmpty()) {
                res.set("bibligraphicCitation", vap4.getBibliographicCitation());
            }
            if (!vap4.getTypes().isEmpty()) {
                res.set("type", vap4.getTypes());
            }
            if (!vap4.getAccessRights().isEmpty()) {
                res.set("accessRights", vap4.getAccessRights());
            }
            if (!vap4.getLicense().isEmpty()) {
                res.set("license", vap4.getLicense());
            }
            if (!vap4.getReviewStatus().isEmpty()) {
                res.set("reviewStatus", vap4.getReviewStatus());
            }
            if (!vap4.getRelations().isEmpty()) {
                res.set("relation", vap4.getRelations());
            }
            if (!vap4.getPublicationStatus().isEmpty()) {
                res.set("publicationStatus", vap4.getPublicationStatus());
            }
            if (!vap4.getConformsTo().isEmpty()) {
                res.set("conformsTo", vap4.getConformsTo());
            }
            if (!vap4.getReferences().isEmpty()) {
                res.set("references", vap4.getReferences());
            }
            if (!vap4.getIsReferencedBy().isEmpty()) {
                res.set("isReferencedBy", vap4.getIsReferencedBy());
            }
            if (!vap4.getHasPart().isEmpty()) {
                res.set("hasPart", vap4.getHasPart());
            }
            if (!vap4.getIsPartOf().isEmpty()) {
                res.set("isPartOf", vap4.getIsPartOf());
            }
            if (!vap4.getHasVersion().isEmpty()) {
                res.set("hasVersion", vap4.getHasVersion());
            }
            if (!vap4.getIsVersionOf().isEmpty()) {
                res.set("isVersionOf", vap4.getIsVersionOf());
            }
            if (!vap4.getHasTranslation().isEmpty()) {
                res.set("hasTranslation", vap4.getHasTranslation());
            }
            if (!vap4.getIsTranslationOf().isEmpty()) {
                res.set("isTranslationOf", vap4.getIsTranslationOf());
            }
            if (!vap4.getHasMetametadata().isEmpty()) {
                res.set("hasMetametadata", vap4.getHasMetametadata());
            }
            if (!vap4.getHasResearch().isEmpty()) {
                res.set("hasResearch", vap4.getHasResearch());
            }
            //System.out.println();
            //System.out.println((File) obj);
        } catch (NoSuchFieldException ex) {

            Logger.getLogger(DCXMLMetadataParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }
}
