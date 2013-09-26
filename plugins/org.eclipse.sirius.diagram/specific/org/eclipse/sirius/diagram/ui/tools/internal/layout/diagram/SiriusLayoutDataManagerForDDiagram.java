/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.layout.diagram;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData;
import org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.NodeLayoutData;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataKey;
import org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * SiriusLayoutDataManager drives by the DDiagram (DNode, DEdge, ...). Use
 * for drag'n'drop and creation process.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SiriusLayoutDataManagerForDDiagram implements SiriusLayoutDataManager {
    Map<DNodeLayoutDataKey, NodeLayoutData> nodeLayoutDataMap = new HashMap<DNodeLayoutDataKey, NodeLayoutData>();

    Map<DEdgeLayoutDataKey, EdgeLayoutData> edgeLayoutDataMap = new HashMap<DEdgeLayoutDataKey, EdgeLayoutData>();

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager#addLayoutData(org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataKey,
     *      org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData)
     */
    public void addLayoutData(final LayoutDataKey key, final AbstractLayoutData layoutData) {
        if (!checkKeyType(key)) {
            // Kind of key not manage
            return;
        }
        if (key instanceof DNodeLayoutDataKey) {
            if (layoutData instanceof NodeLayoutData) {
                nodeLayoutDataMap.put((DNodeLayoutDataKey) key, (NodeLayoutData) layoutData);
            } else {
                // Bad type of layoutData for this key
            }
        } else if (key instanceof DEdgeLayoutDataKey) {
            if (layoutData instanceof EdgeLayoutData) {
                edgeLayoutDataMap.put((DEdgeLayoutDataKey) key, (EdgeLayoutData) layoutData);
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
    public AbstractLayoutData getLayoutData(final LayoutDataKey key) {
        AbstractLayoutData result = null;
        if (checkKeyType(key)) {
            if (key instanceof DNodeLayoutDataKey) {
                result = nodeLayoutDataMap.get(key);
            } else if (key instanceof DEdgeLayoutDataKey) {
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
        return key instanceof DNodeLayoutDataKey || key instanceof DEdgeLayoutDataKey;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager#applyLayout(org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart)
     */
    public void applyLayout(final IGraphicalEditPart rootEditPart) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager#clearLayoutData()
     */
    public void clearLayoutData() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager#containsData()
     */
    public boolean containsData() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager#createKey(org.eclipse.sirius.viewpoint.DSemanticDecorator)
     */
    public LayoutDataKey createKey(final DSemanticDecorator semanticDecorator) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager#getInstance()
     */
    public SiriusLayoutDataManager getInstance() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager#storeLayoutData(org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart)
     */
    public void storeLayoutData(final IGraphicalEditPart rootEditPart) {
        // TODO Auto-generated method stub

    }
}
