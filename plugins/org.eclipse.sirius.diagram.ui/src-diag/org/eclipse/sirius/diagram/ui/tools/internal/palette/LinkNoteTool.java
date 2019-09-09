/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Felix Dorner <felix.dorner@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.palette;

import java.util.Optional;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.tools.CreationTool;
import org.eclipse.gmf.runtime.diagram.ui.util.INotationType;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.internal.dialogs.SingleRepresentationTreeSelectionDialog;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.widgets.Shell;

/**
 * This tool creates a note that references an existing representation. The resulting note displays the name/icon of the
 * referenced representation as the note header and allows additional text below, just like a normal note.
 *
 * @author <a href="mailto:felix.dorner@gmail.com">Felix Dorner</a>
 */
class LinkNoteTool extends CreationTool {

    static final IElementType LINK = ElementTypeRegistry.getInstance().getType("org.eclipse.sirius.diagram.LinkNote"); //$NON-NLS-1$

    LinkNoteTool() {
        super(LINK);
    }

    @Override
    protected Request createTargetRequest() {
        return new CreateViewRequest(new ViewDescriptor(new LinkNoteAdaptable(), Node.class, ((INotationType) getElementType()).getSemanticHint(), getPreferencesHint()));
    }

    @Override
    protected void performCreation(int button) {

        if (getCurrentCommand() == null || !getCurrentCommand().canExecute()) {
            return;
        }

        ViewDescriptor vd = ((CreateViewRequest) getTargetRequest()).getViewDescriptors().get(0);
        if (vd.getElementAdapter().getAdapter(DRepresentationDescriptor.class) != null) {
            super.performCreation(button);
        }
    }

    @Override
    public void setViewer(EditPartViewer viewer) {
        if (viewer != null) {
            // we don't set to null because the tool cannot be reused with control+click since it opens a dialog where
            // the user must select elements and because we loose the viewer with the dialog that takes focus.
            super.setViewer(viewer);
        }
    }

    class LinkNoteAdaptable implements IAdaptable {

        DRepresentationDescriptor linkedRepresentation;

        @SuppressWarnings("unchecked")
        @Override
        public <T> T getAdapter(Class<T> adapter) {
            T result = null;
            if (adapter == DRepresentationDescriptor.class) {
                if (linkedRepresentation == null) {
                    Optional<Session> sessionOption = Session.of((EObject) getTargetEditPart().getModel());
                    if (sessionOption.isPresent()) {
                        Shell shell = LinkNoteTool.this.getCurrentViewer().getControl().getShell();
                        linkedRepresentation = SingleRepresentationTreeSelectionDialog.openSelectRepresentationDescriptor(shell, sessionOption.get());
                    }
                }
                result = (T) linkedRepresentation;
            } else if (adapter == EObject.class) {
                result = (T) linkedRepresentation;
            }
            return result;
        }

    }

}
