package pl.polsl.shopserver.Services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.polsl.shopserver.model.entities.dbentity.User;
import pl.polsl.shopserver.model.entities.dbview.InvoicesView;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest()
class EmailServiceTest {
    @Autowired
    EmailService emailService;
    @Test
    void sendEmail() {
        emailService.sendEmail("szawra.karol@gmail.com","Werfication","Text");
        assertTrue(true);
    }
    @Test
    void sendEmailWithAtt(){
        User user=new User(1,"mail","pass","name","last","ph","1","com","txt","straBe","1","36-3","nrl","213","phon",null,null,"T",null);
        InvoicesService invoicesService=new InvoicesService();

        InvoicesView invoicesView=new InvoicesView(1,"00001","2023-01-03",1,1,10.0,"Product","23");
        InvoicesView invoicesView1=new InvoicesView(2,"00001","2023-01-03",1,2,10.0,"Product","23");
        InvoicesView[] view={invoicesView,invoicesView1};
        byte[] by=invoicesService.generateInvoice(user,view);
        emailService.setInvoicesToUser("142karol@gmail.com","Werfication","Text","abc.pdf",by);
    }
}