package com.raf.restdemo.runner;

import com.raf.restdemo.domain.Notification;
import com.raf.restdemo.repository.NotificationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default"})
@Component
public class TestData implements CommandLineRunner {

    private NotificationRepository notificationRepository;

    public TestData(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

//        Notification notification = new
//                Notification("ACTIVATION_EMAIL",
//                "Hello %firstName% %lastName% !",
//                3L,
//                1L,
//                null);
//
//        notificationRepository.save(notification);
    }
}
