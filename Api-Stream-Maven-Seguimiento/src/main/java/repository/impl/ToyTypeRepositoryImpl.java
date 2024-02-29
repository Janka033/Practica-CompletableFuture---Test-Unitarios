package repository.impl;

import domain.enums.ToyType;
import domain.models.ToyTypes;
import mapping.dtos.ToyTypeDto;
import mapping.Mapper.ToyTypeMapper;
import repository.ToyTypeRepository;
import repository.exceptions.NoSuchElementException;
import repository.exceptions.ReportToyException;
import repository.exceptions.ToyIddException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ToyTypeRepositoryImpl implements ToyTypeRepository {

    private List<ToyTypes> toyTypes = new ArrayList<>();

    @Override
    public List<ToyTypeDto> getAllToys() {
        return ToyTypeMapper.mapFromDto(toyTypes);
    }
        @Override
        public void addToy(ToyTypes toy) {
            try {
                if (toy == null) {
                    throw new ToyIddException("The toy cannot be null.");
                }
                toyTypes.add(toy);
            } catch (ToyIddException e) {
                System.err.println(" Error adding toy:" + e.getMessage());
            }
        }

        @Override
        public void reportToysByType() {
            System.out.println("number of toys for type:");
            try {
                if (toyTypes.isEmpty()) {
                    throw new ReportToyException("The toy list is empty.");
                }
                toyTypes.stream()
                        .collect(Collectors.groupingBy(ToyTypes::getType, Collectors.counting()))
                        .forEach((type, count) -> System.out.println(type + ": " + count));
                System.out.println();
            } catch (ReportToyException e) {
                System.out.println("Error generating the toys by type report: " + e.getMessage());
            }
        }

        @Override
        public int getTotalToys() {
            return toyTypes.size();
        }

        @Override
        public double getTotalValue() {
            return toyTypes.stream()
                    .mapToDouble(toy -> toy.getPrice() * toy.getQuantityInStock())
                    .sum();
        }

        @Override
        public void decreaseStock(long nameId, int quantity) {
            try {
                ToyTypes toy = getToyByNameId(nameId);
                if (toy == null) {
                    throw new NoSuchElementException(": " + nameId);
                }
                int newQuantity = Math.max(0, toy.getQuantityInStock() - quantity);
                toy.setQuantityInStock(newQuantity);
            } catch (NoSuchElementException e) {
                System.out.println("The toy with the ID was not found: " + e.getMessage());
            }
        }

        @Override
        public void increaseStock(long nameId, int quantity) {
            try {
                ToyTypes toy = getToyByNameId(nameId);
                if (toy == null) {
                    throw new NoSuchElementException("The toy with the ID was not found: " + nameId);
                } else if (nameId<0) {
                    throw new NoSuchElementException("The id you entered does not exist");
                } else if (quantity<0) {
                    throw new NoSuchElementException("the quantity must be greater than zero.");

                }

                int newQuantity = toy.getQuantityInStock() + quantity;
                toy.setQuantityInStock(newQuantity);
            } catch (NoSuchElementException e) {
                System.err.println("Error when increasing toy stock: " + e.getMessage());
            }
        }

        @Override
        public ToyType getMostCommonToyType() {
            return toyTypes.stream()
                    .collect(Collectors.groupingBy(ToyTypes::getType, Collectors.counting()))
                    .entrySet().stream()
                    .max(Comparator.comparingLong(java.util.Map.Entry::getValue))
                    .map(java.util.Map.Entry::getKey)
                    .orElse(null);
        }

        @Override
        public ToyType getLeastCommonToyType() {
            return toyTypes.stream()
                    .collect(Collectors.groupingBy(ToyTypes::getType, Collectors.counting()))
                    .entrySet().stream()
                    .min(Comparator.comparingLong(java.util.Map.Entry::getValue))
                    .map(java.util.Map.Entry::getKey)
                    .orElse(null);
        }

        @Override
        public CompletableFuture<List<ToyTypeDto>> getToysWithValueGreaterThan(double minValue) {
            return CompletableFuture.supplyAsync(() -> toyTypes.stream()
                    .filter(toy -> toy.getPrice() > minValue)
                    .map(ToyTypeMapper::mapFrom)
                    .collect(Collectors.toList()));
        }

        @Override
        public CompletableFuture<List<ToyTypeDto>> getToysSortedByStock() {
            return CompletableFuture.supplyAsync(()->toyTypes.stream()
                    .sorted(Comparator.comparingInt(ToyTypes::getQuantityInStock))
                    .map(ToyTypeMapper::mapFrom)
                    .collect(Collectors.toList()));
        }


        private ToyTypes getToyByNameId(long nameId) {
            return toyTypes.stream()
                    .filter(toy -> toy.getNameId() == nameId)
                    .findFirst()
                    .orElse(null);
        }
    }
