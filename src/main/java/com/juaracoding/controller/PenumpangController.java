package com.juaracoding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.Service.JwtPenumpangDetailService;
import com.juaracoding.config.JwtTokenUtil;
import com.juaracoding.model.PenumpangModel;
import com.juaracoding.repostory.PenumpangRepository;

@RestController
@RequestMapping("/penumpang")
public class PenumpangController {

	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	PenumpangRepository penumpangRepository;
	
	@Autowired
	JwtPenumpangDetailService jwtPenumpangDetailService;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	PasswordEncoder pEncoder;
	
	@GetMapping("/")
	private List<PenumpangModel> getAllFullName() {
		return penumpangRepository.findAll();
	}
	
	//@GetMapping("/querynative")
//	private List<CustomerModel> getDataByIdDanFirstName(
//			@RequestParam(name ="id") long id,
//			@RequestParam(name ="firstName") String firstName) {
//		return customerRepository.getDataById(id, firstName);
//	}
	
	@PostMapping("/registrasi")
	private ResponseEntity<String> saveCustomer(@RequestBody PenumpangModel penumpang) {
		penumpang.setPassword(pEncoder.encode(penumpang.getPassword()));
		penumpangRepository.save(penumpang);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("berhasil dibuat");
	}
//	@PatchMapping("/")
//	private String updateAddressCustomer(
//			@RequestParam(name="id") long id,
//			@RequestParam(name="address") String address) {
//		penumpangRepository.updateCustomer(address, id);
//		return "Update berhasil !";
//	}
	
	
	//eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJCYW5nQVdFUCIsImV4cCI6MTY0NzU5MzA3MSwiaWF0IjoxNjQ3NTc1MDcxfQ.VDl5AtNqpyu14_sj7GObipmmHu7bQGCmB8Le83L26jxbAvVM9fzQmklstgNbU4HWsnJ22I445lDa8Ma1oWqFQw
	//http://localhost:8080/penumpang/login
	//ngebuat ginian aja lama banget, baru 3 soal loh udah ngerjain sampe 8 jam gara gara error terus
	//INI NOMER 3 ULANGAN FULLSTACK BATCH 11
	//TANGGAL 19 MARET 2022
	@PostMapping("/login")
	private ResponseEntity<?> login(@RequestBody PenumpangModel penumpangModel) throws Exception {
		authenticate(penumpangModel.getUsername(),penumpangModel.getPassword());
		
		final UserDetails userDetails = jwtPenumpangDetailService
				.loadUserByUsername(penumpangModel.getUsername());
		
		final String  token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(token);
	}
	 
	private void authenticate(String username,String password) throws Exception  {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException error) {
			//User disabled
			throw new Exception("USER_DISABLED",error);
		} catch (BadCredentialsException error) {
			//invalid credentials
			throw new Exception("INVALID_CREDENTIALS",error);
		}
	}
}
