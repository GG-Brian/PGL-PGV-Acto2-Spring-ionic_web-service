package org.briangg.library.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.briangg.library.dao.IComicDao;
import org.briangg.library.models.Comic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComicServiceImpl implements IComicService {
	
	@Autowired
	IComicDao comicDao;
	
	@Override
	public List<Comic> getAll() {
		return (List<Comic>) comicDao.findAll();
	}

	@Override
	public void add(Comic newComic) {
		comicDao.save(newComic);
	}
	
	@Override
	public void update(long idComic, Comic comic) {
		comicDao.findById(idComic).ifPresent((x) -> {
			comicDao.deleteById(idComic);
			comic.setId(idComic);
			comicDao.save(comic);
		});
	}
	
	@Override
	public void delete(long idComic) {
		comicDao.deleteById(idComic);
	}
	
	
	@Override
	public Optional<Comic> getOne(long idComic) {
		return comicDao.findById(idComic);
	}

	
}