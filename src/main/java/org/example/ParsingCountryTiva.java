package org.example;

import java.io.*;
import java.util.*;

public class ParsingCountryTiva {

    public static void main(String[] args) throws IOException {

        ArrayList<String> folderList = new ArrayList<String>();

        folderList.add("U:\\tiva-graphs\\exgr_bsci_2018_updated\\input\\");
//        folderList.add("U:\\tiva-graphs\\fd_exgr_va_2018_updated\\input\\");
        folderList.add("U:\\tiva-graphs\\fdva_bsci_2018_updated\\input\\");
//        folderList.add("U:\\tiva-graphs\\imgr_bsci_2018_updated\\input\\");

        HashMap<String, String> finalMap = new HashMap<String, String>();

        for(String graphFolderPath: folderList) {

            File folderPath = new File(graphFolderPath);

            String csvFileCountryCode = graphFolderPath + "country_codes.csv";

            HashMap<String, String> cmap = new HashMap<String, String>();

                for (File f : folderPath.listFiles()) {

                    for (Map.Entry<String, String> m : getCountries(f.getAbsolutePath(), "").entrySet()) {

                        if (!cmap.containsKey(cmap.containsKey(m.getKey()))) {

                        cmap.put(m.getKey(), m.getValue());

                        }
                    }
                }

                for (Map.Entry<String, String> map : cmap.entrySet()) {

                    if(!finalMap.containsKey(map.getKey())){

                    finalMap.put(map.getKey(),map.getValue());

                    }
                }
        }

        try (PrintWriter writer = new PrintWriter("U:\\tiva-graphs\\final_country_codes.csv")) {

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("country_c");
            stringBuilder.append(",");
            stringBuilder.append("country_iri");
            stringBuilder.append(",");
            stringBuilder.append('\n');

            writer.write(stringBuilder.toString());

        System.out.println("Final map for " + finalMap.size() + " country codes: ");

        for(Map.Entry<String, String> fmap: finalMap.entrySet()){

            System.out.println(fmap.getKey() + "," + fmap.getValue());

            writer.write(fmap.getKey() + "," + fmap.getValue() + ",");

            writer.write("\n");

        }
            writer.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        }

    }

    /**
     *
     * Extract country codes and assigns IRIs to each country code.
     *
     * @param csvFile
     * @param csvFileUpdated
     * @throws FileNotFoundException
     */
    public static HashMap<String, String> getCountries(String csvFile, String csvFileUpdated) throws FileNotFoundException {

        BufferedReader bufferReader = null;
        String line = "";
        String cvsSplitBy = ",";

        HashMap<String, String> countryHashMap = new HashMap<String, String>();


            try {

                bufferReader = new BufferedReader(new FileReader(csvFile));

                int lineNumber = 1;

                while ((line = bufferReader.readLine()) != null) {

                    if(lineNumber>1) {

                        // use comma as separator
                        String[] linesArray = line.split(cvsSplitBy);

                        if(!countryHashMap.containsKey(linesArray[0])){

                        countryHashMap.put(linesArray[0], "http://data.coypu.org/country/" + linesArray[0]);

                        }

                        if(!countryHashMap.containsKey(linesArray[2])){

                        countryHashMap.put(linesArray[2], "http://data.coypu.org/country/"+linesArray[2]);

                        }
                    }

//              if(lineNumber==10)break;

                lineNumber++;

                }

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
            return countryHashMap;
    }
}