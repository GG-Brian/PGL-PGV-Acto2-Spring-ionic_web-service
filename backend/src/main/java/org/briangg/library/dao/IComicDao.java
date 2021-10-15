package org.briangg.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.briangg.library.models.Comic;

public interface IComicDao extends CrudRepository<Comic, Long> {

}