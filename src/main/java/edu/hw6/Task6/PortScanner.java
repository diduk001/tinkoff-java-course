package edu.hw6.Task6;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PortScanner {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int MIN_PORT = 1;
    private static final int MAX_PORT = 49151;
    private static final String PORT_NOT_IN_RANGE_MESSAGE =
        String.format("Port must be between %d and %d", MIN_PORT, MAX_PORT);
    private static final Map<Integer, String> PORT_TO_SERVICE = Map.of(
        80, "HTTP",
        21, "FTP",
        25, "SMTP",
        22, "SSH",
        443, "HTTPS",
        53, "DNS",
        3306, "MySQL Database",
        5432, "PostgreSQL Database",
        3389, "Remote Desktop Protocol (RDP)",
        27017, "MongoDB Database"
    );

    private PortScanner() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) {
        final InetAddress scanMeAddress;
        try {
            scanMeAddress = InetAddress.getByName("scanme.nmap.org");
        } catch (UnknownHostException e) {
            LOGGER.error("Error while resolving host");
            return;
        }

        final List<PortResult> openPorts = getOpenPortsFromList(
            scanMeAddress,
            new ArrayList<>(PORT_TO_SERVICE.keySet())
        );

        LOGGER.info("---- OPENED PORTS ----");
        LOGGER.info("Port\tProtocol\tService");
        for (PortResult result : openPorts) {
            LOGGER.info(String.format("%d\t\t%s\t\t%s", result.port(), result.protocol(), result.service()));
        }
    }

    public static List<PortResult> getOpenPortsFromList(InetAddress address, final List<Integer> ports) {
        return ports
            .stream()
            .mapToInt(Integer::intValue)
            .mapToObj(port -> checkOnePort(address, port))
            .filter(PortResult::isOpen)
            .toList();
    }

    public static List<PortResult> getOpenPortsFromRange(InetAddress address, int portFrom, int portTo) {
        if (portFrom > portTo) {
            throw new IllegalArgumentException("portFrom must be less or equal than portTo");
        } else if (portFrom < MIN_PORT || MAX_PORT < portTo) {
            throw new IllegalArgumentException(PORT_NOT_IN_RANGE_MESSAGE);

        }
        return IntStream
            .range(portFrom, portTo + 1)
            .mapToObj(port -> checkOnePort(address, port))
            .filter(PortResult::isOpen)
            .toList();
    }

    public static PortResult checkOnePort(InetAddress address, int port) {
        if (port < MIN_PORT || MAX_PORT < port) {
            throw new IllegalArgumentException(PORT_NOT_IN_RANGE_MESSAGE);
        }
        LOGGER.info("Checking port " + port);

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(address, port));
            return new PortResult(PortResult.Protocol.TCP, port, PORT_TO_SERVICE.getOrDefault(port, ""));
        } catch (Exception ignored) {
            return new PortResult(PortResult.Protocol.CLOSED, port, "");
        }
    }
}
