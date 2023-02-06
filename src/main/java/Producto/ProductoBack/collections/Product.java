package Producto.ProductoBack.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Product")
public class Product {
    @Id
    private String id;
    private String name;
    private boolean inInventory;
    private Integer min;
    private Integer max;
    private String img;
    private boolean state;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isInInventory() {
        return inInventory;
    }
    public void setInInventory(boolean inInventory) {
        this.inInventory = inInventory;
    }
    public Integer getMin() {
        return min;
    }
    public void setMin(Integer min) {
        this.min = min;
    }
    public Integer getMax() {
        return max;
    }
    public void setMax(Integer max) {
        this.max = max;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
