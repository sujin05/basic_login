package com.victolee.signuplogin.service;

import com.victolee.signuplogin.domain.entity.DataEntity;
import com.victolee.signuplogin.domain.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    private final DataRepository dataRepository;
    @Autowired
    public  DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void saveData(DataEntity dataEntity){
        dataRepository.save(dataEntity);
    }
}
