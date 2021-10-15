package org.briangg.library.services;

import java.util.List;
import java.util.Optional;

import org.briangg.library.models.Comic;

public interface IComicService {
	List<Comic> getAll();
	Optional<Comic> getOne(long idComic);
	void add(Comic comic);
	void delete(long idComic);
	void update(long idComic, Comic comic);
}
