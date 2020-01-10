package com.l.library.auth.authorization.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.l.library.manangement.common.core.entity.vo.Result;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
@Getter
public class CustomOauthException extends OAuth2Exception {
    private final Result result;

    CustomOauthException(OAuth2Exception oAuth2Exception) {
        super(oAuth2Exception.getSummary(), oAuth2Exception);
        this.result = Result.fail(AuthErrorType.valueOf(oAuth2Exception.getOAuth2ErrorCode().toUpperCase()), oAuth2Exception);
    }
}
