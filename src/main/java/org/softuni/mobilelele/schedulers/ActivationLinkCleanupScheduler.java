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
    @Scheduled(fixedRate = 900000, initialDelay = 60000)
    public void cleanup() {
        System.out.println("cleaning up activation links");
        this.userActivationService.cleanUpObsoleteActivationLinks();
    }
}
