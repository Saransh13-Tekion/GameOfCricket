package com.tekion.GameOfCricket.Repository;

import com.tekion.GameOfCricket.Entity.PlayerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerEntity,Long> {
}
