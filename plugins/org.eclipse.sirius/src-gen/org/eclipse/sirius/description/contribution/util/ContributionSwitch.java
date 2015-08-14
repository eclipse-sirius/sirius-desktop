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
package org.eclipse.sirius.description.contribution.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.description.contribution.AddFeatureContribution;
import org.eclipse.sirius.description.contribution.ClearFeatureContribution;
import org.eclipse.sirius.description.contribution.ComputedEObjectReference;
import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.description.contribution.ContributionPackage;
import org.eclipse.sirius.description.contribution.ContributionPoint;
import org.eclipse.sirius.description.contribution.ContributionProvider;
import org.eclipse.sirius.description.contribution.DirectEObjectReference;
import org.eclipse.sirius.description.contribution.EObjectReference;
import org.eclipse.sirius.description.contribution.FeatureContribution;
import org.eclipse.sirius.description.contribution.IgnoreFeatureContribution;
import org.eclipse.sirius.description.contribution.RemoveFeatureContribution;
import org.eclipse.sirius.description.contribution.ResetFeatureContribution;
import org.eclipse.sirius.description.contribution.SetFeatureContribution;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.description.contribution.ContributionPackage
 * @generated
 */
public class ContributionSwitch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static ContributionPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public ContributionSwitch() {
        if (ContributionSwitch.modelPackage == null) {
            ContributionSwitch.modelPackage = ContributionPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == ContributionSwitch.modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        } else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case ContributionPackage.FEATURE_CONTRIBUTION: {
            FeatureContribution featureContribution = (FeatureContribution) theEObject;
            T result = caseFeatureContribution(featureContribution);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ContributionPackage.IGNORE_FEATURE_CONTRIBUTION: {
            IgnoreFeatureContribution ignoreFeatureContribution = (IgnoreFeatureContribution) theEObject;
            T result = caseIgnoreFeatureContribution(ignoreFeatureContribution);
            if (result == null) {
                result = caseFeatureContribution(ignoreFeatureContribution);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ContributionPackage.SET_FEATURE_CONTRIBUTION: {
            SetFeatureContribution setFeatureContribution = (SetFeatureContribution) theEObject;
            T result = caseSetFeatureContribution(setFeatureContribution);
            if (result == null) {
                result = caseFeatureContribution(setFeatureContribution);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ContributionPackage.ADD_FEATURE_CONTRIBUTION: {
            AddFeatureContribution addFeatureContribution = (AddFeatureContribution) theEObject;
            T result = caseAddFeatureContribution(addFeatureContribution);
            if (result == null) {
                result = caseFeatureContribution(addFeatureContribution);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ContributionPackage.REMOVE_FEATURE_CONTRIBUTION: {
            RemoveFeatureContribution removeFeatureContribution = (RemoveFeatureContribution) theEObject;
            T result = caseRemoveFeatureContribution(removeFeatureContribution);
            if (result == null) {
                result = caseFeatureContribution(removeFeatureContribution);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ContributionPackage.CLEAR_FEATURE_CONTRIBUTION: {
            ClearFeatureContribution clearFeatureContribution = (ClearFeatureContribution) theEObject;
            T result = caseClearFeatureContribution(clearFeatureContribution);
            if (result == null) {
                result = caseFeatureContribution(clearFeatureContribution);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ContributionPackage.RESET_FEATURE_CONTRIBUTION: {
            ResetFeatureContribution resetFeatureContribution = (ResetFeatureContribution) theEObject;
            T result = caseResetFeatureContribution(resetFeatureContribution);
            if (result == null) {
                result = caseFeatureContribution(resetFeatureContribution);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ContributionPackage.EOBJECT_REFERENCE: {
            EObjectReference eObjectReference = (EObjectReference) theEObject;
            T result = caseEObjectReference(eObjectReference);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ContributionPackage.DIRECT_EOBJECT_REFERENCE: {
            DirectEObjectReference directEObjectReference = (DirectEObjectReference) theEObject;
            T result = caseDirectEObjectReference(directEObjectReference);
            if (result == null) {
                result = caseEObjectReference(directEObjectReference);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ContributionPackage.COMPUTED_EOBJECT_REFERENCE: {
            ComputedEObjectReference computedEObjectReference = (ComputedEObjectReference) theEObject;
            T result = caseComputedEObjectReference(computedEObjectReference);
            if (result == null) {
                result = caseEObjectReference(computedEObjectReference);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ContributionPackage.CONTRIBUTION: {
            Contribution contribution = (Contribution) theEObject;
            T result = caseContribution(contribution);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ContributionPackage.CONTRIBUTION_PROVIDER: {
            ContributionProvider contributionProvider = (ContributionProvider) theEObject;
            T result = caseContributionProvider(contributionProvider);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ContributionPackage.CONTRIBUTION_POINT: {
            ContributionPoint contributionPoint = (ContributionPoint) theEObject;
            T result = caseContributionPoint(contributionPoint);
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
     * Returns the result of interpreting the object as an instance of '
     * <em>Feature Contribution</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Feature Contribution</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFeatureContribution(FeatureContribution object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Ignore Feature Contribution</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Ignore Feature Contribution</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIgnoreFeatureContribution(IgnoreFeatureContribution object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Set Feature Contribution</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Set Feature Contribution</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSetFeatureContribution(SetFeatureContribution object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Add Feature Contribution</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Add Feature Contribution</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAddFeatureContribution(AddFeatureContribution object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Remove Feature Contribution</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Remove Feature Contribution</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRemoveFeatureContribution(RemoveFeatureContribution object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Clear Feature Contribution</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Clear Feature Contribution</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseClearFeatureContribution(ClearFeatureContribution object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Reset Feature Contribution</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Reset Feature Contribution</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseResetFeatureContribution(ResetFeatureContribution object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>EObject Reference</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>EObject Reference</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEObjectReference(EObjectReference object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Direct EObject Reference</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Direct EObject Reference</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDirectEObjectReference(DirectEObjectReference object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Computed EObject Reference</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Computed EObject Reference</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComputedEObjectReference(ComputedEObjectReference object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Contribution</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Contribution</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContribution(Contribution object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Provider</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Provider</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContributionProvider(ContributionProvider object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Point</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Point</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContributionPoint(ContributionPoint object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>EObject</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch, but this is
     * the last case anyway. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} // ContributionSwitch
