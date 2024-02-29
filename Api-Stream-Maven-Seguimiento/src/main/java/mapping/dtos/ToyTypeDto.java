package mapping.dtos;

import domain.enums.ToyType;

public record ToyTypeDto(long nameId, String name, ToyType type, double price, int quantityInStock) {
    public String getName() {
        return this.name;
    }

    public ToyType getType() {
        return this.type;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantityInStock() {
        return this.quantityInStock;
    }
}
