package com.kns.tenquest.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="preset_doc_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"question","preset"})
@Getter
@Data
public class PresetDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preset_doc_id;

    @ManyToOne
    @JoinColumn(name="preset_id")
    private Preset preset;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    private Long question_order;
}
