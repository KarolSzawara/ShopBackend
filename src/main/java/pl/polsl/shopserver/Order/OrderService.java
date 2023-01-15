package pl.polsl.shopserver.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.shopserver.Auth.JwtToken;
import pl.polsl.shopserver.Cart.CartRepository;
import pl.polsl.shopserver.Exception.QuantityLimit;
import pl.polsl.shopserver.Invoices.InvoiceRepository;
import pl.polsl.shopserver.Invoices.InvoicesViewRepostiory;
import pl.polsl.shopserver.OrderItem.OrderItemRepository;
import pl.polsl.shopserver.Services.EmailService;
import pl.polsl.shopserver.Services.InvoicesService;
import pl.polsl.shopserver.User.UserService;
import pl.polsl.shopserver.Warehouse.WarehouseRepository;
import pl.polsl.shopserver.model.entities.dbentity.*;
import pl.polsl.shopserver.model.entities.dbview.InvoicesView;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    OrderRepostiory orderRepostiory;
    OrderItemRepository orderItemRepository;
    CartRepository cartRepository;
    WarehouseRepository warehouseRepository;
    UserService userService;
    InvoicesViewRepostiory inRepostiory;
    EmailService emailService;
    InvoiceRepository invoiceRepository;
    @Autowired
    OrderService(OrderRepostiory orderRepostiory, OrderItemRepository orderItemRepository,CartRepository cartRepository,WarehouseRepository warehouseRepository,UserService userService,InvoicesViewRepostiory invoicesViewRepostiory,EmailService emailService,InvoiceRepository invoiceRepository){
        this.cartRepository=cartRepository;
        this.orderItemRepository=orderItemRepository;
        this.orderRepostiory=orderRepostiory;
        this.warehouseRepository=warehouseRepository;
        this.userService=userService;
        this.inRepostiory=invoicesViewRepostiory;
        this.emailService=emailService;
        this.invoiceRepository=invoiceRepository;
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public void purchaseCart(String token){
        User user=userService.getUserByToken(token);

        List<Cart> cartList=cartRepository.findCartByIdUser(user.getId());

        Order order=orderRepostiory.save(new Order(user, Instant.now()));
        for(Cart ite:cartList){
            purchaseProduct(ite,order);
        }
        LocalDate localDate = LocalDate.now();
        Invoice nInvoice=new Invoice();
        Invoice lastInvoice=invoiceRepository.findFirstByOrderByIdDesc();
        nInvoice.setInvoiceNumber(nextInvoiceNumber(lastInvoice.getInvoiceNumber()));
        nInvoice.setIdOrderIn(order);
        nInvoice.setInvoiceDate(localDate.toString());
        Invoice invoiceNum = invoiceRepository.save(nInvoice);
        List<InvoicesView> invView=inRepostiory.findAll();
        invView=getViewById(invView,order.getId());

        InvoicesService invoicesService=new InvoicesService();
        byte[] pdf=invoicesService.generateInvoice(user,invView.toArray(new InvoicesView[invView.size()]));
        emailService.setInvoicesToUser(user.getEmail(),"Faktura nr:"+invView.get(0).getInvoiceNumber(),"Faktura Vat","Faktura"+invView.get(0).getInvoiceNumber()+".pdf",pdf);
    }
    void purchaseProduct(Cart cartItem, Order order){

                Optional<Warehouse> oWarehouse = warehouseRepository.getWarehouseByProductId(cartItem.getIdProd().getId());
        Warehouse warehouse=oWarehouse.get();
        if(warehouse.getQuantityProduct()<cartItem.getOrderItemQuantity()){
            throw  new QuantityLimit("Brak takiej ilość produktu");
        }
        warehouse.setQuantityProduct(warehouse.getQuantityProduct()-cartItem.getOrderItemQuantity());
        warehouseRepository.save(warehouse);
        OrderItem orderItem=new OrderItem(order,cartItem.getOrderItemQuantity(),cartItem.getIdProd().getProductPrize(),cartItem.getIdProd());
        orderItemRepository.save(orderItem);
        cartRepository.delete(cartItem);

    }
    public String nextInvoiceNumber(String invoiceN){
        Integer value =Integer.parseInt(invoiceN);
        return String.format("%08d", value++);
    }
    public List<InvoicesView> getViewById(List<InvoicesView> invoicesViews,Integer id){
        List<InvoicesView> retInvoice=new ArrayList<>();
        for(InvoicesView temp:invoicesViews){
            if(temp.getIdOrderIn()==id){
                retInvoice.add(temp);
            }
        }
        return retInvoice;
    }




}
