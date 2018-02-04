package io.spldeolin.bestpractice.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class IdNameInput {

    private Long id;

    private String name;

    public IdNameInput(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
