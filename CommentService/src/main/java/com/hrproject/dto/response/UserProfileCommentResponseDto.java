package com.hrproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserProfileCommentResponseDto {
    private Long userId;
    private String name;
    private String surname;
    private Long companyId;
}
