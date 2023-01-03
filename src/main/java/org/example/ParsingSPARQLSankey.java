package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ParsingSPARQLSankey {

    public static void main(String[] args) throws IOException {

        String csvFile = "C:\\Users\\KrdzavacN\\git\\mapping-trade-in-value-added\\mapping-trade-in-value-added\\src\\main\\resources\\sparql_results\\q9-sankey.csv";

        updateCsv(csvFile);

    }

    public static void updateCsv(String csvFile) throws FileNotFoundException {

        HashMap<String, Integer> countriesMap = new HashMap<String, Integer>();

        BufferedReader bufferReader = null;
        String line = "";
        String cvsSplitBy = ",";



            try {

                bufferReader = new BufferedReader(new FileReader(csvFile));

                int lineNumber = 0;
                int keyValue = 0;

                while ((line = bufferReader.readLine()) != null) {

                    // use comma as separator
                    String[] lines = line.split(cvsSplitBy);

                    lineNumber++;

//            System.out.println( lineNumber + " " + lines[0] + " " + lines[1] + " " + lines[2]);

                    if (!countriesMap.containsKey(lines[0])) {

                        countriesMap.put(lines[0], keyValue);

                        System.out.println(keyValue + ". " + lines[0]);
//                System.out.println(lines[0]+",");

                        keyValue++;

                    }

                    if (!countriesMap.containsKey(lines[1])) {

                        countriesMap.put(lines[1], keyValue);

                        System.out.println(keyValue + ". " + lines[1]);
//                  System.out.println(lines[1]+",");

                        keyValue++;

                    }


                }


//            for(Map.Entry<String, Integer> map:countriesMap.entrySet()){
//            System.out.println(map.getKey() + " " + map.getValue());

//            }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferReader != null) {
                    try {
                        bufferReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

    }
}
