package com.kpi.project;

import com.kpi.project.packing.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ClientObserver implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ClientObserver.class);

    public AtomicBoolean interrupted = new AtomicBoolean(false);

    int[][] matrix;

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void run() {
//        while (!interrupted.get()) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            Random r = new Random();
//            int[][] mat = new int[8][8];
//            for (int i = 0; i < mat.length; i++) {
//                for (int j = 0; j < mat[i].length; j++) {
//                    mat[i][j] = r.nextBoolean() ? 1 : 0;
//                }
//            }

            logger.debug("Notify client");
        Main.printMatrix(matrix);
            // sends the message to /topic/circuit
            template.convertAndSend("/topic/circuit", matrix);
//        }
    }
}