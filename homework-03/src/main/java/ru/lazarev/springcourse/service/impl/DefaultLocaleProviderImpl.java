package ru.lazarev.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.lazarev.springcourse.service.LocaleProvider;

import java.util.Locale;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DefaultLocaleProviderImpl implements LocaleProvider {

    Locale localeTag;

    public DefaultLocaleProviderImpl(@Value("${io.locale}") String localeTag) {
        this.localeTag = Locale.forLanguageTag(localeTag);
    }

    @Override
    public Locale getCurrent() {
        return localeTag;
    }
}
