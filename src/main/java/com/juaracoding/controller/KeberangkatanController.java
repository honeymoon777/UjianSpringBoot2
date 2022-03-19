package com.juaracoding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.model.KeberangkatanModel;
import com.juaracoding.repostory.KeberangkatanRepository;

@RestController
@RequestMapping("/keberangkatan")
public class KeberangkatanController {

	@Autowired
	KeberangkatanRepository keberangkatanRepository;
	
	@GetMapping("/")
	private List<KeberangkatanModel> getAll() {
		return keberangkatanRepository.findAll();
	}
	
	@PostMapping("/insertKeberangkatan")
	private String saveKeberangkatan(@RequestBody List<KeberangkatanModel> model) {
		keberangkatanRepository.saveAll(model);
		return "data berhasil di tambahkan";
	}
}
