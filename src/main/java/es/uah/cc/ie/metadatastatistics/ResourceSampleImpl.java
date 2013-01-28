package es.uah.cc.ie.metadatastatistics;

import java.util.HashMap;

public class ResourceSampleImpl implements ResourceSample {

    private String name;
    private MetadataSchema schema;
    private ResourceSource source;
    private HashMap<String, Integer> haveFieldCounter = new HashMap<String, Integer>();
    private int validCounter = 0;

    public ResourceSampleImpl(String name, MetadataSchema ms, ResourceSource rs) {
        this.name = name;
        this.schema = ms;
        this.source = rs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public final int countHaveField(String field) throws NoSuchFieldException {
        if (!getSchema().getFields().contains(field)) {
            throw new NoSuchFieldException("The resources in this ResourceSample do not have this field");
        }
        return this._countHaveField(field);
    }

    /**
     * Counts the number of resources in this ResourceSample which has certain
     * metadata field. This method is called by contHasField when the field
     * exists. This method does not have to check the field existence.
     *
     * @param field the field the resources must have.
     * @return the number of resources which has the specified metadata field.
     */
    protected int _countHaveField(String field) {
        return this.haveFieldCounter.get(field);
    }

    public MetadataSchema getSchema() {
        return this.schema;
    }

    public int countValid() {
        return this.validCounter;
    }

    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}