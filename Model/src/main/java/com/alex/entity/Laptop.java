package com.alex.entity;

import com.alex.converter.DefaultConverter;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "laptop")
@NamedQueries({
        @NamedQuery(name="Laptop.findAllAvailable", query="select t "
                + "from Laptop t where t.status = ?1 ORDER BY t.id ASC"),
})
public class Laptop{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView(View.Summary.class)
    private Long id;


    @Column(name = "ram", nullable = false)
    @NotNull(message = "You must provide a RAM")
    @Digits(fraction = 0, integer = 5, message ="You must provide only digit")
    @JsonView(View.Summary.class)
    private Integer ram;

    @Column(name = "cpu", nullable = false)
    @NotBlank(message = "You must provide a CPU")
    @JsonView(View.Summary.class)
    private String cpu;

    @Column(name = "screen", nullable = false)
    @NotBlank(message = "You must provide a screen")
    @JsonView(View.Summary.class)
    private String screen;

    @Column(name = "image", nullable = false)
    @Convert(converter = DefaultConverter.class)
    @JsonView(View.Summary.class)
    private String image;

    @Column(name = "price", nullable = false)
    @NotNull(message = "You must provide a price")
    @Digits(fraction = 0, integer = 10, message = "You must provide only digit")
    @JsonView(View.Summary.class)
    private Integer price;

    @Column(name = "status", nullable = false)
    @JsonView(View.Summary.class)
    private boolean status = true;

    @Column(name = "quantity", nullable = false)
    @NotNull(message = "You must provide a quantity")
    @Digits(fraction = 0, integer = 10, message ="You must provide only digit")
    @JsonView(View.Summary.class)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_manufacturer")
    @JsonView(View.Summary.class)
    private Manufacturer manufacturer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Laptop laptop = (Laptop) o;

        if (status != laptop.status) return false;
        if (id != null ? !id.equals(laptop.id) : laptop.id != null) return false;
        if (ram != null ? !ram.equals(laptop.ram) : laptop.ram != null) return false;
        if (cpu != null ? !cpu.equals(laptop.cpu) : laptop.cpu != null) return false;
        if (screen != null ? !screen.equals(laptop.screen) : laptop.screen != null) return false;
        if (image != null ? !image.equals(laptop.image) : laptop.image != null) return false;
        if (price != null ? !price.equals(laptop.price) : laptop.price != null) return false;
        return quantity != null ? quantity.equals(laptop.quantity) : laptop.quantity == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ram != null ? ram.hashCode() : 0);
        result = 31 * result + (cpu != null ? cpu.hashCode() : 0);
        result = 31 * result + (screen != null ? screen.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }

    public Laptop() {
    }

    public Laptop(Integer ram, String cpu, String screen,
                  String image, Integer price, boolean status,
                  Integer quantity, Manufacturer manufacturer) {
        this.ram = ram;
        this.cpu = cpu;
        this.screen = screen;
        this.image = image;
        this.price = price;
        this.status = status;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", ram=" + ram +
                ", cpu='" + cpu + '\'' +
                ", screen='" + screen + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", quantity=" + quantity +
                ", manufacturer=" + manufacturer +
                '}';
    }
}


