package com.simoncat.framework.packet.sniffer;

import java.net.Inet4Address;
import java.net.InetAddress;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;

public class NetworkPacketSniffer {

    private static final String IP = "172.26.143.116";

    private static final String COUNT_KEY
    = NetworkPacketSniffer.class.getName() + ".count";
  private static final int COUNT
    = Integer.getInteger(COUNT_KEY, 5);

  private static final String READ_TIMEOUT_KEY
    = NetworkPacketSniffer.class.getName() + ".readTimeout";
  private static final int READ_TIMEOUT
    = Integer.getInteger(READ_TIMEOUT_KEY, 10); // [ms]

  private static final String SNAPLEN_KEY
    = NetworkPacketSniffer.class.getName() + ".snaplen";
  private static final int SNAPLEN
    = Integer.getInteger(SNAPLEN_KEY, 65536); // [bytes]
    
    public static void main(String[] args) throws Exception {
        // list all interfaces....
//        MacAddressHelper.getInstance().getLocalInterfaces().forEach(l -> System.out.println("Found interface " + l));
//        MacAddress address = MacAddressHelper.getInstance().getMacAddress(Inet4Address.getByName(IP));
//        System.out.println(String.format("ip=%s, mac=%s", IP, address));
//        MacAddressHelper.getInstance().shutdown();
        
        System.out.println(COUNT_KEY + ": " + COUNT);
        System.out.println(READ_TIMEOUT_KEY + ": " + READ_TIMEOUT);
        System.out.println(SNAPLEN_KEY + ": " + SNAPLEN);
        System.out.println("\n");
        
        // Find Network Interface
        InetAddress addr = InetAddress.getByName(IP);
        PcapNetworkInterface nif = Pcaps.getDevByAddress(addr);

        // Open Pcap Handle
        int snapLen = 65536;
        PromiscuousMode mode = PromiscuousMode.PROMISCUOUS;
        int timeout = 10;
        PcapHandle handle = nif.openLive(snapLen, mode, timeout);

        // Capture Packet
        Packet packet = handle.getNextPacketEx();
        handle.close();

        // Get Packet Information
        IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
        Inet4Address srcAddr = ipV4Packet.getHeader().getSrcAddr();
        System.out.println(srcAddr);
    }

}
