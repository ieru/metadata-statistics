package es.uah.cc.ie.metadatastatistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * It returns an ArrayList with the names of the metadata (title, description, date, author, etc.).
 * @author flag
 */
public class ResourceSampleImpl implements ResourceSample {

    private String name;
    private MetadataSchema schema;
    private ResourceSource source;
    private HashMap<String, Integer> haveFieldCounter = new HashMap<String, Integer>();
    private HashMap<String, HashMap<Object, Integer>> fieldValues = new HashMap<String, HashMap<Object, Integer>>();
    private int validCounter = 0;
    private int size = 0;

    public ResourceSampleImpl(String name, MetadataSchema ms, ResourceSource rs, ArrayList<String> countValuesOfFields) {
        this.name = name;
        this.schema = ms;
        this.source = rs;
        this.processResourceSource(countValuesOfFields);
    }

    public ResourceSampleImpl(String name, MetadataSchema ms, ResourceSource rs) {
        this(name, ms, rs, new ArrayList());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResourceSource getSource() {
        return source;
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
        if (this.haveFieldCounter.containsKey(field)) {
            return this.haveFieldCounter.get(field);
        } else {
            return 0;
        }
    }

    public HashMap<Object, Integer> valuesOfField(String field) {
        HashMap<Object, Integer> values = this.fieldValues.get(field);
        if (values == null) {
            values = new HashMap<Object, Integer>();
            this.fieldValues.put(field, values);
        }
        return values;
    }

    public MetadataSchema getSchema() {
        return this.schema;
    }

    public int countValid() {
        return this.validCounter;
    }

    public int incValid() {
        return ++this.validCounter;
    }

    public int size() {
        return this.size;
    }

    protected int incSize() {
        return ++this.size;
    }

    private void processResourceSource(ArrayList<String> countValuesOfFields) {
        ResourceSource src = this.getSource();
        ArrayList<String> fields = this.getSchema().getFields();
        for (Resource res : src) {
            for (String field : fields) {
                int count = 0;
                try {
                    count = this.countHaveField(field);
                    if (res.get(field) != null) {
                        count++;
                    }
                } catch (NoSuchFieldException ex) {
                    Logger.getLogger(ResourceSampleImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.haveFieldCounter.put(field, count);
                if (countValuesOfFields.contains(field)) {
                    procesValues(res, field);
                }
            }
            if (res.isValid()) {
                this.incValid();
            }
            this.incSize();
        }
    }

    private void incValue(HashMap<Object, Integer> hm, Object value) {
        Integer count = hm.get(value);
        if (count == null) {
            count = 0;
        }
        count++;
        hm.put(value, count);
    }

    private void procesValues(Resource res, String field) {
        HashMap<Object, Integer> valuesOfField = this.valuesOfField(field);
        Object value;
        try {
            value = res.get(field);
            if (value != null) {
                if (value instanceof List) {
                    List<Object> valueList = (List<Object>) value;
                    for (Object v : valueList) {
                        this.incValue(valuesOfField, v);
                    }
                } else {
                    incValue(valuesOfField, value);
                }
            }
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(ResourceSampleImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
