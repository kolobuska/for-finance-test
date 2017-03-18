package helpers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class ApiHelper {

    private static final String GAME_API_URL = "https://www.boardgamegeek.com/xmlapi/boardgame/";

    /**
     *
     * @param gameId id of the game
     * @return text value of the maximum poll result
     */
    public static String getMaximumDependencyLevelText(int gameId) {

        int pollVoteNumMax = 0;
        String pollTextMax = "";

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new URL(GAME_API_URL + gameId).openStream());
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("/boardgames/boardgame/poll[@name='language_dependence']/results/result");
            NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            for (int temp = 0; temp < nl.getLength(); temp++) {
                Node nNode = nl.item(temp);
                int pollVoteNum = Integer.parseInt(nNode.getAttributes().getNamedItem("numvotes").getTextContent());
                if (pollVoteNum > pollVoteNumMax) {
                    pollVoteNumMax = pollVoteNum;
                    pollTextMax = nNode.getAttributes().getNamedItem("value").getTextContent();
                }
            }

        } catch (ParserConfigurationException|IOException|XPathExpressionException|SAXException|RuntimeException e) {
            e.printStackTrace();
        }

        return pollTextMax;

    }
}