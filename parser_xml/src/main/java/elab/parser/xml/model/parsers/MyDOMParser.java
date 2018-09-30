package elab.parser.xml.model.parsers;

import elab.parser.xml.model.MyParser;
import elab.parser.xml.model.Person;
import elab.parser.xml.util.PersonsHolder;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyDOMParser implements MyParser {

    private List<Person> persons = new ArrayList<>();

    public void createXMLFile(List<Person> list, String fileName) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element rootElement = doc.createElement("company");
        doc.appendChild(rootElement);

        Element employee = doc.createElement("employees");
        rootElement.appendChild(employee);

        Attr attr = doc.createAttribute("department");
        attr.setValue("dev");
        employee.setAttributeNode(attr);

        for (Person p : list) {
            Element person = doc.createElement("person");
            Attr attrType = doc.createAttribute("id");
            attrType.setValue("" + p.getId());
            person.setAttributeNode(attrType);

            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(p.getName()));
            person.appendChild(name);

            Element address = doc.createElement("address");
            address.appendChild(doc.createTextNode(p.getAddress()));
            person.appendChild(address);

            Element cash = doc.createElement("cash");
            cash.appendChild(doc.createTextNode("" + p.getCash()));
            person.appendChild(cash);

            Element edu = doc.createElement("education");
            edu.appendChild(doc.createTextNode(p.getEducation()));
            person.appendChild(edu);

            employee.appendChild(person);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);
    }

    public void parseXMLFile(String fileName) throws IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = builder.parse(new File(fileName));

        NodeList list = doc.getElementsByTagName("person");

        for(int i = 0; i < list.getLength(); i++) {
            if(list.item(i).getNodeType()== Node.ELEMENT_NODE) {
                Element el = (Element) list.item(i);
                Person person = new Person();
                person.setId(Integer.valueOf(el.getAttribute("id")));

                NodeList childs = el.getChildNodes();

                for(int j = 0; j < childs.getLength(); j++) {
                    if(childs.item(j).getNodeType()==Node.ELEMENT_NODE) {
                        Element child = (Element) childs.item(j);

                        switch (child.getNodeName()) {
                            case "name":
                                person.setName(child.getTextContent());
                                break;
                            case "address":
                                person.setAddress(child.getTextContent());
                                break;
                            case "cash":
                                person.setCash(Double.valueOf(child.getTextContent()));
                                break;
                            case "education":
                                person.setEducation(child.getTextContent());
                                break;
                            default:
                                break;
                        }
                    }
                }
                persons.add(person);
            }
        }
        PersonsHolder.setPersons(persons);
    }
}
