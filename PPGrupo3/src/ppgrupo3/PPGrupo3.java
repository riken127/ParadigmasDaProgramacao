/*
* Nome: <João Pedro Salgado Pereira>
* Número: <8220102>
* Turma: <LEI1T4>
*
* Nome: <José Henrique Noronha Oliveira e Silva>
* Número: <8220343>
* Turma: <LEI1T4>
 */
package ppgrupo3;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import ma02_resources.participants.InstituitionType;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import participants.ContactImpl;
import participants.InstituitionImpl;
import participants.ParticipantImpl;
import participants.FacilitatorImpl;
import participants.PartnerImpl;
import participants.StudentImpl;
import project.ProjectImpl;
import project.SubmissionImpl;
import project.TaskImpl;

public class PPGrupo3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalNumberOfParticipantType, ParticipantAlreadyInProject {

        ContactImpl contacto = new ContactImpl("Rua dos Cravos", "Guimaraes", "Braga", "4325-324", "Porgual", "91242154");
        InstituitionImpl instituicao = new InstituitionImpl("FM Teck", "FMTek@gmail.com", InstituitionType.COMPANY, contacto, "www.FMTek.com", "Somos uma empresa");
        ParticipantImpl participante = new ParticipantImpl("Joao", "joao@gmail.com", instituicao, contacto);
        FacilitatorImpl facilitador = new FacilitatorImpl("Joao o Facilitador", "joaoF@gmail.com", instituicao, contacto, "Informatica");
        PartnerImpl parceiro = new PartnerImpl("Joao o Parceiro", "joaoP@gmail.com", instituicao, contacto, "vat", "www.Parceiro.com");
        StudentImpl estudante = new StudentImpl("Joao o Aluno 1", "joaoA1@gmail.com", instituicao, contacto, 33);
        StudentImpl estudante2 = new StudentImpl("Joao o Aluno 2", "joaoA2@gmail.com", instituicao, contacto, 33);
        StudentImpl estudante3 = new StudentImpl("Joao o Aluno 3", "joaoA3@gmail.com", instituicao, contacto, 33);

        SubmissionImpl submicao = new SubmissionImpl(LocalDateTime.now(), estudante, "Primeira Submição");
        TaskImpl task = new TaskImpl(LocalDate.now(), LocalDate.of(2022, Month.OCTOBER, 10), "Carro", "Fazer um carro");
        ProjectImpl projeto = new ProjectImpl("Primeiro projeto", "Um projeto pa desemvolver", 4, 8, 2, 5, 3);

        try {
            projeto.addParticipant(estudante);
            projeto.addParticipant(facilitador);
            projeto.addParticipant(parceiro);
            projeto.addParticipant(estudante2);
            projeto.addParticipant(estudante3);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        System.out.println(projeto.getNumberOfParticipants());
        System.out.println(projeto.getNumberOfFacilitators());
        System.out.println(projeto.getNumberOfPartners());
        System.out.println(projeto.getNumberOfStudents());
        System.out.println(projeto.getNumberOfTasks());

    }

}
