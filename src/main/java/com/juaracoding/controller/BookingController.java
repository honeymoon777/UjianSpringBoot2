package com.juaracoding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.model.BookingModel;
import com.juaracoding.model.PenumpangModel;
import com.juaracoding.repostory.BookingRepository;

@RestController
@RequestMapping("/bookingsystem")
public class BookingController {
	
	
	//http://localhost:8080/bookingsystem/
	@Autowired
	BookingRepository bookingrepo;
	@GetMapping("/")
	private List<BookingModel> getAllFullName() {
		return bookingrepo.findAll();
	}

}
