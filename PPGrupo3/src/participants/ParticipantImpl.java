/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Participant;

/**
 *
 * @author User
 */
public class ParticipantImpl implements Participant{

    private String name;
    private String email;
    private InstituitionImpl instituition;
    private Contact contact;

    public ParticipantImpl(String name, String email, InstituitionImpl instituition, Contact contact) {
        this.name = name;
        this.email = email;
        this.instituition = instituition;
        this.contact = contact;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public Contact getContact() {
        return this.contact;
    }

    @Override
    public InstituitionImpl getInstituition() {
        return this.instituition;
    }

    @Override
    public void setContact(Contact cntct) {
        this.contact = cntct;
    }

    @Override
    public void setInstituition(Instituition instn) {
        this.instituition = (InstituitionImpl) instn; // ??
    }
    
}
