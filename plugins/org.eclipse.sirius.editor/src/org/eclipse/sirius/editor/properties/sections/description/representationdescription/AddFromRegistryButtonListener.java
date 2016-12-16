/*******************************************************************************
 * Copyright (c) 2011, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.description.representationdescription;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.presentation.EcoreActionBarContributor.ExtendedLoadResourceAction.RegisteredPackageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * A {@link SelectionAdapter} to open a {@link RegisteredPackageDialog} for
 * metamodel (EPackage) selection from the EMF registry.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class AddFromRegistryButtonListener extends SelectionAdapter {

    private AbstractMetamodelPropertySectionSpec abstractMetamodelsPropertySection;

    private DescriptionMetamodelsUpdater descriptionMetamodelsUpdater;

    /**
     * Default constructor.
     * 
     * @param abstractMetamodelsPropertySection
     *            {@link AbstractMetamodelPropertySectionSpec} which use this
     *            listener
     * 
     * @param descriptionMetamodelsUpdater
     *            the {@link DescriptionMetamodelsUpdater} to update the model
     */
    public AddFromRegistryButtonListener(AbstractMetamodelPropertySectionSpec abstractMetamodelsPropertySection, DescriptionMetamodelsUpdater descriptionMetamodelsUpdater) {
        this.abstractMetamodelsPropertySection = abstractMetamodelsPropertySection;
        this.descriptionMetamodelsUpdater = descriptionMetamodelsUpdater;
    }

    /**
     * Overridden to open a {@link RegisteredPackageDialog} for metamodel
     * (EPackage) selection from the EMF registry.
     * 
     * {@inheritDoc}
     */
    @Override
    public void widgetSelected(SelectionEvent e) {
        Shell shell = abstractMetamodelsPropertySection.getPart().getSite().getShell();

        RegisteredPackageDialog registeredPackageDialog = new RegisteredPackageDialog(shell);
        registeredPackageDialog.setTitle("Metamodel selection");
        registeredPackageDialog.open();
        final Object[] result = registeredPackageDialog.getResult();
        if (result != null) {

            try {
                IRunnableContext context = new ProgressMonitorDialog(shell);
                PlatformUI.getWorkbench().getProgressService().runInUI(context, new IRunnableWithProgress() {

                    @Override
                    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                        monitor.beginTask("Import metamodels from registry", IProgressMonitor.UNKNOWN);
                        descriptionMetamodelsUpdater.setEditingDomain(abstractMetamodelsPropertySection.getEditingDomain());
                        List<EPackage> ePackagesFromNsURI = descriptionMetamodelsUpdater.getEPackagesFromNsURI(result);
                        monitor.worked(1);
                        descriptionMetamodelsUpdater.addEPackages(ePackagesFromNsURI);
                        monitor.done();
                    }
                }, null);
            } catch (InvocationTargetException e1) {
                IStatus status = new Status(Status.ERROR, SiriusEditorPlugin.PLUGIN_ID, "InvocationTargetException", e1);
                SiriusEditorPlugin.getPlugin().getLog().log(status);
            } catch (InterruptedException e1) {
                IStatus status = new Status(Status.ERROR, SiriusEditorPlugin.PLUGIN_ID, "InterruptedException", e1);
                SiriusEditorPlugin.getPlugin().getLog().log(status);
            }

        }
    }
}
