package demo.homework;

import org.springframework.stereotype.Component;

@Component
public class NameProvider {
    static final String NAME = "Maksim";

    public String getName() {
        return NAME;
    }
}
