import java.time.LocalDate;
import java.util.*;

public class AnimalRegistry {
    private Map<String, Animal> animalMap = new TreeMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void addAnimal(Counter counter) {
        System.out.println("Введите тип животного (Dog, Cat, Hamster, Camel, Donkey, Horse):");
        String type = scanner.nextLine();
        System.out.println("Введите имя животного:");
        String name = scanner.nextLine();
        System.out.println("Введите дату рождения животного (гггг-мм-дд):");
        String birthDateString = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(birthDateString);
        System.out.println("Введите команды животного:");
        String commandsString = scanner.nextLine();
        List<String> commands = Arrays.asList(commandsString.split(","));

        Animal animal = createAnimal(type, name, birthDate, commands);
        if (animal != null) {
            animalMap.put(name, animal);
            counter.add();
            System.out.println(type + " с именем " + name + " зарегистрирован.");
        } else {
            System.out.println("Неправильный тип животного.");
        }
    }

    private Animal createAnimal(String type, String name, LocalDate birthDate, List<String> commands) {
        switch (type.toLowerCase()) {
            case "dog": return new Dog(name, birthDate, commands);
            case "cat": return new Cat(name, birthDate, commands);
            case "hamster": return new Hamster(name, birthDate, commands);
            case "horse": return new Horse(name, birthDate, commands);
            case "camel": return new Camel(name, birthDate, commands);
            case "donkey": return new Donkey(name, birthDate, commands);
            default: return null;
        }
    }

    public void displayAnimalCommands() {
        System.out.println("Введите имя животного чьи команды хотите увидеть");
        String name = scanner.nextLine();
        Animal animal = animalMap.get(name);
        if (animal != null) {
            System.out.println("Команды для " + name + ": " + animal.getCommands());
        } else {
            System.out.println("Животное не найдено.");
        }
    }

    public void teachAnimalCommand() {
        System.out.println("Введите имя животного которого хотите обучить командам:");
        String name = scanner.nextLine();
        System.out.println("Введите новую команду");
        String command = scanner.nextLine();
        Animal animal = animalMap.get(name);
        if (animal != null) {
            animal.teachCommand(command);
            System.out.println("Добавлена новая команда для " + name + ".");
        } else {
            System.out.println("Животное не найдено.");
        }
    }

    public void displayAnimalsByBirthDate() {
        List<Animal> sortedAnimals = new ArrayList<>(animalMap.values());
        sortedAnimals.sort(Comparator.comparing(Animal::getBirthDate));

        System.out.println("Сортировка животных по дате рождения:");
        for (Animal animal : sortedAnimals) {
            System.out.println(animal.getName() + " - " + animal.getBirthDate());
        }
    }

    public void listAllAnimals() {
        if (animalMap.isEmpty()) {
            System.out.println("Животные не зарегистрированы.");
            return;
        }

        System.out.println("Список зарегистрированных животных");
            for (Map.Entry<String, Animal> entry : animalMap.entrySet()) {
                Animal animal = entry.getValue();
                String category = "Unknown";
            if (animal instanceof Pet) {
                category = ((Pet) animal).getCategory();
            } else if (animal instanceof PackAnimals) {
                category = ((PackAnimals) animal).getCategory();
            }
        System.out.println(entry.getKey() + " - " + category + " (" + animal.getClass().getSimpleName() + ")");
        }   
    }

    public void startMenu() {
        try (Counter counter = new Counter()) {
            String option;
            do {
                System.out.println("\nМеню управления реестром животных:");
                System.out.println("1 - Завести новое животное");
                System.out.println("2 - Вывести список команд животного");
                System.out.println("3 - Обучить животное новой команде");
                System.out.println("4 - Вывести список животных по дате рождения");
                System.out.println("5 - Вывести всех животных");
                System.out.println("0 - Выход");
                System.out.print("Выберите опцию: ");
                option = scanner.nextLine();
                switch (option) {
                    case "1":
                        addAnimal(counter);
                        break;
                    case "2":
                        displayAnimalCommands();
                        break;
                    case "3":
                        teachAnimalCommand();
                        break;
                    case "4":
                        displayAnimalsByBirthDate();
                        break;
                    case "5":
                        listAllAnimals();
                        break;
                    case "0":
                        System.out.println("Выход");
                        break;
                    default:
                        System.out.println("Неверный вариант.");
                        break;
                }
            } while (!option.equals("0"));

            System.out.println("Всего было зарегистрированно: " + counter.getCount());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
