/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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

import org.eclipse.sirius.editor.tools.api.menu.AbstractTypeRestrictingMenuBuilder;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * Menu builder for the elements under a group, the viewpoint and the user
 * colors palette.
 * 
 * @author sbegaudeau
 */
public class ViewpointAndPaletteMenuBuilder extends AbstractTypeRestrictingMenuBuilder {

    /**
     * The constructor.
     */
    public ViewpointAndPaletteMenuBuilder() {
        this.addValidType(DescriptionPackage.Literals.VIEWPOINT);
        this.addValidType(DescriptionPackage.Literals.USER_COLORS_PALETTE);
    }

    @Override
    public String getLabel() {
        return "New";
    }

    @Override
    public int getPriority() {
        // The "lowest" priority in order to always be the first menu
        return 0;
    }

}
