//package kz.iitu.library;
//
//import kz.iitu.library.controllers.BookController;
//import kz.iitu.library.controllers.GenreController;
//import kz.iitu.library.controllers.UserController;
//import kz.iitu.library.models.*;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import java.util.Collections;
//
//public class Main {
//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LibraryApplication.class);
//        //создаем контроллеры через которые управляем всеми что есть
//        UserController userController = context.getBean("userController", UserController.class);
//        BookController bookController = context.getBean("bookController", BookController.class);
//        GenreController genreController = context.getBean("genreController", GenreController.class);
//
//        // создаем автора
//        userController.addAuthor(new Author("Lev Tolstoy"));
//        // создаем 2 автора
//        userController.addAuthor(new Author("Mukhtar Auezov"));
//        // создаем жанр
//        genreController.addGenre(new Genre("Documental"));
//
//        //Cоздаем книгу
//        Book book = new Book();
//        // Название
//        book.setTitle("Abay zholy");
//        // добавляем в базу данных
//        bookController.addBook(book);
//        // добавляем автора книге
//        book.setAuthors(Collections.singletonList(userController.findAuthorByName("Mukhtar Auezov")));
//        // добавляем жанр книги
//        book.setGenres(Collections.singletonList(genreController.findGenreByName("Documental")));
//        // обновляем книгу
//        bookController.editBook(book);
//
//        // точно так же создаем вторую книгу
//        Book book1 = new Book();
//        book1.setTitle("Voina i mir");
//        bookController.addBook(book1);
//        book1.setAuthors(Collections.singletonList(userController.findAuthorByName("Lev Tolstoy")));
//        book1.setGenres(Collections.singletonList(genreController.findGenreByName("Documental")));
//        bookController.editBook(book1);
//
//        // создаем юзера
//        User user = new User();
//        // даем имя юзеру
//        user.setName("Dauren Buribekov");
//        // добавляем юзера в бд
//        userController.addUser(user);
//
//        // создаем юзера
//        User user1 = new User();
//        // даем имя юзеру
//        user1.setName("Кто-то Что-то");
//        // добавляем юзера в бд
//        userController.addUser(user1);
//        // изменяем юзера и даем тип эксперт
//        user1.setType(Type.EXPERT);
//        userController.editUser(user1);
//
//        // это на случай если в базе данных юзер уже существует
////        User user = userController.findUserByName("Dauren Buribekov");
//        // а это если книга уже существует
////        Book book = bookController.findBookByName("Voina i mir");
//
//        // находим книгу по имени
//        System.out.println(bookController.findBookByName("Voina i mir"));
//        // находим все свободные книги
//        bookController.findAllByStatus(Status.AVAILABLE);
//        // когда через юзер контроллер добавляем книгу юзеру то у книги обновляется состояние на REQUESTED
//        userController.addBook(user.getId(), book.getId());
//        userController.addBook(user1.getId(), book1.getId());
//
//        // находим книги которые запрашиваются юзерами
//        bookController.findAllByStatus(Status.REQUESTED);
//        // когда через бук контроллер добавляем книгу юзеру то у книги обновляется состояние на ISSUED
//        // так как бук контроллер действует как библиотекарь и уведомление приходит в обе стороны
//        bookController.addBookToUser(user.getId(), book.getId());
//        bookController.addBookToUser(user1.getId(), book1.getId());
//
//        // находим книги которые у юзеров
//        bookController.findAllByStatus(Status.ISSUED);
//
//        // очищаем все
//        // бук всегда должен быть первым так как она удаляет жанры у книг
//        bookController.clear();
//        genreController.clear();
//        userController.clear();
//
//// то что выводится в консоле
//        // Author{id=16, name='Lev Tolstoy}Автор добавлен
//        // Author{id=17, name='Mukhtar Auezov}Автор добавлен
//        // Genre{id=9, name='Documental'}Жанр добавлен
//        // Книга Book(id=65, title=Abay zholy, genres=null, authors=null, user=null, status=AVAILABLE, givenDate=null, dueDate=null) добавлена
//        // Книга Book(id=65, title=Abay zholy, genres=[Genre{id=9, name='Documental'}], authors=[Author{id=17, name='Mukhtar Auezov}], user=null, status=AVAILABLE, givenDate=null, dueDate=null) переработано
//        // Книга Book(id=66, title=Voina i mir, genres=null, authors=null, user=null, status=AVAILABLE, givenDate=null, dueDate=null) добавлена
//        // Книга Book(id=66, title=Voina i mir, genres=[Genre{id=9, name='Documental'}], authors=[Author{id=16, name='Lev Tolstoy}], user=null, status=AVAILABLE, givenDate=null, dueDate=null) переработано
//        // Пользователь User{id=8, name='Dauren Buribekov', type='NEWBIE'} добавлен
//        // Пользователь User{id=11, name='Кто-то Что-то', type='EXPERT'} добавлен
//
//        // Book(id=66, title=Voina i mir, genres=[Genre{id=9, name='Documental'}], authors=[Author{id=16, name='Lev Tolstoy}], user=null, status=AVAILABLE, givenDate=null, dueDate=null)
//        // [Book(id=65, title=Abay zholy, genres=[Genre{id=9, name='Documental'}], authors=[Author{id=17, name='Mukhtar Auezov}], user=null, status=AVAILABLE, givenDate=null, dueDate=null), Book(id=66, title=Voina i mir, genres=[Genre{id=9, name='Documental'}], authors=[Author{id=16, name='Lev Tolstoy}], user=null, status=AVAILABLE, givenDate=null, dueDate=null)]
//        // [Book(id=65, title=Abay zholy, genres=[Genre{id=11, name='Documental'}], authors=[Author{id=21, name='Mukhtar Auezov}], user=User{id=10, name='Dauren Buribekov, type='NEWBIE}, status=REQUESTED, givenDate=null, dueDate=null), Book(id=66, title=Voina i mir, genres=[Genre{id=11, name='Documental'}], authors=[Author{id=20, name='Lev Tolstoy}], user=User{id=11, name='Кто-то Что-то, type='EXPERT}, status=REQUESTED, givenDate=null, dueDate=null)]
//
//        // Книга добавлена пользователю 8
//        // Книга теперь у клиента Dauren Buribekov
//        // Книга Abay zholy теперь у вас
//        // Книга добавлена пользователю 11
//        // Книга теперь у клиента Кто-то Что-то
//        // Книга Voina i mir теперь у вас
//// Книга которая дана типу NEWBIE дана ему на 2 недели
//// А EXPERTу на месяц
//        // [Book(id=65, title=Abay zholy, genres=[Genre{id=11, name='Documental'}], authors=[Author{id=21, name='Mukhtar Auezov}], user=User{id=8, name='Dauren Buribekov, type='NEWBIE}, status=ISSUED, givenDate=2020-04-04 18:02:00.324, dueDate=2020-04-18 18:02:00.324),
//        // Book(id=66, title=Voina i mir, genres=[Genre{id=11, name='Documental'}], authors=[Author{id=20, name='Lev Tolstoy}], user=User{id=11, name='Кто-то Что-то, type='EXPERT}, status=ISSUED, givenDate=2020-04-04 18:02:00.336, dueDate=2020-05-04 18:02:00.336)]
//
//
//
//
//
//    }
//}
