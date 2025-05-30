package parser;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import feed.*;

/* Esta clase implementa el parser de feed de tipo rss (xml)
 * https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm 
 * */

public class RssParser extends GeneralParser<List<Article>> {
    private List<Article> listArticle;
    protected String siteName;

    public RssParser(String xml) {
        super(xml); // Llamado "in" en clase madre
        listArticle = new ArrayList<Article>();
    }

    public String parseSiteName(String url) {
        String host = url.split("/")[2];
        String[] parts = host.split("\\."); // rss.nytimes.com
        siteName = parts[parts.length - 2];
        return siteName;
    }

    public Document xmlToDocument() {
        Document xmldoc = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            ByteArrayInputStream input = new ByteArrayInputStream(in.getBytes("UTF-8"));
            xmldoc = docBuilder.parse(input);
        } catch (Exception e) {
            System.err.println("ERROR: Error parsing XML");
            System.exit(1);
        }

        return xmldoc;
    }

    public NodeList documentToNodeList(Document xmldoc) {
        return xmldoc.getElementsByTagName("item");
    }

    public Article nodeListToArticle(NodeList nList, int index) {
        Node nNode = nList.item(index);
        Element item = null;
        String title = null, link = null, description = null, stringDate = null;
        Date pubDate = null;

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            item = (Element) nNode;
            title = item.getElementsByTagName("title").item(0).getTextContent();
            link = item.getElementsByTagName("link").item(0).getTextContent();
            description = item.getElementsByTagName("description").item(0).getTextContent();
            stringDate = item.getElementsByTagName("pubDate").item(0).getTextContent();

            SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
            try {
                pubDate = formatter.parse(stringDate);
            } catch (Exception e) {
                System.err.println("ERROR: Date formatter error");
                System.exit(1);
            }
        }
        return new Article(title, description, pubDate, link);
    }

    @Override
    public List<Article> parse() {
        NodeList nList = documentToNodeList(xmlToDocument());
        for (int i = 0; i < nList.getLength(); i++) {
            listArticle.add(nodeListToArticle(nList, i));
        }
        return listArticle;
    }

}
