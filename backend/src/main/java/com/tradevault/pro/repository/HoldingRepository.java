package com.tradevault.pro.repository;

import com.tradevault.pro.model.Holding;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoldingRepository extends MongoRepository<Holding, String> {
    //Find by user
    List<Holding> findByUserId(String userId);

    //Find by userId and Symbol
    List<Holding> findByUserAndSymbol(String userId, String symbol);

    //Find by exchange
    List<Holding> findByUserIdAndExchange(String userId, String exchange);
}
