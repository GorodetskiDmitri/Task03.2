package by.rdepam.parser.service;

import by.rdepam.parser.domain.Document;

public interface IParser {
	Document parse();
	void setXMLFile(String xmlFileURI);
}
