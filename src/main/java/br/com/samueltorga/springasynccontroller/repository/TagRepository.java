package br.com.samueltorga.springasynccontroller.repository;

import br.com.samueltorga.springasynccontroller.repository.model.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer>, ListCrudRepository<Tag, Integer> {
}
