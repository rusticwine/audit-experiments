package org.poc.at.repos;


import org.poc.at.model.Author;
import org.poc.at.model.Book;
import org.poc.at.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class RepoHolder {
    
    public interface BookRepository extends JpaRepository<Book, Long> {        
    }

    public interface AuthorRepository extends JpaRepository<Author, Long> {
    }

    @Repository
    public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    }
}
