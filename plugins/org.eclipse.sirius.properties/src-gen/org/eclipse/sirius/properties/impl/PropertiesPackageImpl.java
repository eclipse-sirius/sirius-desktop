/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
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
import org.eclipse.sirius.properties.EditSupport;
import org.eclipse.sirius.properties.FillLayoutDescription;
import org.eclipse.sirius.properties.GridLayoutDescription;
import org.eclipse.sirius.properties.GroupConditionalStyle;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.GroupStyle;
import org.eclipse.sirius.properties.GroupValidationSetDescription;
import org.eclipse.sirius.properties.HyperlinkDescription;
import org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle;
import org.eclipse.sirius.properties.HyperlinkWidgetStyle;
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.LabelWidgetConditionalStyle;
import org.eclipse.sirius.properties.LabelWidgetStyle;
import org.eclipse.sirius.properties.LayoutDescription;
import org.eclipse.sirius.properties.ListDescription;
import org.eclipse.sirius.properties.ListWidgetConditionalStyle;
import org.eclipse.sirius.properties.ListWidgetStyle;
import org.eclipse.sirius.properties.OperationDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PageValidationSetDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.PropertyValidationRule;
import org.eclipse.sirius.properties.RadioDescription;
import org.eclipse.sirius.properties.RadioWidgetConditionalStyle;
import org.eclipse.sirius.properties.RadioWidgetStyle;
import org.eclipse.sirius.properties.SelectDescription;
import org.eclipse.sirius.properties.SelectWidgetConditionalStyle;
import org.eclipse.sirius.properties.SelectWidgetStyle;
import org.eclipse.sirius.properties.TextAreaDescription;
import org.eclipse.sirius.properties.TextDescription;
import org.eclipse.sirius.properties.TextWidgetConditionalStyle;
import org.eclipse.sirius.properties.TextWidgetStyle;
import org.eclipse.sirius.properties.TitleBarStyle;
import org.eclipse.sirius.properties.ToggleStyle;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.WidgetAction;
import org.eclipse.sirius.properties.WidgetConditionalStyle;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.properties.WidgetStyle;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 *
 * @generated
 */
public class PropertiesPackageImpl extends EPackageImpl implements PropertiesPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass viewExtensionDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass pageDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass pageValidationSetDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass propertyValidationRuleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass groupDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass groupValidationSetDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass controlDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass containerDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass layoutDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass fillLayoutDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass gridLayoutDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass widgetDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass textDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass buttonDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass labelDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass checkboxDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass selectDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dynamicMappingForEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dynamicMappingIfEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass textAreaDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass radioDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass listDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass operationDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass customDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass customExpressionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass customOperationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass hyperlinkDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass widgetStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass textWidgetStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass labelWidgetStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass checkboxWidgetStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass radioWidgetStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass buttonWidgetStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass selectWidgetStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass customWidgetStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass widgetConditionalStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass textWidgetConditionalStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass labelWidgetConditionalStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass checkboxWidgetConditionalStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass radioWidgetConditionalStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass buttonWidgetConditionalStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass selectWidgetConditionalStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass customWidgetConditionalStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass listWidgetConditionalStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass widgetActionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass hyperlinkWidgetConditionalStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass groupConditionalStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass editSupportEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass listWidgetStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass hyperlinkWidgetStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass groupStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum filL_LAYOUT_ORIENTATIONEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum toggleStyleEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum titleBarStyleEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
     * package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory
     * method {@link #init init()}, which also performs initialization of the
     * package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.properties.PropertiesPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private PropertiesPackageImpl() {
        super(PropertiesPackage.eNS_URI, PropertiesFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model,
     * and for any others upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link PropertiesPackage#eINSTANCE}
     * when that field is accessed. Clients should not invoke it directly.
     * Instead, they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static PropertiesPackage init() {
        if (PropertiesPackageImpl.isInited) {
            return (PropertiesPackage) EPackage.Registry.INSTANCE.getEPackage(PropertiesPackage.eNS_URI);
        }

        // Obtain or create and register package
        PropertiesPackageImpl thePropertiesPackage = (PropertiesPackageImpl) (EPackage.Registry.INSTANCE.get(PropertiesPackage.eNS_URI) instanceof PropertiesPackageImpl
                ? EPackage.Registry.INSTANCE.get(PropertiesPackage.eNS_URI) : new PropertiesPackageImpl());

        PropertiesPackageImpl.isInited = true;

        // Initialize simple dependencies
        ViewpointPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        thePropertiesPackage.createPackageContents();

        // Initialize created meta-data
        thePropertiesPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        thePropertiesPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(PropertiesPackage.eNS_URI, thePropertiesPackage);
        return thePropertiesPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getViewExtensionDescription() {
        return viewExtensionDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getViewExtensionDescription_Identifier() {
        return (EAttribute) viewExtensionDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getViewExtensionDescription_Metamodels() {
        return (EReference) viewExtensionDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getViewExtensionDescription_Pages() {
        return (EReference) viewExtensionDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getViewExtensionDescription_Groups() {
        return (EReference) viewExtensionDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getPageDescription() {
        return pageDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getPageDescription_Identifier() {
        return (EAttribute) pageDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getPageDescription_LabelExpression() {
        return (EAttribute) pageDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getPageDescription_DomainClass() {
        return (EAttribute) pageDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getPageDescription_SemanticCandidateExpression() {
        return (EAttribute) pageDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getPageDescription_PreconditionExpression() {
        return (EAttribute) pageDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getPageDescription_Groups() {
        return (EReference) pageDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getPageDescription_ValidationSet() {
        return (EReference) pageDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getPageValidationSetDescription() {
        return pageValidationSetDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getPageValidationSetDescription_SemanticValidationRules() {
        return (EReference) pageValidationSetDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getPropertyValidationRule() {
        return propertyValidationRuleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getPropertyValidationRule_Targets() {
        return (EReference) propertyValidationRuleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getGroupDescription() {
        return groupDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGroupDescription_Identifier() {
        return (EAttribute) groupDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGroupDescription_LabelExpression() {
        return (EAttribute) groupDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGroupDescription_DomainClass() {
        return (EAttribute) groupDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGroupDescription_SemanticCandidateExpression() {
        return (EAttribute) groupDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGroupDescription_PreconditionExpression() {
        return (EAttribute) groupDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGroupDescription_Controls() {
        return (EReference) groupDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGroupDescription_ValidationSet() {
        return (EReference) groupDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGroupDescription_Style() {
        return (EReference) groupDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGroupDescription_ConditionalStyles() {
        return (EReference) groupDescriptionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getGroupValidationSetDescription() {
        return groupValidationSetDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGroupValidationSetDescription_SemanticValidationRules() {
        return (EReference) groupValidationSetDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGroupValidationSetDescription_PropertyValidationRules() {
        return (EReference) groupValidationSetDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getControlDescription() {
        return controlDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getControlDescription_Identifier() {
        return (EAttribute) controlDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getContainerDescription() {
        return containerDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerDescription_Controls() {
        return (EReference) containerDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerDescription_Layout() {
        return (EReference) containerDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getLayoutDescription() {
        return layoutDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getFillLayoutDescription() {
        return fillLayoutDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getFillLayoutDescription_Orientation() {
        return (EAttribute) fillLayoutDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getGridLayoutDescription() {
        return gridLayoutDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGridLayoutDescription_NumberOfColumns() {
        return (EAttribute) gridLayoutDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGridLayoutDescription_MakeColumnsWithEqualWidth() {
        return (EAttribute) gridLayoutDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getWidgetDescription() {
        return widgetDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getWidgetDescription_LabelExpression() {
        return (EAttribute) widgetDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getWidgetDescription_HelpExpression() {
        return (EAttribute) widgetDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getWidgetDescription_IsEnabledExpression() {
        return (EAttribute) widgetDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getTextDescription() {
        return textDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTextDescription_ValueExpression() {
        return (EAttribute) textDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getTextDescription_InitialOperation() {
        return (EReference) textDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getTextDescription_Style() {
        return (EReference) textDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getTextDescription_ConditionalStyles() {
        return (EReference) textDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getButtonDescription() {
        return buttonDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getButtonDescription_ButtonLabelExpression() {
        return (EAttribute) buttonDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getButtonDescription_InitialOperation() {
        return (EReference) buttonDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getButtonDescription_Style() {
        return (EReference) buttonDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getButtonDescription_ConditionalStyles() {
        return (EReference) buttonDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getLabelDescription() {
        return labelDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLabelDescription_ValueExpression() {
        return (EAttribute) labelDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLabelDescription_DisplayExpression() {
        return (EAttribute) labelDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLabelDescription_Style() {
        return (EReference) labelDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLabelDescription_ConditionalStyles() {
        return (EReference) labelDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLabelDescription_Actions() {
        return (EReference) labelDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCheckboxDescription() {
        return checkboxDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCheckboxDescription_ValueExpression() {
        return (EAttribute) checkboxDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCheckboxDescription_InitialOperation() {
        return (EReference) checkboxDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCheckboxDescription_Style() {
        return (EReference) checkboxDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCheckboxDescription_ConditionalStyles() {
        return (EReference) checkboxDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSelectDescription() {
        return selectDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSelectDescription_ValueExpression() {
        return (EAttribute) selectDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSelectDescription_InitialOperation() {
        return (EReference) selectDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSelectDescription_CandidatesExpression() {
        return (EAttribute) selectDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSelectDescription_CandidateDisplayExpression() {
        return (EAttribute) selectDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSelectDescription_Style() {
        return (EReference) selectDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSelectDescription_ConditionalStyles() {
        return (EReference) selectDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDynamicMappingFor() {
        return dynamicMappingForEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDynamicMappingFor_Iterator() {
        return (EAttribute) dynamicMappingForEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDynamicMappingFor_IterableExpression() {
        return (EAttribute) dynamicMappingForEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDynamicMappingFor_Ifs() {
        return (EReference) dynamicMappingForEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDynamicMappingIf() {
        return dynamicMappingIfEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDynamicMappingIf_PredicateExpression() {
        return (EAttribute) dynamicMappingIfEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDynamicMappingIf_Widget() {
        return (EReference) dynamicMappingIfEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getTextAreaDescription() {
        return textAreaDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTextAreaDescription_LineCount() {
        return (EAttribute) textAreaDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getRadioDescription() {
        return radioDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRadioDescription_ValueExpression() {
        return (EAttribute) radioDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getRadioDescription_InitialOperation() {
        return (EReference) radioDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRadioDescription_CandidatesExpression() {
        return (EAttribute) radioDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRadioDescription_CandidateDisplayExpression() {
        return (EAttribute) radioDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getRadioDescription_Style() {
        return (EReference) radioDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRadioDescription_NumberOfColumns() {
        return (EAttribute) radioDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getRadioDescription_ConditionalStyles() {
        return (EReference) radioDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getListDescription() {
        return listDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getListDescription_ValueExpression() {
        return (EAttribute) listDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getListDescription_DisplayExpression() {
        return (EAttribute) listDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getListDescription_OnClickOperation() {
        return (EReference) listDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getListDescription_Actions() {
        return (EReference) listDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getListDescription_Style() {
        return (EReference) listDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getListDescription_ConditionalStyles() {
        return (EReference) listDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getOperationDescription() {
        return operationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getOperationDescription_InitialOperation() {
        return (EReference) operationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCustomDescription() {
        return customDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCustomDescription_CustomExpressions() {
        return (EReference) customDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCustomDescription_CustomOperations() {
        return (EReference) customDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCustomDescription_Style() {
        return (EReference) customDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCustomDescription_ConditionalStyles() {
        return (EReference) customDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCustomExpression() {
        return customExpressionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCustomExpression_Identifier() {
        return (EAttribute) customExpressionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCustomExpression_CustomExpression() {
        return (EAttribute) customExpressionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCustomOperation() {
        return customOperationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCustomOperation_Identifier() {
        return (EAttribute) customOperationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCustomOperation_InitialOperation() {
        return (EReference) customOperationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getHyperlinkDescription() {
        return hyperlinkDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getHyperlinkDescription_ValueExpression() {
        return (EAttribute) hyperlinkDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getHyperlinkDescription_DisplayExpression() {
        return (EAttribute) hyperlinkDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getHyperlinkDescription_InitialOperation() {
        return (EReference) hyperlinkDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getHyperlinkDescription_Style() {
        return (EReference) hyperlinkDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getHyperlinkDescription_ConditionalStyles() {
        return (EReference) hyperlinkDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getHyperlinkDescription_Actions() {
        return (EReference) hyperlinkDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getWidgetStyle() {
        return widgetStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getWidgetStyle_LabelFontNameExpression() {
        return (EAttribute) widgetStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getWidgetStyle_LabelFontSizeExpression() {
        return (EAttribute) widgetStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getWidgetStyle_LabelBackgroundColor() {
        return (EReference) widgetStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getWidgetStyle_LabelForegroundColor() {
        return (EReference) widgetStyleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getWidgetStyle_LabelFontFormat() {
        return (EAttribute) widgetStyleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getTextWidgetStyle() {
        return textWidgetStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTextWidgetStyle_FontNameExpression() {
        return (EAttribute) textWidgetStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTextWidgetStyle_FontSizeExpression() {
        return (EAttribute) textWidgetStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getTextWidgetStyle_BackgroundColor() {
        return (EReference) textWidgetStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getTextWidgetStyle_ForegroundColor() {
        return (EReference) textWidgetStyleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTextWidgetStyle_FontFormat() {
        return (EAttribute) textWidgetStyleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getLabelWidgetStyle() {
        return labelWidgetStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLabelWidgetStyle_FontNameExpression() {
        return (EAttribute) labelWidgetStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLabelWidgetStyle_FontSizeExpression() {
        return (EAttribute) labelWidgetStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLabelWidgetStyle_BackgroundColor() {
        return (EReference) labelWidgetStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLabelWidgetStyle_ForegroundColor() {
        return (EReference) labelWidgetStyleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLabelWidgetStyle_FontFormat() {
        return (EAttribute) labelWidgetStyleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCheckboxWidgetStyle() {
        return checkboxWidgetStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getRadioWidgetStyle() {
        return radioWidgetStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getButtonWidgetStyle() {
        return buttonWidgetStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSelectWidgetStyle() {
        return selectWidgetStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCustomWidgetStyle() {
        return customWidgetStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getWidgetConditionalStyle() {
        return widgetConditionalStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getWidgetConditionalStyle_PreconditionExpression() {
        return (EAttribute) widgetConditionalStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getTextWidgetConditionalStyle() {
        return textWidgetConditionalStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getTextWidgetConditionalStyle_Style() {
        return (EReference) textWidgetConditionalStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getLabelWidgetConditionalStyle() {
        return labelWidgetConditionalStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLabelWidgetConditionalStyle_Style() {
        return (EReference) labelWidgetConditionalStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCheckboxWidgetConditionalStyle() {
        return checkboxWidgetConditionalStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCheckboxWidgetConditionalStyle_Style() {
        return (EReference) checkboxWidgetConditionalStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getRadioWidgetConditionalStyle() {
        return radioWidgetConditionalStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getRadioWidgetConditionalStyle_Style() {
        return (EReference) radioWidgetConditionalStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getButtonWidgetConditionalStyle() {
        return buttonWidgetConditionalStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getButtonWidgetConditionalStyle_Style() {
        return (EReference) buttonWidgetConditionalStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSelectWidgetConditionalStyle() {
        return selectWidgetConditionalStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSelectWidgetConditionalStyle_Style() {
        return (EReference) selectWidgetConditionalStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCustomWidgetConditionalStyle() {
        return customWidgetConditionalStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCustomWidgetConditionalStyle_Style() {
        return (EReference) customWidgetConditionalStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getListWidgetConditionalStyle() {
        return listWidgetConditionalStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getListWidgetConditionalStyle_Style() {
        return (EReference) listWidgetConditionalStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getWidgetAction() {
        return widgetActionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getWidgetAction_LabelExpression() {
        return (EAttribute) widgetActionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getWidgetAction_InitialOperation() {
        return (EReference) widgetActionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getHyperlinkWidgetConditionalStyle() {
        return hyperlinkWidgetConditionalStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getHyperlinkWidgetConditionalStyle_Style() {
        return (EReference) hyperlinkWidgetConditionalStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getGroupConditionalStyle() {
        return groupConditionalStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGroupConditionalStyle_Style() {
        return (EReference) groupConditionalStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEditSupport() {
        return editSupportEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getListWidgetStyle() {
        return listWidgetStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getHyperlinkWidgetStyle() {
        return hyperlinkWidgetStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getHyperlinkWidgetStyle_FontNameExpression() {
        return (EAttribute) hyperlinkWidgetStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getHyperlinkWidgetStyle_FontSizeExpression() {
        return (EAttribute) hyperlinkWidgetStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getHyperlinkWidgetStyle_BackgroundColor() {
        return (EReference) hyperlinkWidgetStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getHyperlinkWidgetStyle_FontFormat() {
        return (EAttribute) hyperlinkWidgetStyleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getGroupStyle() {
        return groupStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGroupStyle_BackgroundColor() {
        return (EReference) groupStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGroupStyle_ForegroundColor() {
        return (EReference) groupStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGroupStyle_FontNameExpression() {
        return (EAttribute) groupStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGroupStyle_FontSizeExpression() {
        return (EAttribute) groupStyleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGroupStyle_BarStyle() {
        return (EAttribute) groupStyleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGroupStyle_ToggleStyle() {
        return (EAttribute) groupStyleEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGroupStyle_ExpandedByDefault() {
        return (EAttribute) groupStyleEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getFILL_LAYOUT_ORIENTATION() {
        return filL_LAYOUT_ORIENTATIONEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getToggleStyle() {
        return toggleStyleEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getTitleBarStyle() {
        return titleBarStyleEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PropertiesFactory getPropertiesFactory() {
        return (PropertiesFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to
     * have no affect on any invocation but its first. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) {
            return;
        }
        isCreated = true;

        // Create classes and their features
        viewExtensionDescriptionEClass = createEClass(PropertiesPackage.VIEW_EXTENSION_DESCRIPTION);
        createEAttribute(viewExtensionDescriptionEClass, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__IDENTIFIER);
        createEReference(viewExtensionDescriptionEClass, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__METAMODELS);
        createEReference(viewExtensionDescriptionEClass, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__PAGES);
        createEReference(viewExtensionDescriptionEClass, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__GROUPS);

        pageDescriptionEClass = createEClass(PropertiesPackage.PAGE_DESCRIPTION);
        createEAttribute(pageDescriptionEClass, PropertiesPackage.PAGE_DESCRIPTION__IDENTIFIER);
        createEAttribute(pageDescriptionEClass, PropertiesPackage.PAGE_DESCRIPTION__LABEL_EXPRESSION);
        createEAttribute(pageDescriptionEClass, PropertiesPackage.PAGE_DESCRIPTION__DOMAIN_CLASS);
        createEAttribute(pageDescriptionEClass, PropertiesPackage.PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION);
        createEAttribute(pageDescriptionEClass, PropertiesPackage.PAGE_DESCRIPTION__PRECONDITION_EXPRESSION);
        createEReference(pageDescriptionEClass, PropertiesPackage.PAGE_DESCRIPTION__GROUPS);
        createEReference(pageDescriptionEClass, PropertiesPackage.PAGE_DESCRIPTION__VALIDATION_SET);

        pageValidationSetDescriptionEClass = createEClass(PropertiesPackage.PAGE_VALIDATION_SET_DESCRIPTION);
        createEReference(pageValidationSetDescriptionEClass, PropertiesPackage.PAGE_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES);

        propertyValidationRuleEClass = createEClass(PropertiesPackage.PROPERTY_VALIDATION_RULE);
        createEReference(propertyValidationRuleEClass, PropertiesPackage.PROPERTY_VALIDATION_RULE__TARGETS);

        groupDescriptionEClass = createEClass(PropertiesPackage.GROUP_DESCRIPTION);
        createEAttribute(groupDescriptionEClass, PropertiesPackage.GROUP_DESCRIPTION__IDENTIFIER);
        createEAttribute(groupDescriptionEClass, PropertiesPackage.GROUP_DESCRIPTION__LABEL_EXPRESSION);
        createEAttribute(groupDescriptionEClass, PropertiesPackage.GROUP_DESCRIPTION__DOMAIN_CLASS);
        createEAttribute(groupDescriptionEClass, PropertiesPackage.GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION);
        createEAttribute(groupDescriptionEClass, PropertiesPackage.GROUP_DESCRIPTION__PRECONDITION_EXPRESSION);
        createEReference(groupDescriptionEClass, PropertiesPackage.GROUP_DESCRIPTION__CONTROLS);
        createEReference(groupDescriptionEClass, PropertiesPackage.GROUP_DESCRIPTION__VALIDATION_SET);
        createEReference(groupDescriptionEClass, PropertiesPackage.GROUP_DESCRIPTION__STYLE);
        createEReference(groupDescriptionEClass, PropertiesPackage.GROUP_DESCRIPTION__CONDITIONAL_STYLES);

        groupValidationSetDescriptionEClass = createEClass(PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION);
        createEReference(groupValidationSetDescriptionEClass, PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES);
        createEReference(groupValidationSetDescriptionEClass, PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION__PROPERTY_VALIDATION_RULES);

        controlDescriptionEClass = createEClass(PropertiesPackage.CONTROL_DESCRIPTION);
        createEAttribute(controlDescriptionEClass, PropertiesPackage.CONTROL_DESCRIPTION__IDENTIFIER);

        containerDescriptionEClass = createEClass(PropertiesPackage.CONTAINER_DESCRIPTION);
        createEReference(containerDescriptionEClass, PropertiesPackage.CONTAINER_DESCRIPTION__CONTROLS);
        createEReference(containerDescriptionEClass, PropertiesPackage.CONTAINER_DESCRIPTION__LAYOUT);

        layoutDescriptionEClass = createEClass(PropertiesPackage.LAYOUT_DESCRIPTION);

        fillLayoutDescriptionEClass = createEClass(PropertiesPackage.FILL_LAYOUT_DESCRIPTION);
        createEAttribute(fillLayoutDescriptionEClass, PropertiesPackage.FILL_LAYOUT_DESCRIPTION__ORIENTATION);

        gridLayoutDescriptionEClass = createEClass(PropertiesPackage.GRID_LAYOUT_DESCRIPTION);
        createEAttribute(gridLayoutDescriptionEClass, PropertiesPackage.GRID_LAYOUT_DESCRIPTION__NUMBER_OF_COLUMNS);
        createEAttribute(gridLayoutDescriptionEClass, PropertiesPackage.GRID_LAYOUT_DESCRIPTION__MAKE_COLUMNS_WITH_EQUAL_WIDTH);

        widgetDescriptionEClass = createEClass(PropertiesPackage.WIDGET_DESCRIPTION);
        createEAttribute(widgetDescriptionEClass, PropertiesPackage.WIDGET_DESCRIPTION__LABEL_EXPRESSION);
        createEAttribute(widgetDescriptionEClass, PropertiesPackage.WIDGET_DESCRIPTION__HELP_EXPRESSION);
        createEAttribute(widgetDescriptionEClass, PropertiesPackage.WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION);

        textDescriptionEClass = createEClass(PropertiesPackage.TEXT_DESCRIPTION);
        createEAttribute(textDescriptionEClass, PropertiesPackage.TEXT_DESCRIPTION__VALUE_EXPRESSION);
        createEReference(textDescriptionEClass, PropertiesPackage.TEXT_DESCRIPTION__INITIAL_OPERATION);
        createEReference(textDescriptionEClass, PropertiesPackage.TEXT_DESCRIPTION__STYLE);
        createEReference(textDescriptionEClass, PropertiesPackage.TEXT_DESCRIPTION__CONDITIONAL_STYLES);

        buttonDescriptionEClass = createEClass(PropertiesPackage.BUTTON_DESCRIPTION);
        createEAttribute(buttonDescriptionEClass, PropertiesPackage.BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION);
        createEReference(buttonDescriptionEClass, PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION);
        createEReference(buttonDescriptionEClass, PropertiesPackage.BUTTON_DESCRIPTION__STYLE);
        createEReference(buttonDescriptionEClass, PropertiesPackage.BUTTON_DESCRIPTION__CONDITIONAL_STYLES);

        labelDescriptionEClass = createEClass(PropertiesPackage.LABEL_DESCRIPTION);
        createEAttribute(labelDescriptionEClass, PropertiesPackage.LABEL_DESCRIPTION__VALUE_EXPRESSION);
        createEAttribute(labelDescriptionEClass, PropertiesPackage.LABEL_DESCRIPTION__DISPLAY_EXPRESSION);
        createEReference(labelDescriptionEClass, PropertiesPackage.LABEL_DESCRIPTION__STYLE);
        createEReference(labelDescriptionEClass, PropertiesPackage.LABEL_DESCRIPTION__CONDITIONAL_STYLES);
        createEReference(labelDescriptionEClass, PropertiesPackage.LABEL_DESCRIPTION__ACTIONS);

        checkboxDescriptionEClass = createEClass(PropertiesPackage.CHECKBOX_DESCRIPTION);
        createEAttribute(checkboxDescriptionEClass, PropertiesPackage.CHECKBOX_DESCRIPTION__VALUE_EXPRESSION);
        createEReference(checkboxDescriptionEClass, PropertiesPackage.CHECKBOX_DESCRIPTION__INITIAL_OPERATION);
        createEReference(checkboxDescriptionEClass, PropertiesPackage.CHECKBOX_DESCRIPTION__STYLE);
        createEReference(checkboxDescriptionEClass, PropertiesPackage.CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES);

        selectDescriptionEClass = createEClass(PropertiesPackage.SELECT_DESCRIPTION);
        createEAttribute(selectDescriptionEClass, PropertiesPackage.SELECT_DESCRIPTION__VALUE_EXPRESSION);
        createEReference(selectDescriptionEClass, PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(selectDescriptionEClass, PropertiesPackage.SELECT_DESCRIPTION__CANDIDATES_EXPRESSION);
        createEAttribute(selectDescriptionEClass, PropertiesPackage.SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION);
        createEReference(selectDescriptionEClass, PropertiesPackage.SELECT_DESCRIPTION__STYLE);
        createEReference(selectDescriptionEClass, PropertiesPackage.SELECT_DESCRIPTION__CONDITIONAL_STYLES);

        dynamicMappingForEClass = createEClass(PropertiesPackage.DYNAMIC_MAPPING_FOR);
        createEAttribute(dynamicMappingForEClass, PropertiesPackage.DYNAMIC_MAPPING_FOR__ITERATOR);
        createEAttribute(dynamicMappingForEClass, PropertiesPackage.DYNAMIC_MAPPING_FOR__ITERABLE_EXPRESSION);
        createEReference(dynamicMappingForEClass, PropertiesPackage.DYNAMIC_MAPPING_FOR__IFS);

        dynamicMappingIfEClass = createEClass(PropertiesPackage.DYNAMIC_MAPPING_IF);
        createEAttribute(dynamicMappingIfEClass, PropertiesPackage.DYNAMIC_MAPPING_IF__PREDICATE_EXPRESSION);
        createEReference(dynamicMappingIfEClass, PropertiesPackage.DYNAMIC_MAPPING_IF__WIDGET);

        textAreaDescriptionEClass = createEClass(PropertiesPackage.TEXT_AREA_DESCRIPTION);
        createEAttribute(textAreaDescriptionEClass, PropertiesPackage.TEXT_AREA_DESCRIPTION__LINE_COUNT);

        radioDescriptionEClass = createEClass(PropertiesPackage.RADIO_DESCRIPTION);
        createEAttribute(radioDescriptionEClass, PropertiesPackage.RADIO_DESCRIPTION__VALUE_EXPRESSION);
        createEReference(radioDescriptionEClass, PropertiesPackage.RADIO_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(radioDescriptionEClass, PropertiesPackage.RADIO_DESCRIPTION__CANDIDATES_EXPRESSION);
        createEAttribute(radioDescriptionEClass, PropertiesPackage.RADIO_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION);
        createEReference(radioDescriptionEClass, PropertiesPackage.RADIO_DESCRIPTION__STYLE);
        createEAttribute(radioDescriptionEClass, PropertiesPackage.RADIO_DESCRIPTION__NUMBER_OF_COLUMNS);
        createEReference(radioDescriptionEClass, PropertiesPackage.RADIO_DESCRIPTION__CONDITIONAL_STYLES);

        listDescriptionEClass = createEClass(PropertiesPackage.LIST_DESCRIPTION);
        createEAttribute(listDescriptionEClass, PropertiesPackage.LIST_DESCRIPTION__VALUE_EXPRESSION);
        createEAttribute(listDescriptionEClass, PropertiesPackage.LIST_DESCRIPTION__DISPLAY_EXPRESSION);
        createEReference(listDescriptionEClass, PropertiesPackage.LIST_DESCRIPTION__ON_CLICK_OPERATION);
        createEReference(listDescriptionEClass, PropertiesPackage.LIST_DESCRIPTION__ACTIONS);
        createEReference(listDescriptionEClass, PropertiesPackage.LIST_DESCRIPTION__STYLE);
        createEReference(listDescriptionEClass, PropertiesPackage.LIST_DESCRIPTION__CONDITIONAL_STYLES);

        operationDescriptionEClass = createEClass(PropertiesPackage.OPERATION_DESCRIPTION);
        createEReference(operationDescriptionEClass, PropertiesPackage.OPERATION_DESCRIPTION__INITIAL_OPERATION);

        customDescriptionEClass = createEClass(PropertiesPackage.CUSTOM_DESCRIPTION);
        createEReference(customDescriptionEClass, PropertiesPackage.CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS);
        createEReference(customDescriptionEClass, PropertiesPackage.CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS);
        createEReference(customDescriptionEClass, PropertiesPackage.CUSTOM_DESCRIPTION__STYLE);
        createEReference(customDescriptionEClass, PropertiesPackage.CUSTOM_DESCRIPTION__CONDITIONAL_STYLES);

        customExpressionEClass = createEClass(PropertiesPackage.CUSTOM_EXPRESSION);
        createEAttribute(customExpressionEClass, PropertiesPackage.CUSTOM_EXPRESSION__IDENTIFIER);
        createEAttribute(customExpressionEClass, PropertiesPackage.CUSTOM_EXPRESSION__CUSTOM_EXPRESSION);

        customOperationEClass = createEClass(PropertiesPackage.CUSTOM_OPERATION);
        createEAttribute(customOperationEClass, PropertiesPackage.CUSTOM_OPERATION__IDENTIFIER);
        createEReference(customOperationEClass, PropertiesPackage.CUSTOM_OPERATION__INITIAL_OPERATION);

        hyperlinkDescriptionEClass = createEClass(PropertiesPackage.HYPERLINK_DESCRIPTION);
        createEAttribute(hyperlinkDescriptionEClass, PropertiesPackage.HYPERLINK_DESCRIPTION__VALUE_EXPRESSION);
        createEAttribute(hyperlinkDescriptionEClass, PropertiesPackage.HYPERLINK_DESCRIPTION__DISPLAY_EXPRESSION);
        createEReference(hyperlinkDescriptionEClass, PropertiesPackage.HYPERLINK_DESCRIPTION__INITIAL_OPERATION);
        createEReference(hyperlinkDescriptionEClass, PropertiesPackage.HYPERLINK_DESCRIPTION__STYLE);
        createEReference(hyperlinkDescriptionEClass, PropertiesPackage.HYPERLINK_DESCRIPTION__CONDITIONAL_STYLES);
        createEReference(hyperlinkDescriptionEClass, PropertiesPackage.HYPERLINK_DESCRIPTION__ACTIONS);

        widgetStyleEClass = createEClass(PropertiesPackage.WIDGET_STYLE);
        createEAttribute(widgetStyleEClass, PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION);
        createEAttribute(widgetStyleEClass, PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION);
        createEReference(widgetStyleEClass, PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR);
        createEReference(widgetStyleEClass, PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR);
        createEAttribute(widgetStyleEClass, PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT);

        textWidgetStyleEClass = createEClass(PropertiesPackage.TEXT_WIDGET_STYLE);
        createEAttribute(textWidgetStyleEClass, PropertiesPackage.TEXT_WIDGET_STYLE__FONT_NAME_EXPRESSION);
        createEAttribute(textWidgetStyleEClass, PropertiesPackage.TEXT_WIDGET_STYLE__FONT_SIZE_EXPRESSION);
        createEReference(textWidgetStyleEClass, PropertiesPackage.TEXT_WIDGET_STYLE__BACKGROUND_COLOR);
        createEReference(textWidgetStyleEClass, PropertiesPackage.TEXT_WIDGET_STYLE__FOREGROUND_COLOR);
        createEAttribute(textWidgetStyleEClass, PropertiesPackage.TEXT_WIDGET_STYLE__FONT_FORMAT);

        labelWidgetStyleEClass = createEClass(PropertiesPackage.LABEL_WIDGET_STYLE);
        createEAttribute(labelWidgetStyleEClass, PropertiesPackage.LABEL_WIDGET_STYLE__FONT_NAME_EXPRESSION);
        createEAttribute(labelWidgetStyleEClass, PropertiesPackage.LABEL_WIDGET_STYLE__FONT_SIZE_EXPRESSION);
        createEReference(labelWidgetStyleEClass, PropertiesPackage.LABEL_WIDGET_STYLE__BACKGROUND_COLOR);
        createEReference(labelWidgetStyleEClass, PropertiesPackage.LABEL_WIDGET_STYLE__FOREGROUND_COLOR);
        createEAttribute(labelWidgetStyleEClass, PropertiesPackage.LABEL_WIDGET_STYLE__FONT_FORMAT);

        checkboxWidgetStyleEClass = createEClass(PropertiesPackage.CHECKBOX_WIDGET_STYLE);

        radioWidgetStyleEClass = createEClass(PropertiesPackage.RADIO_WIDGET_STYLE);

        buttonWidgetStyleEClass = createEClass(PropertiesPackage.BUTTON_WIDGET_STYLE);

        selectWidgetStyleEClass = createEClass(PropertiesPackage.SELECT_WIDGET_STYLE);

        customWidgetStyleEClass = createEClass(PropertiesPackage.CUSTOM_WIDGET_STYLE);

        listWidgetStyleEClass = createEClass(PropertiesPackage.LIST_WIDGET_STYLE);

        hyperlinkWidgetStyleEClass = createEClass(PropertiesPackage.HYPERLINK_WIDGET_STYLE);
        createEAttribute(hyperlinkWidgetStyleEClass, PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_NAME_EXPRESSION);
        createEAttribute(hyperlinkWidgetStyleEClass, PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_SIZE_EXPRESSION);
        createEReference(hyperlinkWidgetStyleEClass, PropertiesPackage.HYPERLINK_WIDGET_STYLE__BACKGROUND_COLOR);
        createEAttribute(hyperlinkWidgetStyleEClass, PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_FORMAT);

        groupStyleEClass = createEClass(PropertiesPackage.GROUP_STYLE);
        createEReference(groupStyleEClass, PropertiesPackage.GROUP_STYLE__BACKGROUND_COLOR);
        createEReference(groupStyleEClass, PropertiesPackage.GROUP_STYLE__FOREGROUND_COLOR);
        createEAttribute(groupStyleEClass, PropertiesPackage.GROUP_STYLE__FONT_NAME_EXPRESSION);
        createEAttribute(groupStyleEClass, PropertiesPackage.GROUP_STYLE__FONT_SIZE_EXPRESSION);
        createEAttribute(groupStyleEClass, PropertiesPackage.GROUP_STYLE__BAR_STYLE);
        createEAttribute(groupStyleEClass, PropertiesPackage.GROUP_STYLE__TOGGLE_STYLE);
        createEAttribute(groupStyleEClass, PropertiesPackage.GROUP_STYLE__EXPANDED_BY_DEFAULT);

        widgetConditionalStyleEClass = createEClass(PropertiesPackage.WIDGET_CONDITIONAL_STYLE);
        createEAttribute(widgetConditionalStyleEClass, PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION);

        textWidgetConditionalStyleEClass = createEClass(PropertiesPackage.TEXT_WIDGET_CONDITIONAL_STYLE);
        createEReference(textWidgetConditionalStyleEClass, PropertiesPackage.TEXT_WIDGET_CONDITIONAL_STYLE__STYLE);

        labelWidgetConditionalStyleEClass = createEClass(PropertiesPackage.LABEL_WIDGET_CONDITIONAL_STYLE);
        createEReference(labelWidgetConditionalStyleEClass, PropertiesPackage.LABEL_WIDGET_CONDITIONAL_STYLE__STYLE);

        checkboxWidgetConditionalStyleEClass = createEClass(PropertiesPackage.CHECKBOX_WIDGET_CONDITIONAL_STYLE);
        createEReference(checkboxWidgetConditionalStyleEClass, PropertiesPackage.CHECKBOX_WIDGET_CONDITIONAL_STYLE__STYLE);

        radioWidgetConditionalStyleEClass = createEClass(PropertiesPackage.RADIO_WIDGET_CONDITIONAL_STYLE);
        createEReference(radioWidgetConditionalStyleEClass, PropertiesPackage.RADIO_WIDGET_CONDITIONAL_STYLE__STYLE);

        buttonWidgetConditionalStyleEClass = createEClass(PropertiesPackage.BUTTON_WIDGET_CONDITIONAL_STYLE);
        createEReference(buttonWidgetConditionalStyleEClass, PropertiesPackage.BUTTON_WIDGET_CONDITIONAL_STYLE__STYLE);

        selectWidgetConditionalStyleEClass = createEClass(PropertiesPackage.SELECT_WIDGET_CONDITIONAL_STYLE);
        createEReference(selectWidgetConditionalStyleEClass, PropertiesPackage.SELECT_WIDGET_CONDITIONAL_STYLE__STYLE);

        customWidgetConditionalStyleEClass = createEClass(PropertiesPackage.CUSTOM_WIDGET_CONDITIONAL_STYLE);
        createEReference(customWidgetConditionalStyleEClass, PropertiesPackage.CUSTOM_WIDGET_CONDITIONAL_STYLE__STYLE);

        listWidgetConditionalStyleEClass = createEClass(PropertiesPackage.LIST_WIDGET_CONDITIONAL_STYLE);
        createEReference(listWidgetConditionalStyleEClass, PropertiesPackage.LIST_WIDGET_CONDITIONAL_STYLE__STYLE);

        widgetActionEClass = createEClass(PropertiesPackage.WIDGET_ACTION);
        createEAttribute(widgetActionEClass, PropertiesPackage.WIDGET_ACTION__LABEL_EXPRESSION);
        createEReference(widgetActionEClass, PropertiesPackage.WIDGET_ACTION__INITIAL_OPERATION);

        hyperlinkWidgetConditionalStyleEClass = createEClass(PropertiesPackage.HYPERLINK_WIDGET_CONDITIONAL_STYLE);
        createEReference(hyperlinkWidgetConditionalStyleEClass, PropertiesPackage.HYPERLINK_WIDGET_CONDITIONAL_STYLE__STYLE);

        groupConditionalStyleEClass = createEClass(PropertiesPackage.GROUP_CONDITIONAL_STYLE);
        createEReference(groupConditionalStyleEClass, PropertiesPackage.GROUP_CONDITIONAL_STYLE__STYLE);

        editSupportEClass = createEClass(PropertiesPackage.EDIT_SUPPORT);

        // Create enums
        filL_LAYOUT_ORIENTATIONEEnum = createEEnum(PropertiesPackage.FILL_LAYOUT_ORIENTATION);
        toggleStyleEEnum = createEEnum(PropertiesPackage.TOGGLE_STYLE);
        titleBarStyleEEnum = createEEnum(PropertiesPackage.TITLE_BAR_STYLE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) {
            return;
        }
        isInitialized = true;

        // Initialize package
        setName(PropertiesPackage.eNAME);
        setNsPrefix(PropertiesPackage.eNS_PREFIX);
        setNsURI(PropertiesPackage.eNS_URI);

        // Obtain other dependent packages
        DescriptionPackage theDescriptionPackage = (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        ValidationPackage theValidationPackage = (ValidationPackage) EPackage.Registry.INSTANCE.getEPackage(ValidationPackage.eNS_URI);
        ToolPackage theToolPackage = (ToolPackage) EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);
        ViewpointPackage theViewpointPackage = (ViewpointPackage) EPackage.Registry.INSTANCE.getEPackage(ViewpointPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        viewExtensionDescriptionEClass.getESuperTypes().add(theDescriptionPackage.getExtension());
        propertyValidationRuleEClass.getESuperTypes().add(theValidationPackage.getValidationRule());
        containerDescriptionEClass.getESuperTypes().add(this.getControlDescription());
        fillLayoutDescriptionEClass.getESuperTypes().add(this.getLayoutDescription());
        gridLayoutDescriptionEClass.getESuperTypes().add(this.getLayoutDescription());
        widgetDescriptionEClass.getESuperTypes().add(this.getControlDescription());
        textDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        buttonDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        labelDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        checkboxDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        selectDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        dynamicMappingForEClass.getESuperTypes().add(this.getControlDescription());
        textAreaDescriptionEClass.getESuperTypes().add(this.getTextDescription());
        radioDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        listDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        customDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        hyperlinkDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        textWidgetStyleEClass.getESuperTypes().add(this.getWidgetStyle());
        labelWidgetStyleEClass.getESuperTypes().add(this.getWidgetStyle());
        checkboxWidgetStyleEClass.getESuperTypes().add(this.getWidgetStyle());
        radioWidgetStyleEClass.getESuperTypes().add(this.getWidgetStyle());
        buttonWidgetStyleEClass.getESuperTypes().add(this.getWidgetStyle());
        selectWidgetStyleEClass.getESuperTypes().add(this.getWidgetStyle());
        customWidgetStyleEClass.getESuperTypes().add(this.getWidgetStyle());
        listWidgetStyleEClass.getESuperTypes().add(this.getWidgetStyle());
        hyperlinkWidgetStyleEClass.getESuperTypes().add(this.getWidgetStyle());
        textWidgetConditionalStyleEClass.getESuperTypes().add(this.getWidgetConditionalStyle());
        labelWidgetConditionalStyleEClass.getESuperTypes().add(this.getWidgetConditionalStyle());
        checkboxWidgetConditionalStyleEClass.getESuperTypes().add(this.getWidgetConditionalStyle());
        radioWidgetConditionalStyleEClass.getESuperTypes().add(this.getWidgetConditionalStyle());
        buttonWidgetConditionalStyleEClass.getESuperTypes().add(this.getWidgetConditionalStyle());
        selectWidgetConditionalStyleEClass.getESuperTypes().add(this.getWidgetConditionalStyle());
        customWidgetConditionalStyleEClass.getESuperTypes().add(this.getWidgetConditionalStyle());
        listWidgetConditionalStyleEClass.getESuperTypes().add(this.getWidgetConditionalStyle());
        hyperlinkWidgetConditionalStyleEClass.getESuperTypes().add(this.getWidgetConditionalStyle());
        groupConditionalStyleEClass.getESuperTypes().add(this.getWidgetConditionalStyle());

        // Initialize classes and features; add operations and parameters
        initEClass(viewExtensionDescriptionEClass, ViewExtensionDescription.class, "ViewExtensionDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getViewExtensionDescription_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, ViewExtensionDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getViewExtensionDescription_Metamodels(), theEcorePackage.getEPackage(), null, "metamodels", null, 0, -1, ViewExtensionDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getViewExtensionDescription_Pages(), this.getPageDescription(), null, "pages", null, 0, -1, ViewExtensionDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getViewExtensionDescription_Groups(), this.getGroupDescription(), null, "groups", null, 0, -1, ViewExtensionDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(pageDescriptionEClass, PageDescription.class, "PageDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getPageDescription_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, PageDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getPageDescription_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", null, 0, 1, PageDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getPageDescription_DomainClass(), theDescriptionPackage.getTypeName(), "domainClass", null, 0, 1, PageDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getPageDescription_SemanticCandidateExpression(), theDescriptionPackage.getInterpretedExpression(), "semanticCandidateExpression", null, 0, 1, PageDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getPageDescription_PreconditionExpression(), theDescriptionPackage.getInterpretedExpression(), "preconditionExpression", null, 0, 1, PageDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getPageDescription_Groups(), this.getGroupDescription(), null, "groups", null, 0, -1, PageDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getPageDescription_ValidationSet(), this.getPageValidationSetDescription(), null, "validationSet", null, 0, 1, PageDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(pageValidationSetDescriptionEClass, PageValidationSetDescription.class, "PageValidationSetDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPageValidationSetDescription_SemanticValidationRules(), theValidationPackage.getSemanticValidationRule(), null, "semanticValidationRules", null, 0, -1, //$NON-NLS-1$
                PageValidationSetDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES,
                !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(propertyValidationRuleEClass, PropertyValidationRule.class, "PropertyValidationRule", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPropertyValidationRule_Targets(), this.getWidgetDescription(), null, "targets", null, 0, -1, PropertyValidationRule.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(groupDescriptionEClass, GroupDescription.class, "GroupDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getGroupDescription_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, GroupDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGroupDescription_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", null, 0, 1, GroupDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGroupDescription_DomainClass(), theDescriptionPackage.getTypeName(), "domainClass", null, 0, 1, GroupDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGroupDescription_SemanticCandidateExpression(), theDescriptionPackage.getInterpretedExpression(), "semanticCandidateExpression", null, 0, 1, GroupDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getGroupDescription_PreconditionExpression(), theDescriptionPackage.getInterpretedExpression(), "preconditionExpression", null, 0, 1, GroupDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getGroupDescription_Controls(), this.getControlDescription(), null, "controls", null, 0, -1, GroupDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getGroupDescription_ValidationSet(), this.getGroupValidationSetDescription(), null, "validationSet", null, 0, 1, GroupDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getGroupDescription_Style(), this.getGroupStyle(), null, "style", null, 0, 1, GroupDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getGroupDescription_ConditionalStyles(), this.getGroupConditionalStyle(), null, "conditionalStyles", null, 0, -1, GroupDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(groupValidationSetDescriptionEClass, GroupValidationSetDescription.class, "GroupValidationSetDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGroupValidationSetDescription_SemanticValidationRules(), theValidationPackage.getSemanticValidationRule(), null, "semanticValidationRules", null, 0, -1, //$NON-NLS-1$
                GroupValidationSetDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES,
                !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getGroupValidationSetDescription_PropertyValidationRules(), this.getPropertyValidationRule(), null, "propertyValidationRules", null, 0, -1, GroupValidationSetDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(controlDescriptionEClass, ControlDescription.class, "ControlDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getControlDescription_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, ControlDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(containerDescriptionEClass, ContainerDescription.class, "ContainerDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getContainerDescription_Controls(), this.getControlDescription(), null, "controls", null, 0, -1, ContainerDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getContainerDescription_Layout(), this.getLayoutDescription(), null, "layout", null, 0, 1, ContainerDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(layoutDescriptionEClass, LayoutDescription.class, "LayoutDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(fillLayoutDescriptionEClass, FillLayoutDescription.class, "FillLayoutDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getFillLayoutDescription_Orientation(), this.getFILL_LAYOUT_ORIENTATION(), "orientation", null, 0, 1, FillLayoutDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(gridLayoutDescriptionEClass, GridLayoutDescription.class, "GridLayoutDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getGridLayoutDescription_NumberOfColumns(), ecorePackage.getEInt(), "numberOfColumns", "1", 0, 1, GridLayoutDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGridLayoutDescription_MakeColumnsWithEqualWidth(), ecorePackage.getEBoolean(), "makeColumnsWithEqualWidth", null, 0, 1, GridLayoutDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(widgetDescriptionEClass, WidgetDescription.class, "WidgetDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getWidgetDescription_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", null, 0, 1, WidgetDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getWidgetDescription_HelpExpression(), theDescriptionPackage.getInterpretedExpression(), "helpExpression", null, 0, 1, WidgetDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getWidgetDescription_IsEnabledExpression(), theDescriptionPackage.getInterpretedExpression(), "isEnabledExpression", null, 0, 1, WidgetDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(textDescriptionEClass, TextDescription.class, "TextDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getTextDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, TextDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getTextDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, TextDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getTextDescription_Style(), this.getTextWidgetStyle(), null, "style", null, 0, 1, TextDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getTextDescription_ConditionalStyles(), this.getTextWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, TextDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(buttonDescriptionEClass, ButtonDescription.class, "ButtonDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getButtonDescription_ButtonLabelExpression(), theDescriptionPackage.getInterpretedExpression(), "buttonLabelExpression", null, 0, 1, ButtonDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getButtonDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, ButtonDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getButtonDescription_Style(), this.getButtonWidgetStyle(), null, "style", null, 0, 1, ButtonDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getButtonDescription_ConditionalStyles(), this.getButtonWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, ButtonDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(labelDescriptionEClass, LabelDescription.class, "LabelDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getLabelDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, LabelDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getLabelDescription_DisplayExpression(), theDescriptionPackage.getInterpretedExpression(), "displayExpression", null, 0, 1, LabelDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getLabelDescription_Style(), this.getLabelWidgetStyle(), null, "style", null, 0, 1, LabelDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getLabelDescription_ConditionalStyles(), this.getLabelWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, LabelDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getLabelDescription_Actions(), this.getWidgetAction(), null, "actions", null, 0, -1, LabelDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(checkboxDescriptionEClass, CheckboxDescription.class, "CheckboxDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getCheckboxDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, CheckboxDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getCheckboxDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, CheckboxDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getCheckboxDescription_Style(), this.getCheckboxWidgetStyle(), null, "style", null, 0, 1, CheckboxDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getCheckboxDescription_ConditionalStyles(), this.getCheckboxWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, CheckboxDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(selectDescriptionEClass, SelectDescription.class, "SelectDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSelectDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, SelectDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getSelectDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, SelectDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getSelectDescription_CandidatesExpression(), theDescriptionPackage.getInterpretedExpression(), "candidatesExpression", null, 0, 1, SelectDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getSelectDescription_CandidateDisplayExpression(), theDescriptionPackage.getInterpretedExpression(), "candidateDisplayExpression", null, 0, 1, SelectDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getSelectDescription_Style(), this.getSelectWidgetStyle(), null, "style", null, 0, 1, SelectDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getSelectDescription_ConditionalStyles(), this.getSelectWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, SelectDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dynamicMappingForEClass, DynamicMappingFor.class, "DynamicMappingFor", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDynamicMappingFor_Iterator(), ecorePackage.getEString(), "iterator", null, 1, 1, DynamicMappingFor.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDynamicMappingFor_IterableExpression(), theDescriptionPackage.getInterpretedExpression(), "iterableExpression", null, 1, 1, DynamicMappingFor.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDynamicMappingFor_Ifs(), this.getDynamicMappingIf(), null, "ifs", null, 1, -1, DynamicMappingFor.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(dynamicMappingIfEClass, DynamicMappingIf.class, "DynamicMappingIf", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDynamicMappingIf_PredicateExpression(), theDescriptionPackage.getInterpretedExpression(), "predicateExpression", null, 1, 1, DynamicMappingIf.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDynamicMappingIf_Widget(), this.getWidgetDescription(), null, "widget", null, 1, 1, DynamicMappingIf.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(textAreaDescriptionEClass, TextAreaDescription.class, "TextAreaDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getTextAreaDescription_LineCount(), ecorePackage.getEInt(), "lineCount", "5", 0, 1, TextAreaDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(radioDescriptionEClass, RadioDescription.class, "RadioDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getRadioDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, RadioDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getRadioDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, RadioDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getRadioDescription_CandidatesExpression(), theDescriptionPackage.getInterpretedExpression(), "candidatesExpression", null, 0, 1, RadioDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getRadioDescription_CandidateDisplayExpression(), theDescriptionPackage.getInterpretedExpression(), "candidateDisplayExpression", null, 0, 1, RadioDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getRadioDescription_Style(), this.getRadioWidgetStyle(), null, "style", null, 0, 1, RadioDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getRadioDescription_NumberOfColumns(), ecorePackage.getEInt(), "numberOfColumns", "-1", 0, 1, RadioDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getRadioDescription_ConditionalStyles(), this.getRadioWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, RadioDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(listDescriptionEClass, ListDescription.class, "ListDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getListDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, ListDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getListDescription_DisplayExpression(), theDescriptionPackage.getInterpretedExpression(), "displayExpression", null, 0, 1, ListDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getListDescription_OnClickOperation(), theToolPackage.getInitialOperation(), null, "onClickOperation", null, 0, 1, ListDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getListDescription_Actions(), this.getWidgetAction(), null, "actions", null, 0, -1, ListDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getListDescription_Style(), this.getListWidgetStyle(), null, "style", null, 0, 1, ListDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getListDescription_ConditionalStyles(), this.getListWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, ListDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(operationDescriptionEClass, OperationDescription.class, "OperationDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getOperationDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, OperationDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(customDescriptionEClass, CustomDescription.class, "CustomDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getCustomDescription_CustomExpressions(), this.getCustomExpression(), null, "customExpressions", null, 0, -1, CustomDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getCustomDescription_CustomOperations(), this.getCustomOperation(), null, "customOperations", null, 0, -1, CustomDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getCustomDescription_Style(), this.getCustomWidgetStyle(), null, "style", null, 0, 1, CustomDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getCustomDescription_ConditionalStyles(), this.getCustomWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, CustomDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(customExpressionEClass, CustomExpression.class, "CustomExpression", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getCustomExpression_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, CustomExpression.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getCustomExpression_CustomExpression(), theDescriptionPackage.getInterpretedExpression(), "customExpression", null, 0, 1, CustomExpression.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(customOperationEClass, CustomOperation.class, "CustomOperation", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getCustomOperation_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, CustomOperation.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getCustomOperation_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, CustomOperation.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(hyperlinkDescriptionEClass, HyperlinkDescription.class, "HyperlinkDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getHyperlinkDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, HyperlinkDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getHyperlinkDescription_DisplayExpression(), theDescriptionPackage.getInterpretedExpression(), "displayExpression", null, 0, 1, HyperlinkDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getHyperlinkDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, HyperlinkDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getHyperlinkDescription_Style(), this.getHyperlinkWidgetStyle(), null, "style", null, 0, 1, HyperlinkDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getHyperlinkDescription_ConditionalStyles(), this.getHyperlinkWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, HyperlinkDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getHyperlinkDescription_Actions(), this.getWidgetAction(), null, "actions", null, 0, -1, HyperlinkDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(widgetStyleEClass, WidgetStyle.class, "WidgetStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getWidgetStyle_LabelFontNameExpression(), theDescriptionPackage.getInterpretedExpression(), "labelFontNameExpression", null, 0, 1, WidgetStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getWidgetStyle_LabelFontSizeExpression(), theDescriptionPackage.getInterpretedExpression(), "labelFontSizeExpression", null, 0, 1, WidgetStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getWidgetStyle_LabelBackgroundColor(), theDescriptionPackage.getColorDescription(), null, "labelBackgroundColor", null, 0, 1, WidgetStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getWidgetStyle_LabelForegroundColor(), theDescriptionPackage.getColorDescription(), null, "labelForegroundColor", null, 0, 1, WidgetStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getWidgetStyle_LabelFontFormat(), theViewpointPackage.getFontFormat(), "labelFontFormat", null, 0, 4, WidgetStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(textWidgetStyleEClass, TextWidgetStyle.class, "TextWidgetStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getTextWidgetStyle_FontNameExpression(), theDescriptionPackage.getInterpretedExpression(), "fontNameExpression", null, 0, 1, TextWidgetStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getTextWidgetStyle_FontSizeExpression(), theDescriptionPackage.getInterpretedExpression(), "fontSizeExpression", null, 0, 1, TextWidgetStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getTextWidgetStyle_BackgroundColor(), theDescriptionPackage.getColorDescription(), null, "backgroundColor", null, 0, 1, TextWidgetStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getTextWidgetStyle_ForegroundColor(), theDescriptionPackage.getColorDescription(), null, "foregroundColor", null, 0, 1, TextWidgetStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getTextWidgetStyle_FontFormat(), theViewpointPackage.getFontFormat(), "fontFormat", null, 0, 4, TextWidgetStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(labelWidgetStyleEClass, LabelWidgetStyle.class, "LabelWidgetStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getLabelWidgetStyle_FontNameExpression(), theDescriptionPackage.getInterpretedExpression(), "fontNameExpression", null, 0, 1, LabelWidgetStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getLabelWidgetStyle_FontSizeExpression(), theDescriptionPackage.getInterpretedExpression(), "fontSizeExpression", null, 0, 1, LabelWidgetStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getLabelWidgetStyle_BackgroundColor(), theDescriptionPackage.getColorDescription(), null, "backgroundColor", null, 0, 1, LabelWidgetStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getLabelWidgetStyle_ForegroundColor(), theDescriptionPackage.getColorDescription(), null, "foregroundColor", null, 0, 1, LabelWidgetStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getLabelWidgetStyle_FontFormat(), theViewpointPackage.getFontFormat(), "fontFormat", null, 0, 4, LabelWidgetStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(checkboxWidgetStyleEClass, CheckboxWidgetStyle.class, "CheckboxWidgetStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(radioWidgetStyleEClass, RadioWidgetStyle.class, "RadioWidgetStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(buttonWidgetStyleEClass, ButtonWidgetStyle.class, "ButtonWidgetStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(selectWidgetStyleEClass, SelectWidgetStyle.class, "SelectWidgetStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(customWidgetStyleEClass, CustomWidgetStyle.class, "CustomWidgetStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(listWidgetStyleEClass, ListWidgetStyle.class, "ListWidgetStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(hyperlinkWidgetStyleEClass, HyperlinkWidgetStyle.class, "HyperlinkWidgetStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getHyperlinkWidgetStyle_FontNameExpression(), theDescriptionPackage.getInterpretedExpression(), "fontNameExpression", null, 0, 1, HyperlinkWidgetStyle.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getHyperlinkWidgetStyle_FontSizeExpression(), theDescriptionPackage.getInterpretedExpression(), "fontSizeExpression", null, 0, 1, HyperlinkWidgetStyle.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getHyperlinkWidgetStyle_BackgroundColor(), theDescriptionPackage.getColorDescription(), null, "backgroundColor", null, 0, 1, HyperlinkWidgetStyle.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getHyperlinkWidgetStyle_FontFormat(), theViewpointPackage.getFontFormat(), "fontFormat", null, 0, 4, HyperlinkWidgetStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(groupStyleEClass, GroupStyle.class, "GroupStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getGroupStyle_BackgroundColor(), theDescriptionPackage.getColorDescription(), null, "backgroundColor", null, 0, 1, GroupStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getGroupStyle_ForegroundColor(), theDescriptionPackage.getColorDescription(), null, "foregroundColor", null, 0, 1, GroupStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGroupStyle_FontNameExpression(), theDescriptionPackage.getInterpretedExpression(), "fontNameExpression", null, 0, 1, GroupStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGroupStyle_FontSizeExpression(), theDescriptionPackage.getInterpretedExpression(), "fontSizeExpression", null, 0, 1, GroupStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGroupStyle_BarStyle(), this.getTitleBarStyle(), "barStyle", null, 0, 1, GroupStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGroupStyle_ToggleStyle(), this.getToggleStyle(), "toggleStyle", null, 0, 1, GroupStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGroupStyle_ExpandedByDefault(), ecorePackage.getEBoolean(), "expandedByDefault", null, 0, 1, GroupStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(widgetConditionalStyleEClass, WidgetConditionalStyle.class, "WidgetConditionalStyle", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getWidgetConditionalStyle_PreconditionExpression(), theDescriptionPackage.getInterpretedExpression(), "preconditionExpression", null, 0, 1, WidgetConditionalStyle.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(textWidgetConditionalStyleEClass, TextWidgetConditionalStyle.class, "TextWidgetConditionalStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTextWidgetConditionalStyle_Style(), this.getTextWidgetStyle(), null, "style", null, 0, 1, TextWidgetConditionalStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(labelWidgetConditionalStyleEClass, LabelWidgetConditionalStyle.class, "LabelWidgetConditionalStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLabelWidgetConditionalStyle_Style(), this.getLabelWidgetStyle(), null, "style", null, 0, 1, LabelWidgetConditionalStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(checkboxWidgetConditionalStyleEClass, CheckboxWidgetConditionalStyle.class, "CheckboxWidgetConditionalStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCheckboxWidgetConditionalStyle_Style(), this.getCheckboxWidgetStyle(), null, "style", null, 0, 1, CheckboxWidgetConditionalStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(radioWidgetConditionalStyleEClass, RadioWidgetConditionalStyle.class, "RadioWidgetConditionalStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRadioWidgetConditionalStyle_Style(), this.getRadioWidgetStyle(), null, "style", null, 0, 1, RadioWidgetConditionalStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(buttonWidgetConditionalStyleEClass, ButtonWidgetConditionalStyle.class, "ButtonWidgetConditionalStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getButtonWidgetConditionalStyle_Style(), this.getButtonWidgetStyle(), null, "style", null, 0, 1, ButtonWidgetConditionalStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(selectWidgetConditionalStyleEClass, SelectWidgetConditionalStyle.class, "SelectWidgetConditionalStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSelectWidgetConditionalStyle_Style(), this.getSelectWidgetStyle(), null, "style", null, 0, 1, SelectWidgetConditionalStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(customWidgetConditionalStyleEClass, CustomWidgetConditionalStyle.class, "CustomWidgetConditionalStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCustomWidgetConditionalStyle_Style(), this.getCustomWidgetStyle(), null, "style", null, 0, 1, CustomWidgetConditionalStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(listWidgetConditionalStyleEClass, ListWidgetConditionalStyle.class, "ListWidgetConditionalStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getListWidgetConditionalStyle_Style(), this.getListWidgetStyle(), null, "style", null, 0, 1, ListWidgetConditionalStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(widgetActionEClass, WidgetAction.class, "WidgetAction", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getWidgetAction_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", null, 0, 1, WidgetAction.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getWidgetAction_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, WidgetAction.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(hyperlinkWidgetConditionalStyleEClass, HyperlinkWidgetConditionalStyle.class, "HyperlinkWidgetConditionalStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getHyperlinkWidgetConditionalStyle_Style(), this.getHyperlinkWidgetStyle(), null, "style", null, 0, 1, HyperlinkWidgetConditionalStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(groupConditionalStyleEClass, GroupConditionalStyle.class, "GroupConditionalStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getGroupConditionalStyle_Style(), this.getGroupStyle(), null, "style", null, 0, 1, GroupConditionalStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(editSupportEClass, EditSupport.class, "EditSupport", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        addEOperation(editSupportEClass, ecorePackage.getEJavaObject(), "getImage", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        addEOperation(editSupportEClass, ecorePackage.getEString(), "getText", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        EOperation op = addEOperation(editSupportEClass, ecorePackage.getEJavaObject(), "getText", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, theEcorePackage.getEStructuralFeature(), "feature", 1, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        addEOperation(editSupportEClass, ecorePackage.getEString(), "getTabName", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        op = addEOperation(editSupportEClass, ecorePackage.getEJavaObject(), "getChoiceOfValues", 0, -1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, theEcorePackage.getEStructuralFeature(), "feature", 1, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        op = addEOperation(editSupportEClass, ecorePackage.getEBoolean(), "isMultiline", 1, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, ecorePackage.getEStructuralFeature(), "eStructuralFeature", 1, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        op = addEOperation(editSupportEClass, ecorePackage.getEString(), "getDescription", 1, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, ecorePackage.getEStructuralFeature(), "eStructuralFeature", 1, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        addEOperation(editSupportEClass, ecorePackage.getEStructuralFeature(), "getEStructuralFeatures", 0, -1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        op = addEOperation(editSupportEClass, ecorePackage.getEJavaObject(), "setValue", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, theEcorePackage.getEStructuralFeature(), "feature", 1, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, ecorePackage.getEJavaObject(), "newValue", 1, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        op = addEOperation(editSupportEClass, theEcorePackage.getEBoolean(), "needsTextWidget", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, theEcorePackage.getEStructuralFeature(), "eStructuralFeature", 1, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        op = addEOperation(editSupportEClass, theEcorePackage.getEBoolean(), "needsCheckboxWidget", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, theEcorePackage.getEStructuralFeature(), "eStructuralFeature", 1, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        // Initialize enums and add enum literals
        initEEnum(filL_LAYOUT_ORIENTATIONEEnum, org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION.class, "FILL_LAYOUT_ORIENTATION"); //$NON-NLS-1$
        addEEnumLiteral(filL_LAYOUT_ORIENTATIONEEnum, org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION.VERTICAL);
        addEEnumLiteral(filL_LAYOUT_ORIENTATIONEEnum, org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION.HORIZONTAL);

        initEEnum(toggleStyleEEnum, ToggleStyle.class, "ToggleStyle"); //$NON-NLS-1$
        addEEnumLiteral(toggleStyleEEnum, ToggleStyle.TWISTIE);
        addEEnumLiteral(toggleStyleEEnum, ToggleStyle.TREE_NODE);
        addEEnumLiteral(toggleStyleEEnum, ToggleStyle.NONE);

        initEEnum(titleBarStyleEEnum, TitleBarStyle.class, "TitleBarStyle"); //$NON-NLS-1$
        addEEnumLiteral(titleBarStyleEEnum, TitleBarStyle.TITLE_BAR);
        addEEnumLiteral(titleBarStyleEEnum, TitleBarStyle.SHORT_TITLE_BAR);
        addEEnumLiteral(titleBarStyleEEnum, TitleBarStyle.NO_TITLE);

        // Create resource
        createResource(PropertiesPackage.eNS_URI);
    }

} // PropertiesPackageImpl
