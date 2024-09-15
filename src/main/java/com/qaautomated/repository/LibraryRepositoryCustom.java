package com.qaautomated.repository;

import java.util.List;

import com.qaautomated.controller.Library;

public interface LibraryRepositoryCustom {
	
	List<Library> findAllByAuthor(String authorName);

	Library findByName(String bookName);

}
