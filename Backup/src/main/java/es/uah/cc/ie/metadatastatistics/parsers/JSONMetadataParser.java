/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uah.cc.ie.metadatastatistics.parsers;

import es.uah.cc.ie.metadatastatistics.MetadataParser;
import es.uah.cc.ie.metadatastatistics.MetadataSchema;
import es.uah.cc.ie.metadatastatistics.Resource;
import org.json.simple.JSONObject;

/**
 *
 * @author flag
 */
public class JSONMetadataParser implements MetadataParser {
    
    MetadataSchema ms;
    
    public JSONMetadataParser(MetadataSchema ms) {
        this.ms = ms;
    }

    public Resource parse(Object obj) {
        
        Resource res = new Resource(this.ms);
        JSONObject json = (JSONObject) obj;
        for (Object key : json.keySet()) {
            res.put(key, json.get(key));
        }
        return res;
    }
}
