package es.uah.cc.ie.metadatastatistics;

import java.util.Iterator;


public class FSResourceSource extends AbstractResourceSource {

  private String path;
  
  FSResourceSource(String path, MetadataParser parser) {
    super(parser);
    this.path = path;
  }

  @Override
  public Iterator<Resource> iterator() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
}