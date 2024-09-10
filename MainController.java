import java.util.*;

// Класс StudyGroup, представляющий учебную группу
class StudyGroup {
    private String groupName; // Название группы
    private int numberOfStudents; // Количество студентов
    private String course; // Название курса

    // Конструктор
    public StudyGroup(String groupName, int numberOfStudents, String course) {
        this.groupName = groupName;
        this.numberOfStudents = numberOfStudents;
        this.course = course;
    }

    // Геттеры
    public String getGroupName() {
        return groupName;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "Группа: " + groupName + ", Студенты: " + numberOfStudents + ", Курс: " + course;
    }
}

// Класс Stream, содержащий список учебных групп и реализующий Iterable
class Stream implements Iterable<StudyGroup> {
    private List<StudyGroup> studyGroups;

    public Stream(List<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }

    public List<StudyGroup> getStudyGroups() {
        return studyGroups;
    }

    @Override
    public Iterator<StudyGroup> iterator() {
        return studyGroups.iterator();
    }
}

// Класс StreamComparator, реализующий сравнение количества групп в потоке
class StreamComparator implements Comparator<Stream> {
    @Override
    public int compare(Stream stream1, Stream stream2) {
        return Integer.compare(stream1.getStudyGroups().size(), stream2.getStudyGroups().size());
    }
}

// Класс StreamService, добавляющий метод для сортировки списка потоков
class StreamService {
    public void sortStreams(List<Stream> streams) {
        Collections.sort(streams, new StreamComparator());
    }
}

// Класс Controller, использующий StreamService для сортировки потоков
class Controller {
    private StreamService streamService;

    public Controller() {
        this.streamService = new StreamService();
    }

    public void sortStreams(List<Stream> streams) {
        streamService.sortStreams(streams);
    }
}

// Главный класс проекта
public class MainController {
    public static void main(String[] args) {
        // Создание нескольких учебных групп
        StudyGroup group1 = new StudyGroup("Группа A", 25, "Математика");
        StudyGroup group2 = new StudyGroup("Группа B", 30, "Физика");
        StudyGroup group3 = new StudyGroup("Группа C", 20, "Химия");

        // Создание потоков
        Stream stream1 = new Stream(Arrays.asList(group1, group2));
        Stream stream2 = new Stream(Arrays.asList(group3));
        Stream stream3 = new Stream(Arrays.asList(group1, group2, group3));

        // Добавление потоков в список
        List<Stream> streams = new ArrayList<>();
        streams.add(stream1);
        streams.add(stream2);
        streams.add(stream3);

        // Создание контроллера
        Controller controller = new Controller();

        // Сортировка потоков
        controller.sortStreams(streams);

        // Вывод отсортированных потоков
        for (Stream stream : streams) {
            System.out.println("Поток с " + stream.getStudyGroups().size() + " учебными группами:");
            for (StudyGroup group : stream.getStudyGroups()) {
                System.out.println("  " + group);
            }
        }
    }
}
