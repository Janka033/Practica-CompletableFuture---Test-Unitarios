package domain.models;

import domain.enums.ToyType;

public class ToyTypes {
    private long nameId;
    private String name;
    private ToyType type;
    private double price;
    private int quantityInStock;

    public ToyTypes(long nameId,String name, ToyType type, double price, int quantityInStock) {
       this.nameId = nameId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ToyType getType() {
        return type;
    }

    public void setType(ToyType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public long getNameId() {
        return nameId;
    }

    public void setNameId(long nameId) {
        this.nameId = nameId;
    }

    @Override
    public String toString() {
        return "ToyTypes{" +
                "nameId=" + nameId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", quantityInStock=" + quantityInStock +
                '}';
    }
}
