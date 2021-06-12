package it.unisannio.ingegneriaDelSoftware.Functional;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import it.unisannio.ingegneriaDelSoftware.DomainTypes.Seriale;

import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Stream;
import java.util.List;

/**Classe utilizzata per la creazione dei vari pdf */
public class PDFGenerator {

    /**Genera il pdf con username e password provvisoria del dipendente generata automaticamente la prima volta che viene inserito dall'amministratore come nuovo Dipendente
     * @param output OutputStream dove verrà stampato il documento
     * @param cdf codice fiscale del Dipendente
     * @param username username del Dipendente
     * @param password password provvisoria del Dipendente
     * @throws DocumentException
     * @throws IOException
     */
    public static void  makeDocumentDipendente(OutputStream output, String cdf, String username , String password) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();
        Image img = Image.getInstance("localsettings/img/logoCTTpdf.png");
        img.setAlignment(1);
        img.setWidthPercentage(20);
        document.add(img);
        Paragraph title = new Paragraph("DIPENDENTE AGGIUNTO AL SISTEMA, ECCO LE SUE CREDENZIALI:");
        title.setAlignment(1);
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD);
        title.setFont(font);
        title.setSpacingAfter(10);
        document.add(title);
        PdfPTable table = new PdfPTable(2);
        //prima riga
        table.addCell("Codice Fiscale");
        table.addCell(cdf);
        //seconda riga
        table.addCell("Username");
        table.addCell(username);
        //terza riga
        table.addCell("password");
        table.addCell(password);
        //aggiungo al pdf
        document.add(table);
        document.close();
    }

    /**Genera il pdf con i dati relativi alla Sacca evasa
     * @param output OutputStream dove verrà stampato il documento
     * @param numeroSacche Numero di sacche che sono state evase
     * @param enteRichiedente Ente che richiede le sacche
     * @param indirizzoEnte Indirizzo dell'ente che richiede le sacche
     * @param dataAffidamento Data in cui le sacche sono arrivate in magazzino
     * @throws DocumentException
     * @throws IOException
     */
    public static void  makeDocumentSacca(OutputStream output,
                                          int numeroSacche,
                                          List<Seriale> seriali,
                                          String enteRichiedente,
                                          String indirizzoEnte,
                                          String dataAffidamento) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();
        Image img = Image.getInstance("localsettings/img/logoCTTpdf.png");
        img.setAlignment(1);
        img.setWidthPercentage(20);
        document.add(img);
        Paragraph title = new Paragraph("SACCHE DA EVADERE");
        title.setAlignment(1);
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD);
        title.setFont(font);
        title.setSpacingAfter(10);
        document.add(title);
        document.add(new Paragraph("Ente richiedente: "+enteRichiedente));
        document.add(new Paragraph("Indirizzo ente richiedente: "+indirizzoEnte));
        document.add(new Paragraph("Data affidamento: "+dataAffidamento));
        document.add(new Paragraph("Numero sacche evase: "+numeroSacche));

        PdfPTable table = new PdfPTable(1);
        table.setSpacingBefore(10);
        //sistema header
        Stream.of("Seriale Sacca")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
        for(Seriale s : seriali) {
            table.addCell(s.getSeriale());
        }
        //aggiungo al pdf
        document.add(table);
        document.close();
    }

    /**Genera il pdf con i seriali delle sacche da smaltire
     * @param output OutputStream dove verrà stampato il documento
     * @param serialiDaSmaltire seriali delle sacche da smaltire
     * @throws DocumentException
     * @throws IOException
     */
    public static void makeDocumentSmaltimento(OutputStream output, List<Seriale> serialiDaSmaltire) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();
        Image img = Image.getInstance("localsettings/img/logoCTTpdf.png");
        img.setAlignment(1);
        img.setWidthPercentage(20);
        document.add(img);
        Paragraph title = new Paragraph("SACCHE DA SMALTIRE");
        title.setAlignment(1);
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD);
        title.setFont(font);
        title.setSpacingAfter(10);
        document.add(title);
        for(Seriale s : serialiDaSmaltire)
            document.add(new Paragraph("Seriale: "+s.getSeriale()+"\n"));
        document.close();
    }
}
