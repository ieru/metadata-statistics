package es.uah.cc.ie.metadatastatistics;

import es.uah.cc.ie.metadatastatistics.conversor.agrisap.FAOMetadataHelper;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;

/**
 * 
 * @author flag
 */
public class FSAgrisResourceSource extends AbstractResourceSource {
    
    private String path;
    
    /**
     * FSAgrisResourceSource Constructor
     * @param path
     * @param parser 
     */
    
    FSAgrisResourceSource(String path, MetadataParser parser) {
        
        super(parser);
        this.path = path;
    }
    
    /**
     * Get the path value
     * @return 
     */
    public String getPath() {
        return path;
    }
    
    /**
     * Iterator from xml resources
     * @return 
     */
    @Override
    public Iterator<Resource> iterator() {
        File dir = new File(this.getPath());
        List<File> listXmlFiles = new ArrayList();
        List<File> fileStringList = new ArrayList();
        fileStringList = directoryList(this.getPath(), fileStringList);
        System.out.println(fileStringList.size());
        listXmlFiles = fileStringList;
        List<Element> agrisResources = new ArrayList();
        FAOMetadataHelper faoMetadataHelper;
        int cont = 0;
        for (File f : listXmlFiles) {
            cont++;
            System.out.print(cont + ", ");
            //System.out.println(listXmlFiles.size());
            faoMetadataHelper = new FAOMetadataHelper(f);
            faoMetadataHelper.parseAgrisapXML();
            HashMap<String, Element> resources = faoMetadataHelper.getAgsResource();
            agrisResources.addAll(resources.values());
        }
        System.out.println();
       
        return new ResourceIterator(agrisResources, this.getParser());
    }
    
    /**
     * Retrieve all the resources in file tree from path
     * @param dirPath
     * @param fileStringList
     * @return List<File>
     */
    public List<File> directoryList(String dirPath, List<File> fileStringList) {
        FileFilter xmlFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                
                return (file.isFile() && file.getName().endsWith(".xml") && !file.getName().equals("deletedRecords.xml"));
            }
        };
        try {
            File dir = new File(dirPath);
            File[] agfolders = dir.listFiles();
            File[] agfiles = dir.listFiles(xmlFilter);
            fileStringList.addAll(Arrays.asList(agfiles));
            
            for (int x = 0; x < agfolders.length; x++) {
                
                if (agfolders[x].isDirectory()) {                    
                    directoryList(agfolders[x].getAbsolutePath(), fileStringList);
                } else {
                    //System.out.println(agfolders[x].getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return fileStringList;
    }
}
