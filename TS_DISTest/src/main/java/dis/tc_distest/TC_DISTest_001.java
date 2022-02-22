package dis.tc_distest;

import dis_lib.IVCT_BaseModel;
import dis_lib.*;

import org.slf4j.Logger;

import edu.nps.moves.dis.*;
import dis.tc_lib_distest.*;

public class TC_DISTest_001 extends AbstractDisTestCase {

    static DISTestTcParam disTestTcParam;
    private DIS_PDUListener testListener;
    private int numberOfCycles;
    private Pdu response;

    @Override
    public IVCT_BaseModel getIVCT_BaseModel(final Logger logger) throws TcInconclusive {
    	IVCT_BaseModel disBaseModel = new IVCT_BaseModel();
    	return disBaseModel;
    }

    @Override
    protected void logTestPurpose(final Logger logger) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append("---------------------------------------------------------------------\n");
        stringBuilder.append("TEST PURPOSE\n");
        stringBuilder.append("Test if a DIS simulator sends Entity State PDUs correctly\n");
        stringBuilder.append("One Entity State PDU is received from the simulator and checked for  \n");
        stringBuilder.append("conformancy to the DIS standard.                                     \n");
        stringBuilder.append("---------------------------------------------------------------------\n");
        final String testPurpose = stringBuilder.toString();

        logger.info(testPurpose);
    }

    public void displayOperatorInstructions(final Logger logger) throws TcInconclusive {
        String s = "\n"
        +   "---------------------------------------------------------------------\n"
        +   "OPERATOR INSTRUCTIONS: \n"
        +   "1. Start the simulator and then hit confirm button\n"
        +   "2. The simulator should run for the full duration of the tests\n"
        +   "3. Please do not move the simulated object during the test\n"
        +   "---------------------------------------------------------------------\n";

        logger.info(s);
        sendOperatorRequest(s);
    }

    @Override
    protected void preambleAction(final Logger logger) throws TcInconclusive {

        // Notify the operator
        displayOperatorInstructions(logger);

        this.numberOfCycles = disTestTcParam.getNumberOfCycles();
        // initiate new DIS simulation object
        this.testListener = new DIS_PDUListener();
    }

    @Override
    protected void performTest(final Logger logger) throws TcInconclusive, TcFailed {

        Pdu[] responseList = new Pdu[numberOfCycles];

        // give the simulator some time to send a PDU first
        try {
            Thread.sleep(5000);
        
            // loop for a specified amount of times, collect one Entity State PDU for each cycle and check them for validity
            for(int i=0;i<this.numberOfCycles;i++) {

                this.testListener.listen();
                this.response = this.testListener.getResponse();

                if(response != null) {
                    responseList[i] = response;
                } else {
                    throw new TcInconclusive("No PDU was received");
                }

                operator().sendTcStatus("running", i*10);
                Thread.sleep(5000);
            }

            for(int i=0;i<responseList.length;i++) {
                if(!(responseList[i] instanceof EntityStatePdu)) {
                    throw new TcFailed("Did not receive an Entity State PDU");
                }
                operator().sendTcStatus("running", i*10);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    protected void postambleAction(final Logger logger) throws TcInconclusive {

    }

}