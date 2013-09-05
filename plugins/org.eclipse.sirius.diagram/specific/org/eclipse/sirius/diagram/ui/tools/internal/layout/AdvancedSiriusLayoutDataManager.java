/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.layout;

import java.util.Map;

import org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.NodeLayoutData;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataKey;
import org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager;

/**
 * Interface to manage
 * {@link org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager}
 * .
 * 
 * @author dlecan
 */
public interface AdvancedSiriusLayoutDataManager extends SiriusLayoutDataManager {

    /**
     * Get only root node layout data, that is to say only the layout data
     * without parent.
     * 
     * @return Map.
     */
    Map<? extends LayoutDataKey, ? extends NodeLayoutData> getRootNodeLayoutData();

    /**
     * Get node layout data.
     * 
     * @return Map.
     */
    Map<? extends NodeLayoutDataKey, NodeLayoutData> getNodeLayoutData();

    /**
     * Get edge layout data.
     * 
     * @return Map.
     */
    Map<? extends EdgeLayoutDataKey, EdgeLayoutData> getEdgeLayoutData();

}
