package es.uah.cc.ie.metadatastatistics;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FSResourceSource extends AbstractResourceSource {

    private String path;

    FSResourceSource(String path, MetadataParser parser) {
        super(parser);
        this.path = path;
    }

    public String getPath() {
        return path;
    }
    
    @Override
    public Iterator<Resource> iterator() {
        FileFilter xmlFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                 
                return (file.isFile() && file.getName().endsWith(".xml") && !file.getName().equals("deletedRecords.xml"));
            }
        };
        
        File dir = new File(this.getPath());
        List<File> listXmlFiles = new ArrayList();
        if (dir.isDirectory()) {
            if (!dir.exists()) {
                System.err.println("Error: The Directory doesn't exist");
            }
            File[] files = dir.listFiles(xmlFilter);
            listXmlFiles.addAll(Arrays.asList(files));
        }
      
        return new ResourceIterator(listXmlFiles, this.getParser());
    }
}