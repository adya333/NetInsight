package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PacketInfo {

    private int size;

    //Ethernet -> Physical
    private String sourceMac;
    private String destinationMac;
    private LinkLayerProtocol linkLayerProtocol;
    private int physicalLayerSize;


    //Network Layer - IPv4, IPv6
    private String sourceIp;
    private String destinationIp;
    private NetworkLayerProtocol networkLayerProtocol;
    private int networkLayerSize;



    //Transport Layer - TCP, UDP, ICMP, PPP
    private int sourcePort;
    private int destinationPort;
    private TransportLayerProtocol transportProtocol;
    private int transportLayerSize;

    private ApplicationLayerProtocol applicationProtocol=ApplicationLayerProtocol.UNKNOWN; // Will be infered from destination port



    private int payloadSize;


    @Override
    public String toString() {
        return """
            ===================== Parsed Packet =====================

            Total Packet Size : %d bytes

            Link Layer
            ---------------------------------------------------------
            Protocol          : %s
            Source MAC        : %s
            Destination MAC   : %s
            Header Size       : %d bytes

            Network Layer
            ---------------------------------------------------------
            Protocol          : %s
            Source IP         : %s
            Destination IP    : %s
            Header Size       : %d bytes

            Transport Layer
            ---------------------------------------------------------
            Protocol          : %s
            Source Port       : %d
            Destination Port  : %d
            Header Size       : %d bytes

            Application Layer
            ---------------------------------------------------------
            Protocol          : %s

            Payload Size      : %d bytes

            =========================================================
            """
                .formatted(
                        size,

                        linkLayerProtocol,
                        sourceMac,
                        destinationMac,
                        physicalLayerSize,

                        networkLayerProtocol,
                        sourceIp,
                        destinationIp,
                        networkLayerSize,

                        transportProtocol,
                        sourcePort,
                        destinationPort,
                        transportLayerSize,

                        applicationProtocol.toString(),

                        payloadSize
                );
    }
}
