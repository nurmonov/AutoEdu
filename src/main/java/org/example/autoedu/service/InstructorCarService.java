package org.example.autoedu.service;


import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.instructorCar.InstructorCarRequest;
import org.example.autoedu.dto.instructorCar.InstructorCarResponse;
import org.example.autoedu.dto.instructorCar.InstructorCarUpdateRequest;
import org.example.autoedu.entity.InstructorCar;
import org.example.autoedu.repo.InstructorCarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InstructorCarService {

    private final InstructorCarRepository instructorCarRepository;

    @Transactional(readOnly = true)
    public List<InstructorCarResponse> getAllInstructorCars() {
        return instructorCarRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public InstructorCarResponse getInstructorCarById(Integer id) {
        InstructorCar car = instructorCarRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Mashina topilmadi: " + id));
        return toResponse(car);
    }

    @Transactional
    public InstructorCarResponse createInstructorCar(InstructorCarRequest request) {
        InstructorCar car = InstructorCar.builder()
                .fullName(request.getFullName())
                .photo(request.getPhoto())
                .number(request.getNumber())
            .instruktorId(request.getInstructorId())
                .build();

        InstructorCar saved = instructorCarRepository.save(car);
        return toResponse(saved);
    }

    @Transactional
    public InstructorCarResponse updateInstructorCar(Integer id, InstructorCarUpdateRequest request) {
        InstructorCar car = instructorCarRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Mashina topilmadi: " + id));

        if (request.getFullName() != null) car.setFullName(request.getFullName());
        if (request.getPhoto() != null) car.setPhoto(request.getPhoto());
        if (request.getNumber() != null) car.setNumber(request.getNumber());

        InstructorCar updated = instructorCarRepository.save(car);
        return toResponse(updated);
    }

    @Transactional
    public void deleteInstructorCar(Integer id) {
        if (!instructorCarRepository.existsById(id)) {
            throw new NoSuchElementException("Mashina topilmadi: " + id);
        }
        instructorCarRepository.deleteById(id);
    }

    private InstructorCarResponse toResponse(InstructorCar entity) {
        return InstructorCarResponse.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .photo(entity.getPhoto())
                .number(entity.getNumber())
               .instructorId(entity.getInstruktorId())
                .build();
    }
}
