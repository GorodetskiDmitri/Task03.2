package by.rdepam.parser.service;

import by.rdepam.parser.domain.impl.AttributeImpl;
import by.rdepam.parser.domain.impl.DocumentImpl;
import by.rdepam.parser.domain.impl.ElementImpl;
import by.rdepam.parser.domain.impl.TextImpl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OwnDOMParser implements IParser {

	 	private final static char tagStart = '<';
	    private final static char tagEnd = '>';
	    private final static char tagClose = '/';
	    private final static char quotes = '"';
	    private final static char space = ' ';
	    private final static char equally = '=';
	    private final static char question = '?';

	    private FileReader fileReader;
	    private char currentSymbol;

	    private DocumentImpl documentImp;

	    public OwnDOMParser() {
	    }

	    public void setXMLFile(String xmlFileURI) {
	        File xmlFile = new File(xmlFileURI);
	        try
	        {
	            fileReader = new FileReader(xmlFile);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public DocumentImpl parse() {

	        while (readSymbol() != 0) {
	             readTag(null);
	        }

	        return documentImp;
	    }

	    private char readSymbol() {
	        int readSymbol = 0;
	        try {
	            readSymbol = fileReader.read();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        if (readSymbol != -1)
	            currentSymbol = (char)readSymbol;
	        else
	            currentSymbol = 0;

	        return currentSymbol;
	    }

	    private void readTag(ElementImpl parentElement) {
	        ElementImpl element = new ElementImpl();

	        boolean closedTag = false;
	        boolean singleTag = false;
	        boolean declaration = false;

	        StringBuilder tagName = new StringBuilder();

	        String textBetweenTags = readText();
	        if(currentSymbol == 0)
	            return;

	        while (readSymbol() != tagEnd) {

	            if (currentSymbol == tagClose){
	                if(tagName.length() == 0)
	                    closedTag = true;
	                else
	                    singleTag = true;
	                break;
	            }
	            if (currentSymbol == space)
	                readAttribute(element);
	            else
	                tagName.append(currentSymbol);
	        }

	        if (tagName.length() != 0)
	            if  (tagName.charAt(0) == question && tagName.charAt(tagName.length() - 1) == question)
	                declaration = true;

	        if (closedTag) {
	            parentElement.setTextContent(new TextImpl(textBetweenTags.toString()));
	        }

	        if (!closedTag && !declaration) {
	            element.setTagName(tagName.toString());
	            element.setParentElement(parentElement);
	            if (parentElement != null)
	                parentElement.addChildElement(element);
	            else
	                documentImp = new DocumentImpl(element);
	        }

	        if (!closedTag && !singleTag && !declaration) {
	            readTag(element);
	        }
	        else if (!closedTag)
	            readTag(parentElement);
	        else
	            readTag((ElementImpl) parentElement.getParentElement());

	    }

	    private String readText() {
	        StringBuilder textBetweenTags = new StringBuilder();

	        while (currentSymbol != tagStart && currentSymbol != 0)
	            textBetweenTags.append(readSymbol());

	        if (textBetweenTags.length() != 0)
	            textBetweenTags.deleteCharAt(textBetweenTags.length() - 1);

	        return textBetweenTags.toString();
	    }

	    private void readAttribute(ElementImpl element) {
	        AttributeImpl attribute = new AttributeImpl();
	        StringBuilder attributeName = new StringBuilder();
	        StringBuilder attributeValue = new StringBuilder();

	        
	        while (readSymbol() != equally)
	            attributeName.append(currentSymbol);
	        
	        readSymbol();
	        
	        while (readSymbol() != quotes)
	            attributeValue.append(currentSymbol);

	        attribute.setName(attributeName.toString());
	        attribute.setValue(attributeValue.toString());
	        element.addAttribute(attribute);
	    }
}
