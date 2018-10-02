/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.editor.tools.internal.editor;

import java.util.LinkedHashSet;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.editor.tools.api.editor.EditorCustomization;

/**
 * We deactivate several menu creations. We shouldn't have tool section or
 * mappings out of a layer.
 * 
 * @author fbarbin
 * 
 */
public class SiriusDiagramEditorCustomization implements EditorCustomization {

    // Features referenced in this list will not have corresponding new child
    // creation menus.
    private final LinkedHashSet<EModelElement> deprecation;

    /**
     * Create the customization.
     */
    public SiriusDiagramEditorCustomization() {
        deprecation = new LinkedHashSet<EModelElement>();
        deprecation.add(DescriptionPackage.eINSTANCE.getDiagramDescription_NodeMappings());
        deprecation.add(DescriptionPackage.eINSTANCE.getDiagramDescription_EdgeMappings());
        deprecation.add(DescriptionPackage.eINSTANCE.getDiagramDescription_ContainerMappings());
        deprecation.add(DescriptionPackage.eINSTANCE.getDiagramDescription_EdgeMappingImports());
        deprecation.add(DescriptionPackage.eINSTANCE.getDiagramDescription_ToolSection());
        deprecation.add(DescriptionPackage.eINSTANCE.getDiagramDescription_ReusedTools());
        deprecation.add(DescriptionPackage.eINSTANCE.getDiagramDescription_ReusedMappings());
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
