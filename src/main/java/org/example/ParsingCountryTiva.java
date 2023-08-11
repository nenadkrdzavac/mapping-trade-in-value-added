package org.example;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ParsingCountryTiva {


    public static void main(String[] args) throws IOException {



        String csvFile = "U:\\tiva-graphs\\exgr_bsci_2018_updated\\input\\exgr_bsci_2018_updated_1000_1.csv";
        String csvFileUpdated = "U:\\tiva-graphs\\exgr_bsci_2018_updated\\input\\exgr_bsci_2018_updated_1000_1_country.csv";

        getCountries(csvFile,csvFileUpdated);

    }

    public static void getCountries(String csvFile, String csvFileUpdated) throws FileNotFoundException {

        BufferedReader bufferReader = null;
        String line = "";
        String cvsSplitBy = ",";

        try (PrintWriter writer = new PrintWriter(csvFileUpdated)) {

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("country_c");
            stringBuilder.append(",");
            stringBuilder.append("country_iri");
            stringBuilder.append(",");
            stringBuilder.append('\n');

            writer.write(stringBuilder.toString());

            try {

                bufferReader = new BufferedReader(new FileReader(csvFile));

                int lineNumber = 1;

                HashMap<String, String> countryHashMap = new HashMap<String, String>();

                while ((line = bufferReader.readLine()) != null) {

                    if(lineNumber>1) {

                        // use comma as separator
                        String[] linesArray = line.split(cvsSplitBy);

                        if(!countryHashMap.containsKey(linesArray[0])){

                            countryHashMap.put(linesArray[0], "http://data.coypu.org/country/"+linesArray[0]);
                        }

                        if(!countryHashMap.containsKey(linesArray[2])){

                            countryHashMap.put(linesArray[2], "http://data.coypu.org/country/"+linesArray[2]);
                        }

                        System.out.println(linesArray[0] + "," + "http://data.coypu.org/country/"+linesArray[0] + ",");
                        System.out.println(linesArray[2] + "," + "http://data.coypu.org/country/"+linesArray[2] + ",");
                    }

                    if(lineNumber==10)break;

                    lineNumber++;
                }

                System.out.println("Unique list of country codes:");

                for(Map.Entry<String, String> m: countryHashMap.entrySet()) {

                    System.out.println(m.getKey()+","+m.getValue()+",");

                    writer.write(m.getKey()+","+m.getValue()+",");
                    writer.write("\n");

                }

                writer.close();
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

}
