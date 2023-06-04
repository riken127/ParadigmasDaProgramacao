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
    public void defineActiveEdition(int index) {

    }

    @Override
    public Edition returnEditionsWithProjectWithMissingSubmissionsInTasks() {
        return null;
    }

    @Override
    public Project returnProjectsWithMissingSubmissions() {
        return null;
    }

    @Override
    public int returnNumberOfProjectFromEdition(int index) {
        return 0;
    }

    @Override
    public int returnNumberOfEditions() {
        return 0;
    }

    @Override
    public String returnProgressOnProject(int index, String string) {
        return null;
    }

    @Override
    public String returnProgressOnEdition(int index) {
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
