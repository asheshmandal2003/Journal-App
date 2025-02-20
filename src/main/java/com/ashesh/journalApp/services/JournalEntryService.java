package com.ashesh.journalApp.services;

import com.ashesh.journalApp.entities.JournalEntry;
import com.ashesh.journalApp.entities.User;
import com.ashesh.journalApp.repositories.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
	@Autowired
	private JournalEntryRepository journalEntryRepository;
	@Autowired
	private UserService userService;

	@Transactional
	public JournalEntry saveJournalEntry(String username, JournalEntry newJournalEntry) throws Exception {
		User user = userService.getUserByUsername(username);
		newJournalEntry.setCreatedAt(LocalDateTime.now());
		JournalEntry journalEntry = journalEntryRepository.save(newJournalEntry);
		user.getJournalEntries().add(journalEntry);
		userService.saveUser(user);
		return journalEntry;
	}

	public List<JournalEntry> getAllJournalEntries(String username) throws Exception {
		User user = userService.getUserByUsername(username);
		return user.getJournalEntries();
	}

	public JournalEntry getJournalEntryById(ObjectId id) throws Exception {
		Optional<JournalEntry> journalEntry = journalEntryRepository.findById(id);
		if(journalEntry.isEmpty()){
			throw new Exception("Journal entry not found!");
		}
		return journalEntry.get();
	}

	public JournalEntry updateJournalEntry(ObjectId id, JournalEntry newJournalEntry, String username) throws Exception {
		JournalEntry journalEntry = getJournalEntryById(id);

		if (newJournalEntry.getTitle() != null && !newJournalEntry.getTitle().trim().isEmpty() && !newJournalEntry.getTitle().trim().equals(journalEntry.getTitle())) {
			journalEntry.setTitle(newJournalEntry.getTitle().trim());
		}

		if (newJournalEntry.getContent() != null && !newJournalEntry.getContent().trim().isEmpty() && !newJournalEntry.getContent().trim().equals(journalEntry.getContent())) {
			journalEntry.setContent(newJournalEntry.getContent().trim());
		}

		JournalEntry saved = journalEntryRepository.save(journalEntry);
		return saved;
	}

	public void deleteJournalEntry(ObjectId id, String username) throws Exception {
		User user = userService.getUserByUsername(username);
		journalEntryRepository.deleteById(id);
		user.getJournalEntries().removeIf(journalEntry -> journalEntry.getId().equals(id));
		userService.saveUser(user);
	}
}
