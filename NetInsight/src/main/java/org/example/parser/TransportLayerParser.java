package org.example.parser;

import org.example.model.PacketInfo;
import org.example.model.TransportLayerProtocol;
import org.pcap4j.packet.*;

public class TransportLayerParser {

    public void parseTransportLayer(Packet packet, PacketInfo packetInfo)
    {
        TransportPacket transportPacket = null;
        if(packet.contains(TcpPacket.class) )
        {
            transportPacket = packet.get(TcpPacket.class);
        }
        if(packet.contains(UdpPacket.class) )
        {
            transportPacket = packet.get(UdpPacket.class);
        }
        if(packet.contains(IcmpV4CommonPacket.class))
        {
            parseIcmpv4Packet(packet, packetInfo);
        }

        if(transportPacket!=null)
        {
            packetInfo.setSourcePort(transportPacket.getHeader().getSrcPort().valueAsInt());
            packetInfo.setDestinationPort(transportPacket.getHeader().getDstPort().valueAsInt());
            packetInfo.setTransportLayerSize(transportPacket.getHeader().length());
//

            if(transportPacket.getPayload()!= null)
            packetInfo.setPayloadSize(transportPacket.getPayload().length());
            else
            packetInfo.setPayloadSize(0);
        }

    }

    public void parseIcmpv4Packet(Packet packet, PacketInfo info)
    {
        info.setTransportProtocol(TransportLayerProtocol.ICMPV4);
        info.setTransportLayerSize(packet.get(IcmpV4CommonPacket.class).getHeader().length());
    }
}
