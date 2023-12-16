package com.chitchat.matchmaking.repository;

import com.chitchat.matchmaking.models.UserDevice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UserDeviceRepository extends MongoRepository<UserDevice, Integer>,
        CrudRepository<UserDevice, Integer>,
        PagingAndSortingRepository<UserDevice, Integer>,
        QueryByExampleExecutor<UserDevice> {
}
