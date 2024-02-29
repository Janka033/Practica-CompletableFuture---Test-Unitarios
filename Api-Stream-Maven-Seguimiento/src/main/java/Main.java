import domain.enums.ToyType;
import domain.models.ToyTypes;
import mapping.dtos.ToyTypeDto;
import mapping.Mapper.ToyTypeMapper;
import repository.ToyTypeRepository;
import repository.impl.ToyTypeRepositoryImpl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
        public static void main(String[] args) {
            ToyTypeRepository toyRepository = new ToyTypeRepositoryImpl();
            toyRepository.addToy(new ToyTypes(1,"Vegetta", ToyType.MALE,2.70,20));
            toyRepository.addToy(new ToyTypes(2,"Car", ToyType.MALE,1.70,20));
            toyRepository.addToy(new ToyTypes(3,"Make-up", ToyType.FEMALE,3.70,20));
            toyRepository.addToy(new ToyTypes(4,"Water gun", ToyType.UNISEX,3.20,20));
            toyRepository.addToy(new ToyTypes(5,"Barbie", ToyType.FEMALE,1.50,20));
            toyRepository.addToy(new ToyTypes(6,"musical piano", ToyType.UNISEX,3.0,20));
            List<ToyTypeDto> allToys = toyRepository.getAllToys();
            System.out.println("All the toys");
            allToys.forEach(System.out::println );
            System.out.println();

            ToyTypes newToy = ToyTypeMapper.mapFrom(new ToyTypeDto(7, "Remote Control Car", ToyType.MALE, 25.99, 10));
            toyRepository.addToy(newToy);
            System.out.println("newToy = " + newToy);
            System.out.println();

            toyRepository.reportToysByType();

            int totalToys = toyRepository.getTotalToys();
            System.out.println("total number of toys: " + totalToys);
            System.out.println();

            double totalValue = toyRepository.getTotalValue();
            System.out.println("Total values of toys: " + totalValue);
            System.out.println();

            toyRepository.decreaseStock(1, 0);

            toyRepository.increaseStock(2, 3);

            ToyType mostCommonType = toyRepository.getMostCommonToyType();
            System.out.println("best selling toys: " + mostCommonType);
            System.out.println();

            ToyType leastCommonType = toyRepository.getLeastCommonToyType();
            System.out.println("low selling toys: " + leastCommonType);
            System.out.println();

            double minValue = 2.0;
            CompletableFuture<List<ToyTypeDto>> toysWithValueGreaterThanMin = toyRepository.getToysWithValueGreaterThan(minValue);
            CompletableFuture<List<ToyTypeDto>> sortedToysByStock = toyRepository.getToysSortedByStock();
            CompletableFuture.allOf(toysWithValueGreaterThanMin,sortedToysByStock).join();

            System.out.println("toys with more value of " + minValue + ":" + toysWithValueGreaterThanMin.join());
            System.out.println();

            System.out.println("Toys sorted by stock quantity:" + sortedToysByStock.join());


        }
    }

