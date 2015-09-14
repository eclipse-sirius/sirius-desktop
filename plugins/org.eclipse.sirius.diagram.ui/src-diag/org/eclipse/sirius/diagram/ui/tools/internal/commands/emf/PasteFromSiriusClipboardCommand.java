/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.commands.emf;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.clipboard.SiriusClipboardManager;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A command to paste from viewpoint clipboard into a new container.
 *
 * @author mporhel
 */
public class PasteFromSiriusClipboardCommand extends RecordingCommand {
    /**
     * The elements in which clipboard element should be pasted.
     */
    private final EObject container;

    private TransactionalEditingDomain domain;

    private Command paste;

    private Collection<Object> clipboard;

    /**
     * Creates a new {@link PasteFromSiriusClipboardCommand}.
     *
     * @param domain
     *            the current editing domain.
     *
     * @param semanticContainer
     *            the element which triggered the paste.
     */
    public PasteFromSiriusClipboardCommand(TransactionalEditingDomain domain, final EObject semanticContainer) {
        super(domain, Messages.PasteFromSiriusClipboardCommand_label);
        this.domain = domain;
        this.container = semanticContainer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean prepare() {
        prepareClipboard();

        paste = PasteFromClipboardCommand.create(domain, container, null);
        return paste.canExecute();
    }

    private void prepareClipboard() {
        // Initiate current domain clipboard from viewpoint multi session
        // clipboard
        SiriusClipboardManager.getInstance().setDomainClipboard(domain);
        clipboard = domain.getClipboard();

        // and filter DElements
        if (clipboard != null) {
            clipboard = Lists.newArrayList(Iterables.filter(clipboard, Predicates.not(Predicates.instanceOf(DSemanticDecorator.class))));
        }

        // add to current domain clipboard
        domain.setClipboard(clipboard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        // ResetClipboard (in case of multi-paste)
        domain.setClipboard(clipboard);

        paste.execute();
    }
}
