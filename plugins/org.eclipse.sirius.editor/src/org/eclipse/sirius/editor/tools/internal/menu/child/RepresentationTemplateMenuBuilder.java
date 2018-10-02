/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.editor.tools.internal.menu.child;

import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.editor.tools.api.menu.AbstractTypeRestrictingMenuBuilder;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * The representations template menu.
 * 
 * @author cbrun
 * 
 */
public class RepresentationTemplateMenuBuilder extends AbstractTypeRestrictingMenuBuilder {
    /**
     * Build the menu.
     */
    public RepresentationTemplateMenuBuilder() {
        super();
        addValidType(DescriptionPackage.eINSTANCE.getRepresentationTemplate());
    }

    @Override
    public String getLabel() {
        return "New Template";
    }

    @Override
    public int getPriority() {
        return AbstractMenuBuilder.TEMPLATE;
    }
}
