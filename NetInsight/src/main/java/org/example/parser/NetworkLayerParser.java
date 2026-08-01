package org.example.parser;

import org.example.model.NetworkLayerProtocol;
import org.example.model.PacketInfo;
import org.example.model.TransportLayerProtocol;
import org.pcap4j.packet.*;

public class NetworkLayerParser {




    public void parseNetworkLayer(Packet packet, PacketInfo info)
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
                parseArpPacket(packet, info);
        }

        if(ipPacket!=null)
        {
            String srcIp = ipPacket.getHeader().getSrcAddr().getHostAddress();
            String destIp = ipPacket.getHeader().getDstAddr().getHostAddress();
            String ipVersion = ipPacket.getHeader().getVersion().name().toUpperCase();

            info.setSourceIp(srcIp);
            info.setDestinationIp(destIp);
            info.setNetworkLayerProtocol(NetworkLayerProtocol.valueOf(ipVersion));
            info.setNetworkLayerSize(ipPacket.getHeader().length());


            // This gives info about the next layer (Transport Layer) protocol type.
            String protocol = ipPacket.getHeader().getProtocol().name().toUpperCase();
            info.setTransportProtocol(TransportLayerProtocol.valueOf(protocol));
        }


    }

    public void parseArpPacket(Packet packet, PacketInfo packetInfo)
    {
        ArpPacket arpPacket = packet.get(ArpPacket.class);
        packetInfo.setNetworkLayerProtocol(NetworkLayerProtocol.ARP);
        packetInfo.setSourceIp(arpPacket.getHeader().getSrcProtocolAddr().getHostAddress());
        packetInfo.setDestinationIp(arpPacket.getHeader().getDstProtocolAddr().getHostAddress());
        packetInfo.setNetworkLayerSize(arpPacket.getHeader().length());
    }



}
