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

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * A {@link SelectionAdapter} to add EPackages from ecore resource in the
 * filesystem.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class AddFromFilesystemButtonListener extends SelectionAdapter {

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
    public AddFromFilesystemButtonListener(AbstractMetamodelPropertySectionSpec abstractMetamodelsPropertySection, DescriptionMetamodelsUpdater descriptionMetamodelsUpdater) {
        this.abstractMetamodelsPropertySection = abstractMetamodelsPropertySection;
        this.descriptionMetamodelsUpdater = descriptionMetamodelsUpdater;
    }

    /**
     * Overridden to display a popup dialog to choose ecore file from
     * filesystem.
     * 
     * {@inheritDoc}
     */
    @Override
    public void widgetSelected(SelectionEvent e) {
        Shell shell = abstractMetamodelsPropertySection.getPart().getSite().getShell();

        FileDialog fileDialog = new FileDialog(shell, SWT.OPEN | SWT.MULTI);
        fileDialog.setText("Metamodel resource selection");
        fileDialog.setFilterExtensions(new String[] { "*.ecore" });
        fileDialog.open();

        String filterPath = fileDialog.getFilterPath();
        String[] fileNames = fileDialog.getFileNames();

        descriptionMetamodelsUpdater.setEditingDomain(abstractMetamodelsPropertySection.getEditingDomain());
        List<URI> filesystemEcoreResourceURIs = descriptionMetamodelsUpdater.getURIs(filterPath, fileNames);
        descriptionMetamodelsUpdater.addEPackagesFromEcoreResource(filesystemEcoreResourceURIs);
    }

}
