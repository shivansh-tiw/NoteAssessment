package com.speer.Note.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speer.Note.entity.Note;
import com.speer.Note.repository.NoteRepository;

@Service
public class NoteService {
	
	@Autowired
	NoteRepository noteRepository;
	
	public List<Note> findAllNotes(String username) {
		try {
			return noteRepository.findByUsername(username);
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	public Note findNote(Long id, String username) {
		if(noteRepository.existsById(id)) {
			Note note = noteRepository.findById(id).get();
			if(note.getUsername().equals(username)) {
				return note;
			}
		}
		return null;
	}

	public boolean saveNote(Note note, String username) {
		try {
			note.setUsername(username);
			noteRepository.save(note);
			return true;
		} catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean updateNote(Long id, Note note, String username) {
		if(noteRepository.existsById(id)) {
			Note oldNote = noteRepository.findById(id).get();
			if(oldNote.getUsername().equals(username)) {
				note.setId(id);
				note.setUsername(username);
				noteRepository.save(note);
				return true;
			}
			else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean shareNote(Long id, String user, String username) {
		try {
			if(noteRepository.existsById(id)) {
				Note sharedNote = noteRepository.findById(id).get();
				Note note = new Note(); 
				note.setUsername(user);
				note.setTitle(sharedNote.getTitle());
				note.setContent(sharedNote.getContent());
				noteRepository.save(note);
				return true;
			}
			return false;
		}
		catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
	}

	public List<Note> searchNotes(String query, String username) {
		try {
			List<Note> notes = noteRepository.findByUsernameAndTitleContainingOrUsernameAndContentContaining(username, query, username, query);
			return notes;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
