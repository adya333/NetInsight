package org.example.capture;

import org.example.analyzer.AnalysisEngine;
import org.example.model.PacketInfo;
import org.example.parser.PacketParser;
import org.pcap4j.core.PcapAddress;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PacketCapture {

    private final PacketParser packetParser;
    private final AnalysisEngine analysisEngine;
    private final NetworkInterfaceResolver networkInterfaceResolver;
    private  Set<String> localIps;


    public PacketCapture(
            PacketParser packetParser,
            AnalysisEngine analysisEngine) {

        this.packetParser = packetParser;
        this.analysisEngine = analysisEngine;
        this.networkInterfaceResolver = new NetworkInterfaceResolver();

    }


    public void start() throws Exception {

        PcapNetworkInterface networkInterface = selectInterface();
        localIps = networkInterfaceResolver.getLocalIps(networkInterface);

        try (PcapHandle handle = openHandle(networkInterface)) {

            analysisEngine.endTime();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                analysisEngine.endTime();
                System.out.println("\n===== THE VERDICT =====");

                analysisEngine.output();

                handle.close();

            }));


            while (true) {


                Packet packet = handle.getNextPacket();
                analysisEngine.startTime();
                if(packet == null) {
                    continue;
                }


                PacketInfo packetInfo = packetParser.parse(packet, localIps);
                System.out.println(packetInfo);
                analysisEngine.analysis(packetInfo);
            }
        }
    }


    private PcapNetworkInterface selectInterface() throws Exception {

        List<PcapNetworkInterface> interfaces =
                Pcaps.findAllDevs();

        return interfaces.get(3);
    }


    private PcapHandle openHandle(
            PcapNetworkInterface networkInterface)
            throws Exception {

        return networkInterface.openLive(
                65536,
                PcapNetworkInterface.PromiscuousMode.PROMISCUOUS,
                10
        );
    }
}