package project.yara_silva.Jp_capacitacao.enums;

public enum AuditActionEnum {
    CREATE("create"),
    UPDATE("update"),
    DELETE("delete");

    private String action;

    AuditActionEnum (String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
