package com.example.rest_api_test.domain.member.entity;

import com.example.rest_api_test.global.jpa.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Member extends BaseEntity {
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String refreshToken;
}
