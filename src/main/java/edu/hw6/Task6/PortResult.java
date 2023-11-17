package edu.hw6.Task6;

public record PortResult(Protocol protocol, Integer port, String service) {
    public boolean isOpen() {
        return protocol != Protocol.CLOSED;
    }

    enum Protocol {
        CLOSED, TCP, UDP
    }
}
