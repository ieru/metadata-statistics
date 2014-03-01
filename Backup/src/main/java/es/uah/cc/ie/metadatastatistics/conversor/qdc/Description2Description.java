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
package es.uah.cc.ie.metadatastatistics.conversor.qdc;

import java.util.logging.Logger;
import org.jdom.Element;
import org.jdom.Namespace;
import es.uah.cc.ie.metadatastatistics.conversor.*;
//import org.ontspace.owl.util.AutomaticLangDetector;
//import org.ontspace.owl.util.LanguageISOHelper;

/**
 *
 * @author raquel
 */
public class Description2Description {
    
    //Language ISO Helper
    private LanguageISOHelper _langISOHelper = null;
    private AutomaticLangDetector _detector = null;


    public Description2Description(Element child, QualifiedDublinCore qdc) {
        String metadata = child.getName();

        Namespace xmlNS = Namespace.getNamespace("xml",
                "http://www.w3.org/XML/1998/namespace");
        Namespace dctermsNS = Namespace.getNamespace("dcterms",
                "http://purl.org/dc/terms/");
        _langISOHelper = new LanguageISOHelper();
        //4-Description
        if (metadata.compareTo("description") == 0) {
            
            String value = child.getTextTrim();
                //I try to obtain the language from the attribute in the XML
                String lang = child.getAttributeValue("lang", xmlNS);
                String iso_lang = null;
                if (lang != null) {
                    iso_lang = new String();
                    iso_lang = _langISOHelper.getISO_639_1_fromText(lang);
                }
                if (iso_lang == null) {
                    //I try to autodetec the language
                    if (_detector != null) {
                        iso_lang = _detector.detectLang(value);
                    } else {
                        iso_lang = "en";
                    }
                }
                qdc.addDescription(iso_lang, value);
            
        }

        //4.1-Table of contents
        if (metadata.compareTo("tableOfContents") == 0) {
            String value = child.getTextTrim();
               //I try to obtain the language from the attribute in the XML
                String lang = child.getAttributeValue("lang", xmlNS);
                String iso_lang = null;
                if (lang != null) {
                    iso_lang = new String();
                    iso_lang = _langISOHelper.getISO_639_1_fromText(lang);
                }
                if (iso_lang == null) {
                    //I try to autodetec the language
                    if (_detector != null) {
                        iso_lang = _detector.detectLang(value);
                    } else {
                        iso_lang = "en";
                    }
                }
                qdc.addTableOfContents(iso_lang, value);
        }

        //4.2-abstract
        if (metadata.compareTo("abstract") == 0) {
            String value = child.getTextTrim();
               //I try to obtain the language from the attribute in the XML
                String lang = child.getAttributeValue("lang", xmlNS);
                String iso_lang = null;
                if (lang != null) {
                    iso_lang = new String();
                    iso_lang = _langISOHelper.getISO_639_1_fromText(lang);
                }
                if (iso_lang == null) {
                    //I try to autodetec the language
                    if (_detector != null) {
                        iso_lang = _detector.detectLang(value);
                    } else {
                        iso_lang = "en";
                    }
                }
                qdc.addAbstract(iso_lang, value);
        }

    }
}
