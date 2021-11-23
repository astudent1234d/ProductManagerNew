package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Smartphone smartphone1 = new Smartphone(2001, "1", 149, "Samsung");
    Smartphone smartphone2 = new Smartphone(2002, "2", 349, "Samsung");
    Smartphone smartphone3 = new Smartphone(2003, "3", 499, "Honor");
    Smartphone smartphone4 = new Smartphone(2004, "4", 449, "Xiaomi");
    Smartphone smartphone5 = new Smartphone(2005, "5", 249, "Xiaomi");
    Book book1 = new Book(1001, "Book1", 10, "Author1");
    Book book2 = new Book(1002, "Book2", 12, "Author2");
    Book book3 = new Book(1003, "Book3", 20, "Author3");
    Book book4 = new Book(1004, "Book4", 11, "Author2");
    Book book5 = new Book(1005, "Xiaomi", 19, "Author4");

    @BeforeEach
    public void setUp() {
        repository.save(smartphone1);
        repository.save(smartphone2);
        repository.save(smartphone3);
        repository.save(smartphone4);
        repository.save(smartphone5);
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(book4);
        repository.save(book5);
    }

    @Test
    void searchBySmartphone() {
        Product[] actual = manager.searchBy("Samsung");
        Product[] expected = new Product[]{smartphone1, smartphone2};
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBySeveralMeanings() {
        Product[] actual = manager.searchBy("Xiaomi");
        Product[] expected = new Product[]{smartphone4, smartphone5, book5};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByAuthor() {
        Product[] expected = {book1};
        Product[] actual = manager.searchBy("Author1");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void notFindAuthor() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("Author5");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByNameSmartphone() {
        Product[] expected = {smartphone1, smartphone2};
        Product[] actual = manager.searchBy("Samsung");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void notFindSmartphone() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("Apple");
        assertArrayEquals(expected, actual);
    }
}