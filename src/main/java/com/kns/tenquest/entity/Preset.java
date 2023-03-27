package com.kns.tenquest.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name="preset_table")
@Getter
@Data
public class Preset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preset_id")
    private Long presetId;

    @Column(name = "preset_name")
    private String presetName;

    public Preset(){} //기본 생성자가 없단다,,

    @Builder
    public Preset(Long presetId, String presetName){
        this.presetId = presetId;
        this.presetName = presetName;
    }
}
