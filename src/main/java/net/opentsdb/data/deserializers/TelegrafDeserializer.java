package net.opentsdb.data.deserializers;

import net.opentsdb.data.Telegraf;
import net.opentsdb.data.TypedIncomingData;
import net.opentsdb.data.TypedIncomingExternalData;
import net.opentsdb.tsd.KafkaRpcPluginThread;
import net.opentsdb.utils.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class TelegrafDeserializer extends JSONDeserializer implements Deserializer {

    private static final Logger LOG = LoggerFactory.getLogger(JSONDeserializer.class);

    @Override
    public List<TypedIncomingData> deserialize(KafkaRpcPluginThread consumer, byte[] data) {

        if (data == null || data.length < 1) {
            LOG.error("Unable to deserialize data. Null or empty byte array.");
            return null;
        }

        boolean external;
        try {
            external =
                    JSON.getMapper().readTree(data).has("type");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if(! external){
            Telegraf externalData;
            try {
                externalData = JSON.parseToObject(data, Telegraf.class);
            } catch (Throwable ex1) {
                LOG.error("Unable to deserialize data ", ex1);
                return null;
            }
            return externalData.transform();
        }
        return super.deserialize(consumer, data);
    }
}
