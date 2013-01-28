package es.uah.cc.ie.metadatastatistics;

import java.util.Iterator;

public class AbstractResourceSource implements ResourceSource {

    private MetadataParser parser;

    public AbstractResourceSource(MetadataParser parser) {
        this.parser = parser;
    }

    public Iterator<Resource> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}