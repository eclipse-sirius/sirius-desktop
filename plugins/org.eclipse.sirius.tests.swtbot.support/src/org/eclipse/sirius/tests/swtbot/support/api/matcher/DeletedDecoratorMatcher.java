/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.matcher;

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

        return DiagramUIPlugin.getPlugin().getImage(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DELETE_FROM_DIAGRAM_ICON));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("conformance info decorator matcher '"); //$NON-NLS-1$

    }
}
