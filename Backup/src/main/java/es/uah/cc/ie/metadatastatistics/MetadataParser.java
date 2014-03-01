package es.uah.cc.ie.metadatastatistics;

/**
 * interface to be implemented by all parsers: DCMetadataParser, QDCMetadataParser, Voa3rAPMetadataParser, etc.
 * @author flag
 */
public interface MetadataParser {

    /**
     * receives an Object with the resource (either a File to the case of xml) returns the metadata Resource refilled.
     * @param obj
     * @return Resource
     */
    public Resource parse(Object obj);
}
