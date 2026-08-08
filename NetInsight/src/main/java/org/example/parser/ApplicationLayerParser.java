package org.example.parser;

import org.example.model.ApplicationLayerProtocol;
import org.example.model.PacketInfo;
import org.pcap4j.packet.Packet;

public class ApplicationLayerParser {

    public void parseApplicationLayer(Packet packet, PacketInfo packetInfo)
    {
        int destPort = packetInfo.getDestinationPort();
        int sourcePort = packetInfo.getSourcePort();
        ApplicationLayerProtocol destProtocol =
                ApplicationLayerProtocol.fromPort(destPort);

        ApplicationLayerProtocol srcProtocol =
                ApplicationLayerProtocol.fromPort(sourcePort);

        if(destProtocol != ApplicationLayerProtocol.UNKNOWN)
        packetInfo.setApplicationProtocol(destProtocol);
        else if(srcProtocol != ApplicationLayerProtocol.UNKNOWN)
        packetInfo.setApplicationProtocol(srcProtocol);
        else
        packetInfo.setApplicationProtocol(ApplicationLayerProtocol.UNKNOWN);

    }
}
