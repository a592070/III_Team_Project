package pojo;

import java.math.BigDecimal;

public class Type {
    private BigDecimal type;
    private String typeName;

    public Type(BigDecimal type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public Type() {
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
