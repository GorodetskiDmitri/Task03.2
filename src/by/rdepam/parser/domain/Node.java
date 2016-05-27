package by.rdepam.parser.domain;

public interface Node {
    short getNodeType();
    
    String getNodeValue();
    Node getParentNode();

    Node getFirstChild();
    Node getLastChild();
}