package org.example.analyzer.protocolWise;

import org.example.analyzer.protocolWise.ApplicationLayer.ApplicationLayerDistributor;
import org.example.analyzer.protocolWise.LinkLayer.LinkLayerDistributor;
import org.example.analyzer.protocolWise.NetworkLayer.NetworkLayerDistributor;
import org.example.analyzer.protocolWise.TransportLayer.TransportLayerDistributor;
import org.example.model.PacketInfo;

// this is the orchestrator for the layer wise protocol distribution..
// this class's function is called in the analysis engine to initiate the protocol wise segregation
public class ProtocolWiseDistributor {

    LinkLayerDistributor linkLayerDistributor;
    NetworkLayerDistributor networkLayerDistributor;
    TransportLayerDistributor transportLayerDistributor;
    ApplicationLayerDistributor applicationLayerDistributor;

    public ProtocolWiseDistributor()
    {
        linkLayerDistributor = new LinkLayerDistributor();
        networkLayerDistributor = new NetworkLayerDistributor();
        transportLayerDistributor = new TransportLayerDistributor();
        applicationLayerDistributor = new ApplicationLayerDistributor();
    }

    public void update(PacketInfo packetInfo)
    {
        linkLayerDistributor.updateLinkLayerDistributor(packetInfo);
        networkLayerDistributor.updateNetworkLayerDistributor(packetInfo);
        transportLayerDistributor.updateTransportLayerDistributor(packetInfo);
        applicationLayerDistributor.updateApplicationLayerDistributor(packetInfo);
    }

    public ProtocolWiseStatistics getSnapshot()
    {
        return new ProtocolWiseStatistics(
                linkLayerDistributor.getLinkLayerProtocolProtocolDataMap(),
                networkLayerDistributor.getNetworkLayerProtocolProtocolDataMap(),
                transportLayerDistributor.getTransportLayerProtocolProtocolDataMap(),
                applicationLayerDistributor.getApplicationLayerProtocolProtocolDataMap()
        );
    }

    public void printSnapshot()
    {
        ProtocolWiseStatistics statistics = getSnapshot();

        System.out.println("\n========== PROTOCOL WISE DISTRIBUTION ==========");

        System.out.println("\n--- LINK LAYER ---");
        statistics.getLinkLayerProtocolProtocolDataMap().forEach(
                (protocol, data) ->
                        System.out.println(protocol + " : " + data)
        );

        System.out.println("\n--- NETWORK LAYER ---");
        statistics.getNetworkLayerProtocolProtocolDataMap().forEach(
                (protocol, data) ->
                        System.out.println(protocol + " : " + data)
        );

        System.out.println("\n--- TRANSPORT LAYER ---");
        statistics.getTransportLayerProtocolProtocolDataMap().forEach(
                (protocol, data) ->
                        System.out.println(protocol + " : " + data)
        );

        System.out.println("\n--- APPLICATION LAYER ---");
        statistics.getApplicationLayerProtocolProtocolDataMap().forEach(
                (protocol, data) ->
                        System.out.println(protocol + " : " + data)
        );
    }
}
