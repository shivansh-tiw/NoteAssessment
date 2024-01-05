package com.speer.Note.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.speer.Note.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

	List<Note> findByUsernameAndTitleContainingOrUsernameAndContentContaining(
            String username, String title, String username2, String content);
    // Add custom query methods if needed

	List<Note> findByUsername(String username);
}
