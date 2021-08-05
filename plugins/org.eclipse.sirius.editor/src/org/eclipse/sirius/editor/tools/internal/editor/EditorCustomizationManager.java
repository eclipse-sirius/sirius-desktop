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
package org.eclipse.sirius.editor.tools.internal.editor;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.editor.tools.api.editor.EditorCustomization;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * Manager responsible to retrieve all the customization from the other customizer and aggregate them.
 * 
 * @author cbrun
 * 
 */
public class EditorCustomizationManager {
    private static final String ID = "org.eclipse.sirius.editor.editorCustomization";

    private static final String CLASS_ATTRIBUTE = "class";

    /**
     * singleton instance.
     */
    private static EditorCustomizationManager eINSTANCE = EditorCustomizationManager.init();

    private final Collection<EditorCustomization> customizers = new LinkedHashSet<EditorCustomization>();

    /**
     * Initialization of the manager.
     * 
     * @return the instance of the manager
     */
    public static EditorCustomizationManager init() {
        final EditorCustomizationManager manager = new EditorCustomizationManager();
        if (SiriusPlugin.IS_ECLIPSE_RUNNING) {
            final List<EditorCustomization> contributedCustomizer = EclipseUtil.getExtensionPlugins(EditorCustomization.class, EditorCustomizationManager.ID,
                    EditorCustomizationManager.CLASS_ATTRIBUTE);
            for (final EditorCustomization menuBuilder : contributedCustomizer) {
                manager.add(menuBuilder);
            }
        }
        return manager;
    }

    private void add(final EditorCustomization editorCustomizer) {
        this.customizers.add(editorCustomizer);
    }

    /**
     * Tell whether the meta element should be hidden or not.
     * 
     * @param feature
     *            meta element to check
     * @return true if the meta element should be hidden in the editor.
     */
    public boolean isHidden(final EModelElement feature) {
        for (final EditorCustomization editorCustomization : customizers) {
            if (editorCustomization.isHidden(feature)) {
                return true;
            }
        }
        return false;

    }

    /**
     * return true if the "all" tab with all the features should be displayed.
     * 
     * @return true if the "all" tab with all the features should be displayed.
     */
    public boolean showTheAllTab() {
        for (final EditorCustomization editorCustomization : customizers) {
            if (!editorCustomization.showAllTab()) {
                return false;
            }
        }
        return true;
    }

    /**
     * return the singleton instance.
     * 
     * @return the singleton instance
     */
    public static EditorCustomizationManager getInstance() {
        return eINSTANCE;
    }

}
