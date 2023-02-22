package com.tekion.GameOfCricket.Repository;

import com.tekion.GameOfCricket.Entity.MatchEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MatchRepository extends CrudRepository<MatchEntity,Long> {
}
