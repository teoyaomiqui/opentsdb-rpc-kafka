package net.opentsdb.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Telegraf implements TypedIncomingExternalData{

    public String name;
    public String type;
    public Map<String, String> fields;
    public long timestamp;
    public Map<String, String> tags;

    public Telegraf(){

    }

    public Telegraf(final String name,
                    final long timestamp,
                    final Map <String, String> fields,
                    final Map<String, String> tags) {
        this.name = name;
        this.timestamp = timestamp;
        this.fields = fields;
        this.tags = tags;

    }
    @Override
    public List<TypedIncomingData> transform(){

        if (fields == null || fields.size() < 1) {
            return null;
        }

        List<TypedIncomingData> metrics = new ArrayList<TypedIncomingData>(fields.size());

        for (Map.Entry<String, String> entry : fields.entrySet()) {

            String metricName = name + "." + entry.getKey();
            String value = entry.getValue();

            Metric metric = new Metric(metricName,
                                       timestamp,
                                       value,
                                       tags);
            metrics.add(metric);
        }

        return metrics;
    }





}
