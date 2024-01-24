package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * [ 계산기 프로그램 웹 어플리케이션으로 만들기 ]
 * Step 1. 사용자 요청을 메인 Thread가 처리하도록 한다.
 * Step 2. 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리하도록 한다.
 * Step 3. Thread Pool을 적용해 안정적인 서비스가 가능하도록 한다.
 *
 * */
public class Step1_CustomWebApplicationServer {
    private static final Logger logger = LoggerFactory.getLogger(Step1_CustomWebApplicationServer.class);
    private final int port;

    public Step1_CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        /**
         * 해당 port로 요청을 보냈을 때 server-socker의 accept 메서드가 기다리고 있다가 클라이언트의 요청을 받아 로그를 남긴다.
         * */
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer] started {} port.", port);

            Socket clientSocket;
            logger.info("[CustomerWebApplicationServer] waiting for client.");

            while ((clientSocket = serverSocket.accept()) != null) {
                logger.info("[CustomerWebApplicationServer] client connected!");

                /**
                 * Step1. 사용자 요청을 메인 Thread가 처리하도록 한다.
                 * 문제점
                 *  : 메인 쓰레드가 작업 수행 과정에서 블러킹에 걸리게 되는 경우, 다음 클라이언트의 요청이 들어와도 이전의 요청이 모두 완료될 때까지 무한히 기다려야만 한다.
                 *  -> 이를 극복하기 위해 요청이 들어오면서 별도의 쓰레드에서 처리하는 Step2. 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리하게 한다.
                 * */

                try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()){
                    /**
                     * inputstream을 한 줄씩 읽기 위해 InputStreamReader로 감싸고, 이를 다시 BufferedReader로 감싸줌
                     * */
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                    DataOutputStream dos = new DataOutputStream(out);

                    String line;
                   // while((line = br.readLine()) != "") {
                   //     System.out.println(line);
//                        GET / HTTP/1.1
//                        Host: localhost:8081
//                        Connection: Keep-Alive
//                        User-Agent: Apache-HttpClient/4.5.14 (Java/17.0.6)
//                        Accept-Encoding: br,deflate,gzip,x-gzip

                        // GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1

                        /**
                         * HttpRequest
                         *  - RequestLine (GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1)
                         *      - HttpMethod
                         *      - path
                         *      - queryString
                         *      - ...
                         *  - Header
                         *  - Body
                         *
                         *  HttpResponse
                         *   -
                         * */
                   // }

                    HttpRequest httpRequest = new HttpRequest(br);

                    // GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1

                    if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {
                        QueryStrings queryStrings = httpRequest.getQueryStrings();

                        int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                        String operator = queryStrings.getValue("operator");
                        int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

                        HttpResponse response = new HttpResponse(dos);
                        byte[] body = String.valueOf(result).getBytes();
                        response.response200Header("application/json", body.length);
                        response.responseBody(body);

                    }



                }
            }

        }

    }
}
