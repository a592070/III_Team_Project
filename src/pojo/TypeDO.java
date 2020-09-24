package pojo;

import java.math.BigDecimal;

public class TypeDO {
    private BigDecimal type;
    private String typeName;

    public TypeDO(BigDecimal type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public TypeDO() {
    }

    public BigDecimal getType() {
        return type;
    }

    public void setType(BigDecimal type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
