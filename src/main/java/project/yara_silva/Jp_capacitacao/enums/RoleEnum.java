package project.yara_silva.Jp_capacitacao.enums;

public enum RoleEnum {
    USER("User"),
    SELLER("Seller"),
    ADMIN("Administrator");

    private String roleReport;

    RoleEnum(String roleReport) {
        this.roleReport = roleReport;
    }

    public String getRoleReport() {
        return roleReport;
    }
}
