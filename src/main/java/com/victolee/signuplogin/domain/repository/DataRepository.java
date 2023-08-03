package com.victolee.signuplogin.domain.repository;

import com.victolee.signuplogin.domain.entity.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<DataEntity, Long> {
}