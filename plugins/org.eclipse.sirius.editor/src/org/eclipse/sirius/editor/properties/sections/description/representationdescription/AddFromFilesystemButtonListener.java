/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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

    private RepresentationDescriptionMetamodelPropertySectionSpec representationDescriptionMetamodelsPropertySection;

    private RepresentationDescriptionMetamodelsUpdater representationDescriptionMetamodelsUpdater;

    /**
     * Default constructor.
     * 
     * @param representationDescriptionMetamodelsPropertySection
     *            {@link RepresentationDescriptionMetamodelPropertySectionSpec}
     *            which use this listener
     * 
     * @param representationDescriptionMetamodelsUpdater
     *            the {@link RepresentationDescriptionMetamodelsUpdater} used to
     *            update the model
     */
    public AddFromFilesystemButtonListener(RepresentationDescriptionMetamodelPropertySectionSpec representationDescriptionMetamodelsPropertySection,
            RepresentationDescriptionMetamodelsUpdater representationDescriptionMetamodelsUpdater) {
        this.representationDescriptionMetamodelsPropertySection = representationDescriptionMetamodelsPropertySection;
        this.representationDescriptionMetamodelsUpdater = representationDescriptionMetamodelsUpdater;
    }

    /**
     * Overridden to display a popup dialog to choose ecore file from
     * filesystem.
     * 
     * {@inheritDoc}
     */
    public void widgetSelected(SelectionEvent e) {
        Shell shell = representationDescriptionMetamodelsPropertySection.getPart().getSite().getShell();

        FileDialog fileDialog = new FileDialog(shell, SWT.OPEN | SWT.MULTI);
        fileDialog.setText("Metamodel resource selection");
        fileDialog.setFilterExtensions(new String[] { "*.ecore" });
        fileDialog.open();

        String filterPath = fileDialog.getFilterPath();
        String[] fileNames = fileDialog.getFileNames();

        representationDescriptionMetamodelsUpdater.setEditingDomain(representationDescriptionMetamodelsPropertySection.getEditingDomain());
        List<URI> filesystemEcoreResourceURIs = representationDescriptionMetamodelsUpdater.getURIs(filterPath, fileNames);
        representationDescriptionMetamodelsUpdater.addEPackagesFromEcoreResource(filesystemEcoreResourceURIs);
    }

}
