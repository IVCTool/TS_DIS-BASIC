package dis.tc_lib_distest;

import java.io.*;
import java.net.*;
import java.util.*;

import edu.nps.moves.dis.*;
import dis.tc_lib_distest.util.PduFactory;

/**
 * Implements a participant in a DIS Simulation
 */
public class DIS_PDUSender implements Runnable {
	
	private static final int LISTENING_PORT = 7000;
	private static final int PORT = 4711;	// port we send from
	private static final String BROADCAST_ADDRESS = "10.59.255.255";
	private static final int MAX_PDU_SIZE = 8192;

	private EntityID munitionID;
	private EntityType munitionType;
	private Vector3Float targetHitPosition;
	private Vector3Float initialVelocity;
	private EventID firingEvent;
	private BurstDescriptor burst;
	private FirePdu fire;
	private DetonationPdu detonation;

    private boolean fireCommandGiven = false;
    private boolean isInterrupted = false;
	private Pdu lastResponse = null;
	
	/**
	 * Builds an Entity State PDU for a Grumman F-14 Tomcat and sets its position
	 * to directly above another entity using that entity's local coordinate system.
	 * @param otherEntity the entity the Tomcat will be placed above of
	 * @return the Entity State PDU for the Tomcat
	 */
	private EntityStatePdu getOwnEntity(EntityStatePdu otherEntity) {
		
		System.out.println("Creating our entity...");
		
		EntityStatePdu own = new EntityStatePdu();
		
		EntityID eid = own.getEntityID();
		eid.setSite(otherEntity.getEntityID().getSite());
		eid.setApplication(otherEntity.getEntityID().getApplication());
		eid.setEntity(otherEntity.getEntityID().getEntity() + 1);
		
		// Grumman F-14 Tomcat
		EntityType ownType = own.getEntityType();
		ownType.setEntityKind((short)1);
		ownType.setDomain((short)2);
		ownType.setCountry(225);
		ownType.setCategory((short)1);
		ownType.setSubcategory((short)2);
		ownType.setSpec((short)1);
		
		// position our entity directly above the other entity
		Vector3Double otherEntityPosition = otherEntity.getEntityLocation();
		Vector3Double ownPosition = own.getEntityLocation();
		ownPosition.setX(otherEntityPosition.getX());
		ownPosition.setY(otherEntityPosition.getY());
		ownPosition.setZ(otherEntityPosition.getZ() + 100);
		
		return own;
	}
	
	/**
	 * Receives one Entity State PDU from the DIS simulation and returns it. If there are
	 * multiple entities participating in the simulation there is no guarantee that any one
	 * in particular is recognized.
	 * @return the first Entity State PDU that is received
	 */
	private EntityStatePdu getOtherEntity() {
		
		System.out.println("Getting target info...");
		
		DatagramSocket socket;
		DatagramPacket packet;
		PduFactory pduFactory = new PduFactory();
		
		try {
            // Specify the socket to receive data
            socket = new DatagramSocket(LISTENING_PORT);
       
            while(true) {
                byte buffer[] = new byte[MAX_PDU_SIZE];
                packet = new DatagramPacket(buffer, buffer.length);

                socket.receive(packet);

                List<Pdu> pduBundle = pduFactory.getPdusFromBundle(packet.getData());
                Iterator<Pdu> it = pduBundle.iterator();

                while(it.hasNext()) { // iterate through PDUs
                    Pdu pdu = it.next();
                
                    if(pdu instanceof EntityStatePdu) {
                    	socket.close();
                        return (EntityStatePdu)pdu;
                    }
                }
				socket.close();

            }
        }
        catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Returns one PDU received from the other Entity
	 * @return the first PDU received upon being called
	 */
	private Pdu getResponse() {
		
		//System.out.println("Getting response...");
		
		DatagramSocket socket;
		DatagramPacket packet;
		PduFactory pduFactory = new PduFactory();
		
		try {
            // Specify the socket to receive data
            socket = new DatagramSocket(LISTENING_PORT);

            byte buffer[] = new byte[MAX_PDU_SIZE];
            packet = new DatagramPacket(buffer, buffer.length);

            socket.receive(packet);

            List<Pdu> pduBundle = pduFactory.getPdusFromBundle(packet.getData());
            //System.out.println("Received " + pduBundle.size() + " PDU");
            Iterator<Pdu> it = pduBundle.iterator();

            while(it.hasNext()) {
                Pdu pdu = it.next();
                //short type = pdu.getPduType();
                //System.out.println(util.PduType.lookup(type));

				socket.close();
				return pdu;
            }

        }
        catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

    public void sendFireCommand(boolean fireCommand) {
        this.fireCommandGiven = fireCommand;
    }

    public void interrupt() {
        this.isInterrupted = true;
    }

	public Pdu getLastResponse() {
		return this.lastResponse;
	}

	@Override
	public void run() {
		
		DatagramSocket socketOut;
		DatagramPacket packet;
		
		EntityStatePdu otherEntity = getOtherEntity();	// this is the target's Entity State PDU
		EntityStatePdu ownEntity = getOwnEntity(otherEntity);	// this is our's

		this.munitionID = new EntityID();
		munitionID.setApplication(ownEntity.getEntityID().getApplication());
		munitionID.setSite(ownEntity.getEntityID().getSite());
		munitionID.setEntity(ownEntity.getEntityID().getEntity() + 1);
		
		// position is given as offset from origin of the target's local coordinate system
		this.targetHitPosition = new Vector3Float();
		targetHitPosition.setX(0);
		targetHitPosition.setY(0);
		targetHitPosition.setZ(0);
		
		this.initialVelocity = new Vector3Float();
		initialVelocity.setX(100);
		initialVelocity.setY(100);
		initialVelocity.setZ(-100);
		
		// AIM-9 Sidewinder
		this.munitionType = new EntityType();
		munitionType.setEntityKind((short)2);
		munitionType.setDomain((short)1);
		munitionType.setCountry(225);
		munitionType.setCategory((short)1);
		munitionType.setSubcategory((short)1);
		
		this.burst = new BurstDescriptor();
		burst.setWarhead(1610);	// == continuous rod warhead
		burst.setFuse(3700);	// == infrared proximity fuse
		burst.setMunition(munitionType);
		burst.setQuantity(1);
		burst.setRate(0);	// if quantity == 1, this should be 0
		
		this.firingEvent = new EventID();
		firingEvent.setApplication(ownEntity.getEntityID().getApplication());
		firingEvent.setSite(ownEntity.getEntityID().getSite());
		firingEvent.setEventNumber(1);
		
		this.fire = new FirePdu();
		fire.setFiringEntityID(ownEntity.getEntityID());
		fire.setTargetEntityID(otherEntity.getEntityID());
		fire.setEventID(firingEvent);
		fire.setLocationInWorldCoordinates(ownEntity.getEntityLocation());
		fire.setMunitionID(munitionID);
		fire.setVelocity(initialVelocity);
		fire.setRangeToTarget(100);
		
		this.detonation = new DetonationPdu();
		detonation.setEventID(firingEvent);
		detonation.setTargetEntityID(otherEntity.getEntityID());
		detonation.setMunitionID(munitionID);
		detonation.setLocationInWorldCoordinates(otherEntity.getEntityLocation());
		detonation.setLocationInEntityCoordinates(targetHitPosition);
		detonation.setDetonationResult((short)1);	// == Entity Impact
		detonation.setBurstDescriptor(burst);

		byte[] entityStateData;
		byte[] fireData;
		byte[] detonationData;

		try {
			
			socketOut = new DatagramSocket(PORT);

			while(!isInterrupted) {

				// marshal the Entity State PDU object including the timestamp
				entityStateData = ownEntity.marshalWithDisAbsoluteTimestamp();
				packet = new DatagramPacket(entityStateData, entityStateData.length, InetAddress.getByName(BROADCAST_ADDRESS), PORT);
				socketOut.send(packet);
			
				Thread.sleep(5000);

				if(fireCommandGiven) {

					System.out.println("Sending Fire PDU...");
					fireData = fire.marshalWithDisAbsoluteTimestamp();
					packet = new DatagramPacket(fireData, fireData.length, InetAddress.getByName(BROADCAST_ADDRESS), PORT);
					socketOut.send(packet);
					
					Thread.sleep(2000);
					
					System.out.println("Sending Detonation PDU...");
					detonationData = detonation.marshalWithDisAbsoluteTimestamp();
					packet = new DatagramPacket(detonationData, detonationData.length, InetAddress.getByName(BROADCAST_ADDRESS), PORT);
					socketOut.send(packet);
					
					Thread.sleep(2000);

				}

				this.lastResponse = getResponse();

			}

			// set the Entity Appearance to Deactivated & send final Entity State PDU
			ownEntity.setEntityAppearance(1);
			entityStateData = ownEntity.marshalWithDisAbsoluteTimestamp();
			packet = new DatagramPacket(entityStateData, entityStateData.length, InetAddress.getByName(BROADCAST_ADDRESS), PORT);
			socketOut.send(packet);
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
