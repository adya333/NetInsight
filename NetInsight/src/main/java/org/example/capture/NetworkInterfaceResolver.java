package org.example.capture;

import org.pcap4j.core.PcapAddress;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NetworkInterfaceResolver {


    public PcapNetworkInterface getDefaultInterface()
            throws Exception {

        List<PcapNetworkInterface> interfaces =
                Pcaps.findAllDevs();

        return interfaces.get(3);
    }


    public Set<String> getLocalIps(
            PcapNetworkInterface networkInterface) {


        Set<String> localIps = new HashSet<>();

        for(PcapAddress address : networkInterface.getAddresses()) {

            if(address.getAddress() != null) {

                localIps.add(
                        address.getAddress().getHostAddress()
                );
            }
        }

        return localIps;
    }
}