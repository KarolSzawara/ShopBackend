package pl.polsl.shopserver.model.entities.dbentity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invoice", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_invoice", nullable = false)
    private Order order;

    @Column(name = "invoice_date", length = 45)
    private String invoiceDate;

    @Column(name = "invoice_number", length = 45)
    private String invoiceNumber;

    @Column(name = "id_order_in")
    private Integer idOrderIn;



}