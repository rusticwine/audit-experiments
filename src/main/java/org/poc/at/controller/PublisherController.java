package org.poc.at.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.poc.at.model.Publisher;
import org.poc.at.service.AuditService;
import org.poc.at.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
@Tag(name = "Publisher", description = "Publisher management APIs")
@RequiredArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class PublisherController {
    
    PublisherService publisherService;
    
    AuditService auditService;   
    

    @Operation(summary = "Get all publishers")
    @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.findAll();
    }

    @Operation(summary = "Get audit trail for publishers")
    @GetMapping("/audit/{id}")
    public List<Object[]> getAllPublisherAudit(@PathVariable Long id) {
        return auditService.getPublisherRevisions(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher updatedPublisher) {
        Publisher publisher = publisherService.updatePublisher(id, updatedPublisher);
        if (publisher != null) {
            return ResponseEntity.ok(publisher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Get publisher by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable Long id) {
        return publisherService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new publisher")
    @PostMapping
    public Publisher createPublisher(@RequestBody Publisher publisher) {
        return publisherService.save(publisher);
    }

    @Operation(summary = "Delete a publisher by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

