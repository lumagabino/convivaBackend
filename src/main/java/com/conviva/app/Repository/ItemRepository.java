package com.conviva.app.Repository;

import com.conviva.app.Model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {

    @Query(value = "SELECT * FROM item_model WHERE event_id = :event_id",
            nativeQuery = true)
    public List<ItemModel> findItemsWithEventId(@Param("event_id") long event_id);
}
