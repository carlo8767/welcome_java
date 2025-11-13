package tasktracker.task;

public class TaskDelete implements TaskOperation {

    private TaskModel taskModel;

    @Override
    public String typeOperation() {
        return TaskOperation.DELETE;
    }
}
