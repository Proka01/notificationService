package com.raf.restdemo.repository;

import com.raf.restdemo.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    Optional<Notification> findNotificationById(Long id);

    //@Query(value = "SELECT * FROM reservationServiceDB.vehicle ss JOIN reservationServiceDB.company s ON(ss.company_id=s.id)  WHERE s.city = ?1 AND s.id = ?2", nativeQuery = true)
    List<Notification> findNotificationsByClientId(Long id);
}
