package com.kns.tenquest.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="preset_doc_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@ToString(exclude = {"question","preset"})
@Getter
@Data
public class PresetDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preset_doc_id")
    private Long presetDocId;

//    @ManyToOne
//    @JoinColumn(name="preset_id")
//    private Preset preset;
    @Column(name="preset_id")
    private Long presetId;

//    @ManyToOne
//    @JoinColumn(name="question_id")
//    private Question question;

    @Column(name="question_id")
    private UUID questionId;

    @Column(name = "question_order")
    private Long questionOrder;
}
