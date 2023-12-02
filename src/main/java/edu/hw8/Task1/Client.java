package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Client {
    private static final Logger LOGGER = LogManager.getLogger();

    Socket socket;

    public Client(InetAddress addr, int port) {
        try {
            socket = new Socket(addr, port);
        } catch (IOException e) {
            LOGGER.error("Error while creating socket: " + e);
            throw new RuntimeException(e);
        }
    }

    public void sendLine(final String text) {
        try {
            final PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.write(text + '\n');
            writer.flush();
        } catch (IOException e) {
            LOGGER.error("Error while writing into socket: " + e);
            throw new RuntimeException(e);
        }
    }

    public String readLine() {
        try {
            return new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
        } catch (IOException e) {
            LOGGER.error("Error while reading from socket: " + e);
            throw new RuntimeException(e);
        }
    }
}
