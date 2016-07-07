/*******************************************************************************
 * Copyright (c) 2009, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.layout.semantic;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData;
import org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.NodeLayoutData;
import org.eclipse.sirius.diagram.ui.tools.api.layout.AbstractSiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataHelper;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataKey;
import org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.AdvancedSiriusLayoutDataManager;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * SiriusLayoutDataManager drives by the semantic elements (EObject). Use for
 * layout duplication.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SiriusLayoutDataManagerForSemanticElements extends AbstractSiriusLayoutDataManager implements AdvancedSiriusLayoutDataManager {

    private static final SiriusLayoutDataManagerForSemanticElements INSTANCE = new SiriusLayoutDataManagerForSemanticElements();

    private final Map<SemanticNodeLayoutDataKey, NodeLayoutData> nodeLayoutDataMap = new HashMap<SemanticNodeLayoutDataKey, NodeLayoutData>();

    private final Map<SemanticEdgeLayoutDataKey, EdgeLayoutData> edgeLayoutDataMap = new HashMap<SemanticEdgeLayoutDataKey, EdgeLayoutData>();

    /**
     * Default constructor.
     */
    public SiriusLayoutDataManagerForSemanticElements() {
        // Nothing.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager#addLayoutData(org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataKey,
     *      org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData)
     */
    @Override
    public void addLayoutData(final LayoutDataKey key, final AbstractLayoutData layoutData) {
        if (!checkKeyType(key)) {
            // Kind of key not manage
            layoutData.setId(null);
            return;
        }

        if (key instanceof SemanticNodeLayoutDataKey) {
            if (layoutData instanceof NodeLayoutData) {
                nodeLayoutDataMap.put((SemanticNodeLayoutDataKey) key, (NodeLayoutData) layoutData);
            } else {
                // Bad type of layoutData for this key
            }
        } else if (key instanceof SemanticEdgeLayoutDataKey) {
            if (layoutData instanceof EdgeLayoutData) {
                edgeLayoutDataMap.put((SemanticEdgeLayoutDataKey) key, (EdgeLayoutData) layoutData);
            } else {
                // Bad type of layoutData for this key
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager#getLayoutData(org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataKey)
     */
    @Override
    public AbstractLayoutData getLayoutData(final LayoutDataKey key) {
        AbstractLayoutData result = null;
        if (checkKeyType(key)) {
            if (key instanceof SemanticNodeLayoutDataKey) {
                result = nodeLayoutDataMap.get(key);
            } else if (key instanceof SemanticEdgeLayoutDataKey) {
                result = edgeLayoutDataMap.get(key);
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
    private boolean checkKeyType(final LayoutDataKey key) {
        return key instanceof SemanticNodeLayoutDataKey || key instanceof SemanticEdgeLayoutDataKey;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager#getInstance()
     */
    public SiriusLayoutDataManager getInstance() {
        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager#createKey(org.eclipse.sirius.viewpoint.DSemanticDecorator)
     */
    @Override
    public LayoutDataKey createKey(final DSemanticDecorator semanticDecorator) {
        LayoutDataKey result = null;
        final EObject realSemanticElement = semanticDecorator.getTarget();
        if (semanticDecorator instanceof DEdge) {
            result = new SemanticEdgeLayoutDataKey(realSemanticElement);
        } else if (semanticDecorator instanceof AbstractDNode || semanticDecorator instanceof DDiagram) {
            result = new SemanticNodeLayoutDataKey(realSemanticElement);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager#containsData()
     */
    @Override
    public boolean containsData() {
        return !nodeLayoutDataMap.isEmpty() || !edgeLayoutDataMap.isEmpty();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager#clearLayoutData()
     */
    @Override
    public void clearLayoutData() {
        nodeLayoutDataMap.clear();
        edgeLayoutDataMap.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<SemanticEdgeLayoutDataKey, EdgeLayoutData> getEdgeLayoutData() {
        return edgeLayoutDataMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<? extends LayoutDataKey, ? extends NodeLayoutData> getRootNodeLayoutData() {
        return (Map<? extends LayoutDataKey, ? extends NodeLayoutData>) LayoutDataHelper.INSTANCE.getRootLayoutData(nodeLayoutDataMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<SemanticNodeLayoutDataKey, NodeLayoutData> getNodeLayoutData() {
        return nodeLayoutDataMap;
    }
}
