package edu.hw6;

import edu.hw6.Task6.PortScanner;
import java.io.IOException;
import java.net.InetAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task6Test {
    private static final Logger LOGGER = LogManager.getLogger();

    @ParameterizedTest(name = "Тест открытого порта {0} на http://scanme.nmap.org/")
    @ValueSource(ints = {22, 80})
    void checkOpenPort(int port) {
        try {
            final InetAddress scanMeAddress = InetAddress.getByName("scanme.nmap.org");
            assertThat(PortScanner.checkOnePort(scanMeAddress, port).isOpen()).isTrue();
        } catch (IOException e) {
            LOGGER.info("Error while creating connection: " + e);
        }
    }

    @ParameterizedTest(name = "Тест закрытого порта {0} на http://scanme.nmap.org/")
    @ValueSource(ints = {11, 160})
    void checkClosedPort(int port) {
        try {
            final InetAddress scanMeAddress = InetAddress.getByName("scanme.nmap.org");
            assertThat(PortScanner.checkOnePort(scanMeAddress, port).isOpen()).isFalse();
        } catch (IOException e) {
            LOGGER.info("Error while creating connection: " + e);
        }
    }

    @ParameterizedTest(name = "Тест невалидного порта {0}")
    @ValueSource(ints = {-1, 0, 100000, 65539})
    void checkInvalidPort(int port) {
        assertThrows(
            IllegalArgumentException.class,
            () -> PortScanner.checkOnePort(InetAddress.getLoopbackAddress(), port)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> PortScanner.getOpenPortsFromRange(InetAddress.getLoopbackAddress(), port - 1000, port + 1000)
        );
    }
}
