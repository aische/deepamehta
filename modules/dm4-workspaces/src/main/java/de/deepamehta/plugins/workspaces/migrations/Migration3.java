package de.deepamehta.plugins.workspaces.migrations;

import de.deepamehta.core.model.AssociationDefinitionModel;
import de.deepamehta.core.service.Migration;



public class Migration3 extends Migration {

    @Override
    public void run() {
        // add "Workspace Type" to "Workspace"
        dms.getTopicType("dm4.workspaces.workspace").addAssocDef(
            new AssociationDefinitionModel("dm4.core.aggregation_def",
                "dm4.workspaces.workspace", "dm4.workspaces.type", "dm4.core.many", "dm4.core.one")
        );
    }
}