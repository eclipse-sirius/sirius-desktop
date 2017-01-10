/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.internal.preprocessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.properties.AbstractDynamicMappingForDescription;
import org.eclipse.sirius.properties.ButtonDescription;
import org.eclipse.sirius.properties.Category;
import org.eclipse.sirius.properties.CheckboxDescription;
import org.eclipse.sirius.properties.CustomDescription;
import org.eclipse.sirius.properties.DynamicMappingForDescription;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.HyperlinkDescription;
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.ListDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.RadioDescription;
import org.eclipse.sirius.properties.SelectDescription;
import org.eclipse.sirius.properties.TextAreaDescription;
import org.eclipse.sirius.properties.TextDescription;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.core.api.DefaultDescriptionPreprocessor;
import org.eclipse.sirius.properties.core.api.DefaultDescriptionPreprocessorWithFiltering;
import org.eclipse.sirius.properties.core.api.IDescriptionPreprocessor;
import org.eclipse.sirius.properties.core.api.PreconfiguredPreprocessor;
import org.eclipse.sirius.properties.util.PropertiesSwitch;

/**
 * This switch is used to determine which statically-available
 * {@link IDescriptionPreprocessor} to use for the given {@link EObject}.
 * 
 * @author flatombe
 * @author mbats
 */
public class PropertiesDescriptionPreprocessorSwitch extends PropertiesSwitch<Optional<IDescriptionPreprocessor>> {
    @Override
    public Optional<IDescriptionPreprocessor> caseViewExtensionDescription(ViewExtensionDescription object) {
        Collection<EStructuralFeature> featuresToCopy = new ArrayList<>();
        featuresToCopy.add(PropertiesPackage.Literals.VIEW_EXTENSION_DESCRIPTION__METAMODELS);
        return Optional.of(new DefaultDescriptionPreprocessorWithFiltering<ViewExtensionDescription>(ViewExtensionDescription.class, new ArrayList<>(), featuresToCopy));
    }

    @Override
    public Optional<IDescriptionPreprocessor> caseCategory(Category object) {
        return Optional.of(new DefaultDescriptionPreprocessorWithFiltering<Category>(Category.class, new ArrayList<>(), new ArrayList<>()));
    }

    @Override
    public Optional<IDescriptionPreprocessor> casePageDescription(PageDescription object) {
        return Optional.of(new PageDescriptionPreprocessor());
    }

    @Override
    public Optional<IDescriptionPreprocessor> caseGroupDescription(GroupDescription object) {
        return Optional.of(new GroupDescriptionPreprocessor());
    }

    // Widgets
    @Override
    public Optional<IDescriptionPreprocessor> caseTextDescription(TextDescription object) {
        return Optional.of(new PreconfiguredPreprocessor<TextDescription>(TextDescription.class, PropertiesPackage.Literals.TEXT_DESCRIPTION));
    }

    @Override
    public Optional<IDescriptionPreprocessor> caseButtonDescription(ButtonDescription object) {
        return Optional.of(new PreconfiguredPreprocessor<ButtonDescription>(ButtonDescription.class, PropertiesPackage.Literals.BUTTON_DESCRIPTION));
    }

    @Override
    public Optional<IDescriptionPreprocessor> caseLabelDescription(LabelDescription object) {
        return Optional.of(new PreconfiguredPreprocessor<LabelDescription>(LabelDescription.class, PropertiesPackage.Literals.LABEL_DESCRIPTION));
    }

    @Override
    public Optional<IDescriptionPreprocessor> caseCheckboxDescription(CheckboxDescription object) {
        return Optional.of(new PreconfiguredPreprocessor<CheckboxDescription>(CheckboxDescription.class, PropertiesPackage.Literals.CHECKBOX_DESCRIPTION));
    }

    @Override
    public Optional<IDescriptionPreprocessor> caseSelectDescription(SelectDescription object) {
        return Optional.of(new PreconfiguredPreprocessor<SelectDescription>(SelectDescription.class, PropertiesPackage.Literals.SELECT_DESCRIPTION));
    }

    @Override
    public Optional<IDescriptionPreprocessor> caseTextAreaDescription(TextAreaDescription object) {
        return Optional.of(new PreconfiguredPreprocessor<TextAreaDescription>(TextAreaDescription.class, PropertiesPackage.Literals.TEXT_AREA_DESCRIPTION));
    }

    @Override
    public Optional<IDescriptionPreprocessor> caseListDescription(ListDescription object) {
        return Optional.of(new PreconfiguredPreprocessor<ListDescription>(ListDescription.class, PropertiesPackage.Literals.LIST_DESCRIPTION));
    }

    @Override
    public Optional<IDescriptionPreprocessor> caseHyperlinkDescription(HyperlinkDescription object) {
        return Optional.of(new PreconfiguredPreprocessor<HyperlinkDescription>(HyperlinkDescription.class, PropertiesPackage.Literals.HYPERLINK_DESCRIPTION));
    }

    @Override
    public Optional<IDescriptionPreprocessor> caseRadioDescription(RadioDescription object) {
        return Optional.of(new PreconfiguredPreprocessor<RadioDescription>(RadioDescription.class, PropertiesPackage.Literals.RADIO_DESCRIPTION));
    }

    @Override
    public Optional<IDescriptionPreprocessor> caseAbstractDynamicMappingForDescription(AbstractDynamicMappingForDescription object) {
        return Optional.of(new PreconfiguredPreprocessor<DynamicMappingForDescription>(DynamicMappingForDescription.class, PropertiesPackage.Literals.DYNAMIC_MAPPING_FOR_DESCRIPTION));
    }

    @Override
    public Optional<IDescriptionPreprocessor> caseCustomDescription(CustomDescription object) {
        return Optional.of(new CustomDescriptionPreprocessor());
    }

    // Default
    @Override
    public Optional<IDescriptionPreprocessor> defaultCase(EObject object) {
        return Optional.of(new DefaultDescriptionPreprocessor<>(EObject.class));
    }
}
