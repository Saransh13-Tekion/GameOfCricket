package com.tekion.GameOfCricket.SQLRepository;

import com.tekion.GameOfCricket.Entity.PlayerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerEntity,Long>{
}
