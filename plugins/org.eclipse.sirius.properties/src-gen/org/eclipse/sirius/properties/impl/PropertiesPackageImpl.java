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
import org.eclipse.sirius.properties.AbstractButtonDescription;
import org.eclipse.sirius.properties.AbstractCheckboxDescription;
import org.eclipse.sirius.properties.AbstractContainerDescription;
import org.eclipse.sirius.properties.AbstractControlDescription;
import org.eclipse.sirius.properties.AbstractCustomDescription;
import org.eclipse.sirius.properties.AbstractDynamicMappingForDescription;
import org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription;
import org.eclipse.sirius.properties.AbstractGroupDescription;
import org.eclipse.sirius.properties.AbstractHyperlinkDescription;
import org.eclipse.sirius.properties.AbstractLabelDescription;
import org.eclipse.sirius.properties.AbstractListDescription;
import org.eclipse.sirius.properties.AbstractOverrideDescription;
import org.eclipse.sirius.properties.AbstractPageDescription;
import org.eclipse.sirius.properties.AbstractRadioDescription;
import org.eclipse.sirius.properties.AbstractSelectDescription;
import org.eclipse.sirius.properties.AbstractTextAreaDescription;
import org.eclipse.sirius.properties.AbstractTextDescription;
import org.eclipse.sirius.properties.AbstractWidgetDescription;
import org.eclipse.sirius.properties.ButtonDescription;
import org.eclipse.sirius.properties.ButtonOverrideDescription;
import org.eclipse.sirius.properties.ButtonWidgetConditionalStyle;
import org.eclipse.sirius.properties.ButtonWidgetStyle;
import org.eclipse.sirius.properties.Category;
import org.eclipse.sirius.properties.CheckboxDescription;
import org.eclipse.sirius.properties.CheckboxOverrideDescription;
import org.eclipse.sirius.properties.CheckboxWidgetConditionalStyle;
import org.eclipse.sirius.properties.CheckboxWidgetStyle;
import org.eclipse.sirius.properties.ContainerDescription;
import org.eclipse.sirius.properties.ContainerOverrideDescription;
import org.eclipse.sirius.properties.ControlDescription;
import org.eclipse.sirius.properties.CustomDescription;
import org.eclipse.sirius.properties.CustomExpression;
import org.eclipse.sirius.properties.CustomOperation;
import org.eclipse.sirius.properties.CustomOverrideDescription;
import org.eclipse.sirius.properties.CustomWidgetConditionalStyle;
import org.eclipse.sirius.properties.CustomWidgetStyle;
import org.eclipse.sirius.properties.DialogButton;
import org.eclipse.sirius.properties.DialogModelOperation;
import org.eclipse.sirius.properties.DynamicMappingForDescription;
import org.eclipse.sirius.properties.DynamicMappingForOverrideDescription;
import org.eclipse.sirius.properties.DynamicMappingIfDescription;
import org.eclipse.sirius.properties.DynamicMappingIfOverrideDescription;
import org.eclipse.sirius.properties.EditSupport;
import org.eclipse.sirius.properties.FillLayoutDescription;
import org.eclipse.sirius.properties.GridLayoutDescription;
import org.eclipse.sirius.properties.GroupConditionalStyle;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.GroupOverrideDescription;
import org.eclipse.sirius.properties.GroupStyle;
import org.eclipse.sirius.properties.GroupValidationSetDescription;
import org.eclipse.sirius.properties.HyperlinkDescription;
import org.eclipse.sirius.properties.HyperlinkOverrideDescription;
import org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle;
import org.eclipse.sirius.properties.HyperlinkWidgetStyle;
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.LabelOverrideDescription;
import org.eclipse.sirius.properties.LabelWidgetConditionalStyle;
import org.eclipse.sirius.properties.LabelWidgetStyle;
import org.eclipse.sirius.properties.LayoutDescription;
import org.eclipse.sirius.properties.ListDescription;
import org.eclipse.sirius.properties.ListOverrideDescription;
import org.eclipse.sirius.properties.ListWidgetConditionalStyle;
import org.eclipse.sirius.properties.ListWidgetStyle;
import org.eclipse.sirius.properties.OperationDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PageOverrideDescription;
import org.eclipse.sirius.properties.PageValidationSetDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.PropertyValidationRule;
import org.eclipse.sirius.properties.RadioDescription;
import org.eclipse.sirius.properties.RadioOverrideDescription;
import org.eclipse.sirius.properties.RadioWidgetConditionalStyle;
import org.eclipse.sirius.properties.RadioWidgetStyle;
import org.eclipse.sirius.properties.SelectDescription;
import org.eclipse.sirius.properties.SelectOverrideDescription;
import org.eclipse.sirius.properties.SelectWidgetConditionalStyle;
import org.eclipse.sirius.properties.SelectWidgetStyle;
import org.eclipse.sirius.properties.TextAreaDescription;
import org.eclipse.sirius.properties.TextAreaOverrideDescription;
import org.eclipse.sirius.properties.TextDescription;
import org.eclipse.sirius.properties.TextOverrideDescription;
import org.eclipse.sirius.properties.TextWidgetConditionalStyle;
import org.eclipse.sirius.properties.TextWidgetStyle;
import org.eclipse.sirius.properties.TitleBarStyle;
import org.eclipse.sirius.properties.ToggleStyle;
import org.eclipse.sirius.properties.ToolbarAction;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.WidgetAction;
import org.eclipse.sirius.properties.WidgetConditionalStyle;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.properties.WidgetStyle;
import org.eclipse.sirius.properties.WizardModelOperation;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
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
    private EClass categoryEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractOverrideDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractPageDescriptionEClass = null;

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
    private EClass pageOverrideDescriptionEClass = null;

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
    private EClass abstractGroupDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass toolbarActionEClass = null;

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
    private EClass groupOverrideDescriptionEClass = null;

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
    private EClass abstractControlDescriptionEClass = null;

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
    private EClass abstractContainerDescriptionEClass = null;

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
    private EClass containerOverrideDescriptionEClass = null;

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
    private EClass abstractWidgetDescriptionEClass = null;

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
    private EClass abstractTextDescriptionEClass = null;

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
    private EClass textOverrideDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractButtonDescriptionEClass = null;

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
    private EClass buttonOverrideDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractLabelDescriptionEClass = null;

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
    private EClass labelOverrideDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractCheckboxDescriptionEClass = null;

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
    private EClass checkboxOverrideDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractSelectDescriptionEClass = null;

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
    private EClass selectOverrideDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractDynamicMappingForDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dynamicMappingForDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dynamicMappingForOverrideDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractDynamicMappingIfDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dynamicMappingIfDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dynamicMappingIfOverrideDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractTextAreaDescriptionEClass = null;

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
    private EClass textAreaOverrideDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractRadioDescriptionEClass = null;

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
    private EClass radioOverrideDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractListDescriptionEClass = null;

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
    private EClass listOverrideDescriptionEClass = null;

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
    private EClass abstractCustomDescriptionEClass = null;

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
    private EClass customOverrideDescriptionEClass = null;

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
    private EClass abstractHyperlinkDescriptionEClass = null;

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
    private EClass hyperlinkOverrideDescriptionEClass = null;

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
    private EClass dialogModelOperationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dialogButtonEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass wizardModelOperationEClass = null;

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
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
     * performs initialization of the package, or returns the registered package, if one already exists. <!--
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
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link PropertiesPackage#eINSTANCE} when that field is accessed. Clients should
     * not invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
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
                ? EPackage.Registry.INSTANCE.get(PropertiesPackage.eNS_URI)
                : new PropertiesPackageImpl());

        PropertiesPackageImpl.isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();
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
    public EReference getViewExtensionDescription_Metamodels() {
        return (EReference) viewExtensionDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getViewExtensionDescription_Categories() {
        return (EReference) viewExtensionDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCategory() {
        return categoryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCategory_Pages() {
        return (EReference) categoryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCategory_Groups() {
        return (EReference) categoryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCategory_Overrides() {
        return (EReference) categoryEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractOverrideDescription() {
        return abstractOverrideDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractPageDescription() {
        return abstractPageDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractPageDescription_LabelExpression() {
        return (EAttribute) abstractPageDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractPageDescription_DomainClass() {
        return (EAttribute) abstractPageDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractPageDescription_SemanticCandidateExpression() {
        return (EAttribute) abstractPageDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractPageDescription_PreconditionExpression() {
        return (EAttribute) abstractPageDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractPageDescription_Groups() {
        return (EReference) abstractPageDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractPageDescription_ValidationSet() {
        return (EReference) abstractPageDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractPageDescription_Actions() {
        return (EReference) abstractPageDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractPageDescription_Extends() {
        return (EReference) abstractPageDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractPageDescription_FilterGroupsFromExtendedPageExpression() {
        return (EAttribute) abstractPageDescriptionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractPageDescription_FilterValidationRulesFromExtendedPageExpression() {
        return (EAttribute) abstractPageDescriptionEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractPageDescription_Indented() {
        return (EAttribute) abstractPageDescriptionEClass.getEStructuralFeatures().get(10);
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
    public EClass getPageOverrideDescription() {
        return pageOverrideDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getPageOverrideDescription_Overrides() {
        return (EReference) pageOverrideDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getPageOverrideDescription_FilterGroupsFromOverriddenPageExpression() {
        return (EAttribute) pageOverrideDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getPageOverrideDescription_FilterValidationRulesFromOverriddenPageExpression() {
        return (EAttribute) pageOverrideDescriptionEClass.getEStructuralFeatures().get(2);
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
    public EClass getAbstractGroupDescription() {
        return abstractGroupDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractGroupDescription_LabelExpression() {
        return (EAttribute) abstractGroupDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractGroupDescription_DomainClass() {
        return (EAttribute) abstractGroupDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractGroupDescription_SemanticCandidateExpression() {
        return (EAttribute) abstractGroupDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractGroupDescription_PreconditionExpression() {
        return (EAttribute) abstractGroupDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractGroupDescription_Controls() {
        return (EReference) abstractGroupDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractGroupDescription_ValidationSet() {
        return (EReference) abstractGroupDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractGroupDescription_Style() {
        return (EReference) abstractGroupDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractGroupDescription_ConditionalStyles() {
        return (EReference) abstractGroupDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractGroupDescription_Extends() {
        return (EReference) abstractGroupDescriptionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractGroupDescription_FilterControlsFromExtendedGroupExpression() {
        return (EAttribute) abstractGroupDescriptionEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractGroupDescription_FilterValidationRulesFromExtendedGroupExpression() {
        return (EAttribute) abstractGroupDescriptionEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractGroupDescription_FilterConditionalStylesFromExtendedGroupExpression() {
        return (EAttribute) abstractGroupDescriptionEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractGroupDescription_Actions() {
        return (EReference) abstractGroupDescriptionEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getToolbarAction() {
        return toolbarActionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getToolbarAction_TooltipExpression() {
        return (EAttribute) toolbarActionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getToolbarAction_ImageExpression() {
        return (EAttribute) toolbarActionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getToolbarAction_InitialOperation() {
        return (EReference) toolbarActionEClass.getEStructuralFeatures().get(2);
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
    public EClass getGroupOverrideDescription() {
        return groupOverrideDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGroupOverrideDescription_Overrides() {
        return (EReference) groupOverrideDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGroupOverrideDescription_FilterControlsFromOverriddenGroupExpression() {
        return (EAttribute) groupOverrideDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGroupOverrideDescription_FilterValidationRulesFromOverriddenGroupExpression() {
        return (EAttribute) groupOverrideDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGroupOverrideDescription_FilterConditionalStylesFromOverriddenGroupExpression() {
        return (EAttribute) groupOverrideDescriptionEClass.getEStructuralFeatures().get(3);
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
    public EClass getAbstractControlDescription() {
        return abstractControlDescriptionEClass;
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
    public EClass getAbstractContainerDescription() {
        return abstractContainerDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractContainerDescription_Controls() {
        return (EReference) abstractContainerDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractContainerDescription_Layout() {
        return (EReference) abstractContainerDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractContainerDescription_Extends() {
        return (EReference) abstractContainerDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractContainerDescription_FilterControlsFromExtendedContainerExpression() {
        return (EAttribute) abstractContainerDescriptionEClass.getEStructuralFeatures().get(3);
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
    public EClass getContainerOverrideDescription() {
        return containerOverrideDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getContainerOverrideDescription_Overrides() {
        return (EReference) containerOverrideDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getContainerOverrideDescription_FilterControlsFromOverriddenContainerExpression() {
        return (EAttribute) containerOverrideDescriptionEClass.getEStructuralFeatures().get(1);
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
    public EClass getAbstractWidgetDescription() {
        return abstractWidgetDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractWidgetDescription_LabelExpression() {
        return (EAttribute) abstractWidgetDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractWidgetDescription_HelpExpression() {
        return (EAttribute) abstractWidgetDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractWidgetDescription_IsEnabledExpression() {
        return (EAttribute) abstractWidgetDescriptionEClass.getEStructuralFeatures().get(2);
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
    public EClass getAbstractTextDescription() {
        return abstractTextDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractTextDescription_ValueExpression() {
        return (EAttribute) abstractTextDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractTextDescription_InitialOperation() {
        return (EReference) abstractTextDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractTextDescription_Style() {
        return (EReference) abstractTextDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractTextDescription_ConditionalStyles() {
        return (EReference) abstractTextDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractTextDescription_Extends() {
        return (EReference) abstractTextDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractTextDescription_FilterConditionalStylesFromExtendedTextExpression() {
        return (EAttribute) abstractTextDescriptionEClass.getEStructuralFeatures().get(5);
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
    public EClass getTextOverrideDescription() {
        return textOverrideDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getTextOverrideDescription_Overrides() {
        return (EReference) textOverrideDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTextOverrideDescription_FilterConditionalStylesFromOverriddenTextExpression() {
        return (EAttribute) textOverrideDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractButtonDescription() {
        return abstractButtonDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractButtonDescription_ButtonLabelExpression() {
        return (EAttribute) abstractButtonDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractButtonDescription_ImageExpression() {
        return (EAttribute) abstractButtonDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractButtonDescription_InitialOperation() {
        return (EReference) abstractButtonDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractButtonDescription_Style() {
        return (EReference) abstractButtonDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractButtonDescription_ConditionalStyles() {
        return (EReference) abstractButtonDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractButtonDescription_Extends() {
        return (EReference) abstractButtonDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractButtonDescription_FilterConditionalStylesFromExtendedButtonExpression() {
        return (EAttribute) abstractButtonDescriptionEClass.getEStructuralFeatures().get(6);
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
    public EClass getButtonOverrideDescription() {
        return buttonOverrideDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getButtonOverrideDescription_Overrides() {
        return (EReference) buttonOverrideDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getButtonOverrideDescription_FilterConditionalStylesFromOverriddenButtonExpression() {
        return (EAttribute) buttonOverrideDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractLabelDescription() {
        return abstractLabelDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractLabelDescription_ValueExpression() {
        return (EAttribute) abstractLabelDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractLabelDescription_DisplayExpression() {
        return (EAttribute) abstractLabelDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractLabelDescription_Style() {
        return (EReference) abstractLabelDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractLabelDescription_ConditionalStyles() {
        return (EReference) abstractLabelDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractLabelDescription_Actions() {
        return (EReference) abstractLabelDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractLabelDescription_Extends() {
        return (EReference) abstractLabelDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractLabelDescription_FilterConditionalStylesFromExtendedLabelExpression() {
        return (EAttribute) abstractLabelDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractLabelDescription_FilterActionsFromExtendedLabelExpression() {
        return (EAttribute) abstractLabelDescriptionEClass.getEStructuralFeatures().get(7);
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
    public EClass getLabelOverrideDescription() {
        return labelOverrideDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLabelOverrideDescription_Overrides() {
        return (EReference) labelOverrideDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLabelOverrideDescription_FilterConditionalStylesFromOverriddenLabelExpression() {
        return (EAttribute) labelOverrideDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLabelOverrideDescription_FilterActionsFromOverriddenLabelExpression() {
        return (EAttribute) labelOverrideDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractCheckboxDescription() {
        return abstractCheckboxDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractCheckboxDescription_ValueExpression() {
        return (EAttribute) abstractCheckboxDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractCheckboxDescription_InitialOperation() {
        return (EReference) abstractCheckboxDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractCheckboxDescription_Style() {
        return (EReference) abstractCheckboxDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractCheckboxDescription_ConditionalStyles() {
        return (EReference) abstractCheckboxDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractCheckboxDescription_Extends() {
        return (EReference) abstractCheckboxDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractCheckboxDescription_FilterConditionalStylesFromExtendedCheckboxExpression() {
        return (EAttribute) abstractCheckboxDescriptionEClass.getEStructuralFeatures().get(5);
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
    public EClass getCheckboxOverrideDescription() {
        return checkboxOverrideDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCheckboxOverrideDescription_Overrides() {
        return (EReference) checkboxOverrideDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCheckboxOverrideDescription_FilterConditionalStylesFromOverriddenCheckboxExpression() {
        return (EAttribute) checkboxOverrideDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractSelectDescription() {
        return abstractSelectDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractSelectDescription_ValueExpression() {
        return (EAttribute) abstractSelectDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractSelectDescription_InitialOperation() {
        return (EReference) abstractSelectDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractSelectDescription_CandidatesExpression() {
        return (EAttribute) abstractSelectDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractSelectDescription_CandidateDisplayExpression() {
        return (EAttribute) abstractSelectDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractSelectDescription_Style() {
        return (EReference) abstractSelectDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractSelectDescription_ConditionalStyles() {
        return (EReference) abstractSelectDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractSelectDescription_Extends() {
        return (EReference) abstractSelectDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractSelectDescription_FilterConditionalStylesFromExtendedSelectExpression() {
        return (EAttribute) abstractSelectDescriptionEClass.getEStructuralFeatures().get(7);
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
    public EClass getSelectOverrideDescription() {
        return selectOverrideDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSelectOverrideDescription_Overrides() {
        return (EReference) selectOverrideDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSelectOverrideDescription_FilterConditionalStylesFromOverriddenSelectExpression() {
        return (EAttribute) selectOverrideDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractDynamicMappingForDescription() {
        return abstractDynamicMappingForDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractDynamicMappingForDescription_Iterator() {
        return (EAttribute) abstractDynamicMappingForDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractDynamicMappingForDescription_IterableExpression() {
        return (EAttribute) abstractDynamicMappingForDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractDynamicMappingForDescription_ForceRefresh() {
        return (EAttribute) abstractDynamicMappingForDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractDynamicMappingForDescription_Ifs() {
        return (EReference) abstractDynamicMappingForDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractDynamicMappingForDescription_Extends() {
        return (EReference) abstractDynamicMappingForDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractDynamicMappingForDescription_FilterIfsFromExtendedDynamicMappingForExpression() {
        return (EAttribute) abstractDynamicMappingForDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDynamicMappingForDescription() {
        return dynamicMappingForDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDynamicMappingForOverrideDescription() {
        return dynamicMappingForOverrideDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDynamicMappingForOverrideDescription_Overrides() {
        return (EReference) dynamicMappingForOverrideDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDynamicMappingForOverrideDescription_FilterIfsFromOverriddenDynamicMappingForExpression() {
        return (EAttribute) dynamicMappingForOverrideDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractDynamicMappingIfDescription() {
        return abstractDynamicMappingIfDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractDynamicMappingIfDescription_PredicateExpression() {
        return (EAttribute) abstractDynamicMappingIfDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractDynamicMappingIfDescription_Widget() {
        return (EReference) abstractDynamicMappingIfDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractDynamicMappingIfDescription_Extends() {
        return (EReference) abstractDynamicMappingIfDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDynamicMappingIfDescription() {
        return dynamicMappingIfDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDynamicMappingIfOverrideDescription() {
        return dynamicMappingIfOverrideDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDynamicMappingIfOverrideDescription_Overrides() {
        return (EReference) dynamicMappingIfOverrideDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractTextAreaDescription() {
        return abstractTextAreaDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractTextAreaDescription_LineCount() {
        return (EAttribute) abstractTextAreaDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractTextAreaDescription_ValueExpression() {
        return (EAttribute) abstractTextAreaDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractTextAreaDescription_InitialOperation() {
        return (EReference) abstractTextAreaDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractTextAreaDescription_Style() {
        return (EReference) abstractTextAreaDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractTextAreaDescription_ConditionalStyles() {
        return (EReference) abstractTextAreaDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractTextAreaDescription_Extends() {
        return (EReference) abstractTextAreaDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractTextAreaDescription_FilterConditionalStylesFromExtendedTextAreaExpression() {
        return (EAttribute) abstractTextAreaDescriptionEClass.getEStructuralFeatures().get(6);
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
    public EClass getTextAreaOverrideDescription() {
        return textAreaOverrideDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getTextAreaOverrideDescription_Overrides() {
        return (EReference) textAreaOverrideDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTextAreaOverrideDescription_FilterConditionalStylesFromOverriddenTextAreaExpression() {
        return (EAttribute) textAreaOverrideDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractRadioDescription() {
        return abstractRadioDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractRadioDescription_ValueExpression() {
        return (EAttribute) abstractRadioDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractRadioDescription_InitialOperation() {
        return (EReference) abstractRadioDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractRadioDescription_CandidatesExpression() {
        return (EAttribute) abstractRadioDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractRadioDescription_CandidateDisplayExpression() {
        return (EAttribute) abstractRadioDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractRadioDescription_Style() {
        return (EReference) abstractRadioDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractRadioDescription_NumberOfColumns() {
        return (EAttribute) abstractRadioDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractRadioDescription_ConditionalStyles() {
        return (EReference) abstractRadioDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractRadioDescription_Extends() {
        return (EReference) abstractRadioDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractRadioDescription_FilterConditionalStylesFromExtendedRadioExpression() {
        return (EAttribute) abstractRadioDescriptionEClass.getEStructuralFeatures().get(8);
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
    public EClass getRadioOverrideDescription() {
        return radioOverrideDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getRadioOverrideDescription_Overrides() {
        return (EReference) radioOverrideDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRadioOverrideDescription_FilterConditionalStylesFromOverriddenRadioExpression() {
        return (EAttribute) radioOverrideDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractListDescription() {
        return abstractListDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractListDescription_ValueExpression() {
        return (EAttribute) abstractListDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractListDescription_DisplayExpression() {
        return (EAttribute) abstractListDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractListDescription_OnClickOperation() {
        return (EReference) abstractListDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractListDescription_Actions() {
        return (EReference) abstractListDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractListDescription_Style() {
        return (EReference) abstractListDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractListDescription_ConditionalStyles() {
        return (EReference) abstractListDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractListDescription_Extends() {
        return (EReference) abstractListDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractListDescription_FilterConditionalStylesFromExtendedListExpression() {
        return (EAttribute) abstractListDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractListDescription_FilterActionsFromExtendedListExpression() {
        return (EAttribute) abstractListDescriptionEClass.getEStructuralFeatures().get(8);
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
    public EClass getListOverrideDescription() {
        return listOverrideDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getListOverrideDescription_Overrides() {
        return (EReference) listOverrideDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getListOverrideDescription_FilterConditionalStylesFromOverriddenListExpression() {
        return (EAttribute) listOverrideDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getListOverrideDescription_FilterActionsFromOverriddenListExpression() {
        return (EAttribute) listOverrideDescriptionEClass.getEStructuralFeatures().get(2);
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
    public EClass getAbstractCustomDescription() {
        return abstractCustomDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractCustomDescription_CustomExpressions() {
        return (EReference) abstractCustomDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractCustomDescription_CustomOperations() {
        return (EReference) abstractCustomDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractCustomDescription_Style() {
        return (EReference) abstractCustomDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractCustomDescription_ConditionalStyles() {
        return (EReference) abstractCustomDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractCustomDescription_Extends() {
        return (EReference) abstractCustomDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractCustomDescription_FilterConditionalStylesFromExtendedCustomExpression() {
        return (EAttribute) abstractCustomDescriptionEClass.getEStructuralFeatures().get(5);
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
    public EClass getCustomOverrideDescription() {
        return customOverrideDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCustomOverrideDescription_Overrides() {
        return (EReference) customOverrideDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCustomOverrideDescription_FilterConditionalStylesFromOverriddenCustomExpression() {
        return (EAttribute) customOverrideDescriptionEClass.getEStructuralFeatures().get(1);
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
    public EAttribute getCustomExpression_CustomExpression() {
        return (EAttribute) customExpressionEClass.getEStructuralFeatures().get(0);
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
    public EReference getCustomOperation_InitialOperation() {
        return (EReference) customOperationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractHyperlinkDescription() {
        return abstractHyperlinkDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractHyperlinkDescription_ValueExpression() {
        return (EAttribute) abstractHyperlinkDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractHyperlinkDescription_DisplayExpression() {
        return (EAttribute) abstractHyperlinkDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractHyperlinkDescription_InitialOperation() {
        return (EReference) abstractHyperlinkDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractHyperlinkDescription_Style() {
        return (EReference) abstractHyperlinkDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractHyperlinkDescription_ConditionalStyles() {
        return (EReference) abstractHyperlinkDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractHyperlinkDescription_Actions() {
        return (EReference) abstractHyperlinkDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractHyperlinkDescription_Extends() {
        return (EReference) abstractHyperlinkDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractHyperlinkDescription_FilterConditionalStylesFromExtendedHyperlinkExpression() {
        return (EAttribute) abstractHyperlinkDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractHyperlinkDescription_FilterActionsFromExtendedHyperlinkExpression() {
        return (EAttribute) abstractHyperlinkDescriptionEClass.getEStructuralFeatures().get(8);
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
    public EClass getHyperlinkOverrideDescription() {
        return hyperlinkOverrideDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getHyperlinkOverrideDescription_Overrides() {
        return (EReference) hyperlinkOverrideDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getHyperlinkOverrideDescription_FilterConditionalStylesFromOverriddenHyperlinkExpression() {
        return (EAttribute) hyperlinkOverrideDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getHyperlinkOverrideDescription_FilterActionsFromOverriddenHyperlinkExpression() {
        return (EAttribute) hyperlinkOverrideDescriptionEClass.getEStructuralFeatures().get(2);
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
    public EAttribute getWidgetAction_ImageExpression() {
        return (EAttribute) widgetActionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getWidgetAction_InitialOperation() {
        return (EReference) widgetActionEClass.getEStructuralFeatures().get(2);
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
    public EClass getDialogModelOperation() {
        return dialogModelOperationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDialogModelOperation_TitleExpression() {
        return (EAttribute) dialogModelOperationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDialogModelOperation_Buttons() {
        return (EReference) dialogModelOperationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDialogModelOperation_Page() {
        return (EReference) dialogModelOperationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDialogModelOperation_Groups() {
        return (EReference) dialogModelOperationEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDialogButton() {
        return dialogButtonEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDialogButton_LabelExpression() {
        return (EAttribute) dialogButtonEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDialogButton_IsEnabledExpression() {
        return (EAttribute) dialogButtonEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDialogButton_InitialOperation() {
        return (EReference) dialogButtonEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDialogButton_Default() {
        return (EAttribute) dialogButtonEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDialogButton_CloseDialogOnClick() {
        return (EAttribute) dialogButtonEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDialogButton_RollbackChangesOnClose() {
        return (EAttribute) dialogButtonEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getWizardModelOperation() {
        return wizardModelOperationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getWizardModelOperation_WindowTitleExpression() {
        return (EAttribute) wizardModelOperationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getWizardModelOperation_TitleExpression() {
        return (EAttribute) wizardModelOperationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getWizardModelOperation_DescriptionExpression() {
        return (EAttribute) wizardModelOperationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getWizardModelOperation_IsPageCompleteExpression() {
        return (EAttribute) wizardModelOperationEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getWizardModelOperation_Pages() {
        return (EReference) wizardModelOperationEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getWizardModelOperation_Groups() {
        return (EReference) wizardModelOperationEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getWizardModelOperation_InitialOperation() {
        return (EReference) wizardModelOperationEClass.getEStructuralFeatures().get(6);
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
     * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but
     * its first. <!-- begin-user-doc --> <!-- end-user-doc -->
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
        createEReference(viewExtensionDescriptionEClass, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__METAMODELS);
        createEReference(viewExtensionDescriptionEClass, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__CATEGORIES);

        categoryEClass = createEClass(PropertiesPackage.CATEGORY);
        createEReference(categoryEClass, PropertiesPackage.CATEGORY__PAGES);
        createEReference(categoryEClass, PropertiesPackage.CATEGORY__GROUPS);
        createEReference(categoryEClass, PropertiesPackage.CATEGORY__OVERRIDES);

        abstractOverrideDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_OVERRIDE_DESCRIPTION);

        abstractPageDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION);
        createEAttribute(abstractPageDescriptionEClass, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__LABEL_EXPRESSION);
        createEAttribute(abstractPageDescriptionEClass, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__DOMAIN_CLASS);
        createEAttribute(abstractPageDescriptionEClass, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION);
        createEAttribute(abstractPageDescriptionEClass, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__PRECONDITION_EXPRESSION);
        createEReference(abstractPageDescriptionEClass, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__GROUPS);
        createEReference(abstractPageDescriptionEClass, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__VALIDATION_SET);
        createEReference(abstractPageDescriptionEClass, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__ACTIONS);
        createEReference(abstractPageDescriptionEClass, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__EXTENDS);
        createEAttribute(abstractPageDescriptionEClass, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__FILTER_GROUPS_FROM_EXTENDED_PAGE_EXPRESSION);
        createEAttribute(abstractPageDescriptionEClass, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_PAGE_EXPRESSION);
        createEAttribute(abstractPageDescriptionEClass, PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__INDENTED);

        pageDescriptionEClass = createEClass(PropertiesPackage.PAGE_DESCRIPTION);

        pageOverrideDescriptionEClass = createEClass(PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION);
        createEReference(pageOverrideDescriptionEClass, PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__OVERRIDES);
        createEAttribute(pageOverrideDescriptionEClass, PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__FILTER_GROUPS_FROM_OVERRIDDEN_PAGE_EXPRESSION);
        createEAttribute(pageOverrideDescriptionEClass, PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_PAGE_EXPRESSION);

        pageValidationSetDescriptionEClass = createEClass(PropertiesPackage.PAGE_VALIDATION_SET_DESCRIPTION);
        createEReference(pageValidationSetDescriptionEClass, PropertiesPackage.PAGE_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES);

        propertyValidationRuleEClass = createEClass(PropertiesPackage.PROPERTY_VALIDATION_RULE);
        createEReference(propertyValidationRuleEClass, PropertiesPackage.PROPERTY_VALIDATION_RULE__TARGETS);

        abstractGroupDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION);
        createEAttribute(abstractGroupDescriptionEClass, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__LABEL_EXPRESSION);
        createEAttribute(abstractGroupDescriptionEClass, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOMAIN_CLASS);
        createEAttribute(abstractGroupDescriptionEClass, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION);
        createEAttribute(abstractGroupDescriptionEClass, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__PRECONDITION_EXPRESSION);
        createEReference(abstractGroupDescriptionEClass, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONTROLS);
        createEReference(abstractGroupDescriptionEClass, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET);
        createEReference(abstractGroupDescriptionEClass, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__STYLE);
        createEReference(abstractGroupDescriptionEClass, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONDITIONAL_STYLES);
        createEReference(abstractGroupDescriptionEClass, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__EXTENDS);
        createEAttribute(abstractGroupDescriptionEClass, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION);
        createEAttribute(abstractGroupDescriptionEClass, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION);
        createEAttribute(abstractGroupDescriptionEClass, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION);
        createEReference(abstractGroupDescriptionEClass, PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__ACTIONS);

        toolbarActionEClass = createEClass(PropertiesPackage.TOOLBAR_ACTION);
        createEAttribute(toolbarActionEClass, PropertiesPackage.TOOLBAR_ACTION__TOOLTIP_EXPRESSION);
        createEAttribute(toolbarActionEClass, PropertiesPackage.TOOLBAR_ACTION__IMAGE_EXPRESSION);
        createEReference(toolbarActionEClass, PropertiesPackage.TOOLBAR_ACTION__INITIAL_OPERATION);

        groupDescriptionEClass = createEClass(PropertiesPackage.GROUP_DESCRIPTION);

        groupOverrideDescriptionEClass = createEClass(PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION);
        createEReference(groupOverrideDescriptionEClass, PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__OVERRIDES);
        createEAttribute(groupOverrideDescriptionEClass, PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_OVERRIDDEN_GROUP_EXPRESSION);
        createEAttribute(groupOverrideDescriptionEClass, PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_GROUP_EXPRESSION);
        createEAttribute(groupOverrideDescriptionEClass, PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_GROUP_EXPRESSION);

        groupValidationSetDescriptionEClass = createEClass(PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION);
        createEReference(groupValidationSetDescriptionEClass, PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES);
        createEReference(groupValidationSetDescriptionEClass, PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION__PROPERTY_VALIDATION_RULES);

        abstractControlDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION);

        controlDescriptionEClass = createEClass(PropertiesPackage.CONTROL_DESCRIPTION);

        abstractContainerDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION);
        createEReference(abstractContainerDescriptionEClass, PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__CONTROLS);
        createEReference(abstractContainerDescriptionEClass, PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__LAYOUT);
        createEReference(abstractContainerDescriptionEClass, PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__EXTENDS);
        createEAttribute(abstractContainerDescriptionEClass, PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION);

        containerDescriptionEClass = createEClass(PropertiesPackage.CONTAINER_DESCRIPTION);

        containerOverrideDescriptionEClass = createEClass(PropertiesPackage.CONTAINER_OVERRIDE_DESCRIPTION);
        createEReference(containerOverrideDescriptionEClass, PropertiesPackage.CONTAINER_OVERRIDE_DESCRIPTION__OVERRIDES);
        createEAttribute(containerOverrideDescriptionEClass, PropertiesPackage.CONTAINER_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_OVERRIDDEN_CONTAINER_EXPRESSION);

        layoutDescriptionEClass = createEClass(PropertiesPackage.LAYOUT_DESCRIPTION);

        fillLayoutDescriptionEClass = createEClass(PropertiesPackage.FILL_LAYOUT_DESCRIPTION);
        createEAttribute(fillLayoutDescriptionEClass, PropertiesPackage.FILL_LAYOUT_DESCRIPTION__ORIENTATION);

        gridLayoutDescriptionEClass = createEClass(PropertiesPackage.GRID_LAYOUT_DESCRIPTION);
        createEAttribute(gridLayoutDescriptionEClass, PropertiesPackage.GRID_LAYOUT_DESCRIPTION__NUMBER_OF_COLUMNS);
        createEAttribute(gridLayoutDescriptionEClass, PropertiesPackage.GRID_LAYOUT_DESCRIPTION__MAKE_COLUMNS_WITH_EQUAL_WIDTH);

        abstractWidgetDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION);
        createEAttribute(abstractWidgetDescriptionEClass, PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION);
        createEAttribute(abstractWidgetDescriptionEClass, PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION);
        createEAttribute(abstractWidgetDescriptionEClass, PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION);

        widgetDescriptionEClass = createEClass(PropertiesPackage.WIDGET_DESCRIPTION);

        abstractTextDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION);
        createEAttribute(abstractTextDescriptionEClass, PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__VALUE_EXPRESSION);
        createEReference(abstractTextDescriptionEClass, PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__INITIAL_OPERATION);
        createEReference(abstractTextDescriptionEClass, PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__STYLE);
        createEReference(abstractTextDescriptionEClass, PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__CONDITIONAL_STYLES);
        createEReference(abstractTextDescriptionEClass, PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__EXTENDS);
        createEAttribute(abstractTextDescriptionEClass, PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_EXPRESSION);

        textDescriptionEClass = createEClass(PropertiesPackage.TEXT_DESCRIPTION);

        textOverrideDescriptionEClass = createEClass(PropertiesPackage.TEXT_OVERRIDE_DESCRIPTION);
        createEReference(textOverrideDescriptionEClass, PropertiesPackage.TEXT_OVERRIDE_DESCRIPTION__OVERRIDES);
        createEAttribute(textOverrideDescriptionEClass, PropertiesPackage.TEXT_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_EXPRESSION);

        abstractButtonDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION);
        createEAttribute(abstractButtonDescriptionEClass, PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION);
        createEAttribute(abstractButtonDescriptionEClass, PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__IMAGE_EXPRESSION);
        createEReference(abstractButtonDescriptionEClass, PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__INITIAL_OPERATION);
        createEReference(abstractButtonDescriptionEClass, PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__STYLE);
        createEReference(abstractButtonDescriptionEClass, PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__CONDITIONAL_STYLES);
        createEReference(abstractButtonDescriptionEClass, PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__EXTENDS);
        createEAttribute(abstractButtonDescriptionEClass, PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION);

        buttonDescriptionEClass = createEClass(PropertiesPackage.BUTTON_DESCRIPTION);

        buttonOverrideDescriptionEClass = createEClass(PropertiesPackage.BUTTON_OVERRIDE_DESCRIPTION);
        createEReference(buttonOverrideDescriptionEClass, PropertiesPackage.BUTTON_OVERRIDE_DESCRIPTION__OVERRIDES);
        createEAttribute(buttonOverrideDescriptionEClass, PropertiesPackage.BUTTON_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_BUTTON_EXPRESSION);

        abstractLabelDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION);
        createEAttribute(abstractLabelDescriptionEClass, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__VALUE_EXPRESSION);
        createEAttribute(abstractLabelDescriptionEClass, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__DISPLAY_EXPRESSION);
        createEReference(abstractLabelDescriptionEClass, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__STYLE);
        createEReference(abstractLabelDescriptionEClass, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__CONDITIONAL_STYLES);
        createEReference(abstractLabelDescriptionEClass, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__ACTIONS);
        createEReference(abstractLabelDescriptionEClass, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__EXTENDS);
        createEAttribute(abstractLabelDescriptionEClass, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION);
        createEAttribute(abstractLabelDescriptionEClass, PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION);

        labelDescriptionEClass = createEClass(PropertiesPackage.LABEL_DESCRIPTION);

        labelOverrideDescriptionEClass = createEClass(PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION);
        createEReference(labelOverrideDescriptionEClass, PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__OVERRIDES);
        createEAttribute(labelOverrideDescriptionEClass, PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LABEL_EXPRESSION);
        createEAttribute(labelOverrideDescriptionEClass, PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_LABEL_EXPRESSION);

        abstractCheckboxDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION);
        createEAttribute(abstractCheckboxDescriptionEClass, PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__VALUE_EXPRESSION);
        createEReference(abstractCheckboxDescriptionEClass, PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__INITIAL_OPERATION);
        createEReference(abstractCheckboxDescriptionEClass, PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__STYLE);
        createEReference(abstractCheckboxDescriptionEClass, PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES);
        createEReference(abstractCheckboxDescriptionEClass, PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__EXTENDS);
        createEAttribute(abstractCheckboxDescriptionEClass, PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION);

        checkboxDescriptionEClass = createEClass(PropertiesPackage.CHECKBOX_DESCRIPTION);

        checkboxOverrideDescriptionEClass = createEClass(PropertiesPackage.CHECKBOX_OVERRIDE_DESCRIPTION);
        createEReference(checkboxOverrideDescriptionEClass, PropertiesPackage.CHECKBOX_OVERRIDE_DESCRIPTION__OVERRIDES);
        createEAttribute(checkboxOverrideDescriptionEClass, PropertiesPackage.CHECKBOX_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_CHECKBOX_EXPRESSION);

        abstractSelectDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION);
        createEAttribute(abstractSelectDescriptionEClass, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__VALUE_EXPRESSION);
        createEReference(abstractSelectDescriptionEClass, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(abstractSelectDescriptionEClass, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATES_EXPRESSION);
        createEAttribute(abstractSelectDescriptionEClass, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION);
        createEReference(abstractSelectDescriptionEClass, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__STYLE);
        createEReference(abstractSelectDescriptionEClass, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CONDITIONAL_STYLES);
        createEReference(abstractSelectDescriptionEClass, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__EXTENDS);
        createEAttribute(abstractSelectDescriptionEClass, PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION);

        selectDescriptionEClass = createEClass(PropertiesPackage.SELECT_DESCRIPTION);

        selectOverrideDescriptionEClass = createEClass(PropertiesPackage.SELECT_OVERRIDE_DESCRIPTION);
        createEReference(selectOverrideDescriptionEClass, PropertiesPackage.SELECT_OVERRIDE_DESCRIPTION__OVERRIDES);
        createEAttribute(selectOverrideDescriptionEClass, PropertiesPackage.SELECT_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_SELECT_EXPRESSION);

        abstractDynamicMappingForDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION);
        createEAttribute(abstractDynamicMappingForDescriptionEClass, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERATOR);
        createEAttribute(abstractDynamicMappingForDescriptionEClass, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERABLE_EXPRESSION);
        createEAttribute(abstractDynamicMappingForDescriptionEClass, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FORCE_REFRESH);
        createEReference(abstractDynamicMappingForDescriptionEClass, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__IFS);
        createEReference(abstractDynamicMappingForDescriptionEClass, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__EXTENDS);
        createEAttribute(abstractDynamicMappingForDescriptionEClass, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FILTER_IFS_FROM_EXTENDED_DYNAMIC_MAPPING_FOR_EXPRESSION);

        dynamicMappingForDescriptionEClass = createEClass(PropertiesPackage.DYNAMIC_MAPPING_FOR_DESCRIPTION);

        dynamicMappingForOverrideDescriptionEClass = createEClass(PropertiesPackage.DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION);
        createEReference(dynamicMappingForOverrideDescriptionEClass, PropertiesPackage.DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__OVERRIDES);
        createEAttribute(dynamicMappingForOverrideDescriptionEClass, PropertiesPackage.DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__FILTER_IFS_FROM_OVERRIDDEN_DYNAMIC_MAPPING_FOR_EXPRESSION);

        abstractDynamicMappingIfDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION);
        createEAttribute(abstractDynamicMappingIfDescriptionEClass, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__PREDICATE_EXPRESSION);
        createEReference(abstractDynamicMappingIfDescriptionEClass, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET);
        createEReference(abstractDynamicMappingIfDescriptionEClass, PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__EXTENDS);

        dynamicMappingIfDescriptionEClass = createEClass(PropertiesPackage.DYNAMIC_MAPPING_IF_DESCRIPTION);

        dynamicMappingIfOverrideDescriptionEClass = createEClass(PropertiesPackage.DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION);
        createEReference(dynamicMappingIfOverrideDescriptionEClass, PropertiesPackage.DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION__OVERRIDES);

        abstractTextAreaDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION);
        createEAttribute(abstractTextAreaDescriptionEClass, PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__LINE_COUNT);
        createEAttribute(abstractTextAreaDescriptionEClass, PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__VALUE_EXPRESSION);
        createEReference(abstractTextAreaDescriptionEClass, PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__INITIAL_OPERATION);
        createEReference(abstractTextAreaDescriptionEClass, PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__STYLE);
        createEReference(abstractTextAreaDescriptionEClass, PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__CONDITIONAL_STYLES);
        createEReference(abstractTextAreaDescriptionEClass, PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__EXTENDS);
        createEAttribute(abstractTextAreaDescriptionEClass, PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_AREA_EXPRESSION);

        textAreaDescriptionEClass = createEClass(PropertiesPackage.TEXT_AREA_DESCRIPTION);

        textAreaOverrideDescriptionEClass = createEClass(PropertiesPackage.TEXT_AREA_OVERRIDE_DESCRIPTION);
        createEReference(textAreaOverrideDescriptionEClass, PropertiesPackage.TEXT_AREA_OVERRIDE_DESCRIPTION__OVERRIDES);
        createEAttribute(textAreaOverrideDescriptionEClass, PropertiesPackage.TEXT_AREA_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_AREA_EXPRESSION);

        abstractRadioDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION);
        createEAttribute(abstractRadioDescriptionEClass, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__VALUE_EXPRESSION);
        createEReference(abstractRadioDescriptionEClass, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(abstractRadioDescriptionEClass, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CANDIDATES_EXPRESSION);
        createEAttribute(abstractRadioDescriptionEClass, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION);
        createEReference(abstractRadioDescriptionEClass, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__STYLE);
        createEAttribute(abstractRadioDescriptionEClass, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__NUMBER_OF_COLUMNS);
        createEReference(abstractRadioDescriptionEClass, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CONDITIONAL_STYLES);
        createEReference(abstractRadioDescriptionEClass, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__EXTENDS);
        createEAttribute(abstractRadioDescriptionEClass, PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_RADIO_EXPRESSION);

        radioDescriptionEClass = createEClass(PropertiesPackage.RADIO_DESCRIPTION);

        radioOverrideDescriptionEClass = createEClass(PropertiesPackage.RADIO_OVERRIDE_DESCRIPTION);
        createEReference(radioOverrideDescriptionEClass, PropertiesPackage.RADIO_OVERRIDE_DESCRIPTION__OVERRIDES);
        createEAttribute(radioOverrideDescriptionEClass, PropertiesPackage.RADIO_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_RADIO_EXPRESSION);

        abstractListDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_LIST_DESCRIPTION);
        createEAttribute(abstractListDescriptionEClass, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__VALUE_EXPRESSION);
        createEAttribute(abstractListDescriptionEClass, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__DISPLAY_EXPRESSION);
        createEReference(abstractListDescriptionEClass, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ON_CLICK_OPERATION);
        createEReference(abstractListDescriptionEClass, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ACTIONS);
        createEReference(abstractListDescriptionEClass, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__STYLE);
        createEReference(abstractListDescriptionEClass, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__CONDITIONAL_STYLES);
        createEReference(abstractListDescriptionEClass, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__EXTENDS);
        createEAttribute(abstractListDescriptionEClass, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION);
        createEAttribute(abstractListDescriptionEClass, PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION);

        listDescriptionEClass = createEClass(PropertiesPackage.LIST_DESCRIPTION);

        listOverrideDescriptionEClass = createEClass(PropertiesPackage.LIST_OVERRIDE_DESCRIPTION);
        createEReference(listOverrideDescriptionEClass, PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__OVERRIDES);
        createEAttribute(listOverrideDescriptionEClass, PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LIST_EXPRESSION);
        createEAttribute(listOverrideDescriptionEClass, PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_LIST_EXPRESSION);

        operationDescriptionEClass = createEClass(PropertiesPackage.OPERATION_DESCRIPTION);
        createEReference(operationDescriptionEClass, PropertiesPackage.OPERATION_DESCRIPTION__INITIAL_OPERATION);

        abstractCustomDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION);
        createEReference(abstractCustomDescriptionEClass, PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS);
        createEReference(abstractCustomDescriptionEClass, PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS);
        createEReference(abstractCustomDescriptionEClass, PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__STYLE);
        createEReference(abstractCustomDescriptionEClass, PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CONDITIONAL_STYLES);
        createEReference(abstractCustomDescriptionEClass, PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__EXTENDS);
        createEAttribute(abstractCustomDescriptionEClass, PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CUSTOM_EXPRESSION);

        customDescriptionEClass = createEClass(PropertiesPackage.CUSTOM_DESCRIPTION);

        customOverrideDescriptionEClass = createEClass(PropertiesPackage.CUSTOM_OVERRIDE_DESCRIPTION);
        createEReference(customOverrideDescriptionEClass, PropertiesPackage.CUSTOM_OVERRIDE_DESCRIPTION__OVERRIDES);
        createEAttribute(customOverrideDescriptionEClass, PropertiesPackage.CUSTOM_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_CUSTOM_EXPRESSION);

        customExpressionEClass = createEClass(PropertiesPackage.CUSTOM_EXPRESSION);
        createEAttribute(customExpressionEClass, PropertiesPackage.CUSTOM_EXPRESSION__CUSTOM_EXPRESSION);

        customOperationEClass = createEClass(PropertiesPackage.CUSTOM_OPERATION);
        createEReference(customOperationEClass, PropertiesPackage.CUSTOM_OPERATION__INITIAL_OPERATION);

        abstractHyperlinkDescriptionEClass = createEClass(PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION);
        createEAttribute(abstractHyperlinkDescriptionEClass, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__VALUE_EXPRESSION);
        createEAttribute(abstractHyperlinkDescriptionEClass, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__DISPLAY_EXPRESSION);
        createEReference(abstractHyperlinkDescriptionEClass, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__INITIAL_OPERATION);
        createEReference(abstractHyperlinkDescriptionEClass, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__STYLE);
        createEReference(abstractHyperlinkDescriptionEClass, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__CONDITIONAL_STYLES);
        createEReference(abstractHyperlinkDescriptionEClass, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__ACTIONS);
        createEReference(abstractHyperlinkDescriptionEClass, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__EXTENDS);
        createEAttribute(abstractHyperlinkDescriptionEClass, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION);
        createEAttribute(abstractHyperlinkDescriptionEClass, PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION);

        hyperlinkDescriptionEClass = createEClass(PropertiesPackage.HYPERLINK_DESCRIPTION);

        hyperlinkOverrideDescriptionEClass = createEClass(PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION);
        createEReference(hyperlinkOverrideDescriptionEClass, PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__OVERRIDES);
        createEAttribute(hyperlinkOverrideDescriptionEClass, PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION);
        createEAttribute(hyperlinkOverrideDescriptionEClass, PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION);

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
        createEAttribute(widgetActionEClass, PropertiesPackage.WIDGET_ACTION__IMAGE_EXPRESSION);
        createEReference(widgetActionEClass, PropertiesPackage.WIDGET_ACTION__INITIAL_OPERATION);

        hyperlinkWidgetConditionalStyleEClass = createEClass(PropertiesPackage.HYPERLINK_WIDGET_CONDITIONAL_STYLE);
        createEReference(hyperlinkWidgetConditionalStyleEClass, PropertiesPackage.HYPERLINK_WIDGET_CONDITIONAL_STYLE__STYLE);

        groupConditionalStyleEClass = createEClass(PropertiesPackage.GROUP_CONDITIONAL_STYLE);
        createEReference(groupConditionalStyleEClass, PropertiesPackage.GROUP_CONDITIONAL_STYLE__STYLE);

        dialogModelOperationEClass = createEClass(PropertiesPackage.DIALOG_MODEL_OPERATION);
        createEAttribute(dialogModelOperationEClass, PropertiesPackage.DIALOG_MODEL_OPERATION__TITLE_EXPRESSION);
        createEReference(dialogModelOperationEClass, PropertiesPackage.DIALOG_MODEL_OPERATION__BUTTONS);
        createEReference(dialogModelOperationEClass, PropertiesPackage.DIALOG_MODEL_OPERATION__PAGE);
        createEReference(dialogModelOperationEClass, PropertiesPackage.DIALOG_MODEL_OPERATION__GROUPS);

        dialogButtonEClass = createEClass(PropertiesPackage.DIALOG_BUTTON);
        createEAttribute(dialogButtonEClass, PropertiesPackage.DIALOG_BUTTON__LABEL_EXPRESSION);
        createEAttribute(dialogButtonEClass, PropertiesPackage.DIALOG_BUTTON__IS_ENABLED_EXPRESSION);
        createEReference(dialogButtonEClass, PropertiesPackage.DIALOG_BUTTON__INITIAL_OPERATION);
        createEAttribute(dialogButtonEClass, PropertiesPackage.DIALOG_BUTTON__DEFAULT);
        createEAttribute(dialogButtonEClass, PropertiesPackage.DIALOG_BUTTON__CLOSE_DIALOG_ON_CLICK);
        createEAttribute(dialogButtonEClass, PropertiesPackage.DIALOG_BUTTON__ROLLBACK_CHANGES_ON_CLOSE);

        wizardModelOperationEClass = createEClass(PropertiesPackage.WIZARD_MODEL_OPERATION);
        createEAttribute(wizardModelOperationEClass, PropertiesPackage.WIZARD_MODEL_OPERATION__WINDOW_TITLE_EXPRESSION);
        createEAttribute(wizardModelOperationEClass, PropertiesPackage.WIZARD_MODEL_OPERATION__TITLE_EXPRESSION);
        createEAttribute(wizardModelOperationEClass, PropertiesPackage.WIZARD_MODEL_OPERATION__DESCRIPTION_EXPRESSION);
        createEAttribute(wizardModelOperationEClass, PropertiesPackage.WIZARD_MODEL_OPERATION__IS_PAGE_COMPLETE_EXPRESSION);
        createEReference(wizardModelOperationEClass, PropertiesPackage.WIZARD_MODEL_OPERATION__PAGES);
        createEReference(wizardModelOperationEClass, PropertiesPackage.WIZARD_MODEL_OPERATION__GROUPS);
        createEReference(wizardModelOperationEClass, PropertiesPackage.WIZARD_MODEL_OPERATION__INITIAL_OPERATION);

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
     * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
     * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
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
        viewExtensionDescriptionEClass.getESuperTypes().add(theDescriptionPackage.getIdentifiedElement());
        viewExtensionDescriptionEClass.getESuperTypes().add(theDescriptionPackage.getDocumentedElement());
        categoryEClass.getESuperTypes().add(theDescriptionPackage.getIdentifiedElement());
        categoryEClass.getESuperTypes().add(theDescriptionPackage.getDocumentedElement());
        abstractPageDescriptionEClass.getESuperTypes().add(theDescriptionPackage.getIdentifiedElement());
        abstractPageDescriptionEClass.getESuperTypes().add(theDescriptionPackage.getDocumentedElement());
        pageDescriptionEClass.getESuperTypes().add(this.getAbstractPageDescription());
        pageOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractPageDescription());
        pageOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractOverrideDescription());
        propertyValidationRuleEClass.getESuperTypes().add(theValidationPackage.getValidationRule());
        abstractGroupDescriptionEClass.getESuperTypes().add(theDescriptionPackage.getIdentifiedElement());
        abstractGroupDescriptionEClass.getESuperTypes().add(theDescriptionPackage.getDocumentedElement());
        groupDescriptionEClass.getESuperTypes().add(this.getAbstractGroupDescription());
        groupOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractGroupDescription());
        groupOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractOverrideDescription());
        abstractControlDescriptionEClass.getESuperTypes().add(theDescriptionPackage.getIdentifiedElement());
        abstractControlDescriptionEClass.getESuperTypes().add(theDescriptionPackage.getDocumentedElement());
        controlDescriptionEClass.getESuperTypes().add(this.getAbstractControlDescription());
        abstractContainerDescriptionEClass.getESuperTypes().add(this.getAbstractControlDescription());
        containerDescriptionEClass.getESuperTypes().add(this.getControlDescription());
        containerDescriptionEClass.getESuperTypes().add(this.getAbstractContainerDescription());
        containerOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractContainerDescription());
        containerOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractOverrideDescription());
        fillLayoutDescriptionEClass.getESuperTypes().add(this.getLayoutDescription());
        gridLayoutDescriptionEClass.getESuperTypes().add(this.getLayoutDescription());
        abstractWidgetDescriptionEClass.getESuperTypes().add(this.getAbstractControlDescription());
        widgetDescriptionEClass.getESuperTypes().add(this.getControlDescription());
        abstractTextDescriptionEClass.getESuperTypes().add(this.getAbstractWidgetDescription());
        textDescriptionEClass.getESuperTypes().add(this.getAbstractTextDescription());
        textDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        textOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractTextDescription());
        textOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractOverrideDescription());
        abstractButtonDescriptionEClass.getESuperTypes().add(this.getAbstractWidgetDescription());
        buttonDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        buttonDescriptionEClass.getESuperTypes().add(this.getAbstractButtonDescription());
        buttonOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractButtonDescription());
        buttonOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractOverrideDescription());
        abstractLabelDescriptionEClass.getESuperTypes().add(this.getAbstractWidgetDescription());
        labelDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        labelDescriptionEClass.getESuperTypes().add(this.getAbstractLabelDescription());
        labelOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractLabelDescription());
        labelOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractOverrideDescription());
        abstractCheckboxDescriptionEClass.getESuperTypes().add(this.getAbstractWidgetDescription());
        checkboxDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        checkboxDescriptionEClass.getESuperTypes().add(this.getAbstractCheckboxDescription());
        checkboxOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractCheckboxDescription());
        checkboxOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractOverrideDescription());
        abstractSelectDescriptionEClass.getESuperTypes().add(this.getAbstractWidgetDescription());
        selectDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        selectDescriptionEClass.getESuperTypes().add(this.getAbstractSelectDescription());
        selectOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractSelectDescription());
        selectOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractOverrideDescription());
        abstractDynamicMappingForDescriptionEClass.getESuperTypes().add(this.getAbstractControlDescription());
        dynamicMappingForDescriptionEClass.getESuperTypes().add(this.getControlDescription());
        dynamicMappingForDescriptionEClass.getESuperTypes().add(this.getAbstractDynamicMappingForDescription());
        dynamicMappingForOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractDynamicMappingForDescription());
        dynamicMappingForOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractOverrideDescription());
        abstractDynamicMappingIfDescriptionEClass.getESuperTypes().add(theDescriptionPackage.getIdentifiedElement());
        dynamicMappingIfDescriptionEClass.getESuperTypes().add(this.getAbstractDynamicMappingIfDescription());
        dynamicMappingIfOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractDynamicMappingIfDescription());
        dynamicMappingIfOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractOverrideDescription());
        abstractTextAreaDescriptionEClass.getESuperTypes().add(this.getAbstractWidgetDescription());
        textAreaDescriptionEClass.getESuperTypes().add(this.getAbstractTextAreaDescription());
        textAreaDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        textAreaOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractTextAreaDescription());
        textAreaOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractOverrideDescription());
        abstractRadioDescriptionEClass.getESuperTypes().add(this.getAbstractWidgetDescription());
        radioDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        radioDescriptionEClass.getESuperTypes().add(this.getAbstractRadioDescription());
        radioOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractRadioDescription());
        radioOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractOverrideDescription());
        abstractListDescriptionEClass.getESuperTypes().add(this.getAbstractWidgetDescription());
        listDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        listDescriptionEClass.getESuperTypes().add(this.getAbstractListDescription());
        listOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractListDescription());
        listOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractOverrideDescription());
        abstractCustomDescriptionEClass.getESuperTypes().add(this.getAbstractWidgetDescription());
        customDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        customDescriptionEClass.getESuperTypes().add(this.getAbstractCustomDescription());
        customOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractCustomDescription());
        customOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractOverrideDescription());
        customExpressionEClass.getESuperTypes().add(theDescriptionPackage.getIdentifiedElement());
        customExpressionEClass.getESuperTypes().add(theDescriptionPackage.getDocumentedElement());
        customOperationEClass.getESuperTypes().add(theDescriptionPackage.getIdentifiedElement());
        customOperationEClass.getESuperTypes().add(theDescriptionPackage.getDocumentedElement());
        abstractHyperlinkDescriptionEClass.getESuperTypes().add(this.getAbstractWidgetDescription());
        hyperlinkDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        hyperlinkDescriptionEClass.getESuperTypes().add(this.getAbstractHyperlinkDescription());
        hyperlinkOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractHyperlinkDescription());
        hyperlinkOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractOverrideDescription());
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
        dialogModelOperationEClass.getESuperTypes().add(theToolPackage.getModelOperation());
        wizardModelOperationEClass.getESuperTypes().add(theToolPackage.getModelOperation());

        // Initialize classes and features; add operations and parameters
        initEClass(viewExtensionDescriptionEClass, ViewExtensionDescription.class, "ViewExtensionDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getViewExtensionDescription_Metamodels(), theEcorePackage.getEPackage(), null, "metamodels", null, 0, -1, ViewExtensionDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getViewExtensionDescription_Categories(), this.getCategory(), null, "categories", null, 0, -1, ViewExtensionDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(categoryEClass, Category.class, "Category", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getCategory_Pages(), this.getPageDescription(), null, "pages", null, 0, -1, Category.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getCategory_Groups(), this.getGroupDescription(), null, "groups", null, 0, -1, Category.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getCategory_Overrides(), this.getAbstractOverrideDescription(), null, "overrides", null, 0, -1, Category.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(abstractOverrideDescriptionEClass, AbstractOverrideDescription.class, "AbstractOverrideDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(abstractPageDescriptionEClass, AbstractPageDescription.class, "AbstractPageDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractPageDescription_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", null, 0, 1, AbstractPageDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractPageDescription_DomainClass(), theDescriptionPackage.getTypeName(), "domainClass", null, 0, 1, AbstractPageDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractPageDescription_SemanticCandidateExpression(), theDescriptionPackage.getInterpretedExpression(), "semanticCandidateExpression", null, 0, 1, //$NON-NLS-1$
                AbstractPageDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractPageDescription_PreconditionExpression(), theDescriptionPackage.getInterpretedExpression(), "preconditionExpression", null, 0, 1, AbstractPageDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getAbstractPageDescription_Groups(), this.getGroupDescription(), null, "groups", null, 0, -1, AbstractPageDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getAbstractPageDescription_Groups().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEReference(getAbstractPageDescription_ValidationSet(), this.getPageValidationSetDescription(), null, "validationSet", null, 0, 1, AbstractPageDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractPageDescription_Actions(), this.getToolbarAction(), null, "actions", null, 0, -1, AbstractPageDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractPageDescription_Extends(), this.getPageDescription(), null, "extends", null, 0, 1, AbstractPageDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getAbstractPageDescription_Extends().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getAbstractPageDescription_FilterGroupsFromExtendedPageExpression(), theDescriptionPackage.getInterpretedExpression(), "filterGroupsFromExtendedPageExpression", null, 0, 1, //$NON-NLS-1$
                AbstractPageDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractPageDescription_FilterValidationRulesFromExtendedPageExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterValidationRulesFromExtendedPageExpression", null, 0, 1, AbstractPageDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractPageDescription_Indented(), theEcorePackage.getEBoolean(), "indented", "false", 0, 1, AbstractPageDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(pageDescriptionEClass, PageDescription.class, "PageDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(pageOverrideDescriptionEClass, PageOverrideDescription.class, "PageOverrideDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPageOverrideDescription_Overrides(), this.getPageDescription(), null, "overrides", null, 0, 1, PageOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getPageOverrideDescription_FilterGroupsFromOverriddenPageExpression(), theDescriptionPackage.getInterpretedExpression(), "filterGroupsFromOverriddenPageExpression", null, 0, 1, //$NON-NLS-1$
                PageOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getPageOverrideDescription_FilterValidationRulesFromOverriddenPageExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterValidationRulesFromOverriddenPageExpression", null, 0, 1, PageOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(pageValidationSetDescriptionEClass, PageValidationSetDescription.class, "PageValidationSetDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPageValidationSetDescription_SemanticValidationRules(), theValidationPackage.getSemanticValidationRule(), null, "semanticValidationRules", null, 0, -1, //$NON-NLS-1$
                PageValidationSetDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES,
                !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getPageValidationSetDescription_SemanticValidationRules().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());

        initEClass(propertyValidationRuleEClass, PropertyValidationRule.class, "PropertyValidationRule", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPropertyValidationRule_Targets(), this.getWidgetDescription(), null, "targets", null, 0, -1, PropertyValidationRule.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getPropertyValidationRule_Targets().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());

        initEClass(abstractGroupDescriptionEClass, AbstractGroupDescription.class, "AbstractGroupDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractGroupDescription_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", null, 0, 1, AbstractGroupDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractGroupDescription_DomainClass(), theDescriptionPackage.getTypeName(), "domainClass", null, 0, 1, AbstractGroupDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractGroupDescription_SemanticCandidateExpression(), theDescriptionPackage.getInterpretedExpression(), "semanticCandidateExpression", null, 0, 1, //$NON-NLS-1$
                AbstractGroupDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractGroupDescription_PreconditionExpression(), theDescriptionPackage.getInterpretedExpression(), "preconditionExpression", null, 0, 1, AbstractGroupDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getAbstractGroupDescription_Controls(), this.getControlDescription(), null, "controls", null, 0, -1, AbstractGroupDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractGroupDescription_ValidationSet(), this.getGroupValidationSetDescription(), null, "validationSet", null, 0, 1, AbstractGroupDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractGroupDescription_Style(), this.getGroupStyle(), null, "style", null, 0, 1, AbstractGroupDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getAbstractGroupDescription_ConditionalStyles(), this.getGroupConditionalStyle(), null, "conditionalStyles", null, 0, -1, AbstractGroupDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractGroupDescription_Extends(), this.getGroupDescription(), null, "extends", null, 0, 1, AbstractGroupDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getAbstractGroupDescription_Extends().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getAbstractGroupDescription_FilterControlsFromExtendedGroupExpression(), theDescriptionPackage.getInterpretedExpression(), "filterControlsFromExtendedGroupExpression", null, 0, //$NON-NLS-1$
                1, AbstractGroupDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractGroupDescription_FilterValidationRulesFromExtendedGroupExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterValidationRulesFromExtendedGroupExpression", null, 0, 1, AbstractGroupDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractGroupDescription_FilterConditionalStylesFromExtendedGroupExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromExtendedGroupExpression", null, 0, 1, AbstractGroupDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractGroupDescription_Actions(), this.getToolbarAction(), null, "actions", null, 0, -1, AbstractGroupDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(toolbarActionEClass, ToolbarAction.class, "ToolbarAction", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getToolbarAction_TooltipExpression(), theDescriptionPackage.getInterpretedExpression(), "tooltipExpression", null, 0, 1, ToolbarAction.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getToolbarAction_ImageExpression(), theDescriptionPackage.getInterpretedExpression(), "imageExpression", null, 0, 1, ToolbarAction.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getToolbarAction_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, ToolbarAction.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(groupDescriptionEClass, GroupDescription.class, "GroupDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(groupOverrideDescriptionEClass, GroupOverrideDescription.class, "GroupOverrideDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGroupOverrideDescription_Overrides(), this.getGroupDescription(), null, "overrides", null, 0, 1, GroupOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGroupOverrideDescription_FilterControlsFromOverriddenGroupExpression(), theDescriptionPackage.getInterpretedExpression(), "filterControlsFromOverriddenGroupExpression", null, //$NON-NLS-1$
                0, 1, GroupOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGroupOverrideDescription_FilterValidationRulesFromOverriddenGroupExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterValidationRulesFromOverriddenGroupExpression", null, 0, 1, GroupOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGroupOverrideDescription_FilterConditionalStylesFromOverriddenGroupExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromOverriddenGroupExpression", null, 0, 1, GroupOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(groupValidationSetDescriptionEClass, GroupValidationSetDescription.class, "GroupValidationSetDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGroupValidationSetDescription_SemanticValidationRules(), theValidationPackage.getSemanticValidationRule(), null, "semanticValidationRules", null, 0, -1, //$NON-NLS-1$
                GroupValidationSetDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES,
                !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getGroupValidationSetDescription_SemanticValidationRules().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEReference(getGroupValidationSetDescription_PropertyValidationRules(), this.getPropertyValidationRule(), null, "propertyValidationRules", null, 0, -1, GroupValidationSetDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getGroupValidationSetDescription_PropertyValidationRules().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());

        initEClass(abstractControlDescriptionEClass, AbstractControlDescription.class, "AbstractControlDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(controlDescriptionEClass, ControlDescription.class, "ControlDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(abstractContainerDescriptionEClass, AbstractContainerDescription.class, "AbstractContainerDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAbstractContainerDescription_Controls(), this.getControlDescription(), null, "controls", null, 0, -1, AbstractContainerDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractContainerDescription_Layout(), this.getLayoutDescription(), null, "layout", null, 0, 1, AbstractContainerDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractContainerDescription_Extends(), this.getContainerDescription(), null, "extends", null, 0, 1, AbstractContainerDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getAbstractContainerDescription_Extends().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getAbstractContainerDescription_FilterControlsFromExtendedContainerExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterControlsFromExtendedContainerExpression", null, 0, 1, AbstractContainerDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(containerDescriptionEClass, ContainerDescription.class, "ContainerDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(containerOverrideDescriptionEClass, ContainerOverrideDescription.class, "ContainerOverrideDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getContainerOverrideDescription_Overrides(), this.getContainerDescription(), null, "overrides", null, 0, 1, ContainerOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getContainerOverrideDescription_FilterControlsFromOverriddenContainerExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterControlsFromOverriddenContainerExpression", null, 0, 1, ContainerOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

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

        initEClass(abstractWidgetDescriptionEClass, AbstractWidgetDescription.class, "AbstractWidgetDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractWidgetDescription_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", null, 0, 1, AbstractWidgetDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractWidgetDescription_HelpExpression(), theDescriptionPackage.getInterpretedExpression(), "helpExpression", null, 0, 1, AbstractWidgetDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractWidgetDescription_IsEnabledExpression(), theDescriptionPackage.getInterpretedExpression(), "isEnabledExpression", null, 0, 1, AbstractWidgetDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(widgetDescriptionEClass, WidgetDescription.class, "WidgetDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(abstractTextDescriptionEClass, AbstractTextDescription.class, "AbstractTextDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractTextDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, AbstractTextDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getAbstractTextDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, AbstractTextDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractTextDescription_Style(), this.getTextWidgetStyle(), null, "style", null, 0, 1, AbstractTextDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getAbstractTextDescription_ConditionalStyles(), this.getTextWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, AbstractTextDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractTextDescription_Extends(), this.getTextDescription(), null, "extends", null, 0, 1, AbstractTextDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getAbstractTextDescription_Extends().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getAbstractTextDescription_FilterConditionalStylesFromExtendedTextExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromExtendedTextExpression", null, 0, 1, AbstractTextDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(textDescriptionEClass, TextDescription.class, "TextDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(textOverrideDescriptionEClass, TextOverrideDescription.class, "TextOverrideDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTextOverrideDescription_Overrides(), this.getTextDescription(), null, "overrides", null, 0, 1, TextOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getTextOverrideDescription_FilterConditionalStylesFromOverriddenTextExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromOverriddenTextExpression", null, 0, 1, TextOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(abstractButtonDescriptionEClass, AbstractButtonDescription.class, "AbstractButtonDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractButtonDescription_ButtonLabelExpression(), theDescriptionPackage.getInterpretedExpression(), "buttonLabelExpression", null, 0, 1, AbstractButtonDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractButtonDescription_ImageExpression(), theDescriptionPackage.getInterpretedExpression(), "imageExpression", null, 0, 1, AbstractButtonDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getAbstractButtonDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, AbstractButtonDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractButtonDescription_Style(), this.getButtonWidgetStyle(), null, "style", null, 0, 1, AbstractButtonDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractButtonDescription_ConditionalStyles(), this.getButtonWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, AbstractButtonDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractButtonDescription_Extends(), this.getButtonDescription(), null, "extends", null, 0, 1, AbstractButtonDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getAbstractButtonDescription_Extends().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getAbstractButtonDescription_FilterConditionalStylesFromExtendedButtonExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromExtendedButtonExpression", null, 0, 1, AbstractButtonDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(buttonDescriptionEClass, ButtonDescription.class, "ButtonDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(buttonOverrideDescriptionEClass, ButtonOverrideDescription.class, "ButtonOverrideDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getButtonOverrideDescription_Overrides(), this.getButtonDescription(), null, "overrides", null, 0, 1, ButtonOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getButtonOverrideDescription_FilterConditionalStylesFromOverriddenButtonExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromOverriddenButtonExpression", null, 0, 1, ButtonOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(abstractLabelDescriptionEClass, AbstractLabelDescription.class, "AbstractLabelDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractLabelDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, AbstractLabelDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractLabelDescription_DisplayExpression(), theDescriptionPackage.getInterpretedExpression(), "displayExpression", null, 0, 1, AbstractLabelDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getAbstractLabelDescription_Style(), this.getLabelWidgetStyle(), null, "style", null, 0, 1, AbstractLabelDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractLabelDescription_ConditionalStyles(), this.getLabelWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, AbstractLabelDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractLabelDescription_Actions(), this.getWidgetAction(), null, "actions", null, 0, -1, AbstractLabelDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractLabelDescription_Extends(), this.getLabelDescription(), null, "extends", null, 0, 1, AbstractLabelDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getAbstractLabelDescription_Extends().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getAbstractLabelDescription_FilterConditionalStylesFromExtendedLabelExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromExtendedLabelExpression", null, 0, 1, AbstractLabelDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractLabelDescription_FilterActionsFromExtendedLabelExpression(), theDescriptionPackage.getInterpretedExpression(), "filterActionsFromExtendedLabelExpression", null, 0, 1, //$NON-NLS-1$
                AbstractLabelDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(labelDescriptionEClass, LabelDescription.class, "LabelDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(labelOverrideDescriptionEClass, LabelOverrideDescription.class, "LabelOverrideDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLabelOverrideDescription_Overrides(), this.getLabelDescription(), null, "overrides", null, 0, 1, LabelOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getLabelOverrideDescription_FilterConditionalStylesFromOverriddenLabelExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromOverriddenLabelExpression", null, 0, 1, LabelOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getLabelOverrideDescription_FilterActionsFromOverriddenLabelExpression(), theDescriptionPackage.getInterpretedExpression(), "filterActionsFromOverriddenLabelExpression", null, //$NON-NLS-1$
                0, 1, LabelOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(abstractCheckboxDescriptionEClass, AbstractCheckboxDescription.class, "AbstractCheckboxDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractCheckboxDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, AbstractCheckboxDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getAbstractCheckboxDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, AbstractCheckboxDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractCheckboxDescription_Style(), this.getCheckboxWidgetStyle(), null, "style", null, 0, 1, AbstractCheckboxDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractCheckboxDescription_ConditionalStyles(), this.getCheckboxWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, AbstractCheckboxDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractCheckboxDescription_Extends(), this.getCheckboxDescription(), null, "extends", null, 0, 1, AbstractCheckboxDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getAbstractCheckboxDescription_Extends().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getAbstractCheckboxDescription_FilterConditionalStylesFromExtendedCheckboxExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromExtendedCheckboxExpression", null, 0, 1, AbstractCheckboxDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(checkboxDescriptionEClass, CheckboxDescription.class, "CheckboxDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(checkboxOverrideDescriptionEClass, CheckboxOverrideDescription.class, "CheckboxOverrideDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCheckboxOverrideDescription_Overrides(), this.getCheckboxDescription(), null, "overrides", null, 0, 1, CheckboxOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getCheckboxOverrideDescription_FilterConditionalStylesFromOverriddenCheckboxExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromOverriddenCheckboxExpression", null, 0, 1, CheckboxOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(abstractSelectDescriptionEClass, AbstractSelectDescription.class, "AbstractSelectDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractSelectDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, AbstractSelectDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getAbstractSelectDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, AbstractSelectDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractSelectDescription_CandidatesExpression(), theDescriptionPackage.getInterpretedExpression(), "candidatesExpression", null, 0, 1, AbstractSelectDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractSelectDescription_CandidateDisplayExpression(), theDescriptionPackage.getInterpretedExpression(), "candidateDisplayExpression", null, 0, 1, //$NON-NLS-1$
                AbstractSelectDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractSelectDescription_Style(), this.getSelectWidgetStyle(), null, "style", null, 0, 1, AbstractSelectDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractSelectDescription_ConditionalStyles(), this.getSelectWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, AbstractSelectDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractSelectDescription_Extends(), this.getSelectDescription(), null, "extends", null, 0, 1, AbstractSelectDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getAbstractSelectDescription_Extends().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getAbstractSelectDescription_FilterConditionalStylesFromExtendedSelectExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromExtendedSelectExpression", null, 0, 1, AbstractSelectDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(selectDescriptionEClass, SelectDescription.class, "SelectDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(selectOverrideDescriptionEClass, SelectOverrideDescription.class, "SelectOverrideDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSelectOverrideDescription_Overrides(), this.getSelectDescription(), null, "overrides", null, 0, 1, SelectOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getSelectOverrideDescription_Overrides().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getSelectOverrideDescription_FilterConditionalStylesFromOverriddenSelectExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromOverriddenSelectExpression", null, 0, 1, SelectOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(abstractDynamicMappingForDescriptionEClass, AbstractDynamicMappingForDescription.class, "AbstractDynamicMappingForDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractDynamicMappingForDescription_Iterator(), ecorePackage.getEString(), "iterator", null, 1, 1, AbstractDynamicMappingForDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractDynamicMappingForDescription_IterableExpression(), theDescriptionPackage.getInterpretedExpression(), "iterableExpression", null, 1, 1, //$NON-NLS-1$
                AbstractDynamicMappingForDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractDynamicMappingForDescription_ForceRefresh(), theEcorePackage.getEBoolean(), "forceRefresh", null, 1, 1, AbstractDynamicMappingForDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getAbstractDynamicMappingForDescription_Ifs(), this.getDynamicMappingIfDescription(), null, "ifs", null, 1, -1, AbstractDynamicMappingForDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractDynamicMappingForDescription_Extends(), this.getDynamicMappingForDescription(), null, "extends", null, 0, 1, AbstractDynamicMappingForDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getAbstractDynamicMappingForDescription_Extends().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getAbstractDynamicMappingForDescription_FilterIfsFromExtendedDynamicMappingForExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterIfsFromExtendedDynamicMappingForExpression", null, 0, 1, AbstractDynamicMappingForDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dynamicMappingForDescriptionEClass, DynamicMappingForDescription.class, "DynamicMappingForDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(dynamicMappingForOverrideDescriptionEClass, DynamicMappingForOverrideDescription.class, "DynamicMappingForOverrideDescription", !EPackageImpl.IS_ABSTRACT, //$NON-NLS-1$
                !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDynamicMappingForOverrideDescription_Overrides(), this.getDynamicMappingForDescription(), null, "overrides", null, 0, 1, DynamicMappingForOverrideDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getDynamicMappingForOverrideDescription_Overrides().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getDynamicMappingForOverrideDescription_FilterIfsFromOverriddenDynamicMappingForExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterIfsFromOverriddenDynamicMappingForExpression", null, 0, 1, DynamicMappingForOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(abstractDynamicMappingIfDescriptionEClass, AbstractDynamicMappingIfDescription.class, "AbstractDynamicMappingIfDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractDynamicMappingIfDescription_PredicateExpression(), theDescriptionPackage.getInterpretedExpression(), "predicateExpression", null, 1, 1, //$NON-NLS-1$
                AbstractDynamicMappingIfDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractDynamicMappingIfDescription_Widget(), this.getWidgetDescription(), null, "widget", null, 1, 1, AbstractDynamicMappingIfDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractDynamicMappingIfDescription_Extends(), this.getDynamicMappingIfDescription(), null, "extends", null, 0, 1, AbstractDynamicMappingIfDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getAbstractDynamicMappingIfDescription_Extends().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());

        initEClass(dynamicMappingIfDescriptionEClass, DynamicMappingIfDescription.class, "DynamicMappingIfDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(dynamicMappingIfOverrideDescriptionEClass, DynamicMappingIfOverrideDescription.class, "DynamicMappingIfOverrideDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDynamicMappingIfOverrideDescription_Overrides(), this.getDynamicMappingIfDescription(), null, "overrides", null, 0, 1, DynamicMappingIfOverrideDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getDynamicMappingIfOverrideDescription_Overrides().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());

        initEClass(abstractTextAreaDescriptionEClass, AbstractTextAreaDescription.class, "AbstractTextAreaDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractTextAreaDescription_LineCount(), ecorePackage.getEInt(), "lineCount", "5", 0, 1, AbstractTextAreaDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractTextAreaDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, AbstractTextAreaDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getAbstractTextAreaDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, AbstractTextAreaDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractTextAreaDescription_Style(), this.getTextWidgetStyle(), null, "style", null, 0, 1, AbstractTextAreaDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractTextAreaDescription_ConditionalStyles(), this.getTextWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, AbstractTextAreaDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractTextAreaDescription_Extends(), this.getTextAreaDescription(), null, "extends", null, 0, 1, AbstractTextAreaDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getAbstractTextAreaDescription_Extends().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getAbstractTextAreaDescription_FilterConditionalStylesFromExtendedTextAreaExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromExtendedTextAreaExpression", null, 0, 1, AbstractTextAreaDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(textAreaDescriptionEClass, TextAreaDescription.class, "TextAreaDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(textAreaOverrideDescriptionEClass, TextAreaOverrideDescription.class, "TextAreaOverrideDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTextAreaOverrideDescription_Overrides(), this.getTextAreaDescription(), null, "overrides", null, 0, 1, TextAreaOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getTextAreaOverrideDescription_Overrides().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getTextAreaOverrideDescription_FilterConditionalStylesFromOverriddenTextAreaExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromOverriddenTextAreaExpression", null, 0, 1, TextAreaOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(abstractRadioDescriptionEClass, AbstractRadioDescription.class, "AbstractRadioDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractRadioDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, AbstractRadioDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getAbstractRadioDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, AbstractRadioDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractRadioDescription_CandidatesExpression(), theDescriptionPackage.getInterpretedExpression(), "candidatesExpression", null, 0, 1, AbstractRadioDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractRadioDescription_CandidateDisplayExpression(), theDescriptionPackage.getInterpretedExpression(), "candidateDisplayExpression", null, 0, 1, //$NON-NLS-1$
                AbstractRadioDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractRadioDescription_Style(), this.getRadioWidgetStyle(), null, "style", null, 0, 1, AbstractRadioDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractRadioDescription_NumberOfColumns(), ecorePackage.getEInt(), "numberOfColumns", "-1", 0, 1, AbstractRadioDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractRadioDescription_ConditionalStyles(), this.getRadioWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, AbstractRadioDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractRadioDescription_Extends(), this.getRadioDescription(), null, "extends", null, 0, 1, AbstractRadioDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getAbstractRadioDescription_Extends().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getAbstractRadioDescription_FilterConditionalStylesFromExtendedRadioExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromExtendedRadioExpression", null, 0, 1, AbstractRadioDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(radioDescriptionEClass, RadioDescription.class, "RadioDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(radioOverrideDescriptionEClass, RadioOverrideDescription.class, "RadioOverrideDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRadioOverrideDescription_Overrides(), this.getRadioDescription(), null, "overrides", null, 0, 1, RadioOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getRadioOverrideDescription_Overrides().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getRadioOverrideDescription_FilterConditionalStylesFromOverriddenRadioExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromOverriddenRadioExpression", null, 0, 1, RadioOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(abstractListDescriptionEClass, AbstractListDescription.class, "AbstractListDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractListDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, AbstractListDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractListDescription_DisplayExpression(), theDescriptionPackage.getInterpretedExpression(), "displayExpression", null, 0, 1, AbstractListDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getAbstractListDescription_OnClickOperation(), theToolPackage.getInitialOperation(), null, "onClickOperation", null, 0, 1, AbstractListDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractListDescription_Actions(), this.getWidgetAction(), null, "actions", null, 0, -1, AbstractListDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getAbstractListDescription_Style(), this.getListWidgetStyle(), null, "style", null, 0, 1, AbstractListDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getAbstractListDescription_ConditionalStyles(), this.getListWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, AbstractListDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractListDescription_Extends(), this.getListDescription(), null, "extends", null, 0, 1, AbstractListDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getAbstractListDescription_Extends().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getAbstractListDescription_FilterConditionalStylesFromExtendedListExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromExtendedListExpression", null, 0, 1, AbstractListDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractListDescription_FilterActionsFromExtendedListExpression(), theDescriptionPackage.getInterpretedExpression(), "filterActionsFromExtendedListExpression", null, 0, 1, //$NON-NLS-1$
                AbstractListDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(listDescriptionEClass, ListDescription.class, "ListDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(listOverrideDescriptionEClass, ListOverrideDescription.class, "ListOverrideDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getListOverrideDescription_Overrides(), this.getListDescription(), null, "overrides", null, 0, 1, ListOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getListOverrideDescription_Overrides().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getListOverrideDescription_FilterConditionalStylesFromOverriddenListExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromOverriddenListExpression", null, 0, 1, ListOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getListOverrideDescription_FilterActionsFromOverriddenListExpression(), theDescriptionPackage.getInterpretedExpression(), "filterActionsFromOverriddenListExpression", null, 0, //$NON-NLS-1$
                1, ListOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(operationDescriptionEClass, OperationDescription.class, "OperationDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getOperationDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, OperationDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(abstractCustomDescriptionEClass, AbstractCustomDescription.class, "AbstractCustomDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAbstractCustomDescription_CustomExpressions(), this.getCustomExpression(), null, "customExpressions", null, 0, -1, AbstractCustomDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractCustomDescription_CustomOperations(), this.getCustomOperation(), null, "customOperations", null, 0, -1, AbstractCustomDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractCustomDescription_Style(), this.getCustomWidgetStyle(), null, "style", null, 0, 1, AbstractCustomDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractCustomDescription_ConditionalStyles(), this.getCustomWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, AbstractCustomDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractCustomDescription_Extends(), this.getCustomDescription(), null, "extends", null, 0, 1, AbstractCustomDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractCustomDescription_FilterConditionalStylesFromExtendedCustomExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromExtendedCustomExpression", null, 0, 1, AbstractCustomDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(customDescriptionEClass, CustomDescription.class, "CustomDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(customOverrideDescriptionEClass, CustomOverrideDescription.class, "CustomOverrideDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCustomOverrideDescription_Overrides(), this.getCustomDescription(), null, "overrides", null, 0, 1, CustomOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getCustomOverrideDescription_Overrides().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getCustomOverrideDescription_FilterConditionalStylesFromOverriddenCustomExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromOverriddenCustomExpression", null, 0, 1, CustomOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(customExpressionEClass, CustomExpression.class, "CustomExpression", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getCustomExpression_CustomExpression(), theDescriptionPackage.getInterpretedExpression(), "customExpression", null, 0, 1, CustomExpression.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(customOperationEClass, CustomOperation.class, "CustomOperation", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getCustomOperation_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, CustomOperation.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(abstractHyperlinkDescriptionEClass, AbstractHyperlinkDescription.class, "AbstractHyperlinkDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractHyperlinkDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, AbstractHyperlinkDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractHyperlinkDescription_DisplayExpression(), theDescriptionPackage.getInterpretedExpression(), "displayExpression", null, 0, 1, AbstractHyperlinkDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getAbstractHyperlinkDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, AbstractHyperlinkDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractHyperlinkDescription_Style(), this.getHyperlinkWidgetStyle(), null, "style", null, 0, 1, AbstractHyperlinkDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractHyperlinkDescription_ConditionalStyles(), this.getHyperlinkWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, AbstractHyperlinkDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractHyperlinkDescription_Actions(), this.getWidgetAction(), null, "actions", null, 0, -1, AbstractHyperlinkDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractHyperlinkDescription_Extends(), this.getHyperlinkDescription(), null, "extends", null, 0, 1, AbstractHyperlinkDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractHyperlinkDescription_FilterConditionalStylesFromExtendedHyperlinkExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromExtendedHyperlinkExpression", null, 0, 1, AbstractHyperlinkDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractHyperlinkDescription_FilterActionsFromExtendedHyperlinkExpression(), theDescriptionPackage.getInterpretedExpression(), "filterActionsFromExtendedHyperlinkExpression", //$NON-NLS-1$
                null, 0, 1, AbstractHyperlinkDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(hyperlinkDescriptionEClass, HyperlinkDescription.class, "HyperlinkDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(hyperlinkOverrideDescriptionEClass, HyperlinkOverrideDescription.class, "HyperlinkOverrideDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getHyperlinkOverrideDescription_Overrides(), this.getHyperlinkDescription(), null, "overrides", null, 0, 1, HyperlinkOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getHyperlinkOverrideDescription_Overrides().getEKeys().add(theDescriptionPackage.getIdentifiedElement_Name());
        initEAttribute(getHyperlinkOverrideDescription_FilterConditionalStylesFromOverriddenHyperlinkExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromOverriddenHyperlinkExpression", null, 0, 1, HyperlinkOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getHyperlinkOverrideDescription_FilterActionsFromOverriddenHyperlinkExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterActionsFromOverriddenHyperlinkExpression", null, 0, 1, HyperlinkOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

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
        initEAttribute(getWidgetAction_ImageExpression(), theDescriptionPackage.getInterpretedExpression(), "imageExpression", null, 0, 1, WidgetAction.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
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

        initEClass(dialogModelOperationEClass, DialogModelOperation.class, "DialogModelOperation", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDialogModelOperation_TitleExpression(), theDescriptionPackage.getInterpretedExpression(), "titleExpression", null, 0, 1, DialogModelOperation.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDialogModelOperation_Buttons(), this.getDialogButton(), null, "buttons", null, 0, -1, DialogModelOperation.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDialogModelOperation_Page(), this.getPageDescription(), null, "page", null, 1, 1, DialogModelOperation.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDialogModelOperation_Groups(), this.getGroupDescription(), null, "groups", null, 0, -1, DialogModelOperation.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(dialogButtonEClass, DialogButton.class, "DialogButton", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDialogButton_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", null, 0, 1, DialogButton.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDialogButton_IsEnabledExpression(), theDescriptionPackage.getInterpretedExpression(), "isEnabledExpression", null, 0, 1, DialogButton.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDialogButton_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, DialogButton.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDialogButton_Default(), theEcorePackage.getEBoolean(), "default", null, 0, 1, DialogButton.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDialogButton_CloseDialogOnClick(), theEcorePackage.getEBoolean(), "closeDialogOnClick", null, 0, 1, DialogButton.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDialogButton_RollbackChangesOnClose(), theEcorePackage.getEBoolean(), "rollbackChangesOnClose", null, 0, 1, DialogButton.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(wizardModelOperationEClass, WizardModelOperation.class, "WizardModelOperation", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getWizardModelOperation_WindowTitleExpression(), theDescriptionPackage.getInterpretedExpression(), "windowTitleExpression", null, 0, 1, WizardModelOperation.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getWizardModelOperation_TitleExpression(), theDescriptionPackage.getInterpretedExpression(), "titleExpression", null, 0, 1, WizardModelOperation.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getWizardModelOperation_DescriptionExpression(), theDescriptionPackage.getInterpretedExpression(), "descriptionExpression", null, 0, 1, WizardModelOperation.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getWizardModelOperation_IsPageCompleteExpression(), theDescriptionPackage.getInterpretedExpression(), "isPageCompleteExpression", null, 0, 1, WizardModelOperation.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getWizardModelOperation_Pages(), this.getPageDescription(), null, "pages", null, 1, -1, WizardModelOperation.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getWizardModelOperation_Groups(), this.getGroupDescription(), null, "groups", null, 0, -1, WizardModelOperation.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getWizardModelOperation_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, WizardModelOperation.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

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
