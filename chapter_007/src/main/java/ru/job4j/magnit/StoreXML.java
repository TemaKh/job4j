package ru.job4j.magnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * The class generates XML from the database data.
 */
public class StoreXML {

    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    public StoreXML() {
    }

    /**
     * Method saves data from the list to the target file.
     * @param list .
     */
    public void save(List<Entry> list) {
        Entries entries = new Entries(list);
        try {
            JAXBContext context = JAXBContext.newInstance(Entries.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(entries, target);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
