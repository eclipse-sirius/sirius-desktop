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
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.editor.tools.api.menu.AbstractUndoRecordingCommand;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Useful to create GroupingCommands.
 * 
 * @author Joao Martins
 * 
 */
public abstract class AbstractToolGroupIntoCommand extends AbstractUndoRecordingCommand {
    /**
     * Selected items to group.
     */
    protected Collection<EObject> selection;

    /**
     * Tool section in which the created item will be added.
     */
    protected ToolSection parent;

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
    public AbstractToolGroupIntoCommand(ResourceSet set, ToolSection parent, Collection<EObject> selection) {
        super(set);
        this.selection = selection;
        this.parent = parent;
    }

    /**
     * Checks current selection to determine if action can be executed.
     * 
     * @return true if selection is valid.
     */
    public boolean canExecute() {
        boolean canExecute = false;
        if (selection != null) {
            canExecute = Iterables.all(selection, getSelectionFilter());
        }
        return canExecute;
    }

    /**
     * To know the Selection.
     * 
     * @return the Selection depending of the action command.
     */
    protected abstract Predicate<Object> getSelectionFilter();
}
