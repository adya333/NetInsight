package org.example.parser;

import org.example.model.ApplicationLayerProtocol;
import org.example.model.PacketInfo;
import org.pcap4j.packet.Packet;

public class ApplicationLayerParser {

    public void parseApplicationLayer(Packet packet, PacketInfo packetInfo)
    {
        int destPort = packetInfo.getDestinationPort();
        ApplicationLayerProtocol protocol =
                ApplicationLayerProtocol.fromPort(destPort);
        packetInfo.setApplicationProtocol(protocol);
    }
}
