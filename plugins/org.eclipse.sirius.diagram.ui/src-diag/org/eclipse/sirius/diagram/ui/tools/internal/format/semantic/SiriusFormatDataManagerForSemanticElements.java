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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.formatdata.AbstractFormatData;
import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.ui.tools.api.format.AbstractSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataHelper;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.format.AdvancedSiriusFormatDataManager;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * SiriusFormatDataManager drives by the semantic elements (EObject). Use for
 * format duplication.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SiriusFormatDataManagerForSemanticElements extends AbstractSiriusFormatDataManager implements AdvancedSiriusFormatDataManager {

    private static final SiriusFormatDataManagerForSemanticElements INSTANCE = new SiriusFormatDataManagerForSemanticElements();

    private final Map<SemanticNodeFormatDataKey, NodeFormatData> nodeFormatDataMap = new HashMap<SemanticNodeFormatDataKey, NodeFormatData>();

    private final Map<SemanticEdgeFormatDataKey, EdgeFormatData> edgeFormatDataMap = new HashMap<SemanticEdgeFormatDataKey, EdgeFormatData>();

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
        if (!checkKeyType(key)) {
            // Kind of key not manage
            formatData.setId(null);
            return;
        }

        if (key instanceof SemanticNodeFormatDataKey) {
            if (formatData instanceof NodeFormatData) {
                nodeFormatDataMap.put((SemanticNodeFormatDataKey) key, (NodeFormatData) formatData);
            } else {
                // Bad type of formatData for this key
            }
        } else if (key instanceof SemanticEdgeFormatDataKey) {
            if (formatData instanceof EdgeFormatData) {
                edgeFormatDataMap.put((SemanticEdgeFormatDataKey) key, (EdgeFormatData) formatData);
            } else {
                // Bad type of formatData for this key
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
        AbstractFormatData result = null;
        if (checkKeyType(key)) {
            if (key instanceof SemanticNodeFormatDataKey) {
                result = nodeFormatDataMap.get(key);
            } else if (key instanceof SemanticEdgeFormatDataKey) {
                result = edgeFormatDataMap.get(key);
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
    public Map<SemanticEdgeFormatDataKey, EdgeFormatData> getEdgeFormatData() {
        return edgeFormatDataMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<? extends FormatDataKey, ? extends NodeFormatData> getRootNodeFormatData() {
        return (Map<? extends FormatDataKey, ? extends NodeFormatData>) FormatDataHelper.INSTANCE.getRootFormatData(nodeFormatDataMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<SemanticNodeFormatDataKey, NodeFormatData> getNodeFormatData() {
        return nodeFormatDataMap;
    }
}
