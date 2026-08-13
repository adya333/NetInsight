package org.example.analyzer.protocolWise;

import lombok.Getter;
import org.example.model.ApplicationLayerProtocol;
import org.example.model.LinkLayerProtocol;
import org.example.model.NetworkLayerProtocol;
import org.example.model.TransportLayerProtocol;

import java.util.Map;

@Getter
public class ProtocolWiseStatistics {

    Map<LinkLayerProtocol, ProtocolWiseAnalysisData> linkLayerProtocolProtocolDataMap;
    Map<NetworkLayerProtocol, ProtocolWiseAnalysisData> networkLayerProtocolProtocolDataMap;
    Map<TransportLayerProtocol, ProtocolWiseAnalysisData> transportLayerProtocolProtocolDataMap;
    Map<ApplicationLayerProtocol, ProtocolWiseAnalysisData> applicationLayerProtocolProtocolDataMap;

    public ProtocolWiseStatistics(
            Map<LinkLayerProtocol, ProtocolWiseAnalysisData> linkLayerProtocolProtocolDataMap,
    Map<NetworkLayerProtocol, ProtocolWiseAnalysisData> networkLayerProtocolProtocolDataMap,
    Map<TransportLayerProtocol, ProtocolWiseAnalysisData> transportLayerProtocolProtocolDataMap,
    Map<ApplicationLayerProtocol, ProtocolWiseAnalysisData> applicationLayerProtocolProtocolDataMap)
    {
        this.linkLayerProtocolProtocolDataMap = linkLayerProtocolProtocolDataMap;
        this.networkLayerProtocolProtocolDataMap = networkLayerProtocolProtocolDataMap;
        this.transportLayerProtocolProtocolDataMap = transportLayerProtocolProtocolDataMap;
        this.applicationLayerProtocolProtocolDataMap = applicationLayerProtocolProtocolDataMap;
    }
}
