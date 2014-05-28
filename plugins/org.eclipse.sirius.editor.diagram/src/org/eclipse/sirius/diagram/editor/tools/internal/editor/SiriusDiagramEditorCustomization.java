/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.editor.tools.internal.editor;

import java.util.LinkedHashSet;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.editor.tools.api.editor.EditorCustomization;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * We deactivate the tool section creation menu on DiagramDescription. We
 * shouldn't have tool section out of a layer.
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
