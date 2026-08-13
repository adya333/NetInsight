package org.example.analyzer.protocolWise.LinkLayer;

import org.example.analyzer.protocolWise.ProtocolWiseAnalysisData;
import org.example.model.LinkLayerProtocol;
import org.example.model.PacketInfo;

import java.util.HashMap;
import java.util.Map;

public class LinkLayerDistributor {

    Map<LinkLayerProtocol, ProtocolWiseAnalysisData> linkLayerProtocolProtocolDataMap;

    public LinkLayerDistributor()
    {
        linkLayerProtocolProtocolDataMap = new HashMap<>();
    }

    public void updateLinkLayerDistributor(PacketInfo packetInfo)
    {
            LinkLayerProtocol linkLayerProtocol = packetInfo.getLinkLayerProtocol();
            ProtocolWiseAnalysisData protocolWiseAnalysisData = linkLayerProtocolProtocolDataMap.computeIfAbsent(linkLayerProtocol,
                    key->new ProtocolWiseAnalysisData());

            protocolWiseAnalysisData.setPacketcount(protocolWiseAnalysisData.getPacketcount()+1);
            protocolWiseAnalysisData.setBytes(protocolWiseAnalysisData.getBytes()+((long) packetInfo.getPhysicalLayerSize()));
    }

    public Map<LinkLayerProtocol, ProtocolWiseAnalysisData> getLinkLayerProtocolProtocolDataMap() {
        return linkLayerProtocolProtocolDataMap;
    }
}
