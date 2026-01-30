package org.example.autoedu.mapper;

import org.example.autoedu.dto.school.SchoolCreateRequest;
import org.example.autoedu.dto.school.SchoolResponse;
import org.example.autoedu.dto.school.SchoolUpdateRequest;
import org.example.autoedu.entity.School;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring")
public interface SchoolMapper {

    @Mapping(target = "id", ignore = true)
    School toEntity(SchoolCreateRequest dto);

    SchoolResponse toResponse(School entity);

    List<SchoolResponse> toResponseList(List<School> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget School entity, SchoolUpdateRequest dto);
}
