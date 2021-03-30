package twisk.mondeIG;

import twisk.designPattern.SujetObserve;
import twisk.exceptions.*;
import twisk.outils.FabriqueIdentifiant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MondeIG extends SujetObserve {
    private final HashMap<String, EtapeIG> etapes;
    private final ArrayList<EtapeIG> etapesSelectionnees;
    private final ArrayList<ArcIG> arcs;

    public MondeIG() {
        super();
        FabriqueIdentifiant fabID = FabriqueIdentifiant.getInstance();
        etapes = new HashMap<>(10);
        etapesSelectionnees = new ArrayList<>(10);
        arcs = new ArrayList<>(10);
        String id = fabID.getIdentifiantEtape();
        ActiviteIG activite = new ActiviteIG("Activité n°" + id, id);
        this.etapes.put(id, activite);
    }

    public void ajouter(String type) {
        switch (type) {
            case "Activite":
                FabriqueIdentifiant fabID = FabriqueIdentifiant.getInstance();
                String id = fabID.getIdentifiantEtape();
                ActiviteIG activite = new ActiviteIG("Activité n°" + id, id);
                this.etapes.put(id, activite);
                this.notifierObservateurs();
        }
    }

    public Iterator<EtapeIG> iterator() {
        return etapes.values().iterator(); //On itère sur les valeurs de la HashMap.
    }

    //Fonctions nécessaires aux tests de MondeIG (fonction "ajouter", "iterator")
    public int nbEtapes() {
        int cpt = 0;
        for (int i = 0; i < this.etapes.size(); ++i) {
            cpt += 1;
        }
        return cpt;
    }

    public void ajouter(PointDeControleIG pdc1, PointDeControleIG pdc2) throws TwiskException {
        for (Iterator<ArcIG> it = this.iteratorArcs(); it.hasNext(); ) {
            ArcIG arc = it.next();
            //Exactement le même arc où exactement l'opposé de cet arc
            if ((arc.getPdcDepart().getId().equals(pdc1.getId()) && arc.getPdcArrive().getId().equals(pdc2.getId()))) {
                throw new ArcAlreadyCreateException("On ne peut pas créer un arc déjà créer!");
            }
            if (arc.getPdcArrive().getId().equals(pdc1.getId()) || arc.getPdcDepart().getId().equals(pdc2.getId())) {
                throw new CreateArcWithEndPdcException("Un arc ne peut pas partir du point d'arrivé d'un autre arc!");
            }
        }
        if (pdc1.getEtapeRattache() == pdc2.getEtapeRattache()) {
            throw new SameActivityException("Vous ne pouvez pas, créer d'arcs entre 2 points de controle identiques! où créer un arc entre 2 points d'une même étape!");
        }
        ArcIG ark = new ArcIG(pdc1, pdc2);
        this.arcs.add(ark);
    }

    public Iterator<ArcIG> iteratorArcs() {
        return arcs.iterator();
    }

    public void creationArc(PointDeControleIG pdc) throws TwiskException {
        boolean isCreated = false;
        for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
            EtapeIG etape = iter.next();
            for (PointDeControleIG pdcIG : etape) {
                //Je cherche dans tous les pdc si il y en a un qui est true, le pdc en paramètre est le 2éme pdc qu'on à cliqué
                //Si j'en trouve un, cela signifie que celui en paramètre est le second
                if (pdcIG.isClicked()) {
                    pdcIG.setClicked();
                    isCreated = true;
                    ajouter(pdcIG, pdc);
                    this.notifierObservateurs();
                }
                //Si j'en trouve pas, le pdc en param est le premier.
            }
        }
        if (!isCreated) {
            pdc.setClicked();
        }
    }

    //Fonctions nécessaires aux tests
    public EtapeIG getEtapeIndice(String indice) {
        return this.etapes.get(indice);
    }

    public int getNbArcs() {
        return this.arcs.size();
    }

    public void ajouterEtapeSelectionnee(EtapeIG etape) {
        if (isSelectionned(etape)) {
            etapesSelectionnees.remove(etape);
        } else {
            etapesSelectionnees.add(etape);
        }
        this.notifierObservateurs();
    }

    public boolean isSelectionned(EtapeIG etape) {
        return etapesSelectionnees.contains(etape);
    }

    public void supprimerLaSelection() {
        for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
            supprimer(iter);
        }
        for (Iterator<ArcIG> iterA = iteratorArcs(); iterA.hasNext(); ) {
            ArcIG arc = iterA.next();
            if (arc.isSelected()) {
                arc.setSelect(false);
                iterA.remove();
                this.arcs.remove(arc);
            }
        }
        this.notifierObservateurs();
    }

    public void supprimer(Iterator<EtapeIG> iterE) {
        ArcIG arc;
        EtapeIG e = iterE.next();
        if (isSelectionned(e)) {
            for (Iterator<ArcIG> iter = this.iteratorArcs(); iter.hasNext(); ) {
                arc = iter.next();
                if (arc.isLinkedToStage(e)) {
                    iter.remove();
                    arcs.remove(arc);
                }
            }
            iterE.remove();
            etapesSelectionnees.remove(e);
            etapes.remove(e.getIdentifiant());
        }
    }

    public void renommerLaSelection(String newName) {
        for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
            EtapeIG e = iter.next();
            if (this.isSelectionned(e)) {
                e.setNom(newName);
            }
        }
        this.effacerLaSelection();
    }

    public int nbEtapesSelectionnees() {
        int cpt = 0;
        for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
            EtapeIG e = iter.next();
            if (this.isSelectionned(e)) {
                cpt++;
            }
        }
        return cpt;
    }

    public void changerEmplacementEtape(String indice, int x, int y) {
        this.getEtapeIndice(indice).setPosXPosY(x, y);
        this.notifierObservateurs();
    }

    public void selectionArc(ArcIG arc) {
        arc.setSelect(true);
        notifierObservateurs();
    }

    public boolean isSelectionned(ArcIG arc) {
        return arc.isSelected();
    }

    public void effacerLaSelection() {
        etapesSelectionnees.clear();
        for (Iterator<ArcIG> iterA = iteratorArcs(); iterA.hasNext(); ) {
            ArcIG arc = iterA.next();
            if (arc.isSelected()) {
                arc.setSelect(false);
            }
        }
        notifierObservateurs();
    }

    public void setEntree() {
        for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
            EtapeIG e = iter.next();
            if (this.isSelectionned(e)) {
                e.invEntree();
            }
        }
    }

    public void setSortie() {
        for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
            EtapeIG e = iter.next();
            if (this.isSelectionned(e)) {
                e.invSortie();
            }
        }
    }

    public void setDelai(String d) throws UncorrectSettingsException {
        try {
            int dBis = Integer.parseInt(d);
            if (dBis < 0) {
                throw new UncorrectSettingsException("Attention, un délai ne peut pas être négatif!");
            }
            for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
                EtapeIG eta = iter.next();
                if (this.isSelectionned(eta)) {
                    if (dBis < eta.getEcart()) {
                        throw new UncorrectSettingsException("Attention, un délai ne peut pas être inférieur à un écart!");
                    }
                    eta.setDelai(dBis);
                }
            }
        } catch (NumberFormatException nFE) {
            throw new UncorrectSettingsException("Les paramètres saisis pour le délai sont erronés!");
        }
        notifierObservateurs();
    }

    public void setEcart(String e) throws UncorrectSettingsException {
        try {
            int eBis = Integer.parseInt(e);
            if (eBis < 0) {
                throw new UncorrectSettingsException("Attention, un écart ne peut pas être négatif!");
            }
            for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
                EtapeIG eta = iter.next();
                if (this.isSelectionned(eta)) {
                    if (eBis > eta.getDelai()) {
                        throw new UncorrectSettingsException("Attention, un écart ne peut pas être supérieur à un délai!");
                    }
                    eta.setEcart(eBis);
                }
            }
        } catch (NumberFormatException nFE) {
            throw new UncorrectSettingsException("Les paramètres saisis pour l'écart sont erronés!");
        }
        notifierObservateurs();
    }
}
