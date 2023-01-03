package com.raf.restdemo.repository;

import com.raf.restdemo.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    Optional<Notification> findNotificationById(Long id);
}
