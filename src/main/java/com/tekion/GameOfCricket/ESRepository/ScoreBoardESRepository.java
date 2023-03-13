package com.tekion.GameOfCricket.ESRepository;

import com.tekion.GameOfCricket.ElasticSearchDocuments.ScoreBoardES;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreBoardESRepository extends ElasticsearchRepository<ScoreBoardES,Long> {
}

