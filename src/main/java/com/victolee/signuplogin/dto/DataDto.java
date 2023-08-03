package com.victolee.signuplogin.dto;

import com.victolee.signuplogin.domain.entity.DataEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DataDto {
    private Long id;
    private String name;
    private String data;
    private String type;
    private String time;

    public DataEntity toEntity(){
        return DataEntity.builder()
                .id(id)
                .name(name)
                .data(data)
                .type(type)
                .time(time)
                .build();
    }

    @Builder
    public DataDto(Long id,String name, String data, String type, String time) {
        this.id = id;
        this.name = name;
        this.data = data;
        this.type = type;
        this.time = time;
    }
}

