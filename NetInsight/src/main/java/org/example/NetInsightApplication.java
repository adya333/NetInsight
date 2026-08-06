package org.example;

import org.example.analyzer.AnalysisEngine;
import org.example.analyzer.traffic.TrafficAnalyzer;
import org.example.capture.PacketCapture;
import org.example.parser.PacketParser;

import java.util.HashSet;

public class NetInsightApplication {

    public static void main(String[] args) throws Exception {


        PacketParser packetParser =
                new PacketParser();


        AnalysisEngine analysisEngine =
                new AnalysisEngine(new TrafficAnalyzer());


        PacketCapture packetCapture =
                new PacketCapture(
                        packetParser,
                        analysisEngine
                );


        packetCapture.start();
    }
}