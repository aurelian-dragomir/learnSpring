package ing.hub.ingHub.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "getModel")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BmwCar.class, name = "bmw"),
        @JsonSubTypes.Type(value = Dacia.class, name = "dacia")
})
public interface Car {
    String getModel();
}
