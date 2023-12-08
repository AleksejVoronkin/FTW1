import java.time.LocalDate;
import java.util.List;

public class PackAnimals extends Animal {
    public PackAnimals(String name, LocalDate birthDate, List<String> commands) {
        super(name, birthDate, commands);
    }
    public String getCategory() {
        return "PackAnimals";
    }
}
