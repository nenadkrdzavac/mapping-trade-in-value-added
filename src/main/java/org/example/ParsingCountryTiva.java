package org.example;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;

public class ParsingCountryTiva {


    public static void main(String[] args) throws IOException {

        HashMap<String,String> euCountriesMap = new HashMap<String, String>();

        String csvFile = "C:\\Users\\KrdzavacN\\git\\mapping-trade-in-value-added\\src\\main\\resources\\exgr_bsci_2018_updated_1000_1.csv";
        String csvFileUpdated = "C:\\Users\\KrdzavacN\\git\\mapping-trade-in-value-added\\src\\main\\resources\\exgr_bsci_2018_updated_1000_1_country.csv";
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

                while ((line = bufferReader.readLine()) != null) {

                    if(lineNumber>1) {

                        // use comma as separator
                        String[] linesArray = line.split(cvsSplitBy);

                        String s = "";

                        s = s + linesArray[0] + "," + "http://data.coypu.org/country/"+linesArray[0] + ",";

                        System.out.println(lineNumber+ "," + s);

                        writer.write(s);
                        writer.write("\n");

                        String p = "";

                        p = p + linesArray[2] + "," + "http://data.coypu.org/country/"+linesArray[2] + ",";

                        System.out.println(lineNumber+ "," + p);

                        writer.write(p);
                        writer.write("\n");



                    }

                    if(lineNumber==10)break;

                    lineNumber++;

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
