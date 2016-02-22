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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.eef.EEFButtonDescription;
import org.eclipse.eef.EEFCheckboxDescription;
import org.eclipse.eef.EEFContainerDescription;
import org.eclipse.eef.EEFCustomExpression;
import org.eclipse.eef.EEFCustomWidgetDescription;
import org.eclipse.eef.EEFDynamicMappingFor;
import org.eclipse.eef.EEFDynamicMappingIf;
import org.eclipse.eef.EEFGroupDescription;
import org.eclipse.eef.EEFLabelDescription;
import org.eclipse.eef.EEFPageDescription;
import org.eclipse.eef.EEFPropertyValidationRuleDescription;
import org.eclipse.eef.EEFRadioDescription;
import org.eclipse.eef.EEFRuleAuditDescription;
import org.eclipse.eef.EEFSelectDescription;
import org.eclipse.eef.EEFSemanticValidationRuleDescription;
import org.eclipse.eef.EEFTextDescription;
import org.eclipse.eef.EEFValidationFixDescription;
import org.eclipse.eef.EEFValidationRuleDescription;
import org.eclipse.eef.EEFViewDescription;
import org.eclipse.eef.EEFWidgetDescription;
import org.eclipse.eef.EEF_VALIDATION_SEVERITY_DESCRIPTION;
import org.eclipse.eef.EefFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.properties.ButtonDescription;
import org.eclipse.sirius.properties.CheckboxDescription;
import org.eclipse.sirius.properties.CustomDescription;
import org.eclipse.sirius.properties.CustomExpression;
import org.eclipse.sirius.properties.CustomOperation;
import org.eclipse.sirius.properties.DynamicMappingFor;
import org.eclipse.sirius.properties.DynamicMappingIf;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertyValidationRule;
import org.eclipse.sirius.properties.RadioDescription;
import org.eclipse.sirius.properties.SelectDescription;
import org.eclipse.sirius.properties.TextAreaDescription;
import org.eclipse.sirius.properties.TextDescription;
import org.eclipse.sirius.properties.WidgetDescription;
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

    /**
     * The contrsuctor.
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
     * @return The {@link EEFViewDescription} computed
     */
    public EEFViewDescription convert() {
        EEFViewDescription view = EefFactory.eINSTANCE.createEEFViewDescription();
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
        EEFContainerDescription containerDesc = EefFactory.eINSTANCE.createEEFContainerDescription();

        if (groupDescription.getContainer() != null) {
            for (WidgetDescription widgetDescription : groupDescription.getContainer().getWidgets()) {
                EEFWidgetDescription description = this.createEEFWidgetDescription(widgetDescription);
                if (description != null) {
                    containerDesc.getWidgets().add(description);
                }
            }
            for (DynamicMappingFor dynamicMappingFor : groupDescription.getContainer().getDynamicMappings()) {
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

                containerDesc.getDynamicMappings().add(eefDynamicMappingFor);
            }
            group.setContainer(containerDesc);
        }
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

    private EEFTextDescription createEEFTextDescription(TextDescription textDescription) {
        EEFTextDescription eefTextDescription = EefFactory.eINSTANCE.createEEFTextDescription();

        eefTextDescription.setIdentifier(textDescription.getIdentifier());
        eefTextDescription.setValueExpression(textDescription.getValueExpression());

        InitialOperation initialOperation = textDescription.getInitialOperation();
        eefTextDescription.setEditExpression(getExpressionForOperation(initialOperation));
        return eefTextDescription;
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
        return eefTextDescription;
    }

    private EEFLabelDescription createEEFLabelDescription(LabelDescription labelDescription) {
        EEFLabelDescription eefLabelDescription = EefFactory.eINSTANCE.createEEFLabelDescription();

        eefLabelDescription.setIdentifier(labelDescription.getIdentifier());
        eefLabelDescription.setBodyExpression(labelDescription.getBodyExpression());
        return eefLabelDescription;
    }

    private EEFButtonDescription createEEFButtonDescription(ButtonDescription buttonDescription) {
        EEFButtonDescription eefButtonDescription = EefFactory.eINSTANCE.createEEFButtonDescription();

        eefButtonDescription.setIdentifier(buttonDescription.getIdentifier());
        eefButtonDescription.setButtonLabelExpression(buttonDescription.getButtonLabelExpression());
        InitialOperation initialOperation = buttonDescription.getInitialOperation();
        eefButtonDescription.setPushExpression(getExpressionForOperation(initialOperation));
        return eefButtonDescription;
    }

    private EEFCheckboxDescription createEEFCheckboxDescription(CheckboxDescription checkboxDescription) {
        EEFCheckboxDescription eefCheckboxDescription = EefFactory.eINSTANCE.createEEFCheckboxDescription();

        eefCheckboxDescription.setIdentifier(checkboxDescription.getIdentifier());
        eefCheckboxDescription.setValueExpression(checkboxDescription.getValueExpression());
        return eefCheckboxDescription;
    }

    private EEFSelectDescription createEEFSelectDescription(SelectDescription selectDescription) {
        EEFSelectDescription eefSelectDescription = EefFactory.eINSTANCE.createEEFSelectDescription();

        eefSelectDescription.setIdentifier(selectDescription.getIdentifier());
        eefSelectDescription.setValueExpression(selectDescription.getValueExpression());
        eefSelectDescription.setCandidatesExpression(selectDescription.getCandidatesExpression());
        eefSelectDescription.setCandidateDisplayExpression(selectDescription.getCandidateDisplayExpression());
        return eefSelectDescription;
    }

    private EEFRadioDescription createEEFRadioDescription(RadioDescription radioDescription) {
        EEFRadioDescription eefRadioDescription = EefFactory.eINSTANCE.createEEFRadioDescription();

        eefRadioDescription.setIdentifier(radioDescription.getIdentifier());
        eefRadioDescription.setValueExpression(radioDescription.getValueExpression());
        eefRadioDescription.setCandidatesExpression(radioDescription.getCandidatesExpression());
        eefRadioDescription.setCandidateDisplayExpression(radioDescription.getCandidateDisplayExpression());
        return eefRadioDescription;
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
        return eefCustomDescription;
    }


}
