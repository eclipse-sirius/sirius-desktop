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

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * A {@link SelectionAdapter} to remove currently selected {@link EPackage}s.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RemoveMetamodelsSelectionButtonListener extends SelectionAdapter {

    private RepresentationDescriptionMetamodelPropertySectionSpec representationDescriptionMetamodelsPropertySection;

    private Button removeButton;

    private Table metamodelsTable;

    private RepresentationDescriptionMetamodelsUpdater representationDescriptionMetamodelsUpdater;

    /**
     * Default constructor.
     * 
     * @param representationDescriptionMetamodelsPropertySection
     *            {@link RepresentationDescriptionMetamodelPropertySectionSpec}
     *            which use this listener
     * @param removeButton
     *            the {@link Button} to remove referenced {@link EPackage}
     * @param metamodelsTable
     *            the {@link Table} representing the metamodels associated to
     *            the {@link RepresentationDescription}
     * @param representationDescriptionMetamodelsUpdater
     *            the {@link RepresentationDescriptionMetamodelsUpdater} used to
     *            update the model
     */
    public RemoveMetamodelsSelectionButtonListener(RepresentationDescriptionMetamodelPropertySectionSpec representationDescriptionMetamodelsPropertySection, Button removeButton,
            Table metamodelsTable, RepresentationDescriptionMetamodelsUpdater representationDescriptionMetamodelsUpdater) {
        this.representationDescriptionMetamodelsPropertySection = representationDescriptionMetamodelsPropertySection;
        this.removeButton = removeButton;
        this.metamodelsTable = metamodelsTable;
        this.representationDescriptionMetamodelsUpdater = representationDescriptionMetamodelsUpdater;
        metamodelsTable.addSelectionListener(this);
    }

    /**
     * Overridden to remove currently selected {@link EPackage}s.
     * 
     * {@inheritDoc}
     */
    public void widgetSelected(SelectionEvent e) {
        Object source = e.getSource();
        TableItem[] selection = metamodelsTable.getSelection();

        if (source == removeButton) {
            if (selection != null && selection.length > 0) {
                representationDescriptionMetamodelsUpdater.setEditingDomain(representationDescriptionMetamodelsPropertySection.getEditingDomain());
                List<EPackage> ePackages = representationDescriptionMetamodelsPropertySection.getEPackages(selection);
                representationDescriptionMetamodelsUpdater.removeEPackages(ePackages);
            }
        } else if (source == metamodelsTable) {
            boolean enable = selection != null && selection.length > 0;
            removeButton.setEnabled(enable);
        }
    }

}
