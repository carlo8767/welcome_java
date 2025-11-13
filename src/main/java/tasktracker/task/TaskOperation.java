package tasktracker.task;

public interface TaskOperation {


    // OPERATION TYPE
    static final String UPDATE = "UPDATE";
    static final String DELETE = "DELETE";
    static final String ADD = "ADD";

    // STATUS TASK
    static final String DONE = "DONE";
    static final String NOT_DONE = "NOT DONE";
    static final String PROGRESS = "PROGRESS";

    // List all tasks that are in progress




    public String typeOperation ();

}
