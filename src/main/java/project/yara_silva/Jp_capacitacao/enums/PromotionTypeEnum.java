package project.yara_silva.Jp_capacitacao.enums;

public enum PromotionTypeEnum {
    PERCENTAGE("percentage promotion"),
    FIXED("fixed price promotion");

    private String promotion;

    PromotionTypeEnum(String promotion) {
        this.promotion = promotion;
    }

    public String getPromotion() {
        return promotion;
    }
}
