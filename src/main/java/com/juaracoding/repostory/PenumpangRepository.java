package com.juaracoding.repostory;

import org.springframework.data.jpa.repository.JpaRepository;


import com.juaracoding.model.PenumpangModel;

public interface PenumpangRepository extends JpaRepository<PenumpangModel, String>{
	PenumpangModel findByNik(String nik);
	PenumpangModel findByUsername(String username);
	//void updateCustomer(String address, long id);
}
