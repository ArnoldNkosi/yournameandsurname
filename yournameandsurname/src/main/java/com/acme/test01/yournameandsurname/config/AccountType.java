package com.acme.test01.yournameandsurname.config;

public enum AccountType {

    SAVINGS_Account("savings_account"),
    CHEQUE_ACCOUNT("cheque_account");

    AccountType(String value){
        this.description = value;
    }

    private String description="";

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
