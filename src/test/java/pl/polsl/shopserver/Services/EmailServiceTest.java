package pl.polsl.shopserver.Services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
class EmailServiceTest {
    @Autowired
    EmailService emailService;
    @Test
    void sendEmail() {
        emailService.sendEmail("szawra.karol@gmail.com","Werfication","Text");
        assertTrue(true);
    }
}