package Producto.ProductoBack.model;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class ProductDTO {
    private String id;
    @NotBlank
    private String name;
    private boolean inInventory;
    private Integer min;
    private Integer max;
    private String img;
    private boolean state;

    public ProductDTO(String id, String name, boolean inInventory, Integer min, Integer max, String img, boolean state) {
        this.id = id;
        this.name = name;
        this.inInventory = inInventory;
        this.min = min;
        this.max = max;
        this.img = img;
        this.state = state;
    }

    public ProductDTO(String name, boolean inInventory, Integer min, Integer max, String img, boolean state) {
        this.name = name;
        this.inInventory = inInventory;
        this.min = min;
        this.max = max;
        this.img = img;
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
                ", min=" + min +
                ", max=" + max +
                ", img='" + img + '\'' +
                ", state=" + state +
                '}';
    }
}
