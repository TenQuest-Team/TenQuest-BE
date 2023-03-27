package com.kns.tenquest.util;

import com.kns.tenquest.entity.Replyer;
import com.kns.tenquest.repository.ReplyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.UUID;
@Service
public class PrimaryKeyGenerator {
    @Autowired
    ReplyerRepository replyerRepository;

    public String UUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public int replyerId(){
        Replyer replyer = replyerRepository.findAll(Sort.by(Sort.Direction.DESC,"replyerId")).get(0);
        if (replyer != null){
            System.out.println("GENERATED ID: " + (replyer.getReplyerId()+1));
            return replyer.getReplyerId()+1;
        }
        System.out.println("GENERATED ID: 0");

        return 0;
    }

    public LocalDateTime localDateTime(){
        return LocalDateTime.now();
    }
}
