package org.example.analyzer.traffic;

import org.example.model.PacketInfo;

public class TrafficAnalyzer {

    private final TrafficStatistics trafficStatistics;

    public TrafficAnalyzer() {
        this.trafficStatistics = new TrafficStatistics();
    }

    public void processStatistics(PacketInfo packetInfo) {

        updatePacketCounters(packetInfo);
        updateDirectionCounters(packetInfo);
    }

    private void updatePacketCounters(PacketInfo packetInfo) {

        trafficStatistics.incrementPacketCount();
        trafficStatistics.addBytes(packetInfo.getSize());
    }

    private void updateDirectionCounters(PacketInfo packetInfo) {

        switch (packetInfo.getPacketDirection()) {

            case UPLOAD ->
                    trafficStatistics.addUploadBytes(packetInfo.getSize());

            case DOWNLOAD ->
                    trafficStatistics.addDownloadBytes(packetInfo.getSize());
            case UNKNOWN ->
                    trafficStatistics.addUnknownBytes(packetInfo.getSize());
        }
    }

    public String printAnalysis() {
        return trafficStatistics.toString();
    }

}