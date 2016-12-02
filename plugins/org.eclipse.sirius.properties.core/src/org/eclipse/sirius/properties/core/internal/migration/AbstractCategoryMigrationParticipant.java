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
package org.eclipse.sirius.properties.core.internal.migration;

import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.sirius.business.api.migration.AbstractVSMMigrationParticipant;
import org.eclipse.sirius.properties.Category;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Group;
import org.osgi.framework.Version;

/**
 * Superclass of all the migration participant impacted by the categories and
 * other refactoring ot the meta-model.
 * 
 * @author sbegaudeau
 */
public abstract class AbstractCategoryMigrationParticipant extends AbstractVSMMigrationParticipant {
    /**
     * The name of the feature referencing the pages.
     */
    private static final String PAGES_FEATURE_NAME = "pages"; //$NON-NLS-1$

    /**
     * The name of the features referencing the groups.
     */
    private static final String GROUPS_FEATURE_NAME = "groups"; //$NON-NLS-1$

    /**
     * The name of the old identifier feature.
     */
    private static final String IDENTIFIER = "identifier"; //$NON-NLS-1$

    /**
     * The old name of the iterable expression.
     */
    private static final String DOMAIN_CLASS_EXPRESSION = "domainClassExpression"; //$NON-NLS-1$

    @Override
    public EClassifier getType(EPackage ePackage, String name, String loadedVersion) {
        // Handle the renaming of DynamicMappingFor and DynamicMappingIf into
        // DynamicMappingForDescription and DynamicMappingIfDescription
        EClassifier eClassifier = null;
        if ("DynamicMappingFor".equals(name)) { //$NON-NLS-1$
            eClassifier = PropertiesPackage.Literals.DYNAMIC_MAPPING_FOR_DESCRIPTION;
        } else if ("DynamicMappingIf".equals(name)) { //$NON-NLS-1$
            eClassifier = PropertiesPackage.Literals.DYNAMIC_MAPPING_IF_DESCRIPTION;
        }

        if (eClassifier != null) {
            return eClassifier;
        }
        return super.getType(ePackage, name, loadedVersion);
    }

    @Override
    public EStructuralFeature getAttribute(EClass eClass, String name, String loadedVersion) {
        EStructuralFeature eStructuralFeature = null;
        if (IDENTIFIER.equals(name) && DescriptionPackage.Literals.IDENTIFIED_ELEMENT.isSuperTypeOf(eClass)) {
            // Handle the renaming of identifier into name since we are now
            // extending IdentifiedElement
            eStructuralFeature = DescriptionPackage.Literals.IDENTIFIED_ELEMENT__NAME;
        } else if (DOMAIN_CLASS_EXPRESSION.equals(name) && PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION.isSuperTypeOf(eClass)) {
            // Handle the renaming of domainClassExpression into
            // iterableExpression
            eStructuralFeature = PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERABLE_EXPRESSION;
        } else {
            eStructuralFeature = super.getAttribute(eClass, name, loadedVersion);
        }
        return eStructuralFeature;
    }

    @Override
    protected void handleFeature(EObject owner, EStructuralFeature unknownFeature, Object valueOfUnknownFeature) {
        if (owner instanceof ViewExtensionDescription && unknownFeature instanceof EReference && valueOfUnknownFeature instanceof AnyType) {
            ViewExtensionDescription viewExtensionDescription = (ViewExtensionDescription) owner;

            // Only migrate if we have a group which has been migrated by the
            // reference migration participant.
            boolean shouldMigrate = true;
            EObject eContainer = owner.eContainer();
            if (eContainer instanceof Group) {
                Group group = (Group) eContainer;
                shouldMigrate = shouldMigrate && this.getMigrationVersion().compareTo(Version.parseVersion(group.getVersion())) > 0;
            }
            if (shouldMigrate) {
                AnyType anyType = (AnyType) valueOfUnknownFeature;
                if (PAGES_FEATURE_NAME.equals(unknownFeature.getName())) {
                    this.handlePages(viewExtensionDescription, anyType);
                } else if (GROUPS_FEATURE_NAME.equals(unknownFeature.getName())) {
                    this.handleGroups(viewExtensionDescription, anyType);
                }
            }
        }
        super.handleFeature(owner, unknownFeature, valueOfUnknownFeature);
    }

    /**
     * Handles the migration of the pages of a group.
     * 
     * @param viewExtensionDescription
     *            The view extension description
     * @param anyType
     *            The value of the page
     */
    private void handlePages(ViewExtensionDescription viewExtensionDescription, AnyType anyType) {
        Category category = this.getOrCreateFirstCategory(viewExtensionDescription);

        Resource eResource = viewExtensionDescription.eResource();
        Optional<IAnyTypeConverter> optionalConverter = AnyTypeConverterRegistry.getConverter(eResource, anyType, PropertiesPackage.Literals.PAGE_DESCRIPTION);
        Optional<EObject> optionalEObject = optionalConverter.flatMap(converter -> converter.convert(eResource, anyType, PropertiesPackage.Literals.PAGE_DESCRIPTION));
        optionalEObject.filter(PageDescription.class::isInstance).map(PageDescription.class::cast).ifPresent(category.getPages()::add);
    }

    /**
     * Handles the migration of the pages of a group.
     * 
     * @param viewExtensionDescription
     *            The view extension description
     * @param anyType
     *            The value of the page
     */
    private void handleGroups(ViewExtensionDescription viewExtensionDescription, AnyType anyType) {
        Category category = this.getOrCreateFirstCategory(viewExtensionDescription);

        Resource eResource = viewExtensionDescription.eResource();
        Optional<IAnyTypeConverter> optionalConverter = AnyTypeConverterRegistry.getConverter(eResource, anyType, PropertiesPackage.Literals.GROUP_DESCRIPTION);
        Optional<EObject> optionalEObject = optionalConverter.flatMap(converter -> converter.convert(eResource, anyType, PropertiesPackage.Literals.GROUP_DESCRIPTION));
        optionalEObject.filter(GroupDescription.class::isInstance).map(GroupDescription.class::cast).ifPresent(category.getGroups()::add);
    }

    /**
     * Returns the first category of the given view extension description or
     * create it if it does not exist.
     * 
     * @param viewExtensionDescription
     *            The view extension description
     * @return The first category of the view extension description
     */
    private Category getOrCreateFirstCategory(ViewExtensionDescription viewExtensionDescription) {
        return viewExtensionDescription.getCategories().stream().findFirst().orElseGet(() -> {
            Category category = PropertiesFactory.eINSTANCE.createCategory();
            category.setName("Default"); //$NON-NLS-1$
            viewExtensionDescription.getCategories().add(category);
            return category;
        });
    }
}
