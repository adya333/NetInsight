package org.example.analyzer.traffic;

import lombok.Getter;

import java.time.Instant;

@Getter
public class TrafficStatistics {

    // Packet Counters
    private long totalPackets;
    private long totalBytes;

    // Direction-wise Counters
    private long uploadBytes;
    private long downloadBytes;
    private long unknownBytes;

    // Capture Lifecycle
    private Instant captureStartTime;
    private Instant captureEndTime;

    public void incrementPacketCount() {
        totalPackets++;
    }

    public void addBytes(long bytes) {
        totalBytes += bytes;
    }

    public void addUploadBytes(long bytes) {
        uploadBytes += bytes;
    }

    public void addUnknownBytes(long bytes)
    {
        unknownBytes += bytes;
    }

    public void addDownloadBytes(long bytes) {
        downloadBytes += bytes;
    }

    public void startCapture() {
        captureStartTime = Instant.now();
        captureEndTime = null;
    }

    public void stopCapture() {
        captureEndTime = Instant.now();
    }

    @Override
    public String toString() {
        return """
               Total Packets : %d
               Total Bytes   : %d
               
               =====Upload=====
               Bytes: %d
               
               =====Download=====
               Bytes: %d
               
               =====Unknown=====
               Bytes: %d
               """.formatted(totalPackets, totalBytes, uploadBytes, downloadBytes, unknownBytes);
    }
}