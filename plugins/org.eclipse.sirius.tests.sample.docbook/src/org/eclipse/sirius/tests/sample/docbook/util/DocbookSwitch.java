/*******************************************************************************
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.docbook.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.sirius.tests.sample.docbook.AbstractSect;
import org.eclipse.sirius.tests.sample.docbook.Author;
import org.eclipse.sirius.tests.sample.docbook.Book;
import org.eclipse.sirius.tests.sample.docbook.Chapter;
import org.eclipse.sirius.tests.sample.docbook.DocbookPackage;
import org.eclipse.sirius.tests.sample.docbook.Emphasis;
import org.eclipse.sirius.tests.sample.docbook.Example;
import org.eclipse.sirius.tests.sample.docbook.Info;
import org.eclipse.sirius.tests.sample.docbook.ItemizedList;
import org.eclipse.sirius.tests.sample.docbook.Link;
import org.eclipse.sirius.tests.sample.docbook.ListItem;
import org.eclipse.sirius.tests.sample.docbook.OrderedList;
import org.eclipse.sirius.tests.sample.docbook.Para;
import org.eclipse.sirius.tests.sample.docbook.Sect1;
import org.eclipse.sirius.tests.sample.docbook.Sect2;
import org.eclipse.sirius.tests.sample.docbook.Sect3;
import org.eclipse.sirius.tests.sample.docbook.SimpleList;
import org.eclipse.sirius.tests.sample.docbook.Title;
import org.eclipse.sirius.tests.sample.docbook.ULink;
import org.eclipse.sirius.tests.sample.docbook.XRef;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.docbook.DocbookPackage
 * @generated
 */
public class DocbookSwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static DocbookPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public DocbookSwitch() {
        if (DocbookSwitch.modelPackage == null) {
            DocbookSwitch.modelPackage = DocbookPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == DocbookSwitch.modelPackage;
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
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case DocbookPackage.BOOK: {
            Book book = (Book) theEObject;
            T result = caseBook(book);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.INFO: {
            Info info = (Info) theEObject;
            T result = caseInfo(info);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.AUTHOR: {
            Author author = (Author) theEObject;
            T result = caseAuthor(author);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.CHAPTER: {
            Chapter chapter = (Chapter) theEObject;
            T result = caseChapter(chapter);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.TITLE: {
            Title title = (Title) theEObject;
            T result = caseTitle(title);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.PARA: {
            Para para = (Para) theEObject;
            T result = casePara(para);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.SIMPLE_LIST: {
            SimpleList simpleList = (SimpleList) theEObject;
            T result = caseSimpleList(simpleList);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.ITEMIZED_LIST: {
            ItemizedList itemizedList = (ItemizedList) theEObject;
            T result = caseItemizedList(itemizedList);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.ORDERED_LIST: {
            OrderedList orderedList = (OrderedList) theEObject;
            T result = caseOrderedList(orderedList);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.SECT1: {
            Sect1 sect1 = (Sect1) theEObject;
            T result = caseSect1(sect1);
            if (result == null) {
                result = caseAbstractSect(sect1);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.ABSTRACT_SECT: {
            AbstractSect abstractSect = (AbstractSect) theEObject;
            T result = caseAbstractSect(abstractSect);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.SECT2: {
            Sect2 sect2 = (Sect2) theEObject;
            T result = caseSect2(sect2);
            if (result == null) {
                result = caseAbstractSect(sect2);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.EMPHASIS: {
            Emphasis emphasis = (Emphasis) theEObject;
            T result = caseEmphasis(emphasis);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.ULINK: {
            ULink uLink = (ULink) theEObject;
            T result = caseULink(uLink);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.LINK: {
            Link link = (Link) theEObject;
            T result = caseLink(link);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.XREF: {
            XRef xRef = (XRef) theEObject;
            T result = caseXRef(xRef);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.EXAMPLE: {
            Example example = (Example) theEObject;
            T result = caseExample(example);
            if (result == null) {
                result = caseAbstractSect(example);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.SECT3: {
            Sect3 sect3 = (Sect3) theEObject;
            T result = caseSect3(sect3);
            if (result == null) {
                result = caseAbstractSect(sect3);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DocbookPackage.LIST_ITEM: {
            ListItem listItem = (ListItem) theEObject;
            T result = caseListItem(listItem);
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
     * <em>Book</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Book</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBook(Book object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Info</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Info</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInfo(Info object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Author</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Author</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAuthor(Author object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Chapter</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Chapter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseChapter(Chapter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Title</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Title</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTitle(Title object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Para</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Para</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePara(Para object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Simple List</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Simple List</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSimpleList(SimpleList object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Itemized List</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Itemized List</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseItemizedList(ItemizedList object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Ordered List</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Ordered List</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOrderedList(OrderedList object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Sect1</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Sect1</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSect1(Sect1 object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Abstract Sect</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Abstract Sect</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractSect(AbstractSect object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Sect2</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Sect2</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSect2(Sect2 object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Emphasis</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Emphasis</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEmphasis(Emphasis object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>ULink</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>ULink</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseULink(ULink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Link</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLink(Link object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>XRef</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>XRef</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseXRef(XRef object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Example</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Example</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExample(Example object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Sect3</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Sect3</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSect3(Sect3 object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>List Item</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>List Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseListItem(ListItem object) {
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
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} // DocbookSwitch
