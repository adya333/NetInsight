package org.example.parser;

import org.example.model.PacketDirection;
import org.example.model.PacketInfo;

import java.util.Set;

public class DirectionParser {

    private final Set<String> localIps;

    public DirectionParser(Set<String> localIps) {
        this.localIps = localIps;
    }

    public void parse(PacketInfo info) {

        String src = info.getSourceIp();
        String dst = info.getDestinationIp();

        if (src == null || dst == null) {
            info.setPacketDirection(PacketDirection.UNKNOWN);
            return;
        }

        boolean srcLocal = localIps.contains(src);
        boolean dstLocal = localIps.contains(dst);

        if (srcLocal && !dstLocal) {
            info.setPacketDirection(PacketDirection.UPLOAD);
        }
        else if (!srcLocal && dstLocal) {
            info.setPacketDirection(PacketDirection.DOWNLOAD);
        }
        else if (srcLocal && dstLocal) {
            info.setPacketDirection(PacketDirection.LOCAL);
        }
        else {
            info.setPacketDirection(PacketDirection.UNKNOWN);
        }
    }
}