package org.example.autoedu.entity;


import jakarta.persistence.*;
import lombok.*;
import org.example.autoedu.entity.composite.SchoolUserId;

import java.io.Serializable;

@Entity
@Table(name = "school_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolUser {

    @EmbeddedId
    private SchoolUserId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("schoolId")
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
}


