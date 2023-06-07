/*
* Nome: <João Pedro Salgado Pereira>
* Número: <8220102>
* Turma: <LEI1T4>
*
* Nome: <José Henrique Noronha Oliveira e Silva>
* Número: <8220343>
* Turma: <LEI1T4>
 */
package project;

import java.time.Duration;
import java.time.LocalDate;
import ma02_resources.project.Submission;
import ma02_resources.project.Task;

public class TaskImpl implements Task {

    private static final int INITIAL_SUBMISSION_SIZE = 20;
    private LocalDate start;
    private LocalDate end;
    private String title;
    private String description;
    private Submission[] submissions;
    private int submissionCounter;

    public TaskImpl(LocalDate start, LocalDate end, String title, String description) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.description = description;
        this.submissions = new Submission[INITIAL_SUBMISSION_SIZE];
        this.submissionCounter = 0;
    }

    @Override
    public LocalDate getStart() {
        return start;
    }

    @Override
    public LocalDate getEnd() {
        return end;
    }

    @Override
    public int getDuration() {
        return ((int) Duration.between(this.getStart(), this.getEnd()).toDays());
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Submission[] getSubmissions() {
        return this.submissions;
    }

    @Override
    public int getNumberOfSubmissions() {
        return this.submissionCounter;
    }

    @Override
    public void addSubmission(Submission sbmsn) {
        if (sbmsn == null) {
            throw new IllegalArgumentException("The given argument is null.");
        }
        if (this.submissionCounter == this.submissions.length) {
            Submission[] temporarySubmissionsArray = new Submission[this.submissions.length];
            for (int i = 0; i < this.submissions.length; i++) {
                temporarySubmissionsArray[i] = this.submissions[i];
            }
            this.submissions = new Submission[this.submissions.length * 2];
            for (int i = 0; i < this.submissions.length; i++) {
                this.submissions[i] = temporarySubmissionsArray[i];
            }
            this.submissions[this.submissionCounter++] = sbmsn;
        } else {
            this.submissions[this.submissionCounter++] = sbmsn;
        }
    }

    @Override
    public void extendDeadline(int i) {
        this.end = this.end.plusDays((long) i);
    }

    @Override
    public int compareTo(Task task) {
        return (this.getStart().compareTo(task.getStart()));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        TaskImpl temporaryTask = (TaskImpl) obj;
        return (temporaryTask.getTitle().equals(this.getTitle())
                && temporaryTask.getDescription().equals(this.getDescription())
                && temporaryTask.getStart().equals(this.getStart())
                && temporaryTask.getEnd().equals(this.getEnd()));
    }
}
