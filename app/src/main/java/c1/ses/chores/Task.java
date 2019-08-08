/* Megan St. Hilaire
   Capital One SES Hackathon --> DUCKLING$
   This file represents a single task assigned to a child by a parent.
*/

package c1.ses.chores;

import java.util.Date;

public class Task {

    private String assignee_id; // child to whom task is assigned
    private String name; // General instruction for task
    private String description; // More detailed set of instructions, if any.
    private Boolean completed; // Whether or not task is done, starts off as incomplete.
    private Double value; // How much child will be paid for completing this task.
    private Date dueDate; // When parent expects this task to be completed.

    public Task(String id, String title, String details, Double compensation, Boolean status, Date reqDate) {

        this.assignee_id = id;
        this.name = title;
        this.description = details;
        this.completed = status;
        this.value = compensation;
        this.dueDate = reqDate;

    }

    /* BEHOLD! THE GETTERS!!! */
    public String getAssignID() {
        return assignee_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isComplete() {
        return completed;
    }

    public Double getCompensation() {
        return value;
    }

    public Date getDueDate() {
        return dueDate;
    }

    /* AT LAST! THE SETTERS!!! */
    public void setAssignee(String id) {
        assignee_id = id;
    }

    public void setName(String nString) {
        name = nString;
    }

    public void setDescription(String dString) {
        description = dString;
    }

    public void setCompletion(Boolean status) {
        completed = status;
    }

    public void setCompensation(Double amount) {
        value = amount;
    }

    public void setDueDate(Date reqDate) {
        dueDate = reqDate;
    }

}

