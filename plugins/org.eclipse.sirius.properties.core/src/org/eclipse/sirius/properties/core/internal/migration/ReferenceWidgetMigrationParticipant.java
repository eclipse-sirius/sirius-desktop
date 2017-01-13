/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.internal.migration;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.migration.AbstractVSMMigrationParticipant;
import org.eclipse.sirius.properties.ContainerDescription;
import org.eclipse.sirius.properties.ControlDescription;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.HyperlinkDescription;
import org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle;
import org.eclipse.sirius.properties.HyperlinkWidgetStyle;
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.LabelWidgetConditionalStyle;
import org.eclipse.sirius.properties.LabelWidgetStyle;
import org.eclipse.sirius.properties.ListDescription;
import org.eclipse.sirius.properties.ListWidgetConditionalStyle;
import org.eclipse.sirius.properties.ListWidgetStyle;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Extension;
import org.eclipse.sirius.viewpoint.description.Group;
import org.osgi.framework.Version;

/**
 * The migration participant used to convert the old reference widget to lists,
 * labels or hyperlinks.
 * 
 * @author sbegaudeau
 */
public class ReferenceWidgetMigrationParticipant extends AbstractVSMMigrationParticipant {

    /**
     * The version of the migration.
     */
    private static final Version MIGRATION_VERSION = new Version("11.0.0.201609021200"); //$NON-NLS-1$

    /**
     * The name of the type to migrate.
     */
    private static final String REFERENCE_DESCRIPTION_TYPE = "ReferenceDescription"; //$NON-NLS-1$

    /**
     * The name of the "multiple" EAttribute.
     */
    private static final String REFERENCE_DESCRIPTION_MULTIPLE = "multiple"; //$NON-NLS-1$

    /**
     * The EClass used for the dynamic EObject that will be used as placeholders
     * for the old reference widgets.
     */
    private final EClass placeholderReferenceDescriptionEClass;

    /**
     * The constructor.
     */
    public ReferenceWidgetMigrationParticipant() {
        EPackage placeholderEPackage = EcoreFactory.eINSTANCE.createEPackage();
        EFactory placeholderEFactory = EcoreFactory.eINSTANCE.createEFactory();

        this.placeholderReferenceDescriptionEClass = EcoreFactory.eINSTANCE.createEClass();

        this.placeholderReferenceDescriptionEClass.setName(REFERENCE_DESCRIPTION_TYPE);
        this.placeholderReferenceDescriptionEClass.getESuperTypes().add(PropertiesPackage.Literals.LIST_DESCRIPTION);
        EAttribute multipleEAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        multipleEAttribute.setName(REFERENCE_DESCRIPTION_MULTIPLE);
        multipleEAttribute.setEType(EcorePackage.Literals.EBOOLEAN);
        this.placeholderReferenceDescriptionEClass.getEStructuralFeatures().add(multipleEAttribute);

        placeholderEPackage.getEClassifiers().add(this.placeholderReferenceDescriptionEClass);
        placeholderEPackage.setEFactoryInstance(placeholderEFactory);
    }

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public EClassifier getType(EPackage ePackage, String name, String loadedVersion) {
        // Replaces the old reference widgets with lit widgets with a custom
        // EClass
        EClassifier eClassifier = null;
        if (REFERENCE_DESCRIPTION_TYPE.equals(name)) {
            eClassifier = this.placeholderReferenceDescriptionEClass;
        }
        if (eClassifier == null) {
            eClassifier = super.getType(ePackage, name, loadedVersion);
        }
        return eClassifier;
    }

    @Override
    protected void postLoad(Group group, Version loadedVersion) {
        super.postLoad(group, loadedVersion);

        List<Extension> extensions = group.getExtensions();
        for (Extension extension : extensions) {
            if (extension instanceof ViewExtensionDescription) {
                ViewExtensionDescription viewExtensionDescription = (ViewExtensionDescription) extension;

                Set<GroupDescription> groups = new LinkedHashSet<>();
                for (PageDescription pageDescription : viewExtensionDescription.getPages()) {
                    groups.addAll(pageDescription.getGroups());
                }
                groups.addAll(viewExtensionDescription.getGroups());

                for (GroupDescription groupDescription : groups) {
                    List<ControlDescription> controls = groupDescription.getControls();
                    for (ControlDescription controlDescription : controls) {
                        this.handleControl(controlDescription);
                    }
                }
            }
        }
    }

    /**
     * This method navigates recursively on the various controls in order to
     * find all the widgets with our custom EClass in order to replace them
     * while keeping the order of the controls in the model.
     * 
     * @param controlDescription
     *            The description of a control
     */
    private void handleControl(ControlDescription controlDescription) {
        if (controlDescription instanceof ContainerDescription) {
            ContainerDescription containerDescription = (ContainerDescription) controlDescription;
            List<ControlDescription> controls = containerDescription.getControls();
            for (ControlDescription control : controls) {
                this.handleControl(control);
            }
        } else if (controlDescription instanceof ListDescription && controlDescription.eClass().equals(this.placeholderReferenceDescriptionEClass)) {
            ListDescription listDescription = (ListDescription) controlDescription;

            EObject newDescription = null;
            Object object = listDescription.eGet(this.placeholderReferenceDescriptionEClass.getEStructuralFeature(REFERENCE_DESCRIPTION_MULTIPLE));
            if (Boolean.TRUE.equals(object)) {
                newDescription = this.handleList(listDescription);
            } else if (Boolean.FALSE.equals(object) && listDescription.getOnClickOperation() != null) {
                newDescription = this.handleHyperlink(listDescription);
            } else if (Boolean.FALSE.equals(object) && listDescription.getOnClickOperation() == null) {
                newDescription = this.handleLabel(listDescription);
            }

            if (newDescription != null) {
                EcoreUtil.replace(listDescription, newDescription);
            }
        }
    }

    /**
     * Handles the creation of a proper list widget form our list placeholder.
     * 
     * @param listDescription
     *            The placeholder
     * @return A list widget
     */
    private ListDescription handleList(ListDescription listDescription) {
        ListDescription list = PropertiesFactory.eINSTANCE.createListDescription();
        list.setIdentifier(listDescription.getIdentifier());
        list.setHelpExpression(listDescription.getHelpExpression());
        list.setDisplayExpression(listDescription.getDisplayExpression());
        list.setIsEnabledExpression(listDescription.getIsEnabledExpression());
        list.setLabelExpression(listDescription.getLabelExpression());
        list.setValueExpression(listDescription.getValueExpression());
        list.getActions().addAll(listDescription.getActions());
        list.setOnClickOperation(listDescription.getOnClickOperation());
        list.setStyle(listDescription.getStyle());
        list.getConditionalStyles().addAll(listDescription.getConditionalStyles());
        return list;
    }

    /**
     * Handles the creation of a proper hyperlink widget form our list
     * placeholder.
     * 
     * @param listDescription
     *            The placeholder
     * @return A hyperlink widget
     */
    private HyperlinkDescription handleHyperlink(ListDescription listDescription) {
        HyperlinkDescription hyperlink = PropertiesFactory.eINSTANCE.createHyperlinkDescription();
        hyperlink.setIdentifier(listDescription.getIdentifier());
        hyperlink.setHelpExpression(listDescription.getHelpExpression());
        hyperlink.setDisplayExpression(listDescription.getDisplayExpression());
        hyperlink.setIsEnabledExpression(listDescription.getIsEnabledExpression());
        hyperlink.setLabelExpression(listDescription.getLabelExpression());
        hyperlink.setValueExpression(listDescription.getValueExpression());
        hyperlink.getActions().addAll(listDescription.getActions());
        hyperlink.setInitialOperation(listDescription.getOnClickOperation());

        ListWidgetStyle listWidgetStyle = listDescription.getStyle();
        if (listWidgetStyle != null) {
            hyperlink.setStyle(this.handleHyperlinkStyle(listWidgetStyle));
        }

        List<ListWidgetConditionalStyle> listConditionalStyles = listDescription.getConditionalStyles();
        for (ListWidgetConditionalStyle listWidgetConditionalStyle : listConditionalStyles) {
            HyperlinkWidgetConditionalStyle hyperlinkWidgetConditionalStyle = PropertiesFactory.eINSTANCE.createHyperlinkWidgetConditionalStyle();
            hyperlinkWidgetConditionalStyle.setPreconditionExpression(listWidgetConditionalStyle.getPreconditionExpression());
            if (listWidgetConditionalStyle.getStyle() != null) {
                hyperlinkWidgetConditionalStyle.setStyle(this.handleHyperlinkStyle(listWidgetConditionalStyle.getStyle()));
            }
            hyperlink.getConditionalStyles().add(hyperlinkWidgetConditionalStyle);
        }
        return hyperlink;
    }

    /**
     * Creates a hyperlink style from the style of the placeholder.
     * 
     * @param listWidgetStyle
     *            The style of the placeholder
     * @return A hyperlink style
     */
    private HyperlinkWidgetStyle handleHyperlinkStyle(ListWidgetStyle listWidgetStyle) {
        HyperlinkWidgetStyle hyperlinkWidgetStyle = PropertiesFactory.eINSTANCE.createHyperlinkWidgetStyle();
        hyperlinkWidgetStyle.setLabelBackgroundColor(listWidgetStyle.getLabelBackgroundColor());
        hyperlinkWidgetStyle.setLabelForegroundColor(listWidgetStyle.getLabelForegroundColor());
        hyperlinkWidgetStyle.setLabelFontNameExpression(listWidgetStyle.getLabelFontNameExpression());
        hyperlinkWidgetStyle.setLabelFontSizeExpression(listWidgetStyle.getLabelFontSizeExpression());
        hyperlinkWidgetStyle.getLabelFontFormat().addAll(listWidgetStyle.getLabelFontFormat());
        return hyperlinkWidgetStyle;
    }

    /**
     * Handles the creation of a proper label widget form our list placeholder.
     * 
     * @param listDescription
     *            The placeholder
     * @return A label widget
     */
    private LabelDescription handleLabel(ListDescription listDescription) {
        LabelDescription label = PropertiesFactory.eINSTANCE.createLabelDescription();
        label.setIdentifier(listDescription.getIdentifier());
        label.setHelpExpression(listDescription.getHelpExpression());
        label.setDisplayExpression(listDescription.getDisplayExpression());
        label.setIsEnabledExpression(listDescription.getIsEnabledExpression());
        label.setLabelExpression(listDescription.getLabelExpression());
        label.setValueExpression(listDescription.getValueExpression());
        label.getActions().addAll(listDescription.getActions());

        ListWidgetStyle listWidgetStyle = listDescription.getStyle();
        label.setStyle(this.handleLabelStyle(listWidgetStyle));

        List<ListWidgetConditionalStyle> listConditionalStyles = listDescription.getConditionalStyles();
        for (ListWidgetConditionalStyle listWidgetConditionalStyle : listConditionalStyles) {
            LabelWidgetConditionalStyle labelWidgetConditionalStyle = PropertiesFactory.eINSTANCE.createLabelWidgetConditionalStyle();
            labelWidgetConditionalStyle.setPreconditionExpression(listWidgetConditionalStyle.getPreconditionExpression());
            labelWidgetConditionalStyle.setStyle(this.handleLabelStyle(listWidgetConditionalStyle.getStyle()));
            label.getConditionalStyles().add(labelWidgetConditionalStyle);
        }
        return label;
    }

    /**
     * Creates a label style from the style of the placeholder.
     * 
     * @param listWidgetStyle
     *            The style of the placeholder
     * @return A lael style
     */
    private LabelWidgetStyle handleLabelStyle(ListWidgetStyle listWidgetStyle) {
        LabelWidgetStyle labelWidgetStyle = PropertiesFactory.eINSTANCE.createLabelWidgetStyle();
        labelWidgetStyle.setLabelBackgroundColor(listWidgetStyle.getLabelBackgroundColor());
        labelWidgetStyle.setLabelForegroundColor(listWidgetStyle.getLabelForegroundColor());
        labelWidgetStyle.setLabelFontNameExpression(listWidgetStyle.getLabelFontNameExpression());
        labelWidgetStyle.setLabelFontSizeExpression(listWidgetStyle.getLabelFontSizeExpression());
        labelWidgetStyle.getLabelFontFormat().addAll(listWidgetStyle.getLabelFontFormat());
        return labelWidgetStyle;
    }
}
