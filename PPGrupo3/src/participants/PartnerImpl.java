/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Partner;

/**
 *
 * @author User
 */
public class PartnerImpl extends ParticipantImpl implements Partner {

    private String vat;
    private String website;
    
    public PartnerImpl(String name, String email, InstituitionImpl instituition, Contact contact, String vat, String website) {
        super(name, email, instituition, contact);
        this.vat = vat;
        this.website = website;
    }

    @Override
    public String getVat() {
        return this.vat;
    }

    @Override
    public String getWebsite() {
        return this.website;
    }
    
}
