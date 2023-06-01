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
import ma02_resources.participants.Partner;

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
