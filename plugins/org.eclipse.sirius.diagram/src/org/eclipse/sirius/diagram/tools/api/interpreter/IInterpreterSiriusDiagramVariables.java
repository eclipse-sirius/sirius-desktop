/*******************************************************************************
 * Copyright (c) 2022 Obeo.
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
package org.eclipse.sirius.diagram.tools.api.interpreter;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.diagram.DEdge;

/**
 * Sirius variables strings (specific for diagram).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * @since 7.0.2
 */
// CHECKSTYLE:OFF
public interface IInterpreterSiriusDiagramVariables extends IInterpreterSiriusVariables {
    /**
     * The name of the variable representing the edge end (graphical element) that is not changed during the
     * reconnection.
     */
    String OTHER_END_VARIABLE_NAME = "otherEnd"; //$NON-NLS-1$

    /**
     * The name of the variable representing the edge view, ie a {@link DEdge}, after the graphical reconnection. If
     * used in the precondition, this variable is the edge view currently being reconnected.
     */
    String EDGE_VIEW_VARIABLE_NAME = "edgeView"; //$NON-NLS-1$
}
// CHECKSTYLE:ON
