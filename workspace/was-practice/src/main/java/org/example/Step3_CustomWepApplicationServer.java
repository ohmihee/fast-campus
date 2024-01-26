package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Step3_CustomWepApplicationServer {
    private final int port;

    /**
     * ThreadPool을 구현하는 방식은 아래와 같은 방식 이외에도 다른 여러 방식이 존재하며,
     * 중요한 것은 ThreadPool을 통해 안정적인 서비스 제공을 가능하게 할 수 있다는 점이다.
     * */
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static final Logger logger = LoggerFactory.getLogger(Step3_CustomWepApplicationServer.class);

    public Step3_CustomWepApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer] started {} port.", port);

            Socket clientSocket;

            logger.info("[CustomWebApplicationServer] client connected");

            while((clientSocket = serverSocket.accept()) != null) {
                logger.info("[CustomeWebApplicationServer] client connected");
                /**
                 * Step3 - Thread Pool을 적용해서 안정적인 서비스가 가능하도록 한다.
                 * 해당 ThreadPool을 이용해서 execute를 하고, 해당 Thread를 전달하여 준다.
                 * */
                executorService.execute(new ClientRequestHandler(clientSocket));
            }

        }
    }

}
