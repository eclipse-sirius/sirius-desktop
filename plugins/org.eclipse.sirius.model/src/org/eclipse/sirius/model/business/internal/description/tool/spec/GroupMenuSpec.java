/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.model.business.internal.description.tool.spec;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.viewpoint.description.tool.GroupMenuItem;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.impl.GroupMenuImpl;

/**
 * Implementation of PasteDescription.
 * 
 * @author lredor
 */
public class GroupMenuSpec extends GroupMenuImpl {

    @Override
    public EList<PopupMenu> getPopupMenus() {
        final Set<PopupMenu> popupMenus = new LinkedHashSet<PopupMenu>();
        for (GroupMenuItem item : getItemDescriptions()) {
            if (item instanceof PopupMenu) {
                popupMenus.add((PopupMenu) item);
            }
        }
        return new EcoreEList.UnmodifiableEList<PopupMenu>(eInternalContainer(), ToolPackage.eINSTANCE.getGroupMenu_PopupMenus(), popupMenus.size(), popupMenus.toArray());
    }

}
