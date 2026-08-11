package org.example.analyzer.protocolWise.LinkLayer;

import org.example.analyzer.protocolWise.ProtocolData;
import org.example.model.LinkLayerProtocol;
import org.example.model.PacketInfo;

import java.util.HashMap;
import java.util.Map;

public class LinkLayerDistributor {

    Map<LinkLayerProtocol, ProtocolData> linkLayerProtocolProtocolDataMap;

    public LinkLayerDistributor()
    {
        linkLayerProtocolProtocolDataMap = new HashMap<>();
    }

    public void updateNetworkLayer(PacketInfo packetInfo)
    {
            LinkLayerProtocol linkLayerProtocol = packetInfo.getLinkLayerProtocol();
            ProtocolData protocolData = linkLayerProtocolProtocolDataMap.computeIfAbsent(linkLayerProtocol,
                    key->new ProtocolData());

            protocolData.setPacketcount(protocolData.getPacketcount()+1);
            protocolData.setBytes(protocolData.getBytes()+((long) packetInfo.getPhysicalLayerSize()));
    }
}
