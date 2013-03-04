package es.uah.cc.ie.metadatastatistics;

import java.util.Iterator;
import java.util.List;

public class AbstractResourceSource implements ResourceSource {

    private MetadataParser parser;
    private List source;

    public AbstractResourceSource(MetadataParser parser) {
        this.parser = parser;
    }

    public Iterator<Resource> iterator() {
        return new ResourceIterator(this.getSource() , parser);
    }
    
    protected List getSource() {
        return this.source;
    }
}