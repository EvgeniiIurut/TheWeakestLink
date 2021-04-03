package com.iurutgui.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

class Parser {
    static ArrayList<String> arrayList = new ArrayList<>();

    public static ArrayList<String> parseSite(String url_in) {
        // Make a URL to the web page
        URL url = null;
        try {
            url = new URL(url_in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //            url = new URL("http://gameshows.ru/wiki/%D0%A1%D0%BB%D0%B0%D0%B1%D0%BE%D0%B5_%D0%B7%D0%B2%D0%B5%D0%BD%D0%BE_(%D0%9E%D0%B1%D0%B7%D0%BE%D1%80_%D0%B2%D1%8B%D0%BF%D1%83%D1%81%D0%BA%D0%B0_2021-02-12)");
        // Get the input stream through URL Connection
        URLConnection con = null;
        try {
            con = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is = null;
        try {
            is = con.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;

        int k = 0;
        int j = 0;
        while (true) {
            try {
                line = br.readLine();
                if (line == null) break;
                if (line.contains("Финал") && arrayList.size()>0) break;
                if (line.contains("title") ||
                        line.contains("<br") ||
                        line.contains("<dt>") ||
                        line.contains("пасует") ||
                        line.contains("руб.") ||
                        line.contains("увеличил банк") ||
                        line.contains("увеличила банк")) {
                    continue;
                }
                if ((line.contains("Правильный ответ") &&
                        (arrayList.get(arrayList.size() - 1).contains("Ответ")))) {
                    arrayList.remove(arrayList.size() - 1);
                    arrayList.add(line);
                    continue;
                }
                if (line.contains("<p>")) {
                    arrayList.add(line);
                    continue;
                }
                if (line.contains("<dd>")) {
                    arrayList.add(line);
                    continue;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.set(i, arrayList.get(i).replaceAll("[^\\d-().^?XIV!:+а-яёА-ЯЁ ]", ""));
            if (i % 2 == 1) {
                arrayList.set(i - 1, arrayList.get(i - 1) + " " + arrayList.get(i));
            }
        }
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            if (i % 2 == 1) {
                arrayList.remove(i);
            }
        }

        for (int i = 0; i < arrayList.size(); i++) {
            String temp = arrayList.get(i);
            String temp1;
            if (temp.contains("Ответ")) {
                arrayList.set(i,temp.replaceAll(temp.substring(temp.indexOf("Ответ"),temp.indexOf(':')), ""));
            }
            else if (temp.contains("Правильный ответ:")) {
                arrayList.set(i,temp.replaceAll("Правильный ответ:", ""));
            }
        }

//        for (int i = 0; i <  arrayList.size() - 1; i++) {
//            String temp = arrayList.get(i);
//            arrayList.set(i, temp.replaceAll("Правильный ответ:",""));
//        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

}
