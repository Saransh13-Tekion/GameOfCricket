package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.Dcoument.PlayerDocument;
import com.tekion.GameOfCricket.Repository.PlayerMongoRepository;
import com.tekion.GameOfCricket.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public abstract class MongoDBRepository {

    @Autowired
    private static PlayerMongoRepository playerMongoRepository;
    @Autowired
    private static PlayerRepository playerRepository;
    public static String mode;

    public static Object getInstance(){
        /*if(Objects.equals(mode, "MongoDB")){
            return playerMongoRepository;
        } else {
            return playerRepository;
        }*/
        return playerRepository;
    }
}
