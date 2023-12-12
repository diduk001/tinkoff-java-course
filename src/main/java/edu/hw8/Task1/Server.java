package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Server {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final int MAX_CONNECTIONS = 10;
    final ExecutorService threadPool = Executors.newFixedThreadPool(MAX_CONNECTIONS);
    boolean isStopped = false;

    public Server(int port) {
        LOGGER.info("Starting server on port " + port);
        while (!isStopped) {
            Socket clientSocket;
            try (ServerSocket server = new ServerSocket(port)) {
                clientSocket = server.accept();
                LOGGER.info("New connection from " + clientSocket.getRemoteSocketAddress().toString());
            } catch (IOException e) {
                LOGGER.error("Error while adding thread in pool: " + e);
                throw new RuntimeException(e);
            }
            threadPool.execute(getThreadRunnable(clientSocket));

        }
    }

    private Runnable getThreadRunnable(Socket socket) {
        if (isStopped) {
            return () -> {
            };
        }

        return () -> {
            try {
                final var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                final var out = new PrintWriter(socket.getOutputStream());

                String request = in.readLine();
                while (request != null && !request.isEmpty()) {
                    LOGGER.info("[ " + socket.getRemoteSocketAddress().toString() + " ] : " + request);
                    final String response = switch (request) {
                        case "личности" -> "Не переходи на личности там, где их нет";
                        case "оскорбления" -> "Если твои противники перешли на личные оскорбления, "
                            + "будь уверена — твоя победа не за горами";
                        case "глупый" -> "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... "
                            + "Ты просто бог идиотизма.";
                        case "интеллект" -> "Чем ниже интеллект, тем громче оскорбления";
                        default -> "Я даже не смог понять, что ты только что сказал.";
                    };

                    out.write(response + '\n');
                    out.flush();
                    request = in.readLine();
                }

                in.close();
                out.close();
                LOGGER.info("Connection closed on " + socket.getRemoteSocketAddress().toString());

            } catch (IOException e) {
                LOGGER.error("Error while handling request on socket "
                    + socket.getRemoteSocketAddress().toString() + ": " + e);
                throw new RuntimeException(e);
            }
        };
    }

    public void close() {
        isStopped = true;
    }
}
