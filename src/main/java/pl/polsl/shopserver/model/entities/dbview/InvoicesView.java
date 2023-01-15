package pl.polsl.shopserver.model.entities.dbview;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "invoices_view")
@Subselect("SELECT \n" +
        "        `i`.`id_invoice` AS `id_invoice`,\n" +
        "        `i`.`invoice_date` AS `invoice_date`,\n" +
        "        `i`.`invoice_number` AS `invoice_number`,\n" +
        "        `i`.`id_order_in` AS `id_order_in`,\n" +
        "        `oi`.`order_item_quantity` AS `order_item_quantity`,\n" +
        "        `oi`.`order_item_price` AS `order_item_price`,\n" +
        "        `p`.`product_name` AS `product_name`,\n" +
        "        `p`.`product_vat` AS `product_vat`\n" +
        "    FROM\n" +
        "        (((`invoices` `i`\n" +
        "        JOIN `order` `o` ON (`i`.`id_order_in` = `o`.`id_order`))\n" +
        "        JOIN `order_item` `oi` ON (`o`.`id_order` = `oi`.`id_order`))\n" +
        "        JOIN `product` `p` ON (`oi`.`product_id` = `p`.`id_product`))")
@Getter
public class InvoicesView {
    @Id
    @Column(name = "id_invoice")
    private Integer id;
    @Column(name = "invoice_date", length = 45)
    private String invoiceDate;
    @Column(name = "invoice_number", length = 45)
    private String invoiceNumber;
    @Column(name = "id_order_in")
    private Integer idOrderIn;
    @Column(name = "order_item_quantity")
    private Integer orderItemQuantity;
    @Column(name = "order_item_price")
    private Double orderItemPrice;
    @Column(name = "product_name", length = 45)
    private String productName;
    @Column(name = "product_vat", length = 45)
    private String productVat;
}
