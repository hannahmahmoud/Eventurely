package com.website.bookingSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.website.bookingSystem.Model.booking;
import java.util.List;



@Repository
public interface bookingRepo extends JpaRepository<booking,Long>
{
    List<booking> findByUserId(Long userId);
boolean existsByEventIdAndUserId(Long eventId, Long userId);

}
