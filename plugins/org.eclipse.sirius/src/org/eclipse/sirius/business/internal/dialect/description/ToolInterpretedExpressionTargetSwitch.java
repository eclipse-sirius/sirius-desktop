/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.dialect.description;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.PasteTargetDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable;
import org.eclipse.sirius.viewpoint.description.tool.Case;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter;
import org.eclipse.sirius.viewpoint.description.tool.For;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.util.ToolSwitch;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;

import com.google.common.collect.Sets;

/**
 * A switch that will return the Target Types associated to a given element
 * (part of the {@link ToolPackage}) and feature corresponding to an Interpreted
 * Expression. For example, for a NodeMapping :
 * <p>
 * <li>if the feature is semantic candidate expression, we return the domain
 * class of the first valid container (representation element mapping or
 * representation description).</li>
 * <li>if the feature is any other interpreted expression, we return the domain
 * class associated to this mapping</li>
 * </p>
 * 
 * Can return {@link Options#newNone()} if the given expression does not require
 * any target type (for example, a Popup menu contribution only uses variables
 * in its expressions).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ToolInterpretedExpressionTargetSwitch extends ToolSwitch<Option<Collection<String>>> {

    /**
     * Constant used in switches on feature id to consider the case when the
     * feature must not be considered.
     */
    private static final int DO_NOT_CONSIDER_FEATURE = -1;

    /**
     * The ID of the feature containing the Interpreted expression.
     */
    protected EStructuralFeature feature;

    /**
     * Indicates if the feature should be considered.
     */
    protected boolean considerFeature;

    private IInterpretedExpressionTargetSwitch globalSwitch;

    /**
     * Default constructor.
     * 
     * @param feature
     *            the feature containing the Interpreted expression
     * @param defaultInterpretedExpressionTargetSwitch
     *            the global switch to use
     */
    public ToolInterpretedExpressionTargetSwitch(EStructuralFeature feature, IInterpretedExpressionTargetSwitch defaultInterpretedExpressionTargetSwitch) {
        super();
        this.feature = feature;
        this.globalSwitch = defaultInterpretedExpressionTargetSwitch;
    }

    @Override
    public Option<Collection<String>> doSwitch(EObject theEObject) {
        Option<Collection<String>> doSwitch = super.doSwitch(theEObject);
        if (doSwitch != null) {
            return doSwitch;
        }
        Collection<String> targets = Collections.emptySet();
        return Options.newSome(targets);
    }

    @Override
    protected Option<Collection<String>> doSwitch(EClass theEClass, EObject theEObject) {
        Option<Collection<String>> result = null;

        if (theEClass.eContainer() == modelPackage) {
            result = doSwitch(theEClass.getClassifierID(), theEObject);
        } else {
            // CreateCellTool expends TableTool and AbstractToolDescription.
            // Here the expected super type is the second, so we go through
            // all super types to find the first non null result.
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            if (eSuperTypes.isEmpty()) {
                result = defaultCase(theEObject);
            } else {
                for (EClass eSuperType : eSuperTypes) {
                    result = doSwitch(eSuperType, theEObject);
                    if (result != null) {
                        break; // found: quit the for
                    }
                } // for
            }
        }

        return result;
    }

    /**
     * Changes the behavior of this switch : if true, then the feature will be
     * considered to calculate target types ; if false, then the feature will be
     * ignored.
     * 
     * @param considerFeature
     *            true if the feature should be considered, false otherwise
     */
    public void setConsiderFeature(boolean considerFeature) {
        this.considerFeature = considerFeature;
    }

    private int getFeatureId(EClass eClass) {
        int featureID = DO_NOT_CONSIDER_FEATURE;
        if (considerFeature && feature != null) {
            featureID = eClass.getFeatureID(feature);
        }
        return featureID;
    }

    /**
     * Returns the first element that changes the context of expressions. For
     * example : for a given operation, will return the first ChangeContext or
     * AbstractTool that contains it.
     * 
     * @param element
     *            the element to get the container from
     * @return the first relevant for the given EObject, i.e. the first
     *         container from which a domain class can be determined
     */
    protected EObject getFirstContextChangingContainer(EObject element) {
        EObject container = element.eContainer();
        while (!isChangingContextElement(container)) {
            container = container.eContainer();
        }
        return container;
    }

    private boolean isChangingContextElement(EObject element) {
        boolean descCanChange = element instanceof RepresentationDescription || element instanceof RepresentationElementMapping;
        boolean operationCanChange = element instanceof CreateInstance || element instanceof ChangeContext || element instanceof For;
        boolean toolCanChange = element instanceof AbstractToolDescription;
        boolean validationRulecanChange = element instanceof ValidationRule;
        return descCanChange || operationCanChange || toolCanChange || validationRulecanChange;
    }

    @Override
    public Option<Collection<String>> caseMappingBasedToolDescription(MappingBasedToolDescription tool) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getMappingBasedToolDescription())) {
        case ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION:
        case ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT:
        case DO_NOT_CONSIDER_FEATURE:
            // Default case for MappingBasedToolDescription, if subclasses or
            // dialects did not return a specific result.
            result = Options.newNone();
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseAbstractToolDescription(AbstractToolDescription tool) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getAbstractToolDescription())) {
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION:
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT:
        case DO_NOT_CONSIDER_FEATURE:
            // Default case for AbstractToolDescription, if subclasses or
            // dialects did not return a specific result.
            result = Options.newNone();
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseToolDescription(ToolDescription object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getToolDescription())) {
        case ToolPackage.TOOL_DESCRIPTION__PRECONDITION:
        case ToolPackage.TOOL_DESCRIPTION__ELEMENTS_TO_SELECT:
        case DO_NOT_CONSIDER_FEATURE:
            result = Options.newNone();
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseOperationAction(OperationAction object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getOperationAction())) {
        case ToolPackage.OPERATION_ACTION__PRECONDITION:
        case ToolPackage.OPERATION_ACTION__ELEMENTS_TO_SELECT:
        case DO_NOT_CONSIDER_FEATURE:
            result = Options.newNone();
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> casePasteDescription(PasteDescription object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getPasteDescription())) {
        case ToolPackage.PASTE_DESCRIPTION__PRECONDITION:
        case ToolPackage.PASTE_DESCRIPTION__ELEMENTS_TO_SELECT:
        case DO_NOT_CONSIDER_FEATURE:
            Collection<String> targets = Sets.newLinkedHashSet();
            for (PasteTargetDescription container : object.getContainers()) {
                Option<Collection<String>> targetsFromMapping = globalSwitch.doSwitch(container, false);
                if (targetsFromMapping.some()) {
                    targets.addAll(targetsFromMapping.get());
                }
            }
            result = Options.newSome(targets);
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseAcceleoVariable(AcceleoVariable object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getAcceleoVariable())) {
        case ToolPackage.ACCELEO_VARIABLE__COMPUTATION_EXPRESSION:
        case DO_NOT_CONSIDER_FEATURE:
            result = globalSwitch.doSwitch(getFirstContextChangingContainer(object), false);
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseSelectModelElementVariable(SelectModelElementVariable object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getSelectModelElementVariable())) {
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__CANDIDATES_EXPRESSION:
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__CHILDREN_EXPRESSION:
        case ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE__ROOT_EXPRESSION:
        case DO_NOT_CONSIDER_FEATURE:
            result = globalSwitch.doSwitch(getFirstContextChangingContainer(object), false);
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> casePaneBasedSelectionWizardDescription(PaneBasedSelectionWizardDescription toolDescription) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription())) {
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CANDIDATES_EXPRESSION:
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHILDREN_EXPRESSION:
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ROOT_EXPRESSION:
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__PRE_SELECTED_CANDIDATES_EXPRESSION:
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__PRECONDITION:
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENTS_TO_SELECT:
        case DO_NOT_CONSIDER_FEATURE:
            result = Options.newNone();
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseSelectionWizardDescription(SelectionWizardDescription toolDescription) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getSelectionWizardDescription())) {
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__CANDIDATES_EXPRESSION:
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__CHILDREN_EXPRESSION:
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__ROOT_EXPRESSION:
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__PRECONDITION:
        case ToolPackage.SELECTION_WIZARD_DESCRIPTION__ELEMENTS_TO_SELECT:
        case DO_NOT_CONSIDER_FEATURE:
            result = Options.newNone();
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> casePopupMenu(PopupMenu object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getPopupMenu())) {
        case ToolPackage.POPUP_MENU__PRECONDITION:
        case ToolPackage.POPUP_MENU__ELEMENTS_TO_SELECT:
        case DO_NOT_CONSIDER_FEATURE:
            result = Options.newNone();
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseExternalJavaAction(ExternalJavaAction object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getExternalJavaAction())) {
        case ToolPackage.EXTERNAL_JAVA_ACTION__PRECONDITION:
        case ToolPackage.EXTERNAL_JAVA_ACTION__ELEMENTS_TO_SELECT:
        case DO_NOT_CONSIDER_FEATURE:
            result = Options.newNone();
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseExternalJavaActionCall(ExternalJavaActionCall object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getExternalJavaActionCall())) {
        case ToolPackage.EXTERNAL_JAVA_ACTION_CALL__PRECONDITION:
        case ToolPackage.EXTERNAL_JAVA_ACTION_CALL__ELEMENTS_TO_SELECT:
        case DO_NOT_CONSIDER_FEATURE:
            result = Options.newNone();
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseExternalJavaActionParameter(ExternalJavaActionParameter object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getExternalJavaActionParameter())) {
        case ToolPackage.EXTERNAL_JAVA_ACTION_PARAMETER__VALUE:
        case DO_NOT_CONSIDER_FEATURE:
            result = Options.newNone();
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseRepresentationCreationDescription(RepresentationCreationDescription toolDescription) {
        Collection<String> targets = Sets.newLinkedHashSet();
        Option<Collection<String>> result = Options.newSome(targets);
        switch (getFeatureId(ToolPackage.eINSTANCE.getRepresentationCreationDescription())) {
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__TITLE_EXPRESSION:
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__BROWSE_EXPRESSION:
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__PRECONDITION:
        case ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT:
        case DO_NOT_CONSIDER_FEATURE:
            for (RepresentationElementMapping mapping : toolDescription.getMappings()) {
                IInterpretedExpressionQuery interpretedExpressionQuery = DialectManager.INSTANCE.createInterpretedExpressionQuery(mapping, null);
                Option<Collection<String>> mappingTypes = interpretedExpressionQuery.getTargetDomainClasses();
                if (mappingTypes.some()) {
                    targets.addAll(mappingTypes.get());
                }
            }
            result = Options.newSome(targets);
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseRepresentationNavigationDescription(RepresentationNavigationDescription toolDescription) {
        Collection<String> targets = Sets.newLinkedHashSet();
        Option<Collection<String>> result = Options.newSome(targets);
        switch (getFeatureId(ToolPackage.eINSTANCE.getRepresentationNavigationDescription())) {
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION:
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION:
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__PRECONDITION:
        case ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__ELEMENTS_TO_SELECT:
        case DO_NOT_CONSIDER_FEATURE:
            for (RepresentationElementMapping mapping : toolDescription.getMappings()) {
                IInterpretedExpressionQuery interpretedExpressionQuery = DialectManager.INSTANCE.createInterpretedExpressionQuery(mapping, null);
                Option<Collection<String>> mappingTypes = interpretedExpressionQuery.getTargetDomainClasses();
                if (mappingTypes.some()) {
                    targets.addAll(mappingTypes.get());
                }
            }
            result = Options.newSome(targets);
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseModelOperation(ModelOperation object) {
        // Default behavior for model operations : returning the first context
        // changing parent Model operation or the containing Tool
        return globalSwitch.doSwitch(getFirstContextChangingContainer(object), false);
    }

    @Override
    public Option<Collection<String>> caseCase(Case object) {
        // Default behavior for cases : returning the first context
        // changing parent Model operation or the containing Tool
        return globalSwitch.doSwitch(getFirstContextChangingContainer(object), false);
    }

    @Override
    public Option<Collection<String>> caseToolFilterDescription(ToolFilterDescription object) {
        // Default behavior for tool filters : returning the first context
        // changing parent Model operation or the containing Tool
        return globalSwitch.doSwitch(getFirstContextChangingContainer(object), false);
    }

    @Override
    public Option<Collection<String>> caseChangeContext(ChangeContext object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(object.eClass())) {
        case ToolPackage.CHANGE_CONTEXT__BROWSE_EXPRESSION:
            return globalSwitch.doSwitch(getFirstContextChangingContainer(object), false);
        case DO_NOT_CONSIDER_FEATURE:
            // Compile expression and ask for return type.
            // EObject other wise.
            result = Options.newNone();
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseFor(For object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(object.eClass())) {
        case ToolPackage.FOR__EXPRESSION:
            return globalSwitch.doSwitch(getFirstContextChangingContainer(object), false);
        case DO_NOT_CONSIDER_FEATURE:
            // Compile expression and ask for return type.
            // EObject other wise.
            result = Options.newNone();
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseCreateInstance(CreateInstance object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(object.eClass())) {
        case DO_NOT_CONSIDER_FEATURE:
            Collection<String> target = Collections.singleton(object.getTypeName());
            result = Options.newSome(target);
            break;
        default:
            break;
        }
        return result;
    }
}
