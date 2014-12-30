/*******************************************************************************
 * Copyright (c) 2007 - 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.editor.properties.section.description.diagramelementmapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.properties.sections.common.AbstractRadioButtonPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * A section for the doubleClickDescription property of a DiagramElementMapping
 * object.
 */
public class DiagramElementMappingSynchronizationPropertySection extends AbstractRadioButtonPropertySection {

    private static final String NOT_SYNCHRONIZED_OPTION = "Not synchronized";

    private static final String UNSYNCHRONIZABLE_OPTION = "Unsynchronizable";

    private static final String SYNCHRONIZED_OPTION = "Synchronized";

    private static final String TOOL_TIP = "Defines the level of synchronization of the mapping." + "\nIf not synchronized, the mapping will be always unsynchronized (createElements=false)."
            + "\nIf synchronized, the mapping will be always synchronized (createElements=true and synchro lock)."
            + "\nIf unsynchronizable, the synchronization of the mapping depends on the synchronization mode defined by the user (createElements=true and no synchro lock).";

    /**
     * @see org.eclipse.sirius.editor.properties.sections.common.AbstractRadioButtonPropertySection#getDefaultLabelText()
     */
    @Override
    protected String getDefaultLabelText() {
        return "Synchronization"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.common.AbstractRadioButtonPropertySection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        return super.getLabelText() + ":"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.common.AbstractRadioButtonPropertySection#getFeature()
     */
    @Override
    protected EAttribute getFeature() {
        return null;
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.common.AbstractRadioButtonPropertySection#getFeatureValue(int)
     */
    @Override
    protected Object getFeatureValue(int index) {
        return getChoiceOfValues().get(index);
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.common.AbstractRadioButtonPropertySection#isEqual(int)
     */
    @Override
    protected boolean isEqual(int index) {

        String value = DiagramElementMappingSynchronizationPropertySection.convertPropertiesToUI((Boolean) eObject.eGet(getFeatures().get(0)), (Boolean) eObject.eGet(getFeatures().get(1)));

        return getChoiceOfValues().get(index).equals(value);
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.common.AbstractRadioButtonPropertySection#getChoiceOfValues()
     */
    @Override
    protected List getChoiceOfValues() {
        List<String> values = new ArrayList<String>();
        values.add(NOT_SYNCHRONIZED_OPTION);
        values.add(UNSYNCHRONIZABLE_OPTION);
        values.add(SYNCHRONIZED_OPTION);
        return values;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

        nameLabel.setToolTipText(TOOL_TIP);

        CLabel help = getWidgetFactory().createCLabel(composite, "");
        FormData data = new FormData();
        data.top = new FormAttachment(nameLabel, 0, SWT.TOP);
        data.left = new FormAttachment(nameLabel);
        help.setLayoutData(data);
        help.setImage(getHelpIcon());
        help.setToolTipText(TOOL_TIP);

    }

    /**
     * Handle the modification event given the index of the newly selected
     * button.
     */
    @Override
    public void handleSelectionChanged(int index) {
        boolean equals = isEqual(index);

        if (!equals) {
            EditingDomain editingDomain = ((SiriusEditor) getPart()).getEditingDomain();
            Object value = getFeatureValue(index);

            List<EAttribute> properties = getFeatures();
            List<Boolean> propertiesValue = DiagramElementMappingSynchronizationPropertySection.convertUIToProperties((String) value);

            CompoundCommand compoundCommand = new CompoundCommand();

            if (eObjectList.size() == 1) {
                // apply the property change to single selected object
                for (int i = 0; i < properties.size(); i++) {
                    compoundCommand.append(SetCommand.create(editingDomain, eObject, properties.get(i), propertiesValue.get(i)));
                }
            } else {
                /* apply the property change to all selected elements */
                for (EObject nextObject : eObjectList) {
                    compoundCommand.append(SetCommand.create(editingDomain, nextObject, properties.get(0), propertiesValue.get(0)));
                    compoundCommand.append(SetCommand.create(editingDomain, nextObject, properties.get(1), propertiesValue.get(1)));
                }

            }

            editingDomain.getCommandStack().execute(compoundCommand);
        }
    }

    @Override
    protected String getDefaultFeatureAsText() {
        return DiagramElementMappingSynchronizationPropertySection.convertPropertiesToUI((Boolean) eObject.eGet(getFeatures().get(0)), (Boolean) eObject.eGet(getFeatures().get(1)));
    }

    @Override
    protected List<IItemPropertyDescriptor> getDescriptors() {
        // TODO
        return Collections.emptyList();
    }

    protected List<EAttribute> getFeatures() {
        List<EAttribute> features = new ArrayList<EAttribute>();
        features.add(DescriptionPackage.eINSTANCE.getDiagramElementMapping_CreateElements());
        features.add(DescriptionPackage.eINSTANCE.getDiagramElementMapping_SynchronizationLock());
        return features;
    }

    public static String convertPropertiesToUI(boolean create, boolean synchroLock) {
        if (!create) {
            return NOT_SYNCHRONIZED_OPTION;
        } else {
            if (!synchroLock) {
                return UNSYNCHRONIZABLE_OPTION;
            } else {
                return SYNCHRONIZED_OPTION;
            }
        }
    }

    public static List<Boolean> convertUIToProperties(String label) {
        List<Boolean> properties = new ArrayList<Boolean>();
        if (NOT_SYNCHRONIZED_OPTION.equals(label)) {
            properties.add(Boolean.FALSE);
            properties.add(Boolean.FALSE);
        } else if (UNSYNCHRONIZABLE_OPTION.equals(label)) {
            properties.add(Boolean.TRUE);
            properties.add(Boolean.FALSE);
        } else if (SYNCHRONIZED_OPTION.equals(label)) {
            properties.add(Boolean.TRUE);
            properties.add(Boolean.TRUE);
        }
        return properties;
    }
}
