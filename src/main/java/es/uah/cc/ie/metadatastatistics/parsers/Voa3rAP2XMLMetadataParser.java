/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uah.cc.ie.metadatastatistics.parsers;

import es.uah.cc.ie.metadatastatistics.*;
import es.uah.cc.ie.metadatastatistics.conversor.Voa3rAP2.*;

import java.io.File;

/**
 *
 * @author flag
 */
public class Voa3rAP2XMLMetadataParser {

    public Resource parse(Object obj) {
        File obj2 = (File) obj;
        Voa3rAP2 vap2;
        vap2 = new Voa3rAP2(obj2);
        vap2.parseVoa3rAP2XML();
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
