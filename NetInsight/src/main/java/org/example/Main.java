package org.example;

import org.example.model.PacketInfo;
import org.example.parser.DirectionParser;
import org.example.parser.PacketParser;
import org.pcap4j.core.PcapAddress;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {

        List<PcapNetworkInterface> interfaces = Pcaps.findAllDevs();


//        for(PcapNetworkInterface nif:interfaces)
//        {
//          System.out.println(nif.getName());
//          System.out.println(nif.getDescription());
//          System.out.println("================");
//        }

        PcapNetworkInterface nif = interfaces.get(3);

        System.out.println(nif.getName());
        Set<String> localIps = new HashSet<>();

        for (PcapAddress address : nif.getAddresses()) {
            if (address.getAddress() != null) {
                localIps.add(address.getAddress().getHostAddress());
            }
        }
       // DirectionParser directionParser = new DirectionParser(localIps);
        PacketParser packetParser = new PacketParser(localIps);

        PcapHandle handle = nif.openLive(
                65536,
                PcapNetworkInterface.PromiscuousMode.PROMISCUOUS,
                10
        );



        try {
            while (true) {
                Packet packet = handle.getNextPacket();

                if (packet!=null) {
                    PacketInfo parsed = packetParser.parse(packet);
                    System.out.println("******************************************");
                    System.out.println(parsed);
                      System.out.println(packet);
                    System.out.println("******************************************");
                }

            }
        } finally {
            handle.close();
        }

    }
}