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

import org.jdom.Element;
import org.jdom.Namespace;

/**
 *
 * @author raquel
 */
public class Subject2Subject {
    
    public Subject2Subject(Element child, Agrisap agris) {
        String metadata = child.getName();
        
        Namespace xmlNS = Namespace.getNamespace("xml",
            "http://www.w3.org/XML/1998/namespace");

        //3-Subject
        if (metadata.compareTo("subject") == 0) {
            
            String value = child.getTextTrim();
            
            if (value.length() > 2) {
                agris.setSubject(value);
                
                
                
                
                
            }
        }        
        if (metadata.compareTo("subjectClassification") == 0) {
            String value = child.getTextTrim();
            if (value.length() > 2) {
                agris.setSubjectClassification(value);
            }
        }        
        if (metadata.compareTo("subjectThesaurus") == 0) {
            String lang = child.getAttributeValue("lang", xmlNS);
            String value = child.getTextTrim();
            if (lang == null) {                
                if (value.length() > 2) {
                    
                    value = value.replace("www.fao.org", "aims.fao.org");
                    value = value.replace("#", "/");
                    agris.setSubjectThesaurus(value);
                }
                
            } else if (lang.equals("en")) {
                agris.setSubjectThesaurus(value);
            }
            agris.setSubject(child.getTextTrim());
              if (child.getTextTrim().contains("http:")) {
                  agris.setSubjectThesaurus_URI(child.getTextTrim());
              }
              else
              {
                   agris.setSubjectThesaurus_TEXT(child.getTextTrim());
              }
            String scheme = child.getAttributeValue("scheme");
            if (scheme.contains("AGROVOC")) {
                
                
                if (child.getTextTrim().contains("http:")) {
                    agris.setSubjectThesaurus_AGROVOC_URI(child.getTextTrim());
                } else {
                    agris.setSubjectThesaurus_AGROVOC_TEXT(child.getTextTrim());
                }
                
            }
            if (scheme.contains("PO")) {
                
                
                if (child.getTextTrim().contains("http:")) {
                    agris.setSubjectThesaurus_PO_URI(child.getTextTrim());
                } else {
                    agris.setSubjectThesaurus_PO_TEXT(child.getTextTrim());
                }
                
            }
            if (scheme.contains("CABI")) {
                
                
                if (child.getTextTrim().contains("http:")) {
                    agris.setSubjectThesaurus_CABI_URI(child.getTextTrim());
                } else {
                    agris.setSubjectThesaurus_CABI_TEXT(child.getTextTrim());
                }
                
            }
            if (scheme.contains("ASFAT")) {
                
                
                if (child.getTextTrim().contains("http:")) {
                    agris.setSubjectThesaurus_ASFAT_URI(child.getTextTrim());
                } else {
                    agris.setSubjectThesaurus_ASFAT_TEXT(child.getTextTrim());
                }
                
            }
            if (scheme.contains("NALT")) {
                
                
                if (child.getTextTrim().contains("http:")) {
                    agris.setSubjectThesaurus_NALT_URI(child.getTextTrim());
                } else {
                    agris.setSubjectThesaurus_NALT_TEXT(child.getTextTrim());
                }
                
            }
        }
    }
}
