package com.work.vladimirs;

import org.springframework.stereotype.Service;

@Service
public class BaseService {

    boolean isMany(int number) {
        return number > 5;
    }
}
