    package com.example.projetcalculmental.model;

    public enum TypeOperationEnum{
        ADD("+"),
        SUBSTRACT("-"),
        MULTIPLY("x"),
        DIVIDE("/");

        private String symbol;
        public String getSymbol() {
            return symbol;
        }
        TypeOperationEnum(String symbol) {
            this.symbol=symbol;
        }
    }
