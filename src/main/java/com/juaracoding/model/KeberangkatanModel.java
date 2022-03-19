package com.juaracoding.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "keberangkatan")
public class KeberangkatanModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int harga;
	private String kelas;
	private String tanggal;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "jurusanId", referencedColumnName = "id")
	private JurusanModel jurusanId;
	
	private String noPolisi;

	//INI NOMER 1 ULANGAN FULLSTACK BATCH 11
	//TANGGAL 19 MARET 2022
	
}
