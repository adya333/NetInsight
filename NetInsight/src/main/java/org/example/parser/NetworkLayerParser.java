package org.example.parser;

import org.example.model.PacketInfo;
import org.pcap4j.packet.*;

public class NetworkLayerParser {




    public String parseNetworkLayer(Packet packet, PacketInfo info)
    {

        IpPacket ipPacket = null;
        if(packet.contains(IpV4Packet.class))
        {
            ipPacket = packet.get(IpV4Packet.class);
        }
        if(packet.contains(IpV6Packet.class))
        {
            ipPacket = packet.get(IpV6Packet.class);
        }
        if(packet.contains(ArpPacket.class))
        {
           // Still needs to be implemented
        }

        String srcIp = ipPacket.getHeader().getSrcAddr().getHostAddress();
        String destIp = ipPacket.getHeader().getDstAddr().getHostAddress();
        String ipVersion = ipPacket.getHeader().getVersion().name();

        info.setSourceIp(srcIp);
        info.setDestinationIp(destIp);
        info.setNetworkLayerProtocol(ipVersion);


        // This gives info about the next layer (Transport Layer) protocol type.
        String protocol = ipPacket.getHeader().getProtocol().name();
        info.setTransportProtocol(protocol);

        return protocol;
    }




}
