package org.example;

import java.io.*;
import java.util.*;

public class ParsingCountryTiva {

    public static void main(String[] args) throws IOException {

        String fPath = "U:\\tiva-graphs\\final_country_codes_1_2_4.csv";
        ArrayList<String> folderList = new ArrayList<String>();

//      0-2
//      folderList.add("U:\\tiva-graphs\\exgr_bsci_2018_updated\\input\\");
//      folderList.add("U:\\tiva-graphs\\fdva_bsci_2018_updated\\input\\");

//      0-1-3
//        folderList.add("U:\\tiva-graphs\\imgr_bsci_2018_updated\\input\\");

//      1-2-4
      folderList.add("U:\\tiva-graphs\\fd_exgr_va_2018_updated\\input\\");

        printCountries(getCountriesMap(folderList),fPath);

    }

    public static HashMap<String, String> getCountriesMap(ArrayList<String> folderList) throws FileNotFoundException {

        HashMap<String, String> finalMap = new HashMap<String, String>();

        for(String graphFolderPath: folderList) {

            File folderPath = new File(graphFolderPath);

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

    return finalMap;

    }
    public static void printCountries(HashMap<String, String> finalMap, String folderPath){

        try (PrintWriter writer = new PrintWriter(folderPath)) {

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

                        if(!countryHashMap.containsKey(linesArray[1])){

                        countryHashMap.put(linesArray[1], "http://data.coypu.org/country/" + linesArray[1]);

                        }

                        if(!countryHashMap.containsKey(linesArray[2])){

                        countryHashMap.put(linesArray[2], "http://data.coypu.org/country/"+linesArray[2]);

                        }

                        if(!countryHashMap.containsKey(linesArray[4])){

                        countryHashMap.put(linesArray[4], "http://data.coypu.org/country/"+linesArray[4]);

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

    public static HashMap<String, String> getCountries(String csvFile, String csvFileUpdated, int x, int y, int z) throws FileNotFoundException {

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

                    if(!countryHashMap.containsKey(linesArray[x])){

                        countryHashMap.put(linesArray[x], "http://data.coypu.org/country/" + linesArray[x]);

                    }

                    if(!countryHashMap.containsKey(linesArray[y])){

                        countryHashMap.put(linesArray[y], "http://data.coypu.org/country/"+linesArray[y]);

                    }

                    if(!countryHashMap.containsKey(linesArray[z])){

                        countryHashMap.put(linesArray[z], "http://data.coypu.org/country/"+linesArray[z]);

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