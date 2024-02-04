package ru.lazarev.springcourse.library.config;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.lazarev.springcourse.library.dto.RoleDto;
import ru.lazarev.springcourse.library.dto.TokenVerifyRequest;
import ru.lazarev.springcourse.library.dto.UserProfileDto;
import ru.lazarev.springcourse.library.enums.UserRoleType;
import ru.lazarev.springcourse.library.feign.AuthServiceProxy;
import ru.lazarev.springcourse.library.mapper.UserProfileMapper;
import ru.lazarev.springcourse.library.model.UserProfile;

import java.util.List;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class UserProfileArgumentResolver implements HandlerMethodArgumentResolver {
    public static final String AUTHORIZATION = "Authorization";

    UserProfileMapper userProfileMapper;
    AuthServiceProxy authServiceProxy;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return UserProfile.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        try {
            var userProfile = authServiceProxy.getUserByToken(TokenVerifyRequest.builder()
                                                                  .token(getJwtToken(webRequest).replace("Bearer ", ""))
                                                                  .build());
            return userProfileMapper.map(userProfile);
        } catch (Exception e) {
            return getGuest();
        }
    }

    private UserProfile getGuest() {
        return new UserProfile(null, "guest", null, List.of(new RoleDto(UserRoleType.ROLE_GUEST)));
    }

    private String getJwtToken(NativeWebRequest webRequest) {
        try {
            return Objects.requireNonNull(webRequest.getHeader(AUTHORIZATION));
        } catch (NullPointerException exception) {
            throw new RuntimeException("Missing auth token in header");
        }
    }
}
