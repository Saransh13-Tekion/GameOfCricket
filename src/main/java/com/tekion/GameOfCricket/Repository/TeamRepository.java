package com.tekion.GameOfCricket.Repository;

import com.tekion.GameOfCricket.Entity.TeamEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface TeamRepository extends CrudRepository<TeamEntity,Long> {
}
