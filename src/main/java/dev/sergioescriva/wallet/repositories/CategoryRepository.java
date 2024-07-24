package dev.sergioescriva.wallet.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.sergioescriva.wallet.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
