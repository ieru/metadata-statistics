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
 *
 * @author flag
 */
public class DCXMLMetadataParser  implements MetadataParser{
    

        
   
    public Resource parse(Object obj) {
      
        Resource res= new Resource(new DCMetadataSchema());
        DublinCore dc;
        dc = new DublinCore((File)obj);
        dc.parseDCXML();
        try { 
            
            res.set("title", dc.getTitles());
            res.set("contributor", dc.getContributors());
            res.set("coverage", dc.getCoverages());
            //System.out.println((File) obj);
        } catch (NoSuchFieldException ex) {
           
            Logger.getLogger(DCXMLMetadataParser.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return res;


    }
    
}
