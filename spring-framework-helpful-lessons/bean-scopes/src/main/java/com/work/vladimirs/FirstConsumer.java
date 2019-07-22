package com.work.vladimirs;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.logging.Logger;

@Service
public class FirstConsumer {
    private final static Logger log = Logger.getLogger(FirstConsumer.class.getName());

    @Inject
    private StatefulBean singletonBean;

    @Inject
    private StatefulBean prototypeBean;

    public void processState() {
        log.info("singletonBean state is: " + singletonBean.getState());
        log.info("prototypeBean state is: " + prototypeBean.getState());

        singletonBean.setState("After FirstConsumer");
        prototypeBean.setState("After FirstConsumer");

        log.info("singletonBean state set to: " + singletonBean.getState());
        log.info("prototypeBean state set to: " + prototypeBean.getState());
    }
}
