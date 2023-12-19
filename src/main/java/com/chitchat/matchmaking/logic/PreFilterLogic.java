package com.chitchat.matchmaking.logic;

import com.chitchat.matchmaking.exceptions.InvalidRequestException;
import com.chitchat.matchmaking.models.User;
import com.chitchat.matchmaking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PreFilterLogic {

    @Autowired
    private UserService userService;

    public List<List<Integer>> returnValidBattlesToUsers(List<List<Integer>> battlesToUsers,
                                                         boolean isCrossLobby, boolean isCrossGeo)
            throws InvalidRequestException {
        for(int i=0; i<battlesToUsers.size(); i++) {
            List<Integer> inputUserIds = battlesToUsers.get(i);
            for(int j=0; j<inputUserIds.size(); j++) {
                int userId = inputUserIds.get(j);
                User user = userService.getUser(userId);
                boolean markNeg = false;
                if(isCrossGeo && !user.isCrossGeoEligible()) {
                    markNeg = true;
                }
                if(isCrossLobby && !user.isCrossLobbyEligible()) {
                    markNeg = true;
                }
                if(markNeg) {
                    battlesToUsers.get(i).set(j,-1);
                }
            }
        }
        return battlesToUsers;
    }
}
