/*******************************************************************************
 * Copyright (c) 2018 TypeFox and others.
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
 * Used to request the model.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramRequestModelAction extends AbstractSiriusDiagramAction {

    /**
     * The kind of the action.
     */
    public static final String KIND = "requestModel"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    public SiriusDiagramRequestModelAction() {
        super(KIND);
    }

}
