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
package org.eclipse.sirius.diagram.ui.tools.api.layout;

import org.eclipse.sirius.diagram.ui.tools.internal.layout.semantic.SiriusLayoutDataManagerForSemanticElements;

/**
 * A factory to give access to a {@link SiriusLayoutDataManager} managed by
 * semantic elements.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * @deprecated Since Sirius 4.1.0. Use
 *             {@link org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManagerForSemanticElementsFactory}
 *             instead.
 */
@Deprecated
public class SiriusLayoutDataManagerForSemanticElementsFactory {
    private static final SiriusLayoutDataManagerForSemanticElementsFactory INSTANCE = new SiriusLayoutDataManagerForSemanticElementsFactory();

    private static final SiriusLayoutDataManagerForSemanticElements VIEWPOINT_LAYOUT_DATA_MANAGER = new SiriusLayoutDataManagerForSemanticElements();

    /**
     * gives access to the singleton instance of
     * <code>SiriusLayoutDataManagerForSemanticElementsFactory</code>.
     * 
     * @return the singleton instance
     */
    public static SiriusLayoutDataManagerForSemanticElementsFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Get the {@link SiriusLayoutDataManager}.
     * 
     * @return an instance of {@link SiriusLayoutDataManagerForSemanticElements}
     */
    public SiriusLayoutDataManager getSiriusLayoutDataManager() {
        return VIEWPOINT_LAYOUT_DATA_MANAGER;
    }
}
