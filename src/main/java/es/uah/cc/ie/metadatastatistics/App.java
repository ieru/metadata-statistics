package es.uah.cc.ie.metadatastatistics;

import es.uah.cc.ie.metadatastatistics.parsers.DCXMLMetadataParser;
import es.uah.cc.ie.metadatastatistics.parsers.JSONMetadataParser;
import es.uah.cc.ie.metadatastatistics.schemas.DCMetadataSchema;
import es.uah.cc.ie.metadatastatistics.schemas.Voa3rAp2MetadataSchema;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

public class App {

    public static void main(String[] args) {
        
        //creamos un esquema y un parser de VAP2
        MetadataSchema dcSchema = new DCMetadataSchema();
        MetadataParser parser = new DCXMLMetadataParser();

        //configuramos un nuevo ResourceSource: le damos la ruta con los recursos y el parser para parsearlos.
        ResourceSource source = new FSResourceSource("/home/link87/job/harvesting_affiliation_all/avano/result/", parser);

        //Definimos una nueva muestra: le damos un nombre, el esquema de metadatos y la fuente de los recursos.
        ResourceSample sample = new ResourceSampleImpl("Example Repository", dcSchema, source);

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
