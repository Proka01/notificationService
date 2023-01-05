package com.raf.restdemo.runner;

import com.raf.restdemo.domain.Notification;
import com.raf.restdemo.domain.NotificationType;
import com.raf.restdemo.repository.NotificationRepository;
import com.raf.restdemo.repository.NotificationTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default"})
@Component
public class TestData implements CommandLineRunner {

    private NotificationRepository notificationRepository;
    private NotificationTypeRepository notificationTypeRepository;

    public TestData(NotificationRepository notificationRepository,NotificationTypeRepository notificationTypeRepository) {
        this.notificationRepository = notificationRepository;
        this.notificationTypeRepository = notificationTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

//        NotificationType notifType = new NotificationType();
//
//        notifType.setType("ACTIVATION_EMAIL");
//        notifType.setEmbededMsg("Hello %firstName% %lastName% !!!");
//        notificationTypeRepository.save(notifType);

//        notifType.setType("CAR_RESERVATION_EMAIL");
//        notifType.setEmbededMsg("Hello %firstName% %lastName% you have rented %carBrand% %carModel% with plates %registrationNum%!!!");
//        notificationTypeRepository.save(notifType);

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
