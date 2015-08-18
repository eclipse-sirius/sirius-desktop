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
package org.eclipse.sirius.ui.tools.api.control;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.control.SiriusControlCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.tools.internal.util.EMFCoreUtil;
import org.eclipse.sirius.ui.tools.internal.wizards.SelectRepresentationsWizard;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IReusableEditor;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.handlers.HandlerUtil;

import com.google.common.collect.Sets;

/**
 * Implements the UI part of the "Control" operation. This class gathers the
 * required parameters from the user and the invokes the properly configured
 * {@link org.eclipse.sirius.business.internal.command.control.ControlCommand} .
 * 
 * @since 0.9.0
 * 
 * @author pcdavid
 */
public class SiriusControlHandler extends AbstractHandler {
    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final EObject semanticRoot = getSelectedEObject(event);
        if (semanticRoot != null) {
            try {
                new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(false, false, new WorkspaceModifyOperation() {

                    @Override
                    protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
                        try {
                            monitor.beginTask("Control resources", 1);
                            performControl(HandlerUtil.getActiveShell(event), semanticRoot, new SubProgressMonitor(monitor, 1));
                        } finally {
                            monitor.done();
                        }
                    }
                });
            } catch (InvocationTargetException e) {
                SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e));
            } catch (InterruptedException e) {
                SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e));
            }

        }
        return null;
    }

    /**
     * Performs the control operation on the specified semantic element, using
     * the provided shell to interact with the end-user, gathering the required
     * parameters.
     * 
     * @param shell
     *            the shell to use to interact with the user
     * @param semanticRoot
     *            the semantic element to control
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of control
     */
    public void performControl(final Shell shell, final EObject semanticRoot, IProgressMonitor monitor) {
        final Session session = SessionManager.INSTANCE.getSession(semanticRoot);
        if (session != null) {
            final URI semanticDest = getControledResourceURI(shell, semanticRoot);
            if (semanticDest != null) {
                Set<DRepresentation> representations;
                try {
                    representations = Sets.newHashSet(getRepresentationsToMove(shell, session, semanticRoot));
                    final URI representationDest = getDefaultCorrespondingAird(semanticDest);

                    Command vcc = new SiriusControlCommand(semanticRoot, semanticDest, representations, representationDest, false, new SubProgressMonitor(monitor, 1));
                    TransactionUtil.getEditingDomain(semanticRoot).getCommandStack().execute(vcc);
                    session.save(new SubProgressMonitor(monitor, 1));

                    // Opened editors of uncontrolled representations need an
                    // update of the editor input with the new DRepresentation
                    // location
                    IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
                    for (DRepresentation dRepresentation : representations) {
                        DialectEditor editor = uiSession.getEditor(dRepresentation);
                        if (editor instanceof IReusableEditor) {
                            IReusableEditor iReusableEditor = (IReusableEditor) editor;
                            SessionEditorInput updatedEditorInput = new SessionEditorInput(EcoreUtil.getURI(dRepresentation), dRepresentation.getName(), session);
                            iReusableEditor.setInput(updatedEditorInput);
                        }
                    }
                } catch (InterruptedException e) {
                    // cancel done : no command to execute
                }
            }
        }
    }

    /**
     * Asks the user for the URI of the controlled resource to create.
     * 
     * @param shell
     *            The shell to use to display the dialog
     * @param semanticRoot
     *            the controlled element.
     * @return the URI of the controlled resource to create
     */
    protected URI getControledResourceURI(final Shell shell, final EObject semanticRoot) {
        final String defaultURI = getDefaultControlURI(semanticRoot);
        final ResourceDialog dlg = createControlResourceDialog(shell, defaultURI);
        if (dlg.open() == Window.OK) {
            final List<URI> uris = dlg.getURIs();
            if (!uris.isEmpty()) {
                return uris.get(0);
            }
        }
        return null;
    }

    /**
     * Creation of the dialog to ask the user for the URI of the controlled
     * resource to create.
     * 
     * @param shell
     *            the shell to use to interact with the user.
     * @param defaultURI
     *            the default URI proposed for the new resource to create.
     * @return the dialog
     */
    protected ResourceDialog createControlResourceDialog(final Shell shell, final String defaultURI) {
        return new ControlResourceDialog(shell, defaultURI);
    }

    private EObject getSelectedEObject(final ExecutionEvent event) {
        final ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection instanceof IStructuredSelection) {
            final IStructuredSelection iss = (IStructuredSelection) selection;
            final Object obj = iss.getFirstElement();
            if (obj instanceof EObject) {
                return (EObject) obj;
            }
        }
        return null;
    }

    /**
     * Finds the URI of the default corresponding Aird of a semantic model with
     * its URI.
     * 
     * @param semanticModelUri
     *            URI of a semantic model
     * @return the URI of the default corresponding Aird
     */
    protected URI getDefaultCorrespondingAird(final URI semanticModelUri) {
        return semanticModelUri.trimFileExtension().appendFileExtension(SiriusUtil.SESSION_RESOURCE_EXTENSION);
    }

    /**
     * Asks the end-user which representations should be controlled in
     * conjunction with the semantic elements. The default is to control all the
     * representations which target an element of the specified resource.
     * 
     * @param shell
     *            the shell to use to interact with the user.
     * @param session
     *            the session opened for semanticRoot.
     * @param semanticRoot
     *            the semantic element to control.
     * @return a collection of representations to move
     * @throws InterruptedException
     *             Exception for cancel
     */
    protected Collection<DRepresentation> getRepresentationsToMove(final Shell shell, final Session session, final EObject semanticRoot) throws InterruptedException {
        final Collection<DRepresentation> representations = collectExistingRepresentations(session, semanticRoot);
        final Collection<DRepresentation> representationsToMove = askUserWhichRepresentationToSplit(shell, session, representations);
        return representationsToMove;
    }

    /**
     * Asks the end-user to select a sub-set of the representations existing in
     * the session.
     * 
     * @param shell
     *            the shell to use to interact with the user.
     * @param session
     *            the session opened for semanticRoot.
     * @param preselection
     *            preselection of representation to split.
     * @return a collection of representations to split
     * @throws InterruptedException
     *             Exception for cancel
     */
    protected Collection<DRepresentation> askUserWhichRepresentationToSplit(final Shell shell, final Session session, final Collection<DRepresentation> preselection) throws InterruptedException {
        if (!DialectManager.INSTANCE.getAllRepresentations(session).isEmpty()) {
            final SelectRepresentationsWizard wizard = new SelectRepresentationsWizard(session, preselection);
            wizard.init();
            final WizardDialog dialog = new WizardDialog(shell, wizard);
            dialog.setHelpAvailable(false);
            dialog.create();
            if (Window.OK == dialog.open()) {
                return wizard.getSelectedRepresentations();
            } else {
                throw new InterruptedException();
            }
        }
        return Collections.emptySet();
    }

    /**
     * Returns all the existing representations in the given session which are
     * associated to a semantic element of the specified resource (excluding
     * elements of sub-resources).
     * 
     * @param session
     *            the session opened for semanticRoot.
     * @param semanticRoot
     *            the semantic element to control.
     * @return a collection of existing representations
     */
    protected Collection<DRepresentation> collectExistingRepresentations(final Session session, final EObject semanticRoot) {
        final Collection<DRepresentation> result = new ArrayList<DRepresentation>();
        result.addAll(DialectManager.INSTANCE.getRepresentations(semanticRoot, session));
        final Iterator<EObject> it = EcoreUtil.getAllProperContents(semanticRoot, true);
        while (it.hasNext()) {
            final EObject root = it.next();
            result.addAll(DialectManager.INSTANCE.getRepresentations(root, session));
        }
        return result;
    }

    /**
     * Returns the text of the URI proposed by default when controlling the
     * specified object. The URI is based on the initial parent resource and the
     * "name" feature of the controlled element.
     * 
     * @param obj
     *            the controlled element.
     * @return the text of the URI.
     */
    protected String getDefaultControlURI(final EObject obj) {
        final StringBuilder uri = new StringBuilder();
        URI objResourceURI = obj.eResource().getURI();
        uri.append(URI.decode(objResourceURI.trimFileExtension().toString()));
        uri.append("_"); //$NON-NLS-1$
        uri.append(EMFCoreUtil.getName(obj));
        uri.append("."); //$NON-NLS-1$
        uri.append(objResourceURI.fileExtension());
        return uri.toString();
    }

    /**
     * A dialog to ask the user for the URI of the resource to put controlled
     * elements into.
     */
    private class ControlResourceDialog extends ResourceDialog {
        private final String defaultURI;

        public ControlResourceDialog(final Shell parent, final String defaultURI) {
            super(parent, EMFEditUIPlugin.INSTANCE.getString("_UI_ControlDialog_title"), SWT.SAVE);
            this.defaultURI = defaultURI;
        }

        @Override
        protected Control createDialogArea(final Composite parent) {
            final Control control = super.createDialogArea(parent);
            this.uriField.setText(URI.encodeFragment(defaultURI, true));
            return control;
        }
    }
}
