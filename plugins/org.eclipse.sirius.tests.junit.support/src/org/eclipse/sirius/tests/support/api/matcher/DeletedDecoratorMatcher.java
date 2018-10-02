/**
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.api.matcher;

import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.swt.graphics.Image;
import org.hamcrest.Description;

/**
 * This class helps to check if the graphical element has a deleted decorator.
 * 
 * @author amartin
 */
public class DeletedDecoratorMatcher extends AbstractDecoratorMatcher {
    @Override
    protected Image getImage() {

        return DiagramUIPlugin.getPlugin().getImage(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DELETED_DIAG_ELEM_DECORATOR_ICON));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("conformance info decorator matcher '"); //$NON-NLS-1$

    }
}
