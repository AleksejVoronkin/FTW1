import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
    private String name;
    private LocalDate birthDate;
    private List<String> commands;

    public Animal(String name, LocalDate birthDate, List<String> commands) {
        this.name = name;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>(commands);
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void teachCommand(String command) {
        if (!commands.contains(command)) {
            commands.add(command);
        }
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", commands=" + commands +
                '}';
    }
}
