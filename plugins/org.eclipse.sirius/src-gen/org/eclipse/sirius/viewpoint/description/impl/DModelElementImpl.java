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
package org.eclipse.sirius.viewpoint.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EModelElementImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.business.internal.query.DModelElementInternalQuery;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DModel Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.DModelElementImpl#getEAnnotations
 * <em>EAnnotations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class DModelElementImpl extends MinimalEObjectImpl.Container implements DModelElement {
    /**
     * The cached value of the '{@link #getEAnnotations() <em>EAnnotations</em>}
     * ' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getEAnnotations()
     * @generated
     * @ordered
     */
    protected EList<DAnnotation> eAnnotations;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DModelElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.DMODEL_ELEMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<DAnnotation> getEAnnotations() {
        if (eAnnotations == null) {
            eAnnotations = new EObjectContainmentEList<DAnnotation>(DAnnotation.class, this, DescriptionPackage.DMODEL_ELEMENT__EANNOTATIONS);
        }
        return eAnnotations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DAnnotation getDAnnotation(String source) {
        return new DModelElementInternalQuery(this).getDAnnotation(source);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.DMODEL_ELEMENT__EANNOTATIONS:
            return ((InternalEList<?>) getEAnnotations()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.DMODEL_ELEMENT__EANNOTATIONS:
            return getEAnnotations();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case DescriptionPackage.DMODEL_ELEMENT__EANNOTATIONS:
            getEAnnotations().clear();
            getEAnnotations().addAll((Collection<? extends DAnnotation>) newValue);
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
        case DescriptionPackage.DMODEL_ELEMENT__EANNOTATIONS:
            getEAnnotations().clear();
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
        case DescriptionPackage.DMODEL_ELEMENT__EANNOTATIONS:
            return eAnnotations != null && !eAnnotations.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    @Override
    public String eURIFragmentSegment(EStructuralFeature eStructuralFeature, EObject eObject) {
        if (eObject instanceof ENamedElement) {
            ENamedElement eNamedElement = (ENamedElement) eObject;
            String name = eNamedElement.getName();
            if (name != null) {
                int count = 0;
                for (Object otherEObject : eContents()) {
                    if (otherEObject == eObject) {
                        break;
                    }
                    if (otherEObject instanceof ENamedElement) {
                        ENamedElement otherENamedElement = (ENamedElement) otherEObject;
                        if (name.equals(otherENamedElement.getName())) {
                            ++count;
                        }
                    }
                }
                name = DModelElementImpl.eEncodeValue(name);
                return count > 0 ? name + "." + count : name; //$NON-NLS-1$
            }
        } else if (eObject instanceof DAnnotation) {
            DAnnotation eAnnotation = (DAnnotation) eObject;
            String source = eAnnotation.getSource();
            if (source != null) {
                int count = 0;
                for (Object otherEObject : eContents()) {
                    if (otherEObject == eObject) {
                        break;
                    }
                    if (otherEObject instanceof DAnnotation) {
                        DAnnotation otherEAnnotation = (DAnnotation) otherEObject;
                        if (source.equals(otherEAnnotation.getSource())) {
                            ++count;
                        }
                    }
                }

                StringBuffer result = new StringBuffer(source.length() + 5);
                result.append('%');
                result.append(URI.encodeSegment(source, false));
                result.append('%');
                if (count > 0) {
                    result.append('.');
                    result.append(count);
                }
                return result.toString();
            }
        }
        return super.eURIFragmentSegment(eStructuralFeature, eObject);
    }

    @Override
    public EObject eObjectForURIFragmentSegment(String uriFragmentSegment) {
        if (uriFragmentSegment.length() > 0) {
            // Is the first character a special character, i.e., something other
            // than '@'?
            //
            char firstCharacter = uriFragmentSegment.charAt(0);
            if (firstCharacter != '@') {
                // Is it the start of a source URI of an annotation?
                //
                if (firstCharacter == '%') {
                    // Find the closing '%'
                    //
                    int index = uriFragmentSegment.lastIndexOf("%"); //$NON-NLS-1$
                    if (index != -1) {
                        // Decode all encoded characters.
                        //
                        String source = URI.decode(uriFragmentSegment.substring(1, index));

                        // Check for a count, i.e., a '.' followed by a number.
                        //
                        int count = 0;
                        ++index;
                        if (uriFragmentSegment.length() > index && uriFragmentSegment.charAt(index) == '.') {
                            try {
                                count = Integer.parseInt(uriFragmentSegment.substring(index + 1));
                            } catch (NumberFormatException exception) {
                                throw new WrappedException(exception);
                            }
                        }

                        // Look for the annotation with the matching source.
                        //
                        for (Object object : eContents()) {
                            if (object instanceof DAnnotation) {
                                DAnnotation eAnnotation = (DAnnotation) object;
                                if (source.equals(eAnnotation.getSource()) && count-- == 0) {
                                    return eAnnotation;
                                }
                            }
                        }
                    }
                } else {
                    // Look for trailing count.
                    //
                    int index = uriFragmentSegment.lastIndexOf("."); //$NON-NLS-1$
                    String name = index == -1 ? uriFragmentSegment : uriFragmentSegment.substring(0, index);
                    int count = 0;
                    if (index != -1) {
                        try {
                            count = Integer.parseInt(uriFragmentSegment.substring(index + 1));
                        } catch (NumberFormatException exception) {
                            // Interpret it as part of the name.
                            //
                            name = uriFragmentSegment;
                        }
                    }

                    name = URI.decode(name);

                    // Look for a matching named element.
                    //
                    for (Object object : eContents()) {
                        if (object instanceof ENamedElement) {
                            ENamedElement eNamedElement = (ENamedElement) object;
                            if (name.equals(eNamedElement.getName()) && count-- == 0) {
                                return eNamedElement;
                            }
                        }
                    }
                }

                return null;
            }
        }

        return super.eObjectForURIFragmentSegment(uriFragmentSegment);
    }

    private static final String[] ESCAPE = { "%00", "%01", "%02", "%03", "%04", "%05", "%06", "%07", "%08", "%09", "%0A", "%0B", "%0C", "%0D", "%0E", "%0F", "%10", "%11", "%12", "%13", "%14", "%15", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$ //$NON-NLS-17$ //$NON-NLS-18$ //$NON-NLS-19$ //$NON-NLS-20$ //$NON-NLS-21$ //$NON-NLS-22$
        "%16", "%17", "%18", "%19", "%1A", "%1B", "%1C", "%1D", "%1E", "%1F", "%20", null, "%22", "%23", null, "%25", "%26", "%27", null, null, null, null, "%2C", null, null, "%2F", null, null, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$ //$NON-NLS-17$ //$NON-NLS-18$
        null, null, null, null, null, null, null, null, "%3A", null, "%3C", null, "%3E", null, }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    /**
     * Returns the encoded value or the original, if no encoding was needed.
     *
     * @see EModelElementImpl#eURIFragmentSegment(EStructuralFeature, EObject)
     * @param value
     *            the value to be encoded.
     * @return the encoded value or the original, if no encoding was needed.
     */
    private static String eEncodeValue(String value) {
        int length = value.length();
        StringBuilder result = null;
        for (int i = 0; i < length; ++i) {
            char character = value.charAt(i);
            if (character < DModelElementImpl.ESCAPE.length) {
                String escape = DModelElementImpl.ESCAPE[character];
                if (escape != null) {
                    if (result == null) {
                        result = new StringBuilder(length + 2);
                        result.append(value, 0, i);
                    }
                    result.append(escape);
                    continue;
                }
            }
            if (result != null) {
                result.append(character);
            }
        }
        return result == null ? value : result.toString();
    }

} // DModelElementImpl
