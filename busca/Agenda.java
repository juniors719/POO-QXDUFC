package busca;

import java.util.*;

public class Agenda {

    private ArrayList<Contact> contacts;

    private int findPosByName(String name) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Agenda() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(String name, ArrayList<Fone> fones) {
        int pos = findPosByName(name);
        if (pos != -1) {
            for (Fone fone : fones) {
                contacts.get(pos).addFone(fone.getId(), fone.getNumber());
            }
            return;
        }
        Contact contact = new Contact(name);
        for (Fone fone : fones) {
            contact.addFone(fone.getId(), fone.getNumber());
        }
        contacts.add(contact);
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                return c1.getName().compareTo(c2.getName()); // Ordenar por nome, por exemplo
            }
        });
    }

    public Contact getContact(String name) {
        int pos = findPosByName(name);
        if (pos == -1) {
            System.out.println("fail: nao encontrado");
            return null;
        }
        return this.contacts.get(pos);
    }

    public void rmContact(String name) {
        int pos = findPosByName(name);
        if (pos == -1) {
            System.out.println("fail: nao encontrado");
            return;
        }
        this.contacts.remove(pos);
    }

    public ArrayList<Contact> search(String pattern) {
        ArrayList<Contact> contatosBusca = new ArrayList<>();
        for (Contact contact : contacts) {
            String contactString = contact.toString();
            if (contactString.contains(pattern)) {
                contatosBusca.add(contact);
            }
        }
        return contatosBusca;
    }

    public ArrayList<Contact> getFavorited() {
        ArrayList<Contact> contatosFav = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.isFavorited()) {
                contatosFav.add(contact);
            }
        }
        return contatosFav;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Contact contact : contacts) {
            sb.append(contact);
            sb.append("\n");
        }
        return sb.toString();
    }

}
