package com.kns.tenquest.service;
import com.kns.tenquest.dto.MemberDto;
import com.kns.tenquest.dto.ReplyerDto;
import com.kns.tenquest.entity.Member;
import com.kns.tenquest.repository.ReplyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kns.tenquest.entity.Replyer;
import java.util.*;
@Service
public class ReplyerService {

    @Autowired
    ReplyerRepository replyerRepository;
    public List<Replyer> getAllReplyers(){
        return replyerRepository.findAll();
    }

    public ReplyerDto getReplyerByReplyerId(int replyerId){
       Optional<Replyer> opt = replyerRepository.findReplyerByReplyerId(replyerId);
       return new ReplyerDto(-1,null);
    }

}
