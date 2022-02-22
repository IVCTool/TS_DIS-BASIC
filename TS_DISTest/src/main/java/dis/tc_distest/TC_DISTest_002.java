package dis.tc_distest;

import dis_lib.IVCT_BaseModel;
import dis_lib.*;

import org.slf4j.Logger;

import edu.nps.moves.dis.*;
import dis.tc_lib_distest.*;

public class TC_DISTest_002 extends AbstractDisTestCase {

    static DISTestTcParam disTestTcParam;
    //private DIS_PDUSender testObject;
    private DIS_PDUListener testListener;
    private short entityKind;
    private short domain;
    private int country;
    private short category;
    private short subcategory;
    private short specification;
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
        stringBuilder.append("Test if a DIS simulator sends the correct Entity State PDUs\n");
        stringBuilder.append("One Entity State PDU is received and the information contained in it \n");
        stringBuilder.append("is checked.                                                          \n");
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

        this.entityKind = disTestTcParam.getEntityKind();
        this.domain = disTestTcParam.getDomain();
        this.country = disTestTcParam.getCountry();
        this.category = disTestTcParam.getCategory();
        this.subcategory = disTestTcParam.getSubcategory();
        this.specification = disTestTcParam.getSpec();
        // initiate new DIS simulation object
        //this.testObject = new DIS_PDUSender();
        this.testListener = new DIS_PDUListener();
    }

    @Override
    protected void performTest(final Logger logger) throws TcInconclusive, TcFailed {

        this.testListener.listen();
        this.response = this.testListener.getResponse();

        if(response != null) {
            if(response instanceof EntityStatePdu) {
                EntityType responseType = ((EntityStatePdu)response).getEntityType();
                if(responseType.getEntityKind() == this.entityKind) {
                    if(responseType.getDomain() == this.domain) {
                        if(responseType.getCountry() == this.country) {
                            if(responseType.getCategory() == this.category) {
                                if(responseType.getSubcategory() == this.subcategory) {
                                    if(responseType.getSpec() == this.specification) {
                                        // if we get here everything went alright
                                        operator().sendTcStatus("running", 100);
                                    } else {
                                        throw new TcFailed("The value of the Specification field was incorrect");
                                    }
                                } else {
                                    throw new TcFailed("The value of the Subcategory field was incorrect");
                                }
                            } else {
                                throw new TcFailed("The value of the Category field was incorrect");
                            }
                        } else {
                            throw new TcFailed("The value of the Country field was incorrect");
                        }
                    } else {
                        throw new TcFailed("The value of the Domain field was incorrect");
                    }
                } else {
                    throw new TcFailed("The value of the EntityKind field was incorrect");
                }
            }
        } else {
            throw new TcInconclusive("No Entity State PDU was received");
        }

        /*
        Pdu[] responseList = new Pdu[5];

        this.testObject.sendFireCommand(true);

        try {
            Thread.sleep(5000);
        

            for(int i=0;i<responseList.length;i++) {
                
                this.response = this.testListener.getResponse();

                if(response != null) {
                    responseList[i] = response;
                } else {
                    throw new TcInconclusive("No PDU was received");
                }

            
                operator().sendTcStatus("running", i*10);
                Thread.sleep(5000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }

    @Override
    protected void postambleAction(final Logger logger) throws TcInconclusive {

    }

}