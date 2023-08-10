package org.example;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 *
 * Author Nenad Krdzavac
 * Email nenad.krdzavac@tib.eu
 * <p>
 * Updating fdva data. Replaces "|" with ",". Prepare csv file for mapping.
 *
 */

public class ParsingTiVACsv {

    public static void main(String[] args) throws IOException {

    HashMap<String,String> euCountriesMap = new HashMap<String, String>();

    String csvFile = "C:\\Users\\KrdzavacN\\git\\mapping-trade-in-value-added\\src\\main\\resources\\imgr_bsci_2018_updated.csv";
    String csvFileUpdated = "C:\\Users\\KrdzavacN\\git\\mapping-trade-in-value-added\\src\\main\\resources\\imgr_bsci_2018_updated_1000.csv";

//  euCountriesMap.putAll(new ParsingTiVACsv().eucountries());

        updateCsv(csvFile,csvFileUpdated);

        }

        public HashMap<String, String> eucountries(){

        HashMap<String, String> euMap = new HashMap<String, String>();

        euMap.put("1","AUT");
        euMap.put("2","BEL");
        euMap.put("3","CZE");
        euMap.put("4","DNK");
        euMap.put("5","EST");
        euMap.put("6","FIN");
        euMap.put("7","FRA");
        euMap.put("8","DEU");
        euMap.put("9","GRC");
        euMap.put("10","HUN");
        euMap.put("11","IRL");
        euMap.put("12","ITA");
        euMap.put("13","LVA");
        euMap.put("14","LTU");
        euMap.put("15","LUX");
        euMap.put("16","NLD");
        euMap.put("17","NOR");
        euMap.put("18","POL");
        euMap.put("19","PRT");
        euMap.put("20","SVK");
        euMap.put("21","SVN");
        euMap.put("22","ESP");
        euMap.put("23","SWE");
        euMap.put("24","BGR");
        euMap.put("25","HRV");
        euMap.put("26","MLT");
        euMap.put("27","CYP");

        return  euMap;

        }

    public static void updateCsv(String csvFile,String csvFileUpdated) throws FileNotFoundException {

        BufferedReader bufferReader = null;
        String line = "";
        String cvsSplitBy = ",";

        try (PrintWriter writer = new PrintWriter(csvFileUpdated)) {

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("country_c");
            stringBuilder.append(",");
            stringBuilder.append("industry_code_i");
            stringBuilder.append(",");
            stringBuilder.append("country_p");
            stringBuilder.append(",");
            stringBuilder.append("industry_code_h");
            stringBuilder.append(",");
            stringBuilder.append("year");
            stringBuilder.append(",");
            stringBuilder.append("sum");
            stringBuilder.append(",");
            stringBuilder.append('\n');

//            writer.write(stringBuilder.toString());

            try {

                bufferReader = new BufferedReader(new FileReader(csvFile));

                int lineNumber = 1;

                while ((line = bufferReader.readLine()) != null) {

//                    System.out.println(lineNumber + ". " + line);

                    if(lineNumber>1) {

                        // use comma as separator
                        String[] linesArray = line.split(cvsSplitBy);

                        double value = Double.valueOf(linesArray[5]);

                        String value_string = new BigDecimal(value*1000).toPlainString();

                        String s = "";

                        s = s + linesArray[0] + "," + linesArray[1] + "," + linesArray[2] + "," + linesArray[3] + "," + linesArray[4] + "," + value_string + ",";

                        System.out.println(lineNumber+ "," + s);

//                    for (String s : linesArray) {

//                        s = s.replace("|", ",");
//                        s = s + ",";

//                        String splitLineBy = ",";

//                      String[] lineSeparator = s.split(splitLineBy);
//                     if(euCountriesMap.containsValue(finalDemandCountryCode) && (!euCountriesMap.containsValue(valueAddedOriginCountryCode)) && (industrySectorValueAddedOrigin=="") ) {

                        writer.write(s);
                        writer.write("\n");

                    }else {

                        writer.write(line);
                        writer.write("\n");

                    }
//                            System.out.println(lineNumber + ".  " + line);
                            lineNumber++;


//                        }
                    }

//                    if(lineNumber==100) break;

//                }
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