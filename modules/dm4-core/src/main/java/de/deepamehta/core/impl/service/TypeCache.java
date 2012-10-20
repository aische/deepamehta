package de.deepamehta.core.impl.service;

import de.deepamehta.core.TopicType;
import de.deepamehta.core.AssociationType;
import de.deepamehta.core.model.TopicModel;
import de.deepamehta.core.model.AssociationTypeModel;
import de.deepamehta.core.model.SimpleValue;
import de.deepamehta.core.model.TopicTypeModel;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Logger;



/**
 * A memory-cache for type definitions: topic types and association types.
 * <p>
 * Types are accessed by the {@link get} methods. They are lazy-loaded from the DB.
 * <p>
 * This class is internally used by the {@link EmbeddedService}. The plugin developer accesses topic types via the
 * {@link de.deepamehta.core.service.DeepaMehtaService#getTopicType} core service call.
 */
class TypeCache {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Map<String, TopicType>       topicTypes = new HashMap();   // key: topic type URI
    private Map<String, AssociationType> assocTypes = new HashMap();   // key: assoc type URI

    private EmbeddedService dms;

    // ### private EndlessRecursionProtection endlessRecursionProtection = new EndlessRecursionProtection();

    private Logger logger = Logger.getLogger(getClass().getName());

    // ---------------------------------------------------------------------------------------------------- Constructors

    TypeCache(EmbeddedService dms) {
        this.dms = dms;
    }

    // ----------------------------------------------------------------------------------------- Package Private Methods

    TopicType getTopicType(String topicTypeUri) {
        TopicType topicType = topicTypes.get(topicTypeUri);
        if (topicType == null) {
            // ### endlessRecursionProtection.check(topicTypeUri);
            topicType = loadTopicType(topicTypeUri);
        }
        return topicType;
    }

    AssociationType getAssociationType(String assocTypeUri) {
        AssociationType assocType = assocTypes.get(assocTypeUri);
        if (assocType == null) {
            // ### endlessRecursionProtection.check(assocTypeUri);
            assocType = loadAssociationType(assocTypeUri);
        }
        return assocType;
    }

    // ---

    void put(TopicType topicType) {
        topicTypes.put(topicType.getUri(), topicType);
    }

    void put(AssociationType assocType) {
        assocTypes.put(assocType.getUri(), assocType);
    }

    // ---

    void invalidate(String topicTypeUri) {
        logger.info("### Invalidating topic type \"" + topicTypeUri + "\"");
        if (topicTypes.remove(topicTypeUri) == null) {
            throw new RuntimeException("Topic type \"" + topicTypeUri + "\" not found in type cache");
        }
    }

    // ------------------------------------------------------------------------------------------------- Private Methods

    private TopicType loadTopicType(String topicTypeUri) {
        logger.info("Loading topic type \"" + topicTypeUri + "\"");
        return dms.objectFactory.fetchTopicType(topicTypeUri);
    }

    private AssociationType loadAssociationType(String assocTypeUri) {
        logger.info("Loading association type \"" + assocTypeUri + "\"");
        return dms.objectFactory.fetchAssociationType(assocTypeUri);
    }

    // ---

    private class EndlessRecursionProtection {

        private Map<String, Integer> callCount = new HashMap();

        private void check(String typeUri) {
            Integer count = callCount.get(typeUri);
            if (count == null) {
                count = 0;
            }
            count++;
            callCount.put(typeUri, count);
            if (count >= 2) {
                throw new RuntimeException("Endless recursion while loading type \"" + typeUri + "\"");
            }
        }
    }
}
