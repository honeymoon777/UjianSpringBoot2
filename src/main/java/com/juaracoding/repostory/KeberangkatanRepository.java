package com.juaracoding.repostory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.juaracoding.model.KeberangkatanModel;

public interface KeberangkatanRepository extends JpaRepository<KeberangkatanModel, Long>{
	
	//please deh ini ngerjainnya nomer 1 sampe empat, stuck di jwt doang ini, 12 jam baru 4 soal
	//gila juga ini soal, mantep emang, mana nomer 4 ga sesuai tapi bodo ah,
	//nyambungin begini aja tanpa harus masukin password tadi lama banget
	//tega sih kalo cuma dikasi nilai 10/20 :c 
	//http://localhost:8080/busbookingsystem/findKeberangkatan?id=1&jurusanId=1
	@Query(value = "SELECT * FROM `keberangkatan` WHERE id = ?1 " 
	+ " AND jurusan_id = ?2 ", nativeQuery = true)
	List<KeberangkatanModel> getDataById(long id, long jurusanId);
}

	

//@Transactional
//@Modifying
/////	@Query(value = "UPDATE `customer` SET `address`=?1 ,"
////		+ "`email`=?2 WHERE customer_id>?3 AND no_hp LIKE %?4% ", nativeQuery = true)
//void updateCustomerPilihan(String address, String email, int customer_id, String no_hp);

	
//@Query(value = "SELECT * FROM `customer` WHERE first_name LIKE ?1% " + " AND last_name = ?2 ", nativeQuery = true)
//List<CustomerModel> getByFirstName(String firstname, String lastname);
//
