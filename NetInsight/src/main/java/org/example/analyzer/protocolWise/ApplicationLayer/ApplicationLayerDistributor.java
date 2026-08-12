package org.example.analyzer.protocolWise.ApplicationLayer;

import org.example.analyzer.protocolWise.ProtocolWiseAnalysisData;
import org.example.model.ApplicationLayerProtocol;
import org.example.model.PacketInfo;

import java.util.HashMap;
import java.util.Map;

public class ApplicationLayerDistributor {

    Map<ApplicationLayerProtocol, ProtocolWiseAnalysisData> applicationLayerProtocolProtocolDataMap;

    public ApplicationLayerDistributor()
    {
        applicationLayerProtocolProtocolDataMap = new HashMap<>();
    }

    public void updateApplicationLayerDistributor(PacketInfo packetInfo)
    {
        ApplicationLayerProtocol applicationLayerProtocol = packetInfo.getApplicationProtocol();
        ProtocolWiseAnalysisData protocolWiseAnalysisData = applicationLayerProtocolProtocolDataMap.computeIfAbsent(
                applicationLayerProtocol,key -> new ProtocolWiseAnalysisData());
        protocolWiseAnalysisData.setPacketcount(protocolWiseAnalysisData.getPacketcount()+1);
        protocolWiseAnalysisData.setBytes(protocolWiseAnalysisData.getBytes()+(long)packetInfo.getPayloadSize());

    }
}
