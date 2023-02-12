package Producto.ProductoBack.model;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class ProductDTO {
    private String id;
    @NotBlank
    private String name;
    private Integer inInventory;
    private boolean enabled;
    private Integer min;
    private Integer max;
    private String img;
    private boolean state;
    private Long price;

    public ProductDTO() {
    }

    public ProductDTO(String id, String name, Integer inInventory, boolean enabled, Integer min, Integer max, String img, boolean state, Long price) {
        this.id = id;
        this.name = name;
        this.inInventory = inInventory;
        this.enabled = enabled;
        this.min = min;
        this.max = max;
        this.img = img;
        this.state = state;
        this.price = price;
    }
    public ProductDTO(String name, Integer inInventory, boolean enabled, Integer min, Integer max, String img, boolean state, Long price) {
        this.name = name;
        this.inInventory = inInventory;
        this.enabled = enabled;
        this.min = min;
        this.max = max;
        this.img = img;
        this.state = state;
        this.price = price;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInInventory() {
        return inInventory;
    }

    public void setInInventory(Integer inInventory) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO productDTO = (ProductDTO) o;
        return Objects.equals(id, productDTO.id);
    }

    @Override
    public int hashCode() {
       return  Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", inInventory=" + inInventory +
                ", enabled=" + enabled +
                ", min=" + min +
                ", max=" + max +
                ", img='" + img + '\'' +
                ", state=" + state +
                ", price=" + price +
                '}';
    }
}
