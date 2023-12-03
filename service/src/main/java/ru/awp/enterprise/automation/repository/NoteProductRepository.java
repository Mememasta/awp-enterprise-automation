package ru.awp.enterprise.automation.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.awp.enterprise.automation.models.dao.NoteProductDAO;
import ru.awp.enterprise.automation.models.dto.TotalValueByAreaAndProductId;

import java.util.UUID;

@Repository
public interface NoteProductRepository extends ReactiveCrudRepository<NoteProductDAO, Long> {

    Flux<NoteProductDAO> findAllByNoteId(UUID noteId);
    Flux<NoteProductDAO> findAllByNoteIdAndProductId(UUID noteId, Long productId);

    @Query(value = """
            SELECT n.area,
               np.product_id,
               SUM(CASE WHEN n.status = 0 THEN np.value ELSE 0 END) AS status_0,
               SUM(CASE WHEN n.status = 1 THEN np.value ELSE 0 END) AS status_1,
               SUM(CASE WHEN n.status = 2 THEN np.value ELSE 0 END) AS status_2
            FROM awp_enterprise_automation.notes_products np
            JOIN awp_enterprise_automation.notes n ON np.note_id = n.id
            WHERE np.is_defect = false
            GROUP BY n.area, np.product_id;
    """)
    Flux<TotalValueByAreaAndProductId> findAllTotalValue();

}
