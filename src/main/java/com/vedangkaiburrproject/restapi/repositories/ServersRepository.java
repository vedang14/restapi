package com.vedangkaiburrproject.restapi.repositories;

import com.vedangkaiburrproject.restapi.models.Servers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ServersRepository extends MongoRepository<Servers,String> {
    List<Servers> findByName(String name);
}

