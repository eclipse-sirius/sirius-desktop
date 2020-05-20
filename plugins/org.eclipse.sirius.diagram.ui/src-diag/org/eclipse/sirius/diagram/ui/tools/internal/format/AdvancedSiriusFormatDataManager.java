/*******************************************************************************
 * Copyright (c) 2009, 2020 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.format;

import java.util.Map;

import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager;

/**
 * Interface to manage {@link org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager} .
 * 
 * @author dlecan
 */
public interface AdvancedSiriusFormatDataManager extends SiriusFormatDataManager {

    /**
     * Get only root node format data, that is to say only the format data without parent.
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
    Map<? extends EdgeFormatDataKey, Map<String, EdgeFormatData>> getEdgeFormatData();

}
