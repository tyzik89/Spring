package com.work.vladimirs;

import org.springframework.stereotype.Service;

@Service
public class TargetServiceImpl implements TargetService {

    public String getTarget() {
        return "World";
    }
}
