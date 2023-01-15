package pl.polsl.shopserver.Services;

import org.junit.jupiter.api.Test;
import pl.polsl.shopserver.model.entities.dbentity.Invoice;
import pl.polsl.shopserver.model.entities.dbentity.Order;
import pl.polsl.shopserver.model.entities.dbentity.User;
import pl.polsl.shopserver.model.entities.dbview.InvoicesView;

import static org.junit.jupiter.api.Assertions.*;

class InvoicesServiceTest {

    @Test
    void generateInvoice() {
        User user=new User(1,"mail","pass","name","last","ph","1","com","txt","straBe","1","36-3","nrl","213","phon",null,null,"T",null);
        InvoicesService invoicesService=new InvoicesService();

        InvoicesView invoicesView=new InvoicesView(1,"00001","2023-01-03",1,1,10.0,"Product","23");
        InvoicesView invoicesView1=new InvoicesView(2,"00001","2023-01-03",1,1,10.0,"Product","23");
        InvoicesView[] view={invoicesView,invoicesView1};
        invoicesService.generateInvoice(user,view);
    }
}