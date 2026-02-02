package org.example.autoedu.service;



import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.user.UserCreateRequest;
import org.example.autoedu.dto.user.UserResponse;
import org.example.autoedu.dto.user.UserUpdateRequest;
import org.example.autoedu.entity.User;
import org.example.autoedu.entity.enums.Role;
import org.example.autoedu.mapper.UserMapper;
import org.example.autoedu.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userMapper.toResponseList(userRepository.findAll());
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Foydalanuvchi topilmadi: " + id));
        return userMapper.toResponse(user);
    }

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new IllegalArgumentException("Bu telefon raqami allaqachon ro'yxatdan o'tgan");
        }

        User user = userMapper.toEntity(request);

        // Parolni hash qilish — majburiy!
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // default role, masalan
        if (user.getRole() == null) {
            user.setRole(Role.STUDENT);
        }

        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Transactional
    public UserResponse updateUser(Integer id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Foydalanuvchi topilmadi: " + id));

        // Qo'lda update qilamiz — faqat null bo'lmagan maydonlarni o'zgartiramiz
        if (request.getFullName() != null && !request.getFullName().isBlank()) {
            user.setFullName(request.getFullName());
        }

        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isBlank()) {
            // telefon o'zgartirilsa, uniqueness ni tekshirish mumkin (ixtiyoriy)
            if (!request.getPhoneNumber().equals(user.getPhoneNumber()) &&
                    userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
                throw new IllegalArgumentException("Bu telefon raqami allaqachon ishlatilmoqda");
            }
            user.setPhoneNumber(request.getPhoneNumber());
        }

        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            if (!request.getEmail().equals(user.getEmail()) &&
                    userRepository.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("Bu email allaqachon ishlatilmoqda");
            }
            user.setEmail(request.getEmail());
        }

        if (request.getPhoto() != null) {
            user.setPhoto(request.getPhoto());
        }

        if (request.getPassportPhoto() != null) {
            user.setPassportPhoto(request.getPassportPhoto());
        }

        if (request.getSeriaRaqam() != null) {
            user.setSeriaRaqam(request.getSeriaRaqam());
        }

        // Yangi parol kiritilgan bo'lsa — hash qilamiz
        if (request.getNewPassword() != null && !request.getNewPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }

        // Role va statusni o'zgartirishni cheklash mumkin (masalan faqat admin o'zgartirsin)
        // Agar kerak bo'lsa shu joyga qo'shing:
        // if (request.getRole() != null) { user.setRole(request.getRole()); }
        // if (request.getStatus() != null) { user.setStatus(request.getStatus()); }

        User updated = userRepository.save(user);
        return userMapper.toResponse(updated);  // response uchun mapperdan foydalanamiz
    }

    @Transactional
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("Foydalanuvchi topilmadi: " + id);
        }
        userRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public UserResponse getUserByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new NoSuchElementException("Foydalanuvchi topilmadi: " + phoneNumber));
        return userMapper.toResponse(user);
    }
}
