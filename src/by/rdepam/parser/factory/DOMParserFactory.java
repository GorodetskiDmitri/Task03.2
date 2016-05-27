package by.rdepam.parser.factory;

import by.rdepam.parser.service.IParser;
import by.rdepam.parser.service.OwnDOMParser;

public class DOMParserFactory {

	private static final DOMParserFactory factory = new DOMParserFactory();

    private DOMParserFactory(){
    }

    public static DOMParserFactory getInstance() {
        return factory;
    }

    public IParser getDOMParser() {
        return new OwnDOMParser();
    }
}
