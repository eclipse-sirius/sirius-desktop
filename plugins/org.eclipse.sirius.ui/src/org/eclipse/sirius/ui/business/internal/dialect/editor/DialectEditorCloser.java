/*******************************************************************************
 * Copyright (c) 2012, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.internal.dialect.editor;

import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.swt.widgets.Display;

/**
 * A {@link ResourceSetListener} to close a {@link DialectEditor} when its
 * DRepresentation or its DRepresentation's target has been deleted.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DialectEditorCloser extends ResourceSetListenerImpl implements ResourceSetListener {

    private IEditingSession editingSession;

    private DialectEditor dialectEditor;

    /**
     * Default constructor.
     * 
     * @param editingSession
     *            the {@link IEditingSession} on which is attached the
     *            {@link DialectEditor}
     * @param dialectEditor
     *            the {@link DialectEditor} for which to listens
     *            DRepresentation's root semantic deletion
     */
    public DialectEditorCloser(IEditingSession editingSession, DialectEditor dialectEditor) {
        super(new DialectEditorCloserFilter(new DRepresentationQuery(dialectEditor.getRepresentation()).getRepresentationDescriptor()));
        this.dialectEditor = dialectEditor;
        this.editingSession = editingSession;
        TransactionalEditingDomain domain = editingSession.getSession().getTransactionalEditingDomain();
        domain.addResourceSetListener(this);
    }

    /**
     * We are only interested in post-commit to close {@link DialectEditor}.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isPostcommitOnly() {
        return true;
    }

    /**
     * Close the {@link DialectEditor} if its DRepresentation's target has been
     * deleted.
     * 
     * {@inheritDoc}
     */
    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        Display.getDefault().asyncExec(new DialectEditorCloserRunnable(editingSession, dialectEditor));
    }

    /**
     * Dispose this listener from the TransactionalEditingDomain.
     */
    public void dispose() {
        getTarget().removeResourceSetListener(this);
        dialectEditor = null;
        editingSession = null;
    }

}
