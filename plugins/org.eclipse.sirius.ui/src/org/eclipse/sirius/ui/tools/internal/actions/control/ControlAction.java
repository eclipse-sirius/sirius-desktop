/**
 * <copyright> 
 *
 * Copyright (c) 2006-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *   
 *
 * </copyright>
 *
 * $Id: ControlAction.java,v 1.5 2007/03/23 17:36:45 marcelop Exp $
 * code comming from org.eclipse.emf.edit.ui.action
 */
package org.eclipse.sirius.ui.tools.internal.actions.control;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.action.CommandActionHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.ui.tools.internal.util.EMFCoreUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * A control action moves a contained object into a new resource or restores it
 * to its containers' resource. It is implemented by creating a
 * {@link AddCommand} or {@link RemoveCommand}.
 * 
 * @since 0.9.0
 * 
 */
// CHECKSTYLE:OFF
public class ControlAction extends CommandActionHandler {

    protected IStructuredSelection selection = null;

    protected EObject eObject = null;

    protected Resource resource = null;

    protected boolean canceled = false;

    public ControlAction(EditingDomain domain) {
        super(domain, EMFEditUIPlugin.INSTANCE.getString("_UI_Control_menu_item"));
    }

    public ControlAction() {
        this(null);
    }

    // We can create the RemoveCommand for an uncontrol, but we must defer
    // creating an AddCommand
    // to control until run(), when the user specifies a target resource.
    //
    @Override
    public boolean updateSelection(IStructuredSelection selection) {
        // At each selection changed, we must reset the eObject to avoid a
        // potential memory leak
        eObject = null;

        this.selection = selection;
        if (selection.size() != 1) {
            return false;
        }

        Object object = AdapterFactoryEditingDomain.unwrap(selection.getFirstElement());
        boolean result = domain.isControllable(object);
        eObject = result ? (EObject) object : null;

        if (!AdapterFactoryEditingDomain.isControlled(object)) {
            setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Control_menu_item"));
            setDescription("_UI_Control_menu_item_description");
            command = null;
        } else {
            setText(EMFEditUIPlugin.INSTANCE.getString("_UI_Uncontrol_menu_item"));
            setDescription("_UI_Uncontrol_menu_item_description");
            if (result) {
                command = new RemoveCommand(domain, eObject.eResource().getContents(), eObject);
                command = new SelfAffectingCommand(EMFEditUIPlugin.INSTANCE.getString("_UI_UncontrolCommand_label"), command);
                result = command.canExecute();
            }
        }
        return result;
    }

    protected class SelfAffectingCommand extends CommandWrapper {
        SelfAffectingCommand(String label, Command command) {
            super(label, command);
        }

        @Override
        public Collection<?> getResult() {
            return selection.toList();
        }

        @Override
        public Collection<?> getAffectedObjects() {
            return selection.toList();
        }
    }

    // For the control case, we need a dialog to ask for the URI and obtain the
    // resource, then we create the command.
    //
    @Override
    public void run() {
        if (command == null) {
            if (eObject == null) {
                return;
            }

            resource = getResource();
            if (resource == null) {
                canceled = true;
                return;
            }

            command = new AddCommand(domain, resource.getContents(), eObject);
            command = new SelfAffectingCommand(EMFEditUIPlugin.INSTANCE.getString("_UI_ControlCommand_label"), command);
        }

        // Ensure that all proxies are resolved so that references into the
        // controlled object will be saved to reference the new resource.
        //
        EcoreUtil.resolveAll(domain.getResourceSet());

        super.run();
    }

    protected Resource getResource() {
        ControlResourceDialog dialog = new ControlResourceDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), domain, eObject.eResource(), eObject);
        dialog.open();
        return dialog.getResource();
    }

    public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
        if (workbenchPart instanceof IEditingDomainProvider) {
            domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
        }
    }

    /**
     * A save-type {@link ResourceDialog resource dialog} that attempts to
     * create the specified resource and load it, if it already exists.
     */
    protected static class ControlResourceDialog extends ResourceDialog {
        protected EditingDomain domain;

        protected Resource resource;

        protected Resource currentResource;

        protected EObject controledObject;

        public ControlResourceDialog(Shell parent, EditingDomain domain, Resource currentResource, final EObject controledObject) {
            super(parent, EMFEditUIPlugin.INSTANCE.getString("_UI_ControlDialog_title"), SWT.SAVE);
            this.domain = domain;
            this.currentResource = currentResource;
            this.controledObject = controledObject;
        }

        @Override
        protected Control createDialogArea(Composite parent) {
            final Control control = super.createDialogArea(parent);
            final StringBuilder defaultURI = new StringBuilder();
            URI controledObjectResourceURI = controledObject.eResource().getURI();
            defaultURI.append(URI.decode(controledObjectResourceURI.trimFileExtension().toString()));
            defaultURI.append("_"); //$NON-NLS-1$
            defaultURI.append(EMFCoreUtil.getName(controledObject));
            defaultURI.append("."); //$NON-NLS-1$
            defaultURI.append(controledObjectResourceURI.fileExtension());
            this.uriField.setText(URI.encodeFragment(defaultURI.toString(), true));
            return control;
        }

        /**
         * Creates and, if it already exists, loads the specified resource. This
         * implementation verifies that a resource can be opened for that URI,
         * that the resource is not the object's current container, and that it
         * is not read-only in the editing domain. If there is an existing
         * resource with that URI, it prompts before overriding or adding to it.
         */
        @Override
        protected boolean processResources() {
            List<URI> uris = getURIs();
            if (uris.isEmpty()) {
                return false;
            }

            URI uri = uris.get(0);
            ResourceSet resourceSet = domain.getResourceSet();
            Resource resource = resourceSet.getResource(uri, false);
            boolean resourceInSet = resource != null;

            if (resource == currentResource) {
                MessageDialog.openError(getShell(), EMFEditUIPlugin.INSTANCE.getString("_UI_InvalidURI_label"), EMFEditUIPlugin.INSTANCE.getString("_WARN_AlreadyInResource"));
                return false;
            }
            if (domain.isReadOnly(resource)) {
                MessageDialog.openError(getShell(), EMFEditUIPlugin.INSTANCE.getString("_UI_InvalidURI_label"), EMFEditUIPlugin.INSTANCE.getString("_WARN_ReadOnlyResource"));
                return false;
            }

            boolean resourceExists = false;
            try {
                InputStream stream = resourceSet.getURIConverter().createInputStream(uri);
                if (stream != null) {
                    resourceExists = true;
                    stream.close();
                }
            } catch (IOException exception) {
                // Ignore
            }

            boolean resourceBad = false;
            if (!resourceInSet) {
                resource = resourceSet.createResource(uri);
                if (resource == null) {
                    MessageDialog.openError(getShell(), EMFEditUIPlugin.INSTANCE.getString("_UI_InvalidURI_label"), EMFEditUIPlugin.INSTANCE.getString("_WARN_CannotCreateResource"));
                    return false;
                }

                if (resourceExists) {
                    try {
                        resource = resourceSet.getResource(uri, true);
                    } catch (RuntimeException exception) {
                        EMFEditUIPlugin.INSTANCE.log(exception);
                        resourceBad = resource.getContents().isEmpty();
                    }
                }
            }

            boolean result = true;
            if (resourceBad) {
                result = MessageDialog.openQuestion(getShell(), EMFEditUIPlugin.INSTANCE.getString("_UI_ExistingResource_label"), EMFEditUIPlugin.INSTANCE.getString("_WARN_ReplaceResource"));
            } else if (resourceExists) {
                result = MessageDialog.openQuestion(getShell(), EMFEditUIPlugin.INSTANCE.getString("_UI_ExistingResource_label"), EMFEditUIPlugin.INSTANCE.getString("_WARN_AddToResource"));
            }

            if (!result && !resourceInSet && resource != null) {
                resource.unload();
                resourceSet.getResources().remove(resource);
            } else {
                this.resource = resource;
            }
            return result;
        }

        public Resource getResource() {
            return resource;
        }
    }
}
