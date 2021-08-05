/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.editor.tools.internal.menu;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * A manager for the menu builders contributions.
 * 
 * @author cbrun
 * 
 */
public class MenuBuildersManager {
    /**
     * Singleton instance for a given Eclipse instance.
     */
    private static MenuBuildersManager eINSTANCE = MenuBuildersManager.init();

    private static final String ID = "org.eclipse.sirius.editor.menuBuilder";

    private static final String CLASS_ATTRIBUTE = "class";

    private final Collection<AbstractMenuBuilder> builders = new LinkedHashSet<AbstractMenuBuilder>();

    /**
     * Initialization of the manager.
     * 
     * @return the instance of the manager
     */
    public static MenuBuildersManager init() {
        final MenuBuildersManager manager = new MenuBuildersManager();
        if (SiriusPlugin.IS_ECLIPSE_RUNNING) {
            final List<AbstractMenuBuilder> contributedBuilders = EclipseUtil.getExtensionPlugins(AbstractMenuBuilder.class, MenuBuildersManager.ID, MenuBuildersManager.CLASS_ATTRIBUTE);
            for (final AbstractMenuBuilder menuBuilder : contributedBuilders) {
                manager.add(menuBuilder);
            }
        }
        return manager;
    }

    private void add(final AbstractMenuBuilder menuBuilder) {
        this.builders.add(menuBuilder);
    }

    public Collection<AbstractMenuBuilder> getContributedMenuBuilders() {
        return Collections.unmodifiableCollection(builders);
    }

    /**
     * return the singleton instance.
     * 
     * @return the singleton instance.
     */
    public static MenuBuildersManager getInstance() {
        return eINSTANCE;
    }
}
