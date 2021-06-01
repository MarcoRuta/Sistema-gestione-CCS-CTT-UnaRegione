package it.unisannio.ingegneriaDelSoftware.PDF;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Stream;


public class PDFGenerator {
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


    public static void  makeDocumentSacca(OutputStream output,
                                          int numeroSacche,
                                          String enteRichiedente,
                                          String indirizzoEnte,
                                          String dataAffidamento,
                                          String gruppoSanguigno) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();
        Image img = Image.getInstance("localsettings/img/logoCTTpdf.png");
        img.setAlignment(1);
        img.setWidthPercentage(20);
        document.add(img);
        Paragraph title = new Paragraph("SACCHE EVASE");
        title.setAlignment(1);
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD);
        title.setFont(font);
        title.setSpacingAfter(10);
        document.add(title);
        document.add(new Paragraph("Ente richiedente: "+enteRichiedente));
        document.add(new Paragraph("Indirizzo ente richiedente: "+indirizzoEnte));
        PdfPTable table = new PdfPTable(3);
        table.setSpacingBefore(10);
        //sistema header
        Stream.of("Numero Di Sacche", "Gruppo Sanguigno",  "Data Di Affidamento")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
        table.addCell(Integer.toString(numeroSacche));
        table.addCell(gruppoSanguigno);
        table.addCell(dataAffidamento.toString());
        //aggiungo al pdf
        document.add(table);
        document.close();
    }
}
