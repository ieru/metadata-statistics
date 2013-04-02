package es.uah.cc.ie.metadatastatistics;

import es.uah.cc.ie.metadatastatistics.conversor.dc.*;
import es.uah.cc.ie.metadatastatistics.parsers.DCXMLMetadataParser;
import es.uah.cc.ie.metadatastatistics.parsers.Voa3rAP2XMLMetadataParser;
import es.uah.cc.ie.metadatastatistics.schemas.DCMetadataSchema;
import es.uah.cc.ie.metadatastatistics.schemas.Voa3rAp2MetadataSchema;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static void main(String[] args) {
       
        //creamos un esquema y un parser de DC
      //  MetadataSchema dcSchema = new  MetadataSchema() {}; // debería ser new DCMetadataSchema()
         
        
        //MetadataSchema dcSchema=new  DCMetadataSchema();
         //DCXMLMetadataParser parser= new DCXMLMetadataParser();
        MetadataSchema voa3rAp2Schema = new Voa3rAp2MetadataSchema();
        Voa3rAP2XMLMetadataParser parser =  new Voa3rAP2XMLMetadataParser();
         
         
         //DublinCore parser= new DublinCore();
//        MetadataParser parser = new MetadataParser() {
//        
//            public Resource parse(Object obj) {
//                throw new UnsupportedOperationException("Not supported yet.");
//            }
//        }; // debería ser new DCXMLMetadataParser()
        
        //configuramos un nuevo ResourceSource: le damos la ruta con los recursos y el parser para parsearlos.
        ResourceSource source = new FSResourceSource("/home/flag/Desktop/oai.agris.cz_80_VOA3R/oai:aol/", parser);
        
        //Definimos una nueva muestra: le damos un nombre, el esquema de metadatos y la fuente de los recursos.
        ResourceSample sample = new ResourceSampleImpl("Example Repository", voa3rAp2Schema, source);

        System.out.println("La muestra " + sample.getName() + " tiene " + sample.size() + " recursos.");
        System.out.println(sample.countValid() + " recursos son válidos.");

        for (String field : sample.getSchema().getFields()) {
            try {
                System.out.println(sample.countHaveField(field) + " recursos tienen el atributo " + field);
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(App.class.getName()).log(Level.WARNING, "The field " + field + " isn't in the schema", ex);
            }
        }

    }
}
