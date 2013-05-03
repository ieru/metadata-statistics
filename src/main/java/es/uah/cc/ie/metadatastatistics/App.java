package es.uah.cc.ie.metadatastatistics;

import es.uah.cc.ie.metadatastatistics.parsers.AgrisAPMetadataParser;
import es.uah.cc.ie.metadatastatistics.parsers.DCXMLMetadataParser;
import es.uah.cc.ie.metadatastatistics.parsers.Voa3rAP2XMLMetadataParser;
import es.uah.cc.ie.metadatastatistics.parsers.Voa3rAP4XMLMetadataParser;
import es.uah.cc.ie.metadatastatistics.schemas.AgrisAPMetadataSchema;
import es.uah.cc.ie.metadatastatistics.schemas.DCMetadataSchema;
import es.uah.cc.ie.metadatastatistics.schemas.Voa3rAp2MetadataSchema;
import es.uah.cc.ie.metadatastatistics.schemas.Voa3rAp4MetadataSchema;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main Class
 * @author flag
 */
public class App {

    /**
     * Enter point of this program. It recieves the Path and Schema Name
     * @param args 
     */
    public static void main(String[] args) {
        MetadataSchema defaultSchema = null;
        MetadataParser parser = null;
        ResourceSource source = null;
        ResourceSample sample = null;
        String dirPath = "";
        String schema = "";

        try {
            if (args.length != 0) {

                if (!"--?".equals(args[0])) {
                    dirPath = args[0];
                    if (args.length == 2) {
                        schema = args[1];
                    }

                     //create a schema and parser 
                    switch (dataSchema.toDataSchema(schema)) {
                        case voa3rAP2:
                            defaultSchema = new Voa3rAp2MetadataSchema();
                            parser = new Voa3rAP2XMLMetadataParser();

                            break;
                        case voa3rAP4:
                            defaultSchema = new Voa3rAp4MetadataSchema();
                            parser = new Voa3rAP4XMLMetadataParser();


                            break;
                        case dublinCore:
                            defaultSchema = new DCMetadataSchema();
                            parser = new DCXMLMetadataParser();


                            break;
                        case Agris:
                            defaultSchema = new AgrisAPMetadataSchema();
                            parser = new AgrisAPMetadataParser();
                            break;

                        default:
                            help("");
                            break;

                    }
                 
                    //configure a new resourceSource: we give the path to the resources and the parser to parse it


                    //We define a new sample: give it a name, the metadata schema and source of resources.
                    if (!schema.equals("Agris")) {
                        source = new FSResourceSource(dirPath, parser);
                    } else {

                        source = new FSAgrisResourceSource(dirPath, parser);
                    }
                  
                    ArrayList<String> countValuesOfFields = new ArrayList<String>();
                    countValuesOfFields.add("type");
                    countValuesOfFields.add("format");
                   
                    sample = new ResourceSampleImpl("Example Repository", defaultSchema, source,countValuesOfFields);
                    System.out.println("The sample " + sample.getName() + " has " + sample.size() + " resources.");
                    System.out.println(sample.countValid() + " resources are valid.");
                    Map<String, String> table = new HashMap();

                    for (String field : sample.getSchema().getFields()) {
                        try {
                            System.out.println(sample.countHaveField(field) + " has the " + field + " attribute. ");
                            table.put(field, String.valueOf(sample.countHaveField(field)));

                        } catch (NoSuchFieldException ex) {
                            Logger.getLogger(App.class.getName()).log(Level.WARNING, "The field " + field + " isn't in the schema", ex);
                        }
                    }
                    for (String field : countValuesOfFields) {
                        System.out.println("Different values of:" +field + ": " +  sample.valuesOfField(field));
                    }
                    
//                    String sFichero = "/home/flag/tabla.txt";
//                    try {
//                        File tablaFile = new File(sFichero);
//                        try {
//                            BufferedWriter bw = new BufferedWriter(new FileWriter(tablaFile));
//                            //for (int x = 0; x < 57; x++) {
//                            if (schema.equals("voa3rAP4")) {
//                                bw.write(table.get("title"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("language"));
//                                bw.write("\n");
//                                bw.write(table.get("type"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("creator"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("creator_name"));
//                                bw.write("\n");
//                                bw.write(table.get("creator_organization"));
//                                bw.write("\n");
//                                bw.write(table.get("creator_email"));
//                                bw.write("\n");
//                                bw.write(table.get("contributor"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("contributor_name"));
//                                bw.write("\n");
//                                bw.write(table.get("contributor_organization"));
//                                bw.write("\n");
//                                bw.write(table.get("contributor_email"));
//                                bw.write("\n");
//                                bw.write(table.get("publisher"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("publisher_name"));
//                                bw.write("\n");
//                                bw.write(table.get("publisher_organization"));
//                                bw.write("\n");
//                                bw.write(table.get("publisher_email"));
//                                bw.write("\n");
//                                bw.write(table.get("date"));
//                                bw.write("\n");
//                                bw.write(table.get("identifier"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("rights"));
//                                bw.write("\n");
//                                bw.write(table.get("accessRights"));
//                                bw.write("\n");
//                                bw.write(table.get("license"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("subject"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("description"));
//                                bw.write("\n");
//                                bw.write(table.get("bibligraphicCitation"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("publicationStatus"));
//                                bw.write("\n");
//                                bw.write(String.valueOf(sample.countValid()));
//                            } else if (schema.equals("voa3rAP2")) {
//                                bw.write(table.get("title"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("language"));
//                                bw.write("\n");
//                                bw.write(table.get("type"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("creator"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("contributor"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("publisher"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("date"));
//                                bw.write("\n");
//                                bw.write(table.get("identifier"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("rights"));
//                                bw.write("\n");
//                                bw.write(table.get("accessRights"));
//                                bw.write("\n");
//                                bw.write(table.get("license"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("subject"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("description"));
//                                bw.write("\n");
//                                bw.write(table.get("bibligraphicCitation"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("publicationStatus"));
//                                bw.write("\n");
//                                bw.write(String.valueOf(sample.countValid()));
//                            } else if (schema.equals("Agris")) {
//                                bw.write(table.get("title"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("language"));
//                                bw.write("\n");
//                                bw.write(table.get("type"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("creator"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                              
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("publisher"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("date"));
//                                bw.write("\n");
//                                bw.write(table.get("identifier"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("rights"));
//                                bw.write("\n");
//                                bw.write("\n");
//                            
//                          
//                                bw.write("\n");
//                                bw.write(table.get("rightsStatement"));
//                                bw.write("\n");
//                                bw.write(table.get("subject"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(table.get("abstract"));
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                          
//                                bw.write("\n");
//                           
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write("\n");
//                                bw.write(String.valueOf(sample.countValid()));
//                            }
//
//                            bw.close();
//                        } catch (IOException ioe) {
//                            System.out.println("Error dentro");
//                            ioe.printStackTrace();
//                        }
//
//                    } catch (Exception e) {
//                        System.out.println("Error fuera");
//                        e.printStackTrace();
//                    }


                } else {
                    help("");

                }
            } else {

                help("XML directory and schema is required");

            }
        } catch (Exception e) {
         
            help("Error: " + e.toString());
        }
    }

    static void help(String message) {
        System.out.println("");
        System.out.println("-------------------------------------------");
        System.out.println(message);
        System.out.println("");
        System.out.println("metadata-statistics");
        System.out.println("-------------------");
        System.out.println("");
        System.out.println("java -jar metadata-statistics.jar <<XML_INPUT_PATH>>, <<METADATA SCHEMA>>");
        System.out.println("");
        System.out.println("METADATA CHEMA");
        System.out.println("");
        System.out.println("voa3rAP2 | voa3rAP4 | dublinCore | Agris");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("PARAMS");
        System.out.println("XML INPUT PATH = the xml repository folder");
        System.out.println("METADATA SCHEMA = the xml metadata Schema: voa3rAP2 | voa3rAP4 | dublinCore | Agris");
        System.out.println("-------------------------------------------");
        System.out.println("");



    }
    
    public enum dataSchema {

        voa3rAP2, voa3rAP4, dublinCore, Agris;

        /**
         * 
         * @param str
         * @return dataSchema
         */
        public static dataSchema toDataSchema(String str) {
            try {
                return valueOf(str);
            } catch (IllegalArgumentException ex) {
                return null;
            }
        }
    }
}
