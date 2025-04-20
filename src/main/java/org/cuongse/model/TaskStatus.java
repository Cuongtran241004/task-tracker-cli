package org.cuongse.model;

public enum TaskStatus {
    TODO("Todo"),
    IN_PROGRESS("In Progress"),
    DONE("Done");

    private final String status;

    private TaskStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    // Convert from String to TaskStatus
    public static TaskStatus fromString(String status) {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.status.equalsIgnoreCase(status)) {
                return taskStatus;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + status);
    }

    @Override
    public String toString() {
        return status;
    }
}
