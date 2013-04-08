package es.uah.cc.ie.metadatastatistics;

import es.uah.cc.ie.metadatastatistics.conversor.dc.*;
import es.uah.cc.ie.metadatastatistics.parsers.AgrisAPMetadataParser;
import es.uah.cc.ie.metadatastatistics.parsers.DCXMLMetadataParser;
import es.uah.cc.ie.metadatastatistics.parsers.Voa3rAP2XMLMetadataParser;
import es.uah.cc.ie.metadatastatistics.parsers.Voa3rAP4XMLMetadataParser;
import es.uah.cc.ie.metadatastatistics.schemas.AgrisAPMetadataSchema;
import es.uah.cc.ie.metadatastatistics.schemas.DCMetadataSchema;
import es.uah.cc.ie.metadatastatistics.schemas.Voa3rAp2MetadataSchema;
import es.uah.cc.ie.metadatastatistics.schemas.Voa3rAp4MetadataSchema;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class App {

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
                    //DublinCore parser= new DublinCore();
//        MetadataParser parser = new MetadataParser() {
//        
//            public Resource parse(Object obj) {
//                throw new UnsupportedOperationException("Not supported yet.");
//            }
//        }; // debería ser new DCXMLMetadataParser()
///home/flag/Desktop/oai.agris.cz_80_VOA3R/oai:aol/
                    //configuramos un nuevo ResourceSource: le damos la ruta con los recursos y el parser para parsearlos.


                    //Definimos una nueva muestra: le damos un nombre, el esquema de metadatos y la fuente de los recursos.
                    source = new FSResourceSource(dirPath, parser);
                    sample = new ResourceSampleImpl("Example Repository", defaultSchema, source);
                    System.out.println("The sample " + sample.getName() + " has " + sample.size() + " resources.");
                    System.out.println(sample.countValid() + " resources are valid.");

                    for (String field : sample.getSchema().getFields()) {
                        try {
                            System.out.println(sample.countHaveField(field) + " has the " + field + " attribute. ");
                        } catch (NoSuchFieldException ex) {
                            Logger.getLogger(App.class.getName()).log(Level.WARNING, "The field " + field + " isn't in the schema", ex);
                        }
                    }

                } else {
                    help("");

                }
            } else {

                help("XML directory required, please type java -jar activity2event.jar 'xmls directory path'");

            }
        } catch (Exception e) {
            // help("Error. See the message at the end");
            help("Error: " + e.getMessage());
        }
        //creamos un esquema y un parser de DC
        //  MetadataSchema dcSchema = new  MetadataSchema() {}; // debería ser new DCMetadataSchema()


        //MetadataSchema dcSchema=new  DCMetadataSchema();
        //DCXMLMetadataParser parser= new DCXMLMetadataParser();


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

        public static dataSchema toDataSchema(String str) {
            try {
                return valueOf(str);
            } catch (IllegalArgumentException ex) {
                return null;
            }
        }
    }
}
