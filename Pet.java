import java.time.LocalDate;
import java.util.List;

public class Pet extends Animal {
    public Pet(String name, LocalDate birthDate, List<String> commands) {
        super(name, birthDate, commands);
    }
}
//// проверка для ветки