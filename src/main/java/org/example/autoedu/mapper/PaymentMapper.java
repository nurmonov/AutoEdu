//package org.example.autoedu.mapper;
//
//import org.example.autoedu.dto.payment.PaymentCreateRequest;
//import org.example.autoedu.dto.payment.PaymentResponse;
//import org.example.autoedu.entity.Payment;
//import org.mapstruct.*;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface PaymentMapper {
//
//    @Mapping(target = "id", ignore = true)
//    Payment toEntity(PaymentCreateRequest dto);
//
//    PaymentResponse toResponse(Payment entity);
//    List<PaymentResponse> toResponseList(List<Payment> entities);
//
////    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
////    @Mapping(target = "id", ignore = true)
////    @Mapping(target = "user", ignore = true)
////    @Mapping(target = "createdAt", ignore = true)
////    @Mapping(target = "confirmedAt", ignore = true)
////    void updateEntity(@MappingTarget Payment entity, PaymentUpdateRequest dto);
//}
