package jfc_mvc.src.view;

import jfc_mvc.src.model.User;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class UserPdf {

    public void exportPdf(List<User> users) {
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("user.dir") + File.separator + "users.pdf"));
            document.open();

            // Define table with 5 columns
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1, 3, 4, 2, 3}); // Adjust column widths

            // Define fonts
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA);

            // Add table headers
            String[] headers = {"No", "Name", "Email", "NRP", "No Telpon"};
            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell(new Phrase(header, headerFont));
                headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                headerCell.setPadding(5);
                table.addCell(headerCell);
            }

            // Add table rows
            int no = 1;
            for (User user : users) {
                table.addCell(new Phrase(String.valueOf(no++), bodyFont));
                table.addCell(new Phrase(user.getName(), bodyFont));
                table.addCell(new Phrase(user.getEmail(), bodyFont));
                table.addCell(new Phrase(user.getNrp(), bodyFont));
                table.addCell(new Phrase(user.getNoTelp(), bodyFont));
            }

            document.add(table);
        } catch (DocumentException | IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            document.close();
        }
    }
}
