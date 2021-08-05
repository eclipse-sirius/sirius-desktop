/*******************************************************************************
 * Copyright (c) 2009, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.description.tool.spec;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.description.tool.impl.ToolSectionImpl;
import org.eclipse.sirius.viewpoint.description.tool.GroupMenu;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;

/**
 * Implementation of ToolSection.
 * 
 * @author mporhel
 */
public class ToolSectionSpec extends ToolSectionImpl {

    @Override
    public EList<PopupMenu> getPopupMenus() {
        final Set<PopupMenu> popupMenus = new LinkedHashSet<PopupMenu>();
        for (ToolEntry tool : getOwnedTools()) {
            if (tool instanceof PopupMenu) {
                popupMenus.add((PopupMenu) tool);
            }
        }
        return new EcoreEList.UnmodifiableEList<PopupMenu>(eInternalContainer(), ToolPackage.eINSTANCE.getToolSection_PopupMenus(), popupMenus.size(), popupMenus.toArray());
    }

    @Override
    public EList<GroupMenu> getGroups() {
        final Set<GroupMenu> groups = new LinkedHashSet<GroupMenu>();
        for (ToolEntry tool : getOwnedTools()) {
            if (tool instanceof GroupMenu) {
                groups.add((GroupMenu) tool);
            }
        }
        return new EcoreEList.UnmodifiableEList<GroupMenu>(eInternalContainer(), ToolPackage.eINSTANCE.getToolSection_Groups(), groups.size(), groups.toArray());
    }
}
