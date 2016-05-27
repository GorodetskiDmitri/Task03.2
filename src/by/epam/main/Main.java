package by.epam.main;

import java.util.List;

import by.epam.domain.Dish;
import by.epam.domain.Section;
import by.epam.domain.Menu;
import by.rdepam.parser.domain.Document;
import by.rdepam.parser.domain.Element;
import by.rdepam.parser.factory.DOMParserFactory;
import by.rdepam.parser.service.IParser;

public class Main {

    private static final String xmlFileURI = "source\\menu.xml";

    public static void main(String[] args) {

        Menu menu = new Menu();

        DOMParserFactory factory = DOMParserFactory.getInstance();
        IParser parser = factory.getDOMParser();
        parser.setXMLFile(xmlFileURI);
        Document document = parser.parse();

        Element menuElement = document.getDocumentElement();

        List<Element> sectionElements = menuElement.getElementsByTagName("section");
        
        for (Element sectionElement : sectionElements) {
            Section section = new Section();
            section.setName(sectionElement.getElementsByTagName("section-name").get(0).getInnerText());
            menu.addSection(section);

            List<Element> dishElements = sectionElement.getElementsByTagName("dish");
            
            for (Element dishElement : dishElements) {
                Dish dish = new Dish();
                try {
                dish.setPhoto(dishElement.getElementsByTagName("dish-photo").get(0).getInnerText());
                dish.setName(dishElement.getElementsByTagName("dish-name").get(0).getInnerText());
                dish.setDescription(dishElement.getElementsByTagName("dish-description").get(0).getInnerText());
                dish.setPortion(dishElement.getElementsByTagName("dish-portion").get(0).getInnerText());
                dish.setPrice(Integer.parseInt(dishElement.getElementsByTagName("dish-price").get(0).getInnerText()));
                } catch (Exception e) {
                	e.printStackTrace();
                }
                section.addDish(dish);
            }
        }

        System.out.println("МЕНЮ:\n");
        for (Section section : menu.getSections()) {
            System.out.println(section.getName());
            for (Dish dish : section.getDishes()) {
                System.out.println(
                		"\tНазвание: " + dish.getName() +
                		"\n\tОписание: " + (dish.getDescription() != null ? dish.getDescription() : "отсутствует") +
                		"\n\tПорция: " + dish.getPortion() + 
                		"\n\tЦена: " + (dish.getPrice() != 0 ? dish.getPrice() : "не указана") +
                		"\n\tФото: " + dish.getPhoto() +
                		"\n"); 		
            }
        }
    }
}