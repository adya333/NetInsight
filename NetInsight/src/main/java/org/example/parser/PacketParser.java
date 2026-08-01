package org.example.parser;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.model.LinkLayerProtocol;
import org.example.model.PacketInfo;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.Packet;


public class PacketParser {


    LinkLayerParser linkLayerParser;
    NetworkLayerParser networkLayerParser;
    TransportLayerParser transportLayerParser;
    ApplicationLayerParser applicationLayerParser;

    public PacketParser() {
        this.linkLayerParser = new LinkLayerParser();
        this.networkLayerParser = new NetworkLayerParser();
        this.transportLayerParser = new TransportLayerParser();
        this.applicationLayerParser = new ApplicationLayerParser();
    }

    public PacketInfo parse(Packet packet)
    {
        PacketInfo info = new PacketInfo();

        info.setSize(packet.length());
        linkLayerParser.parseLinkLayer(packet, info);
        networkLayerParser.parseNetworkLayer(packet, info);
        transportLayerParser.parseTransportLayer(packet, info);
        applicationLayerParser.parseApplicationLayer(packet, info);

        return info;

    }


}
