import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class WriteXmlStAXCursor {

    public void createXMLfile(String nameXMLfile, String player1, String player2, ArrayList<String[]> steps, String[] winner) {

        try(FileOutputStream out = new FileOutputStream(nameXMLfile+".xml")){
            writeXml(out, player1, player2, steps, winner);
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }


    }

    private static void writeXml(OutputStream out, String player1, String player2, ArrayList<String[]> steps, String[] winner) throws XMLStreamException {

        XMLOutputFactory output = XMLOutputFactory.newInstance();

        XMLStreamWriter writer = output.createXMLStreamWriter(out, "windows-1251");

        writer.writeStartDocument("windows-1251", "1.0");
        writer.writeCharacters("\n");

        // <Gameplay>
        writer.writeStartElement("Gameplay");
        writer.writeCharacters("\n");
        writer.writeCharacters("\t");

        writer.writeEmptyElement("Player"); //first player
        writer.writeAttribute("id", "1");
        writer.writeAttribute("name", player1);
        writer.writeAttribute("symbol", "X");
        writer.writeCharacters("\n");
        writer.writeCharacters("\t");

        writer.writeEmptyElement("Player"); //second player
        writer.writeAttribute("id", "2");
        writer.writeAttribute("name", player2);
        writer.writeAttribute("symbol", "0");
        writer.writeCharacters("\n");

        // <Game>
        writer.writeStartElement("Game");
        writer.writeCharacters("\n");
        for (int i=0; i<steps.size(); i++) {
            String[] idAndMove= steps.get(i);
            writer.writeCharacters("\t");
            writer.writeStartElement("Step");
            writer.writeAttribute("num", (i+1)+"");
            writer.writeAttribute("playerId", idAndMove[0]);
            writer.writeCharacters(idAndMove[1]);
            writer.writeEndElement();
            writer.writeCharacters("\n");
        }

        writer.writeEndElement();
        writer.writeCharacters("\n");
        // </Game>

        //GameResult
        writer.writeStartElement("GameResult");
        writer.writeEmptyElement("Player"); //first player
        writer.writeAttribute("id", winner[0]);
        writer.writeAttribute("name", winner[1]);
        writer.writeAttribute("symbol", winner[2]);

        writer.writeEndElement();
        writer.writeCharacters("\n");
        //</GameResult>




        writer.writeEndElement();
        // </Gameplay>

        writer.writeEndDocument();


        writer.flush();

        writer.close();

    }

}
