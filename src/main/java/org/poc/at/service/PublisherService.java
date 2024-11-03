package org.poc.at.service;

import org.poc.at.model.Publisher;
import org.poc.at.repos.RepoHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    @Autowired
    private RepoHolder.PublisherRepository publisherRepository;

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public Optional<Publisher> findById(Long id) {
        return publisherRepository.findById(id);
    }

    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public void deleteById(Long id) {
        publisherRepository.deleteById(id);
    }
    
    public Publisher updatePublisher(Long id, Publisher updatedPublisher) {
        Optional<Publisher> existingPublisher = publisherRepository.findById(id);
        if (existingPublisher.isPresent()) {
            Publisher publisher = existingPublisher.get();
            publisher.setName(updatedPublisher.getName());
            // Update other fields as necessary
            return publisherRepository.save(publisher);
        }
        return null;
    }

}
