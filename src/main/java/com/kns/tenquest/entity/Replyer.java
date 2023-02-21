package com.kns.tenquest.entity;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Table(name = "replyer_table")
@Entity
@NoArgsConstructor
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
