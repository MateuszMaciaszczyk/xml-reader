package uploadxml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "Product")
public class Product {

    private int id;
    private String name;
    private String category;
    private String partNumberNR;
    private String companyName;
    private boolean active;

    public Product(int id, String name, String category, String partNumberNR, String companyName, boolean active) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.partNumberNR = partNumberNR;
        this.companyName = companyName;
        this.active = active;
    }

    public Product() {}  // for JAXB

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "Category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @XmlElement(name = "PartNumberNR")
    public String getPartNumberNR() {
        return partNumberNR;
    }

    public void setPartNumberNR(String partNumberNR) {
        this.partNumberNR = partNumberNR;
    }

    @XmlElement(name = "CompanyName")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @XmlElement(name = "Active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && active == product.active && Objects.equals(name, product.name) && Objects.equals(category, product.category) && Objects.equals(partNumberNR, product.partNumberNR) && Objects.equals(companyName, product.companyName);
    }
}