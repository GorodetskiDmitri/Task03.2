package by.rdepam.parser.domain.impl;

import by.rdepam.parser.domain.Document;
import by.rdepam.parser.domain.Element;
import by.rdepam.parser.domain.Node;

public class DocumentImpl implements Document {
    private Element documentElement;

    public DocumentImpl(){
    }

    public DocumentImpl(Element documentElement){
        this.documentElement = documentElement;
    }

    @Override
    public Element getDocumentElement() {
        return documentElement;
    }

    @Override
    public short getNodeType() {
        return 0;
    }

    @Override
    public String getNodeValue() {
        return null;
    }

    @Override
    public Node getParentNode() {
        return null;
    }

    @Override
    public Node getFirstChild() {
        return documentElement;
    }

    @Override
    public Node getLastChild() {
        return documentElement;
    }
}