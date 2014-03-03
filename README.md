metadata-statistics
===================

Metadata_statistics is a sotfware that gets statistics from a XML file or collection.

Execution
---------

To execute the metadata_statistics program ther are a few steps to follow.

java -jar ieru_metadata_statistics (XML file or folder) (Folder Output) (optional)(Template)

The meaning of each argument are defined and all the steps to follow are in Execution wike page (https://github.com/ieru/metadata-statistics/wiki/Execution)

Cases
-----
1.Count -> It counts all de references written in xpath action.

   1.2. Count with attribute 'option = "existences"', it counts if the element exists or not.

   1.3. Count with attribute 'special = "valueIsURI"', check if the value is a URI or not.

2.Mean -> Calculates the average of occurences of an element.

3.List -> Make simple list with the elements in xpath query.

4.Frequencies -> Calculates the frequency of ocurrences of each element indicates in xpath query.

5.Classify -> Classify the elements in base of many options like date, author, etc.

6.ClassifiedCount -> Classify the elements with no repetition and shows the number of apearances of each element.


For more information visit metadata-statistics wiki (https://github.com/ieru/metadata-statistics/wiki)
