/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstoneproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author omaabdillah
 */
public class CapstoneProject {

    static ArrayList<Village> records = new ArrayList<Village>();
    static Hashtable<String, Integer> summary_district = new Hashtable<String, Integer>();
    static Hashtable<String, String> route_village = new Hashtable<String, String>();
    static String key = "AIzaSyAYm1ZpB5DiQXm0iZk37eNUFHJDPQEb4jg";
    static String google_id = "012552029506297493830:-t7im8zdluq";
    static int count = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        // TODO code application logic here
//        String district_data = "/home/omaabdillah/Documents/DATA SCIENCE INDONESIA/city_district_village_dkijakarta.csv";
//        String bus_route = "/home/omaabdillah/Documents/DATA SCIENCE INDONESIA/trayek_bus_besar_jakarta.csv";
//        String village_sedang = "/home/omaabdillah/Documents/DATA SCIENCE INDONESIA/route_village_sedang.csv";
//        String village_besar = "/home/omaabdillah/Documents/DATA SCIENCE INDONESIA/route_village_bus_besar.csv";
//
//        BufferedReader br = null;
//        String line = "";
//        String splitter = ",";
//        br = new BufferedReader(new FileReader(district_data));
//        br.readLine();
//        while ((line = br.readLine()) != null) {
//            // use comma as separator
//            String[] country = line.split(splitter);
//            Village data = new Village(country[0].toLowerCase(), country[1].toLowerCase(), country[2].toLowerCase(), country[3]);
//            records.add(data);
//            summary_district.put(country[2].toLowerCase(), 0);
//        }
//        br = new BufferedReader(new FileReader(village_sedang));
//        while ((line = br.readLine()) != null) {
//            String[] country = line.split(splitter);
//            route_village.put(country[0], country[1]);
//        }
//        br = new BufferedReader(new FileReader(village_besar));
//        while ((line = br.readLine()) != null) {
//            String[] country = line.split(splitter);
//            route_village.put(country[0], country[1]);
//        }
//        System.out.println("District data has been loaded!!");
//        br = new BufferedReader(new FileReader(bus_route));
//        br.readLine();
//        while ((line = br.readLine()) != null) {
//            String[] country = line.split(splitter);
//            String berangkat = country[8].replaceAll("Term. ", "");
//            String kembali = country[9].replaceAll("Term. ", "");
//            System.out.println(country[8]);
//            identify_region(berangkat, kembali);
//        }
//        System.out.println("Router data has been identified!!");
//        for (String key : summary_district.keySet()) {
//            if (summary_district.get(key) > 0) {
//                System.out.println(key + " amount: " + summary_district.get(key));
//            }
//        }
//        PrintWriter writer = new PrintWriter(new File("village_number_bus_besar.csv"));
//        br = new BufferedReader(new FileReader(district_data));
//        writer.append(br.readLine() + ",number_of_bus\n");
//        while ((line = br.readLine()) != null) {
//            String[] country = line.split(splitter);
//            String kelurahan = country[2].toLowerCase();
//            int value = summary_district.get(kelurahan);
//            writer.append(line.replaceAll("\n", "") + "," + value + "\n");
//        }
//        writer.close();
//        writer = new PrintWriter(new File("route_village_besar.csv"));
//        for (String key : route_village.keySet()) {
//            writer.append(key + "," + route_village.get(key) + "\n");
//        }
//        writer.close();
        replaceRoute();
    }

    private static void replaceRoute() throws FileNotFoundException, IOException {
        String district_data = "/home/omaabdillah/Documents/DATA SCIENCE INDONESIA/city_district_village_dkijakarta.csv";
        String village_sedang = "/home/omaabdillah/Documents/DATA SCIENCE INDONESIA/route_village_sedang.csv";
        String village_besar = "/home/omaabdillah/Documents/DATA SCIENCE INDONESIA/route_village_besar.csv";
        String bus_sedang = "/home/omaabdillah/Documents/DATA SCIENCE INDONESIA/processed-trayek-bus-jakarta.csv";
        String bus_besar = "/home/omaabdillah/Documents/DATA SCIENCE INDONESIA/trayek_bus_besar_jakarta.csv";
        BufferedReader br = null;
        String line = "";
        String splitter = ",";
        br = new BufferedReader(new FileReader(district_data));
        br.readLine();
        while ((line = br.readLine()) != null) {
            // use comma as separator
            String[] country = line.split(splitter);
            Village data = new Village(country[0].toLowerCase(), country[1].toLowerCase(), country[2].toLowerCase(), country[3]);
            records.add(data);
        }
        br = new BufferedReader(new FileReader(village_sedang));
        while ((line = br.readLine()) != null) {
            String[] country = line.split(splitter);
            route_village.put(country[0], country[1]);
        }
        br = new BufferedReader(new FileReader(village_besar));
        while ((line = br.readLine()) != null) {
            String[] country = line.split(splitter);
            route_village.put(country[0], country[1]);
        }
        PrintWriter writer = new PrintWriter(new File("processed-trayek-bus-jakarta_replaced.csv"));
        br = new BufferedReader(new FileReader(bus_sedang));
        line = br.readLine();
        writer.append(line + "\n");
        while ((line = br.readLine()) != null) {
            String[] country = line.split(splitter);
            String berangkat = country[8].replaceAll("Term. ", "");
            String kembali = country[9].replaceAll("Term. ", "");
            for (int i = 0; i < 8; i++) {
                writer.append(country[i] + ",");
            }
            berangkat = change(berangkat);
            kembali = change(kembali);
            writer.append(berangkat + "," + kembali + "\n");
        }
        writer.close();
//        writer = new PrintWriter(new File("trayek_bus_besar_jakarta_replaced.csv"));
//        br = new BufferedReader(new FileReader(bus_besar));
//        line = br.readLine();
//        writer.append(line + "\n");
//        while ((line = br.readLine()) != null) {
//            String[] country = line.split(splitter);
//            String berangkat = country[8].replaceAll("Term. ", "");
//            String kembali = country[9].replaceAll("Term. ", "");
//            for (int i = 0; i < 8; i++) {
//                writer.append(country[i] + ",");
//            }
//            berangkat = change(berangkat);
//            kembali = change(kembali);
//            writer.append(berangkat + "," + kembali + "\n");
//        }
//        writer.close();
    }

    private static void identify_region(String berangkat, String kembali) throws IOException, InterruptedException {
        ArrayList<String> bus_district = new ArrayList<String>();
        String[] data = berangkat.split(" -- ");
        for (String route : data) {
            String hasil = findRegion(route.toLowerCase());
//            System.out.println(route + " " + hasil);
            if (hasil != "") {
                if (!bus_district.contains(hasil)) {
                    bus_district.add(hasil);
                }
            }
        }
        data = kembali.split(" -- ");
        for (String route : data) {
            String hasil = findRegion(route.toLowerCase());
//            System.out.println(route + " " + hasil);
            if (hasil != "") {
                if (!bus_district.contains(hasil)) {
                    bus_district.add(hasil);
                }
            }
        }
        for (String district : bus_district) {
            int value = (int) summary_district.get(district);
            summary_district.put(district, value + 1);
        }
    }

    private static String findRegion(String route) throws IOException, InterruptedException {
        String hasil = "";
        for (Village data : records) {
            if (data.village.contains(route) || route.contains(data.village)) {
                hasil = data.village;
                break;
            }
        }
        // search on historical search data
        if (hasil == "") {
            for (String v : route_village.keySet()) {
                if (v.equalsIgnoreCase(route)) {
                    hasil = route_village.get(v);
                    break;
                }
            }
        }
        // search on google query
        if (count == 100) {
            Thread.sleep(100000);
            count = 0;
        }
        if (hasil == "") {
            hasil = doSearch(route);
            count++;
        }
        return hasil;
    }

    private static String doSearch(String query) throws MalformedURLException, IOException {
        String res = "";
        String village = "";
        PrintWriter writer = null;
        String hasil = makeSearchString(query.replaceAll(" ", "+") + "+jakarta", 1, 10);
        String result = read(hasil);
        if (result != null) {
            writer = new PrintWriter(new File("hasil.txt"));
            writer.write(result);
            writer.close();
            BufferedReader reader = new BufferedReader(new FileReader("hasil.txt"));
            Gson gson = new GsonBuilder().create();
            Result ex = gson.fromJson(reader, Result.class);
            List<Item> items = ex.getItems();
            for (int i = 0; i < items.size(); i++) {
                Item data = items.get(i);
                res = data.getLink();
                break;
            }
            res = res.replaceAll("http://kodepos.whoip.org/", "");
            for (Village data : records) {
                if (data.postal_code.equalsIgnoreCase(res)) {
                    village = data.village;
                    break;
                }
            }
            if (village != "") {
                route_village.put(query, village);
            }
        }

        return village;
    }

    private static String makeSearchString(String qSearch, int start, int numOfResults) {
        String toSearch = "https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=" + google_id + "&q=";

        //replace spaces in the search query with +
        String keys[] = qSearch.split("[+]");
        for (String key : keys) {
            toSearch += key + "+"; //append the keywords to the url
        }

        //specify response format as json
        toSearch += "&alt=json";

        //specify starting result number
        toSearch += "&start=" + start;

        //specify the number of results you need from the starting position
        toSearch += "&num=" + numOfResults;

        return toSearch;
    }

    private static String read(String pUrl) {
        //pUrl is the URL we created in previous step
        try {
            URL url = new URL(pUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String change(String berangkat) {
        String[] data = berangkat.split(" -- ");
        String result = "";
        int i = 0;
        for (String route : data) {
            String hasil = changeIntoVillage(route.toLowerCase());
            if (hasil == "") {
                hasil = route.toLowerCase();
            }
            if (i == data.length - 1) {
                result += hasil;
            } else {
                result += hasil + " -- ";
            }

        }
        return result;
    }

    private static String changeIntoVillage(String route) {
        String hasil = "";
        for (Village data : records) {
            if (data.village.contains(route) || route.contains(data.village)) {
                hasil = data.village;
                break;
            }
        }
        if (hasil == "") {
            for (String v : route_village.keySet()) {
                if (v.equalsIgnoreCase(route)) {
                    hasil = route_village.get(v);
                    break;
                }
            }
        }
        return hasil;
    }
}
