/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.service;

import cl.ucn.disc.arqsist.library.dao.BookDao;
import cl.ucn.disc.arqsist.library.model.Book;

import java.util.List;

/**
 * Catalog rules. This is the only class that changes the copy count of a book.
 */
public final class BookService {

    /** The book persistence layer. */
    private final BookDao dao;

    /**
     * Creates the service.
     *
     * @param dao the book DAO.
     */
    public BookService(BookDao dao) {
        this.dao = dao;
    }

    /**
     * Lists every book.
     *
     * @return all books.
     */
    public List<Book> listAll() {
        return dao.findAll();
    }

    /**
     * Finds a book by id.
     *
     * @param id the book id.
     * @return the book, or null when it does not exist.
     */
    public Book findById(int id) {
        return dao.findById(id);
    }

    /**
     * Creates a book with every copy available.
     *
     * @param book the book to create.
     * @return the created book.
     */
    public Book create(Book book) {
        book.setAvailableCopies(book.getTotalCopies());
        dao.create(book);
        return book;
    }

    /**
     * Takes one copy out of the inventory.
     *
     * @param bookId the book id.
     * @throws NotFoundException if the book does not exist.
     * @throws IllegalStateException if the book has no available copies.
     */
    public void borrow(int bookId) {
        Book book = dao.findById(bookId);
        if (book == null) {
            throw new NotFoundException("Book not found: " + bookId);
        }
        if (book.getAvailableCopies() <= 0) {
            throw new IllegalStateException("No available copies of book " + bookId);
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        dao.update(book);
    }

    /**
     * Puts one copy back into the inventory.
     *
     * @param bookId the book id.
     * @throws NotFoundException if the book does not exist.
     */
    public void returnCopy(int bookId) {
        Book book = dao.findById(bookId);
        if (book == null) {
            throw new NotFoundException("Book not found: " + bookId);
        }
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        dao.update(book);
    }
}