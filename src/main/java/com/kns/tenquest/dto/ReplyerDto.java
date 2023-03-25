package com.kns.tenquest.dto;

import com.kns.tenquest.entity.Replyer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReplyerDto implements DataTransferObject<Replyer> {
    public int replyerId;
    public String replyerName;

    @Builder
    public ReplyerDto(int replyerId, String replyerName) {
        this.replyerId = replyerId;
        this.replyerName = replyerName;
    }

    public ReplyerDto(Replyer replyer) {
        this.replyerId = replyer.getReplyerId();
        this.replyerName = replyer.getReplyerName();
    }

    @Override
    public Replyer toEntity() {
        Replyer replyer = Replyer.builder().replyerId(this.replyerId).replyerName(this.replyerName).build();
        return replyer;
    }

    @Override
    public DataTransferObject<Replyer> toDto(Replyer replyer) {
        return new ReplyerDto(replyer);
    }

}
