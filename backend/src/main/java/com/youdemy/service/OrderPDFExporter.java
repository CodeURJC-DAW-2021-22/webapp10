package com.youdemy.service;
 
import java.awt.Color;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.youdemy.model.OrderP;
 
 
public class OrderPDFExporter {
    private ArrayList<OrderP> orders;
     
    public OrderPDFExporter(ArrayList<OrderP> orders) {
        this.orders = orders;
    }
    
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("User ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Course", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Payment", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);
    }
     
    private void writeTableData(PdfPTable table) {
    	DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        for (OrderP order : orders) {
            table.addCell(String.valueOf(order.getId()));
            table.addCell(String.valueOf(order.getPrice()));
            table.addCell(String.valueOf(order.getCourse()));
            table.addCell("Tarjeta");
            table.addCell(dateFormatter.format(new Date(System.currentTimeMillis())));   
        }
    }
 
    public void export(HttpServletResponse response) throws DocumentException, IOException {
    	Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Users", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.0f, 3.5f, 3.5f, 2.0f, 2.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}