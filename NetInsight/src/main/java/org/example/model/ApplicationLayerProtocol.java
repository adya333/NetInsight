package org.example.model;

import java.util.HashMap;
import java.util.Map;

public enum ApplicationLayerProtocol {

    HTTP(80),
    HTTPS(443),
    DNS(53),
    FTP(20),
    SMTP(25),
    IMAP(143),
    SSH(22),
    DHCP(67),
    UNKNOWN(-1);

    private final int port;



    private static final Map<Integer, ApplicationLayerProtocol> PORT_MAP = new HashMap<>();

    static {
        for (ApplicationLayerProtocol protocol : values()) {
            PORT_MAP.put(protocol.port, protocol);
        }
    }

    ApplicationLayerProtocol(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public static ApplicationLayerProtocol fromPort(int port) {
        return PORT_MAP.getOrDefault(port, UNKNOWN);
    }
}
