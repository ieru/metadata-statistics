package es.uah.cc.ie.metadatastatistics;

import java.util.HashMap;

/**
 * represents a statistical sample of resources.
 * @author flag
 */
public interface ResourceSample {

    /**
     * Returns the metadata schema of the resources in this sample
     *
     * @return the metadata schema of the resources in this sample
     */
    public MetadataSchema getSchema();
    
    /**
     * Gets the name of the sample
     * @return the name of the resource sample 
     */
    public String getName();

    /**
     * Sets the name of the sample
     * @param name the name of the resource sample
     */
    public void setName(String name);
    
    /**
     * Counts the number of resources in this sample which has certain metadata
     * field.
     *
     * @param field the field the resources must have.
     * @return the number of resources which has the specified metadata field.
     */
    public int countHaveField(String field) throws NoSuchFieldException;
    
    /**
     * Returns the different values (and a count of occurences) of the given field.
     *
     * @param field the field the resources must have.
     * @return the number of resources which has the specified metadata field.
     */
    public HashMap<Object, Integer> valuesOfField(String field);

    /**
     * Returns the number of valid resources in this sample. A resource is valid
     * if it can be parsed, has the required fields and the values of the fields
     * are of the correct type.
     *
     * @return the number of valid resources in this sample
     */
    public int countValid();

    /**
     * Returns the number of resources in this sample
     *
     * @return the size of the sample
     */
    public int size();
}
