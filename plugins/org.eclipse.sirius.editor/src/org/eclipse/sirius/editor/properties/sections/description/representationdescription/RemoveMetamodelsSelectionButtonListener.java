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

import org.eclipse.emf.ecore.EPackage;
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

    private AbstractMetamodelPropertySectionSpec abstractMetamodelsPropertySection;

    private Button removeButton;

    private Table metamodelsTable;

    private DescriptionMetamodelsUpdater descriptionMetamodelsUpdater;

    /**
     * Default constructor.
     * 
     * @param abstractMetamodelsPropertySection
     *            {@link AbstractMetamodelPropertySectionSpec} which use this
     *            listener
     * @param removeButton
     *            the {@link Button} to remove referenced {@link EPackage}
     * @param metamodelsTable
     *            the {@link Table} representing the metamodels associated to
     *            the description
     * @param descriptionMetamodelsUpdater
     *            the {@link DescriptionMetamodelsUpdater} used to update the
     *            model
     */
    public RemoveMetamodelsSelectionButtonListener(AbstractMetamodelPropertySectionSpec abstractMetamodelsPropertySection, Button removeButton, Table metamodelsTable,
            DescriptionMetamodelsUpdater descriptionMetamodelsUpdater) {
        this.abstractMetamodelsPropertySection = abstractMetamodelsPropertySection;
        this.removeButton = removeButton;
        this.metamodelsTable = metamodelsTable;
        this.descriptionMetamodelsUpdater = descriptionMetamodelsUpdater;
        metamodelsTable.addSelectionListener(this);
    }

    /**
     * Overridden to remove currently selected {@link EPackage}s.
     * 
     * {@inheritDoc}
     */
    @Override
    public void widgetSelected(SelectionEvent e) {
        Object source = e.getSource();
        TableItem[] selection = metamodelsTable.getSelection();

        if (source == removeButton) {
            if (selection != null && selection.length > 0) {
                descriptionMetamodelsUpdater.setEditingDomain(abstractMetamodelsPropertySection.getEditingDomain());
                List<EPackage> ePackages = abstractMetamodelsPropertySection.getEPackages(selection);
                descriptionMetamodelsUpdater.removeEPackages(ePackages);
            }
        } else if (source == metamodelsTable) {
            boolean enable = selection != null && selection.length > 0;
            removeButton.setEnabled(enable);
        }
    }

}
