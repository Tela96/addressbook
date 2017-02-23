package addressbook;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLHandler implements FileSystem
{

    public AddressBook readFile(File file)
    {
        AddressBook book = new AddressBook();
        book.setFile(file);
        try
        {

            DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder docB = docBF.newDocumentBuilder();
            Document doc = docB.parse(file);
            String firstName;
            String lastName;
            String address;
            String zip;
            String city;
            String state;
            String phone;
            NodeList people = doc.getElementsByTagName("Person");
            for (int i = 0; i < people.getLength(); i++)
            {
                Node person = people.item(i);
                person.normalize();
                firstName = person.getChildNodes().item(0).getTextContent();
                lastName = person.getChildNodes().item(1).getTextContent();
                address = person.getChildNodes().item(2).getTextContent();
                zip = person.getChildNodes().item(3).getTextContent();
                city = person.getChildNodes().item(4).getTextContent();
                state = person.getChildNodes().item(5).getTextContent();
                phone = person.getChildNodes().item(6).getTextContent();
                book.addPerson(firstName, lastName, address, city, state, zip, phone);

            }
            return book;
        }
        catch(Exception e)
        {
            System.out.println("There was an error during file reading, getting new file instead.");
            return book;
        }
    }

    public void saveFile(AddressBook addressBook, File file)
    {
        try
        {
            DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder docB = docBF.newDocumentBuilder();
            Document doc = docB.newDocument();

            Element rootElement = doc.createElement("Peopel");
            doc.appendChild(rootElement);

            for (Person p :addressBook.getCollection())
            {
                Element person = doc.createElement("Person");
                rootElement.appendChild(person);

                Element firstName = doc.createElement("Firstname");
                firstName.appendChild(doc.createTextNode(p.getFirstName()));
                person.appendChild(firstName);

                Element lastName = doc.createElement("Lastname");
                lastName.appendChild(doc.createTextNode(p.getLastName()));
                person.appendChild(lastName);

                Element address = doc.createElement("Address");
                address.appendChild(doc.createTextNode(p.getAddress()));
                person.appendChild(address);

                Element city = doc.createElement("City");
                city.appendChild(doc.createTextNode(p.getCity()));
                person.appendChild(city);

                Element state = doc.createElement("State");
                state.appendChild(doc.createTextNode(p.getState()));
                person.appendChild(state);

                Element zip= doc.createElement("Zipcode");
                zip.appendChild(doc.createTextNode(p.getZip()));
                person.appendChild(zip);

                Element phone = doc.createElement("Phonenumber");
                phone.appendChild(doc.createTextNode(p.getPhone()));
                person.appendChild(phone);
            }
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource ds = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(ds, result);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
