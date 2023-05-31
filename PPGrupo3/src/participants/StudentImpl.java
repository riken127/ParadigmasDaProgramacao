/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Student;

/**
 *
 * @author User
 */
public class StudentImpl extends ParticipantImpl implements Student {

    private int number;
    
    public StudentImpl(String name, String email, InstituitionImpl instituition, Contact contact, int number) {
        super(name, email, instituition, contact);
        this.number = number;
    }

    @Override
    public int getNumber() {
        return this.number;
    }
    
}
