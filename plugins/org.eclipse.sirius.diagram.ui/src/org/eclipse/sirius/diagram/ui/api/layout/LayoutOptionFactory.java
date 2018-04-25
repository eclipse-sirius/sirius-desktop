/*******************************************************************************
 * Copyright (c) 2018 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.api.layout;

import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.description.BooleanLayoutOption;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DoubleLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutValue;
import org.eclipse.sirius.diagram.description.EnumSetLayoutOption;
import org.eclipse.sirius.diagram.description.IntegerLayoutOption;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.description.StringLayoutOption;

/**
 * This factory allows to create layout algorithm options that should be available for edition in the VSM editor. You
 * can specify its id, description. The label that should be used to display your option in UI. And the default value.
 * Option types supported are String, Boolean, Integer, Double and Enum. For Enum you must specify available
 * choices/literal.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class LayoutOptionFactory {
    /**
     * Returns a new layout option with the type EnumSet.
     * 
     * @param choices
     *            a list of {@link EnumChoice} that will be proposed to the VSM specifier to configure an enum option of
     *            a layout algorithm.
     * @param id
     *            the enumset option id allowing to identified it from other options.
     * @param description
     *            a description of the option.
     * @param label
     *            a label used in Sirius UI to represent the option.
     * @param defaultValues
     *            the default option values.
     * @return a new layout option with the type Enum.
     */
    public LayoutOption createEnumSetOption(List<EnumChoice> choices, String id, String description, String label, List<String> defaultValues) {
        EnumSetLayoutOption layoutOption = DescriptionFactory.eINSTANCE.createEnumSetLayoutOption();
        layoutOption.setDescription(description);
        layoutOption.setLabel(label);
        layoutOption.setId(id);

        for (EnumChoice enumChoice : choices) {
            EnumLayoutValue layoutValue = DescriptionFactory.eINSTANCE.createEnumLayoutValue();
            layoutValue.setDescription(enumChoice.getDescription());
            layoutValue.setName(enumChoice.getName());
            layoutOption.getChoices().add(layoutValue);

            if (defaultValues.contains(enumChoice.getName())) {
                layoutOption.getValues().add(EcoreUtil.copy(layoutValue));
            }
        }
        return layoutOption;
    }

    /**
     * Returns a new layout option with the type Enum.
     * 
     * @param choices
     *            a list of {@link EnumChoice} that will be proposed to the VSM specifier to configure an enum option of
     *            a layout algorithm.
     * @param id
     *            the enum option id allowing to identified it from other options.
     * @param description
     *            a description of the option.
     * @param label
     *            a label used in Sirius UI to represent the option.
     * @param defaultValue
     *            the default option value.
     * @return a new layout option with the type Enum.
     */
    public LayoutOption createEnumOption(List<EnumChoice> choices, String id, String description, String label, String defaultValue) {
        EnumLayoutOption layoutOption = DescriptionFactory.eINSTANCE.createEnumLayoutOption();
        layoutOption.setDescription(description);
        layoutOption.setLabel(label);
        layoutOption.setId(id);

        for (EnumChoice enumChoice : choices) {
            EnumLayoutValue layoutValue = DescriptionFactory.eINSTANCE.createEnumLayoutValue();
            layoutValue.setDescription(enumChoice.getDescription());
            layoutValue.setName(enumChoice.getName());
            if (defaultValue.equals(enumChoice.getName())) {
                layoutOption.setValue(layoutValue);
            }
            layoutOption.getChoices().add(EcoreUtil.copy(layoutValue));
        }

        return layoutOption;
    }

    /**
     * Returns a new layout option with the type String.
     * 
     * @param defaultValue
     *            the default option's value of the layout algorithm.
     * @param id
     *            the option's id allowing to identified it from other options.
     * @param description
     *            a description of the option.
     * @param label
     *            a label used in Sirius UI to represent the option.
     * @return a new layout option with the type String.
     */
    public LayoutOption createStringOption(String defaultValue, String id, String description, String label) {
        StringLayoutOption layoutOption = DescriptionFactory.eINSTANCE.createStringLayoutOption();
        setCommonAttributes(id, description, label, layoutOption);
        if (defaultValue != null) {
            layoutOption.setValue(defaultValue);
        }
        return layoutOption;
    }

    /**
     * Returns a new layout option with the type Integer.
     * 
     * @param defaultValue
     *            the default option's value of the layout algorithm.
     * @param id
     *            the option's id allowing to identified it from other options.
     * @param description
     *            a description of the option.
     * @param label
     *            a label used in Sirius UI to represent the option.
     * @return a new layout option with the type Integer.
     */
    public LayoutOption createIntegerOption(Integer defaultValue, String id, String description, String label) {
        IntegerLayoutOption layoutOption = DescriptionFactory.eINSTANCE.createIntegerLayoutOption();
        setCommonAttributes(id, description, label, layoutOption);
        if (defaultValue != null) {
            layoutOption.setValue(defaultValue);
        }
        return layoutOption;
    }

    /**
     * Returns a new layout option with the type Double.
     * 
     * @param defaultValue
     *            the default option's value of the layout algorithm.
     * @param id
     *            the option's id allowing to identified it from other options.
     * @param description
     *            a description of the option.
     * @param label
     *            a label used in Sirius UI to represent the option.
     * @return a new layout option with the type Double.
     */
    public LayoutOption createDoubleOption(Double defaultValue, String id, String description, String label) {

        DoubleLayoutOption layoutOption = DescriptionFactory.eINSTANCE.createDoubleLayoutOption();
        setCommonAttributes(id, description, label, layoutOption);
        if (defaultValue != null) {
            layoutOption.setValue(defaultValue);
        }
        return layoutOption;
    }

    /**
     * Method to set commons attributes of different options.
     * 
     * @param id
     *            the id to set.
     * @param descriptionthe
     *            description to set.
     * @param label
     *            the label to set.
     * @param layoutOption
     *            the options that needs to have its attributes set.
     */
    private void setCommonAttributes(String id, String description, String label, LayoutOption layoutOption) {
        layoutOption.setId(id);
        layoutOption.setLabel(label);
        layoutOption.setDescription(description);
    }

    /**
     * Returns a new layout option with the type Boolean.
     * 
     * @param defaultValue
     *            the default option's value of the layout algorithm.
     * @param id
     *            the option's id allowing to identified it from other options.
     * @param description
     *            a description of the option.
     * @param label
     *            a label used in Sirius UI to represent the option.
     * @return a new layout option with the type Boolean.
     */
    public LayoutOption createBooleanOption(Boolean defaultValue, String id, String description, String label) {
        BooleanLayoutOption layoutOption = (BooleanLayoutOption) EcoreUtil.create(DescriptionPackage.eINSTANCE.getBooleanLayoutOption());
        setCommonAttributes(id, description, label, layoutOption);
        if (defaultValue != null) {
            layoutOption.setValue(defaultValue);
        }
        return layoutOption;
    }

}
