package org.example;

import java.io.*;

public class ParsingNASACsv {

    public static void main(String[] args) throws FileNotFoundException {

        String csvFile = "C:\\Users\\KrdzavacN\\git\\mapping-trade-in-value-added\\mapping-trade-in-value-added\\src\\main\\resources\\all_descriptions_final.txt";
        String csvFileAIP = "C:\\Users\\KrdzavacN\\git\\mapping-trade-in-value-added\\mapping-trade-in-value-added\\src\\main\\resources\\all_descriptions_finals_updated.csv";

        updateCsvAIP(csvFile, csvFileAIP);

    }

    public static void updateCsvAIP(String csvFile, String csvFileUpdated) throws FileNotFoundException {

        BufferedReader bufferReader = null;
        String line = "";
        String cvsSplitBy = ",";

        try (PrintWriter writer = new PrintWriter(csvFileUpdated)) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("aip");
        stringBuilder.append(",");
        stringBuilder.append("entity");
        stringBuilder.append(",");
//      stringBuilder.append('\n');

        writer.write(stringBuilder.toString());

        try {

                bufferReader = new BufferedReader(new FileReader(csvFile));

                int lineNumber = 1;
                String firstLine ="";

                while ((line = bufferReader.readLine()) != null) {

//                  if(lineNumber==1) {
                    if(line.startsWith("urn:nasa")) {

                    System.out.println(line);

                    firstLine=line.toString();

//                  writer.write(line.toString());

                    }

                    if(line.contains("https://www.wikidata.org/wiki")){

                    String[] split_string = line.split(" ");

                    String replaced_wikidata_url= split_string[1].replace("https://www.wikidata.org/wiki","http://www.wikidata.org/entity");

                    System.out.println(split_string[1] + " " + replaced_wikidata_url);

                    writer.write(firstLine + ", "+ replaced_wikidata_url + ", ");

                    }

                   writer.write("\n");

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