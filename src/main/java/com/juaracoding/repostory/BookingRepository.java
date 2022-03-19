package com.juaracoding.repostory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.juaracoding.model.BookingModel;

public interface BookingRepository extends JpaRepository<BookingModel, Long>{
	
	@Query(value = "INSERT INTO `booking` (`id_keberangkatan`, `nik`) VALUES (?1,?2) ",nativeQuery = true)
	List<BookingModel> getPutBooking(long idKeberangkatan, String nik);
	@Query(value = " DELETE FROM `booking` WHERE id=?1 ",nativeQuery = true)
	List<BookingModel> deleteBookingModels(long id);

}
