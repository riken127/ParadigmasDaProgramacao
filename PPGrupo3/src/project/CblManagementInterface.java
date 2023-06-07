package project;
import exceptions.EditionAlreadyInListException;
import exceptions.InvalidIndexException;
import exceptions.InvalidProjectNameException;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Submission;

import java.io.IOException;
import java.text.ParseException;

public interface CblManagementInterface {
    /**
     * @brief Adds an edition to CblManagement.
     * This method adds the specified edition to the CblManagement object. It performs the following steps:
     * Iterates through the existing editions in the edition list to check if any edition is equal to the newCblEdition.
     * If a match is found, it throws an EditionAlreadyInListException.
     * Checks the status of the newCblEdition. If it is set to Status.ACTIVE, the status is changed to Status.INACTIVE.
     * However, if this is the first edition in Cbl, it is automatically set to Status.ACTIVE.
     * Checks if the size of the edition list array is equal to the editionCounter. If so, it calls the
     * expandEditionListArray() method to double the capacity of the edition list array.
     * Adds the newCblEdition to the edition list at the current editionCounter index and increments the editionCounter.
     * @param newCblEdition the edition to be added to CblManagement
     * @throws EditionAlreadyInListException if the newCblEdition is already present in the edition list
     */
    void addEdition(Edition newCblEdition) throws EditionAlreadyInListException;

    /**
     * @brief Removes an edition from the CblManagement at the specified index.
     * This method removes an edition from the edition list based on the given index.
     * It first checks if the edition at the specified index is already null, and if so, throws an InvalidIndexException.
     * Otherwise, it shifts the subsequent editions in the list to the left to fill the gap left by the removed edition.
     * Finally, it sets the last element in the list to null to maintain consistency.
     * @param index the index of the edition to be removed
     * @throws InvalidIndexException if the given index is out of bounds or already null
     */
    void removeEdition(int index) throws InvalidIndexException;

    /**
     * Returns an edition from the CblManagement at the specified index.
     * @brief Returns the edition at the given index.
     * @param index the index of the edition to be returned
     * @return the edition at the specified index, or null if the index is out of bounds
     * @throws ArrayIndexOutOfBoundsException if the index is negative or exceeds the available editions
     */
    Edition returnEdition(int index);

    /**
     * Adds a project to the specified edition in CblManagement.
     * @brief Adds a project to an edition.
     * @param editionIndex the index of the edition in the edition list
     * @param name the name of the project
     * @param description the description of the project
     * @param tags the tags associated with the project
     * @throws InvalidIndexException if the given edition index is invalid or null
     * @throws IOException if an error occurs while trying to read the JSON template
     * @throws ParseException if an error occurs while parsing the JSON template
     */
    void addProjectToEdition(int editionIndex, String name, String description, String[] tags) throws InvalidIndexException, IOException, ParseException;

    /**
     * Sets the active edition at the specified index in the edition list.
     * If the edition at the given index is null, it throws an InvalidIndexException.
     * The method iterates through the edition list and updates the status of editions based on the specified index.
     * The edition at the specified index is set to active (Status.ACTIVE), while all other editions with active status are set to inactive (Status.INACTIVE).
     * @brief Sets the active edition at the specified index.
     * @param index the index of the edition to be set as active
     * @throws InvalidIndexException if the edition at the given index is null
     */
    void setActiveEdition(int index) throws InvalidIndexException;

    /**
     * Returns an array of editions that have projects with missing submissions in their tasks.
     * Iterate over each edition in the edition list.
     * Check each project in the temporary project array.
     * If there are projects with missing submissions, increment the counter.
     * Create a new array to store the editions with missing submissions.
     * Iterate over each edition again.
     * Check each project in the temporary project array.
     * If there are projects with missing submissions, add the edition to the missingSubmissionArray.
     * @brief Returns editions with projects missing submissions in tasks.
     * @return an array of editions with projects missing submissions.
     */
    Edition[] returnEditionsWithProjectsMissingSubmissionsInTasks();
    /**
     * Returns an array of projects with missing submissions based on the specified index of the edition list.
     * @brief Returns projects with missing submissions.
     * This method retrieves the projects from the edition at the specified index of the edition list.
     * It counts the number of projects that are not completed (missing submissions) in the projectArray.
     * If there are no projects with missing submissions, it returns null.
     * Otherwise, it creates a new array called projectsWithMissingSubmissions with a size equal to the counter.
     * It then iterates over the projectArray, and for each project that is not completed, it adds it to the projectsWithMissingSubmissions array.
     * Finally, it returns the projectsWithMissingSubmissions array.
     * @param index the index of the edition list
     * @return an array of projects with missing submissions
     * @throws InvalidIndexException if the given index is null or invalid
     */
    Project[] returnProjectsWithMissingSubmissions(int index) throws InvalidIndexException;
    /**
     * Returns the number of projects in the edition at the specified index.
     * @brief Returns the number of projects in the specified edition.
     * It first checks if the edition at the given index is null. If it is, it throws an InvalidIndexException.
     * Then it retrieves the projects array from the edition at the specified index and returns its length.
     * @param index the index of the edition in the edition list
     * @return the number of projects in the edition
     * @throws InvalidIndexException if the given index is invalid or points to a null edition
     */
    int returnNumberOfProjects(int index) throws InvalidIndexException;
    /**
     * Returns the number of editions in the CblManagement.
     * @brief Returns the number of editions.
     * @return the number of editions in the CblManagement.
     */
    int returnNumberOfEditions();
    /**
     * Returns the progress of a project in the CblManagement system at the specified index.
     * @brief Returns the progress of a project in the specified edition.
     * It starts by constructing the initial progress string.
     * If the edition at the given index is null, it throws an InvalidIndexException with an appropriate error message.
     * Then it retrieves the project object from the edition at the specified index using the provided project name.
     * If the project is not found, it throws an InvalidIndexException with an appropriate error message.
     * The progress string is then updated with the current number of participants, maximum number of participants,
     * current number of partners, maximum number of partners, current number of students, maximum number of students,
     * current number of tasks, maximum number of tasks, and the completion state of the project.
     * @param index the index of the edition in the edition list
     * @param string the name of the project
     * @return the progress of the project as a formatted string
     * @throws InvalidIndexException if the given index is invalid or points to a null edition, or if the project with the given name is not found.
     */
    String returnProjectProgress(int index, String string) throws InvalidIndexException;
    /**
     * Returns the progress information of an edition at the specified index.
     * @brief Returns the edition progress information.
     * It generates a string that includes details about the edition's name, start and end dates, status, and project information.
     * If the edition at the given index is null, it throws an InvalidIndexException.
     * The project information includes the number of projects and the ratio of completed projects.
     * @param index the index of the edition in the edition list
     * @return a string containing the edition progress information
     * @throws InvalidIndexException if the given index is invalid or points to a null edition
     */
    String returnEditionProgress(int index) throws InvalidIndexException;
    /**
     * Adds a project submission to the active edition in CblManagement.
     * @brief Adds a project submission to the active edition.
     * This method adds a project submission to the active edition in CblManagement. It follows the following steps:
     * Finds the index of the active edition using the 'findActiveEdition()' method.
     * If no active edition is found (index = -1), it throws an InvalidIndexException with the message "No active edition was found."
     * Retrieves the current active edition using the editionList[index].
     * Retrieves the project with the given projectName from the current active edition. If no project is found, it throws an InvalidProjectNameException with the message "The given project name was not found."
     * Checks if the participant with the given studentEmail is associated with the current project. If not, it throws an InvalidIndexException with the message "The given student email is not present."
     * Checks if the task with the given title exists in the current project. If not, it throws an InvalidIndexException with the message "The given task title is invalid."
     * Adds the submission (sbmsn) to the task with the given title in the current project.
     * @param submission the project submission to be added
     * @param studentEmail the email of the student associated with the submission
     * @param projectName the name of the project to which the submission belongs
     * @param title the title of the task to which the submission is added
     * @throws InvalidIndexException if no active edition is found, the student email is not present, or the task title is invalid
     * @throws InvalidProjectNameException if the given project name is not found
     */
    void addProjectSubmissionToActiveEdition(Submission submission,  String studentEmail, String projectName, String title) throws InvalidIndexException , InvalidProjectNameException;
    /**
     * Returns a string containing information about the editions.
     * @brief Provides detailed information about the editions in CblManagement.
     * It constructs a string with the edition information, including the edition name, start date,
     * project template, and status. Each edition's information is separated by a line of dashes.
     * @return a string containing the editions information
     */
    String listEditionInformation();
    /**
     * Returns the information of projects organized by edition.
     * @brief Returns a formatted string containing project information grouped by edition.
     * It iterates over each edition in the edition list and appends edition information, including its name,
     * to the output string. Then, it retrieves the projects from the edition and appends each project's name,
     * description, and tags (converted to a string) to the output string. The output string is formatted with
     * appropriate indentation and separators for readability.
     * @return a formatted string containing project information organized by edition
     */
    String listProjectInformationByEdition();
    /**
     * Returns a string containing the project status summary by edition.
     * @brief Generates a summary of project statuses for each edition.
     * It iterates through the edition list and retrieves information about each edition's projects.
     * It counts the number of completed and uncompleted projects in each edition and calculates the completion percentage.
     * @return a formatted string summarizing the project statuses by edition
     */
    String listProjectStatusByEdition();
}
