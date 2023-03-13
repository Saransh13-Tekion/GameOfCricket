package com.tekion.GameOfCricket.ElasticSearchDocuments;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Data
@Document(indexName = "scoreboard")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoreBoardES {
    @Id
    private Long id;
    @Field(type = FieldType.Date, name = "createdAt")
    private LocalDateTime createdAt;
    @Field(type = FieldType.Date, name = "updatedAt")
    private LocalDateTime updatedAt;
    @Field(type = FieldType.Long, name = "teamId")
    private Long teamId;
    @Field(type = FieldType.Long, name = "matchId")
    private Long matchId;
    @Field(type = FieldType.Long, name = "playerId")
    private Long playerId;
    @Field(type = FieldType.Integer, name = "runsScored")
    private int runsScored;
    @Field(type = FieldType.Integer, name = "wicketsTaken")
    private int wicketsTaken;
    @Field(type = FieldType.Integer, name = "ballsPlayed")
    private int ballsPlayed;
    @Field(type = FieldType.Text, name = "playerRole")
    private String playerRole;
}
