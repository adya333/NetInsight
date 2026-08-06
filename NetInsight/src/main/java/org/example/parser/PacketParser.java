package org.example.parser;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.model.LinkLayerProtocol;
import org.example.model.PacketInfo;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.Packet;

import java.util.Set;


public class PacketParser {


    LinkLayerParser linkLayerParser;
    NetworkLayerParser networkLayerParser;
    TransportLayerParser transportLayerParser;
    ApplicationLayerParser applicationLayerParser;
    DirectionParser directionParser;




    public PacketParser() {
        this.linkLayerParser = new LinkLayerParser();
        this.networkLayerParser = new NetworkLayerParser();
        this.transportLayerParser = new TransportLayerParser();
        this.applicationLayerParser = new ApplicationLayerParser();
        this.directionParser = new DirectionParser();

    }

    public PacketInfo parse(Packet packet, Set<String> localIps)
    {
        PacketInfo info = new PacketInfo();

        info.setSize(packet.length());
        linkLayerParser.parseLinkLayer(packet, info);
        networkLayerParser.parseNetworkLayer(packet, info);
        transportLayerParser.parseTransportLayer(packet, info);
        applicationLayerParser.parseApplicationLayer(packet, info);
        directionParser.parse(info,localIps );

        return info;

    }


}
