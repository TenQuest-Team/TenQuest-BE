package com.kns.tenquest.entity;


import jakarta.persistence.*;
import lombok.Builder;
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

    @Builder
    public Replyer(int replyerId, String replyerName) {
        this.replyerId = replyerId;
        this.replyerName = replyerName;
    }
}
