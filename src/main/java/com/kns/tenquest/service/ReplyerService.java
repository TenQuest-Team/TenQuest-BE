package com.kns.tenquest.service;
import com.kns.tenquest.dto.MemberDto;
import com.kns.tenquest.dto.ReplyerDto;
import com.kns.tenquest.dto.ServiceResult;
import com.kns.tenquest.entity.Member;
import com.kns.tenquest.repository.ReplyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.kns.tenquest.entity.Replyer;
import java.util.*;
@Service
@RequiredArgsConstructor
public class ReplyerService {

    private final ReplyerRepository replyerRepository;

    public ServiceResult getAllReplyers(){
        List<Replyer> replyerList = replyerRepository.findAll();

        return new ServiceResult().success()
                .data(replyerList);
    }

    public ServiceResult getReplyerByReplyerId(int replyerId){
       Optional<Replyer> optReplyer = replyerRepository.findReplyerByReplyerId(replyerId);
       if (optReplyer.isEmpty()) new ServiceResult().fail().message("No such replyer for replyerId: "+replyerId);

       return new ServiceResult().success().data(optReplyer.get());

    }

}
