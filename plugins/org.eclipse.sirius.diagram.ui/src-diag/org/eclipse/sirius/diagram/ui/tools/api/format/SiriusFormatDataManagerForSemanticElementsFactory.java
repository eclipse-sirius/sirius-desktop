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
package org.eclipse.sirius.diagram.ui.tools.api.format;

import org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.SiriusFormatDataManagerForSemanticElements;

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
