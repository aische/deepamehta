package de.deepamehta.core.model;

import org.codehaus.jettison.json.JSONObject;

import java.util.Set;
import java.util.logging.Logger;



/**
 * Collection of the data that makes up an {@link AssociationType}.
 *
 * @author <a href="mailto:jri@deepamehta.de">Jörg Richter</a>
 */
public class AssociationTypeModel extends TypeModel {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    // ---------------------------------------------------------------------------------------------------- Constructors

    public AssociationTypeModel(String uri, String value, String dataTypeUri) {
        super(uri, "dm4.core.assoc_type", new SimpleValue(value), dataTypeUri);
    }

    // ### TODO: to be completed
    public AssociationTypeModel(TopicModel model, String dataTypeUri, Set<IndexMode> indexModes) {
        super(model, dataTypeUri, indexModes);
    }

    public AssociationTypeModel(JSONObject assocTypeModel) {
        super(assocTypeModel, "dm4.core.assoc_type");
    }

    // -------------------------------------------------------------------------------------------------- Public Methods

    @Override
    public String toString() {
        return "association type (" + super.toString() + ")";
    }
}
