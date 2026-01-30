package org.example.autoedu.mapper;

import org.example.autoedu.dto.instructorCar.InstructorCarRequest;
import org.example.autoedu.dto.instructorCar.InstructorCarResponse;
import org.example.autoedu.entity.InstructorCar;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstructorCarMapper {

    @Mapping(target = "id", ignore = true)
    InstructorCar toEntity(InstructorCarRequest dto);

    InstructorCarResponse toResponse(InstructorCar entity);
    List<InstructorCarResponse> toResponseList(List<InstructorCar> entities);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "instructor", ignore = true)
//    void updateEntity(@MappingTarget InstructorCar entity, InstructorCarUpdateRequest dto);
}
