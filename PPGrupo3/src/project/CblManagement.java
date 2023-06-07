package project;

import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Submission;

public class CblManagement implements CblManagementInterface{
    @Override
    public void addEdition(Edition newCblEdition) {

    }

    @Override
    public void removeEdition(int index) {

    }

    @Override
    public Edition returnEdition(int index) {
        return null;
    }

    @Override
    public void addProjectToEdition(int index) {

    }

    @Override
    public void setActiveEdition(int index) {

    }

    @Override
    public Edition[] returnEditionsWithProjectsMissingSubmissionsInTasks() {
        return null;
    }

    @Override
    public Project[] returnProjectsWithMissingSubmissions() {
        return null;
    }

    @Override
    public int returnNumberOfProjects(int index) {
        return 0;
    }

    @Override
    public int returnNumberOfEditions() {
        return 0;
    }

    @Override
    public String returnProjectProgress(int index, String string) {
        return null;
    }

    @Override
    public String returnEditionProgress(int index) {
        return null;
    }

    @Override
    public void addProjectSubmissionToActiveEdition(Submission sbmsn) {

    }

    @Override
    public String listOne() {
        return null;
    }

    @Override
    public String listTwo() {
        return null;
    }

    @Override
    public String listThree() {
        return null;
    }
}
