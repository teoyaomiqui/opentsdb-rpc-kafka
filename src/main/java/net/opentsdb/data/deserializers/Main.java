package net.opentsdb.data.deserializers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.xml.internal.bind.v2.model.core.TypeRef;
import net.opentsdb.data.Metric;
import net.opentsdb.data.Metrics;
import net.opentsdb.data.Telegraf;
import net.opentsdb.data.TypedIncomingData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.stumbleupon.async.Deferred;

import net.opentsdb.core.TSDB;
import net.opentsdb.utils.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Main {

    public static void main(String[] args) {
        String json = "{\n" +
                "    \"fields\": {\n" +
                "        \"field_1\": 30,\n" +
                "        \"field_2\": 4,\n" +
                "        \"field_N\": 59,\n" +
                "        \"n_images\": 660\n" +
                "    },\n" +
                "    \"name\": \"docker\",\n" +
                "    \"opentsdb_external_type_metric\": \"Telegraf\",\n" +
                "    \"tags\": {\n" +
                "        \"host\": \"raynor\"\n" +
                "    },\n" +
                "    \"timestamp\": 1458229140\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] data;

        data = json.getBytes();
        try {
            if (JSON.getMapper().readTree(data).has("opentsdb_external_type_metric")) {
                System.out.println("SLAVA UKRAINE");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Telegraf newJson;
        newJson = JSON.parseToObject(data, Telegraf.class);
        System.out.println(newJson.toString());
        /**
        Map tags = newJson.getTags();
        List<TypedIncomingData> metrics = new ArrayList<TypedIncomingData>(newJson.fields.size());

        for (Map.Entry<String, String> entry : newJson.fields.entrySet()) {

            String metricName = newJson.name + "." + entry.getKey();
            Metric metric = new Metric();

            metric.setValue(entry.getValue());
            metric.setMetric(metricName);
            metric.setTags(new HashMap<String, String>(tags));
            metric.setTimestamp(newJson.getTimestamp());

            metrics.add(metric);
        }
        if (TelegrafMetric.class == newJson.getClass()) {
            System.out.println();
        }
         */
    }
}
