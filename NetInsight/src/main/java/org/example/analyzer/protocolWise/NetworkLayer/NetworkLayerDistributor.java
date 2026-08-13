package org.example.analyzer.protocolWise.NetworkLayer;

import org.example.analyzer.protocolWise.ProtocolWiseAnalysisData;
import org.example.model.NetworkLayerProtocol;
import org.example.model.PacketInfo;

import java.util.HashMap;
import java.util.Map;

public class NetworkLayerDistributor {
    Map<NetworkLayerProtocol, ProtocolWiseAnalysisData> networkLayerProtocolProtocolDataMap;

    public NetworkLayerDistributor()
    {
        networkLayerProtocolProtocolDataMap = new HashMap<>();
    }

    public void updateNetworkLayerDistributor(PacketInfo packetInfo)
    {
        NetworkLayerProtocol networkLayerProtocol = packetInfo.getNetworkLayerProtocol();
        ProtocolWiseAnalysisData protocolWiseAnalysisData = networkLayerProtocolProtocolDataMap.computeIfAbsent(
                networkLayerProtocol,key->new ProtocolWiseAnalysisData());
        protocolWiseAnalysisData.setPacketcount(protocolWiseAnalysisData.getPacketcount()+1);
        protocolWiseAnalysisData.setBytes(protocolWiseAnalysisData.getBytes()+(long)packetInfo.getNetworkLayerSize());
    }

    public Map<NetworkLayerProtocol, ProtocolWiseAnalysisData> getNetworkLayerProtocolProtocolDataMap() {
        return networkLayerProtocolProtocolDataMap;
    }
}
