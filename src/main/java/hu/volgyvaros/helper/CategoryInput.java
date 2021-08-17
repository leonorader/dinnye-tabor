package hu.volgyvaros.helper;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CategoryInput {

    private String name;

    private String description;

    private String icon;

    private String colour;

}
