#include "ns3/core-module.h"
#include "ns3/network-module.h"
#include "ns3/mobility-module.h"
#include "ns3/config-store-module.h"
#include "ns3/wifi-module.h"
#include "ns3/internet-module.h"
#include "ns3/aodv-helper.h"
#include "ns3/aodv-routing-protocol.h"
#include "ns3/delay-jitter-estimation.h"
#include "ns3/v4ping-helper.h"

#include <iostream>
#include <fstream>

NS_LOG_COMPONENT_DEFINE ("AODV");

using namespace ns3;
using namespace std;

uint32_t received_packets = 0;
long double total_delay = 0;

void ReceivePacket (Ptr<Socket> socket)
{
	ofstream outfile;
	outfile.open("aodv400n_Simulation_general.txt", ios::app);
	Ptr<Packet> packet;
	Address from;
	while (packet = socket->RecvFrom (from))
	{
		if (packet->GetSize () > 0)//10
		{
			received_packets++;
			DelayJitterEstimation delay;
			delay.RecordRx(packet);
			total_delay += delay.GetLastDelay().GetSeconds();
			InetSocketAddress iaddr = InetSocketAddress::ConvertFrom (from);
			
			NS_LOG_UNCOND ("--\nOne packet recieved! The number of packets until now: " << received_packets <<
						   " From: " << iaddr.GetIpv4() <<
						   " Delay: " << delay.GetLastDelay().GetSeconds() << 
						   " Packet size "<< packet -> GetSize() << 
							" port: " << iaddr.GetPort () << 
							" at time = " << Simulator::Now ().GetSeconds () << "\n--");
			
			outfile << "One packet received! Socket: "<< iaddr.GetIpv4 ()<< 
					   " port: " << iaddr.GetPort () << 
					   " at time = " << Simulator::Now ().GetSeconds () << "\n";
			(void) iaddr;
		}
	}
	outfile.close();
}

static void GenerateTraffic (Ptr<Socket> socket, uint32_t pktSize, Ptr<Node> n, uint32_t pktCount, Time pktInterval)
{
	Ptr<Packet> p = Create<Packet> (pktSize);
	DelayJitterEstimation delay;
	delay.PrepareTx(p);
	if (pktCount > 0)
	{
		socket->Send (p);
		Simulator::Schedule (pktInterval, &GenerateTraffic, socket, pktSize, n, pktCount - 1, pktInterval);
	}
	else
	{
		socket->Close ();
	}
}

int main (int argc, char *argv[])
{
	bool verbose = false;
	double startTime = 0.0;
	uint32_t numPackets = 100; // Number of packets sent
	std::string phyMode ("DsssRate11Mbps");
	uint32_t packetSize = 1024;
	long double interval = 1;
	uint32_t numNodes = 300; //Number node; 50, 100, 150, 200, 250, 300, 350, 400, 450
	uint32_t speed = 2; //Node speed

	ofstream outfile;
	ofstream outfile2;

	outfile.open("aodv400n_Simulation_general.txt", ios::app);
	outfile << "Simulation Parameters" << endl
			<< "Routing Protocol: AODV" << endl
			<< "Number Node: " << numNodes << endl
			<< "Speed: " << speed << " m/s" << endl
			<< "Number of packets sent each node: " << numPackets << endl
			<< "Data rate: " << ((packetSize/interval)*8)/1024 << " Mbps" << endl;

	outfile2.open("aodv400n_Simulation_results.txt", ios::app);
	outfile2 << "----Simulation Parameters----" << endl
			 << "Routing Protocol: AODV" << endl
			 << "Number Node: " << numNodes << endl
			 << "Speed: " << speed << " m/s" << endl
			 << "Number of packets sent each node: " << numPackets <<endl
			 << "Data rate: " << ((packetSize/interval)*8)/1024 << " Mbps" << endl;

	CommandLine cmd;
	cmd.AddValue ("phyMode", "Wifi Phy mode", phyMode);
	cmd.AddValue ("PacketSize", "Packet size aplikasi dikirim", packetSize);
	cmd.AddValue ("numPackets", "Number total paket yang dikirim", numPackets);
	cmd.AddValue ("startTime", "Waktu mulai simulasi", startTime);
	cmd.AddValue ("verbose", "Turn on all device log components", verbose);
	cmd.Parse (argc, argv);
	Time interPacketInterval = Seconds (interval);
	Config::SetDefault ("ns3::WifiRemoteStationManager::FragmentationThreshold", StringValue ("2200"));
	Config::SetDefault ("ns3::WifiRemoteStationManager::RtsCtsThreshold", StringValue ("2200"));
	Config::SetDefault ("ns3::WifiRemoteStationManager::NonUnicastMode", StringValue (phyMode));
	NodeContainer networkNodes;
	networkNodes.Create (numNodes);
	WifiHelper wifi;
	if (verbose)
	{
		wifi.EnableLogComponents ();
	}
	wifi.SetStandard (WIFI_PHY_STANDARD_80211b);

	/** Wifi PHY **/
	/**************************************************************************/
	YansWifiPhyHelper wifiPhy = YansWifiPhyHelper::Default ();
	wifiPhy.Set ("RxGain", DoubleValue (5));
	wifiPhy.Set ("TxGain", DoubleValue (5));
	wifiPhy.Set ("CcaMode1Threshold", DoubleValue (0.0));
	/**************************************************************************/

	/** wifi channel **/
	YansWifiChannelHelper wifiChannel;
	wifiChannel.SetPropagationDelay ("ns3::ConstantSpeedPropagationDelayModel");
	wifiChannel.AddPropagationLoss ("ns3::FriisPropagationLossModel");
	Ptr<YansWifiChannel> wifiChannelPtr = wifiChannel.Create ();
	wifiPhy.SetChannel (wifiChannelPtr);

	/** MAC layer **/
	NqosWifiMacHelper wifiMac = NqosWifiMacHelper::Default ();
	wifi.SetRemoteStationManager ("ns3::ConstantRateWifiManager", 
								  "DataMode", StringValue (phyMode),
								  "ControlMode", StringValue (phyMode));
	wifiMac.SetType ("ns3::AdhocWifiMac");
	NetDeviceContainer devices = wifi.Install (wifiPhy, wifiMac, networkNodes);
	MobilityHelper mobility;
	ObjectFactory pos;
	pos.SetTypeId ("ns3::RandomRectanglePositionAllocator");
	pos.Set ("X", StringValue ("ns3::UniformRandomVariable[Min=0.0|Max=1000.0]"));
	pos.Set ("Y", StringValue ("ns3::UniformRandomVariable[Min=0.0|Max=1000.0]"));
	std::ostringstream speedConstantRandomVariableStream;
	speedConstantRandomVariableStream << "ns3::ConstantRandomVariable[Constant=" << speed << "]";
	Ptr <PositionAllocator> taPositionAlloc = pos.Create ()->GetObject <PositionAllocator> ();
	mobility.SetMobilityModel ("ns3::RandomWaypointMobilityModel", 
							   "Speed", StringValue (speedConstantRandomVariableStream.str ()),
							   "Pause", StringValue ("ns3::ConstantRandomVariable[Constant=2.0]"), 
							   "PositionAllocator", PointerValue (taPositionAlloc));
	mobility.SetPositionAllocator (taPositionAlloc);
	mobility.SetMobilityModel ("ns3::ConstantPositionMobilityModel");
	mobility.Install (networkNodes);
	
	AodvHelper aodv;
	InternetStackHelper internet;
	internet.SetRoutingHelper(aodv);
	internet.Install (networkNodes);
	Ipv4AddressHelper ipv4;

	NS_LOG_INFO ("Assign IP Addresses.");
	ipv4.SetBase ("10.1.0.0", "255.255.0.0");
	Ipv4InterfaceContainer i = ipv4.Assign (devices);
	TypeId tid = TypeId::LookupByName ("ns3::UdpSocketFactory");
	Ptr<Socket> recvSink;
	recvSink = Socket::CreateSocket (networkNodes.Get (0), tid);
	InetSocketAddress local = InetSocketAddress (Ipv4Address::GetAny (), 80);
	recvSink->Bind (local);
	recvSink->SetRecvCallback (MakeCallback (&ReceivePacket));
	Ptr<Socket> source;
	for(uint32_t count1 = 1; count1 < numNodes; count1++)
	{
		source = Socket::CreateSocket (networkNodes.Get (count1), tid);
		InetSocketAddress remote = InetSocketAddress (Ipv4Address::GetBroadcast (), 80);
		source->SetAllowBroadcast (true);
		source->Connect (remote);
	}
	for(uint32_t count2 = 1; count2 < numNodes; count2++)
	{
		Simulator::Schedule (Seconds(startTime), &GenerateTraffic, source,
			packetSize, networkNodes.Get(count2), numPackets, interPacketInterval);
	}
	Simulator::Stop (Seconds (30.0));
	AsciiTraceHelper ascii;
	wifiPhy.EnableAsciiAll (ascii.CreateFileStream ("aodv400n.tr"));
	Simulator::Run ();
	Simulator::Destroy();

	NS_LOG_UNCOND ("Number of packets sent: " << numPackets*(numNodes - 1) << 
				   " packet. \nNumber of packets recieved: " << received_packets << 
				   " packet. \nPackets Recieved: " << double(received_packets*100)/(numPackets*(numNodes - 1)) << 
				   "%. \nAverage Delay: " << total_delay/received_packets << 
				   " Seconds. \nThroughput: " << double ((received_packets*packetSize)/30)*8/1024 << 
				   " Kbps.\nSimulator End");
	outfile2 << "----Simulator Results----" << endl 
			 << "Number of packets sent: " << numPackets*(numNodes - 1) << endl
			 << "Number of packets recieved: " << received_packets << " packet. " << endl 
			 << "Packet recieved: " << double(received_packets*100)/(numPackets*(numNodes - 1)) << "%." << endl 
			 << "Average Delay: " << total_delay/received_packets << " Seconds." << endl 
			 << "Throughput: " << double((received_packets*packetSize)/30)*8/1024 << " Kbps." << endl;

	outfile << "Simulator End" << endl << endl;
	outfile2 << "Simulator End" << endl << endl <<
	"---------------------------------------------------------" << endl << endl;
	outfile.close();
	outfile2.close();
	received_packets = 0;
	total_delay = 0;
	return 0;
}
