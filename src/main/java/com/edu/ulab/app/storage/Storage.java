package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class Storage {
    // создать хранилище в котором будут содержаться данные
    // сделать абстракции через которые можно будет производить операции с хранилищем
    // продумать логику поиска и сохранения
    // продумать возможные ошибки
    // учесть, что при сохранеии юзера или книги, должен генерироваться идентификатор
    // продумать что у узера может быть много книг и нужно создать эту связь
    // так же учесть, что методы хранилища принимают другой тип данных - учесть это в абстракции

    /*
    Комментарий по реализации: Сделал хранение юзеров и книг в одном хранилище, как указано в задании, хотя напрашивается
    сделать interface Storage с методами хранилища и расширять его в классах UserStorage и BookStorage
     */

    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong userIdentity = new AtomicLong(0);

    private final Map<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong bookIdentity = new AtomicLong(0);


    // methods for User

    public List<User> findAllUser() {
        return new CopyOnWriteArrayList<>(users.values());
    }

    public User findUserById(Long id) {
        return users.get(id);
    }

    public User createUser(User user) {
        if (user.getUserId() == null) {
            Long id = userIdentity.incrementAndGet();
            user.setUserId(id);
        }
        users.put(user.getUserId(), user);

        return user;
    }

    public User updateUser(User user) {
        User userFromStorage = users.values().stream()
                .filter(it -> it.equals(user.getFullName()) && it.equals(user.getTitle()) && it.equals(user.getAge()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Can't update. Such user not found"));

        user.setUserId(userFromStorage.getUserId());
        users.put(user.getUserId(), user);

        return user;
    }

    public User deleteUserById(Long id) {
        User userForDelete = users.get(id);
        users.remove(id);
        return userForDelete;
    }
    
    // methods for Book
    
    public List<Book> findAllBook() {
        return new CopyOnWriteArrayList<>(books.values());
    }

    public Book findBookById(Long id) {
        return books.get(id);
    }

    public Book createBook(Book book) {
        if (book.getId() == null) {
            Long id = bookIdentity.incrementAndGet();
            book.setId(id);
        }
        books.put(book.getId(), book);
        return book;
    }

    public Book updateBook(Book book) {
        Predicate<Book> findEqualsBookPredicate = it -> it.equals(book.getUserId())
                                                        && it.equals(book.getTitle())
                                                        && it.equals(book.getAuthor())
                                                        && it.equals(book.getPageCount());
        Book bookFromStorage = books.values().stream()
                .filter(findEqualsBookPredicate)
                .findFirst()
                .orElse(createBook(book));

        book.setId(bookFromStorage.getId());
        books.put(book.getId(), book);

        return book;
    }

    public Book deleteBookById(Long id) {
        return books.remove(id);
    }
}
