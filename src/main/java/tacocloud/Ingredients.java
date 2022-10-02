package tacocloud;

import lombok.Data;

@Data
public class Ingredients {
    private final String id;
    private final String name;
    private final Type type;
}
