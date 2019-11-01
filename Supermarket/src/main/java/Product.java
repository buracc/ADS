/**
 * Supermarket Customer check-out and Cashier simulation
 * @author  hbo-ict@hva.nl
 */

import utils.XMLParser;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Collection;
import java.util.Locale;
import java.util.Set;

public class Product implements Comparable<Product> {
    private String code;            // a unique product code; identical codes designate identical products
    private String description;     // the product description, useful for reporting
    private double price;           // the product's price

    /**
     * Constrcutor for Product consists of a product code, a description and a price
     * @param code the product code
     * @param description the description of the Product
     * @param price the price of the Product
     */
    public Product(String code, String description, double price) {
        this.code = code;
        this.description = description;
        this.price = price;
    }

    @Override
    public String  toString() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    /**
     * read a series of products from the xml stream
     * and add them to the provided products list
     * @param xmlParser
     * @param products
     * @return
     * @throws XMLStreamException
     */
    public static Set<Product> importProductsFromXML(XMLParser xmlParser, Set<Product> products) throws XMLStreamException {
        if (xmlParser.nextBeginTag("products")) {
            xmlParser.nextTag();
            if (products != null) {
                Product product;
                while ((product = importFromXML(xmlParser)) != null) {
                    products.add(product);
                }
            }

            xmlParser.findAndAcceptEndTag("products");
            return products;
        }
        return null;
    }

    /**
     * read a single product from the xml stream
     * @param xmlParser
     * @return
     * @throws XMLStreamException
     */
    public static Product importFromXML(XMLParser xmlParser) throws XMLStreamException {
        if (xmlParser.nextBeginTag("product")) {
            String code = xmlParser.getAttributeValue(null, "code");
            String description = xmlParser.getAttributeValue(null, "description");
            double price = xmlParser.getDoubleAttributeValue(null, "price", 0);

            Product product = new Product(code, description, price);

            xmlParser.findAndAcceptEndTag("product");
            return product;
        }
        return null;
    }

    /**
     * write a single product to the xml stream
     * @param xmlWriter
     * @throws XMLStreamException
     */
    public void exportToXML(XMLStreamWriter xmlWriter) throws XMLStreamException {
        xmlWriter.writeStartElement("product");
        xmlWriter.writeAttribute("code", this.code);
        xmlWriter.writeAttribute("description", this.description);
        xmlWriter.writeAttribute("price", String.format(Locale.US, "%.2f", this.price));
        xmlWriter.writeEndElement();
    }

    /**
     * Method thaat checks if this product code is equal to the given product code, depending on the hashCode
     * @param o the given Product in the form of an Object
     * @return true or false if the Products do equal with each other
     */
    @Override
    public boolean equals(Object o){
        return this.getCode().equals(((Product) o).getCode());
    }

    /**
     * Returns the hashcode of the product code
     * @return hashcode of the code of the Product
     */
    @Override
    public int hashCode(){
        return this.getCode().hashCode();
    }

    /**
     * Compares the product codes with the given Product and returns a positive, negative or 0 as value,
     * which determines the position of the Product in a list
     * @param o the given Product
     * @return positive, negative or 0 depending on which Product is greater
     */
    @Override
    public int compareTo(Product o) {
        return this.getCode().compareTo(o.getCode());
    }
}
