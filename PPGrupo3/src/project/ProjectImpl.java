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

import ma02_resources.participants.Participant;
import ma02_resources.project.Project;
import ma02_resources.project.Task;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import ma02_resources.project.exceptions.TaskAlreadyInProject;
import participants.FacilitatorImpl;
import participants.PartnerImpl;
import participants.StudentImpl;

public class ProjectImpl implements Project {

    private final int maxParticipants;
    private final int maxStudents;
    private final int maxPartners;
    private final int maxFacilitators;
    private final int maxTasks;

    private String name;
    private String description;
    private Participant[] participantList;
    private Task[] taskList;
    private int participantCounter;
    private int taskCounter;
    private String[] tagList;

    public ProjectImpl(String name, String description, int maxTasks, int maxPartners, int maxStudents, int maxFacilitators, String[] tags) {
        this.maxFacilitators = maxFacilitators;
        this.maxPartners = maxPartners;
        this.maxStudents = maxStudents;
        this.maxParticipants = maxFacilitators + maxPartners + maxStudents;
        this.maxTasks = maxTasks;
        this.name = name;
        this.description = description;
        this.participantList = new Participant[this.maxParticipants];
        this.participantCounter = 0;
        this.taskList = new Task[this.maxTasks];
        this.taskCounter = 0;
        //this.tagList[] = tags[];
        
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int getNumberOfParticipants() {
        return this.participantCounter;
    }

    @Override
    public int getNumberOfStudents() {
        int studentCounter = 0;
        for (Participant participant : this.participantList) {
            if (participant instanceof StudentImpl) {
                studentCounter++;
            }
        }
        return studentCounter;
    }

    @Override
    public int getNumberOfPartners() {
        int partnersCounter = 0;
        for (Participant participant : this.participantList) {
            if (participant instanceof PartnerImpl) {
                partnersCounter++;
            }
        }
        return partnersCounter;
    }

    @Override
    public int getNumberOfFacilitators() {
        int facilitatorsCounter = 0;
        for (Participant participant : this.participantList) {
            if (participant instanceof FacilitatorImpl) {
                facilitatorsCounter++;
            }
        }
        return facilitatorsCounter;
    }

    @Override
    public int getNumberOfTasks() {
        return this.taskCounter;
    }

    @Override
    public int getMaximumNumberOfTasks() {
        return this.maxTasks;
    }

    @Override
    public long getMaximumNumberOfParticipants() {
        return this.maxParticipants;
    }

    @Override
    public int getMaximumNumberOfStudents() {
        return this.maxStudents;
    }

    @Override
    public int getMaximumNumberOfPartners() {
        return this.maxPartners;
    }

    @Override
    public int getMaximumNumberOfFacilitators() {
        return this.maxFacilitators;
    }

    @Override
    public void addParticipant(Participant participant) throws IllegalNumberOfParticipantType, ParticipantAlreadyInProject {
        if (participant == null) {
            throw new IllegalArgumentException("The given argument is null.");
        }
        if (participant instanceof StudentImpl) {
            if (this.getNumberOfStudents() == this.maxStudents) {
                throw new IllegalNumberOfParticipantType("The max number of students was achieved");
            }
        }
        if (participant instanceof PartnerImpl) {
            if (this.getNumberOfPartners() == this.maxPartners) {
                throw new IllegalNumberOfParticipantType("The max number of partners was achieved");
            }
        }
        if (participant instanceof FacilitatorImpl) {
            if (this.getNumberOfFacilitators() == this.maxFacilitators) {
                throw new IllegalNumberOfParticipantType("The max number of facilitators was achieved");
            }
        }
        for (Participant participants : this.participantList) {
            if (participants.equals(participant)) {
                throw new ParticipantAlreadyInProject("The participant is already in the project");
            }
        }
        this.participantList[this.participantCounter++] = participant;
    }

    @Override
    public Participant removeParticipant(String email) {
      Participant removedParticipant = null;
      for(int i = 0; i < this.participantCounter;i ++){
          if(this.participantList[i].getEmail().equals(email)){
              removedParticipant = this.participantList[i];
              for(int j = i;j < this.participantCounter-1;j++){
                  this.participantList[j] = this.participantList[j+1];
              }
              break;
          }
      }
      return removedParticipant;
    }

    @Override
    public Participant getParticipant(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String[] getTags() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean hasTag(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addTask(Task task) throws IllegalNumberOfTasks, TaskAlreadyInProject {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Task getTask(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Task[] getTasks() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isCompleted() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
