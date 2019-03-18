package ru.job4j.magnit;

import java.io.File;
import java.sql.SQLException;

public class StartApp {
    public static void main(String[] args) throws SQLException {
        File source = new File("chapter_007/src/main/resources/source.xml");
        File dest = new File("chapter_007/src/main/resources/dest.xml");
        File scheme = new File("chapter_007/src/main/resources/scheme.xsl");
        StoreSQL storeSQL = new StoreSQL(new Config());
        storeSQL.generated(7);
        StoreXML storeXML = new StoreXML(source);
        storeXML.save(storeSQL.load());
        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(source, dest, scheme);
        ParserXML parserXML = new ParserXML();
        int count = parserXML.parse(dest);
        System.out.println(count);
    }
}
