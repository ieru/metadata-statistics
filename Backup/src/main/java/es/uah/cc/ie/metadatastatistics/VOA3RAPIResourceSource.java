package es.uah.cc.ie.metadatastatistics;

import java.io.File;
import java.io.FileFilter;
import java.util.*;

public class VOA3RAPIResourceSource extends AbstractResourceSource {

    private String repoid;

    VOA3RAPIResourceSource(String repoid, MetadataParser parser) {
        super(parser);
        this.repoid = repoid;
    }

    public String getRepoid() {
        return repoid;
    }
    
    @Override
    public Iterator<Resource> iterator() {
        
        List<HashMap> recordList = new ArrayList();
        
        List<String> idList = VOA3RAPIHelper.getRecordIdsFromRepo(repoid);
        for (String voa3rid : idList) {
            recordList.add(VOA3RAPIHelper.getRecord(voa3rid));
        }
      
        return new ResourceIterator(recordList, this.getParser());
    }
}