package dis.tc_lib_distest;

import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;

import de.fraunhofer.iosb.tc_lib_if.IVCT_TcParam;
import de.fraunhofer.iosb.tc_lib_if.TcInconclusive;

public class DISTestTcParam implements IVCT_TcParam {

    private final int FILE_NUM = 1;
    private URL[] urls = new URL[this.FILE_NUM];
    private int numberOfCycles = 10;
    private short entityKind;
    private short domain;
    private int country;
    private short category;
    private short subcategory;
    private short specification;
    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DISTestTcParam.class);

    public DISTestTcParam(String jsonParamFile) throws TcInconclusive {

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(jsonParamFile);

			// get a String from the JSON object
			String numberOfCyclesStr =  (String)jsonObject.get("numberOfCycles");
			if (numberOfCyclesStr == null) {
                throw new TcInconclusive("The key <numberOfCycles> was not found");
			}
			this.numberOfCycles = Integer.parseInt(numberOfCyclesStr);

            String entityKindStr =  (String)jsonObject.get("entityKind");
			if (entityKindStr == null) {
                throw new TcInconclusive("The key <entityKind> was not found");
			}
			this.entityKind = Short.parseShort(entityKindStr);

            String domainStr =  (String)jsonObject.get("domain");
			if (domainStr == null) {
                throw new TcInconclusive("The key <domain> was not found");
			}
			this.domain = Short.parseShort(domainStr);

            String countryStr =  (String)jsonObject.get("country");
			if (countryStr == null) {
                throw new TcInconclusive("The key <country> was not found");
			}
			this.country = Integer.parseInt(countryStr);

            String categoryStr =  (String)jsonObject.get("category");
			if (categoryStr == null) {
                throw new TcInconclusive("The key <category> was not found");
			}
			this.category = Short.parseShort(categoryStr);

            String subCategoryStr =  (String)jsonObject.get("subcategory");
			if (subCategoryStr == null) {
                throw new TcInconclusive("The key <subcategory> was not found");
			}
			this.subcategory = Short.parseShort(subCategoryStr);

            String specStr =  (String)jsonObject.get("specification");
			if (specStr == null) {
                throw new TcInconclusive("The key <specification> was not found");
			}
			this.specification = Short.parseShort(specStr);

		} catch (ParseException e1) {
            throw new TcInconclusive("Error in parsing FOM file: " + e1.toString());
		}
    }


    /**
     * @return the number of Entity State PDUs that should be received from the SuT
     */
    public int getNumberOfCycles() {
        return this.numberOfCycles;
    }

    /**
     * @return the number of Entity State PDUs that should be received from the SuT
     */
    public short getEntityKind() {
        return this.entityKind;
    }

    /**
     * @return the number of Entity State PDUs that should be received from the SuT
     */
    public short getDomain() {
        return this.domain;
    }

    /**
     * @return the number of Entity State PDUs that should be received from the SuT
     */
    public int getCountry() {
        return this.country;
    }

    /**
     * @return the number of Entity State PDUs that should be received from the SuT
     */
    public short getCategory() {
        return this.category;
    }

    /**
     * @return the number of Entity State PDUs that should be received from the SuT
     */
    public short getSubcategory() {
        return this.subcategory;
    }

    /**
     * @return the number of Entity State PDUs that should be received from the SuT
     */
    public short getSpec() {
        return this.specification;
    }

    /**
     * @return the urls
     */
    @Override
    public URL[] getUrls() {
        return this.urls;
    }
    
}
