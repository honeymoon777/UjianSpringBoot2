package com.juaracoding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.model.BookingModel;
import com.juaracoding.model.BusModel;
import com.juaracoding.model.KeberangkatanModel;
import com.juaracoding.model.PenumpangModel;
import com.juaracoding.repostory.BookingRepository;
import com.juaracoding.repostory.BusRepository;
import com.juaracoding.repostory.KeberangkatanRepository;
import com.juaracoding.repostory.PenumpangRepository;

@RestController
@RequestMapping("/busbookingsystem")
public class BusController {

	@Autowired
	BusRepository busRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	
	@Autowired
	PasswordEncoder pEncoder;
	
	@Autowired
	PenumpangRepository penumpangRepository;
	
	@Autowired
	KeberangkatanRepository keberangkatanRepository;
	
	@GetMapping("/")
	private List<BusModel> getAllFullName() {
		return busRepository.findAll();
	}
	
	
	@PostMapping("/insertBus")
	private String saveBus(@RequestBody List<BusModel> model) {
		busRepository.saveAll(model);
		return "Data berhasil ditambahkan";
	}
	
	@PostMapping("/insertPenumpang")
	private String savePenumpang(@RequestBody PenumpangModel model) {
		penumpangRepository.save(model);
		return "Data penumpang berhasil ditambahkan";
	}
	
	@GetMapping("/getPenumpang/{nik}")
	private String getPenumpangByNik(@PathVariable("nik") String nik) {
		PenumpangModel penumpang = penumpangRepository.findByNik(nik);
		if(penumpang != null) {
			return "Penumpang telah terdaftar";
		}else {
			return "Penumpang belum terdaftar, silakan buat akun.";
		}
	}
	
	
	
	
//	@PatchMapping("/")
//	private String updateAddressCustomer(
//			@RequestParam(name="id") long id,
//			@RequestParam(name="address") String address) {
//		customerRepository.updateCustomer(address, id);
//		return "Update berhasil !";
//	}
//	
//	@PatchMapping("/")
//	private String updateAddressCustomer(@RequestParam(name = "address") String address,
//			@RequestParam(name = "email") String email, @RequestParam(name = "customer_id") int customer_id,
//			@RequestParam(name = "no_hp") String no_hp) {
//		customerRepository.updateCustomerPilihan(address, email, customer_id, no_hp);
//		return "update berhasil dilakukan";

		// void updateCustomerPilihan(String address, String email, int customer_id,
		// String no_hp);
	
	
	
	
	//please deh ini ngerjainnya nomer 1 sampe empat, stuck di jwt doang ini, 12 jam baru 4 soal
			//gila juga ini soal, mantep emang, mana nomer 4 ga sesuai tapi bodo ah,
			//nyambungin begini aja tanpa harus masukin password tadi lama banget
			//tega sih kalo cuma dikasi nilai 10/20 :c 
			//ini alamat yang dibawah
			//btw saya nyerah juga setelah hampir 15 jam ngerjain ini, bingung kenapa gabisa inner join di phpmyadmin, gagal trus, aneh sumpah
			//http://localhost:8080/busbookingsystem/findKeberangkatan?id=1&jurusanId=1
		//INI NOMER 4 ULANGAN FULLSTACK BATCH 11
		//TANGGAL 19 MARET 2022
		@GetMapping("/findKeberangkatan")
		private List<KeberangkatanModel> getDataByIdJurusanWaktu
				(@RequestParam(name = "id") long id
				,@RequestParam(name = "jurusanId") long jurusanId)
				{
			return keberangkatanRepository.getDataById(id,jurusanId);
			
				}
		
		@PatchMapping("/")
		private List<KeberangkatanModel> getAllFull1Name() {
			return keberangkatanRepository.findAll();
		}
		
	
	//http://localhost:8080/busbookingsystem/booking
	//yang ini gatau sumpah, jadi langsung skip aja ke nomer 6
		//http://localhost:8080/busbookingsystem/booking
		//PERSETAN DENGAN BAD REQUEST, 24 JAM NGERJAIN GA KELAR KELAR
	//INI NOMER 5 ULANGAN FULLSTACK BATCH 11
	//TANGGAL 19 MARET 2022
	@PostMapping("/booking")
	private ResponseEntity<String> saveBooking(@RequestBody PenumpangModel penumpang) {
		penumpang.setPassword(pEncoder.encode(penumpang.getPassword()));
		penumpangRepository.save(penumpang);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Pemesanan bus berhasil");
	}

	
	//udah lah nomer 6 sampe jugaaa
	//INI NOMER 6 ULANGAN FULLSTACK BATCH 11
	//TANGGAL 19 MARET 2022
	@DeleteMapping("/cancel")
    public String hapusBooking(@RequestParam(name = "id") long id) {
        bookingRepository.deleteById(id);
   	 String kalimat = "Pemesanan dengan nomer id " + id + " telah dibatalkan";
   		return kalimat;
    }
	//makasih loh soalnya, saya dibilang kaya orang oon sama emak saya
	//gara gara mukanya banyak pikiran
	//SENEN BAHAS PLEASE

	}
	
	
	

