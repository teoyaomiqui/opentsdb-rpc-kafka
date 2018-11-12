package net.opentsdb.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "opentsdb_external_type_metric")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Telegraf.class, name = "Telegraf"),
})
public interface TypedIncomingExternalData {

    public List<TypedIncomingData> transform();
}
