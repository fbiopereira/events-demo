package dev.fbiopereira.eventsdemo.fanoutexchange.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados específicos do evento")
public class EventData {

    @Schema(description = "Identificador do objeto", example = "12345")
    @NotNull
    private String objectId;

    @Schema(description = "Detalhes do objeto", example = "Detalhes do objeto processado")
    @NotNull
    private String objectDetail;

    // Getters e setters
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectDetail() {
        return objectDetail;
    }

    public void setObjectDetail(String objectDetail) {
        this.objectDetail = objectDetail;
    }
}
