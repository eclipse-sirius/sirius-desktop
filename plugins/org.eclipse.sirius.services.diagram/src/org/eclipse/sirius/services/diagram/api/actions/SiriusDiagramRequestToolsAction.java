/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.services.diagram.api.actions;

import org.eclipse.sirius.services.diagram.api.AbstractSiriusDiagramAction;

/**
 * The action used to request the tools.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramRequestToolsAction extends AbstractSiriusDiagramAction {

    /**
     * The kind of the action.
     */
    public static final String KIND = "requestTools"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    public SiriusDiagramRequestToolsAction() {
        super(KIND);
    }

}
