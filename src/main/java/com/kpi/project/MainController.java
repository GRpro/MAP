package com.kpi.project;

import com.kpi.project.model.Graph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private ClientObserver observer;
    private Thread thread;
    private final Lock lock = new ReentrantLock(true);

    private Graph graph;

    @MessageMapping("/process/graph")
    public void updateGraph(Graph graph) {
        logger.debug("Update graph: " + graph);
        this.graph = graph;
    }

    /* In current implementation only one
     process is shared among all UI clients */

    @MessageMapping("/process/start")
    public void startTask() throws Exception {
        logger.debug("Start process");
        try {
            lock.lock();
            if (thread == null || !thread.isAlive()) {
                observer.interrupted.set(false);
                thread = new Thread(observer);
                thread.start();
            }
        } finally {
            lock.unlock();
        }
    }

    @MessageMapping("/process/stop")
    public void stopTask() {
        logger.debug("Stop process");
        try {
            lock.lock();
            if (thread != null && thread.isAlive()) {
                observer.interrupted.set(true);
                thread = null;
            }
        } finally {
            lock.unlock();
        }
    }

}
