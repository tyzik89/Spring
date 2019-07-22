package com.work.vladimirs;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.logging.Logger;

@Service
public class SecondConsumer {
    private final static Logger log = Logger.getLogger(SecondConsumer.class.getName());

    @Inject
    private StatefulBean singletonBean;

    @Inject
    private StatefulBean prototypeBean;

    public void processState() {
        log.info("singletonBean state is: " + singletonBean.getState());
        log.info("prototypeBean state is: " + prototypeBean.getState());

        singletonBean.setState("After SecondConsumer");
        prototypeBean.setState("After SecondConsumer");

        log.info("singletonBean state set to: " + singletonBean.getState());
        log.info("prototypeBean state set to: " + prototypeBean.getState());
    }
}
