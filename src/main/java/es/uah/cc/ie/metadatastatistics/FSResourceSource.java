package es.uah.cc.ie.metadatastatistics;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Attributes: String path: is the file system path where xml path is allocated.
 * AbstractResourceSource extends, and obtains xml file resources in the file system.
 * @author flag
 */
public class FSResourceSource extends AbstractResourceSource {

    private String path;

    FSResourceSource(String path, MetadataParser parser) {
        super(parser);
        this.path = path;
    }

    /**
     * Get the resource path
     * @return 
     */
    public String getPath() {
        return path;
    }
    
    /**
     * Create the iterator
     * @return  Iterator<Resource> 
     */
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