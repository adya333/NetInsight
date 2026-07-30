package org.example.parser;

import org.example.model.LinkLayerProtocol;
import org.example.model.PacketInfo;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.Packet;

public class LinkLayerParser {


    // Ethernet
    public String parseLinkLayer(Packet packet, PacketInfo info)
    {
        EthernetPacket ethernetPacket = packet.get(EthernetPacket.class);

        EthernetPacket.EthernetHeader header = ethernetPacket.getHeader();

        // This gives the protocol of the underlying payload.
        String protocol = header.getType().name();

        info.setSourceMac(header.getSrcAddr().toString());
        info.setDestinationMac(header.getDstAddr().toString());
        info.setLinkLayerProtocol(LinkLayerProtocol.ETHERNET);

        return protocol;

    }
}
