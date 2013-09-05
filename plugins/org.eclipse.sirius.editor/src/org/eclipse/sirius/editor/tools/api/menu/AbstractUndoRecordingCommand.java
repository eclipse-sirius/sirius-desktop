/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.api.menu;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * A simple recording command not needing any transactional editing domain.
 * That's useful if you don't want to code the "undo" for your command.
 * 
 * @author cbrun
 * 
 */
public abstract class AbstractUndoRecordingCommand extends AbstractCommand {

    private ChangeDescription changes;

    private final ResourceSet set;

    /**
     * Create a new abstract undo recording command.
     * 
     * @param set
     *            the current resourceset.
     */
    public AbstractUndoRecordingCommand(final ResourceSet set) {
        this.set = set;
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        final ChangeRecorder recorder = new ChangeRecorder(set);
        doExecute();
        changes = recorder.endRecording();
        recorder.dispose();
    }

    /**
     * This method should be implemented to specify what is the command
     * behavior.
     */
    protected abstract void doExecute();

    /**
     * This method should be implemented to return a label for the command.
     * 
     * @return a label for the command.
     */
    protected abstract String getText();

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLabel() {
        return getText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canExecute() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void undo() {
        if (changes != null) {
            changes.applyAndReverse();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void redo() {
        if (changes != null) {
            changes.applyAndReverse();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        super.dispose();
        if (changes != null) {
            changes = null;
        }
    }

}
