package org.example.analyzer;

import org.example.analyzer.traffic.TrafficAnalyzer;
import org.example.analyzer.traffic.TrafficStatistics;
import org.example.model.PacketInfo;

public class AnalysisEngine {

    TrafficAnalyzer trafficAnalyzer;
    TrafficStatistics trafficStatistics;

    public AnalysisEngine(TrafficAnalyzer trafficAnalyzer)
    {
        this.trafficAnalyzer = trafficAnalyzer;
    }

    public void analysis(PacketInfo packetInfo)
    {

        trafficAnalyzer.processStatistics(packetInfo);
    }
    public void startTime()
    {
        trafficStatistics.startCapture();
    }

    public void endTime()
    {
        trafficStatistics.stopCapture();
    }

    public void output()
    {
        System.out.println(trafficAnalyzer.printAnalysis());
    }
}
