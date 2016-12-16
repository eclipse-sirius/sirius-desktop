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

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.common.ui.tools.api.resource.WorkspaceResourceDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

/**
 * A {@link SelectionAdapter} to open a {@link WorkspaceResourceDialog} for
 * metamodel resource (*.ecore) selection.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class AddFromWorkspaceButtonListener extends SelectionAdapter {

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
     *            the {@link DescriptionMetamodelsUpdater} used to update the
     *            model
     */
    public AddFromWorkspaceButtonListener(AbstractMetamodelPropertySectionSpec abstractMetamodelsPropertySection, DescriptionMetamodelsUpdater descriptionMetamodelsUpdater) {
        this.abstractMetamodelsPropertySection = abstractMetamodelsPropertySection;
        this.descriptionMetamodelsUpdater = descriptionMetamodelsUpdater;
    }

    /**
     * Overridden to display a {@link WorkspaceResourceDialog} for choose a
     * ecore resource in the workspace.
     * 
     * {@inheritDoc}
     */
    @Override
    public void widgetSelected(SelectionEvent e) {
        Shell shell = abstractMetamodelsPropertySection.getPart().getSite().getShell();

        WorkspaceResourceDialog workspaceResourceDialog = new WorkspaceResourceDialog(shell, SWT.MULTI, "Ecore resource selection", Collections.singletonList("ecore"));
        workspaceResourceDialog.open();

        List<IResource> selectedEcoreResources = workspaceResourceDialog.getSelectedResources();
        if (selectedEcoreResources != null) {
            descriptionMetamodelsUpdater.setEditingDomain(abstractMetamodelsPropertySection.getEditingDomain());
            List<URI> workspaceEcoreResourceURIs = descriptionMetamodelsUpdater.getURIs(selectedEcoreResources);
            descriptionMetamodelsUpdater.addEPackagesFromEcoreResource(workspaceEcoreResourceURIs);

        }
    }

}
