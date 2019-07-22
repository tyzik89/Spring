package com.work.vladimirs;

import javax.inject.Inject;

public class GreeterService {

    @Inject
    private TargetService targetService;

    public final String greet() {
        return "Hello " + targetService.getTarget() + "!";
    }
}
