package com.chitchat.matchmaking.schedulingtasks;

import com.chitchat.matchmaking.logic.MMLogic;
import com.chitchat.matchmaking.models.ParticipationInfo;
import com.chitchat.matchmaking.repository.ParticipationInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    private ParticipationInfoRepository participationInfoRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(initialDelay = 100, fixedRate = 200)
    public void checkAndScheduleMatchMaking() {
        List<ParticipationInfo> participationInfoList =
                participationInfoRepository.getAllUnprocessedParticipations();
        if (participationInfoList== null || participationInfoList.isEmpty()) {
            return;
        }
        for(ParticipationInfo participationInfo : participationInfoList) {
            // Get Score from external source
        }
    }
}
