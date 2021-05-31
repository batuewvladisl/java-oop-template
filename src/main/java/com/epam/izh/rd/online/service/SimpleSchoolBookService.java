package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService<SchoolBook> {

    private BookRepository<SchoolBook> schoolBookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookRepository, AuthorService authorService) {
        this.schoolBookRepository = schoolBookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(SchoolBook book) {
        if (authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName()) != null) {
            schoolBookRepository.save(book);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public SchoolBook[] findByName(String name) {

        return schoolBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {

        return schoolBookRepository.findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {

        return schoolBookRepository.removeByName(name);
    }

    @Override
    public int count() {
        return schoolBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {

        if (authorService.findByFullName(schoolBookRepository.findByName(name)[0].getAuthorName(), schoolBookRepository.findByName(name)[0].getAuthorLastName()) != null) {
            return authorService.findByFullName(schoolBookRepository.findByName(name)[0].getAuthorName(), schoolBookRepository.findByName(name)[0].getAuthorLastName());
        } else
            return null;
    }
}