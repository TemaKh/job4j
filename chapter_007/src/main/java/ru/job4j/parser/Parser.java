package ru.job4j.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class for parsing the site sql.ru.
 */
public class Parser {
    /**
     * Method for parsing the site sql.ru.
     */
    public void parsing() {
        try (DBConnect dbConnect = new DBConnect()) {
            Timestamp lastTimestamp = getLastTimestamp(dbConnect);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            for (int i = 1; timestamp.after(lastTimestamp); i++) {
                Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers" + i).get();
                Elements elements = doc.getElementsByClass("postslisttopic");
                for (Element element : elements) {
                    String name = element.child(0).text();
                    String description = element.child(0).text();
                    String link = element.child(0).attr("href");
                    Document innerHtml = Jsoup.connect(link).get();
                    Element dateHtml = innerHtml.getElementsByClass("msgFooter").first();
                    String date = dateHtml.text().split("\\[")[0];
                    timestamp = new Timestamp(parseDate(date));
                    if (name.matches("(.*)Java\\W(.*)") && !name.matches("(.*)Java Script(.*)")
                            && timestamp.after(lastTimestamp)) {
                        dbConnect.insert(name, description, link, timestamp.getTime());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method returns the date of the last record from the database.
     * @param dbConnect base connection.
     * @return Timestamp.
     */
    private Timestamp getLastTimestamp(DBConnect dbConnect) {
        Timestamp lastTimestamp = dbConnect.getLastDate();
        if (lastTimestamp == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2019, 0, 1);
            lastTimestamp = new Timestamp(calendar.getTimeInMillis());
        }
        return lastTimestamp;
    }

    /**
     * Method translates the date from the site to the Timestamp.
     * @param dateSite from the site.
     * @return the number of milliseconds.
     */
    private long parseDate(String dateSite) {
        long result = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yy, h:mm");
        Date date = null;
        try {
            date = format.parse(dateSite);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            result = date.getTime();
        }
        return result;
    }
}
