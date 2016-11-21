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
package org.eclipse.sirius.diagram.ui.tools.internal.format;

import java.util.Map;

import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManagerWithMapping;
import org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.SemanticEdgeFormatDataKey;

/**
 * Interface to manage
 * {@link org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager}
 * .
 * 
 * @author dlecan
 */
public interface AdvancedSiriusFormatDataManager extends SiriusFormatDataManagerWithMapping {

    /**
     * Get only root node format data, that is to say only the format data
     * without parent.
     * 
     * @return Map.
     */
    Map<? extends NodeFormatDataKey, Map<String, NodeFormatData>> getRootNodeFormatData();

    /**
     * Get node format data.
     * 
     * @return Map.
     */
    Map<? extends NodeFormatDataKey, Map<String, NodeFormatData>> getNodeFormatData();

    /**
     * Get edge format data.
     * 
     * @return Map.
     */
    Map<SemanticEdgeFormatDataKey, Map<String, EdgeFormatData>> getEdgeFormatData();

}
