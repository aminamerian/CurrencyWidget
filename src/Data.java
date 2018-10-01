
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Amin
 */
public class Data {

    public Currency getCryptoCurrency(String name) throws IOException {
        Currency currency;
        if (name.equals("dollar") || name.equals("euro")) {
            currency = getCurrency(name);
        } else {
            String url = "https://coinmarketcap.com/";
            org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
            org.jsoup.nodes.Element content = doc.getElementById("id-" + name);
            Elements links = content.getElementsByTag("td");
            currency = new Currency(
                    name,
                    links.get(3).getElementsByTag("a").text(),
                    links.get(6).text(),
                    links.get(7).getElementsByTag("img").attr("abs:src"));
        }
        return currency;
    }

    public Currency getCurrency(String name) {
        Currency currency = null;
        try {
            String url = "http://parsijoo.ir/api?serviceType=price-API&query=Currency";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            int responseCode = con.getResponseCode();
            System.out.println("Get Request Response Code: " + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(response.toString())));

            NodeList nList = doc.getElementsByTagName("item");
            Element eElement = null;
            if (name.equals("dollar")) {
                eElement = (Element) nList.item(0);
            } else if (name.equals("euro")) {
                eElement = (Element) nList.item(1);
            }
            String percent = new String(eElement.getElementsByTagName("percent").item(0).getTextContent().getBytes(), "UTF-8");
            String change = new String(eElement.getElementsByTagName("change").item(0).getTextContent().getBytes(), "UTF-8");
            String price = new String(eElement.getElementsByTagName("price").item(0).getTextContent().getBytes(), "UTF-8");

            currency = new Currency(name, price, change + percent.replace('.', '/') + "%");

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            System.out.println(e);
        }
        return currency;
    }
}
