/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * This is used in order to test services resolution. See test case
 * {@link org.eclipse.sirius.common.acceleo.tests.ServicesTest ServicesTest}.
 * 
 * @author <a href="mailto:yann.mortier@obeo.fr">Yann Mortier</a>
 */
public class UMLServices {

    /**
     * Get the the actor name.
     * 
     * @param actor
     *            the UML2 Actor.
     * @return the name
     */
    public String actorNameService(Actor actor) {
        return "##" + actor.getName() + "##";
    }

    /**
     * Return all associations of the class <code>umlClass</code>.
     * 
     * @param umlClass
     *            the class.
     * @return all associations of the class <code>umlClass</code>.
     */
    public Collection<Association> getAssociations(Classifier umlClass) {
        return umlClass.getAssociations();
    }

    /**
     * Returns a human readable string of the multiplicity of the given element.
     * 
     * @param multiplicityElement
     *            an element that has a multiplicity.
     * @return a human readable string of the multiplicity of the given element.
     */
    public String multiplicityToString(MultiplicityElement multiplicityElement) {
        StringBuffer result = new StringBuffer();
        result.append("[");
        if (multiplicityElement.getLowerValue() != null) {
            result.append(multiplicityElement.getLowerValue().stringValue());
        } else {
            result.append(new Integer(multiplicityElement.getLower()).toString());
        }
        result.append("..");
        if (multiplicityElement.getUpperValue() != null) {
            result.append(multiplicityElement.getUpperValue().stringValue());
        } else {
            if (multiplicityElement.getLower() < 0) {
                result.append("*");
            } else {
                result.append(new Integer(multiplicityElement.getUpper()).toString());
            }
        }
        result.append("]");
        return result.toString();
    }

    /**
     * Returns all operations of the given classifier.
     * 
     * @param classifier
     *            the classifier
     * @return all operations of the given classifier.
     */
    public List<Operation> getOperationsP(Classifier classifier) {
        return classifier.getOperations();
    }

    /**
     * Returns a human readable string for the specified visibility.
     * 
     * @param visibilityString
     *            the visibility.
     * @return a human readable string for the specified visibility.
     */
    public String visibilityToString(String visibilityString) {
        VisibilityKind visibilityKind = VisibilityKind.getByName(visibilityString);
        switch (visibilityKind.getValue()) {
        case VisibilityKind.PACKAGE:
            return "";
        case VisibilityKind.PRIVATE:
            return "-";
        case VisibilityKind.PUBLIC:
            return "+";
        case VisibilityKind.PROTECTED:
            return "#";
        }
        return "";
    }

    /**
     * Convert a visibility to a {@link VisibilityKind}.
     * 
     * @param visibility
     *            the visibility (+, -, # or nothing).
     * @return the corresponding {@link VisibilityKind}.
     */
    public VisibilityKind getVisibility(final String visibility) {
        String visibilityTemp = visibility;
        if (visibilityTemp != null) {
            visibilityTemp = visibilityTemp.trim();
            if (visibilityTemp.equals("")) {
                return VisibilityKind.PACKAGE_LITERAL;
            } else if (visibilityTemp.equals("-")) {
                return VisibilityKind.PRIVATE_LITERAL;
            } else if (visibilityTemp.equals("+")) {
                return VisibilityKind.PUBLIC_LITERAL;
            } else if (visibilityTemp.equals("#")) {
                return VisibilityKind.PROTECTED_LITERAL;
            } else {
                if (VisibilityKind.getByName(visibilityTemp) != null) {
                    return VisibilityKind.getByName(visibilityTemp);
                }
            }
        }
        return VisibilityKind.PACKAGE_LITERAL;
    }

    /**
     * Return the type having the name <code>typeName</code>.
     * 
     * @param current
     *            an object of the model.
     * @param typeName
     *            the name of the type.
     * @return the type having the name <code>typeName</code>.
     */
    public Type getType(EObject current, String typeName) {
        EObject element = EcoreUtil.getRootContainer(current);
        Type result = null;
        Iterator<EObject> iterContent = element.eAllContents();
        while (iterContent.hasNext() && result == null) {
            Object next = iterContent.next();
            if (next instanceof Type) {
                if (((Type) next).getName().equalsIgnoreCase(typeName)) {
                    result = (Type) next;
                }
            }
        }
        return result;
    }

    /**
     * Return {@link ExecutionSpecification}s that overlaps the current
     * execution specification.
     * 
     * @param current
     *            an execution specification.
     * @return {@link ExecutionSpecification}s that overlaps the current
     *         execution specification.
     */
    public List<?> getOverlappingExecutionSpecifications(ExecutionSpecification current) {
        return Collections.emptyList();
    }

    /**
     * Returns all messages that are sent by the specified execution
     * specification between the send event and the receive event.
     * 
     * @param executionSpecification
     *            the execution specification.
     * @return all messages that are sent by the specified execution
     *         specification between the send event and the receive event.
     */
    public List<?> getMessagesSentByExexSpec(ExecutionSpecification executionSpecification) {
        return Collections.emptyList();
    }

    /**
     * Returns the level of the given element.
     * 
     * @param element
     *            the element.
     * @return the level of the given element.
     */
    public int getLevel(final Element element) {
        Element elementTemp = element;
        int level = 0;
        while (!(elementTemp instanceof Model)) {
            elementTemp = elementTemp.getOwner();
            level++;
        }
        return level;
    }

    /**
     * Returns <code>a % b</code>.
     * 
     * @param a
     *            the first operand.
     * @param b
     *            the second operand.
     * @return <code>a % b</code>.
     */
    public int modulo(int a, int b) {
        return a % b;
    }

    /**
     * Creates an association from the.
     * 
     * @param property
     * @param classifier
     * @return the new Association.
     */
    public EObject createAssociation(Property property, Classifier classifier) {
        Association association = UMLFactory.eINSTANCE.createAssociation();
        association.setName(property.getName());
        property.setAssociation(association);
        association.createOwnedEnd("inverse" + property.getName(), property.getClass_());
        return association;
    }

    /**
     * Returns <code>true</code> if the given property is navigable.
     * 
     * @param property
     *            the property to test.
     * @return <code>true</code> if the given property is navigable.
     */
    public boolean isNavigableP(Property property) {
        return property.isNavigable();
    }

    /**
     * Returns the other end of the given association.
     * 
     * @param property
     *            one end of the association.
     * @param association
     *            the association.
     * @return the other end of the given association.
     */
    public Property getOtherEnd(Property property, Association association) {
        if (association.getMemberEnds().size() != 2) {
            return null;
        }
        if (association.getMemberEnds().get(0).equals(property)) {
            return association.getMemberEnds().get(1);
        } else {
            return association.getMemberEnds().get(0);
        }
    }

    /**
     * Returns all associations of the given type.
     * 
     * @param c
     *            the type.
     * @return all associations of the given type.
     */
    public Collection<Association> getAssociationP(Type c) {
        return c.getAssociations();
    }

    /**
     * Returns the textual rendering of an operation.
     * 
     * @param operation
     *            the Operation to render.
     * @return the textual rendering of the operation.
     */
    public String parametersToString(Operation operation) {
        StringBuffer sb = new StringBuffer();
        Iterator<Parameter> iterParameters = operation.getOwnedParameters().iterator();
        boolean first = true;
        while (iterParameters.hasNext()) {
            Parameter currentParameter = iterParameters.next();
            if (currentParameter.getDirection() != ParameterDirectionKind.RETURN_LITERAL) {
                if (first) {
                    first = false;
                } else {
                    sb.append(", ");
                }
                sb.append(currentParameter.getName());
                sb.append(":");
                sb.append(currentParameter.getType().getName());
            }
        }
        return sb.toString();
    }

    /**
     * Returns the string value of the value specification.
     * 
     * @param valueSpecification
     *            the value specification.
     * @return the string value of the value specification.
     */
    public String valueSpecToString(ValueSpecification valueSpecification) {
        return valueSpecification.stringValue();
    }

    public List<Package> getRootPackages(List<Package> input) {
        final List<Package> result = new LinkedList<Package>();
        for (final Package package_ : input) {
            List<Package> packages = new ArrayList<Package>(input);
            packages.remove(package_);
            if (!EcoreUtil.isAncestor(packages, package_)) {
                result.add(package_);
            }
        }
        return result;
    }

    public List<Package> getChildren(Package parent, List<Package> packList) {
        final List<Package> result = new LinkedList<Package>();
        Iterator<Package> iter = packList.iterator();
        while (iter.hasNext()) {

        }
        return result;
    }

    /**
     * Returns a qualified name of the given element. If a namespace has no name
     * (invalid model) it is removed from the qualified name.
     * 
     * @param element
     *            The element
     * @return a qualified name of the given element.
     */
    public String getQualifiedName(final NamedElement element) {
        StringBuilder qName = new StringBuilder();
        NamedElement current = element;
        while (current != null) {
            if (element != current)
                qName.insert(0, UMLUtil.QualifiedTextProvider.DEFAULT.getSeparator());
            qName.insert(0, current.getName());
            EObject container = current.eContainer();
            while (!(container instanceof NamedElement) && container != null) {
                container = container.eContainer();
            }
            current = (NamedElement) container;
        }
        return qName.toString();
    }
}
