package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

public class Token {

    @JsonProperty("token")
    private UUID uuid;

    public Token() {

    }

    public void setUUID() {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUUID() {
        return uuid;
    }




}
