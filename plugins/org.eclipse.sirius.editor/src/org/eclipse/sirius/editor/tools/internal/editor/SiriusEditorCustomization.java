/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.editor;

import org.eclipse.emf.ecore.EModelElement;

import org.eclipse.sirius.description.DescriptionPackage;
import org.eclipse.sirius.editor.tools.api.editor.EditorCustomization;

/**
 * We deactivate the tool section creation menu on DiagramDescription. We
 * shouldn't have tool section out of a layer.
 * 
 * @author fbarbin
 * 
 */
public class SiriusEditorCustomization implements EditorCustomization {
    /**
     * {@inheritDoc}
     */
    public boolean isHidden(EModelElement metaElement) {
        return metaElement.equals(DescriptionPackage.eINSTANCE.getDiagramDescription_ToolSection());
    }

    /**
     * {@inheritDoc}
     */
    public boolean showAllTab() {
        return false;
    }

}
