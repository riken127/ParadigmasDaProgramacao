/*
* Nome: <João Pedro Salgado Pereira>
* Número: <8220102>
* Turma: <LEI1T4>
*
* Nome: <José Henrique Noronha Oliveira e Silva>
* Número: <8220343>
* Turma: <LEI1T4>
*/
package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Student;

public class StudentImpl extends ParticipantImpl implements Student {

    private int number;
    
    public StudentImpl(String name, String email, InstituitionImpl institution, Contact contact, int number) {
        super(name, email, institution, contact);
        this.number = number;
    }

    @Override
    public int getNumber() {
        return this.number;
    }
    
}
