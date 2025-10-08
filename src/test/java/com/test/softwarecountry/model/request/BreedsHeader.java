package com.test.softwarecountry.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BreedsHeader {

    @Builder.Default
    private int limit = 2;
}
