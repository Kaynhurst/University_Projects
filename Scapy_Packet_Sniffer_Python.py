from scapy.all import *
from scapy.layers.dot11 import Dot11, Dot11Beacon, Dot11Deauth
import time
import os
import subprocess

#Lambda Functions
beacon_packet = lambda pkt: pkt.haslayer(Dot11Beacon)
deauth_packet = lambda pkt: pkt.haslayer(Dot11Deauth) and pkt.addr2 and pkt.addr1 and pkt.addr2 != pkt.addr1
STA_MAC_packet = lambda pkt: pkt.haslayer(Dot11) and pkt.type in [0, 2] and pkt.addr2 and not pkt.addr2.startswith("ff:ff:ff:ff:ff:ff")

#Packets Storage
beacon_packets = []
deauth_packets = []
STA_MAC_packets = []

#Packet grabber
def packet_handler(pkt):
    if beacon_packet(pkt) and len(beacon_packets) < 5:
        beacon_packets.append(pkt)
        print(f"[Beacon] AP MAC: {pkt.addr2} (Captured {len(beacon_packets)}/5)")
    elif deauth_packet(pkt) and len(deauth_packets) < 5:
        deauth_packets.append(pkt)
        print(f"[Deauth] From AP: {pkt.addr2} → Client: {pkt.addr1} (Captured {len(deauth_packets)}/5)")
    elif STA_MAC_packet(pkt) and len(STA_MAC_packets) < 5:
        STA_MAC_packets.append(pkt)
        print(f"[STA MAC] Detected MAC: {pkt.addr2} (Captured {len(STA_MAC_packets)}/5)")



def check_wlan0mon(interface="wlan0mon"):
    result = subprocess.run(["ip", "link", "show", interface], stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    return result.returncode == 0

def capture_packets():

            #Capture packets
            print("Sniffing for packets...")
            start_time = time.time()
            
            while True:
                sniff(prn=packet_handler, iface="wlan0mon", store=0, timeout=1)

                
                if len(beacon_packets) >= 5 and len(deauth_packets) >= 5 and len(STA_MAC_packets) >= 5:
                    break

                #Timeout for deauth packets
                if time.time() - start_time > 5 and len(deauth_packets) == 0:
                    print("\n[Timeout] No deauth packets captured in 5 seconds. Stopping...")
                    break

            print("\nCapture complete!")
            print(f"Total Beacon Packets: {len(beacon_packets)}")
            print(f"Total Deauth Packets: {len(deauth_packets)}")
            print(f"Total STA MAC Packets: {len(STA_MAC_packets)}")

            user_input = input("Would you like to examine the packets? (Y/N)").strip().lower()

            if user_input == "y" :
                user_input = input("1. 802.11 Beacon Frames\n"+
                                    "2. 802.11Deauthentication Frames\n"+
                                    "3. 802.11 other Packets\n"+
                                    ">> ")

                if user_input == "1":
                    for packet in beacon_packets:
                        print(packet.summary()+ "\n")
                elif user_input == "2":
                    for packet in deauth_packets:
                        print(packet.summary() + "\n")
                elif user_input == "3":
                    for packet in STA_MAC_packets:
                        print(packet.summary()+ "\n")
                else:
                    print("Invalid input.")

                    
if __name__ == "__main__":

    #Check for sudo
    if os.getuid() == 0:
        #Enable wlan0 monitoring mode
        if check_wlan0mon():
            capture_packets()

            print("Process complete. Re-enabling wlan0 interface")
            subprocess.run(["sudo","airmon-ng","stop","wlan0mon"],check=True)
            subprocess.run(["sudo","systemctl","restart","NetworkManager"],check=True)
            
        else:
            print("Interface wlan0mon is not active")
            user_input = input("Would you like to enable the interface ? (Y/N)").strip().lower()

            if user_input == "y":
                try:
                    subprocess.run(["sudo", "airmon-ng", "start", "wlan0"], check=True)
                    print("Monitor mode enabled on wlan0 (now likely wlan0mon).")

                    capture_packets()

                    print("Process complete. Re-enabling wlan0 interface")
                    subprocess.run(["sudo","airmon-ng","stop","wlan0mon"],check=True)
                    subprocess.run(["sudo","systemctl","restart","NetworkManager"],check=True)

                except subprocess.CalledProcessError:
                    print("An error has occured. Check your installation of airmon-ng.")
                    exit(1)
            else:
                print("The interface needs to be enabled for the script to execute..\n"+
                        "You can use the command \"sudo airmon-ng start wlan0\" to enable the interface. ")
    else:
        print("Program needs to run as sudo. Exit and retry as sudo.")