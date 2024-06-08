package org.softuni.mobilelele.schedulers;

import org.softuni.mobilelele.service.UserActivationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ActivationLinkCleanupScheduler {

    private final UserActivationService userActivationService;

    public ActivationLinkCleanupScheduler(UserActivationService userActivationService) {
        this.userActivationService = userActivationService;
    }

    //    @Scheduled(cron = "*/10 * * * * *")
    @Scheduled(fixedRate = 10000, initialDelay = 10000)
    public void cleanup() {
        System.out.println("Cleaning up activation links.");
        this.userActivationService.cleanUpObsoleteActivationLinks();
    }
}
