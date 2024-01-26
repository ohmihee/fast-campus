package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Step2_CustomWebApplicationServer {
    private final int port;

    private static final Logger logger = LoggerFactory.getLogger(Step2_CustomWebApplicationServer.class);

    public Step2_CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("[Step2_CustomWebApplicationServer] started {} port", port);

            Socket clientSocket;
            logger.info("[Step2_CustomWebApplicationServer] waiting for client.");

            while((clientSocket = serverSocket.accept()) != null) {
                logger.info("[Step2_CustomWebApplicationServer] client connected");

                /**
                 * Step2. 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리하도록 한다.
                 * 문제
                 *  : 스레드는 새로 생성될 때마다 독립적인 스택 메모리 공간을 할당받는데 이와 같이 메모리를 할당 받는 작업은 상당히
                 *  부담이 되는 작업이다. (즉 사용자의 요청마다 새로운 스레드를 생성할 경우 서버의 성능을 매우 떨어뜨릴 수 있다.)
                 *  스레드가 많아지게 되면 CPU 컨텍스트 스위칭 횟수도 증가하고, CPU 또는 CPU와 메모리 사용량도 매우 증가하게 된다.
                 *  최악의 경우에는 서버의 리소스가 이를 감당하지 못해서 서버가 다운될 가능성도 있다.
                 *
                 *  대안 -> 사용자의 요청마다 새로운 스레드를 생성하는 것이 아닌, 스레드를 미리 고정된 개수만큼 생성해두고, 해당 스레드들을
                 *  재활용하는 스레드 풀 개념을 적용해서 안정적인 서비스 제공
                 *
                 *  즉 정리해서 이야기하면 Thread는 생성될 때마다 독립적인 스택 메모리 공간을 할당받는데, (메모리 할당을 받는 작업은 상당히 비용이 비싼 작업)
                 *  사용자 요청이 있을 때마다 Thread를 생성하는 것은 성능의 저하를 불러오고,
                 *  동시 접속자 수가 많아질 경우 많은 Thread가 생성되어, CPU 컨텍스트 스위칭 횟수와 CPU 메모리 사용량이 증가하게 된다.
                 *  최악의 경우 서버의 리소스 한계로 인해 서버가 다운될 가능성도 있다. 이에 대한 대안으로 ThreadPool을 통한 안정적인 서비스 제공이 필요하다.
                 * */
                new Thread(new ClientRequestHandler(clientSocket)).start();
            }

        }
    }
}
