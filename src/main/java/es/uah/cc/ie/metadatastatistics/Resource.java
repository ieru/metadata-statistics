package es.uah.cc.ie.metadatastatistics;

import java.util.HashMap;

public class Resource {

    private HashMap<String, Object> metadata = new HashMap<String, Object>();
    private MetadataSchema schema;
    private boolean valid = false;

    public Resource(MetadataSchema ms) {
        this.schema = ms;
    }

    public MetadataSchema getSchema() {
        return schema;
    }

    public void setSchema(MetadataSchema schema) {
        this.schema = schema;
    }

    public Object get(String fieldName) throws NoSuchFieldException {
        assertInSchema(fieldName);
        return this.metadata.get(fieldName);
    }

    public void set(String fieldName, Object value) throws NoSuchFieldException {
        assertInSchema(fieldName);
        this.metadata.put(fieldName, value);
    }

    private void assertInSchema(String fieldName) throws NoSuchFieldException {
        if (!this.getSchema().getFields().contains(fieldName)) {
            throw new NoSuchFieldException("Invalid field");
        }
    }

    public boolean isValid() {
        return this.valid;
    }
    
    public void setValid(boolean valid) {
        this.valid = valid;
    }
}