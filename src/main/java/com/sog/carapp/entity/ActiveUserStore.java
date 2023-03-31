package com.sog.carapp.entity;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@Data
public class ActiveUserStore {

    public List<String> users;

    public ActiveUserStore() {
        users = new ArrayList<String>();
    }

    @Bean
    public ActiveUserStore activeUserStore(){
        return new ActiveUserStore();
    }
}
