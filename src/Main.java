public class Main {
    public static void main(String[] args) {

        Manager manager = new Manager();

        // 1. Обычные задачи
        Task task1 = new Task("Задача 1", "Описание 1", Progress.NEW);
        Task task2 = new Task("Задача 2", "Описание 2", Progress.NEW);

        manager.createTask(task1);
        manager.createTask(task2);

        // 2. Эпик 1 (2 подзадачи)
        Epic epic1 = new Epic("Эпик 1", "Первый эпик", Progress.NEW);
        manager.createEpic(epic1);

        SubTask st1 = new SubTask("Подзадача 1", "A", Progress.NEW, epic1.getId());
        SubTask st2 = new SubTask("Подзадача 2", "B", Progress.NEW, epic1.getId());

        manager.createSubtask(st1);
        manager.createSubtask(st2);

        // 3. Эпик 2 (1 подзадача)
        Epic epic2 = new Epic("Эпик 2", "Второй эпик", Progress.NEW);
        manager.createEpic(epic2);

        SubTask st3 = new SubTask("Подзадача 3", "C", Progress.NEW, epic2.getId());
        manager.createSubtask(st3);

        // 4. Печать начального состояния
        System.out.println("НАЧАЛО");

        System.out.println(manager.getTaskById(task1.getId()));
        System.out.println(manager.getTaskById(task2.getId()));

        System.out.println(manager.getEpicById(epic1.getId()));
        System.out.println(manager.getEpicById(epic2.getId()));

        System.out.println(manager.getSubtaskById(st1.getId()));
        System.out.println(manager.getSubtaskById(st2.getId()));
        System.out.println(manager.getSubtaskById(st3.getId()));

        // 5. Изменяем статусы
        task1.setProgress(Progress.DONE);
        manager.updateTask(task1.getId(), task1);

        st1.setProgress(Progress.DONE);
        manager.updateSubtask(st1.getId(), st1);

        st2.setProgress(Progress.DONE);
        manager.updateSubtask(st2.getId(), st2);

        st3.setProgress(Progress.DONE);
        manager.updateSubtask(st3.getId(), st3);

        // 6. Проверка после изменений
        System.out.println("\nПОСЛЕ ИЗМЕНЕНИЙ");

        System.out.println(manager.getTaskById(task1.getId()));
        System.out.println(manager.getTaskById(task2.getId()));

        System.out.println(manager.getEpicById(epic1.getId()));
        System.out.println(manager.getEpicById(epic2.getId()));

        // 7. Удаление задач
        manager.deleteTask(task2.getId());
        manager.deleteEpic(epic2.getId());

        System.out.println("\nzПОСЛЕ УДАЛЕНИЯ");

        System.out.println(manager.getTaskById(task1.getId()));
        System.out.println(manager.getTaskById(task2.getId())); // null

        System.out.println(manager.getEpicById(epic1.getId()));
        System.out.println(manager.getEpicById(epic2.getId())); // null
    }
}