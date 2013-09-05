/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.description.filter.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.sirius.business.internal.metamodel.description.filter.spec.CompositeFilterDescriptionSpec;
import org.eclipse.sirius.business.internal.metamodel.description.filter.spec.MappingFilterSpec;
import org.eclipse.sirius.business.internal.metamodel.description.filter.spec.VariableFilterSpec;
import org.eclipse.sirius.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.description.filter.FilterFactory;
import org.eclipse.sirius.description.filter.FilterKind;
import org.eclipse.sirius.description.filter.FilterPackage;
import org.eclipse.sirius.description.filter.FilterVariable;
import org.eclipse.sirius.description.filter.MappingFilter;
import org.eclipse.sirius.description.filter.VariableFilter;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class FilterFactoryImpl extends EFactoryImpl implements FilterFactory {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static FilterFactory init() {
        try {
            FilterFactory theFilterFactory = (FilterFactory) EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/sirius/description/filter/1.1.0");
            if (theFilterFactory != null) {
                return theFilterFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new FilterFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public FilterFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case FilterPackage.MAPPING_FILTER:
            return createMappingFilter();
        case FilterPackage.COMPOSITE_FILTER_DESCRIPTION:
            return createCompositeFilterDescription();
        case FilterPackage.VARIABLE_FILTER:
            return createVariableFilter();
        case FilterPackage.FILTER_VARIABLE:
            return createFilterVariable();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
        case FilterPackage.FILTER_KIND:
            return createFilterKindFromString(eDataType, initialValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
        case FilterPackage.FILTER_KIND:
            return convertFilterKindToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public MappingFilter createMappingFilter() {
        MappingFilterImpl mappingFilter = new MappingFilterSpec();
        return mappingFilter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public CompositeFilterDescription createCompositeFilterDescription() {
        CompositeFilterDescriptionImpl compositeFilterDescription = new CompositeFilterDescriptionSpec();
        return compositeFilterDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public VariableFilter createVariableFilter() {
        VariableFilterImpl variableFilter = new VariableFilterSpec();
        return variableFilter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FilterVariable createFilterVariable() {
        FilterVariableImpl filterVariable = new FilterVariableImpl();
        return filterVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FilterKind createFilterKindFromString(EDataType eDataType, String initialValue) {
        FilterKind result = FilterKind.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertFilterKindToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FilterPackage getFilterPackage() {
        return (FilterPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static FilterPackage getPackage() {
        return FilterPackage.eINSTANCE;
    }

} // FilterFactoryImpl
