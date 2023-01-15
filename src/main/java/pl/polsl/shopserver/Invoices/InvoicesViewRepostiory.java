package pl.polsl.shopserver.Invoices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.shopserver.model.entities.dbview.Cartlist;
import pl.polsl.shopserver.model.entities.dbview.InvoicesView;

import java.util.List;

@Transactional(readOnly = true)
public interface InvoicesViewRepostiory extends JpaRepository<InvoicesView,Integer> {
    @Query(value = "SELECT i FROM InvoicesView i where i.id=:id")
    List<InvoicesView> findInvoicesById(@Param("id")Integer id);
}
