package project.yara_silva.Jp_capacitacao.enums;

public enum InventoryReasonEnum {
    PURCHASE("stock entry"),
    RETURN("devolution");

    private String reason;

    InventoryReasonEnum(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
