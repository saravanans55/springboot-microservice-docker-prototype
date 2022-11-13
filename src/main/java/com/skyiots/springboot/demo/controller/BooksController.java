package com.skyiots.springboot.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyiots.springboot.demo.model.Books;
import com.skyiots.springboot.demo.repository.BookRepository;
import com.skyiots.springboot.demo.exception.ResourceNotFoundException;
import com.skyiots.springboot.demo.exception.ResposeMessage;


@RestController
@RequestMapping("/api/v1")
public class BooksController {

	@Autowired
	private BookRepository bookRepository;

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/book/list")
	public List<Books> getAllBooks() {
		return bookRepository.findAll();
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Books> getBookById(@PathVariable(value = "id") Long bookId)
			throws ResourceNotFoundException {
		Books book = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));
		return ResponseEntity.ok().body(book);
	}
	
	@PostMapping("/book/create")
	public Books createBook(@Valid @RequestBody Books books) {
		return bookRepository.save(books);
	}
	
	@PutMapping("/book/update/{bookSeqId}")
	public ResponseEntity<Object> update(@PathVariable(value = "bookSeqId") Long bookSeqId,
			@Valid @RequestBody Books bookObj) throws ResourceNotFoundException {

		ResposeMessage errRes = new ResposeMessage();
		errRes.setStatus(false);
		errRes.setData(null);

		if(bookObj.getBookId().isEmpty() || bookObj.getBookId() == null) {

			errRes.setMessage("Book Id is required!");
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(errRes);

		}else if(bookObj.getBookTitle().isEmpty() || bookObj.getBookTitle() == null) {

			errRes.setMessage("Book Title  is required!");
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(errRes);

		}else if(bookObj.getPrice() == null || bookObj.getPrice() < 0) {

			errRes.setMessage("Book price is required!");
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(errRes);

		}else if(bookObj.getAuthorName().isEmpty() || bookObj.getAuthorName() == null) {

			errRes.setMessage("Author Name is required!");
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(errRes);

		}else {

			Books book = bookRepository.findById(bookSeqId).orElseThrow(() ->
			new ResourceNotFoundException("Book not found for this id :: " + bookSeqId));

			book.setBookId(bookObj.getBookId());
			book.setBookTitle(bookObj.getBookTitle());
			book.setBookDesc(bookObj.getBookDesc());
			book.setPrice(bookObj.getPrice());
			book.setAuthorName(bookObj.getAuthorName());
			final Books updatedBook = bookRepository.save(book);
			return ResponseEntity.ok(updatedBook);
		}
	}
	
	@DeleteMapping("/book/delete/{bookSeqId}")
	public ResponseEntity<Object> deleteBook(@PathVariable(value = "bookSeqId") Long bookSeqId)
			throws ResourceNotFoundException {

		ResposeMessage resMsg = new ResposeMessage();
		resMsg.setStatus(false);
		resMsg.setData(null);

		if(bookSeqId == null) {

			resMsg.setMessage("Book Unique Id is required!");
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(resMsg);

		}else {
			Books book = bookRepository.findById(bookSeqId).orElseThrow(() ->
			new ResourceNotFoundException("Book not found for this id :: " + bookSeqId));

			bookRepository.delete(book);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);

			resMsg.setStatus(true);
			resMsg.setMessage("Given Book Deleted!");

			return ResponseEntity.ok(resMsg);
		}
	} 

}
