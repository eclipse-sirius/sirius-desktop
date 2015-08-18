/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;

/**
 * Utility class to work with the GMF Annotation model.
 * 
 * @author cbrun
 * 
 */
public final class GMFNotationHelper {
    /*
     * This is an utility class, should not be initialized!
     */
    private GMFNotationHelper() {

    }

    /**
     * Return the X position of GMF {@link Node}.
     * 
     * @param gmfView
     *            the GMF {@link Node}.
     * @return the X position of the node
     */
    public static int getX(final Node gmfView) {
        if (gmfView.getLayoutConstraint() instanceof Bounds) {
            final Bounds nodeBounds = (Bounds) gmfView.getLayoutConstraint();
            return nodeBounds.getX();
        }
        return 0;
    }

    /**
     * Return the Y position of GMF {@link Node}.
     * 
     * @param gmfView
     *            the GMF {@link Node}.
     * @return the Y position of the node
     */
    public static int getY(final Node gmfView) {
        if (gmfView.getLayoutConstraint() instanceof Bounds) {
            final Bounds nodeBounds = (Bounds) gmfView.getLayoutConstraint();
            return nodeBounds.getY();
        }
        return 0;
    }

    /**
     * Return the width of GMF {@link Node}.
     * 
     * @param gmfView
     *            the GMF {@link Node}.
     * @return the width of the node
     */
    public static int getWidth(final Node gmfView) {
        if (gmfView.getLayoutConstraint() instanceof Bounds) {
            final Bounds nodeBounds = (Bounds) gmfView.getLayoutConstraint();
            return nodeBounds.getWidth();
        }
        return 0;
    }

    /**
     * Return the height of GMF {@link Node}.
     * 
     * @param gmfView
     *            the GMF {@link Node}.
     * @return the height of the node
     */
    public static int getHeight(final Node gmfView) {
        if (gmfView.getLayoutConstraint() instanceof Bounds) {
            final Bounds nodeBounds = (Bounds) gmfView.getLayoutConstraint();
            return nodeBounds.getHeight();
        }
        return 0;
    }

    /**
     * Return the text of a note.
     * 
     * @param note
     *            a GMF Note.
     * @return the text of the note.
     */
    public static String getNoteDescription(final Node note) {
        final Iterator<?> it = note.getStyles().iterator();
        while (it.hasNext()) {
            final Object obj = it.next();
            if (obj instanceof ShapeStyle) {
                return ((ShapeStyle) obj).getDescription();
            }
        }
        return ""; //$NON-NLS-1$
    }

    /**
     * return a list of nodes corresponding to notes.
     * 
     * @param gmfDiagram
     *            any GMF Diagram
     * @return a list of nodes corresponding to notes.
     */
    public static Collection<Node> getNotes(final Diagram gmfDiagram) {
        final Collection<Node> result = new ArrayList<Node>();
        final Iterator<EObject> it = gmfDiagram.eAllContents();
        while (it.hasNext()) {
            final EObject obj = it.next();
            if (obj instanceof Node && GMFNotationHelper.isNote((Node) obj)) {
                result.add((Node) obj);
            }
        }
        return result;
    }

    /**
     * Usefull to get all the note attachments of a GMF diagram.
     * 
     * @param gmfDiagram
     *            any GMF diagram.
     * @return a list of {@link Edge} which are the note attachments.
     */
    public static Collection<Edge> getNotesAttachments(final Diagram gmfDiagram) {
        final Collection<Edge> result = new ArrayList<Edge>();
        final Iterator<EObject> it = gmfDiagram.eAllContents();
        while (it.hasNext()) {
            final EObject obj = it.next();
            if (obj instanceof Edge && GMFNotationHelper.isNoteAttachment((Edge) obj)) {
                result.add((Edge) obj);
            }
        }
        return result;
    }

    /**
     * Tell whether an Edge is a note attachment or not.
     * 
     * @param obj
     *            any GMF Edge.
     * @return true if the edge is a note attachment.
     */
    public static boolean isNoteAttachment(final Edge obj) {
        return ViewType.NOTEATTACHMENT.equals(obj.getType());
    }

    /**
     * tell whether a node is a note or not.
     * 
     * @param node
     *            any node.
     * @return true if the node is a Note
     */
    public static boolean isNote(final Node node) {
        return ViewType.NOTE.equals(node.getType());
    }

    /**
     * Browse the resource content of the given element and retrieve GMF
     * {@link Diagram} corresponding to this element if available.
     * 
     * @param eObject
     *            any eObject of the Resource containing the diagrams.
     * @return the first GMF Diagram targeting the eObject, null if not found.
     * 
     * @deprecated as it is inefficient use
     *             {@link org.eclipse.sirius.diagram.business.api.query.EObjectQuery#getParentDiagram()}
     *             to get the viewpoint
     *             {@link org.eclipse.sirius.diagram.DDiagram} and from it use
     *             {@link org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil#findAssociatedGMFDiagram()}
     *             instead.
     */
    public static Diagram findGMFDiagram(final EObject eObject) {
        final Iterator<EObject> it = eObject.eResource().getAllContents();
        while (it.hasNext()) {
            final Object obj = it.next();
            if (obj instanceof Diagram) {
                if (((Diagram) obj).getElement() == eObject) {
                    return (Diagram) obj;
                }
            }
        }
        return null;
    }

    /**
     * Return the list of all the GMF diagrams contained in the resource.
     * 
     * @param resource
     *            the resource
     * @return the list of GMF diagram contained in the resource.
     */
    public static Collection<Diagram> getGMFDiagrams(final Resource resource) {
        final Collection<Diagram> result = new ArrayList<Diagram>();
        final Iterator<EObject> it = resource.getAllContents();
        while (it.hasNext()) {
            final EObject obj = it.next();
            if (obj instanceof Diagram) {
                result.add((Diagram) obj);
            }
        }
        return result;
    }

    /**
     * Create a new Note and attach it on the diagram.
     * 
     * @param container
     *            the container diagram.
     * @param noteText
     *            the content of the note.
     * @return the newly created note.
     */
    public static Node createNote(final Diagram container, final String noteText) {
        final Node note = ViewService.createNode(container, ViewType.NOTE, PreferencesHint.USE_DEFAULTS);
        final Iterator<?> it = note.getStyles().iterator();
        while (it.hasNext()) {
            final Object cur = it.next();
            if (cur instanceof ShapeStyle) {
                ((ShapeStyle) cur).setDescription(noteText);
            }
        }
        return note;

    }

    /**
     * Create a new Note and attach it on the diagram.
     * 
     * @param container
     *            the container diagram.
     * @param noteText
     *            the content of the note.
     * @param preferencesStore
     *            the preferencesStore of the Calling plugin.
     * @return the newly created note.
     */
    public static Node createNote(final Diagram container, final String noteText, final IPreferenceStore preferencesStore) {
        final Node note = GMFNotationHelper.createNote(container, noteText);
        if (note instanceof Shape) {
            Shape shape = (Shape) note;
            shape.setDescription(noteText);
            RGB fillRGB = PreferenceConverter.getColor(preferencesStore, IPreferenceConstants.PREF_NOTE_FILL_COLOR);
            int fillColor = FigureUtilities.RGBToInteger(fillRGB);
            shape.setFillColor(fillColor);
            RGB lineRGB = PreferenceConverter.getColor(preferencesStore, IPreferenceConstants.PREF_NOTE_LINE_COLOR);
            int lineColor = FigureUtilities.RGBToInteger(lineRGB);
            shape.setLineColor(lineColor);
        }
        return note;

    }

    /**
     * Create a LayoutPosition instance from a set of integer values.
     * 
     * @param x
     *            x position.
     * @param y
     *            y position .
     * @param width
     *            layout width.
     * @param height
     *            layout height.
     * @return the newly created {@link LayoutConstraint}.
     */
    public static LayoutConstraint createLayoutPosition(final BigInteger x, final BigInteger y, final BigInteger width, final BigInteger height) {
        final Bounds layout = NotationFactory.eINSTANCE.createBounds();
        layout.setX(x.intValue());
        layout.setY(y.intValue());
        layout.setWidth(width.intValue());
        layout.setHeight(height.intValue());
        return layout;
    }

    /**
     * Browse the given diagram to find a GMF Node targeting the given element.
     * 
     * @param diagram
     *            a GMF Diagram.
     * @param modelElement
     *            any element.
     * @return a Node targeting the model element if found, null otherwise.
     */
    public static Node findGMFNode(final Diagram diagram, final EObject modelElement) {
        final Iterator<EObject> it = diagram.eAllContents();
        while (it.hasNext()) {
            final EObject cur = it.next();
            if (cur instanceof Node) {
                if (((Node) cur).getElement() == modelElement) {
                    return (Node) cur;
                }
            }
        }
        return null;
    }

    /**
     * Create a new note attachment and append it on the diagram.
     * 
     * @param note
     *            : the note .
     * @param target
     *            : the GMF node to attach to.
     */
    public static void createNoteAttachment(final Node note, final Node target) {
        ViewService.createEdge(note, target, ViewType.NOTEATTACHMENT, PreferencesHint.USE_DEFAULTS);
    }

    /**
     * Computes and returns the absolute location of the specified {@link Node}.
     * Returns (0,0) if the node is <code>null</code>.
     * 
     * @param node
     *            the node.
     * @return the absolute location of the specified {@link Node}.
     */
    public static Point getAbsoluteLocation(final Node node) {
        final Point location = new Point(0, 0);
        EObject current = node;
        //
        // Iterates over all parents.
        while (current instanceof Node) {
            if (((Node) current).getLayoutConstraint() instanceof Location) {
                final Location nodeLocation = (Location) ((Node) current).getLayoutConstraint();
                location.x += nodeLocation.getX();
                location.y += nodeLocation.getY();
            }
            current = current.eContainer();
        }
        return location;
    }

    /**
     * Get a copy.
     * 
     * @param bounds
     *            Bounds to copy
     * 
     * @return the copy
     */
    public static Bounds getCopy(Bounds bounds) {
        Bounds copy = NotationFactory.eINSTANCE.createBounds();
        copy.setX(bounds.getX());
        copy.setY(bounds.getY());
        copy.setWidth(bounds.getWidth());
        copy.setHeight(bounds.getHeight());
        return copy;
    }

    /**
     * Get a copy.
     * 
     * @param location
     *            Location to copy
     * 
     * @return the copy
     */
    public static Location getCopy(Location location) {
        Location copy = NotationFactory.eINSTANCE.createLocation();
        copy.setX(location.getX());
        copy.setY(location.getY());
        return copy;
    }

    /**
     * Get a copy.
     * 
     * @param size
     *            Size to copy
     * 
     * @return the copy
     */
    public static Size getCopy(Size size) {
        Size copy = NotationFactory.eINSTANCE.createSize();
        copy.setWidth(size.getWidth());
        copy.setHeight(size.getHeight());
        return copy;
    }
}
