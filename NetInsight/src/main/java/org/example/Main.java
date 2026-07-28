package org.example;

import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        List<PcapNetworkInterface> interfaces = Pcaps.findAllDevs();

        for (PcapNetworkInterface nif : interfaces) {
            System.out.println("--------------------------------");
            System.out.println("Name: " + nif.getName());
            System.out.println("Description: " + nif.getDescription());
        }
    }
}