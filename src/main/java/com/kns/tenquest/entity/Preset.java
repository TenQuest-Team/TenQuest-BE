package com.kns.tenquest.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="preset_table")
@Getter
@Data
@NoArgsConstructor
public class Preset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preset_id")
    private Long presetId;

    @Column(name = "preset_name")
    private String presetName;


    @Builder
    public Preset(Long presetId, String presetName){
        this.presetId = presetId;
        this.presetName = presetName;
    }
}
