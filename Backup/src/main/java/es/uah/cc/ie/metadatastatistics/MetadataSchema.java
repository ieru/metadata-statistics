package es.uah.cc.ie.metadatastatistics;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Abstract class that represents a metadata schema, each new metadata schema must extend this class.
 * @author link87
 */
public abstract class MetadataSchema {

    private ArrayList<String> fields;

    /**
     * Constructor
     * @author flag
     */
    public MetadataSchema() {
       this.fields = new ArrayList<String>();
    }

    /**
     * Obtain fields
     * @return ArrayList<String>
     */
    public final ArrayList<String> getFields() {
        return this.fields;
    }

    /**
     * Adds a new field to the MetadataSchema
     *
     * @param fieldName the new field
     */
    protected final void declareField(String fieldName) {
        this.fields.add(fieldName);
    }

    /**
     * Adds a new collection of fields to the MetadataSchema
     *
     * @param fields the collection of fields to be added.
     */
    protected final void declareFields(Collection<String> fields) {
        //System.out.println(fields);
        this.fields.addAll(fields);
    }
}
