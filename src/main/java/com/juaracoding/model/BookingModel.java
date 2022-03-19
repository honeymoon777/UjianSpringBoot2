package com.juaracoding.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class BookingModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idKeberangkatan", referencedColumnName = "id")
	private KeberangkatanModel idKeberangkatan;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "nik", referencedColumnName = "nik")
	private PenumpangModel nik;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idJurusan", referencedColumnName = "id")
	private JurusanModel idJurusan;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "noPolisi", referencedColumnName = "noPolisi")
	private BusModel noPolisi;
	private String tanggal;

}
