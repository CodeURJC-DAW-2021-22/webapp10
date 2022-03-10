package com.youdemy.service;
 
import java.awt.Color;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.youdemy.model.OrderP;
 
 
public class OrderPDFExporter {
    private ArrayList<OrderP> orders;
     
    public OrderPDFExporter(ArrayList<OrderP> orders) {
        this.orders = orders;
    }
    
    private OrderP order;
    
    public OrderPDFExporter(OrderP order) {
        this.order = order;
    }
    
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        
        cell.setPhrase(new Phrase("Order ID", font));         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("User ID", font));         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Course", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Billing address", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Payment method", font));
        table.addCell(cell);
       
    }
     
    private void writeTableData(PdfPTable table) {
    	DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        for (OrderP order : orders) {
            table.addCell(String.valueOf(order.getId()));
            table.addCell(String.valueOf(order.getUser()));
            table.addCell(String.valueOf(order.Date()));
            table.addCell(String.valueOf(order.getCourse()));
            table.addCell(String.valueOf(order.getbillingAddress()));
            table.addCell(String.valueOf(order.getPaymentMethod()));   
        }
    }
 
    public void export(HttpServletResponse response) throws DocumentException, IOException {
    	Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Orders", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.0f, 2.0f, 2.0f, 2.0f, 2.5f, 2.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
    
    public void orderExport(HttpServletResponse response) throws DocumentException, IOException {
    	Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Order Info", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.0f, 2.0f, 2.0f, 2.0f, 2.5f, 2.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        
        table.addCell(String.valueOf(order.getId()));
        table.addCell(String.valueOf(order.getUser()));
        table.addCell(String.valueOf(order.Date()));
        table.addCell(String.valueOf(order.getCourse()));
        table.addCell(String.valueOf(order.getbillingAddress()));
        table.addCell(String.valueOf(order.getPaymentMethod()));

        document.add(table);
        
        Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(11);
        font.setColor(Color.BLACK);
        
        Paragraph p1 = new Paragraph("Total: "+order.getPrice()+" €", font1);
        p1.setAlignment(Paragraph.ALIGN_RIGHT);
        
        document.add(p1);
         
        document.close();
         
    }
}