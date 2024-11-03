package org.poc.at.service;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.poc.at.model.Publisher;
import org.poc.at.repos.RepoHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class TestService {
    
    RepoHolder.PublisherRepository publisherRepository;
    
    @PostConstruct
    void init() {
        Publisher testPublisher =  Publisher.builder()
                .name("testPublisher 2")
                .build();        
//        publisherRepository.save(testPublisher);

        Publisher testPublisher2 =  Publisher.builder()
                .name("testPublisher")
                .build();
//        publisherRepository.save(testPublisher2);

        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA");
//        publisherRepository.findAll().forEach(System.out::println);
    }
}

