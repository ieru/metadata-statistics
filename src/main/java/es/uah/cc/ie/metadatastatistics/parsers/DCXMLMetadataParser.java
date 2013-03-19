/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uah.cc.ie.metadatastatistics.parsers;
import es.uah.cc.ie.metadatastatistics.*;
import es.uah.cc.ie.metadatastatistics.conversor.dc.*;
import java.io.File;

/**
 *
 * @author flag
 */
public class DCXMLMetadataParser  implements MetadataParser{

    public Resource parse(Object obj) {
        File obj2 = (File) obj;
        DublinCore dc;
        dc = new DublinCore(obj2);
        
        dc.parseDCXMLStats(obj2.getName());
        throw new UnsupportedOperationException("Not supported yet.");


    }
    
}
