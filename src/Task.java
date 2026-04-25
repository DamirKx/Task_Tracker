public class Task {
    private String name;
    private String description;
    private int id;
    private Progress progress;

    public Task(String name, String description, Progress progress){
        this.name = name;
        this.description = description;
        this.progress = progress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString() {
        return "id: "  + id +
                "\nНазвание: " + name +
                "\nОписание: " + description +
                "\nПрогресс: " + progress;
    }
}
