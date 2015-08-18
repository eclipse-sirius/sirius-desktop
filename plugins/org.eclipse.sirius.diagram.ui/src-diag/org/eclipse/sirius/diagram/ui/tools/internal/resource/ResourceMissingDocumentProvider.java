/*******************************************************************************
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.resource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResourceFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.ui.IEditorInput;

/**
 * Provide an "error" diagram if initial resource could not be found.
 * 
 * @author mchauvin
 */
public class ResourceMissingDocumentProvider {

    private static final String DEFAULT_NOTE_MESSAGE = "This diagram was not saved. You can close the editor";

    private ResourceSet errorResourceSet;

    private Resource errorResource;

    private Diagram errorDiagram;

    /**
     * Set the document content.
     * 
     * @param document
     *            the document
     * @param element
     *            the element as editor input
     */
    public void setDocumentContent(final IDocument document, final IEditorInput element) {
        // TODO get diagram name
        document.setContent(getErrorDiagram(DEFAULT_NOTE_MESSAGE));
    }

    /**
     * Set the document content.
     * 
     * @param document
     *            the document
     * @param element
     *            the element as editor input
     * @param noteMessage
     *            the message to write in the note
     */
    public void setDocumentContent(final IDocument document, final IEditorInput element, final String noteMessage) {
        // TODO get diagram name
        document.setContent(getErrorDiagram(noteMessage));
    }

    private Diagram getErrorDiagram(final String noteMessage) {
        if (errorDiagram == null) {
            errorResourceSet = new ResourceSetImpl();
            final TransactionalEditingDomain domain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(errorResourceSet);
            errorResource = new GMFResourceFactory().createResource(URI.createURI("error://diagram")); //$NON-NLS-1$
            errorResourceSet.getResources().add(errorResource);
            errorDiagram = NotationFactory.eINSTANCE.createDiagram();
            domain.getCommandStack().execute(new AddErrorCommand(domain, errorDiagram, errorResource, noteMessage));
        }
        return errorDiagram;
    }

    /**
     * Dispose the created resources.
     */
    public void dispose() {
        if (errorResourceSet != null) {
            TransactionUtil.getEditingDomain(errorResourceSet).dispose();
            errorResource = null;
            errorDiagram = null;
        }
    }

    /**
     * Specific command to add error diagram in error resource.
     * 
     * @author mporhel
     */
    private class AddErrorCommand extends RecordingCommand {

        private final Diagram diagram;

        private final String noteMessage;

        private final Resource resource;

        /**
         * Constructor.
         * 
         * @param domain
         *            the editing domain.
         * @param noteMessage
         * @param errorResource
         * @param errorDiagram
         * @param editpart
         *            the source edit part.
         * @param representation
         *            the source representation.
         * @param session
         *            the current session.
         */
        public AddErrorCommand(TransactionalEditingDomain domain, Diagram diagram, Resource resource, String noteMessage) {
            super(domain, "Navigate to another representation");
            this.diagram = diagram;
            this.resource = resource;
            this.noteMessage = noteMessage;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void doExecute() {
            if (errorResource == null || diagram == null) {
                return;
            }

            resource.getContents().add(diagram);
            GMFNotationHelper.createNote(diagram, noteMessage, DiagramUIPlugin.getPlugin().getPreferenceStore());
        }
    }
}
