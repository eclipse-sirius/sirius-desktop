/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration ' <em><b>Edge Arrows</b></em>', and
 * utility methods for working with them. <!-- end-user-doc --> <!-- begin-model-doc --> Decoration of a connection.
 * <!-- end-model-doc -->
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getEdgeArrows()
 * @model
 * @generated
 */
public enum EdgeArrows implements Enumerator {
    /**
     * The '<em><b>No Decoration</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #NO_DECORATION
     * @generated
     * @ordered
     */
    NO_DECORATION_LITERAL(0, "NoDecoration", "NoDecoration"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Output Arrow</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #OUTPUT_ARROW
     * @generated
     * @ordered
     */
    OUTPUT_ARROW_LITERAL(1, "OutputArrow", "OutputArrow"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Input Arrow</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #INPUT_ARROW
     * @generated
     * @ordered
     */
    INPUT_ARROW_LITERAL(2, "InputArrow", "InputArrow"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Output Closed Arrow</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #OUTPUT_CLOSED_ARROW
     * @generated
     * @ordered
     */
    OUTPUT_CLOSED_ARROW_LITERAL(3, "OutputClosedArrow", "OutputClosedArrow"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Input Closed Arrow</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #INPUT_CLOSED_ARROW
     * @generated
     * @ordered
     */
    INPUT_CLOSED_ARROW_LITERAL(4, "InputClosedArrow", "InputClosedArrow"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Output Fill Closed Arrow</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #OUTPUT_FILL_CLOSED_ARROW
     * @generated
     * @ordered
     */
    OUTPUT_FILL_CLOSED_ARROW_LITERAL(5, "OutputFillClosedArrow", "OutputFillClosedArrow"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Input Fill Closed Arrow</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #INPUT_FILL_CLOSED_ARROW
     * @generated
     * @ordered
     */
    INPUT_FILL_CLOSED_ARROW_LITERAL(6, "InputFillClosedArrow", "InputFillClosedArrow"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Diamond</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #DIAMOND
     * @generated
     * @ordered
     */
    DIAMOND_LITERAL(7, "Diamond", "Diamond"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Fill Diamond</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #FILL_DIAMOND
     * @generated
     * @ordered
     */
    FILL_DIAMOND_LITERAL(8, "FillDiamond", "FillDiamond"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Input Arrow With Diamond</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #INPUT_ARROW_WITH_DIAMOND
     * @generated
     * @ordered
     */
    INPUT_ARROW_WITH_DIAMOND_LITERAL(9, "InputArrowWithDiamond", "InputArrowWithDiamond"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Input Arrow With Fill Diamond</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #INPUT_ARROW_WITH_FILL_DIAMOND
     * @generated
     * @ordered
     */
    INPUT_ARROW_WITH_FILL_DIAMOND_LITERAL(10, "InputArrowWithFillDiamond", "InputArrowWithFillDiamond"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Circle Plus</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #CIRCLE_PLUS
     * @generated
     * @ordered
     */
    CIRCLE_PLUS_LITERAL(11, "CirclePlus", "CirclePlus"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Dot</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #DOT
     * @generated
     * @ordered
     */
    DOT_LITERAL(12, "Dot", "Dot"), //$NON-NLS-1$//$NON-NLS-2$
    /**
     * The '<em><b>Input Arrow With Dot</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #INPUT_ARROW_WITH_DOT
     * @generated
     * @ordered
     */
    INPUT_ARROW_WITH_DOT_LITERAL(13, "InputArrowWithDot", "InputArrowWithDot"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Diamond With Dot</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #DIAMOND_WITH_DOT
     * @generated
     * @ordered
     */
    DIAMOND_WITH_DOT_LITERAL(14, "DiamondWithDot", "DiamondWithDot"), //$NON-NLS-1$//$NON-NLS-2$
    /**
     * The '<em><b>Fill Diamond With Dot</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #FILL_DIAMOND_WITH_DOT
     * @generated
     * @ordered
     */
    FILL_DIAMOND_WITH_DOT_LITERAL(15, "FillDiamondWithDot", "FillDiamondWithDot"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Input Arrow With Diamond And Dot</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #INPUT_ARROW_WITH_DIAMOND_AND_DOT
     * @generated
     * @ordered
     */
    INPUT_ARROW_WITH_DIAMOND_AND_DOT_LITERAL(16, "InputArrowWithDiamondAndDot", "InputArrowWithDiamondAndDot"), //$NON-NLS-1$ //$NON-NLS-2$
    /**
     * The '<em><b>Input Arrow With Fill Diamond And Dot</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #INPUT_ARROW_WITH_FILL_DIAMOND_AND_DOT
     * @generated
     * @ordered
     */
    INPUT_ARROW_WITH_FILL_DIAMOND_AND_DOT_LITERAL(17, "InputArrowWithFillDiamondAndDot", "InputArrowWithFillDiamondAndDot"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>No Decoration</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>No Decoration</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #NO_DECORATION_LITERAL
     * @model name="NoDecoration" annotation="Sirius imagePath='icons/full/decorator/noDecoration.gif'"
     * @generated
     * @ordered
     */
    public static final int NO_DECORATION = 0;

    /**
     * The '<em><b>Output Arrow</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Output Arrow</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #OUTPUT_ARROW_LITERAL
     * @model name="OutputArrow" annotation="Sirius imagePath='icons/full/decorator/outputArrow.gif'"
     * @generated
     * @ordered
     */
    public static final int OUTPUT_ARROW = 1;

    /**
     * The '<em><b>Input Arrow</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Input Arrow</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #INPUT_ARROW_LITERAL
     * @model name="InputArrow" annotation="Sirius imagePath='icons/full/decorator/inputArrow.gif'"
     * @generated
     * @ordered
     */
    public static final int INPUT_ARROW = 2;

    /**
     * The '<em><b>Output Closed Arrow</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Output Closed Arrow</b></em>' literal object isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #OUTPUT_CLOSED_ARROW_LITERAL
     * @model name="OutputClosedArrow" annotation="Sirius imagePath='icons/full/decorator/outputClosedArrow.gif'"
     * @generated
     * @ordered
     */
    public static final int OUTPUT_CLOSED_ARROW = 3;

    /**
     * The '<em><b>Input Closed Arrow</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Input Closed Arrow</b></em>' literal object isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #INPUT_CLOSED_ARROW_LITERAL
     * @model name="InputClosedArrow" annotation="Sirius imagePath='icons/full/decorator/inputClosedArrow.gif'"
     * @generated
     * @ordered
     */
    public static final int INPUT_CLOSED_ARROW = 4;

    /**
     * The '<em><b>Output Fill Closed Arrow</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Output Fill Closed Arrow</b></em>' literal object isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #OUTPUT_FILL_CLOSED_ARROW_LITERAL
     * @model name="OutputFillClosedArrow" annotation="Sirius
     *        imagePath='icons/full/decorator/outputFillClosedArrow.gif'"
     * @generated
     * @ordered
     */
    public static final int OUTPUT_FILL_CLOSED_ARROW = 5;

    /**
     * The '<em><b>Input Fill Closed Arrow</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Input Fill Closed Arrow</b></em>' literal object isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #INPUT_FILL_CLOSED_ARROW_LITERAL
     * @model name="InputFillClosedArrow" annotation="Sirius imagePath='icons/full/decorator/inputFillClosedArrow.gif'"
     * @generated
     * @ordered
     */
    public static final int INPUT_FILL_CLOSED_ARROW = 6;

    /**
     * The '<em><b>Diamond</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Diamond</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #DIAMOND_LITERAL
     * @model name="Diamond" annotation="Sirius imagePath='icons/full/decorator/diamond.gif'"
     * @generated
     * @ordered
     */
    public static final int DIAMOND = 7;

    /**
     * The '<em><b>Fill Diamond</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Fill Diamond</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #FILL_DIAMOND_LITERAL
     * @model name="FillDiamond" annotation="Sirius imagePath='icons/full/decorator/fillDiamond.gif'"
     * @generated
     * @ordered
     */
    public static final int FILL_DIAMOND = 8;

    /**
     * The '<em><b>Input Arrow With Diamond</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Input Arrow With Diamond</b></em>' literal object isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #INPUT_ARROW_WITH_DIAMOND_LITERAL
     * @model name="InputArrowWithDiamond" annotation="Sirius
     *        imagePath='icons/full/decorator/inputArrowWithDiamond.gif'"
     * @generated
     * @ordered
     */
    public static final int INPUT_ARROW_WITH_DIAMOND = 9;

    /**
     * The '<em><b>Input Arrow With Fill Diamond</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Input Arrow With Fill Diamond</b></em>' literal object isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #INPUT_ARROW_WITH_FILL_DIAMOND_LITERAL
     * @model name="InputArrowWithFillDiamond" annotation="Sirius
     *        imagePath='icons/full/decorator/inputArrowWithFillDiamond.gif'"
     * @generated
     * @ordered
     */
    public static final int INPUT_ARROW_WITH_FILL_DIAMOND = 10;

    /**
     * The '<em><b>Circle Plus</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #CIRCLE_PLUS_LITERAL
     * @model name="CirclePlus" annotation="Sirius imagePath='icons/full/decorator/circlePlus.gif'"
     * @generated
     * @ordered
     */
    public static final int CIRCLE_PLUS = 11;

    /**
     * The '<em><b>Dot</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #DOT_LITERAL
     * @model name="Dot" annotation="Sirius imagePath='icons/full/decorator/dot.gif'"
     * @generated
     * @ordered
     */
    public static final int DOT = 12;

    /**
     * The '<em><b>Input Arrow With Dot</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #INPUT_ARROW_WITH_DOT_LITERAL
     * @model name="InputArrowWithDot" annotation="Sirius imagePath='icons/full/decorator/inputArrowWithDot.gif'"
     * @generated
     * @ordered
     */
    public static final int INPUT_ARROW_WITH_DOT = 13;

    /**
     * The '<em><b>Diamond With Dot</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #DIAMOND_WITH_DOT_LITERAL
     * @model name="DiamondWithDot" annotation="Sirius imagePath='icons/full/decorator/diamondWithDot.gif'"
     * @generated
     * @ordered
     */
    public static final int DIAMOND_WITH_DOT = 14;

    /**
     * The '<em><b>Fill Diamond With Dot</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #FILL_DIAMOND_WITH_DOT_LITERAL
     * @model name="FillDiamondWithDot" annotation="Sirius imagePath='icons/full/decorator/fillDiamondWithDot.gif'"
     * @generated
     * @ordered
     */
    public static final int FILL_DIAMOND_WITH_DOT = 15;

    /**
     * The '<em><b>Input Arrow With Diamond And Dot</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #INPUT_ARROW_WITH_DIAMOND_AND_DOT_LITERAL
     * @model name="InputArrowWithDiamondAndDot" annotation="Sirius
     *        imagePath='icons/full/decorator/inputArrowWithDiamondAndDot.gif'"
     * @generated
     * @ordered
     */
    public static final int INPUT_ARROW_WITH_DIAMOND_AND_DOT = 16;

    /**
     * The '<em><b>Input Arrow With Fill Diamond And Dot</b></em>' literal value. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #INPUT_ARROW_WITH_FILL_DIAMOND_AND_DOT_LITERAL
     * @model name="InputArrowWithFillDiamondAndDot" annotation="Sirius
     *        imagePath='icons/full/decorator/inputArrowWithFillDiamondAndDot.gif'"
     * @generated
     * @ordered
     */
    public static final int INPUT_ARROW_WITH_FILL_DIAMOND_AND_DOT = 17;

    /**
     * An array of all the '<em><b>Edge Arrows</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final EdgeArrows[] VALUES_ARRAY = new EdgeArrows[] { NO_DECORATION_LITERAL, OUTPUT_ARROW_LITERAL, INPUT_ARROW_LITERAL, OUTPUT_CLOSED_ARROW_LITERAL, INPUT_CLOSED_ARROW_LITERAL,
            OUTPUT_FILL_CLOSED_ARROW_LITERAL, INPUT_FILL_CLOSED_ARROW_LITERAL, DIAMOND_LITERAL, FILL_DIAMOND_LITERAL, INPUT_ARROW_WITH_DIAMOND_LITERAL, INPUT_ARROW_WITH_FILL_DIAMOND_LITERAL,
            CIRCLE_PLUS_LITERAL, DOT_LITERAL, INPUT_ARROW_WITH_DOT_LITERAL, DIAMOND_WITH_DOT_LITERAL, FILL_DIAMOND_WITH_DOT_LITERAL, INPUT_ARROW_WITH_DIAMOND_AND_DOT_LITERAL,
            INPUT_ARROW_WITH_FILL_DIAMOND_AND_DOT_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Edge Arrows</b></em>' enumerators. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public static final List<EdgeArrows> VALUES = Collections.unmodifiableList(Arrays.asList(EdgeArrows.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Edge Arrows</b></em>' literal with the specified literal value. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static EdgeArrows get(String literal) {
        for (EdgeArrows result : EdgeArrows.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Edge Arrows</b></em>' literal with the specified name. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static EdgeArrows getByName(String name) {
        for (EdgeArrows result : EdgeArrows.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Edge Arrows</b></em>' literal with the specified integer value. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static EdgeArrows get(int value) {
        switch (value) {
        case NO_DECORATION:
            return NO_DECORATION_LITERAL;
        case OUTPUT_ARROW:
            return OUTPUT_ARROW_LITERAL;
        case INPUT_ARROW:
            return INPUT_ARROW_LITERAL;
        case OUTPUT_CLOSED_ARROW:
            return OUTPUT_CLOSED_ARROW_LITERAL;
        case INPUT_CLOSED_ARROW:
            return INPUT_CLOSED_ARROW_LITERAL;
        case OUTPUT_FILL_CLOSED_ARROW:
            return OUTPUT_FILL_CLOSED_ARROW_LITERAL;
        case INPUT_FILL_CLOSED_ARROW:
            return INPUT_FILL_CLOSED_ARROW_LITERAL;
        case DIAMOND:
            return DIAMOND_LITERAL;
        case FILL_DIAMOND:
            return FILL_DIAMOND_LITERAL;
        case INPUT_ARROW_WITH_DIAMOND:
            return INPUT_ARROW_WITH_DIAMOND_LITERAL;
        case INPUT_ARROW_WITH_FILL_DIAMOND:
            return INPUT_ARROW_WITH_FILL_DIAMOND_LITERAL;
        case CIRCLE_PLUS:
            return CIRCLE_PLUS_LITERAL;
        case DOT:
            return DOT_LITERAL;
        case INPUT_ARROW_WITH_DOT:
            return INPUT_ARROW_WITH_DOT_LITERAL;
        case DIAMOND_WITH_DOT:
            return DIAMOND_WITH_DOT_LITERAL;
        case FILL_DIAMOND_WITH_DOT:
            return FILL_DIAMOND_WITH_DOT_LITERAL;
        case INPUT_ARROW_WITH_DIAMOND_AND_DOT:
            return INPUT_ARROW_WITH_DIAMOND_AND_DOT_LITERAL;
        case INPUT_ARROW_WITH_FILL_DIAMOND_AND_DOT:
            return INPUT_ARROW_WITH_FILL_DIAMOND_AND_DOT_LITERAL;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EdgeArrows(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getLiteral() {
        return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }

} // EdgeArrows
