/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.decoration;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IPrimaryEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorKeys;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusUnresolvedViewDecorator;

/**
 * The goal of this class is to "disable" (replace by something that do
 * nothing), the decorators
 * {@link org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorKeys.BOOKMARK}
 * and
 * {@link org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorKeys.UNRESOLVED_VIEW}
 * provided by
 * {@link org.eclipse.gmf.runtime.diagram.ui.providers.internal.DiagramDecoratorProvider}
 * on Sirius diagram element edit parts and not used by Sirius. Even if they are
 * not used, they have performance cost during refresh of edit parts. This class
 * does not avoid the instantiation of BookmarkDecorator and
 * UnresolvedViewDecorator but it avoids to have cost during each refresh. It is
 * not possible without "modifying" GMF to avoid the instantiation.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
@SuppressWarnings("restriction")
public class SiriusBookmarkAndUnresolvedViewDecoratorProvider extends AbstractProvider implements IDecoratorProvider {

    @Override
    public boolean provides(IOperation operation) {
        Assert.isNotNull(operation);

        if (!(operation instanceof CreateDecoratorsOperation)) {
            return false;
        }

        IDecoratorTarget decoratorTarget = ((CreateDecoratorsOperation) operation).getDecoratorTarget();
        return decoratorTarget.getAdapter(View.class) != null;
    }

    @Override
    public void createDecorators(IDecoratorTarget decoratorTarget) {
        EditPart ep = decoratorTarget.getAdapter(EditPart.class);
        if (ep instanceof IPrimaryEditPart) {
            // Replace decorator installed by GMF by another one that do nothing
            decoratorTarget.installDecorator(IDecoratorKeys.BOOKMARK, new SiriusBookmarkDecorator(decoratorTarget));
            Object model = ep.getModel();
            if (!(model instanceof View))
                return;
            if (((View) model).getElement() != null) {
                // Replace decorator installed by GMF by another one that do
                // nothing
                decoratorTarget.installDecorator(IDecoratorKeys.UNRESOLVED_VIEW, new SiriusUnresolvedViewDecorator(decoratorTarget));
            }
        }
    }
}
