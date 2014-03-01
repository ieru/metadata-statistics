metadata-statistics
===================


Extracts statistics from a set of metadata records following a metadata schema

INPUT AND EXECUTION
--------------

Java –jar metadata-statistics.jar << XML_PATH >><< METADATA SCHEMA >>

-> XML_PATH= Full, path of the xml repository. As this script creates a resource representation, the folder(s) 
  –   This will work correctly depending on the memory amount allocated to the JVM 
  and the number of resources included in the folder (including subfolders) 

-> METADATA SCHEMA = the xml metadata Schema: voa3rAP2 | voa3rAP4 | dublinCore | Agris


OUPUT
---------

Returns a list of metadata schema and the number of occurrences within the requested sample.

