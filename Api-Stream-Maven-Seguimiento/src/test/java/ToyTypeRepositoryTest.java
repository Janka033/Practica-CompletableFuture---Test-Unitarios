import domain.enums.ToyType;
import domain.models.ToyTypes;
import org.junit.jupiter.api.Test;
import repository.ToyTypeRepository;
import repository.impl.ToyTypeRepositoryImpl;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToyTypeRepositoryTest {
        @Test
        void testGetAllToys() {
            ToyTypeRepository toyRepository = new ToyTypeRepositoryImpl();
            assertEquals(6, toyRepository.getAllToys().size());
        }

        @Test
        void testAddToy() {
            ToyTypeRepository toyRepository = new ToyTypeRepositoryImpl();
            ToyTypes newToy = new ToyTypes(7, "Remote Control Car", ToyType.MALE, 25.99, 10);
            toyRepository.addToy(newToy);
            assertEquals(7, toyRepository.getAllToys().size());
        }

        @Test
        void testDecreaseStock() {
            ToyTypeRepository toyRepository = new ToyTypeRepositoryImpl();
            toyRepository.decreaseStock(1, 5);
            assertEquals(15, toyRepository.getAllToys().get(0).quantityInStock());
        }

        @Test
        void testIncreaseStock() {
            ToyTypeRepository toyRepository = new ToyTypeRepositoryImpl();
            toyRepository.increaseStock(2, 3);
            assertEquals(23, toyRepository.getAllToys().get(1).getQuantityInStock());
        }
    }

