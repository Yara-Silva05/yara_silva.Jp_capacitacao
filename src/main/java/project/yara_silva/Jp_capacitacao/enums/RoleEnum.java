package project.yara_silva.Jp_capacitacao.enums;

public enum RoleEnum {
    USER("User"),
    SELLER("Seller"),
    ADMIN("Administrator");

    private String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
