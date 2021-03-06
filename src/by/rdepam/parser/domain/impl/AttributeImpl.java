package by.rdepam.parser.domain.impl;

import by.rdepam.parser.domain.Attribute;
import by.rdepam.parser.domain.Element;
import by.rdepam.parser.domain.Node;

public class AttributeImpl implements Attribute {
    private Element ownerElement;
    private String name;
    private String value;

    public AttributeImpl() {
    }

    public AttributeImpl(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    @Override
    public Element getOwnerElement() {
        return ownerElement;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean isId() {
        return name.equals("ID");
    }

    @Override
    public short getNodeType() {
        return 2;
    }

    @Override
    public String getNodeValue() {
        return value;
    }

    @Override
    public Node getParentNode() {
        return ownerElement;
    }

    @Override
    public Node getFirstChild() {
        return null;
    }

    @Override
    public Node getLastChild() {
        return null;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttributeImpl that = (AttributeImpl) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(value != null ? !value.equals(that.value) : that.value != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
    	return getClass().getName() + "@" + "name : " + name +
    			", value : " + value;
    }
}