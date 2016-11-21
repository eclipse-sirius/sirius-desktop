/*******************************************************************************
 * Copyright (c) 2009, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.format.semantic;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.formatdata.AbstractFormatData;
import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.ui.tools.api.format.AbstractSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.format.AdvancedSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.format.NodeFormatDataKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;

/**
 * SiriusFormatDataManager drives by the semantic elements (EObject). Use for
 * format duplication.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SiriusFormatDataManagerForSemanticElements extends AbstractSiriusFormatDataManager implements AdvancedSiriusFormatDataManager {

    /**
     * Error message used when this manager is added an
     * {@link AbstractFormatData} which specific type is not supported.
     */
    private static final String ERROR_MESSAGE_UNSUPPORTED_FORMAT_DATA_TYPE = "The default format data manager SiriusFormatDataManagerForSemanticElements does not support AbstractDataFormat of the type :"; //$NON-NLS-1$

    /**
     * Error message used when this format data manager is called by the
     * obsolete method
     * org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.
     * SiriusFormatDataManagerForSemanticElements.addFormatData(FormatDataKey,
     * AbstractFormatData) or
     * org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.
     * SiriusFormatDataManagerForSemanticElements.getFormatData(FormatDataKey).
     */
    private static final String ERROR_MESSAGE_UNSUPPORTED_METHOD_CALL = "The default SiriusFormatDataManager does not use this method anymore"; //$NON-NLS-1$

    private static final SiriusFormatDataManagerForSemanticElements INSTANCE = new SiriusFormatDataManagerForSemanticElements();

    private static final Predicate<EObject> ROOT_PREDICATE = new Predicate<EObject>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean apply(final EObject input) {
            return input.eContainer() == null;
        }
    };

    /**
     * The map containing node formats associated to its mapping id and indexed
     * by the key as {@link SemanticNodeFormatDataKey}.
     */
    private final Map<SemanticNodeFormatDataKey, Map<String, NodeFormatData>> nodeFormatDataMap = new HashMap<SemanticNodeFormatDataKey, Map<String, NodeFormatData>>();

    /**
     * The map containing edge formats associated to its mapping id and indexed
     * by the key as {@link SemanticEdgeFormatDataKey}.
     */
    private final Map<SemanticEdgeFormatDataKey, Map<String, EdgeFormatData>> edgeFormatDataMap = new HashMap<SemanticEdgeFormatDataKey, Map<String, EdgeFormatData>>();

    /**
     * Default constructor.
     */
    public SiriusFormatDataManagerForSemanticElements() {
        // Nothing.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager#addFormatData(org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey,
     *      org.eclipse.sirius.diagram.formatdata.AbstractFormatData)
     */
    @Override
    public void addFormatData(final FormatDataKey key, final AbstractFormatData formatData) {
        clearFormatData();
        throw new UnsupportedOperationException(ERROR_MESSAGE_UNSUPPORTED_METHOD_CALL);
    }

    @Override
    public void addFormatData(FormatDataKey key, RepresentationElementMapping mapping, AbstractFormatData formatData) {
        if (!checkKeyType(key)) {
            // Kind of key not manage
            formatData.setId(null);
            return;
        }

        if (key instanceof SemanticNodeFormatDataKey) {
            if (formatData instanceof NodeFormatData) {
                Map<String, NodeFormatData> formatsMap = nodeFormatDataMap.get(key);
                if (formatsMap == null) {
                    formatsMap = new TreeMap<String, NodeFormatData>();
                    nodeFormatDataMap.put((SemanticNodeFormatDataKey) key, formatsMap);
                }

                formatsMap.put(mapping.getName(), (NodeFormatData) formatData);
            } else {
                clearFormatData();
                throw new IllegalStateException(ERROR_MESSAGE_UNSUPPORTED_FORMAT_DATA_TYPE + formatData.getClass().getSimpleName());
            }
        } else if (key instanceof SemanticEdgeFormatDataKey) {
            if (formatData instanceof EdgeFormatData) {
                Map<String, EdgeFormatData> formatsMap = edgeFormatDataMap.get(key);
                if (formatsMap == null) {
                    formatsMap = new TreeMap<String, EdgeFormatData>();
                    edgeFormatDataMap.put((SemanticEdgeFormatDataKey) key, formatsMap);
                }

                formatsMap.put(mapping.getName(), (EdgeFormatData) formatData);
            } else {
                clearFormatData();
                throw new IllegalStateException(ERROR_MESSAGE_UNSUPPORTED_FORMAT_DATA_TYPE + formatData.getClass().getSimpleName());
            }
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager#getFormatData(org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey)
     */
    @Override
    public AbstractFormatData getFormatData(final FormatDataKey key) {
        clearFormatData();
        throw new UnsupportedOperationException(ERROR_MESSAGE_UNSUPPORTED_METHOD_CALL);
    }

    @Override
    public AbstractFormatData getFormatData(FormatDataKey key, RepresentationElementMapping mapping) {
        AbstractFormatData result = null;
        if (checkKeyType(key)) {
            String mappingName = mapping == null ? null : mapping.getName();
            if (key instanceof SemanticNodeFormatDataKey) {
                Map<String, NodeFormatData> formatsMap = nodeFormatDataMap.get(key);
                if (formatsMap != null) {
                    NodeFormatData nodeFormatData = formatsMap.get(mappingName);
                    if (nodeFormatData == null && formatsMap.size() > 0) {
                        result = formatsMap.values().iterator().next();
                    } else {
                        result = nodeFormatData;
                    }
                }

            } else if (key instanceof SemanticEdgeFormatDataKey) {
                Map<String, EdgeFormatData> formatsMap = edgeFormatDataMap.get(key);
                if (formatsMap != null) {
                    EdgeFormatData edgeFormatData = formatsMap.get(mappingName);
                    if (edgeFormatData == null && formatsMap.size() > 0) {
                        result = formatsMap.values().iterator().next();
                    } else {
                        result = edgeFormatData;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Check if the key is manage by this manager.
     * 
     * @param key
     *            the key to check
     * @return
     */
    private boolean checkKeyType(final FormatDataKey key) {
        return key instanceof SemanticNodeFormatDataKey || key instanceof SemanticEdgeFormatDataKey;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager#getInstance()
     */
    public SiriusFormatDataManager getInstance() {
        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager#createKey(org.eclipse.sirius.viewpoint.DSemanticDecorator)
     */
    @Override
    public FormatDataKey createKey(final DSemanticDecorator semanticDecorator) {
        FormatDataKey result = null;
        final EObject realSemanticElement = semanticDecorator.getTarget();
        if (semanticDecorator instanceof DEdge) {
            result = new SemanticEdgeFormatDataKey(realSemanticElement);
        } else if (semanticDecorator instanceof AbstractDNode || semanticDecorator instanceof DDiagram) {
            result = new SemanticNodeFormatDataKey(realSemanticElement);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager#containsData()
     */
    @Override
    public boolean containsData() {
        return !nodeFormatDataMap.isEmpty() || !edgeFormatDataMap.isEmpty();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager#clearFormatData()
     */
    @Override
    public void clearFormatData() {
        nodeFormatDataMap.clear();
        edgeFormatDataMap.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<SemanticEdgeFormatDataKey, Map<String, EdgeFormatData>> getEdgeFormatData() {
        return edgeFormatDataMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<? extends NodeFormatDataKey, Map<String, NodeFormatData>> getRootNodeFormatData() {
        return getRootFormatData(nodeFormatDataMap);
    }

    /**
     * {@inheritDoc}
     */
    public Map<? extends NodeFormatDataKey, Map<String, NodeFormatData>> getRootFormatData(final Map<SemanticNodeFormatDataKey, Map<String, NodeFormatData>> theNodeFormatDataMap) {
        Map<NodeFormatDataKey, Map<String, NodeFormatData>> resultValues = new HashMap<NodeFormatDataKey, Map<String, NodeFormatData>>();
        Set<Entry<SemanticNodeFormatDataKey, Map<String, NodeFormatData>>> allEntries = theNodeFormatDataMap.entrySet();
        for (Entry<SemanticNodeFormatDataKey, Map<String, NodeFormatData>> entry : allEntries) {
            Map<String, NodeFormatData> value = entry.getValue();
            Map<String, NodeFormatData> filterValues = Maps.filterValues(value, ROOT_PREDICATE);
            if (filterValues.size() > 0) {
                resultValues.put(entry.getKey(), filterValues);
            }
        }
        return resultValues;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<? extends NodeFormatDataKey, Map<String, NodeFormatData>> getNodeFormatData() {
        return nodeFormatDataMap;
    }

}
