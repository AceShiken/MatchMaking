package com.chitchat.matchmaking.dao.impl;

import com.chitchat.matchmaking.dao.BattleDao;
import com.chitchat.matchmaking.models.Battle;
import com.chitchat.matchmaking.repository.BattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class BattleDaoImpl implements BattleDao {

    @Autowired
    private BattleRepository battleRepository;

}
