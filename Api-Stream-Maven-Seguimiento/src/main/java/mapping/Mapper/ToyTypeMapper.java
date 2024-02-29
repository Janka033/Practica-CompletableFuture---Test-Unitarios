package mapping.Mapper;

import domain.models.ToyTypes;
import mapping.dtos.ToyTypeDto;

import java.util.List;

public class ToyTypeMapper {
    public static ToyTypeDto mapFrom(ToyTypes source){
        return new ToyTypeDto(source.getNameId(),
                source.getName(),source.getType(), source.getPrice(), source.getQuantityInStock());
    }
    public static ToyTypes mapFrom(ToyTypeDto source){
        return new ToyTypes(source.nameId(), source.name(),source.type(), source.price(), source.quantityInStock());
    }
    public static List<ToyTypes> mapFrom(List<ToyTypeDto> source){
        return  source.parallelStream()
                .map(ToyTypeMapper::mapFrom).toList();
    }
    public static List<ToyTypeDto> mapFromDto(List<ToyTypes> source){
        return source.parallelStream()
                .map(ToyTypeMapper::mapFrom)
                .toList();
    }
}
