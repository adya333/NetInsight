package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PacketInfo {

    //Ethernet -> Physical
    private String sourceMac;
    private String destinationMac;
    private String linkLayerProtocol;

    //Network Layer - IPv4, IPv6
    private String sourceIp;
    private String destinationIp;
    private String networkLayerProtocol;


    //Transport Layer - TCP, UDP, ICMP, PPP
    private int sourcePort;
    private int destinationPort;
    private String transportProtocol;
    private String applicationProtocol; // Will be infered from destination port

    private int payloadSize;
}
