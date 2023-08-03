package com.victolee.signuplogin.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "data")
public class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(length = 20)
    private String data;

    @Column(length = 100)
    private String type;
    @Column(length = 100)
    private String time;

    //@Column(length = 20, nullable = false)
    //private LocalDateTime time;

    @Builder
    public DataEntity(Long id,String name, String data, String type, String time) {
        this.id = id;
        this.name = name;
        this.data = data;
        this.type = type;
        this.time = time;
    }
}