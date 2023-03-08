package com.tekion.GameOfCricket.Repository;

import com.tekion.GameOfCricket.Entity.ScoreBoardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreBoardRepository extends CrudRepository<ScoreBoardEntity,Long> {
}
