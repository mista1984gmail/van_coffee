package com.mista.soft.domain;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class Van implements Serializable {

    private static final long serialVersionUID = 1L;

    String nameVan;

    @NonNull
    int id;

    List<Coffee> coffeeList;


}
