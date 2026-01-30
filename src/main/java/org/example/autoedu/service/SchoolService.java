package org.example.autoedu.service;


import lombok.RequiredArgsConstructor;

import org.example.autoedu.dto.school.SchoolCreateRequest;
import org.example.autoedu.dto.school.SchoolResponse;
import org.example.autoedu.dto.school.SchoolUpdateRequest;
import org.example.autoedu.entity.School;
import org.example.autoedu.mapper.SchoolMapper;
import org.example.autoedu.repo.SchoolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    @Transactional(readOnly = true)
    public List<SchoolResponse> getAllSchools() {
        return schoolMapper.toResponseList(schoolRepository.findAll());
    }

    @Transactional(readOnly = true)
    public SchoolResponse getSchoolById(Integer id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Maktab topilmadi: " + id));
        return schoolMapper.toResponse(school);
    }

    @Transactional
    public SchoolResponse createSchool(SchoolCreateRequest request) {
        // telefon raqami mavjudligini tekshirish (masalan)
        if (request.getPhoneNumber() != null &&
                schoolRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new IllegalArgumentException("Bu telefon raqami bilan maktab allaqachon mavjud");
        }

        School school = schoolMapper.toEntity(request);
        School saved = schoolRepository.save(school);
        return schoolMapper.toResponse(saved);
    }

    @Transactional
    public SchoolResponse updateSchool(Integer id, SchoolUpdateRequest request) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Maktab topilmadi: " + id));

        schoolMapper.updateEntity(school, request);

        School updated = schoolRepository.save(school);
        return schoolMapper.toResponse(updated);
    }

    @Transactional
    public void deleteSchool(Integer id) {
        if (!schoolRepository.existsById(id)) {
            throw new NoSuchElementException("Maktab topilmadi: " + id);
        }
        schoolRepository.deleteById(id);
    }

    // qo'shimcha misol metod (agar kerak bo'lsa)
    @Transactional(readOnly = true)
    public List<SchoolResponse> findActiveSchools() {
        return schoolMapper.toResponseList(schoolRepository.findByStatus("active"));
    }
}