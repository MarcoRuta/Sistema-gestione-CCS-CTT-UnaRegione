package it.unisannio.ingegneriaDelSoftware.Functional;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;

/**Classe utilizzata per la creazione dei vari pdf */
public class PDFGenerator {
	
	/**Genera il pdf con username e password provvisoria del Dipendente generata automaticamente la prima volta che viene inserito dall'amministratore come nuovo Dipendente 
	 * @param output OutputStream dove verrà stampato il documento
	 * @param cdf codice fiscale del Dipendente 
	 * @param username username del Dipendente 
	 * @param password password provvisoria del Dipendente 
	 * @throws DocumentException, IOException
	 */
    public static void  makeDocumentDipendente(OutputStream output, String cdf, String username , String password) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();
        Image img = Image.getInstance("localsettings/img/logoCCSpdf.png");
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
        table.addCell("Codice Fiscale");
        table.addCell(cdf);
        table.addCell("Username");
        table.addCell(username);
        table.addCell("password");
        table.addCell(password);
        document.add(table);
        document.close();
    }
}