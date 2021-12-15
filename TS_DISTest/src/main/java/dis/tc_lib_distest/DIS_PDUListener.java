package dis.tc_lib_distest;

import java.io.*;
import java.net.*;
import java.util.*;

import edu.nps.moves.dis.*;
//import edu.nps.moves.disenum.PduType;
import dis.tc_lib_distest.util.PduFactory;

public class DIS_PDUListener {

    private static final int LISTENING_PORT = 7000;
    private static final int MAX_PDU_SIZE = 8192;

    private Pdu response;

    public Pdu getResponse() {
        return this.response;
    }
    
    public void listen() {

        DatagramSocket socket;
		DatagramPacket packet;
		PduFactory pduFactory = new PduFactory();

        try {
            
            // open a socket to receive data on
            socket = new DatagramSocket(LISTENING_PORT);
    
            byte buffer[] = new byte[MAX_PDU_SIZE];
            packet = new DatagramPacket(buffer, buffer.length);
            
            socket.setSoTimeout(10000);
            socket.receive(packet);
    
            List<Pdu> pduBundle = pduFactory.getPdusFromBundle(packet.getData());
            Iterator<Pdu> it = pduBundle.iterator();
    
            // get the first (and only) Pdu from the received bundle
            while(it.hasNext()) {
                Pdu pdu = it.next();
                this.response = pdu;
            }

            socket.close();

        }
        catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}

    }

}
