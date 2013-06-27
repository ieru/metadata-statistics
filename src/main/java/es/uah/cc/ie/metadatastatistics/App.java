package es.uah.cc.ie.metadatastatistics;

import es.uah.cc.ie.metadatastatistics.parsers.AgrisAPMetadataParser;
import es.uah.cc.ie.metadatastatistics.parsers.DCXMLMetadataParser;
import es.uah.cc.ie.metadatastatistics.parsers.Voa3rAP2XMLMetadataParser;
import es.uah.cc.ie.metadatastatistics.parsers.Voa3rAP4XMLMetadataParser;
import es.uah.cc.ie.metadatastatistics.schemas.AgrisAPMetadataSchema;
import es.uah.cc.ie.metadatastatistics.schemas.DCMetadataSchema;
import es.uah.cc.ie.metadatastatistics.schemas.Voa3rAp2MetadataSchema;
import es.uah.cc.ie.metadatastatistics.schemas.Voa3rAp4MetadataSchema;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main Class
 *
 * @author flag
 */
public class App {

    /**
     * Enter point of this program. It recieves the Path and Schema Name     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
               MetadataSchema defaultSchema = null;
        MetadataParser parser = null;
        ResourceSource source = null;
        ResourceSample sample = null;
        String dirPath = "";
        String schema = "";

         String htmlFile = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 3.2//EN'>"
            + "<HTML>"
            + "<HEAD>"
            + "<META HTTP-EQUIV='CONTENT-TYPE' CONTENT='text/html; charset=utf-8'>"
            + "<TITLE></TITLE>"
            + "<META NAME='GENERATOR' CONTENT='LibreOffice 3.5  (Linux)'>"
            + "<META NAME='CREATED' CONTENT='20130529;12354500'>"
            + "<META NAME='CHANGED' CONTENT='20130529;12434800'>"
            + "<STYLE>"
            + "	<!-- "
            + "	BODY,DIV,TABLE,THEAD,TBODY,TFOOT,TR,TH,TD,P { font-family:'Liberation Sans'; font-size:x-small }"
            + "	 -->"
            + "</STYLE>"
            + "</HEAD>"
            + "<BODY TEXT='#000000'>"
            + "<TABLE CELLSPACING='0' COLS='2' BORDER='0'>"
            + "	<COLGROUP WIDTH='293'></COLGROUP>"
            + "	<COLGROUP WIDTH='60'></COLGROUP>"
            + "	<TR>"
            + "		<TD STYLE='border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: 1px solid #000000' HEIGHT='23' ALIGN='LEFT' BGCOLOR='#C0C0C0'><FONT FACE='Liberation Serif' SIZE=4>Mandatory</FONT></TD>"
            + "		<TD STYLE='border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: 1px solid #000000' ALIGN='LEFT' BGCOLOR='#C0C0C0'><FONT FACE='Liberation Serif'>&nbsp;</FONT></TD>"
            + "	</TR>";
           
        String sFichero = "./table.html";
        File tablaFile = new File(sFichero);
        BufferedWriter bw = new BufferedWriter(new FileWriter(tablaFile));
        bw.write(htmlFile);
        String type="";
        String subtype="  - ";
        String subsubtype="     * ";
        
        
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

                    sample = new ResourceSampleImpl("Example Repository", defaultSchema, source, countValuesOfFields);
                    System.out.println("The sample " + sample.getName() + " has " + sample.size() + " resources.");
                    System.out.println(sample.countValid() + " resources are valid.");
                    Map<String, String> table = new HashMap();
                    String fieldTable="";
                    for (String field : sample.getSchema().getFields()) {
                         type="";
                         fieldTable=field;
                        try {
                           
                            if(("title_without_lang".equals(field))||("creator_name".equals(field))||("creator_email".equals(field))||("creator_organization".equals(field))||("creator_foaf".equals(field))||("creator_uri".equals(field))
                                ||("contributor_name".equals(field))||("contributor_email".equals(field))||("contributor_organization".equals(field))||("contributor_foaf".equals(field))||("contributor_uri".equals(field))
                                ||("publisher_name".equals(field))||("publisher_email".equals(field))||("publisher_organization".equals(field))
                                ||("publisher_foaf".equals(field))||("identifier_URI".equals(field))||("identifier_ISBN".equals(field))||("identifier_ISSN".equals(field))||("identifier_DOI".equals(field))){
                                type=subtype;
                                
                            }
                            else
                            {
                                fieldTable=fieldTable.toUpperCase();
                            }
                            if(("creator".equals(field)))
                            {
                                 bw.write("<TR>"
                               + "		<TD STYLE='border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: 1px solid #000000' HEIGHT='23' ALIGN='LEFT' BGCOLOR='#C0C0C0'><FONT FACE='Liberation Serif' SIZE=4>Recommenderd</FONT></TD>"
            + "		<TD STYLE='border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: 1px solid #000000' ALIGN='LEFT' BGCOLOR='#C0C0C0'><FONT FACE='Liberation Serif'>&nbsp;</FONT></TD>"
                                + "</TR>");
                            }
                            
                            bw.write("<TR>"
                                + "<TD STYLE='border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: 1px solid #000000' HEIGHT='17' ALIGN='LEFT'><FONT FACE='Liberation Serif SIZE=10'>"+type +" "+ fieldTable + "(Element)</FONT></TD>"
                                + "<TD STYLE='border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: 1px solid #000000' ALIGN='LEFT'><FONT FACE='Liberation Serif'>" + sample.countHaveField(field) + "</FONT></TD>"
                                + "</TR>");
                            System.out.println(sample.countHaveField(field) + " has the " + field + " attribute. ");
                            table.put(field, String.valueOf(sample.countHaveField(field)));
                            

                        } catch (NoSuchFieldException ex) {
                            Logger.getLogger(App.class.getName()).log(Level.WARNING, "The field " + field + " isn't in the schema", ex);
                        }
                    }

                    for (String field : countValuesOfFields) {
                        System.out.println("Different values of:" + field + ": " + sample.valuesOfField(field));
                    }

                    bw.write("</TABLE>"
                        + "<!-- ************************************************************************** -->"
                        + "</BODY>"
                        + "</HTML>");
                    bw.close();






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
