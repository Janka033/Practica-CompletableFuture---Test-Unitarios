package repository;

import domain.enums.ToyType;
import domain.models.ToyTypes;
import mapping.dtos.ToyTypeDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ToyTypeRepository {
    List<ToyTypeDto> getAllToys();
    void addToy(ToyTypes toy);

    void reportToysByType();

    int getTotalToys();

    double getTotalValue();

    void decreaseStock(long nameId, int quantity);

    void increaseStock(long nameId, int quantity);

    ToyType getMostCommonToyType();

    ToyType getLeastCommonToyType();

    CompletableFuture<List<ToyTypeDto>> getToysWithValueGreaterThan(double minValue);

    CompletableFuture<List<ToyTypeDto>> getToysSortedByStock();
}
