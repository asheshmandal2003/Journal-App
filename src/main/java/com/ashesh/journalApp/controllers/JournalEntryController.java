package com.ashesh.journalApp.controllers;

import com.ashesh.journalApp.entities.JournalEntry;
import com.ashesh.journalApp.services.JournalEntryService;
import com.ashesh.journalApp.utils.ResponseData;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal-entries")
public class JournalEntryController {

	@Autowired
	private JournalEntryService journalEntryService;

	@PostMapping
	public ResponseEntity<ResponseData> saveJournalEntry(
			@RequestParam String username,
			@RequestBody JournalEntry newJournalEntry) {
		try {
			JournalEntry journalEntry = journalEntryService.saveJournalEntry(username, newJournalEntry);
			return new ResponseEntity<>(
					new ResponseData("Journal entry added!", journalEntry),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ResponseData(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<ResponseData> getAllJournalEntriesOfUser(@RequestParam String username) {
		try {
			List<JournalEntry> journalEntries = journalEntryService.getAllJournalEntries(username);
			return new ResponseEntity<>(
					new ResponseData("Journal entries fetched!", journalEntries),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ResponseData(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseData> getJournalEntry(@PathVariable ObjectId id) {
		try {
			JournalEntry journalEntry = journalEntryService.getJournalEntryById(id);
			return new ResponseEntity<>(
					new ResponseData("Journal entry fetched!", journalEntry),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ResponseData(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseData> updateJournalEntry(
			@PathVariable ObjectId id,
			@RequestParam String username,
			@RequestBody JournalEntry newJournalEntry) {
		try {
			JournalEntry journalEntry = journalEntryService.updateJournalEntry(id, newJournalEntry, username);
			return new ResponseEntity<>(
					new ResponseData("Journal entry updated!", journalEntry),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ResponseData(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteJournalEntry(
			@PathVariable ObjectId id,
			@RequestParam String username) {
		try {
			journalEntryService.deleteJournalEntry(id, username);
			return new ResponseEntity<>(
					new ResponseData("Journal entry deleted!", null),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ResponseData(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
