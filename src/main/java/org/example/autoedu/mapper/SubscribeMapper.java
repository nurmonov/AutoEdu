package org.example.autoedu.mapper;

import org.example.autoedu.dto.subscription.SubscribeCreateRequest;
import org.example.autoedu.dto.subscription.SubscribeResponse;
import org.example.autoedu.dto.subscription.SubscribeUpdateRequest;
import org.example.autoedu.entity.Subscribe;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscribeMapper {

    @Mapping(target = "id", ignore = true)
    Subscribe toEntity(SubscribeCreateRequest dto);

    SubscribeResponse toResponse(Subscribe entity);
    List<SubscribeResponse> toResponseList(List<Subscribe> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Subscribe entity, SubscribeUpdateRequest dto);
}
