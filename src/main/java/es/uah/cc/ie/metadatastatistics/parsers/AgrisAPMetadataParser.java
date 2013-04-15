/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uah.cc.ie.metadatastatistics.parsers;

import es.uah.cc.ie.metadatastatistics.MetadataParser;
import es.uah.cc.ie.metadatastatistics.Resource;

import es.uah.cc.ie.metadatastatistics.conversor.agrisap.Agrisap;
import es.uah.cc.ie.metadatastatistics.schemas.AgrisAPMetadataSchema;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flag
 */
public class AgrisAPMetadataParser implements MetadataParser {

    public Resource parse(Object obj) {

        Resource res = new Resource(new AgrisAPMetadataSchema());
        Agrisap ag;
        ag = new Agrisap((File) obj);
        try {



            if (!ag.getTitles().isEmpty()) {
                res.set("title", ag.getTitles());
            }
            if (!ag.getAlternative().isEmpty()) {
                res.set("alternative", ag.getAlternative());
            }

            // List<String> creatorNames = new ArrayList<String>(, ag.
            if (!ag.getCreator().isEmpty()) {
                res.set("creator", ag.getCreator());
            }
            if (!ag.getCreatorPersonal().isEmpty()) {
                res.set("creatorPersonal", ag.getCreatorPersonal());
            }
            if (!ag.getCreatorCorporate().isEmpty()) {
                res.set("creatorCorporate", ag.getCreatorCorporate());
            }
            if (!ag.getCreatorConference().isEmpty()) {
                res.set("creatorConference", ag.getCreatorConference());
            }

            // List<String> publisherNames = new ArrayList<String>(, ag.
            if (!ag.getPublisher().isEmpty()) {
                res.set("publisher", ag.getPublisher());
            }
            if (!ag.getPublisherName().isEmpty()) {
                res.set("publisherName", ag.getPublisherName());
            }
            if (!ag.getPublisherPlace().isEmpty()) {
                res.set("publisherPlace", ag.getPublisherPlace());
            }

            // List<String> dateNames = new ArrayList<String>(, ag.
            if (!ag.getDate().isEmpty()) {
                res.set("date", ag.getDate());
            }
            //extra cheking about the date and dateIssued elements inside 
            //    the Date2Date translator
            // if (!ag.getAbstracts().isEmpty()) {                res.set("dateIssued", ag.

            //List<String> subjectNames = new ArrayList<String>(, ag.
            if (!ag.getSubject().isEmpty()) {
                res.set("subject", ag.getSubject());
            }
            if (!ag.getSubjectClassification().isEmpty()) {
                res.set("subjectClassification", ag.getSubjectClassification());
            }
            if (!ag.getSubjectThesaurus().isEmpty()) {
                res.set("subjectThesaurus", ag.getSubjectThesaurus());
            }

            // List<String> descriptionNames = new ArrayList<String>(, ag.
            if (!ag.getAbstracts().isEmpty()) {
                res.set("abstract", ag.getAbstracts());
            }
            if (!ag.getDescriptionNotes().isEmpty()) {
                res.set("descriptionNotes", ag.getDescriptionNotes());
            }
            if (!ag.getDescriptionEdition().isEmpty()) {
                res.set("descriptionEdition", ag.getDescriptionEdition());
            }

            // List<String> identifierNames = new ArrayList<String>(, ag.
            if (!ag.getIdentifier().isEmpty()) {
                res.set("identifier", ag.getIdentifier());
            }

            // List<String> typeNames = new ArrayList<String>(, ag.
            if (!ag.getType().isEmpty()) {
                res.set("type", ag.getType());
            }

            //List<String> formatNames = new ArrayList<String>(, ag.
            if (!ag.getFormat().isEmpty()) {
                res.set("format", ag.getFormat());
            }
            if (!ag.getExtent().isEmpty()) {
                res.set("extent", ag.getExtent());
            }
            if (!ag.getMedium().isEmpty()) {
                res.set("medium", ag.getMedium());
            }

            //  List<String> languageNames = new ArrayList<String>(, ag.
            if (!ag.getLanguage().isEmpty()) {
                res.set("language", ag.getLanguage());
            }

            //  List<String> relationNames = new ArrayList<String>(, ag.
            if (!ag.getLanguage().isEmpty()) {
                res.set("relation", ag.getRelation());
            }
            if (!ag.getIsVersionOf().isEmpty()) {
                res.set("isVersionOf", ag.getIsVersionOf());
            }
            if (!ag.getHasVersion().isEmpty()) {
                res.set("hasVersion", ag.getHasVersion());
            }
            if (!ag.getIsReplacedBy().isEmpty()) {
                res.set("isReplacedBy", ag.getIsReplacedBy());
            }
            if (!ag.getReplaces().isEmpty()) {
                res.set("replaces", ag.getReplaces());
            }
            if (!ag.getIsRequiredBy().isEmpty()) {
                res.set("isRequiredBy", ag.getIsRequiredBy());
            }
            if (!ag.getRequires().isEmpty()) {
                res.set("requires", ag.getRequires());
            }
            if (!ag.getIsPartOf().isEmpty()) {
                res.set("isPartOf", ag.getIsPartOf());
            }
            if (!ag.getHasPart().isEmpty()) {
                res.set("hasPart", ag.getHasPart());
            }
            if (!ag.getIsReferencedBy().isEmpty()) {
                res.set("isReferencedBy", ag.getIsReferencedBy());
            }
            if (!ag.getReferences().isEmpty()) {
                res.set("references", ag.getReferences());
            }
            if (!ag.getIsFormatOf().isEmpty()) {
                res.set("isFormatOf", ag.getIsFormatOf());
            }
            if (!ag.getHasFormat().isEmpty()) {
                res.set("hasFormat", ag.getHasFormat());
            }
            if (!ag.getIsTranslationOf().isEmpty()) {
                res.set("isTranslationOf", ag.getIsTranslationOf());
            }
            if (!ag.getHasTranslation().isEmpty()) {
                res.set("hasTranslation", ag.getHasTranslation());
            }

            // List<String> sourceNames = new ArrayList<String>(, ag.
            if (!ag.getSource().isEmpty()) {
                res.set("source", ag.getSource());
            }

            //  List<String> rightsNames = new ArrayList<String>(, ag.
            if (!ag.getRights().isEmpty()) {
                res.set("rights", ag.getRights());
            }
            if (!ag.getRightsStatement().isEmpty()) {
                res.set("rightsStatement", ag.getRightsStatement());
            }
            if (!ag.getTermsOfUse().isEmpty()) {
                res.set("termsOfUse", ag.getTermsOfUse());
            }

            // List<String> coverageNames = new ArrayList<String>(, ag.
            if (!ag.getCoverage().isEmpty()) {
                res.set("coverage", ag.getCoverage());
            }
            if (!ag.getSpatial().isEmpty()) {
                res.set("spatial", ag.getSpatial());
            }
            if (!ag.getTemporal().isEmpty()) {
                res.set("temporal", ag.getTemporal());
            }

            // List<String> availabilityNames = new ArrayList<String>(, ag.
            if (!ag.getAvailability().isEmpty()) {
                res.set("availability", ag.getAvailability());
            }
            if (!ag.getAvailabilityLocation().isEmpty()) {
                res.set("availabilityLocation", ag.getAvailabilityLocation());
            }
            if (!ag.getAvailabilityNumber().isEmpty()) {
                res.set("availabilityNumber", ag.getAvailabilityNumber());
            }

            // List<String> citationNames = new ArrayList<String>(, ag.
            if (!ag.getCitation().isEmpty()) {
                res.set("citation", ag.getCitation());
            }
            if (!ag.getCitationTitle().isEmpty()) {
                res.set("citationTitle", ag.getCitationTitle());
            }
            if (!ag.getCitationIdentifier().isEmpty()) {
                res.set("citationIdentifier", ag.getCitationIdentifier());
            }
            if (!ag.getCitationNumber().isEmpty()) {
                res.set("citationNumber", ag.getCitationNumber());
            }
            if (!ag.getCitationChronology().isEmpty()) {
                res.set("citationChronology", ag.getCitationChronology());
            }
        } catch (NoSuchFieldException ex) {

            Logger.getLogger(DCXMLMetadataParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }
}
