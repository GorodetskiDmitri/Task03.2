package by.rdepam.parser.domain;

public interface Attribute extends Node {
    String getName();
    Element getOwnerElement();
    String getValue();
    void setValue(String value);
    boolean isId();
}