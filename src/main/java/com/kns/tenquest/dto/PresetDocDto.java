package com.kns.tenquest.dto;

import com.kns.tenquest.entity.Preset;
import com.kns.tenquest.entity.PresetDoc;
import com.kns.tenquest.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class PresetDocDto implements DataTransferObject<PresetDoc> {
    public Long presetDocId;
    public Preset preset;
    public Question question;
    public Long questionOrder;
    public PresetDocDto(Long presetDocId, Preset preset, Question question, Long questionOrder){

        this.presetDocId = presetDocId;
        this.preset = preset;
        this.question = question;
        this.questionOrder = questionOrder;
    }

    public PresetDocDto(PresetDoc presetDoc){
        this.presetDocId = presetDoc.getPresetDocId();
        this.preset = presetDoc.getPreset();
        this.question = presetDoc.getQuestion();
        this.questionOrder = presetDoc.getQuestionOrder();
    }

    @Override
    public PresetDoc toEntity() {
        return PresetDoc.builder().presetDocId(this.presetDocId).preset(this.preset).questionOrder(this.questionOrder).question(this.question).build();
    }

    @Override
    public DataTransferObject<PresetDoc> toDto(PresetDoc presetDoc) {
        return new PresetDocDto(presetDoc.getPresetDocId(),presetDoc.getPreset(),presetDoc.getQuestion(),presetDoc.getQuestionOrder());
    }
}
