package com.worldexplorer.springboot.data.redis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.worldexplorer.springboot.data.redis.domain.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer>{
}
