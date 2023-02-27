package com.kns.tenquest.dto;

import com.kns.tenquest.entity.PresetDoc;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class PresetDocDto implements DataTransferObject<PresetDoc> {
    public Long presetDocId;
    public Long presetId;
    public String questionId;
    public Long questionOrder;
    public PresetDocDto(Long presetDocId, Long presetId, String questionId, Long questionOrder){

        this.presetDocId = presetDocId;
        this.presetId = presetId;
        this.questionId = questionId;
        this.questionOrder = questionOrder;
    }

    public PresetDocDto(PresetDoc presetDoc){
        this.presetDocId = presetDoc.getPresetDocId();
        this.presetId = presetDoc.getPresetId();
        this.questionId = presetDoc.getQuestionId();
        this.questionOrder = presetDoc.getQuestionOrder();
    }

    @Override
    public PresetDoc toEntity() {
        return PresetDoc.builder().presetDocId(this.presetDocId).presetId(this.presetId).questionOrder(this.questionOrder).questionId(this.questionId).build();
    }

    @Override
    public DataTransferObject<PresetDoc> toDto(PresetDoc presetDoc) {
        return new PresetDocDto(presetDoc.getPresetDocId(),presetDoc.getPresetId(),presetDoc.getQuestionId(),presetDoc.getQuestionOrder());
    }
}
