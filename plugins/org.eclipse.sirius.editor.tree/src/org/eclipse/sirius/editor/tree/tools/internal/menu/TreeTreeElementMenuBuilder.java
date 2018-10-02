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
package org.eclipse.sirius.editor.tree.tools.internal.menu;

import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.editor.tools.api.menu.AbstractTypeRestrictingMenuBuilder;
import org.eclipse.sirius.tree.description.DescriptionPackage;

/**
 * Menu containing all Tree Elements.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class TreeTreeElementMenuBuilder extends AbstractTypeRestrictingMenuBuilder {

    /**
     * Initializes the TreeToolsMenuBuilder.
     * 
     */
    public TreeTreeElementMenuBuilder() {
        super();
        addValidType(DescriptionPackage.eINSTANCE.getTreeMapping());
    }

    @Override
    public String getLabel() {
        return "New Tree Element";
    }

    @Override
    public int getPriority() {
        return AbstractMenuBuilder.TREE_ELEMENT;
    }
}
