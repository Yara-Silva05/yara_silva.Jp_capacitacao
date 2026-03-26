package project.yara_silva.Jp_capacitacao.enums;

public enum OrderStatusEnum {
    PENDING("awaiting payment"),
    PAID("paid"),
    SHIPPED("shipped"),
    DELIVERED("delivered"),
    CANCELED("canceled");

    private String status;

    OrderStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
