package com.kns.tenquest.entity;


import jakarta.persistence.*;
import lombok.CustomLog;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "replyer_table")
@Entity
public class Replyer {
    @Id
    @Column(name="replyer_id")
    private int replyerId;

    @Column(name="replyer_name")
    private String replyerName;
}
