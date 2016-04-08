/*******************************************************************************
 * Copyright (c) 2015, 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal.tabprovider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.eef.EEFButtonConditionalStyle;
import org.eclipse.eef.EEFButtonDescription;
import org.eclipse.eef.EEFButtonStyle;
import org.eclipse.eef.EEFCheckboxConditionalStyle;
import org.eclipse.eef.EEFCheckboxDescription;
import org.eclipse.eef.EEFCheckboxStyle;
import org.eclipse.eef.EEFContainerDescription;
import org.eclipse.eef.EEFControlDescription;
import org.eclipse.eef.EEFCustomExpression;
import org.eclipse.eef.EEFCustomWidgetConditionalStyle;
import org.eclipse.eef.EEFCustomWidgetDescription;
import org.eclipse.eef.EEFCustomWidgetStyle;
import org.eclipse.eef.EEFDynamicMappingFor;
import org.eclipse.eef.EEFDynamicMappingIf;
import org.eclipse.eef.EEFFillLayoutDescription;
import org.eclipse.eef.EEFGridLayoutDescription;
import org.eclipse.eef.EEFGroupDescription;
import org.eclipse.eef.EEFLabelConditionalStyle;
import org.eclipse.eef.EEFLabelDescription;
import org.eclipse.eef.EEFLabelStyle;
import org.eclipse.eef.EEFPageDescription;
import org.eclipse.eef.EEFPropertyValidationRuleDescription;
import org.eclipse.eef.EEFRadioConditionalStyle;
import org.eclipse.eef.EEFRadioDescription;
import org.eclipse.eef.EEFRadioStyle;
import org.eclipse.eef.EEFReferenceConditionalStyle;
import org.eclipse.eef.EEFReferenceDescription;
import org.eclipse.eef.EEFReferenceStyle;
import org.eclipse.eef.EEFRuleAuditDescription;
import org.eclipse.eef.EEFSelectConditionalStyle;
import org.eclipse.eef.EEFSelectDescription;
import org.eclipse.eef.EEFSelectStyle;
import org.eclipse.eef.EEFSemanticValidationRuleDescription;
import org.eclipse.eef.EEFTextConditionalStyle;
import org.eclipse.eef.EEFTextDescription;
import org.eclipse.eef.EEFTextStyle;
import org.eclipse.eef.EEFValidationFixDescription;
import org.eclipse.eef.EEFValidationRuleDescription;
import org.eclipse.eef.EEFViewDescription;
import org.eclipse.eef.EEFWidgetAction;
import org.eclipse.eef.EEFWidgetDescription;
import org.eclipse.eef.EEFWidgetStyle;
import org.eclipse.eef.EEF_FILL_LAYOUT_ORIENTATION;
import org.eclipse.eef.EEF_VALIDATION_SEVERITY_DESCRIPTION;
import org.eclipse.eef.EefFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.color.AbstractColorUpdater;
import org.eclipse.sirius.properties.ButtonDescription;
import org.eclipse.sirius.properties.ButtonWidgetConditionalStyle;
import org.eclipse.sirius.properties.ButtonWidgetStyle;
import org.eclipse.sirius.properties.CheckboxDescription;
import org.eclipse.sirius.properties.CheckboxWidgetConditionalStyle;
import org.eclipse.sirius.properties.CheckboxWidgetStyle;
import org.eclipse.sirius.properties.ContainerDescription;
import org.eclipse.sirius.properties.ControlDescription;
import org.eclipse.sirius.properties.CustomDescription;
import org.eclipse.sirius.properties.CustomExpression;
import org.eclipse.sirius.properties.CustomOperation;
import org.eclipse.sirius.properties.CustomWidgetConditionalStyle;
import org.eclipse.sirius.properties.CustomWidgetStyle;
import org.eclipse.sirius.properties.DynamicMappingFor;
import org.eclipse.sirius.properties.DynamicMappingIf;
import org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION;
import org.eclipse.sirius.properties.FillLayoutDescription;
import org.eclipse.sirius.properties.GridLayoutDescription;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.LabelWidgetConditionalStyle;
import org.eclipse.sirius.properties.LabelWidgetStyle;
import org.eclipse.sirius.properties.LayoutDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertyValidationRule;
import org.eclipse.sirius.properties.RadioDescription;
import org.eclipse.sirius.properties.RadioWidgetConditionalStyle;
import org.eclipse.sirius.properties.RadioWidgetStyle;
import org.eclipse.sirius.properties.ReferenceDescription;
import org.eclipse.sirius.properties.ReferenceWidgetConditionalStyle;
import org.eclipse.sirius.properties.ReferenceWidgetStyle;
import org.eclipse.sirius.properties.SelectDescription;
import org.eclipse.sirius.properties.SelectWidgetConditionalStyle;
import org.eclipse.sirius.properties.SelectWidgetStyle;
import org.eclipse.sirius.properties.TextAreaDescription;
import org.eclipse.sirius.properties.TextDescription;
import org.eclipse.sirius.properties.TextWidgetConditionalStyle;
import org.eclipse.sirius.properties.TextWidgetStyle;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.WidgetAction;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.properties.WidgetStyle;
import org.eclipse.sirius.ui.properties.internal.SiriusInputDescriptor;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL;
import org.eclipse.sirius.viewpoint.description.validation.RuleAudit;
import org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFix;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;

/**
 * Interprets the high-level property views description defined in a Sirius VSM
 * into a lower-level EEFViewDescription suitable for the EEF runtime.
 * 
 * @author pcdavid
 */
public class ViewDescriptionConverter {
    private Map<WidgetDescription, EEFWidgetDescription> widget2eefWidget = new HashMap<>();

    private Map<EEFPropertyValidationRuleDescription, PropertyValidationRule> eefPropertyValidationRule2propertyValidationRule = new HashMap<>();

    private final List<PageDescription> pageDescriptions;

    private EObject semanticElement;

    /**
     * The constructor.
     * 
     * @param pageDescriptions
     *            The description of the pages to convert
     */
    public ViewDescriptionConverter(List<PageDescription> pageDescriptions) {
        this.pageDescriptions = pageDescriptions;
    }

    /**
     * Use the description of the pages provided in order to create an
     * {@link EEFViewDescription}.
     * 
     * @param input
     *            Semantic element
     * 
     * @return The {@link EEFViewDescription} computed
     */
    public EEFViewDescription convert(SiriusInputDescriptor input) {
        EEFViewDescription view = EefFactory.eINSTANCE.createEEFViewDescription();

        // TODO Replace by the retrieval of the image from the label provider
        view.setImageExpression("service:image");

        Set<EPackage> ePackages = new LinkedHashSet<>();
        for (PageDescription pageDescription : pageDescriptions) {
            EObject eContainer = pageDescription.eContainer();
            if (eContainer instanceof ViewExtensionDescription) {
                ViewExtensionDescription viewExtensionDescription = (ViewExtensionDescription) eContainer;
                ePackages.addAll(viewExtensionDescription.getMetamodels());
            }
        }

        view.getEPackages().addAll(ePackages);

        // TODO Replace by the retrieval of the label from the label provider
        view.setLabelExpression("feature:name");

        for (PageDescription pageDescription : pageDescriptions) {
            createPage(pageDescription, view);
        }

        // Resolve the links from property validation rules to widgets
        Set<Entry<EEFPropertyValidationRuleDescription, PropertyValidationRule>> entries = this.eefPropertyValidationRule2propertyValidationRule.entrySet();
        for (Entry<EEFPropertyValidationRuleDescription, PropertyValidationRule> entry : entries) {
            List<WidgetDescription> widgets = entry.getValue().getTargets();
            for (WidgetDescription widgetDescription : widgets) {
                EEFWidgetDescription eefWidgetDescription = this.widget2eefWidget.get(widgetDescription);
                if (eefWidgetDescription != null) {
                    EEFPropertyValidationRuleDescription eefPropertyValidationRule = entry.getKey();
                    eefPropertyValidationRule.getTargets().add(eefWidgetDescription);
                }
            }
        }
        if (input != null) {
            this.semanticElement = input.getSemanticElement();
        }
        this.widget2eefWidget.clear();
        this.eefPropertyValidationRule2propertyValidationRule.clear();

        return view;
    }

    /**
     * Creates a concrete page instance bound to a specific semantic target.
     */
    private void createPage(PageDescription pageDescription, EEFViewDescription view) {
        EEFPageDescription page = EefFactory.eINSTANCE.createEEFPageDescription();
        page.setIdentifier(pageDescription.getIdentifier());
        page.setLabelExpression(pageDescription.getLabelExpression());
        page.setDomainClass(pageDescription.getDomainClass());
        page.setSemanticCandidateExpression(pageDescription.getSemanticCandidateExpression());
        page.setPreconditionExpression(pageDescription.getPreconditionExpression());

        if (page.getIdentifier() == null || page.getIdentifier().trim().length() == 0) {
            page.setIdentifier(EcoreUtil.getURI(pageDescription).toString());
        }

        for (GroupDescription groupDescription : pageDescription.getGroups()) {
            createGroup(groupDescription, page, view);
        }

        if (pageDescription.getValidationSet() != null) {
            List<SemanticValidationRule> semanticValidationRules = pageDescription.getValidationSet().getSemanticValidationRules();
            for (SemanticValidationRule semanticValidationRule : semanticValidationRules) {
                page.getSemanticValidationRules().add(this.createSemanticValidationRuleDescription(semanticValidationRule));
            }
        }
        view.getPages().add(page);
    }

    private EEFSemanticValidationRuleDescription createSemanticValidationRuleDescription(SemanticValidationRule semanticValidationRule) {
        EEFSemanticValidationRuleDescription eefSemanticValidationRuleDescription = EefFactory.eINSTANCE.createEEFSemanticValidationRuleDescription();
        eefSemanticValidationRuleDescription.setTargetClass(semanticValidationRule.getTargetClass());
        eefSemanticValidationRuleDescription.setSeverity(this.getValidationSeverity(semanticValidationRule.getLevel()));

        this.createValidationRuleContent(eefSemanticValidationRuleDescription, semanticValidationRule);
        return eefSemanticValidationRuleDescription;
    }

    private EEFPropertyValidationRuleDescription createPropertyValidationRuleDescription(PropertyValidationRule propertyValidationRule) {
        EEFPropertyValidationRuleDescription eefPropertyValidationRuleDescription = EefFactory.eINSTANCE.createEEFPropertyValidationRuleDescription();
        eefPropertyValidationRuleDescription.setSeverity(this.getValidationSeverity(propertyValidationRule.getLevel()));

        this.createValidationRuleContent(eefPropertyValidationRuleDescription, propertyValidationRule);

        return eefPropertyValidationRuleDescription;
    }

    private void createValidationRuleContent(EEFValidationRuleDescription eefValidationRuleDescription, ValidationRule validationRule) {
        eefValidationRuleDescription.setMessageExpression(validationRule.getMessage());

        List<RuleAudit> audits = validationRule.getAudits();
        for (RuleAudit audit : audits) {
            EEFRuleAuditDescription eefRuleAuditDescription = EefFactory.eINSTANCE.createEEFRuleAuditDescription();
            eefRuleAuditDescription.setAuditExpression(audit.getAuditExpression());
            eefValidationRuleDescription.getAudits().add(eefRuleAuditDescription);
        }

        List<ValidationFix> fixes = validationRule.getFixes();
        for (ValidationFix validationFix : fixes) {
            EEFValidationFixDescription eefValidationFixDescription = EefFactory.eINSTANCE.createEEFValidationFixDescription();
            eefValidationFixDescription.setName(validationFix.getName());
            eefValidationFixDescription.setFixExpression(this.getExpressionForOperation(validationFix.getInitialOperation()));
            eefValidationRuleDescription.getFixes().add(eefValidationFixDescription);
        }
    }

    private EEF_VALIDATION_SEVERITY_DESCRIPTION getValidationSeverity(ERROR_LEVEL level) {
        EEF_VALIDATION_SEVERITY_DESCRIPTION severity = EEF_VALIDATION_SEVERITY_DESCRIPTION.INFO;

        switch (level) {
        case INFO_LITERAL:
            severity = EEF_VALIDATION_SEVERITY_DESCRIPTION.INFO;
            break;
        case WARNING_LITERAL:
            severity = EEF_VALIDATION_SEVERITY_DESCRIPTION.WARNING;
            break;
        case ERROR_LITERAL:
            severity = EEF_VALIDATION_SEVERITY_DESCRIPTION.ERROR;
            break;
        default:
            severity = EEF_VALIDATION_SEVERITY_DESCRIPTION.INFO;
            break;
        }

        return severity;
    }

    /**
     * Creates a concrete group instance bound to a specific semantic target.
     */
    private void createGroup(GroupDescription groupDescription, EEFPageDescription page, EEFViewDescription view) {
        EEFGroupDescription group = EefFactory.eINSTANCE.createEEFGroupDescription();
        group.setIdentifier(groupDescription.getIdentifier());
        group.setLabelExpression(groupDescription.getLabelExpression());
        group.setDomainClass(groupDescription.getDomainClass());
        group.setSemanticCandidateExpression(groupDescription.getSemanticCandidateExpression());
        group.setPreconditionExpression(groupDescription.getPreconditionExpression());

        if (group.getIdentifier() == null || group.getIdentifier().trim().length() == 0) {
            group.setIdentifier(EcoreUtil.getURI(groupDescription).toString());
        }

        convertGroupContents(groupDescription, group);

        if (groupDescription.getValidationSet() != null) {
            for (SemanticValidationRule semanticValidationRule : groupDescription.getValidationSet().getSemanticValidationRules()) {
                group.getSemanticValidationRules().add(this.createSemanticValidationRuleDescription(semanticValidationRule));
            }

            for (PropertyValidationRule propertyValidationRule : groupDescription.getValidationSet().getPropertyValidationRules()) {
                EEFPropertyValidationRuleDescription propertyValidationRuleDescription = this.createPropertyValidationRuleDescription(propertyValidationRule);
                this.eefPropertyValidationRule2propertyValidationRule.put(propertyValidationRuleDescription, propertyValidationRule);
                group.getPropertyValidationRules().add(propertyValidationRuleDescription);
            }
        }

        page.getGroups().add(group);
        view.getGroups().add(group);
    }

    private void convertGroupContents(GroupDescription groupDescription, EEFGroupDescription group) {
        List<ControlDescription> controls = groupDescription.getControls();
        for (ControlDescription controlDescription : controls) {
            EEFControlDescription eefControlDescription = this.createEEFControlDescription(controlDescription);
            if (eefControlDescription != null) {
                group.getControls().add(eefControlDescription);
            }
        }
    }

    private EEFControlDescription createEEFControlDescription(ControlDescription controlDescription) {
        EEFControlDescription eefControlDescription = null;
        if (controlDescription instanceof WidgetDescription) {
            WidgetDescription widgetDescription = (WidgetDescription) controlDescription;
            eefControlDescription = this.createEEFWidgetDescription(widgetDescription);
        } else if (controlDescription instanceof ContainerDescription) {
            ContainerDescription containerDescription = (ContainerDescription) controlDescription;
            eefControlDescription = this.createEEFContainerDescription(containerDescription);
        } else if (controlDescription instanceof DynamicMappingFor) {
            DynamicMappingFor dynamicMappingFor = (DynamicMappingFor) controlDescription;
            eefControlDescription = this.createEEFDynamicMappingFor(dynamicMappingFor);
        }
        return eefControlDescription;
    }

    private EEFContainerDescription createEEFContainerDescription(ContainerDescription containerDescription) {
        EEFContainerDescription eefContainerDescription = EefFactory.eINSTANCE.createEEFContainerDescription();

        LayoutDescription layout = containerDescription.getLayout();
        if (layout instanceof FillLayoutDescription) {
            EEFFillLayoutDescription eefFillLayoutDescription = EefFactory.eINSTANCE.createEEFFillLayoutDescription();
            if (((FillLayoutDescription) layout).getOrientation() == FILL_LAYOUT_ORIENTATION.HORIZONTAL) {
                eefFillLayoutDescription.setOrientation(EEF_FILL_LAYOUT_ORIENTATION.HORIZONTAL);
            } else if (((FillLayoutDescription) layout).getOrientation() == FILL_LAYOUT_ORIENTATION.VERTICAL) {
                eefFillLayoutDescription.setOrientation(EEF_FILL_LAYOUT_ORIENTATION.VERTICAL);
            }
            eefContainerDescription.setLayout(eefFillLayoutDescription);
        } else if (layout instanceof GridLayoutDescription) {
            EEFGridLayoutDescription eefGridLayoutDescription = EefFactory.eINSTANCE.createEEFGridLayoutDescription();
            eefGridLayoutDescription.setNumberOfColumns(((GridLayoutDescription) layout).getNumberOfColumns());
            eefGridLayoutDescription.setMakeColumnsWithEqualWidth(((GridLayoutDescription) layout).isMakeColumnsWithEqualWidth());
            eefContainerDescription.setLayout(eefGridLayoutDescription);
        }

        List<ControlDescription> controls = containerDescription.getControls();
        for (ControlDescription controlDescription : controls) {
            EEFControlDescription eefControlDescription = this.createEEFControlDescription(controlDescription);
            if (eefControlDescription != null) {
                eefContainerDescription.getControls().add(eefControlDescription);
            }
        }
        return eefContainerDescription;
    }

    private EEFDynamicMappingFor createEEFDynamicMappingFor(DynamicMappingFor dynamicMappingFor) {
        EEFDynamicMappingFor eefDynamicMappingFor = EefFactory.eINSTANCE.createEEFDynamicMappingFor();
        eefDynamicMappingFor.setDomainClassExpression(dynamicMappingFor.getDomainClassExpression());
        eefDynamicMappingFor.setIterator(dynamicMappingFor.getIterator());

        List<DynamicMappingIf> dynamicMappingIfs = dynamicMappingFor.getIfs();
        for (DynamicMappingIf dynamicMappingIf : dynamicMappingIfs) {
            EEFDynamicMappingIf eefDynamicMappingIf = EefFactory.eINSTANCE.createEEFDynamicMappingIf();
            eefDynamicMappingIf.setPredicateExpression(dynamicMappingIf.getPredicateExpression());

            EEFWidgetDescription eefWidgetDescription = this.createEEFWidgetDescription(dynamicMappingIf.getWidget());
            eefDynamicMappingIf.setWidget(eefWidgetDescription);

            eefDynamicMappingFor.getIfs().add(eefDynamicMappingIf);
        }
        return eefDynamicMappingFor;
    }

    private EEFWidgetDescription createEEFWidgetDescription(WidgetDescription widgetDescription) {
        EEFWidgetDescription description = null;
        if (widgetDescription instanceof TextAreaDescription) {
            description = createEEFTextDescription((TextAreaDescription) widgetDescription);
        } else if (widgetDescription instanceof TextDescription) {
            description = createEEFTextDescription((TextDescription) widgetDescription);
        } else if (widgetDescription instanceof LabelDescription) {
            description = createEEFLabelDescription((LabelDescription) widgetDescription);
        } else if (widgetDescription instanceof CheckboxDescription) {
            description = createEEFCheckboxDescription((CheckboxDescription) widgetDescription);
        } else if (widgetDescription instanceof SelectDescription) {
            description = createEEFSelectDescription((SelectDescription) widgetDescription);
        } else if (widgetDescription instanceof ButtonDescription) {
            description = createEEFButtonDescription((ButtonDescription) widgetDescription);
        } else if (widgetDescription instanceof RadioDescription) {
            description = createEEFRadioDescription((RadioDescription) widgetDescription);
        } else if (widgetDescription instanceof ReferenceDescription) {
            description = createEEFReferenceDescription((ReferenceDescription) widgetDescription);
        } else if (widgetDescription instanceof CustomDescription) {
            description = createEEFCustomDescription((CustomDescription) widgetDescription);
        }

        if (description != null) {
            description.setHelpExpression(widgetDescription.getHelpExpression());
            description.setLabelExpression(widgetDescription.getLabelExpression());
            this.widget2eefWidget.put(widgetDescription, description);
        }

        return description;
    }

    private EEFWidgetStyle createEEFWidgetStyle(WidgetStyle widgetStyle) {
        EEFWidgetStyle eefWidgetStyle = null;

        if (widgetStyle instanceof TextWidgetStyle) {
            eefWidgetStyle = createEEFTextStyle((TextWidgetStyle) widgetStyle);
        } else if (widgetStyle instanceof LabelWidgetStyle) {
            eefWidgetStyle = createEEFLabelStyle((LabelWidgetStyle) widgetStyle);
        } else if (widgetStyle instanceof ButtonWidgetStyle) {
            eefWidgetStyle = EefFactory.eINSTANCE.createEEFButtonStyle();
        } else if (widgetStyle instanceof CheckboxWidgetStyle) {
            eefWidgetStyle = EefFactory.eINSTANCE.createEEFCheckboxStyle();
        } else if (widgetStyle instanceof RadioWidgetStyle) {
            eefWidgetStyle = EefFactory.eINSTANCE.createEEFRadioStyle();
        } else if (widgetStyle instanceof SelectWidgetStyle) {
            eefWidgetStyle = EefFactory.eINSTANCE.createEEFSelectStyle();
        } else if (widgetStyle instanceof CustomWidgetStyle) {
            eefWidgetStyle = EefFactory.eINSTANCE.createEEFCustomWidgetStyle();
        } else if (widgetStyle instanceof ReferenceWidgetStyle) {
            eefWidgetStyle = EefFactory.eINSTANCE.createEEFReferenceStyle();
        }

        // Set label style
        if (eefWidgetStyle != null) {
            ColorDescription backgroundColorDescription = widgetStyle.getLabelBackgroundColor();
            if (backgroundColorDescription != null) {
                String backgroundColorExpression = getColorExpression(backgroundColorDescription);
                if (backgroundColorExpression != null) {
                    eefWidgetStyle.setLabelBackgroundColorExpression(backgroundColorExpression);
                }
            }

            ColorDescription foregroundColorDescription = widgetStyle.getLabelForegroundColor();
            if (foregroundColorDescription != null) {
                String foregroundColorExpression = getColorExpression(foregroundColorDescription);
                if (foregroundColorExpression != null) {
                    eefWidgetStyle.setLabelForegroundColorExpression(foregroundColorExpression);
                }
            }

            eefWidgetStyle.setLabelFontNameExpression(widgetStyle.getLabelFontNameExpression());
            eefWidgetStyle.setLabelFontSizeExpression(Integer.toString(widgetStyle.getLabelFontSize()));
            eefWidgetStyle.setLabelFontStyleExpression(getFontStyleExpression(widgetStyle.getLabelFontFormat()));
        }
        return eefWidgetStyle;
    }

    private EEFTextStyle createEEFTextStyle(TextWidgetStyle textStyle) {
        EEFTextStyle eefTextStyle = EefFactory.eINSTANCE.createEEFTextStyle();

        ColorDescription backgroundColorDescription = textStyle.getBackgroundColor();
        if (backgroundColorDescription != null) {
            String backgroundColorExpression = getColorExpression(backgroundColorDescription);
            if (backgroundColorExpression != null) {
                eefTextStyle.setBackgroundColorExpression(backgroundColorExpression);
            }
        }

        ColorDescription foregroundColorDescription = textStyle.getForegroundColor();
        if (foregroundColorDescription != null) {
            String foregroundColorExpression = getColorExpression(foregroundColorDescription);
            if (foregroundColorExpression != null) {
                eefTextStyle.setForegroundColorExpression(foregroundColorExpression);
            }
        }

        eefTextStyle.setFontNameExpression(textStyle.getFontNameExpression());
        eefTextStyle.setFontSizeExpression(Integer.toString(textStyle.getFontSize()));
        eefTextStyle.setFontStyleExpression(getFontStyleExpression(textStyle.getFontFormat()));

        return eefTextStyle;
    }

    private EEFLabelStyle createEEFLabelStyle(LabelWidgetStyle labelStyle) {
        EEFLabelStyle eefLabelStyle = EefFactory.eINSTANCE.createEEFLabelStyle();

        ColorDescription backgroundColorDescription = labelStyle.getBackgroundColor();
        if (backgroundColorDescription != null) {
            String backgroundColorExpression = getColorExpression(backgroundColorDescription);
            if (backgroundColorExpression != null) {
                eefLabelStyle.setBackgroundColorExpression(backgroundColorExpression);
            }
        }

        ColorDescription foregroundColorDescription = labelStyle.getForegroundColor();
        if (foregroundColorDescription != null) {
            String foregroundColorExpression = getColorExpression(foregroundColorDescription);
            if (foregroundColorExpression != null) {
                eefLabelStyle.setForegroundColorExpression(foregroundColorExpression);
            }
        }

        eefLabelStyle.setFontNameExpression(labelStyle.getFontNameExpression());
        eefLabelStyle.setFontSizeExpression(Integer.toString(labelStyle.getFontSize()));
        eefLabelStyle.setFontStyleExpression(getFontStyleExpression(labelStyle.getFontFormat()));

        return eefLabelStyle;
    }

    private String getFontStyleExpression(List<FontFormat> fontFormats) {
        String fontFormat = fontFormats.toString();
        return fontFormat.substring(1, fontFormat.length() - 1);
    }

    private EEFTextDescription createEEFTextDescription(TextDescription textDescription) {
        EEFTextDescription eefTextDescription = EefFactory.eINSTANCE.createEEFTextDescription();

        eefTextDescription.setIdentifier(textDescription.getIdentifier());
        eefTextDescription.setValueExpression(textDescription.getValueExpression());

        InitialOperation initialOperation = textDescription.getInitialOperation();
        eefTextDescription.setEditExpression(getExpressionForOperation(initialOperation));

        TextWidgetStyle textStyle = textDescription.getStyle();
        if (textStyle != null) {
            eefTextDescription.setStyle((EEFTextStyle) createEEFWidgetStyle(textStyle));
        }

        List<TextWidgetConditionalStyle> conditionalStyles = textDescription.getConditionalStyles();
        if (conditionalStyles != null && !conditionalStyles.isEmpty()) {
            List<EEFTextConditionalStyle> eefConditionalStyles = new ArrayList<EEFTextConditionalStyle>();
            for (TextWidgetConditionalStyle conditionalStyle : conditionalStyles) {
                EEFTextConditionalStyle eefConditionalStyle = EefFactory.eINSTANCE.createEEFTextConditionalStyle();
                eefConditionalStyle.setPreconditionExpression(conditionalStyle.getPreconditionExpression());
                eefConditionalStyle.setStyle((EEFTextStyle) createEEFWidgetStyle(conditionalStyle.getStyle()));
                eefConditionalStyles.add(eefConditionalStyle);
            }

            if (eefConditionalStyles != null && !eefConditionalStyles.isEmpty()) {
                eefTextDescription.getConditionalStyles().addAll(eefConditionalStyles);
            }
        }
        return eefTextDescription;
    }

    /**
     * Get the rgb color expression from a color description.
     * 
     * @param colorDescription
     *            Color description
     * @return A string representing the color as 'rgb(0,0,0)'
     */
    private String getColorExpression(ColorDescription colorDescription) {
        // TODO To update when new
        // ColorQuery(colorDescription).getRGBValues(EObject context,
        // IInterpreter itp) will be available
        // See bug 490661:
        // https://bugs.eclipse.org/bugs/show_bug.cgi?id=490661
        AbstractColorUpdater colorUpdater = new AbstractColorUpdater();
        RGBValues rgbValues = colorUpdater.getRGBValuesFromColorDescription(this.semanticElement, colorDescription);
        if (rgbValues != null) {
            return "rgb(" + rgbValues.getRed() + "," + rgbValues.getGreen() + "," + rgbValues.getBlue() + ")";
        }
        return null;
    }

    private String getExpressionForOperation(InitialOperation initialOperation) {
        if (initialOperation != null) {
            return "aql:self.executeOperation('" + EcoreUtil.getURI(initialOperation).toString() + "')";
        }
        return null;
    }

    private EEFTextDescription createEEFTextDescription(TextAreaDescription textDescription) {
        EEFTextDescription eefTextDescription = EefFactory.eINSTANCE.createEEFTextDescription();

        eefTextDescription.setIdentifier(textDescription.getIdentifier());
        eefTextDescription.setValueExpression(textDescription.getValueExpression());
        eefTextDescription.setLineCount(textDescription.getLineCount());

        InitialOperation initialOperation = textDescription.getInitialOperation();
        eefTextDescription.setEditExpression(getExpressionForOperation(initialOperation));

        TextWidgetStyle textStyle = textDescription.getStyle();
        if (textStyle != null) {
            EEFTextStyle eefTextStyle = createEEFTextStyle(textStyle);
            eefTextDescription.setStyle(eefTextStyle);
        }
        return eefTextDescription;
    }

    private EEFLabelDescription createEEFLabelDescription(LabelDescription labelDescription) {
        EEFLabelDescription eefLabelDescription = EefFactory.eINSTANCE.createEEFLabelDescription();

        eefLabelDescription.setIdentifier(labelDescription.getIdentifier());
        eefLabelDescription.setBodyExpression(labelDescription.getBodyExpression());
        LabelWidgetStyle bodyStyle = labelDescription.getStyle();
        if (bodyStyle != null) {
            eefLabelDescription.setStyle((EEFLabelStyle) createEEFWidgetStyle(bodyStyle));
        }

        List<LabelWidgetConditionalStyle> conditionalStyles = labelDescription.getConditionalStyles();
        if (conditionalStyles != null && !conditionalStyles.isEmpty()) {
            List<EEFLabelConditionalStyle> eefConditionalStyles = new ArrayList<EEFLabelConditionalStyle>();
            for (LabelWidgetConditionalStyle conditionalStyle : conditionalStyles) {
                EEFLabelConditionalStyle eefConditionalStyle = EefFactory.eINSTANCE.createEEFLabelConditionalStyle();
                eefConditionalStyle.setPreconditionExpression(conditionalStyle.getPreconditionExpression());
                eefConditionalStyle.setStyle((EEFLabelStyle) createEEFWidgetStyle(conditionalStyle.getStyle()));
                eefConditionalStyles.add(eefConditionalStyle);
            }

            if (eefConditionalStyles != null && !eefConditionalStyles.isEmpty()) {
                eefLabelDescription.getConditionalStyles().addAll(eefConditionalStyles);
            }
        }

        return eefLabelDescription;
    }

    private EEFButtonDescription createEEFButtonDescription(ButtonDescription buttonDescription) {
        EEFButtonDescription eefButtonDescription = EefFactory.eINSTANCE.createEEFButtonDescription();

        eefButtonDescription.setIdentifier(buttonDescription.getIdentifier());
        eefButtonDescription.setButtonLabelExpression(buttonDescription.getButtonLabelExpression());
        InitialOperation initialOperation = buttonDescription.getInitialOperation();
        eefButtonDescription.setPushExpression(getExpressionForOperation(initialOperation));

        ButtonWidgetStyle buttonStyle = buttonDescription.getStyle();
        if (buttonStyle != null) {
            eefButtonDescription.setStyle((EEFButtonStyle) createEEFWidgetStyle(buttonStyle));
        }

        List<ButtonWidgetConditionalStyle> conditionalStyles = buttonDescription.getConditionalStyles();
        if (conditionalStyles != null && !conditionalStyles.isEmpty()) {
            List<EEFButtonConditionalStyle> eefConditionalStyles = new ArrayList<EEFButtonConditionalStyle>();
            for (ButtonWidgetConditionalStyle conditionalStyle : conditionalStyles) {
                EEFButtonConditionalStyle eefConditionalStyle = EefFactory.eINSTANCE.createEEFButtonConditionalStyle();
                eefConditionalStyle.setPreconditionExpression(conditionalStyle.getPreconditionExpression());
                eefConditionalStyle.setStyle((EEFButtonStyle) createEEFWidgetStyle(conditionalStyle.getStyle()));
                eefConditionalStyles.add(eefConditionalStyle);
            }

            if (eefConditionalStyles != null && !eefConditionalStyles.isEmpty()) {
                eefButtonDescription.getConditionalStyles().addAll(eefConditionalStyles);
            }
        }

        return eefButtonDescription;
    }

    private EEFCheckboxDescription createEEFCheckboxDescription(CheckboxDescription checkboxDescription) {
        EEFCheckboxDescription eefCheckboxDescription = EefFactory.eINSTANCE.createEEFCheckboxDescription();

        eefCheckboxDescription.setIdentifier(checkboxDescription.getIdentifier());
        eefCheckboxDescription.setValueExpression(checkboxDescription.getValueExpression());

        CheckboxWidgetStyle checkboxStyle = checkboxDescription.getStyle();
        if (checkboxStyle != null) {
            eefCheckboxDescription.setStyle((EEFCheckboxStyle) createEEFWidgetStyle(checkboxStyle));
        }

        List<CheckboxWidgetConditionalStyle> conditionalStyles = checkboxDescription.getConditionalStyles();
        if (conditionalStyles != null && !conditionalStyles.isEmpty()) {
            List<EEFCheckboxConditionalStyle> eefConditionalStyles = new ArrayList<EEFCheckboxConditionalStyle>();
            for (CheckboxWidgetConditionalStyle conditionalStyle : conditionalStyles) {
                EEFCheckboxConditionalStyle eefConditionalStyle = EefFactory.eINSTANCE.createEEFCheckboxConditionalStyle();
                eefConditionalStyle.setPreconditionExpression(conditionalStyle.getPreconditionExpression());
                eefConditionalStyle.setStyle((EEFCheckboxStyle) createEEFWidgetStyle(conditionalStyle.getStyle()));
                eefConditionalStyles.add(eefConditionalStyle);
            }

            if (eefConditionalStyles != null && !eefConditionalStyles.isEmpty()) {
                eefCheckboxDescription.getConditionalStyles().addAll(eefConditionalStyles);
            }
        }

        return eefCheckboxDescription;
    }

    private EEFSelectDescription createEEFSelectDescription(SelectDescription selectDescription) {
        EEFSelectDescription eefSelectDescription = EefFactory.eINSTANCE.createEEFSelectDescription();

        eefSelectDescription.setIdentifier(selectDescription.getIdentifier());
        eefSelectDescription.setValueExpression(selectDescription.getValueExpression());
        eefSelectDescription.setCandidatesExpression(selectDescription.getCandidatesExpression());
        eefSelectDescription.setCandidateDisplayExpression(selectDescription.getCandidateDisplayExpression());

        SelectWidgetStyle selectStyle = selectDescription.getStyle();
        if (selectStyle != null) {
            eefSelectDescription.setStyle((EEFSelectStyle) createEEFWidgetStyle(selectStyle));
        }

        List<SelectWidgetConditionalStyle> conditionalStyles = selectDescription.getConditionalStyles();
        if (conditionalStyles != null) {
            List<EEFSelectConditionalStyle> eefConditionalStyles = new ArrayList<EEFSelectConditionalStyle>();
            for (SelectWidgetConditionalStyle conditionalStyle : conditionalStyles) {
                EEFSelectConditionalStyle eefConditionalStyle = EefFactory.eINSTANCE.createEEFSelectConditionalStyle();
                eefConditionalStyle.setPreconditionExpression(conditionalStyle.getPreconditionExpression());
                eefConditionalStyle.setStyle((EEFSelectStyle) createEEFWidgetStyle(conditionalStyle.getStyle()));
                eefConditionalStyles.add(eefConditionalStyle);
            }

            if (eefConditionalStyles != null && !eefConditionalStyles.isEmpty()) {
                eefSelectDescription.getConditionalStyles().addAll(eefConditionalStyles);
            }
        }

        return eefSelectDescription;
    }

    private EEFRadioDescription createEEFRadioDescription(RadioDescription radioDescription) {
        EEFRadioDescription eefRadioDescription = EefFactory.eINSTANCE.createEEFRadioDescription();

        eefRadioDescription.setIdentifier(radioDescription.getIdentifier());
        eefRadioDescription.setValueExpression(radioDescription.getValueExpression());
        eefRadioDescription.setCandidatesExpression(radioDescription.getCandidatesExpression());
        eefRadioDescription.setCandidateDisplayExpression(radioDescription.getCandidateDisplayExpression());
        eefRadioDescription.setNumberOfColumns(radioDescription.getNumberOfColumns());
        RadioWidgetStyle radioStyle = radioDescription.getStyle();
        if (radioStyle != null) {
            eefRadioDescription.setStyle((EEFRadioStyle) createEEFWidgetStyle(radioStyle));
        }

        List<RadioWidgetConditionalStyle> conditionalStyles = radioDescription.getConditionalStyles();
        if (conditionalStyles != null && !conditionalStyles.isEmpty()) {
            List<EEFRadioConditionalStyle> eefConditionalStyles = new ArrayList<EEFRadioConditionalStyle>();
            for (RadioWidgetConditionalStyle conditionalStyle : conditionalStyles) {
                EEFRadioConditionalStyle eefConditionalStyle = EefFactory.eINSTANCE.createEEFRadioConditionalStyle();
                eefConditionalStyle.setPreconditionExpression(conditionalStyle.getPreconditionExpression());
                eefConditionalStyle.setStyle((EEFRadioStyle) createEEFWidgetStyle(conditionalStyle.getStyle()));
                eefConditionalStyles.add(eefConditionalStyle);
            }

            if (eefConditionalStyles != null && !eefConditionalStyles.isEmpty()) {
                eefRadioDescription.getConditionalStyles().addAll(eefConditionalStyles);
            }
        }

        return eefRadioDescription;
    }

    private EEFReferenceDescription createEEFReferenceDescription(ReferenceDescription referenceDescription) {
        EEFReferenceDescription eefReferenceDescription = EefFactory.eINSTANCE.createEEFReferenceDescription();
        eefReferenceDescription.setIdentifier(referenceDescription.getIdentifier());
        eefReferenceDescription.setValueExpression(referenceDescription.getValueExpression());
        eefReferenceDescription.setMultiple(referenceDescription.isMultiple());
        eefReferenceDescription.setDisplayExpression(referenceDescription.getDisplayExpression());
        eefReferenceDescription.setOnClickExpression(getExpressionForOperation(referenceDescription.getOnClickOperation()));
        for (WidgetAction action : referenceDescription.getActions()) {
            EEFWidgetAction eefAction = EefFactory.eINSTANCE.createEEFWidgetAction();
            eefAction.setLabelExpression(action.getLabelExpression());
            eefAction.setActionExpression(getExpressionForOperation(action.getInitialOperation()));
            eefReferenceDescription.getActions().add(eefAction);
        }
        ReferenceWidgetStyle referenceStyle = referenceDescription.getStyle();
        if (referenceStyle != null) {
            eefReferenceDescription.setStyle((EEFReferenceStyle) createEEFWidgetStyle(referenceStyle));
        }

        List<ReferenceWidgetConditionalStyle> conditionalStyles = referenceDescription.getConditionalStyles();
        if (conditionalStyles != null && !conditionalStyles.isEmpty()) {
            List<EEFReferenceConditionalStyle> eefConditionalStyles = new ArrayList<EEFReferenceConditionalStyle>();
            for (ReferenceWidgetConditionalStyle conditionalStyle : conditionalStyles) {
                EEFReferenceConditionalStyle eefConditionalStyle = EefFactory.eINSTANCE.createEEFReferenceConditionalStyle();
                eefConditionalStyle.setPreconditionExpression(conditionalStyle.getPreconditionExpression());
                eefConditionalStyle.setStyle((EEFReferenceStyle) createEEFWidgetStyle(conditionalStyle.getStyle()));
                eefConditionalStyles.add(eefConditionalStyle);
            }

            if (eefConditionalStyles != null && !eefConditionalStyles.isEmpty()) {
                eefReferenceDescription.getConditionalStyles().addAll(eefConditionalStyles);
            }
        }
        return eefReferenceDescription;
    }

    private EEFCustomWidgetDescription createEEFCustomDescription(CustomDescription customDescription) {
        EEFCustomWidgetDescription eefCustomDescription = EefFactory.eINSTANCE.createEEFCustomWidgetDescription();
        eefCustomDescription.setIdentifier(customDescription.getIdentifier());
        eefCustomDescription.setLabelExpression(customDescription.getLabelExpression());
        for (CustomExpression customExpression : customDescription.getCustomExpressions()) {
            EEFCustomExpression eefCustomExpression = EefFactory.eINSTANCE.createEEFCustomExpression();
            eefCustomExpression.setIdentifier(customExpression.getIdentifier());
            String expression = customExpression.getCustomExpression();
            eefCustomExpression.setCustomExpression(expression);
            eefCustomDescription.getCustomExpressions().add(eefCustomExpression);
        }

        for (CustomOperation customOperation : customDescription.getCustomOperations()) {
            EEFCustomExpression eefCustomExpression = EefFactory.eINSTANCE.createEEFCustomExpression();

            eefCustomExpression.setIdentifier(customOperation.getIdentifier());
            eefCustomExpression.setCustomExpression(getExpressionForOperation(customOperation.getInitialOperation()));
            eefCustomDescription.getCustomExpressions().add(eefCustomExpression);
        }

        CustomWidgetStyle customStyle = customDescription.getStyle();
        if (customStyle != null) {
            eefCustomDescription.setStyle((EEFCustomWidgetStyle) createEEFWidgetStyle(customStyle));
        }

        List<CustomWidgetConditionalStyle> conditionalStyles = customDescription.getConditionalStyles();
        if (conditionalStyles != null && !conditionalStyles.isEmpty()) {
            List<EEFCustomWidgetConditionalStyle> eefConditionalStyles = new ArrayList<EEFCustomWidgetConditionalStyle>();
            for (CustomWidgetConditionalStyle conditionalStyle : conditionalStyles) {
                EEFCustomWidgetConditionalStyle eefConditionalStyle = EefFactory.eINSTANCE.createEEFCustomWidgetConditionalStyle();
                eefConditionalStyle.setPreconditionExpression(conditionalStyle.getPreconditionExpression());
                eefConditionalStyle.setStyle((EEFCustomWidgetStyle) createEEFWidgetStyle(conditionalStyle.getStyle()));
                eefConditionalStyles.add(eefConditionalStyle);
            }

            if (eefConditionalStyles != null && !eefConditionalStyles.isEmpty()) {
                eefCustomDescription.getConditionalStyles().addAll(eefConditionalStyles);
            }
        }
        return eefCustomDescription;
    }
}
