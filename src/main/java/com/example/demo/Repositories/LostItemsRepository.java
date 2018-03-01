package com.example.demo.Repositories;

import com.example.demo.Models.AppUser;
import com.example.demo.Models.LostItems;
import org.springframework.data.repository.CrudRepository;

public interface LostItemsRepository extends CrudRepository<LostItems,Long> {
    Iterable<LostItems> findAllByAppUserIs(AppUser appUser);
    Iterable<LostItems> findAllByItemTypeIs(String type);
    Iterable<LostItems> findAllByFoundStatusIs(Boolean foundstat);
    Iterable<LostItems> findAllByItemDescriptionContainingIgnoreCase(String search);
}
