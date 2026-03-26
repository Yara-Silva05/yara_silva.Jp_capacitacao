package project.yara_silva.Jp_capacitacao.enums;

public enum InventoryReasonEnum {
    PURCHASE("stock entry"),
    ORDER("sale"),
    ADJUSTMENT("manual correction"),
    RETURN("devolution");

    private String reason;

    InventoryReasonEnum(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
