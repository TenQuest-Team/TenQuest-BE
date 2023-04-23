package com.kns.tenquest.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="preset_doc_table")
//@ToString(exclude = {"question","preset"})
@Getter
@Data
@NoArgsConstructor
public class PresetDoc {
    @Id
    @Column(name = "preset_doc_id")
    private String presetDocId;

//    @ManyToOne
//    @JoinColumn(name="preset_id")
//    private Preset preset;
    @Column(name="preset_id")
    private String presetId;

//    @ManyToOne
//    @JoinColumn(name="question_id")
//    private Question question;

    @Column(name="question_id")
    private String questionId;

    @Column(name = "question_order")
    private Long questionOrder;

//    public PresetDoc(){}//기본 생성자가 없어서 안된단다,,

    @Builder
    public PresetDoc(String presetDocId, String presetId, String questionId, Long questionOrder){

        this.presetDocId = presetDocId;
        this.presetId = presetId;
        this.questionId = questionId;
        this.questionOrder = questionOrder;
    }
}
