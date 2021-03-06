package de.deepamehta.core.impl;

import de.deepamehta.core.Association;
import de.deepamehta.core.RelatedAssociation;



/**
 * An Association-Association pair that is attached to the {@link PersistenceLayer}.
 */
class RelatedAssociationImpl extends AssociationImpl implements RelatedAssociation {

    // ---------------------------------------------------------------------------------------------------- Constructors

    RelatedAssociationImpl(RelatedAssociationModelImpl model, PersistenceLayer pl) {
        super(model, pl);
    }

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public Association getRelatingAssociation() {
        return getModel().getRelatingAssociation().instantiate();
    }

    @Override
    public RelatedAssociationModelImpl getModel() {
        return (RelatedAssociationModelImpl) model;
    }
}
