package org.example.parser;

import org.example.model.PacketInfo;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.TransportPacket;
import org.pcap4j.packet.UdpPacket;

public class TransportLayerParser {

    public void ParseTransportLayer(Packet packet, PacketInfo packetInfo)
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

        packetInfo.setSourcePort(transportPacket.getHeader().getSrcPort().valueAsInt());
        packetInfo.setDestinationPort(transportPacket.getHeader().getDstPort().valueAsInt());


    }
}
