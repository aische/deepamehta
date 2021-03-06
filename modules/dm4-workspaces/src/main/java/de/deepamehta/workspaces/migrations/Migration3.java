package de.deepamehta.workspaces.migrations;

import de.deepamehta.core.model.AssociationDefinitionModel;
import de.deepamehta.core.service.Migration;



/**
 * Adds child type "Sharing Mode" to the "Workspace" topic type.
 * Runs ALWAYS.
 * <p>
 * Part of DM 4.5
 */
public class Migration3 extends Migration {

    @Override
    public void run() {
        dm4.getTopicType("dm4.workspaces.workspace").addAssocDef(
            mf.newAssociationDefinitionModel("dm4.core.aggregation_def",
                "dm4.workspaces.workspace", "dm4.workspaces.sharing_mode", "dm4.core.many", "dm4.core.one")
        );
    }
}
