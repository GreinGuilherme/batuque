package com.api.batuque.adpater.input.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RefreshTokenRequest {
    @NotBlank
    private String refreshToken;
}
