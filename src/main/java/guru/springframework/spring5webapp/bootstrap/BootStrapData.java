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

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book book = new Book("Domain Driven Design", "123456");
        eric.getBooks().add(book);
        book.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(book);

        Author rod = new Author("Rod", "Johnson");
        Book book1 = new Book("J2EE development without EJ8", "12345566");
        rod.getBooks().add(book1);
        book1.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(book1);


        System.out.println("Started Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());

        Publisher mondadori = new Publisher("Mondadori Editore", "Via Milano 55, Milano");
        publisherRepository.save(mondadori);

        System.out.println("Publisher saved -> " + mondadori);
    }
}
