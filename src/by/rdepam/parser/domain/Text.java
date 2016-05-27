package by.rdepam.parser.domain;

public interface Text extends Node {
    String getWholeText();
    void replaceWholeText(String content);
}
