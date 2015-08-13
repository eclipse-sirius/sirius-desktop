/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.tool.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.audit.AuditPackage;
import org.eclipse.sirius.viewpoint.description.audit.impl.AuditPackageImpl;
import org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable;
import org.eclipse.sirius.viewpoint.description.tool.Case;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.Default;
import org.eclipse.sirius.viewpoint.description.tool.DeleteView;
import org.eclipse.sirius.viewpoint.description.tool.DialogVariable;
import org.eclipse.sirius.viewpoint.description.tool.DragSource;
import org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;
import org.eclipse.sirius.viewpoint.description.tool.ElementDeleteVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementDropVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementSelectVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter;
import org.eclipse.sirius.viewpoint.description.tool.FeatureChangeListener;
import org.eclipse.sirius.viewpoint.description.tool.For;
import org.eclipse.sirius.viewpoint.description.tool.If;
import org.eclipse.sirius.viewpoint.description.tool.InitEdgeCreationOperation;
import org.eclipse.sirius.viewpoint.description.tool.InitialContainerDropOperation;
import org.eclipse.sirius.viewpoint.description.tool.InitialNodeCreationOperation;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionReference;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemOrRef;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.MoveElement;
import org.eclipse.sirius.viewpoint.description.tool.NameVariable;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.RemoveElement;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.SetObject;
import org.eclipse.sirius.viewpoint.description.tool.SetValue;
import org.eclipse.sirius.viewpoint.description.tool.SubVariable;
import org.eclipse.sirius.viewpoint.description.tool.Switch;
import org.eclipse.sirius.viewpoint.description.tool.SwitchChild;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.Unset;
import org.eclipse.sirius.viewpoint.description.tool.VariableContainer;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;
import org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl;
import org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class ToolPackageImpl extends EPackageImpl implements ToolPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass toolEntryEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass abstractToolDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass mappingBasedToolDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass toolDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass pasteDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass selectionWizardDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass paneBasedSelectionWizardDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass representationCreationDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass representationNavigationDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass menuItemOrRefEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass menuItemDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass menuItemDescriptionReferenceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass operationActionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass externalJavaActionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass externalJavaActionCallEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass popupMenuEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass abstractVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass variableContainerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass acceleoVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass subVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dialogVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass elementDropVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass elementSelectVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass elementVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass elementViewVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass elementDeleteVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dropContainerVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass selectContainerVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass containerViewVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass selectModelElementVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass editMaskVariablesEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass containerModelOperationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass modelOperationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass initialNodeCreationOperationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass initialOperationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass initEdgeCreationOperationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass initialContainerDropOperationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass createInstanceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass changeContextEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass setValueEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass setObjectEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass unsetEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass moveElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass removeElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass forEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass ifEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass deleteViewEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass nameVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass externalJavaActionParameterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass toolFilterDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass featureChangeListenerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass caseEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass switchChildEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass defaultEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass switchEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum dragSourceEEnum = null;

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
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ToolPackageImpl() {
        super(ToolPackage.eNS_URI, ToolFactory.eINSTANCE);
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
     * This method is used to initialize {@link ToolPackage#eINSTANCE} when that
     * field is accessed. Clients should not invoke it directly. Instead, they
     * should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ToolPackage init() {
        if (ToolPackageImpl.isInited) {
            return (ToolPackage) EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);
        }

        // Obtain or create and register package
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (EPackage.Registry.INSTANCE.get(ToolPackage.eNS_URI) instanceof ToolPackageImpl ? EPackage.Registry.INSTANCE.get(ToolPackage.eNS_URI)
                : new ToolPackageImpl());

        ToolPackageImpl.isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        ViewpointPackageImpl theViewpointPackage = (ViewpointPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ViewpointPackage.eNS_URI) instanceof ViewpointPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ViewpointPackage.eNS_URI) : ViewpointPackage.eINSTANCE);
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI) instanceof DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(DescriptionPackage.eNS_URI) : DescriptionPackage.eINSTANCE);
        StylePackageImpl theStylePackage = (StylePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI) instanceof StylePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(StylePackage.eNS_URI) : StylePackage.eINSTANCE);
        ValidationPackageImpl theValidationPackage = (ValidationPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ValidationPackage.eNS_URI) instanceof ValidationPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ValidationPackage.eNS_URI) : ValidationPackage.eINSTANCE);
        AuditPackageImpl theAuditPackage = (AuditPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(AuditPackage.eNS_URI) instanceof AuditPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(AuditPackage.eNS_URI) : AuditPackage.eINSTANCE);

        // Create package meta-data objects
        theToolPackage.createPackageContents();
        theViewpointPackage.createPackageContents();
        theDescriptionPackage.createPackageContents();
        theStylePackage.createPackageContents();
        theValidationPackage.createPackageContents();
        theAuditPackage.createPackageContents();

        // Initialize created meta-data
        theToolPackage.initializePackageContents();
        theViewpointPackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();
        theStylePackage.initializePackageContents();
        theValidationPackage.initializePackageContents();
        theAuditPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theToolPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ToolPackage.eNS_URI, theToolPackage);
        return theToolPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getToolEntry() {
        return toolEntryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getAbstractToolDescription() {
        return abstractToolDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAbstractToolDescription_Precondition() {
        return (EAttribute) abstractToolDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAbstractToolDescription_ForceRefresh() {
        return (EAttribute) abstractToolDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getAbstractToolDescription_Filters() {
        return (EReference) abstractToolDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAbstractToolDescription_ElementsToSelect() {
        return (EAttribute) abstractToolDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAbstractToolDescription_InverseSelectionOrder() {
        return (EAttribute) abstractToolDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getMappingBasedToolDescription() {
        return mappingBasedToolDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getToolDescription() {
        return toolDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getToolDescription_IconPath() {
        return (EAttribute) toolDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getToolDescription_Element() {
        return (EReference) toolDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getToolDescription_ElementView() {
        return (EReference) toolDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getToolDescription_InitialOperation() {
        return (EReference) toolDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getPasteDescription() {
        return pasteDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPasteDescription_Container() {
        return (EReference) pasteDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPasteDescription_ContainerView() {
        return (EReference) pasteDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPasteDescription_CopiedView() {
        return (EReference) pasteDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPasteDescription_CopiedElement() {
        return (EReference) pasteDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPasteDescription_InitialOperation() {
        return (EReference) pasteDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getSelectionWizardDescription() {
        return selectionWizardDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getSelectionWizardDescription_Element() {
        return (EReference) selectionWizardDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getSelectionWizardDescription_ContainerView() {
        return (EReference) selectionWizardDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getSelectionWizardDescription_Container() {
        return (EReference) selectionWizardDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getSelectionWizardDescription_InitialOperation() {
        return (EReference) selectionWizardDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getSelectionWizardDescription_IconPath() {
        return (EAttribute) selectionWizardDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getSelectionWizardDescription_WindowTitle() {
        return (EAttribute) selectionWizardDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getSelectionWizardDescription_WindowImagePath() {
        return (EAttribute) selectionWizardDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getPaneBasedSelectionWizardDescription() {
        return paneBasedSelectionWizardDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPaneBasedSelectionWizardDescription_Element() {
        return (EReference) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPaneBasedSelectionWizardDescription_ContainerView() {
        return (EReference) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPaneBasedSelectionWizardDescription_Container() {
        return (EReference) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPaneBasedSelectionWizardDescription_InitialOperation() {
        return (EReference) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getPaneBasedSelectionWizardDescription_IconPath() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getPaneBasedSelectionWizardDescription_WindowTitle() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getPaneBasedSelectionWizardDescription_WindowImagePath() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getPaneBasedSelectionWizardDescription_Message() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getPaneBasedSelectionWizardDescription_ChoiceOfValuesMessage() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getPaneBasedSelectionWizardDescription_CandidatesExpression() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getPaneBasedSelectionWizardDescription_Tree() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getPaneBasedSelectionWizardDescription_RootExpression() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getPaneBasedSelectionWizardDescription_ChildrenExpression() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getPaneBasedSelectionWizardDescription_SelectedValuesMessage() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getPaneBasedSelectionWizardDescription_PreSelectedCandidatesExpression() {
        return (EAttribute) paneBasedSelectionWizardDescriptionEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getRepresentationCreationDescription() {
        return representationCreationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRepresentationCreationDescription_TitleExpression() {
        return (EAttribute) representationCreationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRepresentationCreationDescription_BrowseExpression() {
        return (EAttribute) representationCreationDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getRepresentationCreationDescription_RepresentationDescription() {
        return (EReference) representationCreationDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getRepresentationCreationDescription_InitialOperation() {
        return (EReference) representationCreationDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getRepresentationCreationDescription_ContainerViewVariable() {
        return (EReference) representationCreationDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getRepresentationCreationDescription_RepresentationNameVariable() {
        return (EReference) representationCreationDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getRepresentationNavigationDescription() {
        return representationNavigationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRepresentationNavigationDescription_BrowseExpression() {
        return (EAttribute) representationNavigationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRepresentationNavigationDescription_NavigationNameExpression() {
        return (EAttribute) representationNavigationDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getRepresentationNavigationDescription_RepresentationDescription() {
        return (EReference) representationNavigationDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getRepresentationNavigationDescription_ContainerViewVariable() {
        return (EReference) representationNavigationDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getRepresentationNavigationDescription_ContainerVariable() {
        return (EReference) representationNavigationDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getRepresentationNavigationDescription_RepresentationNameVariable() {
        return (EReference) representationNavigationDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getMenuItemOrRef() {
        return menuItemOrRefEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getMenuItemDescription() {
        return menuItemDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getMenuItemDescription_Icon() {
        return (EAttribute) menuItemDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getMenuItemDescriptionReference() {
        return menuItemDescriptionReferenceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getMenuItemDescriptionReference_Item() {
        return (EReference) menuItemDescriptionReferenceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getOperationAction() {
        return operationActionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getOperationAction_View() {
        return (EReference) operationActionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getOperationAction_InitialOperation() {
        return (EReference) operationActionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getExternalJavaAction() {
        return externalJavaActionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getExternalJavaAction_Id() {
        return (EAttribute) externalJavaActionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getExternalJavaAction_Parameters() {
        return (EReference) externalJavaActionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getExternalJavaActionCall() {
        return externalJavaActionCallEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getExternalJavaActionCall_Action() {
        return (EReference) externalJavaActionCallEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getPopupMenu() {
        return popupMenuEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPopupMenu_MenuItemDescription() {
        return (EReference) popupMenuEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getAbstractVariable() {
        return abstractVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAbstractVariable_Name() {
        return (EAttribute) abstractVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getVariableContainer() {
        return variableContainerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getVariableContainer_SubVariables() {
        return (EReference) variableContainerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getAcceleoVariable() {
        return acceleoVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAcceleoVariable_ComputationExpression() {
        return (EAttribute) acceleoVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getSubVariable() {
        return subVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDialogVariable() {
        return dialogVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getDialogVariable_DialogPrompt() {
        return (EAttribute) dialogVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getElementDropVariable() {
        return elementDropVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getElementSelectVariable() {
        return elementSelectVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getElementVariable() {
        return elementVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getElementViewVariable() {
        return elementViewVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getElementDeleteVariable() {
        return elementDeleteVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDropContainerVariable() {
        return dropContainerVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getSelectContainerVariable() {
        return selectContainerVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getContainerViewVariable() {
        return containerViewVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getSelectModelElementVariable() {
        return selectModelElementVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getEditMaskVariables() {
        return editMaskVariablesEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getEditMaskVariables_Mask() {
        return (EAttribute) editMaskVariablesEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getContainerModelOperation() {
        return containerModelOperationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getContainerModelOperation_SubModelOperations() {
        return (EReference) containerModelOperationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getModelOperation() {
        return modelOperationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getInitialNodeCreationOperation() {
        return initialNodeCreationOperationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getInitialNodeCreationOperation_FirstModelOperations() {
        return (EReference) initialNodeCreationOperationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getInitialOperation() {
        return initialOperationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getInitialOperation_FirstModelOperations() {
        return (EReference) initialOperationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getInitEdgeCreationOperation() {
        return initEdgeCreationOperationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getInitEdgeCreationOperation_FirstModelOperations() {
        return (EReference) initEdgeCreationOperationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getInitialContainerDropOperation() {
        return initialContainerDropOperationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getInitialContainerDropOperation_FirstModelOperations() {
        return (EReference) initialContainerDropOperationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCreateInstance() {
        return createInstanceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getCreateInstance_TypeName() {
        return (EAttribute) createInstanceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getCreateInstance_ReferenceName() {
        return (EAttribute) createInstanceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getCreateInstance_VariableName() {
        return (EAttribute) createInstanceEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getChangeContext() {
        return changeContextEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getChangeContext_BrowseExpression() {
        return (EAttribute) changeContextEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getSetValue() {
        return setValueEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getSetValue_FeatureName() {
        return (EAttribute) setValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getSetValue_ValueExpression() {
        return (EAttribute) setValueEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getSetObject() {
        return setObjectEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getSetObject_FeatureName() {
        return (EAttribute) setObjectEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getSetObject_Object() {
        return (EReference) setObjectEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getUnset() {
        return unsetEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getUnset_FeatureName() {
        return (EAttribute) unsetEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getUnset_ElementExpression() {
        return (EAttribute) unsetEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getMoveElement() {
        return moveElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getMoveElement_NewContainerExpression() {
        return (EAttribute) moveElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getMoveElement_FeatureName() {
        return (EAttribute) moveElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getRemoveElement() {
        return removeElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getFor() {
        return forEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getFor_Expression() {
        return (EAttribute) forEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getFor_IteratorName() {
        return (EAttribute) forEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getIf() {
        return ifEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getIf_ConditionExpression() {
        return (EAttribute) ifEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDeleteView() {
        return deleteViewEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getNameVariable() {
        return nameVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getExternalJavaActionParameter() {
        return externalJavaActionParameterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getExternalJavaActionParameter_Name() {
        return (EAttribute) externalJavaActionParameterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getExternalJavaActionParameter_Value() {
        return (EAttribute) externalJavaActionParameterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getToolFilterDescription() {
        return toolFilterDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getToolFilterDescription_Precondition() {
        return (EAttribute) toolFilterDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getToolFilterDescription_ElementsToListen() {
        return (EAttribute) toolFilterDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getToolFilterDescription_Listeners() {
        return (EReference) toolFilterDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getFeatureChangeListener() {
        return featureChangeListenerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getFeatureChangeListener_DomainClass() {
        return (EAttribute) featureChangeListenerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getFeatureChangeListener_FeatureName() {
        return (EAttribute) featureChangeListenerEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCase() {
        return caseEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getCase_ConditionExpression() {
        return (EAttribute) caseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getSwitchChild() {
        return switchChildEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getSwitchChild_SubModelOperations() {
        return (EReference) switchChildEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDefault() {
        return defaultEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getSwitch() {
        return switchEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getSwitch_Cases() {
        return (EReference) switchEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getSwitch_Default() {
        return (EReference) switchEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EEnum getDragSource() {
        return dragSourceEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ToolFactory getToolFactory() {
        return (ToolFactory) getEFactoryInstance();
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
        toolEntryEClass = createEClass(ToolPackage.TOOL_ENTRY);

        abstractToolDescriptionEClass = createEClass(ToolPackage.ABSTRACT_TOOL_DESCRIPTION);
        createEAttribute(abstractToolDescriptionEClass, ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION);
        createEAttribute(abstractToolDescriptionEClass, ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH);
        createEReference(abstractToolDescriptionEClass, ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS);
        createEAttribute(abstractToolDescriptionEClass, ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT);
        createEAttribute(abstractToolDescriptionEClass, ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER);

        mappingBasedToolDescriptionEClass = createEClass(ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION);

        toolDescriptionEClass = createEClass(ToolPackage.TOOL_DESCRIPTION);
        createEAttribute(toolDescriptionEClass, ToolPackage.TOOL_DESCRIPTION__ICON_PATH);
        createEReference(toolDescriptionEClass, ToolPackage.TOOL_DESCRIPTION__ELEMENT);
        createEReference(toolDescriptionEClass, ToolPackage.TOOL_DESCRIPTION__ELEMENT_VIEW);
        createEReference(toolDescriptionEClass, ToolPackage.TOOL_DESCRIPTION__INITIAL_OPERATION);

        pasteDescriptionEClass = createEClass(ToolPackage.PASTE_DESCRIPTION);
        createEReference(pasteDescriptionEClass, ToolPackage.PASTE_DESCRIPTION__CONTAINER);
        createEReference(pasteDescriptionEClass, ToolPackage.PASTE_DESCRIPTION__CONTAINER_VIEW);
        createEReference(pasteDescriptionEClass, ToolPackage.PASTE_DESCRIPTION__COPIED_VIEW);
        createEReference(pasteDescriptionEClass, ToolPackage.PASTE_DESCRIPTION__COPIED_ELEMENT);
        createEReference(pasteDescriptionEClass, ToolPackage.PASTE_DESCRIPTION__INITIAL_OPERATION);

        selectionWizardDescriptionEClass = createEClass(ToolPackage.SELECTION_WIZARD_DESCRIPTION);
        createEReference(selectionWizardDescriptionEClass, ToolPackage.SELECTION_WIZARD_DESCRIPTION__ELEMENT);
        createEReference(selectionWizardDescriptionEClass, ToolPackage.SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW);
        createEReference(selectionWizardDescriptionEClass, ToolPackage.SELECTION_WIZARD_DESCRIPTION__CONTAINER);
        createEReference(selectionWizardDescriptionEClass, ToolPackage.SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(selectionWizardDescriptionEClass, ToolPackage.SELECTION_WIZARD_DESCRIPTION__ICON_PATH);
        createEAttribute(selectionWizardDescriptionEClass, ToolPackage.SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE);
        createEAttribute(selectionWizardDescriptionEClass, ToolPackage.SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH);

        paneBasedSelectionWizardDescriptionEClass = createEClass(ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION);
        createEReference(paneBasedSelectionWizardDescriptionEClass, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT);
        createEReference(paneBasedSelectionWizardDescriptionEClass, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW);
        createEReference(paneBasedSelectionWizardDescriptionEClass, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER);
        createEReference(paneBasedSelectionWizardDescriptionEClass, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ICON_PATH);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__MESSAGE);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHOICE_OF_VALUES_MESSAGE);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CANDIDATES_EXPRESSION);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__TREE);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ROOT_EXPRESSION);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHILDREN_EXPRESSION);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__SELECTED_VALUES_MESSAGE);
        createEAttribute(paneBasedSelectionWizardDescriptionEClass, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__PRE_SELECTED_CANDIDATES_EXPRESSION);

        representationCreationDescriptionEClass = createEClass(ToolPackage.REPRESENTATION_CREATION_DESCRIPTION);
        createEAttribute(representationCreationDescriptionEClass, ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__TITLE_EXPRESSION);
        createEAttribute(representationCreationDescriptionEClass, ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__BROWSE_EXPRESSION);
        createEReference(representationCreationDescriptionEClass, ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_DESCRIPTION);
        createEReference(representationCreationDescriptionEClass, ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION);
        createEReference(representationCreationDescriptionEClass, ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE);
        createEReference(representationCreationDescriptionEClass, ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE);

        representationNavigationDescriptionEClass = createEClass(ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION);
        createEAttribute(representationNavigationDescriptionEClass, ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION);
        createEAttribute(representationNavigationDescriptionEClass, ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION);
        createEReference(representationNavigationDescriptionEClass, ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_DESCRIPTION);
        createEReference(representationNavigationDescriptionEClass, ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE);
        createEReference(representationNavigationDescriptionEClass, ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE);
        createEReference(representationNavigationDescriptionEClass, ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE);

        menuItemOrRefEClass = createEClass(ToolPackage.MENU_ITEM_OR_REF);

        menuItemDescriptionEClass = createEClass(ToolPackage.MENU_ITEM_DESCRIPTION);
        createEAttribute(menuItemDescriptionEClass, ToolPackage.MENU_ITEM_DESCRIPTION__ICON);

        menuItemDescriptionReferenceEClass = createEClass(ToolPackage.MENU_ITEM_DESCRIPTION_REFERENCE);
        createEReference(menuItemDescriptionReferenceEClass, ToolPackage.MENU_ITEM_DESCRIPTION_REFERENCE__ITEM);

        operationActionEClass = createEClass(ToolPackage.OPERATION_ACTION);
        createEReference(operationActionEClass, ToolPackage.OPERATION_ACTION__VIEW);
        createEReference(operationActionEClass, ToolPackage.OPERATION_ACTION__INITIAL_OPERATION);

        externalJavaActionEClass = createEClass(ToolPackage.EXTERNAL_JAVA_ACTION);
        createEAttribute(externalJavaActionEClass, ToolPackage.EXTERNAL_JAVA_ACTION__ID);
        createEReference(externalJavaActionEClass, ToolPackage.EXTERNAL_JAVA_ACTION__PARAMETERS);

        externalJavaActionCallEClass = createEClass(ToolPackage.EXTERNAL_JAVA_ACTION_CALL);
        createEReference(externalJavaActionCallEClass, ToolPackage.EXTERNAL_JAVA_ACTION_CALL__ACTION);

        popupMenuEClass = createEClass(ToolPackage.POPUP_MENU);
        createEReference(popupMenuEClass, ToolPackage.POPUP_MENU__MENU_ITEM_DESCRIPTION);

        abstractVariableEClass = createEClass(ToolPackage.ABSTRACT_VARIABLE);
        createEAttribute(abstractVariableEClass, ToolPackage.ABSTRACT_VARIABLE__NAME);

        variableContainerEClass = createEClass(ToolPackage.VARIABLE_CONTAINER);
        createEReference(variableContainerEClass, ToolPackage.VARIABLE_CONTAINER__SUB_VARIABLES);

        acceleoVariableEClass = createEClass(ToolPackage.ACCELEO_VARIABLE);
        createEAttribute(acceleoVariableEClass, ToolPackage.ACCELEO_VARIABLE__COMPUTATION_EXPRESSION);

        subVariableEClass = createEClass(ToolPackage.SUB_VARIABLE);

        dialogVariableEClass = createEClass(ToolPackage.DIALOG_VARIABLE);
        createEAttribute(dialogVariableEClass, ToolPackage.DIALOG_VARIABLE__DIALOG_PROMPT);

        elementDropVariableEClass = createEClass(ToolPackage.ELEMENT_DROP_VARIABLE);

        elementSelectVariableEClass = createEClass(ToolPackage.ELEMENT_SELECT_VARIABLE);

        elementVariableEClass = createEClass(ToolPackage.ELEMENT_VARIABLE);

        elementViewVariableEClass = createEClass(ToolPackage.ELEMENT_VIEW_VARIABLE);

        elementDeleteVariableEClass = createEClass(ToolPackage.ELEMENT_DELETE_VARIABLE);

        dropContainerVariableEClass = createEClass(ToolPackage.DROP_CONTAINER_VARIABLE);

        selectContainerVariableEClass = createEClass(ToolPackage.SELECT_CONTAINER_VARIABLE);

        containerViewVariableEClass = createEClass(ToolPackage.CONTAINER_VIEW_VARIABLE);

        selectModelElementVariableEClass = createEClass(ToolPackage.SELECT_MODEL_ELEMENT_VARIABLE);

        editMaskVariablesEClass = createEClass(ToolPackage.EDIT_MASK_VARIABLES);
        createEAttribute(editMaskVariablesEClass, ToolPackage.EDIT_MASK_VARIABLES__MASK);

        containerModelOperationEClass = createEClass(ToolPackage.CONTAINER_MODEL_OPERATION);
        createEReference(containerModelOperationEClass, ToolPackage.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS);

        modelOperationEClass = createEClass(ToolPackage.MODEL_OPERATION);

        initialNodeCreationOperationEClass = createEClass(ToolPackage.INITIAL_NODE_CREATION_OPERATION);
        createEReference(initialNodeCreationOperationEClass, ToolPackage.INITIAL_NODE_CREATION_OPERATION__FIRST_MODEL_OPERATIONS);

        initialOperationEClass = createEClass(ToolPackage.INITIAL_OPERATION);
        createEReference(initialOperationEClass, ToolPackage.INITIAL_OPERATION__FIRST_MODEL_OPERATIONS);

        initEdgeCreationOperationEClass = createEClass(ToolPackage.INIT_EDGE_CREATION_OPERATION);
        createEReference(initEdgeCreationOperationEClass, ToolPackage.INIT_EDGE_CREATION_OPERATION__FIRST_MODEL_OPERATIONS);

        initialContainerDropOperationEClass = createEClass(ToolPackage.INITIAL_CONTAINER_DROP_OPERATION);
        createEReference(initialContainerDropOperationEClass, ToolPackage.INITIAL_CONTAINER_DROP_OPERATION__FIRST_MODEL_OPERATIONS);

        createInstanceEClass = createEClass(ToolPackage.CREATE_INSTANCE);
        createEAttribute(createInstanceEClass, ToolPackage.CREATE_INSTANCE__TYPE_NAME);
        createEAttribute(createInstanceEClass, ToolPackage.CREATE_INSTANCE__REFERENCE_NAME);
        createEAttribute(createInstanceEClass, ToolPackage.CREATE_INSTANCE__VARIABLE_NAME);

        changeContextEClass = createEClass(ToolPackage.CHANGE_CONTEXT);
        createEAttribute(changeContextEClass, ToolPackage.CHANGE_CONTEXT__BROWSE_EXPRESSION);

        setValueEClass = createEClass(ToolPackage.SET_VALUE);
        createEAttribute(setValueEClass, ToolPackage.SET_VALUE__FEATURE_NAME);
        createEAttribute(setValueEClass, ToolPackage.SET_VALUE__VALUE_EXPRESSION);

        setObjectEClass = createEClass(ToolPackage.SET_OBJECT);
        createEAttribute(setObjectEClass, ToolPackage.SET_OBJECT__FEATURE_NAME);
        createEReference(setObjectEClass, ToolPackage.SET_OBJECT__OBJECT);

        unsetEClass = createEClass(ToolPackage.UNSET);
        createEAttribute(unsetEClass, ToolPackage.UNSET__FEATURE_NAME);
        createEAttribute(unsetEClass, ToolPackage.UNSET__ELEMENT_EXPRESSION);

        moveElementEClass = createEClass(ToolPackage.MOVE_ELEMENT);
        createEAttribute(moveElementEClass, ToolPackage.MOVE_ELEMENT__NEW_CONTAINER_EXPRESSION);
        createEAttribute(moveElementEClass, ToolPackage.MOVE_ELEMENT__FEATURE_NAME);

        removeElementEClass = createEClass(ToolPackage.REMOVE_ELEMENT);

        forEClass = createEClass(ToolPackage.FOR);
        createEAttribute(forEClass, ToolPackage.FOR__EXPRESSION);
        createEAttribute(forEClass, ToolPackage.FOR__ITERATOR_NAME);

        ifEClass = createEClass(ToolPackage.IF);
        createEAttribute(ifEClass, ToolPackage.IF__CONDITION_EXPRESSION);

        deleteViewEClass = createEClass(ToolPackage.DELETE_VIEW);

        nameVariableEClass = createEClass(ToolPackage.NAME_VARIABLE);

        externalJavaActionParameterEClass = createEClass(ToolPackage.EXTERNAL_JAVA_ACTION_PARAMETER);
        createEAttribute(externalJavaActionParameterEClass, ToolPackage.EXTERNAL_JAVA_ACTION_PARAMETER__NAME);
        createEAttribute(externalJavaActionParameterEClass, ToolPackage.EXTERNAL_JAVA_ACTION_PARAMETER__VALUE);

        toolFilterDescriptionEClass = createEClass(ToolPackage.TOOL_FILTER_DESCRIPTION);
        createEAttribute(toolFilterDescriptionEClass, ToolPackage.TOOL_FILTER_DESCRIPTION__PRECONDITION);
        createEAttribute(toolFilterDescriptionEClass, ToolPackage.TOOL_FILTER_DESCRIPTION__ELEMENTS_TO_LISTEN);
        createEReference(toolFilterDescriptionEClass, ToolPackage.TOOL_FILTER_DESCRIPTION__LISTENERS);

        featureChangeListenerEClass = createEClass(ToolPackage.FEATURE_CHANGE_LISTENER);
        createEAttribute(featureChangeListenerEClass, ToolPackage.FEATURE_CHANGE_LISTENER__DOMAIN_CLASS);
        createEAttribute(featureChangeListenerEClass, ToolPackage.FEATURE_CHANGE_LISTENER__FEATURE_NAME);

        caseEClass = createEClass(ToolPackage.CASE);
        createEAttribute(caseEClass, ToolPackage.CASE__CONDITION_EXPRESSION);

        switchChildEClass = createEClass(ToolPackage.SWITCH_CHILD);
        createEReference(switchChildEClass, ToolPackage.SWITCH_CHILD__SUB_MODEL_OPERATIONS);

        defaultEClass = createEClass(ToolPackage.DEFAULT);

        switchEClass = createEClass(ToolPackage.SWITCH);
        createEReference(switchEClass, ToolPackage.SWITCH__CASES);
        createEReference(switchEClass, ToolPackage.SWITCH__DEFAULT);

        // Create enums
        dragSourceEEnum = createEEnum(ToolPackage.DRAG_SOURCE);
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
        setName(ToolPackage.eNAME);
        setNsPrefix(ToolPackage.eNS_PREFIX);
        setNsURI(ToolPackage.eNS_URI);

        // Obtain other dependent packages
        DescriptionPackage theDescriptionPackage = (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        toolEntryEClass.getESuperTypes().add(theDescriptionPackage.getDocumentedElement());
        toolEntryEClass.getESuperTypes().add(theDescriptionPackage.getIdentifiedElement());
        abstractToolDescriptionEClass.getESuperTypes().add(this.getToolEntry());
        mappingBasedToolDescriptionEClass.getESuperTypes().add(this.getAbstractToolDescription());
        toolDescriptionEClass.getESuperTypes().add(this.getMappingBasedToolDescription());
        pasteDescriptionEClass.getESuperTypes().add(this.getMappingBasedToolDescription());
        selectionWizardDescriptionEClass.getESuperTypes().add(this.getAbstractToolDescription());
        selectionWizardDescriptionEClass.getESuperTypes().add(theDescriptionPackage.getSelectionDescription());
        paneBasedSelectionWizardDescriptionEClass.getESuperTypes().add(this.getAbstractToolDescription());
        representationCreationDescriptionEClass.getESuperTypes().add(this.getAbstractToolDescription());
        representationNavigationDescriptionEClass.getESuperTypes().add(this.getAbstractToolDescription());
        menuItemDescriptionEClass.getESuperTypes().add(this.getAbstractToolDescription());
        menuItemDescriptionEClass.getESuperTypes().add(this.getMenuItemOrRef());
        menuItemDescriptionReferenceEClass.getESuperTypes().add(this.getMenuItemOrRef());
        operationActionEClass.getESuperTypes().add(this.getMenuItemDescription());
        externalJavaActionEClass.getESuperTypes().add(this.getMenuItemDescription());
        externalJavaActionEClass.getESuperTypes().add(this.getContainerModelOperation());
        externalJavaActionCallEClass.getESuperTypes().add(this.getMenuItemDescription());
        externalJavaActionCallEClass.getESuperTypes().add(this.getContainerModelOperation());
        popupMenuEClass.getESuperTypes().add(this.getAbstractToolDescription());
        acceleoVariableEClass.getESuperTypes().add(this.getVariableContainer());
        acceleoVariableEClass.getESuperTypes().add(this.getSubVariable());
        subVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        dialogVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        elementDropVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        elementDropVariableEClass.getESuperTypes().add(this.getVariableContainer());
        elementSelectVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        elementVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        elementVariableEClass.getESuperTypes().add(this.getVariableContainer());
        elementViewVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        elementViewVariableEClass.getESuperTypes().add(this.getVariableContainer());
        elementDeleteVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        elementDeleteVariableEClass.getESuperTypes().add(this.getVariableContainer());
        dropContainerVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        dropContainerVariableEClass.getESuperTypes().add(this.getVariableContainer());
        selectContainerVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        selectContainerVariableEClass.getESuperTypes().add(this.getVariableContainer());
        containerViewVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        containerViewVariableEClass.getESuperTypes().add(this.getVariableContainer());
        selectModelElementVariableEClass.getESuperTypes().add(this.getSubVariable());
        selectModelElementVariableEClass.getESuperTypes().add(theDescriptionPackage.getSelectionDescription());
        containerModelOperationEClass.getESuperTypes().add(this.getModelOperation());
        createInstanceEClass.getESuperTypes().add(this.getContainerModelOperation());
        changeContextEClass.getESuperTypes().add(this.getContainerModelOperation());
        setValueEClass.getESuperTypes().add(this.getContainerModelOperation());
        setObjectEClass.getESuperTypes().add(this.getContainerModelOperation());
        unsetEClass.getESuperTypes().add(this.getContainerModelOperation());
        moveElementEClass.getESuperTypes().add(this.getContainerModelOperation());
        removeElementEClass.getESuperTypes().add(this.getContainerModelOperation());
        forEClass.getESuperTypes().add(this.getContainerModelOperation());
        ifEClass.getESuperTypes().add(this.getContainerModelOperation());
        deleteViewEClass.getESuperTypes().add(this.getContainerModelOperation());
        nameVariableEClass.getESuperTypes().add(this.getAbstractVariable());
        caseEClass.getESuperTypes().add(this.getSwitchChild());
        defaultEClass.getESuperTypes().add(this.getSwitchChild());
        switchEClass.getESuperTypes().add(this.getModelOperation());

        // Initialize classes and features; add operations and parameters
        initEClass(toolEntryEClass, ToolEntry.class, "ToolEntry", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(abstractToolDescriptionEClass, AbstractToolDescription.class,
                "AbstractToolDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getAbstractToolDescription_Precondition(),
                theDescriptionPackage.getInterpretedExpression(),
                "precondition", "", 0, 1, AbstractToolDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getAbstractToolDescription_ForceRefresh(),
                theEcorePackage.getEBoolean(),
                "forceRefresh", "false", 0, 1, AbstractToolDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(
                getAbstractToolDescription_Filters(),
                this.getToolFilterDescription(),
                null,
                "filters", null, 0, -1, AbstractToolDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getAbstractToolDescription_ElementsToSelect(),
                theDescriptionPackage.getInterpretedExpression(),
                "elementsToSelect", "", 0, 1, AbstractToolDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getAbstractToolDescription_InverseSelectionOrder(),
                theEcorePackage.getEBoolean(),
                "inverseSelectionOrder", "false", 0, 1, AbstractToolDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(mappingBasedToolDescriptionEClass, MappingBasedToolDescription.class,
                "MappingBasedToolDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(toolDescriptionEClass, ToolDescription.class, "ToolDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getToolDescription_IconPath(),
                theDescriptionPackage.getImagePath(),
                "iconPath", "", 0, 1, ToolDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEReference(
                getToolDescription_Element(),
                this.getElementVariable(),
                null,
                "element", null, 1, 1, ToolDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getToolDescription_ElementView(),
                this.getElementViewVariable(),
                null,
                "elementView", null, 1, 1, ToolDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getToolDescription_InitialOperation(),
                this.getInitialOperation(),
                null,
                "initialOperation", null, 1, 1, ToolDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(pasteDescriptionEClass, PasteDescription.class, "PasteDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getPasteDescription_Container(),
                this.getDropContainerVariable(),
                null,
                "container", null, 1, 1, PasteDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getPasteDescription_ContainerView(),
                this.getContainerViewVariable(),
                null,
                "containerView", null, 1, 1, PasteDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getPasteDescription_CopiedView(),
                this.getElementViewVariable(),
                null,
                "copiedView", null, 1, 1, PasteDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getPasteDescription_CopiedElement(),
                this.getElementVariable(),
                null,
                "copiedElement", null, 1, 1, PasteDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getPasteDescription_InitialOperation(),
                this.getInitialOperation(),
                null,
                "initialOperation", null, 1, 1, PasteDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        addEOperation(pasteDescriptionEClass, theDescriptionPackage.getPasteTargetDescription(), "getContainers", 0, -1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(selectionWizardDescriptionEClass, SelectionWizardDescription.class,
                "SelectionWizardDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getSelectionWizardDescription_Element(),
                this.getElementSelectVariable(),
                null,
                "element", null, 1, 1, SelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getSelectionWizardDescription_ContainerView(),
                this.getContainerViewVariable(),
                null,
                "containerView", null, 1, 1, SelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getSelectionWizardDescription_Container(),
                this.getSelectContainerVariable(),
                null,
                "container", null, 1, 1, SelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getSelectionWizardDescription_InitialOperation(),
                this.getInitialOperation(),
                null,
                "initialOperation", null, 1, 1, SelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getSelectionWizardDescription_IconPath(),
                theDescriptionPackage.getImagePath(),
                "iconPath", "/org.eclipse.sirius.ui/icons/full/obj16/SelectionWizardDescription.gif", 1, 1, SelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getSelectionWizardDescription_WindowTitle(),
                theEcorePackage.getEString(),
                "windowTitle", "Selection Wizard", 1, 1, SelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getSelectionWizardDescription_WindowImagePath(),
                theDescriptionPackage.getImagePath(),
                "windowImagePath", null, 0, 1, SelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(paneBasedSelectionWizardDescriptionEClass, PaneBasedSelectionWizardDescription.class,
                "PaneBasedSelectionWizardDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getPaneBasedSelectionWizardDescription_Element(),
                this.getElementSelectVariable(),
                null,
                "element", null, 1, 1, PaneBasedSelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getPaneBasedSelectionWizardDescription_ContainerView(),
                this.getContainerViewVariable(),
                null,
                "containerView", null, 1, 1, PaneBasedSelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getPaneBasedSelectionWizardDescription_Container(),
                this.getSelectContainerVariable(),
                null,
                "container", null, 1, 1, PaneBasedSelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getPaneBasedSelectionWizardDescription_InitialOperation(),
                this.getInitialOperation(),
                null,
                "initialOperation", null, 1, 1, PaneBasedSelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getPaneBasedSelectionWizardDescription_IconPath(),
                theDescriptionPackage.getImagePath(),
                "iconPath", "/org.eclipse.sirius.ui/icons/full/obj16/PaneBasedSelectionWizardDescription.gif", 1, 1, PaneBasedSelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getPaneBasedSelectionWizardDescription_WindowTitle(),
                theEcorePackage.getEString(),
                "windowTitle", "Selection Wizard", 1, 1, PaneBasedSelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getPaneBasedSelectionWizardDescription_WindowImagePath(),
                theDescriptionPackage.getImagePath(),
                "windowImagePath", null, 0, 1, PaneBasedSelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getPaneBasedSelectionWizardDescription_Message(),
                theEcorePackage.getEString(),
                "message", null, 0, 1, PaneBasedSelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getPaneBasedSelectionWizardDescription_ChoiceOfValuesMessage(),
                theEcorePackage.getEString(),
                "choiceOfValuesMessage", "Choice of values", 0, 1, PaneBasedSelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getPaneBasedSelectionWizardDescription_CandidatesExpression(),
                theDescriptionPackage.getInterpretedExpression(),
                "candidatesExpression", null, 1, 1, PaneBasedSelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getPaneBasedSelectionWizardDescription_Tree(),
                theEcorePackage.getEBoolean(),
                "tree", null, 1, 1, PaneBasedSelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getPaneBasedSelectionWizardDescription_RootExpression(),
                theDescriptionPackage.getInterpretedExpression(),
                "rootExpression", null, 0, 1, PaneBasedSelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getPaneBasedSelectionWizardDescription_ChildrenExpression(),
                theDescriptionPackage.getInterpretedExpression(),
                "childrenExpression", null, 0, 1, PaneBasedSelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getPaneBasedSelectionWizardDescription_SelectedValuesMessage(),
                theEcorePackage.getEString(),
                "selectedValuesMessage", "Selected values", 0, 1, PaneBasedSelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getPaneBasedSelectionWizardDescription_PreSelectedCandidatesExpression(),
                theDescriptionPackage.getInterpretedExpression(),
                "preSelectedCandidatesExpression", null, 0, 1, PaneBasedSelectionWizardDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(representationCreationDescriptionEClass, RepresentationCreationDescription.class,
                "RepresentationCreationDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getRepresentationCreationDescription_TitleExpression(),
                theDescriptionPackage.getInterpretedExpression(),
                "titleExpression", "", 0, 1, RepresentationCreationDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getRepresentationCreationDescription_BrowseExpression(),
                theDescriptionPackage.getInterpretedExpression(),
                "browseExpression", null, 0, 1, RepresentationCreationDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getRepresentationCreationDescription_RepresentationDescription(),
                theDescriptionPackage.getRepresentationDescription(),
                null,
                "representationDescription", null, 1, 1, RepresentationCreationDescription.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getRepresentationCreationDescription_InitialOperation(),
                this.getInitialOperation(),
                null,
                "initialOperation", null, 0, 1, RepresentationCreationDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getRepresentationCreationDescription_ContainerViewVariable(),
                this.getContainerViewVariable(),
                null,
                "containerViewVariable", null, 1, 1, RepresentationCreationDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getRepresentationCreationDescription_RepresentationNameVariable(),
                this.getNameVariable(),
                null,
                "representationNameVariable", null, 1, 1, RepresentationCreationDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        addEOperation(representationCreationDescriptionEClass, theDescriptionPackage.getRepresentationElementMapping(), "getMappings", 1, -1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(representationNavigationDescriptionEClass, RepresentationNavigationDescription.class,
                "RepresentationNavigationDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getRepresentationNavigationDescription_BrowseExpression(),
                theDescriptionPackage.getInterpretedExpression(),
                "browseExpression", null, 0, 1, RepresentationNavigationDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getRepresentationNavigationDescription_NavigationNameExpression(),
                theDescriptionPackage.getInterpretedExpression(),
                "navigationNameExpression", null, 0, 1, RepresentationNavigationDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getRepresentationNavigationDescription_RepresentationDescription(),
                theDescriptionPackage.getRepresentationDescription(),
                null,
                "representationDescription", null, 1, 1, RepresentationNavigationDescription.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getRepresentationNavigationDescription_ContainerViewVariable(),
                this.getContainerViewVariable(),
                null,
                "containerViewVariable", null, 1, 1, RepresentationNavigationDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getRepresentationNavigationDescription_ContainerVariable(),
                this.getElementSelectVariable(),
                null,
                "containerVariable", null, 1, 1, RepresentationNavigationDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getRepresentationNavigationDescription_RepresentationNameVariable(),
                this.getNameVariable(),
                null,
                "representationNameVariable", null, 1, 1, RepresentationNavigationDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        addEOperation(representationNavigationDescriptionEClass, theDescriptionPackage.getRepresentationElementMapping(), "getMappings", 1, -1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(menuItemOrRefEClass, MenuItemOrRef.class, "MenuItemOrRef", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(menuItemDescriptionEClass, MenuItemDescription.class, "MenuItemDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getMenuItemDescription_Icon(),
                theDescriptionPackage.getImagePath(),
                "icon", null, 0, 1, MenuItemDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(menuItemDescriptionReferenceEClass, MenuItemDescriptionReference.class,
                "MenuItemDescriptionReference", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getMenuItemDescriptionReference_Item(),
                this.getMenuItemDescription(),
                null,
                "item", null, 1, 1, MenuItemDescriptionReference.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(operationActionEClass, OperationAction.class, "OperationAction", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getOperationAction_View(),
                this.getContainerViewVariable(),
                null,
                "view", null, 1, 1, OperationAction.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getOperationAction_InitialOperation(),
                this.getInitialOperation(),
                null,
                "initialOperation", null, 1, 1, OperationAction.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(externalJavaActionEClass, ExternalJavaAction.class, "ExternalJavaAction", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getExternalJavaAction_Id(),
                theEcorePackage.getEString(),
                "id", null, 1, 1, ExternalJavaAction.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getExternalJavaAction_Parameters(),
                this.getExternalJavaActionParameter(),
                null,
                "parameters", null, 0, -1, ExternalJavaAction.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(externalJavaActionCallEClass, ExternalJavaActionCall.class,
                "ExternalJavaActionCall", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getExternalJavaActionCall_Action(),
                this.getExternalJavaAction(),
                null,
                "action", null, 1, 1, ExternalJavaActionCall.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(popupMenuEClass, PopupMenu.class, "PopupMenu", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getPopupMenu_MenuItemDescription(),
                this.getMenuItemDescription(),
                null,
                "menuItemDescription", null, 1, -1, PopupMenu.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(abstractVariableEClass, AbstractVariable.class, "AbstractVariable", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getAbstractVariable_Name(),
                theEcorePackage.getEString(),
                "name", null, 0, 1, AbstractVariable.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(variableContainerEClass, VariableContainer.class, "VariableContainer", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getVariableContainer_SubVariables(),
                this.getSubVariable(),
                null,
                "subVariables", null, 0, -1, VariableContainer.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(acceleoVariableEClass, AcceleoVariable.class, "AcceleoVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getAcceleoVariable_ComputationExpression(),
                theDescriptionPackage.getInterpretedExpression(),
                "computationExpression", null, 0, 1, AcceleoVariable.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(subVariableEClass, SubVariable.class, "SubVariable", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(dialogVariableEClass, DialogVariable.class, "DialogVariable", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getDialogVariable_DialogPrompt(),
                theEcorePackage.getEString(),
                "dialogPrompt", null, 0, 1, DialogVariable.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(elementDropVariableEClass, ElementDropVariable.class, "ElementDropVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(elementSelectVariableEClass, ElementSelectVariable.class, "ElementSelectVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(elementVariableEClass, ElementVariable.class, "ElementVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(elementViewVariableEClass, ElementViewVariable.class, "ElementViewVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(elementDeleteVariableEClass, ElementDeleteVariable.class, "ElementDeleteVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(dropContainerVariableEClass, DropContainerVariable.class, "DropContainerVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(selectContainerVariableEClass, SelectContainerVariable.class,
                "SelectContainerVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(containerViewVariableEClass, ContainerViewVariable.class, "ContainerViewVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(selectModelElementVariableEClass, SelectModelElementVariable.class,
                "SelectModelElementVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(editMaskVariablesEClass, EditMaskVariables.class, "EditMaskVariables", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getEditMaskVariables_Mask(),
                theEcorePackage.getEString(),
                "mask", null, 0, 1, EditMaskVariables.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(containerModelOperationEClass, ContainerModelOperation.class,
                "ContainerModelOperation", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getContainerModelOperation_SubModelOperations(),
                this.getModelOperation(),
                null,
                "subModelOperations", null, 0, -1, ContainerModelOperation.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(modelOperationEClass, ModelOperation.class, "ModelOperation", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(initialNodeCreationOperationEClass, InitialNodeCreationOperation.class,
                "InitialNodeCreationOperation", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getInitialNodeCreationOperation_FirstModelOperations(),
                this.getModelOperation(),
                null,
                "firstModelOperations", null, 1, 1, InitialNodeCreationOperation.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(initialOperationEClass, InitialOperation.class, "InitialOperation", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getInitialOperation_FirstModelOperations(),
                this.getModelOperation(),
                null,
                "firstModelOperations", null, 1, 1, InitialOperation.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(initEdgeCreationOperationEClass, InitEdgeCreationOperation.class,
                "InitEdgeCreationOperation", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getInitEdgeCreationOperation_FirstModelOperations(),
                this.getModelOperation(),
                null,
                "firstModelOperations", null, 1, 1, InitEdgeCreationOperation.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(initialContainerDropOperationEClass, InitialContainerDropOperation.class,
                "InitialContainerDropOperation", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getInitialContainerDropOperation_FirstModelOperations(),
                this.getModelOperation(),
                null,
                "firstModelOperations", null, 1, 1, InitialContainerDropOperation.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(createInstanceEClass, CreateInstance.class, "CreateInstance", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getCreateInstance_TypeName(),
                theDescriptionPackage.getTypeName(),
                "typeName", null, 0, 1, CreateInstance.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getCreateInstance_ReferenceName(),
                theDescriptionPackage.getFeatureName(),
                "referenceName", null, 1, 1, CreateInstance.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getCreateInstance_VariableName(),
                theEcorePackage.getEString(),
                "variableName", "instance", 0, 1, CreateInstance.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(changeContextEClass, ChangeContext.class, "ChangeContext", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getChangeContext_BrowseExpression(),
                theDescriptionPackage.getInterpretedExpression(),
                "browseExpression", null, 0, 1, ChangeContext.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(setValueEClass, SetValue.class, "SetValue", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getSetValue_FeatureName(),
                theDescriptionPackage.getFeatureName(),
                "featureName", null, 1, 1, SetValue.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getSetValue_ValueExpression(),
                theDescriptionPackage.getInterpretedExpression(),
                "valueExpression", null, 0, 1, SetValue.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(setObjectEClass, SetObject.class, "SetObject", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getSetObject_FeatureName(),
                theDescriptionPackage.getFeatureName(),
                "featureName", null, 1, 1, SetObject.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getSetObject_Object(),
                theEcorePackage.getEObject(),
                null,
                "object", null, 0, 1, SetObject.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(unsetEClass, Unset.class, "Unset", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getUnset_FeatureName(),
                theDescriptionPackage.getFeatureName(),
                "featureName", "", 1, 1, Unset.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(
                getUnset_ElementExpression(),
                theDescriptionPackage.getInterpretedExpression(),
                "elementExpression", null, 0, 1, Unset.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(moveElementEClass, MoveElement.class, "MoveElement", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getMoveElement_NewContainerExpression(),
                theDescriptionPackage.getInterpretedExpression(),
                "newContainerExpression", null, 1, 1, MoveElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getMoveElement_FeatureName(),
                theDescriptionPackage.getFeatureName(),
                "featureName", null, 1, 1, MoveElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(removeElementEClass, RemoveElement.class, "RemoveElement", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(forEClass, For.class, "For", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getFor_Expression(),
                theDescriptionPackage.getInterpretedExpression(),
                "expression", null, 1, 1, For.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getFor_IteratorName(),
                theEcorePackage.getEString(),
                "iteratorName", "i", 1, 1, For.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(ifEClass, If.class, "If", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getIf_ConditionExpression(),
                theDescriptionPackage.getInterpretedExpression(),
                "conditionExpression", null, 0, 1, If.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(deleteViewEClass, DeleteView.class, "DeleteView", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(nameVariableEClass, NameVariable.class, "NameVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(externalJavaActionParameterEClass, ExternalJavaActionParameter.class,
                "ExternalJavaActionParameter", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getExternalJavaActionParameter_Name(),
                theEcorePackage.getEString(),
                "name", null, 1, 1, ExternalJavaActionParameter.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getExternalJavaActionParameter_Value(),
                theDescriptionPackage.getInterpretedExpression(),
                "value", null, 0, 1, ExternalJavaActionParameter.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(toolFilterDescriptionEClass, ToolFilterDescription.class, "ToolFilterDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getToolFilterDescription_Precondition(),
                theDescriptionPackage.getInterpretedExpression(),
                "precondition", null, 0, 1, ToolFilterDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getToolFilterDescription_ElementsToListen(),
                theDescriptionPackage.getInterpretedExpression(),
                "elementsToListen", null, 0, 1, ToolFilterDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getToolFilterDescription_Listeners(),
                this.getFeatureChangeListener(),
                null,
                "listeners", null, 1, -1, ToolFilterDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(featureChangeListenerEClass, FeatureChangeListener.class, "FeatureChangeListener", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getFeatureChangeListener_DomainClass(),
                theDescriptionPackage.getTypeName(),
                "domainClass", null, 1, 1, FeatureChangeListener.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getFeatureChangeListener_FeatureName(),
                theDescriptionPackage.getFeatureName(),
                "featureName", null, 1, 1, FeatureChangeListener.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(caseEClass, Case.class, "Case", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getCase_ConditionExpression(),
                theDescriptionPackage.getInterpretedExpression(),
                "conditionExpression", null, 0, 1, Case.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(switchChildEClass, SwitchChild.class, "SwitchChild", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getSwitchChild_SubModelOperations(),
                this.getModelOperation(),
                null,
                "subModelOperations", null, 0, -1, SwitchChild.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(defaultEClass, Default.class, "Default", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(switchEClass, Switch.class, "Switch", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getSwitch_Cases(),
                this.getCase(),
                null,
                "cases", null, 1, -1, Switch.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getSwitch_Default(),
                this.getDefault(),
                null,
                "default", null, 1, 1, Switch.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        // Initialize enums and add enum literals
        initEEnum(dragSourceEEnum, DragSource.class, "DragSource"); //$NON-NLS-1$
        addEEnumLiteral(dragSourceEEnum, DragSource.DIAGRAM_LITERAL);
        addEEnumLiteral(dragSourceEEnum, DragSource.PROJECT_EXPLORER_LITERAL);
        addEEnumLiteral(dragSourceEEnum, DragSource.BOTH_LITERAL);

        // Create annotations
        // http://www.eclipse.org/sirius/interpreted/expression/returnType
        createReturnTypeAnnotations();
        // http://www.eclipse.org/sirius/interpreted/expression/variables
        createVariablesAnnotations();
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/returnType</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createReturnTypeAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType"; //$NON-NLS-1$
        addAnnotation(getAbstractToolDescription_Precondition(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getAbstractToolDescription_ElementsToSelect(), source, new String[] { "returnType", "a Collection<EObject>" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getAbstractToolDescription_InverseSelectionOrder(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getPaneBasedSelectionWizardDescription_CandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getPaneBasedSelectionWizardDescription_RootExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getPaneBasedSelectionWizardDescription_ChildrenExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getPaneBasedSelectionWizardDescription_PreSelectedCandidatesExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getRepresentationCreationDescription_TitleExpression(), source, new String[] { "returnType", "a string." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getRepresentationCreationDescription_BrowseExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getRepresentationNavigationDescription_BrowseExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getRepresentationNavigationDescription_NavigationNameExpression(), source, new String[] { "returnType", "a string." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getAcceleoVariable_ComputationExpression(), source, new String[] { "returnType", "a Collection<Object> or an Object." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getChangeContext_BrowseExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getSetValue_ValueExpression(), source, new String[] { "returnType", "any type supported by the feature." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getUnset_ElementExpression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getMoveElement_NewContainerExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getFor_Expression(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getIf_ConditionExpression(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getExternalJavaActionParameter_Value(), source, new String[] { "returnType", "any type supported by the corresponding java parameter." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getToolFilterDescription_Precondition(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getToolFilterDescription_ElementsToListen(), source, new String[] { "returnType", "a collection." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getCase_ConditionExpression(), source, new String[] { "returnType", "a boolean." //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/variables</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createVariablesAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/variables"; //$NON-NLS-1$
        addAnnotation(getAbstractToolDescription_Precondition(), source, new String[] { "container", "ecore.EObject | the container." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getToolDescription_Element(), source, new String[] { "type", "ecore.EObject" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getToolDescription_ElementView(), source, new String[] { "type", "ecore.EObject" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getPaneBasedSelectionWizardDescription_CandidatesExpression(), source, new String[] { "diagram", "diagram.DDiagram | the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
            "containerView", "viewpoint.DSemanticDecorator | the selected view.", //$NON-NLS-1$ //$NON-NLS-2$
            "container", "ecore.EObject | the semantic element of containerView." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getPaneBasedSelectionWizardDescription_RootExpression(), source, new String[] { "diagram", "diagram.DDiagram | the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
            "containerView", "viewpoint.DSemanticDecorator | the selected view.", //$NON-NLS-1$ //$NON-NLS-2$
            "container", "ecore.EObject | the semantic element of containerView." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getPaneBasedSelectionWizardDescription_ChildrenExpression(), source, new String[] { "diagram", "diagram.DDiagram | the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
            "containerView", "viewpoint.DSemanticDecorator | the selected view.", //$NON-NLS-1$ //$NON-NLS-2$
            "container", "ecore.EObject | the semantic element of containerView." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getPaneBasedSelectionWizardDescription_PreSelectedCandidatesExpression(), source, new String[] { "diagram", "diagram.DDiagram | the current DDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
            "containerView", "viewpoint.DSemanticDecorator | the selected view.", //$NON-NLS-1$ //$NON-NLS-2$
            "container", "ecore.EObject | the semantic element of containerView." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getRepresentationCreationDescription_TitleExpression(), source, new String[] {});
        addAnnotation(getRepresentationCreationDescription_BrowseExpression(), source, new String[] {});
        addAnnotation(getRepresentationNavigationDescription_BrowseExpression(), source, new String[] {});
        addAnnotation(getRepresentationNavigationDescription_NavigationNameExpression(), source, new String[] { "name", "name of the targeted Representation." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getAcceleoVariable_ComputationExpression(), source, new String[] {});
        addAnnotation(getChangeContext_BrowseExpression(), source, new String[] {});
        addAnnotation(getSetValue_ValueExpression(), source, new String[] {});
        addAnnotation(getUnset_ElementExpression(), source, new String[] {});
        addAnnotation(getMoveElement_NewContainerExpression(), source, new String[] {});
        addAnnotation(getFor_Expression(), source, new String[] {});
        addAnnotation(getIf_ConditionExpression(), source, new String[] {});
        addAnnotation(getExternalJavaActionParameter_Value(), source, new String[] {});
        addAnnotation(getToolFilterDescription_Precondition(), source, new String[] {});
        addAnnotation(getToolFilterDescription_ElementsToListen(), source, new String[] {});
        addAnnotation(getCase_ConditionExpression(), source, new String[] {});
    }

} // ToolPackageImpl
