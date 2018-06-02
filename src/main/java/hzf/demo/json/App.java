package hzf.demo.json;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WTO on 2016/8/20 0020.
 *
 */
public class App
{
    public static void main(String[] args) {
        try
        {
            String json = "{\n" +
                    "\t\"condition\": \"yes\",\n" +
                    "        \"Branch\": {  \"route1\": \"1\" },\n" +
                    "\t\"dataList\": [\n" +
                    "\t\t{\n" +
                    "\t\t\t\"corePlanId\": \"1003597\",\n" +
                    "\t\t\t\"createTime\": \"2016-08-19 16:33:09\",\n" +
                    "\t\t\t\"repaymentAmount\": 41700.04,\n" +
                    "\t\t\t\"type\": \"mobile\",\n" +
                    "\t\t\t\"yn\": \"1\"\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"corePlanId\": \"1003598\",\n" +
                    "\t\t\t\"createTime\": \"2016-08-19 16:33:09\",\n" +
                    "                         \"repaymentAmount\": 4100.04,\n" +
                    "\t\t\t\"type\": \"pc\",\n" +
                    "\t\t\t\"yn\": \"1\"\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"corePlanId\": \"1003599\",\n" +
                    "\t\t\t\"createTime\": \"2016-08-19 16:33:09\",\n" +
                    "\t\t\t\"repaymentAmount\": 40566.68,\n" +
                    "\t\t\t\"type\": \"mobile\",\n" +
                    "\t\t\t\"yn\": \"3\"\n" +
                    "\t\t}\n" +
                    "\t]\n" +
                    "}\n";

            String bigJsonData = "{\"LSS_SENREPLAN\":" + json + "}";
            ObjectMapper jackson = new ObjectMapper();
            JsonNode target = jackson.readTree(bigJsonData);

            String time = "LSS_SENREPLAN.dataList.createTime";
            String data = "LSS_SENREPLAN.dataList.repaymentAmount";
            String rulePuKey = "001";
            String rulePu1 = "LSS_SENREPLAN.dataList.yn";
            String rulePu2 = "LSS_SENREPLAN.condition";
            String rulePu3 = "LSS_SENREPLAN.branch.route1";
            String rulePrKey = "002";
            String rulePr = "LSS_SENREPLAN.dataList.type";

            String[] timeKeys = time.split("\\.");
            String[] dataKeys = data.split("\\.");
            String[] rulePu1Keys = rulePu1.split("\\.");
            String[] rulePu2Keys = rulePu2.split("\\.");
            String[] rulePrKeys = rulePr.split("\\.");

            Map<String, String[]> map = new HashMap<String, String[]>();

            map.put(timeKeys[timeKeys.length - 1], timeKeys);
            map.put(dataKeys[dataKeys.length - 1], dataKeys);
            map.put(rulePu1Keys[rulePu1Keys.length - 1], rulePu1Keys);
            map.put(rulePu2Keys[rulePu2Keys.length - 1], rulePu2Keys);
            map.put(rulePrKeys[rulePrKeys.length - 1], rulePrKeys);

            for (Map.Entry<String, String[]> val : map.entrySet())
            {
                String key = val.getKey();
                String[] array = val.getValue();
                String prefix = "";

                for (int i = 0; i < array.length - 1; i++)
                {
                    prefix += array[i] + ".";
                }

                System.out.println("key - " + key + "\t" + "prefix - " + prefix);
            }

            List<String> keys1 = new ArrayList<String>();
            List<String> keys2 = new ArrayList<String>();

            keys1.add("condition");
            keys2.add("type");
            keys2.add("yn");

            List<JsonNode> nodes1 = new ArrayList<JsonNode>();

            for (String key : keys1)
            {
                JsonNode nodeTmp = target.get(key);
                if ( nodeTmp.isArray() )
                {
                    for (JsonNode node : nodeTmp)
                    {
                        nodes1.add(node);
                    }
                }
            }


        }
        catch (Exception e) { e.printStackTrace(); }

    }
}
