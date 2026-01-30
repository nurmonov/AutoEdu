package org.example.autoedu.entity.composite;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolUserId implements Serializable {
    private Integer schoolId;
    private Integer userId;
}