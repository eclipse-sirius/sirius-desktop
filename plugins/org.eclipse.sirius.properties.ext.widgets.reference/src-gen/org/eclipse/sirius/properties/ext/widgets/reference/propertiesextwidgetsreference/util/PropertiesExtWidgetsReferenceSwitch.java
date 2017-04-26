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
package org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.sirius.properties.AbstractControlDescription;
import org.eclipse.sirius.properties.AbstractOverrideDescription;
import org.eclipse.sirius.properties.AbstractWidgetDescription;
import org.eclipse.sirius.properties.ControlDescription;
import org.eclipse.sirius.properties.WidgetConditionalStyle;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.properties.WidgetStyle;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceOverrideDescription;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetConditionalStyle;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetStyle;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage
 * @generated
 */
public class PropertiesExtWidgetsReferenceSwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static PropertiesExtWidgetsReferencePackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public PropertiesExtWidgetsReferenceSwitch() {
        if (PropertiesExtWidgetsReferenceSwitch.modelPackage == null) {
            PropertiesExtWidgetsReferenceSwitch.modelPackage = PropertiesExtWidgetsReferencePackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param ePackage
     *            the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == PropertiesExtWidgetsReferenceSwitch.modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION: {
            AbstractExtReferenceDescription abstractExtReferenceDescription = (AbstractExtReferenceDescription) theEObject;
            T result = caseAbstractExtReferenceDescription(abstractExtReferenceDescription);
            if (result == null) {
                result = caseAbstractWidgetDescription(abstractExtReferenceDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(abstractExtReferenceDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(abstractExtReferenceDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractExtReferenceDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION: {
            ExtReferenceDescription extReferenceDescription = (ExtReferenceDescription) theEObject;
            T result = caseExtReferenceDescription(extReferenceDescription);
            if (result == null) {
                result = caseAbstractExtReferenceDescription(extReferenceDescription);
            }
            if (result == null) {
                result = caseWidgetDescription(extReferenceDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(extReferenceDescription);
            }
            if (result == null) {
                result = caseControlDescription(extReferenceDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(extReferenceDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(extReferenceDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(extReferenceDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_WIDGET_STYLE: {
            ExtReferenceWidgetStyle extReferenceWidgetStyle = (ExtReferenceWidgetStyle) theEObject;
            T result = caseExtReferenceWidgetStyle(extReferenceWidgetStyle);
            if (result == null) {
                result = caseWidgetStyle(extReferenceWidgetStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_WIDGET_CONDITIONAL_STYLE: {
            ExtReferenceWidgetConditionalStyle extReferenceWidgetConditionalStyle = (ExtReferenceWidgetConditionalStyle) theEObject;
            T result = caseExtReferenceWidgetConditionalStyle(extReferenceWidgetConditionalStyle);
            if (result == null) {
                result = caseWidgetConditionalStyle(extReferenceWidgetConditionalStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_OVERRIDE_DESCRIPTION: {
            ExtReferenceOverrideDescription extReferenceOverrideDescription = (ExtReferenceOverrideDescription) theEObject;
            T result = caseExtReferenceOverrideDescription(extReferenceOverrideDescription);
            if (result == null) {
                result = caseAbstractExtReferenceDescription(extReferenceOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractOverrideDescription(extReferenceOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(extReferenceOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(extReferenceOverrideDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(extReferenceOverrideDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(extReferenceOverrideDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Ext Reference Description</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Ext Reference Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractExtReferenceDescription(AbstractExtReferenceDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Ext Reference Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Ext Reference Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExtReferenceDescription(ExtReferenceDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Ext Reference Widget Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Ext Reference Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExtReferenceWidgetStyle(ExtReferenceWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Ext Reference Widget Conditional
     * Style</em>'. <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Ext Reference Widget Conditional
     *         Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExtReferenceWidgetConditionalStyle(ExtReferenceWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Ext Reference Override Description</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Ext Reference Override Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExtReferenceOverrideDescription(ExtReferenceOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Identified Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Identified Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIdentifiedElement(IdentifiedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Documented Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Documented Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentedElement(DocumentedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Control Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Control Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractControlDescription(AbstractControlDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Widget Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Widget Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractWidgetDescription(AbstractWidgetDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Control Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Control Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseControlDescription(ControlDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Widget Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Widget Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWidgetDescription(WidgetDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Widget Style</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWidgetStyle(WidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Widget Conditional Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWidgetConditionalStyle(WidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Override Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Override Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractOverrideDescription(AbstractOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch, but this is the last case
     * anyway. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} // PropertiesExtWidgetsReferenceSwitch
