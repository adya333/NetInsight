package org.example.analyzer.protocolWise;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProtocolWiseAnalysisData {
     long packetcount;
     long bytes;

    @Override
    public String toString()
    {
        return "Packets: " + packetcount +
                ", Bytes: " + bytes;
    }
}
