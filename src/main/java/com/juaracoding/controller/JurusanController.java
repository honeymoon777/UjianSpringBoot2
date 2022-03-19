package com.juaracoding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.model.JurusanModel;
import com.juaracoding.repostory.JurusanRepository;

@RestController
@RequestMapping("/jurusan")
public class JurusanController {

	@Autowired
	JurusanRepository jurusanRepository;
	
	@PostMapping("/insertJurusan")
	private String saveJurusan(@RequestBody List<JurusanModel> model) {
		jurusanRepository.saveAll(model);
		return "data berhasil ditambahkan";
	}
}
