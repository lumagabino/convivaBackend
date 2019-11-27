package com.conviva.app.Service;

import com.conviva.app.Exceptions.InvalidInputException;
import com.conviva.app.Model.ItemModel;
import com.conviva.app.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    // find all items from one eventId
    public List<ItemModel> findItemsFromEvent(long event_id) {
        return itemRepository.findItemsWithEventId(event_id);
    }

    // Find item through id
    public ItemModel findSectorById(long id) {
        return itemRepository.getOne(id);
    }

    // Find all items
    public List<ItemModel> listAllItems() { return itemRepository.findAll(Sort.by("event_id")); }

    // Create item
    @Transactional
    public void createItem(ItemModel item) {
        if(item.getId() != 0) { // // check if item already exists so the new one doesn't override it
            throw new InvalidInputException("Invalid input id!");
        }
        itemRepository.save(item);
    }

    // Delete event
    @Transactional
    public void deleteItemById(long id) {
        Optional<ItemModel> itemOptional = itemRepository.findById(id);

        if(itemOptional.isEmpty()) { // check of item exists in repository
            throw new InvalidInputException("This id does not exist. It can not be deleted");
        }
        try {
            itemRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException exception) {
            System.out.println("Item could not be deleted because it will affect Data Integrity in DB.");
        }
    }

    // Edit item
    public void editItem(long id, ItemModel item) {
        Optional<ItemModel> itemOptional = itemRepository.findById(id);

        if(itemOptional.isEmpty()) { // check if item exists in repository
            throw new InvalidInputException("This id does not exist. It can not be edited");
        }
        item.setId(id); // set the id of the old item as the id of the edited item
        itemRepository.save(item);
    }
}
