package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started Bootstrap");

        Publisher mondadori = new Publisher("Mondadori Editore", "Via Milano 55", "Milano", "MI", "00000");
        publisherRepository.save(mondadori);

        System.out.println("Number of publisher: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123456");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(mondadori);
        mondadori.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(mondadori);

        Author rod = new Author("Rod", "Johnson");
        Book book1 = new Book("J2EE development without EJ8", "12345566");
        rod.getBooks().add(book1);
        book1.getAuthors().add(rod);

        book1.setPublisher(mondadori);
        mondadori.getBooks().add(book1);

        authorRepository.save(rod);
        bookRepository.save(book1);
        publisherRepository.save(mondadori);

        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher numer of books: " +  mondadori.getBooks().size());


    }
}
