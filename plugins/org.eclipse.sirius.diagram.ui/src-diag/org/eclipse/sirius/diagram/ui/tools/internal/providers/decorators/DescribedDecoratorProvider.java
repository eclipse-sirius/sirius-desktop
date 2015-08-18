/*******************************************************************************
 * Copyright (c) 2008, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.providers.decorators;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNameEditPart;

/**
 * This decorator is installed on SiriusElements edit parts. It display an
 * icon when the element provides detail diagrams.
 * 
 * @author mPorhel
 * 
 */
public class DescribedDecoratorProvider extends AbstractProvider implements IDecoratorProvider {

    /**
     * KEY for descriptors map.
     */
    public static final String KEY = "describedDecorator"; //$NON-NLS-1$

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
     */
    public boolean provides(final IOperation operation) {
        if (!(operation instanceof CreateDecoratorsOperation)) {
            return false;
        }
        boolean provide = false;
        final IDecoratorTarget decoratorTarget = ((CreateDecoratorsOperation) operation).getDecoratorTarget();
        final EditPart editPart = (EditPart) decoratorTarget.getAdapter(EditPart.class);
        if (editPart instanceof IDiagramElementEditPart && !(editPart instanceof IDiagramNameEditPart)) {
            provide = true;
        }
        return provide;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider#createDecorators(org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget)
     */
    public void createDecorators(final IDecoratorTarget decoratorTarget) {
        final EditPart editPart = (EditPart) decoratorTarget.getAdapter(EditPart.class);
        if (editPart instanceof IDiagramElementEditPart) {
            decoratorTarget.installDecorator(KEY, new DescribedDecorator(decoratorTarget));
        }
    }
}
