/*******************************************************************************
 * Copyright (c) 2014 Obeo.
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

import java.util.LinkedHashSet;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.sirius.editor.tools.api.editor.EditorCustomization;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * We deactivate the SetEObject model operation.
 * 
 * @author mporhel
 * 
 */
public class SiriusCoreEditorCustomization implements EditorCustomization {

    // Features referenced in this list will not have corresponding new child
    // creation menus.
    private final LinkedHashSet<EModelElement> deprecation;

    /**
     * Create the customization.
     */
    public SiriusCoreEditorCustomization() {
        deprecation = new LinkedHashSet<EModelElement>();
        deprecation.add(ToolPackage.eINSTANCE.getSetObject());
    }

    /**
     * {@inheritDoc}
     */
    public boolean isHidden(final EModelElement metaElement) {
        return deprecation.contains(metaElement);
    }

    /**
     * {@inheritDoc}
     */
    public boolean showAllTab() {
        return false;
    }
}
