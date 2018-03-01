package com.example.demo.Repositories;

import com.example.demo.Models.LostItems;
import org.springframework.data.repository.CrudRepository;

public interface LostItemsRepository extends CrudRepository<LostItems,Long> {
}
