package pl.polsl.shopserver.Services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import pl.polsl.shopserver.model.entities.dbentity.User;
import pl.polsl.shopserver.model.entities.dbview.InvoicesView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class InvoicesService {
    PDDocument document;
    PDPageContentStream contentStream;
    PDPage page;
    Double nettValue;
    Double vatValue;
    Double bruttoValue;
     private static final int[] cellWidth =

    {
        40,150,60,50,70,40,70,70
    };
     private static final String[] headers={
       "L.P","Nazwa","Cena netto","Ilość","Wartość netto","VAT","Wartość VAT","Wartość brutto"
     };
    private static final int startX = 30;
    private static final int startY=480;
    public InvoicesService(){
        document=new PDDocument();
        page=new PDPage();
        document.addPage(page);
        nettValue= (double) 0;
        vatValue= (double) 0;
        bruttoValue= (double) 0;
    }
    public byte[] generateInvoice(User user, InvoicesView[] invoices){
        try
        {
            Thread.currentThread().setContextClassLoader(PDDocument.class.getClassLoader());
            this.contentStream=new PDPageContentStream(document,page);

            PDFont fontNormal=PDType0Font.load(document, new File("src/main/resources/micross.ttf"));

            //PDFont fontBold=
            LocalDate localDate = LocalDate.now();

            addText(new String[]{"Faktura nr:" + invoices[0].getId()},200,700,fontNormal,19.5f,1);
            addText(new String[]{"Data wystawienia: ","Data sprzedaży: "},200,670,fontNormal,11.5f,15);
            addText(new String[]{invoices[0].getInvoiceDate(),localDate.toString()},295,670,fontNormal,11.5f,15);
            contentStream.moveTo(0, 645);
            contentStream.lineTo(page.getMediaBox().getWidth(), 645);
            contentStream.stroke();
            contentStream.moveTo(0,0);
            addText(new String[]{"Sprzedawca"},50,620,fontNormal,19.5f,1);
            addText(new String[]{"Sklep budowlany","Francuska 12","03-400 Katowice","Nip: 526119820"},50,600,fontNormal,11.5f,15);
            addText(new String[]{"Odbiorca"},400,620,fontNormal,19.5f,1);
            if(user.getCompanyOrPerson().equals("F")){
                addText(new String[]{user.getFirstName()+" "+user.getLastName(),user.getStreet()+user.getStreetNumber()+"/"+user.getFlatNumber(),user.getPostCode()+" "+user.getPostTown()}
                        ,400,600,fontNormal,11.5f,15);
            }
            else{
                addText(new String[]{user.getFirstName()+" "+user.getLastName(),user.getStreet()+user.getStreetNumber()+"/"+user.getFlatNumber(),user.getPostCode()+" "+user.getPostTown(),"Nip:"+user.getNip()}
                        ,400,600,fontNormal,11.5f,15);
            }

            int newX=startX;
            int newY=startY;
            for(int i=0;i<headers.length;i++){
                addCell(newX,newY,headers[i], cellWidth[i], 17,fontNormal,10f);
                newX+=cellWidth[i];
            }
            newX=startX;
            newY=startY-17;
            for(int j=0;j<invoices.length;j++){
                String[] cellValue=getTextInvoices(invoices[j],j+1+"");
                for(int i=0;i<headers.length;i++){
                    addCell(newX,newY,cellValue[i], cellWidth[i], 17,fontNormal,10f);
                    newX+=cellWidth[i];

                }
                newX=startX;
                newY-=17;
            }
            DecimalFormat df = new DecimalFormat("#.00");
            newX=startX;
            addCell(newX,newY,"Razem", 300, 17,fontNormal,10f);
            newX+=300;
            addCell(newX,newY,df.format(nettValue), cellWidth[4], 17,fontNormal,10f);
            newX+=cellWidth[4];
            addCell(newX,newY,"x", cellWidth[5], 17,fontNormal,10f);
            newX+=cellWidth[5];
            addCell(newX,newY,df.format(vatValue), cellWidth[6], 17,fontNormal,10f);
            newX+=cellWidth[6];
            addCell(newX,newY,df.format(bruttoValue), cellWidth[7], 17,fontNormal,10f);
            contentStream.close();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            document.close();
            byte[] bytes = outputStream.toByteArray();

            return bytes;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    void addText(String[] text, int xPos, int yPos, PDFont font,float fontSize,float lead)throws IOException {
        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.setLeading(lead);
        contentStream.newLineAtOffset(xPos, yPos);
        for (String value: text)
        {
            contentStream.showText(value);
            contentStream.newLine();
        }
        contentStream.endText();
        contentStream.moveTo(0,0);


    }
    void addCell(int xPos,int yPos,String text,int colWidth,int colHeight,PDFont font,float fSize) throws IOException {

        contentStream.addRect(xPos,yPos,colWidth,colHeight);
        contentStream.stroke();
        contentStream.beginText();
        contentStream.setFont(font, fSize);
        contentStream.newLineAtOffset(xPos+((colWidth-getTextWidth(text,font,fSize))/2),yPos+5);
        contentStream.showText(text);
        contentStream.endText();
        contentStream.moveTo(0,0);

    }
    float getTextWidth(String text,PDFont font,float size) throws IOException {
        return font.getStringWidth(text)/1000*size;
    }
    String[] getTextInvoices(InvoicesView view,String id){
        DecimalFormat df = new DecimalFormat("#.00");
        nettValue+=getNettoPrize(view.getOrderItemPrice()*view.getOrderItemQuantity(),view.getProductVat());
        vatValue+= getVatPrize(view.getOrderItemPrice()*view.getOrderItemQuantity(),view.getProductVat());
        bruttoValue+=view.getOrderItemPrice()*view.getOrderItemQuantity();
        return new String[]{
                id,
                view.getProductName(),
                df.format(getNettoPrize(view.getOrderItemPrice(),view.getProductVat())),
                view.getOrderItemQuantity().toString(),
                df.format(getNettoPrize(view.getOrderItemPrice()*view.getOrderItemQuantity(),view.getProductVat())),
                view.getProductVat()+"%",
                df.format(getVatPrize(view.getOrderItemPrice()*view.getOrderItemQuantity(),view.getProductVat())),
                df.format(view.getOrderItemPrice()*view.getOrderItemQuantity()),
                df.format(view.getOrderItemPrice())
        };
    }
    Double getNettoPrize(Double prize,String vat){
        return prize*(100-Integer.parseInt(vat))/100;
    }
    Double getVatPrize(Double prize,String vat){
        return prize*Integer.parseInt(vat)/100;
    }



}
