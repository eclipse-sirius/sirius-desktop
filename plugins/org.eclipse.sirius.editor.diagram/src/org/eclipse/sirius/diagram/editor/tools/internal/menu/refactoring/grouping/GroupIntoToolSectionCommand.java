/*******************************************************************************
 * Copyright (c) 2014 - Joao Martins and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Joao Martins <joaomartins27396@gmail.com> - initial API and implementation 
 *   Maxime Porhel <maxime.porhel@obeo.fr> Obeo - Bug 434698, remarks and correction during review.
 *******************************************************************************/

package org.eclipse.sirius.diagram.editor.tools.internal.menu.refactoring.grouping;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Group the current selection on ToolSection.
 * 
 * @author Joao Martins
 * 
 */
public class GroupIntoToolSectionCommand extends AbstractToolGroupIntoCommand {

    /**
     * Label of this command.
     */
    public static final String TEXT = "Group into a new Tool Section";

    /**
     * Constructor.
     * 
     * @param set
     *            the current resourceset.
     * @param parent
     *            ToolSection to group the selection.
     * @param selection
     *            the current selection.
     */
    public GroupIntoToolSectionCommand(ResourceSet set, ToolSection parent, Collection<EObject> selection) {
        super(set, parent, selection);
    }

    @Override
    protected void doExecute() {
        ToolSection toolSection = ToolFactory.eINSTANCE.createToolSection();
        Iterables.addAll(toolSection.getOwnedTools(), Iterables.filter(selection, ToolEntry.class));
        this.parent.getSubSections().add(toolSection);
    }

    protected Predicate<Object> getSelectionFilter() {
        return Predicates.instanceOf(ToolEntry.class);
    }

    @Override
    protected String getText() {
        return TEXT;
    }

}
