package com.speer.Note.controller;

import com.speer.Note.entity.Note;
import com.speer.Note.repository.NoteRepository;
import com.speer.Note.service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
	@Autowired
    private NoteService noteService;

    @GetMapping("/")
    public List<Note> getAllNotes(@RequestHeader("username") String username) {
        return noteService.findAllNotes(username);
    }
    
    @GetMapping("/{id}")
    public Note getNote(@PathVariable Long id, @RequestHeader("username") String username) {
        return noteService.findNote(id, username);
    }

    @PostMapping("/")
    public boolean createNote(@RequestBody Note note, @RequestHeader("username") String username) {
        return noteService.saveNote(note, username);
    }
    
    @PutMapping("/{id}")
    public boolean updateNote(@PathVariable Long id, @RequestBody Note note, @RequestHeader("username") String username) {
        return noteService.updateNote(id, note, username);
    }

    @PostMapping("/{id}/share")
    public boolean shareNote(@PathVariable Long id, @RequestParam String user, @RequestHeader("username") String username) {
        
    	return noteService.shareNote(id, user, username);
    }
    
    @GetMapping("/search")
    public List<Note> searchNotes(@RequestParam String query, @RequestHeader("username") String username) {
        return noteService.searchNotes(query, username);
    }
}
