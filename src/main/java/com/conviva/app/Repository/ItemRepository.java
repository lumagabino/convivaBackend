package com.conviva.app.Repository;

import com.conviva.app.Model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {
    @Query(value = "SELECT * FROM item_model WHERE eventId = :eventId;",
            nativeQuery = true)
    public List<ItemModel> findItemsWithEventId(@Param("eventId") long eventId);
}
