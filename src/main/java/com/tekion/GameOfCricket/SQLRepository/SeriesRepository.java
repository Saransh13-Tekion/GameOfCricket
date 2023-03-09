package com.tekion.GameOfCricket.SQLRepository;

import com.tekion.GameOfCricket.Entity.SeriesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends CrudRepository<SeriesEntity,Long> {
}
