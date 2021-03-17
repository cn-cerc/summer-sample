package com.tieke.business.db;

public class Field {
    private String code;
    private String name;
    private String type = "String";

    public Field(String code, String name, String fieldType) {
        this.setCode(code);
        this.setName(name);
        this.setType(fieldType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVariantCode() {
        String ss = code;
        if (code.endsWith("_"))
            ss = code.substring(0, code.length() - 1);
        return ss;
    }

    public String getType() {
        if (type.startsWith("int("))
            return "int";
        else if (type.startsWith("bigint("))
            return "long";
        else if (type.startsWith("decimal("))
            return "double";
        else if (type.startsWith("varchar("))
            return "String";
        else if (type.startsWith("datetime"))
            return "TDateTime";
        else if (type.startsWith("date"))
            return "TDateTime";
        else
            return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
