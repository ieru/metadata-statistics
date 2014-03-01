package es.uah.cc.ie.metadatastatistics;

import java.util.HashMap;

public class Resource extends HashMap{

//    private HashMap<String, Object> metadata = new HashMap<String, Object>();
    private MetadataSchema schema;
    private boolean valid = true;

    /**
     * Contructor
     * @param ms 
     */
    public Resource(MetadataSchema ms) {
        this.schema = ms;
    }

    /**
     * it receives a string, which is the name of a metadata, and returns the value of the metadata if defined in the schema, otherwise it throws an exception NoSuchFieldException
     * @return MetadataSchema
     */
    public MetadataSchema getSchema() {
        return schema;
    }

    /**
     * it receives a string with the name of the metadata, and it receives an Object with the value and stores it in the hashmap
     * @param schema 
     */
    public void setSchema(MetadataSchema schema) {
        this.schema = schema;
    }

    /**
     * 
     * @param fieldName
     * @return Object
     * @throws NoSuchFieldException 
     */
    public Object get(String fieldName) throws NoSuchFieldException {
        assertInSchema(fieldName);
        return super.get(fieldName);
    }

    /**
     * 
     * @param fieldName
     * @param value
     * @throws NoSuchFieldException 
     */
    public void set(String fieldName, Object value) throws NoSuchFieldException {
        assertInSchema(fieldName);
        super.put(fieldName, value);
    }

    /**
     * 
     * @param fieldName
     * @throws NoSuchFieldException 
     */
    private void assertInSchema(String fieldName) throws NoSuchFieldException {
        if (!this.getSchema().getFields().contains(fieldName)) {
            throw new NoSuchFieldException("Invalid field");
        }
    }
    
    /**
     * 
     * @return boolean
     */
    public boolean isValid() {
        return this.valid;
    }
    
    /**
     * 
     * @param valid 
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
