package pl.polsl.shopserver.Invoices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.polsl.shopserver.model.entities.dbentity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
    Invoice findFirstByOrderByIdDesc();
}

