package com.scm.helper;

public enum MessageType {

    RED("text-red-800", "bg-red-50", "border-red-300"),
    BLUE("text-blue-800", "bg-blue-50", "border-blue-300"),
    GREEN("text-green-800", "bg-green-50", "border-green-300"),
    YELLOW("text-yellow-800", "bg-yellow-50", "border-yellow-300");

    private final String textColor;
    private final String backgroundColor;
    private final String borderColor;

    MessageType(String textColor, String backgroundColor, String borderColor) {
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

}
