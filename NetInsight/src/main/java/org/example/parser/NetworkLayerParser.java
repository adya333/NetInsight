package org.example.parser;

import org.example.model.PacketInfo;
import org.pcap4j.packet.IpPacket;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.IpV6Packet;
import org.pcap4j.packet.Packet;

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

        String srcIp = ipPacket.getHeader().getSrcAddr().getHostAddress();
        String destIp = ipPacket.getHeader().getDstAddr().getHostAddress();
        String ipVersion = ipPacket.getHeader().getVersion().name();

        info.setSourceIp(srcIp);
        info.setDestinationIp(destIp);
        info.setNetworkLayerProtocol(ipVersion);


        // This gives info about the next layer (Transport Layer) protocol type.
        String protocol = ipPacket.getHeader().getProtocol().name();

        return protocol;
    }




}
