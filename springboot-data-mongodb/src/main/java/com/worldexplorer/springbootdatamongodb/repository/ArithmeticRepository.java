package com.worldexplorer.springbootdatamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.worldexplorer.springbootdatamongodb.domain.Arithmetic;

public interface ArithmeticRepository extends MongoRepository<Arithmetic, String>{

}
