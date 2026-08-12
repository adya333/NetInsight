package org.example.analyzer.protocolWise.TransportLayer;

import org.example.analyzer.protocolWise.ProtocolWiseAnalysisData;
import org.example.model.PacketInfo;
import org.example.model.TransportLayerProtocol;

import java.util.HashMap;
import java.util.Map;

public class TransportLayerDistributor {
    Map<TransportLayerProtocol, ProtocolWiseAnalysisData> transportLayerProtocolProtocolDataMap;

    public TransportLayerDistributor()
    {
        transportLayerProtocolProtocolDataMap = new HashMap<>();
    }
    public void updateTransportLayerDistributor(PacketInfo packetInfo)
    {
        TransportLayerProtocol transportLayerProtocol = packetInfo.getTransportProtocol();
        ProtocolWiseAnalysisData protocolWiseAnalysisData = transportLayerProtocolProtocolDataMap.computeIfAbsent(
                transportLayerProtocol, key -> new ProtocolWiseAnalysisData()
        );

        protocolWiseAnalysisData.setPacketcount(protocolWiseAnalysisData.getPacketcount()+1);
        protocolWiseAnalysisData.setBytes(protocolWiseAnalysisData.getBytes()+(long) packetInfo.getTransportLayerSize());
    }
}
