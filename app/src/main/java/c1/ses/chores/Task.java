package c1.ses.chores;

import java.util.Date;

/**
 * This file represents a single task assigned to a child by a parent.
 *
 * @author Megan St. Hilaire
 * @author Spencer Colton
 */
class Task {
    private String assignee_id;
    private String name;
    private String description;
    private Boolean completed;
    private Double value;
    private Date dueDate;

    /**
     * Blank constructor is required for Firebase to utilize this class directly.
     */
    @SuppressWarnings("unused")
    public Task() {}

    /**
     * Constructor so that all properties have usage.
     *
     * @param assignee_id The ID of the Kid to whom this task is assigned
     * @param title The title of the Task
     * @param description The description of the task
     * @param value The reward for completing this task, in dollars
     * @param completed Whether or not the task has been completed
     * @param dueDate When the task is due
     */
    @SuppressWarnings("unused")
    public Task(String assignee_id,
                String title,
                String description,
                Double value,
                Boolean completed,
                Date dueDate) {
        this.assignee_id = assignee_id;
        this.name = title;
        this.description = description;
        this.completed = completed;
        this.value = value;
        this.dueDate = dueDate;
    }

    /**
     * Getter for the name variable. Required by Firebase.
     *
     * @return This Kid's name
     */
    @SuppressWarnings("unused")
    public String getAssignee_id() {
        return assignee_id;
    }

    /**
     * Getter for the name variable. Required by Firebase.
     *
     * @return This Tasks's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the description variable. Required by Firebase.
     *
     * @return This Task's description
     */
    @SuppressWarnings("unused")
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the completed variable. Required by Firebase.
     *
     * @return Whether or not this Task has been completed
     */
    @SuppressWarnings("unused")
    public Boolean isCompleted() {
        return completed;
    }

    /**
     * Getter for the compensation variable. Required by Firebase.
     *
     * @return The reward for completing this Task
     */
    @SuppressWarnings("unused")
    public Double getCompensation() {
        return value;
    }

    /**
     * Getter for the dueDate variable. Required by Firebase.
     *
     * @return The due date of this Task
     */
    @SuppressWarnings("unused")
    public Date getDueDate() {
        return dueDate;
    }
}

