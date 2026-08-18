package org.example.analyzer.communicationWise;

import org.example.model.TransportLayerProtocol;

import java.time.Instant;

public class Communication {

    private Endpoint endpointA;
    private Endpoint endpointB;

    private long packetCount;
    private long totalBytes;

    private TransportLayerProtocol transportLayerProtocol;
    private Instant startTime;
    private Instant endTime;

}