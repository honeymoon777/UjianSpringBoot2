package com.juaracoding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.model.PerusahaanModel;
import com.juaracoding.repostory.PerusahaanRepository;

@RestController
@RequestMapping("/perusahaan")
public class PerusahaanController {
	@Autowired
	PerusahaanRepository perusahaanRepository;
	
	@PostMapping("/insertPerusahaan")
	private String savePerusahaan(@RequestBody List<PerusahaanModel> model) {
		perusahaanRepository.saveAll(model);
		return "data berhasil di masukan";
	}
}
