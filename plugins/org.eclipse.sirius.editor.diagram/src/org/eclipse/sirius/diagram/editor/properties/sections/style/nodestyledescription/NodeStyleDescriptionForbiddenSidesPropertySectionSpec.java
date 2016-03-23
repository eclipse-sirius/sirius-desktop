/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.editor.properties.sections.style.nodestyledescription;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.description.style.Side;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.editor.properties.sections.common.AbstractCheckBoxGroupPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * A section for the forbiddenSides property of a NodeStyleDescription object.
 * 
 * @author fbarbin
 */
public class NodeStyleDescriptionForbiddenSidesPropertySectionSpec extends AbstractCheckBoxGroupPropertySection {

    public NodeStyleDescriptionForbiddenSidesPropertySectionSpec() {
        buttonGroup = false;
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractCheckBoxGroupPropertySection#getDefaultLabelText()
     */
    @Override
    protected String getDefaultLabelText() {
        return "AuthorizedSides"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractCheckBoxGroupPropertySection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        return "Authorized Sides:";//$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractCheckBoxGroupPropertySection#getFeature()
     */
    @Override
    protected EAttribute getFeature() {
        return StylePackage.eINSTANCE.getNodeStyleDescription_ForbiddenSides();
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractCheckBoxGroupPropertySection#isEqual(List)
     */
    @Override
    protected boolean isEqual(List<?> newList) {
        return newList.equals(eObject.eGet(getFeature()));
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractCheckBoxGroupPropertySection#getEnumerationFeatureValues()
     */
    @Override
    protected List<?> getChoiceOfValues() {
        return Side.VALUES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

        Group group = getWidgetFactory().createGroup(composite, "");
        group.setLayout(new RowLayout(SWT.HORIZONTAL));
        for (int i = 0; i < 4; i++) {
            button[i].setParent(group);
            button[i].setLayoutData(new RowData());
        }
        FormData data = new FormData();
        data.left = new FormAttachment(0, LABEL_WIDTH);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        group.setLayoutData(data);

        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(group, -ITabbedPropertyConstants.HSPACE - 20);
        data.top = new FormAttachment(group, 0, SWT.CENTER);
        nameLabel.setLayoutData(data);

        // combo.setToolTipText("Forbidden sides of the parent node or
        // container.");
        //
        CLabel help = getWidgetFactory().createCLabel(composite, "");
        data = new FormData();
        data.top = new FormAttachment(nameLabel, 0, SWT.TOP);
        data.left = new FormAttachment(nameLabel);
        help.setLayoutData(data);
        help.setImage(getHelpIcon());
        help.setToolTipText("Authorized sides on the parent node or container.");
    }

    /**
     * Since we display the authorized sides (but the feature represents the
     * forbidden sides) we inverse the selection.
     */
    @Override
    public void refresh() {
        // By default all side are authorized
        for (int i = 0; i < button.length; i++) {
            button[i].setSelection(true);
        }
        String featureText = getFeatureAsText();
        if (!StringUtil.isEmpty(featureText)) {
            String[] values = featureText.split(", ");
            for (String sideText : values) {
                // if the side is forbidden, we remove the selection of the
                // check-box.
                Side currentSide = Side.get(sideText);
                button[currentSide.getValue()].setSelection(false);
            }
        }
    }

    /**
     * This method returns the inverse selection. Indeed, the feature values
     * represent the forbidden sides but the end user see the authorized side.
     */
    @Override
    protected List<?> getSelectedValues() {
        List<?> possibleValues = getChoiceOfValues();
        List<Object> selectedValues = new ArrayList<Object>();

        for (int i = 0; i < button.length; i++)
            if (!button[i].getSelection())
                selectedValues.add(possibleValues.get(i));

        return selectedValues;
    }

    @Override
    public void handleSelectionChanged() {
        List<?> value = getSelectedValues();
        boolean equals = isEqual(value);

        if (!equals) {
            EditingDomain editingDomain = ((IEditingDomainProvider) getPart()).getEditingDomain();

            if (eObjectList.size() == 1) {
                // apply the property change to single selected object
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, eObject, getFeature(), value));
            } else {
                CompoundCommand compoundCommand = new CompoundCommand();
                /* apply the property change to all selected elements */
                for (Iterator<EObject> i = eObjectList.iterator(); i.hasNext();) {
                    EObject nextObject = i.next();
                    compoundCommand.append(SetCommand.create(editingDomain, nextObject, getFeature(), value));
                }
                editingDomain.getCommandStack().execute(compoundCommand);
            }
        }
    }
}
