package dev.fbiopereira.eventsdemo.fanoutexchange.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Estrutura do CloudEvent")
public class CloudEventSchema {

    @Schema(description = "Versão da especificação CloudEvent", example = "1.0")
    @NotNull
    private String specversion;

    @Schema(description = "Tipo do evento", example = "com.example.notification")
    @NotNull
    private String type;

    @Schema(description = "Origem do evento", example = "/myapp/notifications")
    @NotNull
    private String source;

    @Schema(description = "Identificador único do evento", example = "string")
    @NotNull
    private String id;

    @Schema(description = "Timestamp do evento", example = "2023-01-01T00:00:00Z")
    private String time;

    @Schema(description = "Tipo de conteúdo dos dados", example = "application/json")
    private String datacontenttype;

    @Schema(description = "Dados do evento")
    @Valid
    @NotNull
    private EventData data;

    // Getters e setters
    public String getSpecversion() {
        return specversion;
    }

    public void setSpecversion(String specversion) {
        this.specversion = specversion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDatacontenttype() {
        return datacontenttype;
    }

    public void setDatacontenttype(String datacontenttype) {
        this.datacontenttype = datacontenttype;
    }

    public EventData getData() {
        return data;
    }

    public void setData(EventData data) {
        this.data = data;
    }
}
