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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementSelectVariable;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Pane Based Selection Wizard Description</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl#getElement
 * <em>Element</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl#getContainerView
 * <em>Container View</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl#getContainer
 * <em>Container</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl#getIconPath
 * <em>Icon Path</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl#getWindowTitle
 * <em>Window Title</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl#getWindowImagePath
 * <em>Window Image Path</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl#getMessage
 * <em>Message</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl#getChoiceOfValuesMessage
 * <em>Choice Of Values Message</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl#getCandidatesExpression
 * <em>Candidates Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl#isTree
 * <em>Tree</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl#getRootExpression
 * <em>Root Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl#getChildrenExpression
 * <em>Children Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl#getSelectedValuesMessage
 * <em>Selected Values Message</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl#getPreSelectedCandidatesExpression
 * <em>Pre Selected Candidates Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PaneBasedSelectionWizardDescriptionImpl extends AbstractToolDescriptionImpl implements PaneBasedSelectionWizardDescription {
    /**
     * The cached value of the '{@link #getElement() <em>Element</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getElement()
     * @generated
     * @ordered
     */
    protected ElementSelectVariable element;

    /**
     * The cached value of the '{@link #getContainerView()
     * <em>Container View</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getContainerView()
     * @generated
     * @ordered
     */
    protected ContainerViewVariable containerView;

    /**
     * The cached value of the '{@link #getContainer() <em>Container</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getContainer()
     * @generated
     * @ordered
     */
    protected SelectContainerVariable container;

    /**
     * The cached value of the '{@link #getInitialOperation()
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getInitialOperation()
     * @generated
     * @ordered
     */
    protected InitialOperation initialOperation;

    /**
     * The default value of the '{@link #getIconPath() <em>Icon Path</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIconPath()
     * @generated
     * @ordered
     */
    protected static final String ICON_PATH_EDEFAULT = "/org.eclipse.sirius.ui/icons/full/obj16/PaneBasedSelectionWizardDescription.gif"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getIconPath() <em>Icon Path</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIconPath()
     * @generated
     * @ordered
     */
    protected String iconPath = PaneBasedSelectionWizardDescriptionImpl.ICON_PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getWindowTitle() <em>Window Title</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getWindowTitle()
     * @generated
     * @ordered
     */
    protected static final String WINDOW_TITLE_EDEFAULT = "Selection Wizard"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getWindowTitle() <em>Window Title</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getWindowTitle()
     * @generated
     * @ordered
     */
    protected String windowTitle = PaneBasedSelectionWizardDescriptionImpl.WINDOW_TITLE_EDEFAULT;

    /**
     * The default value of the '{@link #getWindowImagePath()
     * <em>Window Image Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getWindowImagePath()
     * @generated
     * @ordered
     */
    protected static final String WINDOW_IMAGE_PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getWindowImagePath()
     * <em>Window Image Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getWindowImagePath()
     * @generated
     * @ordered
     */
    protected String windowImagePath = PaneBasedSelectionWizardDescriptionImpl.WINDOW_IMAGE_PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getMessage() <em>Message</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMessage()
     * @generated
     * @ordered
     */
    protected static final String MESSAGE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMessage() <em>Message</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMessage()
     * @generated
     * @ordered
     */
    protected String message = PaneBasedSelectionWizardDescriptionImpl.MESSAGE_EDEFAULT;

    /**
     * The default value of the '{@link #getChoiceOfValuesMessage()
     * <em>Choice Of Values Message</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getChoiceOfValuesMessage()
     * @generated
     * @ordered
     */
    protected static final String CHOICE_OF_VALUES_MESSAGE_EDEFAULT = "Choice of values";

    /**
     * The cached value of the '{@link #getChoiceOfValuesMessage()
     * <em>Choice Of Values Message</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getChoiceOfValuesMessage()
     * @generated
     * @ordered
     */
    protected String choiceOfValuesMessage = PaneBasedSelectionWizardDescriptionImpl.CHOICE_OF_VALUES_MESSAGE_EDEFAULT;

    /**
     * The default value of the '{@link #getCandidatesExpression()
     * <em>Candidates Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getCandidatesExpression()
     * @generated
     * @ordered
     */
    protected static final String CANDIDATES_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCandidatesExpression()
     * <em>Candidates Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getCandidatesExpression()
     * @generated
     * @ordered
     */
    protected String candidatesExpression = PaneBasedSelectionWizardDescriptionImpl.CANDIDATES_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #isTree() <em>Tree</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isTree()
     * @generated
     * @ordered
     */
    protected static final boolean TREE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isTree() <em>Tree</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isTree()
     * @generated
     * @ordered
     */
    protected boolean tree = PaneBasedSelectionWizardDescriptionImpl.TREE_EDEFAULT;

    /**
     * The default value of the '{@link #getRootExpression()
     * <em>Root Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getRootExpression()
     * @generated
     * @ordered
     */
    protected static final String ROOT_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRootExpression()
     * <em>Root Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getRootExpression()
     * @generated
     * @ordered
     */
    protected String rootExpression = PaneBasedSelectionWizardDescriptionImpl.ROOT_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getChildrenExpression()
     * <em>Children Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getChildrenExpression()
     * @generated
     * @ordered
     */
    protected static final String CHILDREN_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getChildrenExpression()
     * <em>Children Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getChildrenExpression()
     * @generated
     * @ordered
     */
    protected String childrenExpression = PaneBasedSelectionWizardDescriptionImpl.CHILDREN_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getSelectedValuesMessage()
     * <em>Selected Values Message</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getSelectedValuesMessage()
     * @generated
     * @ordered
     */
    protected static final String SELECTED_VALUES_MESSAGE_EDEFAULT = "Selected values";

    /**
     * The cached value of the '{@link #getSelectedValuesMessage()
     * <em>Selected Values Message</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getSelectedValuesMessage()
     * @generated
     * @ordered
     */
    protected String selectedValuesMessage = PaneBasedSelectionWizardDescriptionImpl.SELECTED_VALUES_MESSAGE_EDEFAULT;

    /**
     * The default value of the '{@link #getPreSelectedCandidatesExpression()
     * <em>Pre Selected Candidates Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPreSelectedCandidatesExpression()
     * @generated
     * @ordered
     */
    protected static final String PRE_SELECTED_CANDIDATES_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPreSelectedCandidatesExpression()
     * <em>Pre Selected Candidates Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPreSelectedCandidatesExpression()
     * @generated
     * @ordered
     */
    protected String preSelectedCandidatesExpression = PaneBasedSelectionWizardDescriptionImpl.PRE_SELECTED_CANDIDATES_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected PaneBasedSelectionWizardDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.PANE_BASED_SELECTION_WIZARD_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ElementSelectVariable getElement() {
        if (element != null && element.eIsProxy()) {
            InternalEObject oldElement = (InternalEObject) element;
            element = (ElementSelectVariable) eResolveProxy(oldElement);
            if (element != oldElement) {
                InternalEObject newElement = (InternalEObject) element;
                NotificationChain msgs = oldElement.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT, null, null);
                if (newElement.eInternalContainer() == null) {
                    msgs = newElement.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT, oldElement, element));
                }
            }
        }
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ElementSelectVariable basicGetElement() {
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetElement(ElementSelectVariable newElement, NotificationChain msgs) {
        ElementSelectVariable oldElement = element;
        element = newElement;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT, oldElement, newElement);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setElement(ElementSelectVariable newElement) {
        if (newElement != element) {
            NotificationChain msgs = null;
            if (element != null) {
                msgs = ((InternalEObject) element).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT, null, msgs);
            }
            if (newElement != null) {
                msgs = ((InternalEObject) newElement).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT, null, msgs);
            }
            msgs = basicSetElement(newElement, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT, newElement, newElement));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ContainerViewVariable getContainerView() {
        if (containerView != null && containerView.eIsProxy()) {
            InternalEObject oldContainerView = (InternalEObject) containerView;
            containerView = (ContainerViewVariable) eResolveProxy(oldContainerView);
            if (containerView != oldContainerView) {
                InternalEObject newContainerView = (InternalEObject) containerView;
                NotificationChain msgs = oldContainerView
                        .eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW, null, null);
                if (newContainerView.eInternalContainer() == null) {
                    msgs = newContainerView.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW, oldContainerView, containerView));
                }
            }
        }
        return containerView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ContainerViewVariable basicGetContainerView() {
        return containerView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetContainerView(ContainerViewVariable newContainerView, NotificationChain msgs) {
        ContainerViewVariable oldContainerView = containerView;
        containerView = newContainerView;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW, oldContainerView, newContainerView);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setContainerView(ContainerViewVariable newContainerView) {
        if (newContainerView != containerView) {
            NotificationChain msgs = null;
            if (containerView != null) {
                msgs = ((InternalEObject) containerView).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW, null, msgs);
            }
            if (newContainerView != null) {
                msgs = ((InternalEObject) newContainerView).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW, null, msgs);
            }
            msgs = basicSetContainerView(newContainerView, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW, newContainerView, newContainerView));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public SelectContainerVariable getContainer() {
        if (container != null && container.eIsProxy()) {
            InternalEObject oldContainer = (InternalEObject) container;
            container = (SelectContainerVariable) eResolveProxy(oldContainer);
            if (container != oldContainer) {
                InternalEObject newContainer = (InternalEObject) container;
                NotificationChain msgs = oldContainer.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER, null, null);
                if (newContainer.eInternalContainer() == null) {
                    msgs = newContainer.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER, oldContainer, container));
                }
            }
        }
        return container;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SelectContainerVariable basicGetContainer() {
        return container;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetContainer(SelectContainerVariable newContainer, NotificationChain msgs) {
        SelectContainerVariable oldContainer = container;
        container = newContainer;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER, oldContainer, newContainer);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setContainer(SelectContainerVariable newContainer) {
        if (newContainer != container) {
            NotificationChain msgs = null;
            if (container != null) {
                msgs = ((InternalEObject) container).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER, null, msgs);
            }
            if (newContainer != null) {
                msgs = ((InternalEObject) newContainer).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER, null, msgs);
            }
            msgs = basicSetContainer(newContainer, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER, newContainer, newContainer));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public InitialOperation getInitialOperation() {
        if (initialOperation != null && initialOperation.eIsProxy()) {
            InternalEObject oldInitialOperation = (InternalEObject) initialOperation;
            initialOperation = (InitialOperation) eResolveProxy(oldInitialOperation);
            if (initialOperation != oldInitialOperation) {
                InternalEObject newInitialOperation = (InternalEObject) initialOperation;
                NotificationChain msgs = oldInitialOperation.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION,
                        null, null);
                if (newInitialOperation.eInternalContainer() == null) {
                    msgs = newInitialOperation.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, initialOperation));
                }
            }
        }
        return initialOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public InitialOperation basicGetInitialOperation() {
        return initialOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetInitialOperation(InitialOperation newInitialOperation, NotificationChain msgs) {
        InitialOperation oldInitialOperation = initialOperation;
        initialOperation = newInitialOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation,
                    newInitialOperation);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setInitialOperation(InitialOperation newInitialOperation) {
        if (newInitialOperation != initialOperation) {
            NotificationChain msgs = null;
            if (initialOperation != null) {
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION, null,
                        msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION, null,
                        msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getIconPath() {
        return iconPath;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setIconPath(String newIconPath) {
        String oldIconPath = iconPath;
        iconPath = newIconPath;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ICON_PATH, oldIconPath, iconPath));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getWindowTitle() {
        return windowTitle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setWindowTitle(String newWindowTitle) {
        String oldWindowTitle = windowTitle;
        windowTitle = newWindowTitle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE, oldWindowTitle, windowTitle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getWindowImagePath() {
        return windowImagePath;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setWindowImagePath(String newWindowImagePath) {
        String oldWindowImagePath = windowImagePath;
        windowImagePath = newWindowImagePath;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH, oldWindowImagePath, windowImagePath));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setMessage(String newMessage) {
        String oldMessage = message;
        message = newMessage;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__MESSAGE, oldMessage, message));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getChoiceOfValuesMessage() {
        return choiceOfValuesMessage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setChoiceOfValuesMessage(String newChoiceOfValuesMessage) {
        String oldChoiceOfValuesMessage = choiceOfValuesMessage;
        choiceOfValuesMessage = newChoiceOfValuesMessage;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHOICE_OF_VALUES_MESSAGE, oldChoiceOfValuesMessage, choiceOfValuesMessage));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getCandidatesExpression() {
        return candidatesExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setCandidatesExpression(String newCandidatesExpression) {
        String oldCandidatesExpression = candidatesExpression;
        candidatesExpression = newCandidatesExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CANDIDATES_EXPRESSION, oldCandidatesExpression, candidatesExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isTree() {
        return tree;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setTree(boolean newTree) {
        boolean oldTree = tree;
        tree = newTree;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__TREE, oldTree, tree));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getRootExpression() {
        return rootExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setRootExpression(String newRootExpression) {
        String oldRootExpression = rootExpression;
        rootExpression = newRootExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ROOT_EXPRESSION, oldRootExpression, rootExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getChildrenExpression() {
        return childrenExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setChildrenExpression(String newChildrenExpression) {
        String oldChildrenExpression = childrenExpression;
        childrenExpression = newChildrenExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHILDREN_EXPRESSION, oldChildrenExpression, childrenExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getSelectedValuesMessage() {
        return selectedValuesMessage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSelectedValuesMessage(String newSelectedValuesMessage) {
        String oldSelectedValuesMessage = selectedValuesMessage;
        selectedValuesMessage = newSelectedValuesMessage;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__SELECTED_VALUES_MESSAGE, oldSelectedValuesMessage, selectedValuesMessage));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getPreSelectedCandidatesExpression() {
        return preSelectedCandidatesExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setPreSelectedCandidatesExpression(String newPreSelectedCandidatesExpression) {
        String oldPreSelectedCandidatesExpression = preSelectedCandidatesExpression;
        preSelectedCandidatesExpression = newPreSelectedCandidatesExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__PRE_SELECTED_CANDIDATES_EXPRESSION, oldPreSelectedCandidatesExpression,
                    preSelectedCandidatesExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT:
            return basicSetElement(null, msgs);
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW:
            return basicSetContainerView(null, msgs);
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER:
            return basicSetContainer(null, msgs);
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION:
            return basicSetInitialOperation(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT:
            if (resolve) {
                return getElement();
            }
            return basicGetElement();
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW:
            if (resolve) {
                return getContainerView();
            }
            return basicGetContainerView();
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER:
            if (resolve) {
                return getContainer();
            }
            return basicGetContainer();
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION:
            if (resolve) {
                return getInitialOperation();
            }
            return basicGetInitialOperation();
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ICON_PATH:
            return getIconPath();
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE:
            return getWindowTitle();
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH:
            return getWindowImagePath();
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__MESSAGE:
            return getMessage();
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHOICE_OF_VALUES_MESSAGE:
            return getChoiceOfValuesMessage();
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CANDIDATES_EXPRESSION:
            return getCandidatesExpression();
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__TREE:
            return isTree();
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ROOT_EXPRESSION:
            return getRootExpression();
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHILDREN_EXPRESSION:
            return getChildrenExpression();
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__SELECTED_VALUES_MESSAGE:
            return getSelectedValuesMessage();
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__PRE_SELECTED_CANDIDATES_EXPRESSION:
            return getPreSelectedCandidatesExpression();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT:
            setElement((ElementSelectVariable) newValue);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW:
            setContainerView((ContainerViewVariable) newValue);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER:
            setContainer((SelectContainerVariable) newValue);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) newValue);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ICON_PATH:
            setIconPath((String) newValue);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE:
            setWindowTitle((String) newValue);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH:
            setWindowImagePath((String) newValue);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__MESSAGE:
            setMessage((String) newValue);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHOICE_OF_VALUES_MESSAGE:
            setChoiceOfValuesMessage((String) newValue);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CANDIDATES_EXPRESSION:
            setCandidatesExpression((String) newValue);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__TREE:
            setTree((Boolean) newValue);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ROOT_EXPRESSION:
            setRootExpression((String) newValue);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHILDREN_EXPRESSION:
            setChildrenExpression((String) newValue);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__SELECTED_VALUES_MESSAGE:
            setSelectedValuesMessage((String) newValue);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__PRE_SELECTED_CANDIDATES_EXPRESSION:
            setPreSelectedCandidatesExpression((String) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT:
            setElement((ElementSelectVariable) null);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW:
            setContainerView((ContainerViewVariable) null);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER:
            setContainer((SelectContainerVariable) null);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) null);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ICON_PATH:
            setIconPath(PaneBasedSelectionWizardDescriptionImpl.ICON_PATH_EDEFAULT);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE:
            setWindowTitle(PaneBasedSelectionWizardDescriptionImpl.WINDOW_TITLE_EDEFAULT);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH:
            setWindowImagePath(PaneBasedSelectionWizardDescriptionImpl.WINDOW_IMAGE_PATH_EDEFAULT);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__MESSAGE:
            setMessage(PaneBasedSelectionWizardDescriptionImpl.MESSAGE_EDEFAULT);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHOICE_OF_VALUES_MESSAGE:
            setChoiceOfValuesMessage(PaneBasedSelectionWizardDescriptionImpl.CHOICE_OF_VALUES_MESSAGE_EDEFAULT);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CANDIDATES_EXPRESSION:
            setCandidatesExpression(PaneBasedSelectionWizardDescriptionImpl.CANDIDATES_EXPRESSION_EDEFAULT);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__TREE:
            setTree(PaneBasedSelectionWizardDescriptionImpl.TREE_EDEFAULT);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ROOT_EXPRESSION:
            setRootExpression(PaneBasedSelectionWizardDescriptionImpl.ROOT_EXPRESSION_EDEFAULT);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHILDREN_EXPRESSION:
            setChildrenExpression(PaneBasedSelectionWizardDescriptionImpl.CHILDREN_EXPRESSION_EDEFAULT);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__SELECTED_VALUES_MESSAGE:
            setSelectedValuesMessage(PaneBasedSelectionWizardDescriptionImpl.SELECTED_VALUES_MESSAGE_EDEFAULT);
            return;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__PRE_SELECTED_CANDIDATES_EXPRESSION:
            setPreSelectedCandidatesExpression(PaneBasedSelectionWizardDescriptionImpl.PRE_SELECTED_CANDIDATES_EXPRESSION_EDEFAULT);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT:
            return element != null;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW:
            return containerView != null;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER:
            return container != null;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION:
            return initialOperation != null;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ICON_PATH:
            return PaneBasedSelectionWizardDescriptionImpl.ICON_PATH_EDEFAULT == null ? iconPath != null : !PaneBasedSelectionWizardDescriptionImpl.ICON_PATH_EDEFAULT.equals(iconPath);
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE:
            return PaneBasedSelectionWizardDescriptionImpl.WINDOW_TITLE_EDEFAULT == null ? windowTitle != null : !PaneBasedSelectionWizardDescriptionImpl.WINDOW_TITLE_EDEFAULT.equals(windowTitle);
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH:
            return PaneBasedSelectionWizardDescriptionImpl.WINDOW_IMAGE_PATH_EDEFAULT == null ? windowImagePath != null : !PaneBasedSelectionWizardDescriptionImpl.WINDOW_IMAGE_PATH_EDEFAULT
                    .equals(windowImagePath);
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__MESSAGE:
            return PaneBasedSelectionWizardDescriptionImpl.MESSAGE_EDEFAULT == null ? message != null : !PaneBasedSelectionWizardDescriptionImpl.MESSAGE_EDEFAULT.equals(message);
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHOICE_OF_VALUES_MESSAGE:
            return PaneBasedSelectionWizardDescriptionImpl.CHOICE_OF_VALUES_MESSAGE_EDEFAULT == null ? choiceOfValuesMessage != null
                    : !PaneBasedSelectionWizardDescriptionImpl.CHOICE_OF_VALUES_MESSAGE_EDEFAULT.equals(choiceOfValuesMessage);
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CANDIDATES_EXPRESSION:
            return PaneBasedSelectionWizardDescriptionImpl.CANDIDATES_EXPRESSION_EDEFAULT == null ? candidatesExpression != null
                    : !PaneBasedSelectionWizardDescriptionImpl.CANDIDATES_EXPRESSION_EDEFAULT.equals(candidatesExpression);
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__TREE:
            return tree != PaneBasedSelectionWizardDescriptionImpl.TREE_EDEFAULT;
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ROOT_EXPRESSION:
            return PaneBasedSelectionWizardDescriptionImpl.ROOT_EXPRESSION_EDEFAULT == null ? rootExpression != null : !PaneBasedSelectionWizardDescriptionImpl.ROOT_EXPRESSION_EDEFAULT
                    .equals(rootExpression);
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHILDREN_EXPRESSION:
            return PaneBasedSelectionWizardDescriptionImpl.CHILDREN_EXPRESSION_EDEFAULT == null ? childrenExpression != null : !PaneBasedSelectionWizardDescriptionImpl.CHILDREN_EXPRESSION_EDEFAULT
                    .equals(childrenExpression);
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__SELECTED_VALUES_MESSAGE:
            return PaneBasedSelectionWizardDescriptionImpl.SELECTED_VALUES_MESSAGE_EDEFAULT == null ? selectedValuesMessage != null
                    : !PaneBasedSelectionWizardDescriptionImpl.SELECTED_VALUES_MESSAGE_EDEFAULT.equals(selectedValuesMessage);
        case ToolPackage.PANE_BASED_SELECTION_WIZARD_DESCRIPTION__PRE_SELECTED_CANDIDATES_EXPRESSION:
            return PaneBasedSelectionWizardDescriptionImpl.PRE_SELECTED_CANDIDATES_EXPRESSION_EDEFAULT == null ? preSelectedCandidatesExpression != null
                    : !PaneBasedSelectionWizardDescriptionImpl.PRE_SELECTED_CANDIDATES_EXPRESSION_EDEFAULT.equals(preSelectedCandidatesExpression);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (iconPath: "); //$NON-NLS-1$
        result.append(iconPath);
        result.append(", windowTitle: "); //$NON-NLS-1$
        result.append(windowTitle);
        result.append(", windowImagePath: "); //$NON-NLS-1$
        result.append(windowImagePath);
        result.append(", message: "); //$NON-NLS-1$
        result.append(message);
        result.append(", choiceOfValuesMessage: "); //$NON-NLS-1$
        result.append(choiceOfValuesMessage);
        result.append(", candidatesExpression: "); //$NON-NLS-1$
        result.append(candidatesExpression);
        result.append(", tree: "); //$NON-NLS-1$
        result.append(tree);
        result.append(", rootExpression: "); //$NON-NLS-1$
        result.append(rootExpression);
        result.append(", childrenExpression: "); //$NON-NLS-1$
        result.append(childrenExpression);
        result.append(", selectedValuesMessage: "); //$NON-NLS-1$
        result.append(selectedValuesMessage);
        result.append(", preSelectedCandidatesExpression: "); //$NON-NLS-1$
        result.append(preSelectedCandidatesExpression);
        result.append(')');
        return result.toString();
    }

} // PaneBasedSelectionWizardDescriptionImpl
