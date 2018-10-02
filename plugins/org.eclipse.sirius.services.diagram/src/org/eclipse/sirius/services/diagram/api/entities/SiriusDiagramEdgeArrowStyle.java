/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.services.diagram.api.entities;

/**
 * The style of the arrows of the edge.
 *
 * @author sbegaudeau
 */
public enum SiriusDiagramEdgeArrowStyle {
    /**
     * The diamond style.
     */
    DIAMOND,

    /**
     * The fill diamond style.
     */
    FILL_DIAMOND,

    /**
     * The input arrow style.
     */
    INPUT_ARROW,

    /**
     * The input arrow with diamond style.
     */
    INPUT_ARROW_WITH_DIAMOND,

    /**
     * The input arrow with fill diamond style.
     */
    INPUT_ARROW_WITH_FILL_DIAMOND,

    /**
     * The input closed arrow style.
     */
    INPUT_CLOSED_ARROW,

    /**
     * The input fill closed arrow style.
     */
    INPUT_FILL_CLOSED_ARROW,

    /**
     * The no decoration style.
     */
    NO_DECORATION,

    /**
     * The ouput arrow style.
     */
    OUTPUT_ARROW,

    /**
     * The ouput closed arrow style.
     */
    OUTPUT_CLOSED_ARROW,

    /**
     * The ouput fill closed arrow style.
     */
    OUTPUT_FILL_CLOSED_ARROW
}
