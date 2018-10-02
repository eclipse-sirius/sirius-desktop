/*******************************************************************************
 * Copyright (c) 2009, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.format;

import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.SiriusFormatDataManagerForSemanticElements;

/**
 * A factory to give access to a {@link SiriusFormatDataManager} managed by
 * semantic elements.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SiriusFormatDataManagerForSemanticElementsFactory {
    private static final SiriusFormatDataManagerForSemanticElementsFactory INSTANCE = new SiriusFormatDataManagerForSemanticElementsFactory();

    private static final SiriusFormatDataManagerForSemanticElements SIRIUS_FORMAT_DATA_MANAGER = new SiriusFormatDataManagerForSemanticElements();

    /**
     * gives access to the singleton instance of
     * <code>SiriusFormatDataManagerForSemanticElementsFactory</code>.
     * 
     * @return the singleton instance
     */
    public static SiriusFormatDataManagerForSemanticElementsFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Get the {@link SiriusFormatDataManager}.
     * 
     * @return an instance of {@link SiriusFormatDataManagerForSemanticElements}
     */
    public SiriusFormatDataManager getSiriusFormatDataManager() {
        return SIRIUS_FORMAT_DATA_MANAGER;
    }
}
