package com.charu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.charu.entity.CharuEntity;

public interface CharuRepo extends JpaRepository<CharuEntity, Integer>
{

}
