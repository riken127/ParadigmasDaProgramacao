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

import ma02_resources.participants.Instituition;
import ma02_resources.participants.Contact;
import ma02_resources.participants.InstituitionType;

public class InstituitionImpl implements Instituition{
    private String name;
    private String email;
    private InstituitionType type;
    private Contact contact;
    private String website;
    private String description;

    public InstituitionImpl(String name, String email, InstituitionType type, Contact contact, String website, String description) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.contact = contact;
        this.website = website;
        this.description = description;
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
    public InstituitionType getType() {
        return this.type;
    }

    @Override
    public Contact getContact() {
        return this.contact;
    }

    @Override
    public String getWebsite() {
        return this.website;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setWebsite(String string) {
        this.website = string;
    }

    @Override
    public void setDescription(String string) {
        this.description = string;
    }

    @Override
    public void setContact(Contact cntct) {
        this.contact = cntct;
    }

    @Override
    public void setType(InstituitionType it) {
        this.type = it;
    }
    
}
