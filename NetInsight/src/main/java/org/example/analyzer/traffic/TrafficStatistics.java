package org.example.analyzer.traffic;

import lombok.Getter;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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


    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
                    .withZone(ZoneId.systemDefault());


    public void incrementPacketCount() {
        totalPackets++;
    }

    public void addBytes(long bytes) {
        totalBytes += bytes;
    }

    public void addUploadBytes(long bytes) {
        uploadBytes += bytes;
    }

    public void addUnknownBytes(long bytes) {
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


    private String formatTime(Instant time) {

        if (time == null) {
            return "Not Available";
        }

        return TIME_FORMATTER.format(time);
    }

    private double calculateMbps() {

        if (captureStartTime == null || captureEndTime == null) {
            return 0.0;
        }

        double durationSeconds =
                Duration.between(captureStartTime, captureEndTime).toMillis() / 1000.0;

        if (durationSeconds <= 0) {
            return 0.0;
        }

        // Bytes -> bits -> megabits
        return (totalBytes * 8.0) / durationSeconds / 1_000_000;
    }


    private double calculatePps() {

        if (captureStartTime == null || captureEndTime == null) {
            return 0.0;
        }

        double durationSeconds =
                Duration.between(captureStartTime, captureEndTime).toMillis() / 1000.0;

        if (durationSeconds <= 0) {
            return 0.0;
        }

        return totalPackets / durationSeconds;
    }

    private String formatDuration() {

        if (captureStartTime == null || captureEndTime == null) {
            return "Not Available";
        }

        Duration duration = Duration.between(
                captureStartTime,
                captureEndTime
        );

        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        return String.format(
                "%02dh %02dm %02ds",
                hours,
                minutes,
                seconds
        );
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
               
               Start Time : %s
               End Time   : %s
               Duration   : %s
               
               =====Performance=====
                           Average Speed : %.2f Mbps
                           Packet Rate   : %.2f PPS
               
               """.formatted(
                totalPackets,
                totalBytes,
                uploadBytes,
                downloadBytes,
                unknownBytes,
                formatTime(captureStartTime),
                formatTime(captureEndTime),
                formatDuration(),
                calculateMbps(),
                calculatePps()
        );
    }
}