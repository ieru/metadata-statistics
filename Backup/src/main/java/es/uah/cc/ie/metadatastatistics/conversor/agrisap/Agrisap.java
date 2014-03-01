/*
 ont-space - The ontology-based resource metadata repository
 Copyright (c) 2006-2011, Information Eng. Research Unit - Univ. of Alcalá
 http://www.cc.uah.es/ie
 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License along
 with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package es.uah.cc.ie.metadatastatistics.conversor.agrisap;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import es.uah.cc.ie.metadatastatistics.conversor.*;

/**
 * Java class to model de Agris AP resources
 */
public class Agrisap {

    private ArrayList<String> _subjectThesaurus =
        new ArrayList<String>();
    //DublinCore and QDC Metadata
    private ArrayList<String> _identifier = null;
    private ArrayList<String> _identifier_URI = null;
    private ArrayList<String> _identifier_ISBN = null;
    private ArrayList<String> _identifier_ISSN = null;
    private ArrayList<String> _identifier_DOI = null;
    private HashMap<String, String> _title = null;
    private ArrayList<String> _language = null;
    private ArrayList<String> _description = null;
    private ArrayList<String> _subject = null;
    private ArrayList<String> _subjectThesaurus_URI;
    private ArrayList<String> _subjectThesaurus_TEXT;
    private ArrayList<String> _subjectThesaurus_AGROVOC_TEXT;
    private ArrayList<String> _subjectThesaurus_AGROVOC_URI;
    private ArrayList<String> _subjectThesaurus_PO_TEXT;
    private ArrayList<String> _subjectThesaurus_PO_URI;
    private ArrayList<String> _subjectThesaurus_CABI_TEXT;
    private ArrayList<String> _subjectThesaurus_CABI_URI;
    private ArrayList<String> _subjectThesaurus_ASFAT_TEXT;
    private ArrayList<String> _subjectThesaurus_ASFAT_URI;
    private ArrayList<String> _subjectThesaurus_NALT_TEXT;
    private ArrayList<String> _subjectThesaurus_NALT_URI;
    private ArrayList<String> _coverage = null;
    private ArrayList<String> _type = null;
    private ArrayList<String> _date = null;
    private ArrayList<String> _creator = null;
    private ArrayList<String> _publisher = null;
    private ArrayList<String> _format = null;
    private ArrayList<String> _rights = null;
    private ArrayList<String> _relation = null;
    private ArrayList<String> _source = null;
    //refined qualified dublin core elements
    private ArrayList<String> _alternative = null;
    private HashMap<String, String> _abstract = null;
    private ArrayList<String> _dateIssued = null;
    private ArrayList<String> _extent = null;
    private ArrayList<String> _medium = null;
    private ArrayList<String> _isVersionOf = null;
    private ArrayList<String> _hasVersion = null;
    private ArrayList<String> _isReplacedBy = null;
    private ArrayList<String> _replaces = null;
    private ArrayList<String> _isRequiredBy = null;
    private ArrayList<String> _requires = null;
    private ArrayList<String> _isPartOf = null;
    private ArrayList<String> _hasPart = null;
    private ArrayList<String> _isReferencedBy = null;
    private ArrayList<String> _references = null;
    private ArrayList<String> _isFormatOf = null;
    private ArrayList<String> _hasFormat = null;
    //AgrisAP refinements
    private ArrayList<String> _spatial = null;
    private ArrayList<String> _temporal = null;
    private ArrayList<String> _availability = null;
    private ArrayList<String> _creatorCorporate = null;
    private ArrayList<String> _citationNumber = null;
    private ArrayList<String> _creatorPersonal = null;
    private ArrayList<String> _availabilityNumber = null;
    private ArrayList<String> _citationIdentifier = null;
    private ArrayList<String> _citationTitle = null;
    private ArrayList<String> _citationChronology = null;
    private ArrayList<String> _availabilityLocation = null;
    private ArrayList<String> _citation = null;
    private ArrayList<String> _creatorConference = null;
    private ArrayList<String> _descriptionNotes = null;
    private ArrayList<String> _rightsStatement = null;
    private ArrayList<String> _descriptionEdition = null;
    private ArrayList<String> _subjectClassification = null;
    private ArrayList<String> _termsOfUse = null;
    private ArrayList<String> _hasTranslation = null;
    private ArrayList<String> _publisherPlace = null;
    private ArrayList<String> _publisherName = null;
    private ArrayList<String> _isTranslationOf = null;
    //Language ISO Helper
    private LanguageISOHelper _langISOHelper = null;
    private File _xmlFile = null;
    private Logger _logger = null;
    private AutomaticLangDetector _detector = null;

    /**
     * Default constructor
     *
     * @param xmlFile XML file with the metadata (absolute path)
     */
    public Agrisap(File xmlFile) {
        _identifier = new ArrayList();
        _identifier_URI = new ArrayList();
        _identifier_DOI = new ArrayList();
        _identifier_ISBN = new ArrayList();
        _identifier_ISSN = new ArrayList();
        _title = new HashMap<String, String>();
        _language = new ArrayList();
        _description = new ArrayList();
        _subject = new ArrayList();

        _subjectThesaurus_URI = new ArrayList();
        _subjectThesaurus_TEXT = new ArrayList();
        _subjectThesaurus_AGROVOC_TEXT = new ArrayList();
        _subjectThesaurus_AGROVOC_URI = new ArrayList();
        _subjectThesaurus_PO_TEXT = new ArrayList();
        _subjectThesaurus_PO_URI = new ArrayList();
        _subjectThesaurus_CABI_TEXT = new ArrayList();
        _subjectThesaurus_CABI_URI = new ArrayList();
        _subjectThesaurus_ASFAT_TEXT = new ArrayList();
        _subjectThesaurus_ASFAT_URI = new ArrayList();
        _subjectThesaurus_NALT_TEXT = new ArrayList();
        _subjectThesaurus_NALT_URI = new ArrayList();

        _coverage = new ArrayList();
        _type = new ArrayList();
        _date = new ArrayList();
        _creator = new ArrayList();
        _publisher = new ArrayList();
        _format = new ArrayList();
        _rights = new ArrayList();
        _relation = new ArrayList();
        _source = new ArrayList();
        //refined qualified dublin core elements
        _alternative = new ArrayList();
        _abstract = new HashMap<String, String>();
        _dateIssued = new ArrayList();
        _extent = new ArrayList();
        _medium = new ArrayList();
        _isVersionOf = new ArrayList();
        _hasVersion = new ArrayList();
        _isReplacedBy = new ArrayList();
        _replaces = new ArrayList();
        _isRequiredBy = new ArrayList();
        _requires = new ArrayList();
        _isPartOf = new ArrayList();
        _hasPart = new ArrayList();
        _isReferencedBy = new ArrayList();
        _references = new ArrayList();
        _isFormatOf = new ArrayList();
        _hasFormat = new ArrayList();
        //AgrisAP refinements
        _spatial = new ArrayList();
        _temporal = new ArrayList();
        _availability = new ArrayList();
        _creatorCorporate = new ArrayList();
        _citationNumber = new ArrayList();
        _creatorPersonal = new ArrayList();
        _availabilityNumber = new ArrayList();
        _citationIdentifier = new ArrayList();
        _citationTitle = new ArrayList();
        _citationChronology = new ArrayList();
        _availabilityLocation = new ArrayList();
        _citation = new ArrayList();
        _creatorConference = new ArrayList();
        _descriptionNotes = new ArrayList();
        _rightsStatement = new ArrayList();
        _descriptionEdition = new ArrayList();
        _subjectClassification = new ArrayList();
        _termsOfUse = new ArrayList();
        _hasTranslation = new ArrayList();
        _publisherPlace = new ArrayList();
        _publisherName = new ArrayList();
        _isTranslationOf = new ArrayList();
        //Language ISO Helper
        _langISOHelper = new LanguageISOHelper();
        _xmlFile = xmlFile;
    }

    /**
     * Constructor with the language detector
     *
     * @param xmlFile XML file with the metadata (absolute path)
     * @param logger Logger
     */
    public Agrisap(File xmlFile, Logger logger) {
        this(xmlFile);
        _logger = logger;
    }

    /**
     * Constructor with all the parameters
     *
     * @param xmlFile XML file with the metadata (absolute path)
     * @param logger Logger
     * @param langDetector Language detector
     */
    public Agrisap(File xmlFile, Logger logger,
        AutomaticLangDetector langDetector) {
        this(xmlFile, logger);
        _detector = langDetector;
    }

    /**
     * This method fulfills an Agris Object that stores the metadata.
     */
    public void parseAgrisapXML() {
        SAXBuilder builder = new SAXBuilder();
        Document doc = null;
        try {
            InputStream is = new FileInputStream(_xmlFile);
            doc = builder.build(is);
            Element root = doc.getRootElement();
            translateNodes(root);
            is.close();
        } catch (JDOMException ex) {
            Logger.getLogger(Agrisap.class.getName()).log(Level.SEVERE, null,
                ex);
        } catch (IOException ex) {
            Logger.getLogger(Agrisap.class.getName()).log(Level.SEVERE, null,
                ex);
        }
    }

    /**
     * This method receives JDOM Element and creates an Agris Object that stores
     * its content.
     *
     * @param root JDOM root element
     */
    public void parseAgrisapXML(Element root) {
        try {
            translateNodes(root);
        } catch (Exception ex) {
            Logger.getLogger(Agrisap.class.getName()).log(Level.SEVERE, null,
                ex);
        }
    }

    /**
     * Translate each XML node
     *
     * @param root DOM element
     */
    public void translateNodes(Element root) {

        List<String> titleNames = new ArrayList<String>();
        titleNames.add("title");
        titleNames.add("alternative");

        List<String> creatorNames = new ArrayList<String>();
        creatorNames.add("creator");
        creatorNames.add("creatorPersonal");
        creatorNames.add("creatorCorporate");
        creatorNames.add("creatorConference");

        List<String> publisherNames = new ArrayList<String>();
        publisherNames.add("publisher");
        publisherNames.add("publisherName");
        publisherNames.add("publisherPlace");

        List<String> dateNames = new ArrayList<String>();
        dateNames.add("date");
        //extra cheking about the date and dateIssued elements inside 
        //    the Date2Date translator
        //dateNames.add("dateIssued");

        List<String> subjectNames = new ArrayList<String>();
        subjectNames.add("subject");
        subjectNames.add("subjectClassification");
        subjectNames.add("subjectThesaurus");

        List<String> descriptionNames = new ArrayList<String>();
        descriptionNames.add("abstract");
        descriptionNames.add("descriptionNotes");
        descriptionNames.add("descriptionEdition");

        List<String> identifierNames = new ArrayList<String>();
        identifierNames.add("identifier");



        List<String> typeNames = new ArrayList<String>();
        typeNames.add("type");

        List<String> formatNames = new ArrayList<String>();
        formatNames.add("format");
        formatNames.add("extent");
        formatNames.add("medium");

        List<String> languageNames = new ArrayList<String>();
        languageNames.add("language");

        List<String> relationNames = new ArrayList<String>();
        relationNames.add("relation");
        relationNames.add("isVersionOf");
        relationNames.add("hasVersion");
        relationNames.add("isReplacedBy");
        relationNames.add("replaces");
        relationNames.add("isRequiredBy");
        relationNames.add("requires");
        relationNames.add("isPartOf");
        relationNames.add("hasPart");
        relationNames.add("isReferencedBy");
        relationNames.add("references");
        relationNames.add("isFormatOf");
        relationNames.add("hasFormat");
        relationNames.add("isTranslationOf");
        relationNames.add("hasTranslation");

        List<String> sourceNames = new ArrayList<String>();
        sourceNames.add("source");

        List<String> rightsNames = new ArrayList<String>();
        rightsNames.add("rights");
        rightsNames.add("rightsStatement");
        rightsNames.add("termsOfUse");

        List<String> coverageNames = new ArrayList<String>();
        coverageNames.add("coverage");
        coverageNames.add("spatial");
        coverageNames.add("temporal");

        List<String> availabilityNames = new ArrayList<String>();
        availabilityNames.add("availability");
        availabilityNames.add("availabilityLocation");
        availabilityNames.add("availabilityNumber");

        List<String> citationNames = new ArrayList<String>();
        citationNames.add("citation");
        citationNames.add("citationTitle");
        citationNames.add("citationIdentifier");
        citationNames.add("citationNumber");
        citationNames.add("citationChronology");

        List<Element> children = root.getChildren();
        for (Element child : children) {
            //      System.out.println("traduciendo elemento "+child.getName());
            String childrenName = child.getName();

            if (identifierNames.contains(childrenName)) {
                Identifier2Identifier identifier2Identifier =
                    new Identifier2Identifier(child, this);


            } else if (titleNames.contains(childrenName)) {
                Title2Title title2Title = new Title2Title(child, this,
                    _langISOHelper, _detector, _logger, _xmlFile);
            } else if (languageNames.contains(childrenName)) {
                Language2Language language2Language = new Language2Language(
                    child, this);
            } else if (descriptionNames.contains(childrenName)) {
                Description2Description description2Description =
                    new Description2Description(child, this,
                    _langISOHelper, _detector, _logger, _xmlFile);
            } else if (subjectNames.contains(childrenName)) {
                Subject2Subject subject2Subject =
                    new Subject2Subject(child, this);
            } else if (coverageNames.contains(childrenName)) {
                Coverage2Coverage coverage2Coverage = new Coverage2Coverage(
                    child, this);
            } else if (typeNames.contains(childrenName)) {
                Type2Type type2Type = new Type2Type(child, this);
            } else if (dateNames.contains(childrenName)) {
                Date2Date date2Date = new Date2Date(child, this);
            } else if (creatorNames.contains(childrenName)) {
                Creator2Creator creator2Creator =
                    new Creator2Creator(child, this);
            } else if (publisherNames.contains(childrenName)) {
                Publisher2Publisher publisher2Publisher =
                    new Publisher2Publisher(child, this);
            } else if (formatNames.contains(childrenName)) {
                Format2Format format2Format = new Format2Format(child, this);
            } else if (rightsNames.contains(childrenName)) {
                Rights2Rights rights2Rights = new Rights2Rights(child, this);
            } else if (relationNames.contains(childrenName)) {
                Relation2Relation relation2Relation = new Relation2Relation(
                    child, this);
            } else if (sourceNames.contains(childrenName)) {
                Source2Source source2Source = new Source2Source(child, this);
            } else if (availabilityNames.contains(childrenName)) {
                Availability2Availability availability2availability =
                    new Availability2Availability(child, this);
            } else if (citationNames.contains(childrenName)) {
                Citation2Citation citation2citation = new Citation2Citation(
                    child, this);
            }

            translateNodes(child);
        }

    }

    /**
     * @return the _subjectThesaurus values
     */
    public ArrayList<String> getSubjectThesaurus() {
        return _subjectThesaurus;
    }
    
   
    
     /**
     * @return the _subjectThesaurus values
     */
    public ArrayList<String> getSubjectThesaurus_URI() {
        return _subjectThesaurus_URI;
    }
     /**
     * @return the _subjectThesaurus values
     */
    public ArrayList<String> getSubjectThesaurus_TEXT() {
        return _subjectThesaurus_TEXT;
    }
     /**
     * @return the _subjectThesaurus values
     */
    public ArrayList<String> getSubjectThesaurus_AGROVOC_TEXT() {
        return _subjectThesaurus_AGROVOC_TEXT;
    }
     /**
     * @return the _subjectThesaurus values
     */
    public ArrayList<String> getSubjectThesaurus_AGROVOC_URI() {
        return _subjectThesaurus_AGROVOC_URI;
    }
     /**
     * @return the _subjectThesaurus values
     */
    public ArrayList<String> getSubjectThesaurus_PO_TEXT() {
        return _subjectThesaurus_PO_TEXT;
    }
     /**
     * @return the _subjectThesaurus values
     */
    public ArrayList<String> getSubjectThesaurus_PO_URI() {
        return _subjectThesaurus_PO_URI;
    }
     /**
     * @return the _subjectThesaurus values
     */
    public ArrayList<String> getSubjectThesaurus_CABI_TEXT() {
        return _subjectThesaurus_CABI_TEXT;
    }
     /**
     * @return the _subjectThesaurus values
     */
    public ArrayList<String> getSubjectThesaurus_CABI_URI() {
        return _subjectThesaurus_CABI_URI;
    }
     /**
     * @return the _subjectThesaurus values
     */
    public ArrayList<String> getSubjectThesaurus_ASFAT_TEXT() {
        return _subjectThesaurus_ASFAT_TEXT;
    }
     /**
     * @return the _subjectThesaurus values
     */
    public ArrayList<String> getSubjectThesaurus_ASFAT_URI() {
        return _subjectThesaurus_ASFAT_URI;
    }
     /**
     * @return the _subjectThesaurus values
     */
    public ArrayList<String> getSubjectThesaurus_NALT_TEXT() {
        return _subjectThesaurus_NALT_TEXT;
    }
     /**
     * @return the _subjectThesaurus values
     */
    public ArrayList<String> getSubjectThesaurus_NALT_URI() {
        return _subjectThesaurus_NALT_URI;
    }
    
    
    
    

    /**
     * @param subjectThesaurus the _subjectThesaurus to set
     */
    public void setSubjectThesaurus(String subjectThesaurus) {
        _subjectThesaurus.add(subjectThesaurus);

    }

    /**
     * @return the _identifier
     */
    public ArrayList<String> getIdentifier() {
        return _identifier;
    }

    /**
     * @return the _identifier
     */
    public ArrayList<String> getIdentifier_URI() {
        return _identifier_URI;
    }

    /**
     * @return the _identifier
     */
    public ArrayList<String> getIdentifier_DOI() {
        return _identifier_DOI;
    }

    /**
     * @return the _identifier
     */
    public ArrayList<String> getIdentifier_ISSN() {
        return _identifier_ISSN;
    }

    /**
     * @return the _identifier
     */
    public ArrayList<String> getIdentifier_ISBN() {
        return _identifier_ISBN;
    }

    /**
     * @return the _title
     */
    public HashMap<String, String> getTitles() {
        return _title;
    }

    /**
     * Obtain the title in the correct lang
     *
     * @param lang Language
     * @return the title in the given language, or null if the title does not
     * exist in this language
     */
    public String getTitle(String lang) {
        return _title.get(lang);
    }

    /**
     * @return the _language
     */
    public ArrayList<String> getLanguage() {
        return _language;
    }

    /**
     * @return the _description
     */
    public ArrayList<String> getDescription() {
        return _description;
    }

    /**
     * @return the _subject
     */
    public ArrayList<String> getSubject() {
        return _subject;
    }

    /**
     * @return the _coverage
     */
    public ArrayList<String> getCoverage() {
        return _coverage;
    }

    /**
     * @return the _type
     */
    public ArrayList<String> getType() {
        return _type;
    }

    /**
     * @return the _date
     */
    public ArrayList<String> getDate() {
        return _date;
    }

    /**
     * @return the _date
     */
    public ArrayList<String> getDateIssued() {
        return _dateIssued;
    }

    /**
     * @return the _creator
     */
    public ArrayList<String> getCreator() {
        return _creator;
    }

    /**
     * @return the _publisher
     */
    public ArrayList<String> getPublisher() {
        return _publisher;
    }

    /**
     * @return the _format
     */
    public ArrayList<String> getFormat() {
        return _format;
    }

    /**
     * @return the _rights
     */
    public ArrayList<String> getRights() {
        return _rights;
    }

    /**
     * @return the _relation
     */
    public ArrayList<String> getRelation() {
        return _relation;
    }

    /**
     * @return the _source
     */
    public ArrayList<String> getSource() {
        return _source;
    }

    /**
     * @return the _alternative
     */
    public ArrayList<String> getAlternative() {
        return _alternative;
    }

    /**
     * @return the _abstract
     */
    public HashMap<String, String> getAbstracts() {
        return _abstract;
    }

    /**
     * Get the Abstract in the correct language
     *
     * @param lang language for the abstract
     * @return the abstract in the given language, or null if the abstract is
     * not availabe in the given language
     */
    public String getAbstract(String lang) {
        return _abstract.get(lang);
    }

    /**
     * @return the _extent
     */
    public ArrayList<String> getExtent() {
        return _extent;
    }

    /**
     * @return the _medium
     */
    public ArrayList<String> getMedium() {
        return _medium;
    }

    /**
     * @return the _isVersionOf
     */
    public ArrayList<String> getIsVersionOf() {
        return _isVersionOf;
    }

    /**
     * @return the _hasVersion
     */
    public ArrayList<String> getHasVersion() {
        return _hasVersion;
    }

    /**
     * @return the _isReplacedBy
     */
    public ArrayList<String> getIsReplacedBy() {
        return _isReplacedBy;
    }

    /**
     * @return the _replaces
     */
    public ArrayList<String> getReplaces() {
        return _replaces;
    }

    /**
     * @return the _isRequiredBy
     */
    public ArrayList<String> getIsRequiredBy() {
        return _isRequiredBy;
    }

    /**
     * @return the _requires
     */
    public ArrayList<String> getRequires() {
        return _requires;
    }

    /**
     * @return the _isPartOf
     */
    public ArrayList<String> getIsPartOf() {
        return _isPartOf;
    }

    /**
     * @return the _hasPart
     */
    public ArrayList<String> getHasPart() {
        return _hasPart;
    }

    /**
     * @return the _isReferencedBy
     */
    public ArrayList<String> getIsReferencedBy() {
        return _isReferencedBy;
    }

    /**
     * @return the _references
     */
    public ArrayList<String> getReferences() {
        return _references;
    }

    /**
     * @return the _isFormatOf
     */
    public ArrayList<String> getIsFormatOf() {
        return _isFormatOf;
    }

    /**
     * @return the _hasFormat
     */
    public ArrayList<String> getHasFormat() {
        return _hasFormat;
    }

    /**
     * @return the _spatial
     */
    public ArrayList<String> getSpatial() {
        return _spatial;
    }

    /**
     * @return the _temporal
     */
    public ArrayList<String> getTemporal() {
        return _temporal;
    }

    /**
     * @param identifier the _identifier to set
     */
    public void setIdentifier(String identifier) {
        this._identifier.add(identifier);
    }

    /**
     * @param identifier the _identifier to set
     */
    public void setIdentifier_URI(String identifier_URI) {
        this._identifier_URI.add(identifier_URI);
    }

    /**
     * @param identifier the _identifier to set
     */
    public void setIdentifier_DOI(String identifier_DOI) {
        this._identifier_DOI.add(identifier_DOI);
    }

    /**
     * @param identifier the _identifier to set
     */
    public void setIdentifier_ISSN(String identifier_ISSN) {
        this._identifier_ISSN.add(identifier_ISSN);
    }

    /**
     * @param identifier the _identifier to set
     */
    public void setIdentifier_ISBN(String identifier_ISBN) {
        this._identifier_ISBN.add(identifier_ISBN);
    }

    /**
     * @param lang language of the title
     * @param title the _title to set
     */
    public void addTitle(String lang, String title) {
        this._title.put(lang, title);
    }

    /**
     * @param language the _language to set
     */
    public void setLanguage(String language) {
        this._language.add(language);
    }

    /**
     * @param description the _description to set
     */
    public void setDescription(String description) {
        this._description.add(description);
    }
 
    /**
     * @param subject the _subject to set
     */
    public void setSubject(String subject) {
        this._subject.add(subject);
    }
 
      /**
     * @param subjectThesaurus the _subjectThesaurus to set
     */
    public void setSubjectThesaurus_TEXT(String subjectThesaurus_TEXT) {
        _subjectThesaurus_TEXT.add(subjectThesaurus_TEXT);

    }
      /**
     * @param subjectThesaurus the _subjectThesaurus to set
     */
    public void setSubjectThesaurus_AGROVOC_TEXT(String subjectThesaurus_AGROVOC_TEXT) {
        _subjectThesaurus_AGROVOC_TEXT.add(subjectThesaurus_AGROVOC_TEXT);

    }
      /**
     * @param subjectThesaurus the _subjectThesaurus to set
     */
    public void setSubjectThesaurus_AGROVOC_URI(String subjectThesaurus_AGROVOC_URI) {
        _subjectThesaurus_AGROVOC_URI.add(subjectThesaurus_AGROVOC_URI);

    }
      /**
     * @param subjectThesaurus the _subjectThesaurus to set
     */
    public void setSubjectThesaurus_PO_TEXT(String subjectThesaurus_PO_TEXT) {
        _subjectThesaurus_PO_TEXT.add(subjectThesaurus_PO_TEXT);

    }
      /**
     * @param subjectThesaurus the _subjectThesaurus to set
     */
    public void setSubjectThesaurus_PO_URI(String subjectThesaurus_PO_URI) {
        _subjectThesaurus_PO_URI.add(subjectThesaurus_PO_URI);

    }
      /**
     * @param subjectThesaurus the _subjectThesaurus to set
     */
    public void setSubjectThesaurus_CABI_TEXT(String subjectThesaurus_CABI_TEXT) {
        _subjectThesaurus_CABI_TEXT.add(subjectThesaurus_CABI_TEXT);

    }
      /**
     * @param subjectThesaurus the _subjectThesaurus to set
     */
    public void setSubjectThesaurus_CABI_URI(String subjectThesaurus_CABI_URI) {
        _subjectThesaurus_CABI_URI.add(subjectThesaurus_CABI_URI);

    }
      /**
     * @param subjectThesaurus the _subjectThesaurus to set
     */
    public void setSubjectThesaurus_ASFAT_TEXT(String subjectThesaurus_ASFAT_TEXT) {
        _subjectThesaurus_ASFAT_TEXT.add(subjectThesaurus_ASFAT_TEXT);

    }
      /**
     * @param subjectThesaurus the _subjectThesaurus to set
     */
    public void setSubjectThesaurus_ASFAT_URI(String subjectThesaurus_ASFAT_URI) {
        _subjectThesaurus_ASFAT_URI.add(subjectThesaurus_ASFAT_URI);

    }
      /**
     * @param subjectThesaurus the _subjectThesaurus to set
     */
    public void setSubjectThesaurus_NALT_URI(String subjectThesaurus_NALT_URI) {
        _subjectThesaurus_NALT_URI.add(subjectThesaurus_NALT_URI);

    }
      /**
     * @param subjectThesaurus the _subjectThesaurus to set
     */
    public void setSubjectThesaurus_NALT_TEXT(String subjectThesaurus_NALT_TEXT) {
        _subjectThesaurus_NALT_TEXT.add(subjectThesaurus_NALT_TEXT);

    }
      /**
     * @param subjectThesaurus the _subjectThesaurus to set
     */
    public void setSubjectThesaurus_URI(String subjectThesaurus_URI) {
        _subjectThesaurus_URI.add(subjectThesaurus_URI);

    }

    /**
     * @param coverage the _coverage to set
     */
    public void setCoverage(String coverage) {
        this._coverage.add(coverage);
    }

    /**
     * @param type the _type to set
     */
    public void setType(String type) {
        this._type.add(type);
    }

    /**
     * @param date the _date to set
     */
    public void setDate(String date) {
        this._date.add(date);
    }

    /**
     * @param creator the _creator to set
     */
    public void setCreator(String creator) {
        this._creator.add(creator);
    }

    /**
     * @param publisher the _publisher to set
     */
    public void setPublisher(String publisher) {
        this._publisher.add(publisher);
    }

    /**
     * @param format the _format to set
     */
    public void setFormat(String format) {
        this._format.add(format);
    }

    /**
     * @param rights the _rights to set
     */
    public void setRights(String rights) {
        this._rights.add(rights);
    }

    /**
     * @param relation the _relation to set
     */
    public void setRelation(String relation) {
        this._relation.add(relation);
    }

    /**
     * @param source the _source to set
     */
    public void setSource(String source) {
        this._source.add(source);
    }

    /**
     * @param alternativetitle the _alternative to set
     */
    public void setAlternative(String alternative) {
        this._alternative.add(alternative);
    }

    /**
     * @param abstract the _abstract to set
     */
    public void addAbstract(String lang, String abs) {
        this._abstract.put(lang, abs);
    }

    /**
     * @param extent the _extent to set
     */
    public void setExtent(String extent) {
        this._extent.add(extent);
    }

    /**
     * @param medium the _medium to set
     */
    public void setMedium(String medium) {
        this._medium.add(medium);
    }

    /**
     * @param isVersionOf the _isVersionOf to set
     */
    public void setIsVersionOf(String isVersionOf) {
        this._isVersionOf.add(isVersionOf);
    }

    /**
     * @param hasVersion the _hasVersion to set
     */
    public void setHasVersion(String hasVersion) {
        this._hasVersion.add(hasVersion);
    }

    /**
     * @param isReplacedBy the _isReplacedBy to set
     */
    public void setIsReplacedBy(String isReplacedBy) {
        this._isReplacedBy.add(isReplacedBy);
    }

    /**
     * @param replaces the _replaces to set
     */
    public void setReplaces(String replaces) {
        this._replaces.add(replaces);
    }

    /**
     * @param isRequiredBy the _isRequiredBy to set
     */
    public void setIsRequiredBy(String isRequiredBy) {
        this._isRequiredBy.add(isRequiredBy);
    }

    /**
     * @param requires the _requires to set
     */
    public void setRequires(String requires) {
        this._requires.add(requires);
    }

    /**
     * @param isPartOf the _isPartOf to set
     */
    public void setIsPartOf(String isPartOf) {
        this._isPartOf.add(isPartOf);
    }

    /**
     * @param hasPart the _hasPart to set
     */
    public void setHasPart(String hasPart) {
        this._hasPart.add(hasPart);
    }

    /**
     * @param isReferencedBy the _isReferencedBy to set
     */
    public void setIsReferencedBy(String isReferencedBy) {
        this._isReferencedBy.add(isReferencedBy);
    }

    /**
     * @param references the _references to set
     */
    public void setReferences(String references) {
        this._references.add(references);
    }

    /**
     * @param isFormatOf the _isFormatOf to set
     */
    public void setIsFormatOf(String isFormatOf) {
        this._isFormatOf.add(isFormatOf);
    }

    /**
     * @param hasFormat the _hasFormat to set
     */
    public void setHasFormat(String hasFormat) {
        this._hasFormat.add(hasFormat);
    }

    /**
     * @param spatial the _spatial to set
     */
    public void setSpatial(String spatial) {
        this._spatial.add(spatial);
    }

    /**
     * @param temporal the _temporal to set
     */
    public void setTemporal(String temporal) {
        this._temporal.add(temporal);
    }

    public void setAvailability(String availability) {
        this._availability.add(availability);
    }

    public void setAvailabilityLocation(String value) {
        this._availabilityLocation.add(value);
    }

    public void setAvailabilityNumber(String value) {
        this._availabilityNumber.add(value);
    }

    public void setCitation(String value) {
        this._citation.add(value);
    }

    public void setCitationTitle(String value) {
        this._citationTitle.add(value);
    }

    public void setCreatorPersonal(String value) {
        this._creatorPersonal.add(value);
    }

    public void setCitationIdentifier(String value) {
        this._citationIdentifier.add(value);
    }

    public void setCitationNumber(String value) {
        this._citationNumber.add(value);
    }

    public void setCitationChronology(String value) {
        this._citationChronology.add(value);
    }

    public void setCreatorCorporate(String value) {
        this._creatorCorporate.add(value);
    }

    public void setCreatorConference(String value) {
        this._creatorConference.add(value);
    }

    public void setDateIssued(String value) {
        this._dateIssued.add(value);
    }

    public void setDescriptionNotes(String value) {
        this._descriptionNotes.add(value);
    }

    public void setDescriptionEdition(String value) {
        this._descriptionEdition.add(value);
    }

    public void setPublisherName(String value) {
        this._publisherName.add(value);
    }

    public void setPublisherPlace(String value) {
        this._publisherPlace.add(value);
    }

    public void setIsTranslationOf(String value) {
        this._isTranslationOf.add(value);
    }

    public void setHasTranslation(String value) {
        this._hasTranslation.add(value);
    }

    public void setRightsStatement(String value) {
        this._rightsStatement.add(value);
    }

    public void setTermsOfUse(String value) {
        this._termsOfUse.add(value);
    }

    public void setSubjectClassification(String value) {
        this._subjectClassification.add(value);
    }

    /**
     * @return the _availability
     */
    public ArrayList<String> getAvailability() {
        return _availability;
    }

    /**
     * @return the _creatorCorporate
     */
    public ArrayList<String> getCreatorCorporate() {
        return _creatorCorporate;
    }

    /**
     * @return the _citationNumber
     */
    public ArrayList<String> getCitationNumber() {
        return _citationNumber;
    }

    /**
     * @return the _creatorPersonal
     */
    public ArrayList<String> getCreatorPersonal() {
        return _creatorPersonal;
    }

    /**
     * @return the _availabilityNumber
     */
    public ArrayList<String> getAvailabilityNumber() {
        return _availabilityNumber;
    }

    /**
     * @return the _citationIdentifier
     */
    public ArrayList<String> getCitationIdentifier() {
        return _citationIdentifier;
    }

    /**
     * @return the _citationTitle
     */
    public ArrayList<String> getCitationTitle() {
        return _citationTitle;
    }

    /**
     * @return the _citationChronology
     */
    public ArrayList<String> getCitationChronology() {
        return _citationChronology;
    }

    /**
     * @return the _availabilityLocation
     */
    public ArrayList<String> getAvailabilityLocation() {
        return _availabilityLocation;
    }

    /**
     * @return the _citation
     */
    public ArrayList<String> getCitation() {
        return _citation;
    }

    /**
     * @return the _creatorConference
     */
    public ArrayList<String> getCreatorConference() {
        return _creatorConference;
    }

    /**
     * @return the _descriptionNotes
     */
    public ArrayList<String> getDescriptionNotes() {
        return _descriptionNotes;
    }

    /**
     * @return the _rightsStatement
     */
    public ArrayList<String> getRightsStatement() {
        return _rightsStatement;
    }

    /**
     * @return the _descriptionEdition
     */
    public ArrayList<String> getDescriptionEdition() {
        return _descriptionEdition;
    }

    /**
     * @return the _subjectClassification
     */
    public ArrayList<String> getSubjectClassification() {
        return _subjectClassification;
    }

    /**
     * @return the _termsOfUse
     */
    public ArrayList<String> getTermsOfUse() {
        return _termsOfUse;
    }

    /**
     * @return the _hasTranslation
     */
    public ArrayList<String> getHasTranslation() {
        return _hasTranslation;
    }

    /**
     * @return the _publisherPlace
     */
    public ArrayList<String> getPublisherPlace() {
        return _publisherPlace;
    }

    /**
     * @return the _publisherName
     */
    public ArrayList<String> getPublisherName() {
        return _publisherName;
    }

    /**
     * @return the _isTranslationOf
     */
    public ArrayList<String> getIsTranslationOf() {
        return _isTranslationOf;
    }
}
