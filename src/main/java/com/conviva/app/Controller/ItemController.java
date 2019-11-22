package com.conviva.app.Controller;

import com.conviva.app.Model.ItemModel;
import com.conviva.app.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemService itemService;

    // GET all items
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public List<ItemModel> getAllItems() {
        return itemService.listAllItems();
    }

    // GET event by id
    @RequestMapping(method = RequestMethod.GET, path = {"/{id}"})
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public ItemModel getItemById(@PathVariable("id") long id) {
        return itemService.findSectorById(id);
    }

    // GET all items from event
    @RequestMapping( path = "/from_event",
            method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public List<ItemModel> getItemsFromEvent(@RequestParam("eventId") String eventId) {
        return itemService.findItemsFromEvent(Long.parseLong(eventId));
    }

    // POST (create item)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK, reason = "Item created")
    public void createItem(@Valid @RequestBody ItemModel item) {
        itemService.createItem(item);
    }

    // PUT (edit item)
    @RequestMapping(method = RequestMethod.PUT, path = {"/{id}"})
    @ResponseStatus(code = HttpStatus.OK, reason = "Item edited")
    public void editItem (@PathVariable("id") long id, @Valid @RequestBody ItemModel item) {
        itemService.editItem(id, item);
    }

    // DELETE item by id
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Item deleted")
    public void deleteItem(@PathVariable("id") long id) {
        itemService.deleteItemById(id);
    }
}

