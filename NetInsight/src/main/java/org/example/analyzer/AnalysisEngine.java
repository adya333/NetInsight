package org.example.analyzer;

import org.example.analyzer.protocolWise.ProtocolWiseDistributor;
import org.example.analyzer.traffic.TrafficAnalyzer;
import org.example.analyzer.traffic.TrafficStatistics;
import org.example.model.PacketInfo;

public class AnalysisEngine {

    TrafficAnalyzer trafficAnalyzer;
    ProtocolWiseDistributor protocolWiseDistributor;


    public AnalysisEngine(TrafficAnalyzer trafficAnalyzer)
    {

        this.trafficAnalyzer = trafficAnalyzer;
        protocolWiseDistributor = new ProtocolWiseDistributor();
    }

    public void analysis(PacketInfo packetInfo)
    {

        trafficAnalyzer.processStatistics(packetInfo);


        protocolWiseDistributor.update(packetInfo);

    }
    public void startTime()
    {
        trafficAnalyzer.startCapture();
    }

    public void endTime()
    {
        trafficAnalyzer.stopCapture();
    }

    public void output()
    {

        System.out.println("*******Traffic Analyser*********");
        System.out.println(trafficAnalyzer.printAnalysis());

        System.out.println("*********Protocol Wise Distributor*********");
        protocolWiseDistributor.printSnapshot();

    }
}
