package com.juaracoding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="penumpang")
public class PenumpangModel {
	
	@Id
	@Column(length = 20)
	private String nik;
	private String nama;
	private String username;
	private String password;
	private String telepon;
	
	
	//INI NOMER 2 ULANGAN FULLSTACK BATCH 11
	//TANGGAL 19 MARET 2022
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private	long id;
//	@Column(nullable = false,unique = true)
//	private String nik;
//	private String nama;
//	private String telepon;

}
