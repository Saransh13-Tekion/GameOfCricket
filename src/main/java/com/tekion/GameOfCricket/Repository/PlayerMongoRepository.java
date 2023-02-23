package com.tekion.GameOfCricket.Repository;

import com.tekion.GameOfCricket.Document.PlayerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerMongoRepository extends MongoRepository<PlayerDocument,Long> {
}

